package com.sea.handler.test;

import gen_b2g.test.TestI;

import java.util.Map;
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

import com.bowlong.bio2.B2Helper;
import com.bowlong.netty.bio2.TcpChannel;
import com.bowlong.util.NewMap;
import com.sea.handler.response.ResponseTcp;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class GateTestHandle extends SimpleChannelHandler {

	static Log log = LogFactory.getLog(GateTestHandle.class);
	static ExecutorService pool = Executors.newCachedThreadPool();
	static TestI sever = new TestServerImpl();
	static final Runtime runtime = Runtime.getRuntime();

	// 正常关闭
	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		super.channelClosed(ctx, e);
		System.out.println("======测试入口正常关闭========" + ctx.getChannel().getId());
	}

	// 异常关闭
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		super.exceptionCaught(ctx, e);
		System.out.println("======测试入口异常关闭========" + ctx.getChannel().getId());
	}

	// 链接挂起
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		super.channelConnected(ctx, e);
		System.out.println("======测试入口链接挂起========" + ctx.getChannel().getId());
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
						long freeMem1 = runtime.freeMemory();
						System.out.println("====== 测试入口接收数据 ========");
						Object o = e.getMessage();
						byte[] b = (byte[]) o;
						NewMap map = B2Helper.toMap(b);
						TestTcpChn testtcp = new TestTcpChn(channel);
						sever.disp(testtcp, map);
						long freeMem2 = runtime.freeMemory();
						long totalMemory = runtime.totalMemory();

						StringBuffer sb = new StringBuffer();
						sb.append("/////////////////////////////////////////").append("\n");
						sb.append("// used Memory  :"
								+ ((freeMem1 - freeMem2) / (1024 * 1024))
								+ "MB").append("\n");
						sb.append("// free Memory  :"
								+ ((freeMem2) / (1024 * 1024)) + "MB").append("\n");
						sb.append("// totalMemory  :"
								+ ((totalMemory) / (1024 * 1024)) + "MB").append("\n");
						sb.append("/////////////////////////////////////////");
						log.info(sb);
					} catch (Exception ec) {
						log.info(UtileTools.ex2s(ec));
					}
				}
			});

		}
	}

}

class TestTcpChn implements TcpChannel {

	public Channel chn;

	public TestTcpChn(Channel chn) {
		this.chn = chn;
	}

	@Override
	public void send(Map<Object, Object> params) throws Exception {
		ResponseTcp.send(chn, params);
	}

	@Override
	public void send(byte[] buff) throws Exception {
		ResponseTcp.send(chn, buff);
	}

}