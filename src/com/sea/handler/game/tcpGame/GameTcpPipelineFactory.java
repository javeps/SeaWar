package com.sea.handler.game.tcpGame;

import static org.jboss.netty.channel.Channels.pipeline;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.util.HashedWheelTimer;
import org.jboss.netty.util.Timer;

import com.sea.handler.B2Decoder;

public class GameTcpPipelineFactory implements ChannelPipelineFactory {
	static Timer timer = new HashedWheelTimer();

	// static IpFilterRuleList rules=new IpFilterRuleList("-n:192.168.2.222");

	// static OrderedMemoryAwareThreadPoolExecutor e = new
	// OrderedMemoryAwareThreadPoolExecutor(
	// 16, 0, 0);
	// static ExecutionHandler executionHandler = new ExecutionHandler(e);

	public ChannelPipeline getPipeline() throws Exception {
		// Create a default pipeline implementation.
		ChannelPipeline pipeline = pipeline();

		// Uncomment the following line if you want HTTPS
		// SSLEngine engine =
		// SecureChatSslContextFactory.getServerContext().createSSLEngine();
		// engine.setUseClientMode(false);
		// pipeline.addLast("ssl", new SslHandler(engine));
		// pipeline.addLast("decoder", new HttpRequestDecoder());
		// Uncomment the following line if you don't want to handle HttpChunks.
		// pipeline.addLast("aggregator", new HttpChunkAggregator(1048576));
		// pipeline.addLast("encoder", new HttpResponseEncoder());
		// Remove the following line if you don't want automatic content
		// compression.
		// pipeline.addLast("deflater", new HttpContentCompressor());
		// pipeline.addLast("handler", new WebHandler());
		// pipeline.addLast("timeout", new IdleHandler(timer,600));
		// IpFilterRuleHandler firewall = new IpFilterRuleHandler();
		// firewall.addAll(rules);
		// pipeline.addLast("firewall", firewall);
		// pipeline.addLast("executor", executionHandler);
		pipeline.addLast("decoder", new B2Decoder());
		// pipeline.addLast("tcp", new TcpHandle());
		pipeline.addLast("handler", new GameTcpHandle());
		return pipeline;
	}
}
