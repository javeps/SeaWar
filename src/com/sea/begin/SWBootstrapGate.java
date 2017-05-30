package com.sea.begin;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.util.DateEx;
import com.sea.content.AppContext;
import com.sea.content.Svc;
import com.sea.handler.gate.tcp.GateTcpBootstrap;
import com.sea.handler.gate.web.GateHttpBootstrap;
import com.sea.tools.ExpiredLog4j;

public class SWBootstrapGate extends Svc {
	static Log log = LogFactory.getLog(SWBootstrapGate.class);
	public static Date BEGIN = new Date(); // 开服时间

	static String NAME = "sea.gate";
	static double VER = 1.0;
	static int GATE_PORT_WEB = 8881;
	static int GATE_PORT_TCP = 8882;
	static int SHUTDOWNPORT = 8000;
	static String GATE_HOST = "127.0.0.1";

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
			GATE_HOST = AppContext.GATE_SERVER();
			GATE_PORT_WEB = AppContext.GATE_PORT_WEB();
			GATE_PORT_TCP = AppContext.GATE_PORT_TCP();
			SHUTDOWNPORT = AppContext.SHUTDOWNPORT_GATE();
			// ====end

			loadAll();

			ShutdownGate shutdown = new ShutdownGate(SHUTDOWNPORT);
			shutdown.start();
			// =======向入口发送请求，告知已准备就绪
			GateHttpBootstrap.start(GATE_HOST, GATE_PORT_WEB);
			GateTcpBootstrap.start(GATE_HOST, GATE_PORT_TCP);
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
			sn(sb, "// GATE_PORT_WEB:%s", GATE_PORT_WEB + "");
			sn(sb, "// GATE_PORT_TCP:%s", GATE_PORT_TCP + "");
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
			sn(sb, "// GATE_PORT_WEB:%s", GATE_PORT_WEB + "");
			sn(sb, "// GATE_PORT_TCP:%s", GATE_PORT_TCP + "");
			sn(sb, "// Shutdown  on :%s", SHUTDOWNPORT + "");
			sn(sb, "// Start Time   :%s", sdate + "");
			sn(sb, "// Exception    :%s", e2s(e) + "");
			sn(sb, "/////////////////////////////////////////");
			log.error(sb);
			System.exit(1);
		}
	}

	static void loadAll() {
		boolean isDs = true;
		if (isDs)
			AppContext.dataSource();
	}
}