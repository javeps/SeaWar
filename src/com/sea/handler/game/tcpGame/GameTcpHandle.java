package com.sea.handler.game.tcpGame;

import gen_b2g.serv.SeaWarServiceI;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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
import com.bowlong.util.NewMap;
import com.sea.db.bean.Player;
import com.sea.json.recordError.RecordByte;
import com.sea.logic.BackToClientLogicHandle;
import com.sea.logic.logicOperate.LogicPV;
import com.sea.logic.session.LogicalSession;
import com.sea.logic.session.Session;
import com.sea.service.SeaWarServiceImp;
import com.sea.tools.UtileTools;

//socet 链接
@SuppressWarnings({ "rawtypes" })
public class GameTcpHandle extends SimpleChannelHandler {
	static Log log = LogFactory.getLog(GameTcpHandle.class);
	public static final SeaWarServiceI service = new SeaWarServiceImp();
	// static ScheduledExecutorService SES =
	// Svc.newScheduledThreadPool("TcpReceve", 2);
	static ExecutorService pool = Executors.newCachedThreadPool();

	static public final Map<Integer, GameTcpRepsonse> tcps = new ConcurrentHashMap<Integer, GameTcpRepsonse>();

	// 正常关闭
	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		int tcpKeys = ctx.getChannel().getId();
		System.out.println("======正常关闭========" + tcpKeys);
		tcps.remove(tcpKeys);
		super.channelClosed(ctx, e);
	}

	// 异常关闭
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		int tcpKeys = ctx.getChannel().getId();
		System.out.println("======异常关闭========" + tcpKeys);
		tcps.remove(tcpKeys);
		super.exceptionCaught(ctx, e);
	}

	// 链接挂起
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		System.out.println("======链接挂起========" + ctx.getChannel().getId());
		super.channelConnected(ctx, e);

	}

	// 接收数据
	@Override
	public void messageReceived(final ChannelHandlerContext ctx,
			final MessageEvent e) throws Exception {
		final Channel channel = ctx.getChannel();
		final byte[] buff = (byte[]) e.getMessage();
		synchronized (channel) {
			pool.execute(new Runnable() {
				@Override
				public void run() {
					execute(channel, buff);
				}
			});

		}
	}

	void execute(final Channel chn, final byte[] buff) {
		NewMap params = null;
		String methodName = "";
		try {
			long bgtime = System.currentTimeMillis();
			params = B2Helper.toMap(buff);
			int tcpKey = chn.getId();
			GameTcpRepsonse tcp = tcps.get(tcpKey);
			if (tcp == null) {
				tcp = new GameTcpRepsonse(chn, params);
				tcps.put(tcpKey, tcp);
			} else {
				tcp.resetParams(params);
			}

			methodName = service.disp(tcp, params);
			refreshOutTime(tcp, methodName);
			// PV 监视
			Player p = tcp.getPlayer();
			LogicPV.exceMonitor(p, params);

			long edtime = System.currentTimeMillis();

			System.out.println("==处理完毕，费时:" + (edtime - bgtime) + "(ms),方法:"
					+ methodName + ",参数:" + params);

		} catch (Exception ec) {
			if (methodName != null && !methodName.equals("")) {
				log.info("== method ==" + methodName);
			} else {
				if (params != null) {
					log.info("== map ==" + params);
				} else {
					if (buff != null) {
						RecordByte.getInstanse().write(buff);
					}
				}
			}
			log.error(UtileTools.ex2s(ec));
		}
	}

	// ============== 操作处理 ===============
	private void refreshOutTime(GameTcpRepsonse ctx, final String methodName) {
		if (ctx == null)
			return;
		try {
			boolean isCan = BackToClientLogicHandle
					.isCanDelayOutTime(methodName);
			if (!isCan)
				return;
			int pcid = ctx.getPcid();
			Session session = LogicalSession.getSessionByPcid(pcid);
			if (session == null)
				return;
			session.changeTimeOverdue();

		} catch (Exception ex) {
			log.info(UtileTools.ex2s(ex));
		}
	}
}