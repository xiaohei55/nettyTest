package netty01;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;


/**
 * @Author yufeng.lin@ucarinc.com
 * @Date 2019年9月2日 0002 10:46:48
 * @Version 1.0
 * @Description 描述:浏览器发起http请求不会直接进入关闭流程；直到整个浏览器进程关闭后才会进入关闭流程; 因为这里居于http1.1 实现长链接 浏览器不会自动关闭
 **/
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {


    int times = 0;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        //http 基于tcp有3次握手协议 这里看到进入两次
//        System.out.print("enter times:" + ++times  );
//        System.out.println(ctx);
//        System.out.println(msg);

        // 从通道上获取远程的请求地址信息！！！
        System.out.println(ctx.channel().remoteAddress());

        if (msg instanceof HttpRequest) {
            HttpRequest httpRequest = (HttpRequest) msg;
            System.out.println(" 请求方法名:" + httpRequest.method().name());
            if ("/favicon.ico".equalsIgnoreCase(httpRequest.uri())) {
                System.out.println("浏览器请求图标！");
            }

            ByteBuf content = Unpooled.copiedBuffer("hello world", CharsetUtil.UTF_8);
            //这里居于http1.1 实现长链接 浏览器不会自动关闭
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_0, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            ctx.writeAndFlush(response);
        }


    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel active");
        super.channelActive(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelRegistered");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive");
        super.channelInactive(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelUnregistered");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete");
        super.channelReadComplete(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded");
        super.handlerAdded(ctx);
    }
}
