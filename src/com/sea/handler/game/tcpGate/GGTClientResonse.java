package com.sea.handler.game.tcpGate;

import gen_b2g.gamegate.CallGameGateI;

import java.io.Serializable;
import java.util.Map;

import org.jboss.netty.channel.Channel;

import com.bowlong.bio2.B2Helper;
import com.bowlong.netty.bio2.TcpChannel;
import com.bowlong.util.NewMap;
import com.sea.handler.response.ResponseTcp;
import com.sea.service.WebToGameImpl;

public class GGTClientResonse implements Serializable {

	private static final long serialVersionUID = 1L;

	private GGTClientResonse() {
	}

	static GGTClientResonse _self;

	public static GGTClientResonse getInstance() {
		if (_self == null)
			_self = new GGTClientResonse();
		return _self;
	}
	
	private TcpChannel getTcpByChannnel(final Channel channel)
			throws Exception {

		TcpChannel tcp = new TcpChannel() {

			@Override
			public void send(byte[] buff) throws Exception {
				ResponseTcp.send(channel, buff);
			}

			@Override
			public void send(Map<Object, Object> params) throws Exception {
				ResponseTcp.send(channel, params);
			}
		};
		return tcp;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public void _dispIssue(byte[] buff,Channel channel) throws Exception{
		NewMap<String, Object> map_ = B2Helper.toMap(buff);
		TcpChannel chn = getTcpByChannnel(channel);
		CallGameGateI clientGameGate = new WebToGameImpl(chn);
		clientGameGate.disp(map_);
	}
}
