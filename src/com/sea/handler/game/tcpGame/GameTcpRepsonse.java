package com.sea.handler.game.tcpGame;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.channel.Channel;

import com.bowlong.util.NewMap;
import com.sea.cache.process.ProPlayer;
import com.sea.db.bean.Player;
import com.sea.handler.response.ResponseTcp;
import com.sea.logic.logicOperate.LogicPlayer;

@SuppressWarnings("all")
public class GameTcpRepsonse extends GameTcpAbsRepsonse {

	static Log log = LogFactory.getLog(GameTcpRepsonse.class);

	public GameTcpRepsonse(Channel chn, NewMap map) {
		super(chn, map);
	}

	@Override
	public void send(Map<Object, Object> params) throws Exception {
		ResponseTcp.send(chn, params);
	}

	@Override
	public void send(byte[] buff) throws Exception {
		ResponseTcp.send(chn, buff);
	}

	@Override
	public void close() {
		Player p = getPlayer();
		if (p != null) {
			LogicPlayer.downLine(p);
		}
	}
}