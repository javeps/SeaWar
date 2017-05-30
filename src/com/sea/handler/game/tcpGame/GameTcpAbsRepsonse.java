package com.sea.handler.game.tcpGame;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.channel.Channel;

import com.bowlong.netty.bio2.TcpChannel;
import com.bowlong.util.NewMap;
import com.sea.db.bean.Player;
import com.sea.logic.session.LogicalSession;
import com.sea.logic.session.Session;

/**
 * 处理数据，并向客户端发送数据 每个通道上来都会被new一个对象
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("all")
public abstract class GameTcpAbsRepsonse implements TcpChannel {

	static Log log = LogFactory.getLog(GameTcpAbsRepsonse.class);

	public final Channel chn;
	public NewMap params;

	public String hostIP;
	public int sessionId;
	public Session session;
	public int cmd;
	public int ucid;//用户的标识id;
	public int pcid;//角色的标识id;

	public GameTcpAbsRepsonse(Channel chn, NewMap map) {
		this.chn = chn;
		resetParams(map);
	}

	public void resetParams(NewMap map) {
		this.params = map;
		this.sessionId = map.getInt(-100);
		this.hostIP = this.chn.getRemoteAddress().toString();
		if (this.session == null && this.sessionId != 0) {
			this.session = LogicalSession.getSessionBySid(sessionId);
		}
	}

	/** 用于 关闭 时候 数据操作 **/
	public abstract void close();

	// ============== 操作 用的函数封装
	public int getPcid() {
		if (this.session != null) {
			return this.session.getPcid();
		}
		return 0;
	}

	public Player getPlayer() {
		if (this.session != null) {
			return this.session.getPl();
		}
		return null;
	}
}
