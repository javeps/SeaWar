package com.sea.begin;

import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sea.content.AppContext;
import com.sea.logic.logicOperate.LogicAtomicInteger;
import com.sea.timer.TimerDownLine;
import com.sea.timer.TimerSave;

//SHUTDOWN
public class Shutdown extends Thread {
	static Log log = LogFactory.getLog(Shutdown.class);

	public ServerSocket ssocket = null;

	public Shutdown(int port) throws Exception {
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
	void beforeShutDown() throws Exception {
		TimerDownLine.downLineAllWhenShutDown();
		LogicAtomicInteger.save();
		TimerSave.saveAndClear();
		AppContext.closeJedisPool();
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		Socket sss = new Socket("", 9990);
		OutputStream out = sss.getOutputStream();
		out.write("he".getBytes());
		out.flush();
		out.close();
	}
}