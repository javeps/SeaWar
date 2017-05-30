package com.sea.begin;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.timeout.ReadTimeoutHandler;
import org.jboss.netty.util.Timer;

public class IdleHandler extends ReadTimeoutHandler {
	public IdleHandler(Timer timer, int timeoutSeconds) {
		super(timer, timeoutSeconds);
	}

	@Override
	protected void readTimedOut(ChannelHandlerContext ctx) throws Exception {
		Channel chn = ctx.getChannel();
		chn.close();
	}
}
