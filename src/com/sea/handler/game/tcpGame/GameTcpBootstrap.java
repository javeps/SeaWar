package com.sea.handler.game.tcpGame;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.channel.ChannelPipelineFactory;

import com.sea.handler.BootstrapSocketServer;

public class GameTcpBootstrap {
	static Log log = LogFactory.getLog(GameTcpBootstrap.class);

	public static void start(final int port) {
		// ServerBootstrap tcpBootstrap = new ServerBootstrap(
		// new NioServerSocketChannelFactory(
		// Executors.newCachedThreadPool(),
		// Executors.newCachedThreadPool()));
		String host = "";
		boolean nodelay = true;
		boolean alive = false;
		ChannelPipelineFactory pipeline = new GameTcpPipelineFactory();
		BootstrapSocketServer.start(host, port, nodelay, alive, pipeline);
	}
}