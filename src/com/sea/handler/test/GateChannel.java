package com.sea.handler.test;

import gen_b2g.test.CallTestI;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;

import com.bowlong.bio2.B2Helper;
import com.bowlong.bio2.B2InputStream;
import com.bowlong.bio2.B2OutputStream;
import com.bowlong.lang.ThreadEx;
import com.bowlong.netty.bio2.TcpChannel;
import com.bowlong.util.NewMap;

@SuppressWarnings("rawtypes")
public class GateChannel implements TcpChannel {

	final String host;
	final int port;

	boolean isConnection = false;
	Socket socket = null;
	CallTestI _cgi;

	InputStream in = null;
	OutputStream out = null;

	public GateChannel(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void setLoop(CallTestI cgi) {
		this._cgi = cgi;
	}

	public OutputStream getOutput() throws Exception {
		if (socket == null || in == null || out == null || socket.isClosed()
				|| socket.isInputShutdown() || socket.isOutputShutdown()) {

			socket = new Socket(host, port);
			this.in = socket.getInputStream();
			this.out = socket.getOutputStream();

			isConnection = true;

			startLoop();
		}
		return out;
	}

	public void close() {
		isConnection = false;

		try {
			this.socket.close();
		} catch (Exception e) {
		} finally {
			socket = null;
			in = null;
			out = null;
		}
	}

	private void startLoop() {
		ThreadEx.execute(new Runnable() {
			@Override
			public void run() {
				try {
					while (isConnection || _cgi != null) {
						B2InputStream.readInt(in);
						NewMap map = B2Helper.toMap(in);
						// System.out.println("CALL = " + map);
						_cgi.disp(map);
					}
				} catch (Exception e) {
					// System.out.println("RemoteChannel end.");
					close();
				}
			}
		});
	}

	@Override
	public void send(Map map) throws Exception {
		try {
			byte[] buff = B2Helper.toBytes(map);
			send(buff);
		} catch (Exception e) {
			close();
		}
	}

	@Override
	public void send(byte[] buff) throws Exception {
		OutputStream out = getOutput();
		B2OutputStream.writeInt(out, buff.length);
		out.write(buff);
		out.flush();
	}

}
