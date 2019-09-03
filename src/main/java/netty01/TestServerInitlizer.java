package netty01;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @Author yufeng.lin@ucarinc.com
 * @Date 2019年9月2日 0002 09:59:36
 * @Version 1.0
 * @Description 描述: 给管道添加处理器 ;每接收一个连接就会执行
 **/
public class TestServerInitlizer  extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();
        //传入对象包含解码编码功能
        pipeline.addLast("HttpServerCodec",new HttpServerCodec());
        pipeline.addLast("HttpServerHandler",new TestHttpServerHandler());
    }
}
