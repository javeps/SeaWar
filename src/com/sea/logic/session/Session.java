package com.sea.logic.session;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.netty.bio2.TcpChannel;
import com.bowlong.util.DateEx;
import com.sea.cache.jedis.game._PInfoJedis;
import com.sea.cache.process.PInfoAll;
import com.sea.cache.process.ProPlayer;
import com.sea.content.Svc;
import com.sea.db.bean.Player;
import com.sea.handler.game.tcpGame.GameTcpAbsRepsonse;
import com.sea.handler.game.tcpGame.GameTcpRepsonse;
import com.sea.logic.logicEntity.LEAchievement;
import com.sea.logic.logicEntity.LEArmy;
import com.sea.logic.logicEntity.LEAttack;
import com.sea.logic.logicEntity.LEBuild;
import com.sea.logic.logicEntity.LEChat;
import com.sea.logic.logicEntity.LEClan;
import com.sea.logic.logicEntity.LEGameMap;
import com.sea.logic.logicEntity.LEMail;
import com.sea.logic.logicEntity.LEObst;
import com.sea.logic.logicEntity.LEPlayer;
import com.sea.logic.logicEntity.LERequest;
import com.sea.logic.logicOperate.LogicPlayer;

public class Session implements Serializable {

	private static final long serialVersionUID = 1L;
	static Log log = LogFactory.getLog(Session.class);

	static final long liveTime = 6 * DateEx.TIME_MINUTE;

	private long sessionId;
	private int pcid;
	private int ucid;
	private Player pl;
	private PInfoAll pinfo;
	// session 过期时间
	private long timeOverdue;
	// 每个session 与 chn之间绑定一一对应关系
	private TcpChannel chn;

