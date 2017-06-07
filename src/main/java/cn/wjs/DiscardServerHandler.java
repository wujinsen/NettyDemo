package cn.wjs;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * Handles a server-side channel.
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter { // (1)

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        // Discard the received data silently.
        //((ByteBuf) msg).release(); // (3)
        //ByteBuf implements ReferenceCounted
        ByteBuf in = (ByteBuf) msg;//发送到ByteBuf缓存

        try{
            while(in.isReadable()){
                System.out.print((char)in.readByte());//服务器端打印, 输出读取的内容并转化为字符类型
               System.out.flush();
            //    ctx.writeAndFlush(msg);
            }
        }finally{
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}