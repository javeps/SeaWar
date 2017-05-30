package com.sea.handler.game.tcpGate;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipelineFactory;

import com.sea.content.AppContext;
import com.sea.handler.BootstrapSocketClient;

public class GGTClientBootstrap implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(GGTClientBootstrap.class);

	private GGTClientBootstrap() {
	}

	static GGTClientBootstrap _self;

	static ClientBootstrap _clientBstrap;

	public static GGTClientBootstrap getInstanse() {
		if (_self == null) {
			_self = new GGTClientBootstrap();
			_clientBstrap = initClient();
		}
		return _self;
	}

	static ClientBootstrap initClient() {
		boolean nodelay = true;
		boolean alive = false;
		ChannelPipelineFactory pipeline = new GGTClientPipelineFactory();
		return BootstrapSocketClient.initClient(nodelay, alive, pipeline);
	}

	ChannelFuture future = null;
	
	protected void conectClient() {
		String host = AppContext.GATE_SERVER();
		int port = AppContext.GATE_PORT_TCP();
		future = BootstrapSocketClient.connetServer(host, port, _clientBstrap);
	}

	public void timerToConnect() {
		boolean isCanCon = false;
		if(future == null){
			isCanCon = true;
		}else{
			Channel chn = future.getChannel();
			isCanCon  = chn == null || !chn.isConnected();
		}
		if(isCanCon){
			conectClient();
		}
	}
}