	public long getSessionId() {
		return sessionId;
	}

	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}

	public int getPcid() {
		return pcid;
	}

	public void setPcid(int unid) {
		this.pcid = unid;
	}

	public Player getPl() {
		return pl;
	}

	public void setPl(Player pl) {
		this.pl = pl;
	}

	public long getTimeOverdue() {
		return timeOverdue;
	}

	public void setTimeOverdue(long timeOverdue) {
		this.timeOverdue = timeOverdue;
	}

	public void changeTimeOverdue() {
		long now_time = System.currentTimeMillis();
		this.timeOverdue = now_time + liveTime;
	}

	public TcpChannel getChn() {
		return chn;
	}

	public void setChn(TcpChannel chn) {
		this.chn = chn;
	}

	public int getUcid() {
		return ucid;
	}

	public void setUcid(int ucid) {
		this.ucid = ucid;
	}

	public PInfoAll getPinfo() {
		return pinfo;
	}

	public void setPinfo(PInfoAll pinfo) {
		this.pinfo = pinfo;
	}

	public Session(long sessionId, Player pl, TcpChannel rs) {
		super();
		this.sessionId = sessionId;
		this.pl = pl;
		if (this.pl != null) {
			this.pcid = this.pl.getPcid();
		}
		long now_time = System.currentTimeMillis();
		this.timeOverdue = now_time + liveTime;
		this.chn = rs;
	}

	public Session(long sessionId, GameTcpAbsRepsonse abcChn) {
		super();
		resetSession(sessionId, abcChn);
	}

	/******* 重新刷新 用户 *******/
	public void resetSession(long sessionId, GameTcpAbsRepsonse abcChn) {
		this.sessionId = sessionId;
		this.pcid = abcChn.pcid;
		this.ucid = abcChn.ucid;
		if (this.sessionId != 0 && this.pcid != 0) {
			this.pl = ProPlayer.getPlayerByPcidUcid(pcid, ucid);
		}
		long now_time = System.currentTimeMillis();
		this.timeOverdue = now_time + liveTime;
		this.chn = abcChn;
	}

	// ======

	private boolean isSended = false; // 用于判断是否已经下发了数据

	public void initData() {
		if (this.pl == null)
			return;

		getLeGMMap().clear();

		getLeMark().reset();

		getLeBuild().reset(this.pl.getMaxbuidid());

		getLeAttact().reset(this.pl.getMaxattmcid());

		getLeObst().reset(this.pl.getMaxobstid());

		getLeChat().reset();

		getLeMail().reset(30, 30);

		String clancid = this.pl.getClancid();
		if (!Svc.isEmpty(clancid)) {
			boolean isBol = true;
			getLeClan().reset(isBol);
		}

		initPinfo();

		isSended = false;
	}

	// 初始化用户的信息

	public void initPinfo() {
		if (this.pcid == 0)
			return;

		if (pinfo != null && pinfo.isLoad())
			return;

		this.pinfo = _PInfoJedis.getEnBy(pcid,false);
	}

	public void firstIssused() {
		if (isSended)
			return;
		if (this.pl == null)
			return;

		isSended = true;
		LogicPlayer.changeStatusMonCode(this.pl);
		LogicalSession.pushToClientFirst(getTcpCtx());
	}

	public GameTcpRepsonse getTcpCtx() {
		if (this.chn != null && this.chn instanceof GameTcpRepsonse)
			return (GameTcpRepsonse) this.chn;
		return null;
	}

	public boolean resetPInfo() {
		if (this.pinfo != null) {
			this.pinfo.reset();
			this.pinfo = _PInfoJedis.getEnBy(this.pcid,true);
			return true;
		}
		return false;
	}

	// ================角色玩家===
	private LEPlayer _lePlayer;

	public LEPlayer getLePlayer() {
		if (_lePlayer == null)
			_lePlayer = LEPlayer.newDefault();
		return _lePlayer;
	}

	// ================防止请求被刷===
	private LERequest _leRequest;

	public LERequest getLeRequest() {
		if (_leRequest == null) {
			_leRequest = LERequest.newDefault();
		}
		return _leRequest;
	}

	// ================下发聊天的逻辑===
	private LEChat _leChat;

	public LEChat getLeChat() {
		if (this._leChat == null) {
			this._leChat = LEChat.newDefault();
		}
		return this._leChat;
	}

	// ================下发邮件的逻辑===
	private LEMail _leMail;

	public LEMail getLeMail() {
		if (this._leMail == null) {
			this._leMail = LEMail.newDefault();
		}
		return this._leMail;
	}

	// ===========战斗的逻辑
	private LEAttack _leAttack;

	public LEAttack getLeAttact() {
		if (this._leAttack == null) {
			this._leAttack = LEAttack.newDefault();
		}
		return this._leAttack;
	}

	// ===========建筑逻辑
	private LEBuild _leBuild;

	public LEBuild getLeBuild() {
		if (this._leBuild == null) {
			this._leBuild = LEBuild.newDefault();
		}
		return this._leBuild;
	}

	// ===========其他物体(墙，障碍，树，装饰)逻辑
	private LEObst _leObst;

	public LEObst getLeObst() {
		if (this._leObst == null) {
			this._leObst = LEObst.newDefault();
		}
		return this._leObst;
	}

	// ===========兵
	private LEArmy _leArmy;

	public LEArmy getLeArmy() {
		if (this._leArmy == null) {
			this._leArmy = LEArmy.newDefault();
		}
		return this._leArmy;
	}

	// ===========成就
	private LEAchievement _leMark;

	public LEAchievement getLeMark() {
		if (this._leMark == null) {
			this._leMark = LEAchievement.newDefault();
		}
		return this._leMark;
	}

	// ===========联盟
	private LEClan _leClan;

	public LEClan getLeClan() {
		if (this._leClan == null) {
			this._leClan = LEClan.newDefault();
		}
		return this._leClan;
	}

	// ===========游戏的地图对象
	private LEGameMap leGMMap;

	public LEGameMap getLeGMMap() {
		if (this.leGMMap == null) {
			this.leGMMap = LEGameMap.newDefault();
		}
		return this.leGMMap;
	}
}
