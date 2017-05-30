package com.sea.handler.test;

import gen_b2g.serv.bean.ReturnStatus;
import gen_b2g.test.CallTestI;
import gen_b2g.test.bean.NString;

import com.bowlong.netty.bio2.TcpChannel;

public class TestClientImpl extends CallTestI {

	public TestClientImpl(TcpChannel chn) {
		super(chn);
	}

	@Override
	public void onTest(NString reslut, ReturnStatus val) throws Exception {
		System.out.println(reslut.strV);
	}

}
