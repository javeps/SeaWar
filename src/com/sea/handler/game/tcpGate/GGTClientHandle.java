package com.sea.handler.game.tcpGate;

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

public class GGTClientHandle extends SimpleChannelHandler {

	static Log log = LogFactory.getLog(GGTClientHandle.class);
	static ExecutorService pool = Executors.newCachedThreadPool();

	// 正常关闭
	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		super.channelClosed(ctx, e);
		System.out.println("======GameConGate正常关闭========" + ctx.getChannel().getId());
	}

	// 异常关闭
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		super.exceptionCaught(ctx, e);
		System.out.println("======GameConGate异常关闭========" + ctx.getChannel().getId());
	}

	// 链接挂起
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		super.channelConnected(ctx, e);
		System.out.println("======GameConGate链接挂起========" + ctx.getChannel().getId());
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
						System.out.println("======GameConGate接收数据========");
						Object o = e.getMessage();
						byte[] buff = (byte[]) o;
						GGTClientResonse.getInstance()._dispIssue(buff, channel);
					} catch (Exception ec) {
						log.info(UtileTools.ex2s(ec));
					}
				}
			});

		}
	}

}
