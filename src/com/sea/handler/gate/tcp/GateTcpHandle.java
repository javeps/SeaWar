package com.sea.handler.gate.tcp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import com.sea.tools.UtileTools;

public class GateTcpHandle extends SimpleChannelHandler {

	static Log log = LogFactory.getLog(GateTcpHandle.class);
	static ExecutorService pool = Executors.newCachedThreadPool();
	
	// 正常关闭
	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		super.channelClosed(ctx, e);
		System.out.println("======GateTcp正常关闭========" + ctx.getChannel().getId());
		NotifyGateGame.getInstance().removeKey(ctx.getChannel().getId());
	}

	// 异常关闭
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		super.exceptionCaught(ctx, e);
		System.out.println("======GateTcp异常关闭========" + ctx.getChannel().getId());
	}

	// 链接挂起
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		super.channelConnected(ctx, e);
		System.out.println("======GateTcp链接挂起========" + ctx.getChannel().getId());
		NotifyGateGame.getInstance().addGameChannel(ctx);
	}

	// 接收数据
	@Override
	public void messageReceived(final ChannelHandlerContext ctx,
			final MessageEvent e) throws Exception {
		final Channel channel = ctx.getChannel();
		synchronized (channel) {
			pool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println("====== GateTcp接收数据 ========");
						
					} catch (Exception ec) {
						log.info(UtileTools.ex2s(ec));
					}
				}
			});

		}
	}

}
