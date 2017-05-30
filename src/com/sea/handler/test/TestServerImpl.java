package com.sea.handler.test;

import java.io.ByteArrayOutputStream;

import com.bowlong.netty.bio2.TcpChannel;

import gen_b2g.serv.bean.ReturnStatus;
import gen_b2g.test.TestI;
import gen_b2g.test.bean.NString;

public class TestServerImpl extends TestI {

	@Override
	public TcpChannel chn(int XID) throws Exception {
		return null;
	}

	@Override
	public ByteArrayOutputStream getOutStream() {
		return null;
	}

	@Override
	public void freeOutStream(ByteArrayOutputStream baos) {

	}

	@Override
	public ReturnStatus onTest(TcpChannel chn, String paramet, NString reslut,
			ReturnStatus ret) throws Exception {
		System.out.println(paramet);
		byte[] b = new byte[1024];
		for (int i = 0; i < b.length; i++) {
			b[i] = 100;
		}
		String r = new String(b);
		reslut.strV = r;
		return ret;
	}

}
