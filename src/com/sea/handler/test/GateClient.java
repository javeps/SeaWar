package com.sea.handler.test;

import gen_b2g.test.CallTestI;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sea.content.Svc;

public class GateClient extends Svc {
	static Log log = LogFactory.getLog(GateClient.class);

	static String tetsHost = GateTestBootstrap.host;
	static int testPort = GateTestBootstrap.port;
	static GateChannel chn = new GateChannel(tetsHost, testPort);
	static CallTestI cgi = new TestClientImpl(chn);
	static final Runtime runtime = Runtime.getRuntime();
	
	static {
		chn.setLoop(cgi);
	}
	static ScheduledExecutorService scheduledPool;

	public static void Start() {
		if (scheduledPool == null)
			scheduledPool = Executors.newScheduledThreadPool(1);

		scheduledPool.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {

				try {
					byte[] b = new byte[1024];
					for (int i = 0; i < b.length; i++) {
						b[i] = 110;
					}
					String paramet = new String(b);
					cgi.test(paramet);
				} catch (Exception e) {
					e.printStackTrace();
					chn.close();
				}
			}
		}, 1 * 1000, 10, TimeUnit.MILLISECONDS);

		log.info("Start..");
	}

	public static void main(String[] args) {
		Start();
	}
}
