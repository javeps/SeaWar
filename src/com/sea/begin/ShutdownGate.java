package com.sea.begin;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sea.content.AppContext;

//SHUTDOWN
public class ShutdownGate extends Thread {
	static Log log = LogFactory.getLog(ShutdownGate.class);

	public ServerSocket ssocket = null;

	public ShutdownGate(int port) throws Exception {
		InetAddress addr = InetAddress.getByName("127.0.0.1");

		try { // 关闭其他进程
			new Socket(addr, port);
			Thread.sleep(1000);
		} catch (Exception e) {
		}

		ssocket = new ServerSocket(port, 2, addr);
	}

	@Override
	public void run() {
		try {
			ssocket.accept();
			beforeShutDown();
			System.exit(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 在下线之前
	void beforeShutDown() {
		AppContext.closeJedisPool();
	}
}