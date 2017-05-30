package com.sea.handler.gate.tcp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.channel.ChannelPipelineFactory;

import com.sea.content.Svc;
import com.sea.handler.BootstrapSocketServer;

public class GateTcpBootstrap extends Svc {
	static Log log = LogFactory.getLog(GateTcpBootstrap.class);

	public static void start(final String host, final int port) {
		boolean nodelay = true;
		boolean alive = true;
		ChannelPipelineFactory pipeline = new GateTcpPipelineFactory();
		BootstrapSocketServer.start(host, port, nodelay, alive, pipeline);
	}
}
