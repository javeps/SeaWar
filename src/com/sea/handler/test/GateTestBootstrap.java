package com.sea.handler.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.channel.ChannelPipelineFactory;

import com.sea.content.Svc;
import com.sea.handler.BootstrapSocketServer;

public class GateTestBootstrap extends Svc {
	static Log log = LogFactory.getLog(GateTestBootstrap.class);

	public static final String host = "";
	public static final int port = 10000;

	public static void start() {
		boolean nodelay = true;
		boolean alive = true;
		ChannelPipelineFactory pipeline = new GateTestPipelineFactory();
		BootstrapSocketServer.start(host, port, nodelay, alive, pipeline);
	}
	public static void main(String[] args) {
		start();
	}
}
