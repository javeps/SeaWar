package com.sea.begin;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.util.DateEx;
import com.sea.cache.jedis.admin.AdminJson;
import com.sea.cache.process.ProPlayer;
import com.sea.content.AppContext;
import com.sea.content.Svc;
import com.sea.handler.game.tcpGame.GameTcpBootstrap;
import com.sea.handler.game.web.GameHttpBootstrap;
import com.sea.logic.logicOperate.LogicAtomicInteger;
import com.sea.timer.TimerChangeNew;
import com.sea.timer.TimerDay;
import com.sea.timer.TimerDownLine;
import com.sea.timer.TimerGmConGt;
import com.sea.timer.TimerRank;
import com.sea.timer.TimerSave;
import com.sea.tools.ExpiredLog4j;
import com.sea.tools.NameRandom;

public class SWBootstrap extends Svc {
	static Log log = LogFactory.getLog(SWBootstrap.class);
	public static Date BEGIN = new Date(); // 开服时间

	static String NAME = "sea";
	static double VER = 1.0;
	static int WEB_PORT = 8879;
	static int TCP_PORT = 8881;
	static int SHUTDOWNPORT = 8000;

	static final Runtime runtime = Runtime.getRuntime();

	public static void main(String[] args) {

		try {
			long l1 = now();
			String sdate = tFmt(new Date());
			long freeMem1 = runtime.freeMemory();

			// ////////////////////////////////////
			// // 初始化数据
			NAME = AppContext.NAME();
			VER = AppContext.VER();
			TCP_PORT = AppContext.TCP_PORT();
			WEB_PORT = AppContext.WEB_PORT();
			SHUTDOWNPORT = AppContext.SHUTDOWNPORT();
			// ====end

			loadAll();

			Shutdown shutdown = new Shutdown(SHUTDOWNPORT);
			shutdown.start();
			// =======向入口发送请求，告知已准备就绪
			GameTcpBootstrap.start(TCP_PORT);
			GameHttpBootstrap.start("", WEB_PORT);

			// =======end
			// ExecutorService executor = Executors.newCachedThreadPool();
			// NioServerBossPool bossPool = new NioServerBossPool(executor, 2);
			// NioWorkerPool workerPool = new NioWorkerPool(executor, 32);
			//
			// ServerBootstrap webBootstrap = new ServerBootstrap(
			// new NioServerSocketChannelFactory(bossPool, workerPool));
			//
			// webBootstrap.setPipelineFactory(new WebPipelineFactory());
			// InetSocketAddress web_addr = new InetSocketAddress(WEB_PORT);
			// webBootstrap.setOption("child.tcpNoDelay", true);
			// webBootstrap.setOption("child.keepAlive", true);
			// webBootstrap.bind(web_addr);
			// 日志清理器
			ExpiredLog4j.beginIt();

			// ////////////////////////////////////
			long freeMem2 = runtime.freeMemory();
			long totalMemory = runtime.totalMemory();

			String sdate2 = tFmt(new Date());
			long l2 = now();
			long df = l2 - l1;

			String strStarUp = "";
			if (df > DateEx.TIME_SECOND) {
				df = (long) Math.ceil((double) df / DateEx.TIME_SECOND);
				strStarUp = df + " 秒(s)";
			} else {
				strStarUp = df + " 毫秒(ms)";
			}

			StringBuffer sb = new StringBuffer();
			sn(sb, "");
			sn(sb, "/////////////////////////////////////////");
			sn(sb, "// Applicatin   :%s v: %.2f", NAME, VER);
			sn(sb, "// WEB_PORT on :%s", WEB_PORT + "");
			sn(sb, "// TCP_PORT on :%s", TCP_PORT + "");
			sn(sb, "// Shutdown  on :%s", SHUTDOWNPORT + "");
			sn(sb, "// used Memory  :%s",
					((freeMem1 - freeMem2) / (1024 * 1024)) + "MB");
			sn(sb, "// free Memory  :%s", ((freeMem2) / (1024 * 1024)) + "MB");
			sn(sb, "// totalMemory  :%s", ((totalMemory) / (1024 * 1024))
					+ "MB");
			sn(sb, "// Start Time   :%s", sdate + "");
			sn(sb, "// startup      :%s", strStarUp);
			sn(sb, "// start time2  :%s", sdate2 + "");
			sn(sb, "/////////////////////////////////////////");
			log.info(sb);

		} catch (Exception e) {
			String sdate = tFmt(new Date());
			StringBuffer sb = new StringBuffer();
			sn(sb, "");
			sn(sb, "/////////////////////////////////////////");
			sn(sb, "// Applicatin   :%s", NAME + "");
			sn(sb, "// Applicatin   :%s v:%.2f", NAME, VER);
			sn(sb, "// WEB_PORT on :%s", WEB_PORT + "");
			sn(sb, "// TCP_PORT on :%s", TCP_PORT + "");
			sn(sb, "// Shutdown  on :%s", SHUTDOWNPORT + "");
			sn(sb, "// Start Time   :%s", sdate + "");
			sn(sb, "// Exception    :%s", e2s(e) + "");
			sn(sb, "/////////////////////////////////////////");
			log.error(sb);
			System.exit(1);
		}
	}

	static void loadAll() {
		// 老外服务器为false
		boolean isDs = true;
		if (isDs)
			AppContext.dataSource();

		LogicAtomicInteger.init();

		// 分区处理删除数据(国外渠道，true,国内false);
		// TimerSubarea.startTimer(true);
		// _HanderErrorUP.handlerAll();

		AdminJson.initSomeAdmin();

		ProPlayer.loadAll();
		LogicAtomicInteger.resetAll(-1);

		// 只是本次执行
		// TimerDay.delAllPattern();
		// TimerDay.delMailAndChatPattern();
		TimerRank.repairByPlayer();// 开启修复就不用加载辅助AssistUserPlayerJedis
		TimerRank.repairByUser(); // 用于修复用户的辅助表,不能与_HanderErrorUP同时在
		
		// AssistUserPlayerJedis.initMapNow();
		TimerRank.getInstance().recordRank(null, true);
		NameRandom.initNames();

		TimerDownLine.startTimer();
		TimerRank.startTimer();
		TimerDay.startTimer();
		TimerGmConGt.startTimer();
		TimerSave.startTimer();
		TimerChangeNew.startTimer();
	}
}