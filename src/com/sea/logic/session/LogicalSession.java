package com.sea.logic.session;

import gen_b2g.serv.bean.ConstantType;
import gen_b2g.serv.bean.NInt;
import gen_b2g.serv.bean.ReturnStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;

import com.bowlong.netty.bio2.TcpChannel;
import com.bowlong.util.DateEx;
import com.bowlong.util.NewMap;
import com.sea.content.AppContext;
import com.sea.content.Svc;
import com.sea.db.bean.Player;
import com.sea.handler.game.tcpGame.GameTcpAbsRepsonse;
import com.sea.handler.game.tcpGame.GameTcpRepsonse;
import com.sea.logic.BackToClientCofig;
import com.sea.logic.logicOperate.LogicAchievement;
import com.sea.logic.logicOperate.LogicChat;
import com.sea.logic.logicOperate.LogicClan;
import com.sea.logic.logicOperate.LogicMail;
import com.sea.logic.logicOperate.LogicPlayer;
import com.sea.tools.UtileTools;

/**
 * 回话操作
 * 
 * @author Administrator
 * 
 */
public class LogicalSession extends Svc implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(LogicalSession.class);

	// 添加jedis原子性质
	static private final String keyJedisAtomic = "atomicKey";

	static public void setAtomicIdJedis(long nv) {
		Jedis jedis = null;
		try {
			jedis = AppContext.getJedis();
			jedis.hset(keyJedisAtomic, "sessionId", String.valueOf(nv));
		} catch (Exception e) {
			log.info(e2s(e));
		} finally {
			AppContext.returnJedis(jedis);
		}
	}

	static public long getAtidValJedis() {
		Jedis jedis = null;
		long result = 0;
		try {
			jedis = AppContext.getJedis();
			String val = jedis.hget(keyJedisAtomic, "sessionId");
			result = Long.parseLong(val);
		} catch (Exception e) {
			log.info(e2s(e));
		} finally {
			AppContext.returnJedis(jedis);
		}
		return result;
	}

	static public long getAtomicIdJedis() {
		Jedis jedis = null;
		long result = 0;
		try {
			jedis = AppContext.getJedis();
			result = jedis.hincrBy(keyJedisAtomic, "sessionId", 1);
		} catch (Exception e) {
			log.info(e2s(e));
		} finally {
			AppContext.returnJedis(jedis);
		}
		return result;
	}

	// 添加数据的原子性质
	static public final AtomicLong auto = new AtomicLong(300);

	static public void setAtomicId(long nv) {
		auto.set(nv);
	}

	static public long getAtidVal() {
		return auto.get();
	}

	static public long getAtomicId() {
		return auto.addAndGet(1);
	}

	static public final Map<Long, Session> mapSid2Session = new ConcurrentHashMap<Long, Session>();
	static Map<Integer, Long> mapPcid2Sid = new ConcurrentHashMap<Integer, Long>();
	static Map<Long, Integer> mapSid2Pcid = new ConcurrentHashMap<Long, Integer>();

	static public Session addSession(TcpChannel chn) {
		if (chn == null)
			return null;

		if (!(chn instanceof GameTcpAbsRepsonse))
			return null;

		GameTcpAbsRepsonse rs = (GameTcpAbsRepsonse) chn;
		int pcid = rs.pcid;
		if (pcid == 0)
			return null;

		Session session = null;
		long sessionId = getAtomicId();
		boolean isHas = mapPcid2Sid.containsKey(pcid);
		if (isHas) {
			long presessionId = mapPcid2Sid.get(pcid);
			session = mapSid2Session.get(presessionId);
			GameTcpRepsonse preTcp = session.getTcpCtx();
			preTcp.session = null;

			session.resetSession(sessionId, rs);

			mapSid2Pcid.remove(presessionId);
			mapSid2Session.remove(presessionId);
		} else {
			session = new Session(sessionId, rs);
		}

		mapSid2Session.put(sessionId, session);
		mapSid2Pcid.put(sessionId, pcid);
		mapPcid2Sid.put(pcid, sessionId);
		return session;
	}

	/** 根据sessionId 取得session **/
	static public Session getSessionBySid(long sessionId) {
		if (sessionId == 0)
			return null;
		boolean isHas = mapSid2Session.containsKey(sessionId);
		if (!isHas)
			return null;
		return mapSid2Session.get(sessionId);
	}

	/** 根据pcid 取得session **/
	static public Session getSessionByPcid(int pcid) {
		if (pcid == 0)
			return null;
		boolean isHas = mapPcid2Sid.containsKey(pcid);
		if (!isHas)
			return null;
		long sessionId = mapPcid2Sid.get(pcid);
		return mapSid2Session.get(sessionId);
	}

	/** 根据sessionId 移除session **/
	static public void removeSession(long sessionId) {
		boolean isHas = mapSid2Session.containsKey(sessionId);
		if (!isHas)
			return;
		int unid = mapSid2Pcid.get(sessionId);
		mapSid2Session.remove(sessionId);
		mapSid2Pcid.remove(sessionId);
		mapPcid2Sid.remove(unid);
	}

	/** 根据pcid 移除session **/
	static public void removeSession(int pcid) {
		boolean isHas = mapPcid2Sid.containsKey(pcid);
		if (!isHas)
			return;
		long sessionId = mapPcid2Sid.get(pcid);
		mapSid2Session.remove(sessionId);
		mapSid2Pcid.remove(sessionId);
		mapPcid2Sid.remove(pcid);
	}

	/** 取得在线人的pcids **/
	static public List<Integer> getListOnlineIds() {
		List<Integer> result = new CopyOnWriteArrayList<Integer>();
		Set<Integer> ids = mapPcid2Sid.keySet();
		result.addAll(ids);
		return result;
	}

	/** 取得在线人的数量 **/
	static public int getNumOnline() {
		List<Integer> result = getListOnlineIds();
		if (isEmpty(result))
			return 0;
		return result.size();
	}

	/** 取得在线人的sessions **/
	static public List<Session> getListOnlineSessions() {
		List<Session> result = new CopyOnWriteArrayList<Session>();
		for (Entry<Long, Session> entry : mapSid2Session.entrySet()) {
			result.add(entry.getValue());
		}
		return result;
	}

	/** 停服时处理全部在线玩家 **/
	static public void downLineAll() {
		List<Session> result = getListOnlineSessions();
		for (Session session : result) {
			GameTcpRepsonse ctx = session.getTcpCtx();
			if (ctx == null)
				continue;
			ctx.close();
		}
	}

	static private Map<Integer, Integer> recordMap = new HashMap<Integer, Integer>();

	static private void recordOnlineNum(int len) {
		Date date = new Date();
		int houre = DateEx.hour(date);
		if (houre == 0) {
			recordMap.clear();
		}

		int recordNum = 0;
		if (recordMap.containsKey(houre)) {
			recordNum = recordMap.get(houre);
		}
		boolean isChange = recordNum < len;
		if (!isChange)
			return;
		recordMap.put(houre, len);
		if (len < 10)
			return;
		String t = DateEx.now(DateEx.fmt_yyyy_MM_dd_HH_mm_ss);
		StringBuffer buffer = new StringBuffer();
		buffer.append("==时间:").append(t).append(",在线人数:").append(len);
		String info = buffer.toString();
		buffer.setLength(0);
		log.info(info);
	}

	/** 处理玩家下线 **/
	static public void downLine() {
		long nwtime = System.currentTimeMillis();
		List<Session> result = getListOnlineSessions();
		int len = result.size();

		List<Session> rmlist = new ArrayList<Session>();
		for (Session session : result) {
			GameTcpRepsonse ctx = session.getTcpCtx();
			if (ctx == null)
				continue;
			if (session.getTimeOverdue() > nwtime)
				continue;
			ctx.close();
			rmlist.add(session);
		}
		for (Session session : rmlist) {
			removeSession(session.getSessionId());
		}
		recordOnlineNum(len);
	}

	// ================ 下发
	static String heart = "heart";

	static public void pushToClientFirst(GameTcpRepsonse ctx) {
		pushToClientFirst(ctx, heart);
	}

	static public void pushToClientFirst(GameTcpRepsonse ctx,
			final String methodName) {
		LogicPlayer.issuedData(ctx, methodName);
		LogicChat.issuedChat(ctx, methodName);
		LogicMail.issuedMail(ctx, methodName);
		LogicAchievement.issuedMarks(ctx, methodName);
		LogicClan.issuedClanAll(ctx, methodName);
	}

	// 帐号登录异常(其他地方登录)
	static public void playerLoginOther(GameTcpRepsonse ctx) {
		try {
			heartPlayer(ctx, 0, BackToClientCofig.LOGIN_OTHER_MORE);
		} catch (Exception e) {
			log.error(UtileTools.ex2s(e));
		}
	}

	// 联盟解散
	static public void clanDisband(List<Integer> origin) {
		if (origin == null || origin.isEmpty())
			return;
		for (int pcid : origin) {
			refreshOutClan(pcid, true);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	static private void heartPlayer(GameTcpRepsonse ctx, int heatInt, int rstInt)
			throws Exception {
		if (ctx == null || ctx.chn == null || !ctx.chn.isConnected())
			return;
		NInt heat = new NInt();
		heat.val = heatInt;
		ReturnStatus rst = new ReturnStatus();
		rst.succ = rstInt;
		rst.msg = BackToClientCofig.getMsgStr(rstInt);

		Map result = new NewMap();
		result.put(0, 99151942);
		result.put(1, rst.toMap());
		result.put(3198448, heat.toMap());
		ctx.send(result);
	}

	// =====更新其他人的聊天下发 ============
	static public void refreshLEChatTrueForOther(int pcid, int ChatType) {
		List<Session> list = getListOnlineSessions();
		long nwtime = System.currentTimeMillis();
		for (Session session : list) {
			if (session.getPcid() == pcid)
				continue;
			if (session.getTimeOverdue() <= nwtime)
				continue;

			GameTcpRepsonse ctx = session.getTcpCtx();
			if (ctx == null)
				continue;
			session.getLeChat().setIssuedTrue(ChatType);
			LogicChat.issuedChat(ctx, heart);
		}
	}

	// =====更新邮件的下发 ============
	static public void refreshLEMailTrueForOther(int fpcid, int type, int toId) {

		List<Session> list = getListOnlineSessions();
		long nwtime = System.currentTimeMillis();
		for (Session session : list) {
			if (session.getPcid() == fpcid)
				continue;
			if (session.getTimeOverdue() <= nwtime)
				continue;

			GameTcpRepsonse ctx = session.getTcpCtx();
			if (ctx == null)
				continue;

			boolean isOk = false;
			int refreshType = LogicMail.Type_Mail_Player;
			if (type == ConstantType.Type_Mail_System) {
				isOk = true;
				session.getLeMail().setIssuedTrue(refreshType);
			} else {
				int pcid = session.getPcid();
				if (pcid == toId) {
					isOk = true;
					session.getLeMail().setIssuedTrue(refreshType);
				}
			}

			if (isOk) {
				LogicMail.issuedMail(ctx, heart);
			}
		}
	}

	// =====更新资源的下发 ============
	static public void refreshPInfoByPlayer(Player p) {
		if (p == null)
			return;
		int pcid = p.getPcid();
		refreshPInfo(pcid);
	}

	static public void refreshPInfo(int toId) {
		Session session = getSessionByPcid(toId);
		if (session == null)
			return;
		long nwtime = System.currentTimeMillis();
		if (session.getTimeOverdue() <= nwtime)
			return;

		GameTcpRepsonse ctx = session.getTcpCtx();
		if (ctx == null)
			return;

		session.getLePlayer().setCanIssuedPinfo(true);
		LogicPlayer.issuedData(ctx, heart);
	}

	// =====更新用户英雄信息的下发 ============
	static public void refreshLEPHeroTrue(int toId) {

		Session session = getSessionByPcid(toId);
		if (session == null)
			return;
		long nwtime = System.currentTimeMillis();
		if (session.getTimeOverdue() <= nwtime)
			return;

		GameTcpRepsonse ctx = session.getTcpCtx();
		if (ctx == null)
			return;

		session.getLePlayer().setCanIssuedHeros(true);
		LogicPlayer.issuedData(ctx, heart);
	}

	// =====更新联盟信息 ============
	static public void refreshLEClan(int pcid, String ccid, boolean isOther) {
		if (isEmpty(ccid))
			return;
		long nwtime = System.currentTimeMillis();
		List<Session> list = getListOnlineSessions();
		for (Session session : list) {
			if (session.getPl() == null)
				continue;

			if (session.getTimeOverdue() <= nwtime)
				continue;

			GameTcpRepsonse ctx = session.getTcpCtx();
			if (ctx == null)
				continue;

			if (isOther) {
				if (ctx.getPcid() == pcid)
					continue;
			}

			if (!session.getPl().getClancid().equals(ccid))
				continue;

			session.getLeClan().setIsIssuedClan(true);
			LogicClan.issuedClanAll(ctx, heart);
		}
	}

	// =====更新联盟成员信息 ============
	static public void refreshLEClanMember(int pcid, String ccid,
			boolean isOther) {
		if (isEmpty(ccid))
			return;

		long nwtime = System.currentTimeMillis();
		List<Session> list = getListOnlineSessions();
		for (Session session : list) {
			if (session.getPl() == null)
				continue;
			if (session.getTimeOverdue() <= nwtime)
				continue;

			GameTcpRepsonse ctx = session.getTcpCtx();
			if (ctx == null)
				continue;

			if (isOther) {
				if (ctx.getPcid() == pcid)
					continue;
			}

			if (!session.getPl().getClancid().equals(ccid))
				continue;

			session.getLeClan().setIsIssuedClanMember(true);
			LogicClan.issuedClanAll(ctx, heart);
		}
	}

	// =====更新联盟请求信息 ============
	static public void refreshLEClanReqForOther(int pcid, String ccid) {
		if (isEmpty(ccid))
			return;

		long nwtime = System.currentTimeMillis();
		List<Session> list = getListOnlineSessions();
		for (Session session : list) {
			if (session.getPl() == null)
				continue;
			if (session.getTimeOverdue() <= nwtime)
				continue;

			GameTcpRepsonse ctx = session.getTcpCtx();
			if (ctx == null)
				continue;

			if (ctx.getPcid() == pcid)
				continue;

			if (!session.getPl().getClancid().equals(ccid))
				continue;

			session.getLeClan().setIsIssuedClanRequest(true);
			LogicClan.issuedClanAll(ctx, heart);
		}
	}

	// =====更新离开联盟 ============
	static public void refreshOutClan(int pcid, boolean isDisband) {
		try {
			Session session = getSessionByPcid(pcid);
			if (session == null)
				return;

			long nwtime = System.currentTimeMillis();
			if (session.getTimeOverdue() <= nwtime)
				return;

			GameTcpRepsonse ctx = session.getTcpCtx();
			if (ctx == null)
				return;

			session.getLeClan().setIsIssuedClanRequest(true);

			int back = BackToClientCofig.CLAN_Out_U;
			if (isDisband) {
				back = BackToClientCofig.CLAN_Disband;
			}
			heartPlayer(ctx, 0, back);
		} catch (Exception e) {
		}

	}
}
