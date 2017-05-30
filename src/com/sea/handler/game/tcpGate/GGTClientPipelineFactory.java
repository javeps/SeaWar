package com.sea.handler.game.tcpGate;

import static org.jboss.netty.channel.Channels.pipeline;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;

import com.sea.handler.B2Decoder;

public class GGTClientPipelineFactory implements ChannelPipelineFactory{

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = pipeline();
		pipeline.addLast("decoder", new B2Decoder());
		pipeline.addLast("handler", new GGTClientHandle());
		return pipeline;
	}

}
