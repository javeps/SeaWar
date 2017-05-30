package com.sea.handler.gate.tcp;

import static org.jboss.netty.channel.Channels.pipeline;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;

import com.sea.handler.B2Decoder;

public class GateTcpPipelineFactory implements ChannelPipelineFactory {

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = pipeline();
		pipeline.addLast("decoder", new B2Decoder());
		pipeline.addLast("handler", new GateTcpHandle());
		return pipeline;
	}

}
