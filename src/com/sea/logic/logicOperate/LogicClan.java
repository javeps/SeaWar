package com.sea.logic.logicOperate;

import gen_b2g.serv.bean.ConstantType;
import gen_b2g.serv.bean.NClan;
import gen_b2g.serv.bean.NClanMembers;
import gen_b2g.serv.bean.NClanRequests;
import gen_b2g.serv.bean.NClans;
import gen_b2g.serv.bean.NRankClans;
import gen_b2g.serv.bean.ReturnStatus;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.util.DateEx;
import com.bowlong.util.NewMap;
import com.sea.cache.process.ProClan;
import com.sea.content.Svc;
import com.sea.db.bean.Clan;
import com.sea.db.bean.Clan_member;
import com.sea.db.bean.Clan_request;
import com.sea.db.bean.Player;
import com.sea.db.entity.ClanEntity;
import com.sea.db.entity.Clan_memberEntity;
import com.sea.db.entity.Clan_requestEntity;
import com.sea.db.entity.PlayerEntity;
import com.sea.db.entity.Rank_clanEntity;
import com.sea.handler.game.tcpGame.GameTcpHandle;
import com.sea.handler.game.tcpGame.GameTcpRepsonse;
import com.sea.logic.BackToClientCofig;
import com.sea.logic.BackToClientLogicHandle;
import com.sea.tools.UtileTools;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class LogicClan {

	static Log log = LogFactory.getLog(LogicClan.class);

	public static int getWaitTime(Player p) {
		if (p == null)
			return BackToClientCofig.NONE_PLAYER;

		long now_time = System.currentTimeMillis();
		long wait_time = DateEx.TIME_DAY;
		long lastLeaveClan = p.getLastleaveclan() + wait_time;
		if (lastLeaveClan > now_time)
			return BackToClientCofig.CLAN_Leave_Time_Enough;
		return BackToClientCofig.SUCCESS;
	}

	public static String createClan(Player p, String cname, int icon,
			String desc) {
		int result = getWaitTime(p);
		if (result != BackToClientCofig.SUCCESS)
			return "";

		int renownAll = p.getRenown();
		int renownWeek = p.getWeekrenown();

		boolean isHasCName = ProClan.isHasClanName(cname);
		if (isHasCName)
			return "";

		Clan c = ClanEntity.createClanInsert(cname, icon, desc, renownAll,
				renownWeek);
		boolean isNull = c == null;
		if (isNull)
			return "";

		String ccid = c.getCcid();
		int post = ConstantType.Type_ClanMember_Admin;
		createClanMember(p, ccid, post);
		return ccid;
	}

	private static int changeClanNum(String ccid, int num) {
		Clan c = ClanEntity.getClan(ccid);
		if (c == null)
			return BackToClientCofig.CLAN_Disband;
		int maxNum = c.getMaxnum();
		int curNum = c.getCurnum();
		curNum += num;
		if (curNum > maxNum) {
			return BackToClientCofig.CLAN_Max_Mun;
		}
		c.setCurnum(curNum);
		ClanEntity.updateClan(c);
		return BackToClientCofig.SUCCESS;
	}

	public static int exitClan(Player p) {
		if (p == null)
			return BackToClientCofig.NONE_PLAYER;
		String ccid = p.getClancid();
		int post = p.getClanpost();
		ClanEntity.delByPlayer(p);
		if (post == ConstantType.Type_ClanMember_Admin) {
			return BackToClientCofig.CLAN_Disband;
		}
		return changeClanNum(ccid, -1);
	}

	public static int createClanMember(Player p, String ccid, int post) {
		if (p == null)
			return BackToClientCofig.NONE_PLAYER;

		String pccid = p.getClancid();
		if (pccid != null) {
			pccid = pccid.trim();
			if (!"0".equals(pccid) && !"".equals(pccid) && !pccid.equals(ccid))
				return BackToClientCofig.CLAN_Has;
		}

		Clan c = ClanEntity.getClan(ccid);
		if (c == null)
			return BackToClientCofig.CLAN_Disband;

		if (post != ConstantType.Type_ClanMember_Admin) {
			int result = changeClanNum(ccid, 1);
			if (result != BackToClientCofig.SUCCESS)
				return result;
		}

		int pcid = p.getPcid();
		int ucid = p.getUcid();
		String pname = p.getPname();
		String cname = c.getClan_name();
		int renownAll = p.getRenown();
		int renownWeek = p.getWeekrenown();
		Clan_memberEntity.createClanMemberInsert(ccid, cname, ucid, pcid,
				pname, post, renownAll, renownWeek);

		int cicon = c.getIcon();

		// 更新角色联盟信息
		LogicPlayer.upClanCid(p, ccid, post, cname, cicon);
		return BackToClientCofig.SUCCESS;
	}

	public static int joinClanReq(Player p, String ccid) {
		int result = getWaitTime(p);
		if (result != BackToClientCofig.SUCCESS)
			return result;

		boolean isHasClan = LogicBuild.isHasClanBuild(p);
		if (!isHasClan)
			return BackToClientCofig.NONE_Clan_Build;

		Clan c = ClanEntity.getClan(ccid);
		if (c == null)
			return BackToClientCofig.CLAN_Disband_Other;

		int pcid = p.getPcid();
		String pname = p.getPname();
		int renown = p.getRenown();
		Clan_request r = Clan_requestEntity.getClanReq(ccid, pcid);
		if (r != null)
			return BackToClientCofig.CLAN_Request_Sended;
		Clan_requestEntity.createClanReqInsert(ccid, pcid, pname, renown);
		return BackToClientCofig.SUCCESS;
	}

	public static int accepteJoin(Player p, String ccid, int pcid) {
		if (p == null)
			return BackToClientCofig.NONE_PLAYER;

		String pccid = p.getClancid();
		if (!pccid.equals(ccid))
			return BackToClientCofig.CLAN_Not_Same;
		int ppcid = p.getPcid();

		Clan_member m = Clan_memberEntity.getClanMember(pccid, ppcid);
		if (m == null)
			return BackToClientCofig.CLAN_None_Power_Handle;
		int post = m.getPost();
		int normal = ConstantType.Type_ClanMember_Normal;
		if (post == normal)
			return BackToClientCofig.CLAN_None_Power_Handle;

		Clan_request cr = Clan_requestEntity.getClanReq(ccid, pcid);
		if (cr == null)
			return BackToClientCofig.CLAN_Request_BeHandle;

		Clan_requestEntity.deleteClanReq(cr);
		Player badd = PlayerEntity.getPlayer(pcid);
		return createClanMember(badd, ccid, normal);
	}

	public static int refuseJoinIn(Player p, String ccid, int pcid) {
		if (p == null)
			return BackToClientCofig.NONE_PLAYER;

		String pccid = p.getClancid();
		if (!pccid.equals(ccid))
			return BackToClientCofig.CLAN_None_Power_Handle;
		int ppcid = p.getPcid();

		Clan_member m = Clan_memberEntity.getClanMember(pccid, ppcid);
		if (m == null)
			return BackToClientCofig.CLAN_None_Power_Handle;
		int post = m.getPost();
		int normal = ConstantType.Type_ClanMember_Normal;
		if (post == normal)
			return BackToClientCofig.CLAN_None_Power_Handle;
		Clan_request cr = Clan_requestEntity.getClanReq(ccid, pcid);
		if (cr == null)
			return BackToClientCofig.CLAN_Request_BeHandle;

		Clan_requestEntity.deleteClanReq(cr);

		return BackToClientCofig.SUCCESS;
	}

	public static void getNClanRequests(Player p, NClanRequests reqes) {
		Clan_requestEntity.getNClanRequests(p, reqes);
	}

	public static void getNClan(Player p, NClan nc) {
		ClanEntity.getNClan(p, nc);
	}

	public static void getNClanMembers(Player p, NClanMembers nm) {
		Clan_memberEntity.getNClanmembers(p, nm);
	}

	public static void searchClansByName(String cname, NClans ncs) {
		ClanEntity.getNClansByName(cname, ncs);
	}

	public static void seeClan(String ccid, NClan clan, NClanMembers members,
			ReturnStatus ret) {
		Clan c = ClanEntity.getClan(ccid);
		if (c == null) {
			ret.succ = BackToClientCofig.CLAN_Disband_Other;
			return;
		}
		ClanEntity.toNClan(c, clan);
		Clan_memberEntity.getNClanmembersByCCid(ccid, members);
	}

	private static boolean isDonate(long time) {
		Date date_cur = new Date(time);
		int y_cur = DateEx.year(date_cur);
		int m_cur = DateEx.month(date_cur);
		int d_cur = DateEx.day(date_cur);
		Date date_now = new Date();
		int y_now = DateEx.year(date_now);
		int m_now = DateEx.month(date_now);
		int d_now = DateEx.day(date_now);
		boolean r = y_cur == y_now && m_cur == m_now && d_cur == d_now;
		return r;
	}

	public static boolean donateClanGold(Player p, int val) {
		if (p == null || val == 0 || val > 500000)
			return false;
		String ccid = p.getClancid();
		if (ccid == null || "".equals(ccid.trim()))
			return false;

		String resType = ConstantType.Type_Res_Gold;
		val = Math.abs(val);

		int pcid = p.getPcid();
		Clan_member m = Clan_memberEntity.getClanMember(ccid, pcid);
		long preTime = m.getLastdgold();
		boolean isCan = isDonate(preTime);
		if (isCan)
			return false;

		int perHP = 0;
		int curHP = 0;
		Clan c = ClanEntity.getClan(ccid);
		if (c != null)
			perHP = c.getPointhp();

		boolean isOkClan = ClanEntity.changeClanDonateRes(c, resType, val);
		if (c != null)
			curHP = c.getPointhp();

		Clan_memberEntity.changeMemberDonateRes(m, resType, val);

		if (curHP != perHP) {
			Clan_memberEntity.clearAllCurDonateInClan(ccid, true);
		}
		return isOkClan;
	}

	public static boolean donateClanOil(Player p, int val) {
		if (p == null || val == 0 || val > 250000)
			return false;
		String ccid = p.getClancid();
		if (ccid == null || "".equals(ccid.trim()))
			return false;

		String resType = ConstantType.Type_Res_Oil;
		val = Math.abs(val);
		int pcid = p.getPcid();
		Clan_member m = Clan_memberEntity.getClanMember(ccid, pcid);
		long preTime = m.getLastdoil();
		boolean isCan = isDonate(preTime);
		if (isCan)
			return false;

		int perAtt = 0;
		int curAtt = 0;

		Clan c = ClanEntity.getClan(ccid);
		if (c != null)
			perAtt = c.getPointatt();

		boolean isOkClan = ClanEntity.changeClanDonateRes(c, resType, val);
		if (c != null)
			curAtt = c.getPointatt();

		Clan_memberEntity.changeMemberDonateRes(m, resType, val);

		if (perAtt != curAtt) {
			Clan_memberEntity.clearAllCurDonateInClan(ccid, true);
		}
		return isOkClan;
	}

	public static boolean changeClanDesc(Player p, String desc) {
		boolean r = false;
		if (p == null || desc == null || "".equals(desc.trim()))
			return r;
		String ccid = p.getClancid();
		if (ccid == null || "".equals(ccid.trim()))
			return r;
		int post = p.getClanpost();
		if (post != ConstantType.Type_ClanMember_Admin)
			return r;
		Clan c = ClanEntity.getClan(ccid);
		if (c == null)
			return r;
		c.setDesc(desc);
		ClanEntity.updateClan(c);
		r = true;
		return r;
	}

	public static boolean allotClanPost(Player p, int bePcid, int post) {
		boolean r = false;
		if (p == null)
			return r;
		String ccid = p.getClancid();
		if (ccid == null || "".equals(ccid.trim()))
			return r;
		int ppost = p.getClanpost();
		if (ppost != ConstantType.Type_ClanMember_Admin)
			return r;
		if (post == ConstantType.Type_ClanMember_Admin)
			return r;

		Clan_member m = Clan_memberEntity.getClanMember(ccid, bePcid);
		if (m == null)
			return r;
		m.setPost(post);
		Clan_memberEntity.updateClanMember(m);

		// 更新角色联盟信息
		Player beP = PlayerEntity.getPlayer(bePcid);
		String cname = beP.getCname();
		int cicon = beP.getCicon();
		LogicPlayer.upClanCid(beP, ccid, post, cname, cicon);
		r = true;
		return r;
	}

	public static boolean outClanMember(Player p, int bePcid) {
		boolean r = false;
		if (p == null)
			return r;
		String ccid = p.getClancid();
		if (ccid == null || "".equals(ccid.trim()))
			return r;
		int ppost = p.getClanpost();
		if (ppost == ConstantType.Type_ClanMember_Normal)
			return r;
		Player beP = PlayerEntity.getPlayer(bePcid);
		if (beP == null)
			return r;
		int bepost = beP.getClanpost();
		boolean isCanDel = false;
		isCanDel = ppost == ConstantType.Type_ClanMember_Admin
				|| bepost == ConstantType.Type_ClanMember_Normal;

		if (isCanDel) {
			ClanEntity.delByPlayer(beP);
			changeClanNum(ccid, -1);
			r = true;
		}
		return r;
	}

	/***
	 * 改变联盟捐献等级
	 * 
	 * @param ccid
	 */
	static public void clearClanDonate(String ccid, int att, int hp) {
		Clan clan = ClanEntity.getClan(ccid);
		if (clan == null)
			return;
		clan.setCurdonategold(0);
		clan.setCurdonateoil(0);
		long nextGold = ClanEntity.getNextResGold(hp);
		long nextOil = ClanEntity.getNextResOil(att);
		clan.setNextattoil(nextOil);
		clan.setNexthpgold(nextGold);
		clan.setPointatt(att);
		clan.setPointhp(hp);
		ClanEntity.updateClan(clan);

		List<Clan_member> origin = Clan_memberEntity.getListInClan(ccid);
		if (origin == null || origin.isEmpty())
			return;
		for (Clan_member clan_member : origin) {
			clan_member.setDonategold(0);
			clan_member.setDonateoil(0);
		}
		Clan_memberEntity.updateClanMember(origin);
	}

	public static void clearClanRenownWeek() {
		List<Clan> origin = ClanEntity.getListAll();
		if (Svc.isEmpty(origin))
			return;

		int len = origin.size();
		for (int i = 0; i < len; i++) {
			Clan clan = origin.get(i);
			if (clan == null)
				continue;
			String ccid = clan.getCcid();
			clan.setRenownweek(0);
			clearMemberRenownWeek(ccid);
		}

		ClanEntity.updateClan(origin);
	}

	private static void clearMemberRenownWeek(String ccid) {
		List<Clan_member> origin = Clan_memberEntity.getListInClan(ccid);
		if (origin == null || origin.isEmpty())
			return;
		for (Clan_member clan_member : origin) {
			clan_member.setRenownweek(0);
		}
		Clan_memberEntity.updateClanMember(origin);
	}

	public static void getNRankClanWeek(int page, NRankClans rankClans) {
		Rank_clanEntity.getNRankClanByWeek(page, rankClans);
	}

	public static void getNRankClanAll(int page, NRankClans rankClans) {
		Rank_clanEntity.getNRankClanByAll(page, rankClans);
	}

	// =========== 推送消息
	private static void issuedClan(GameTcpRepsonse ctx, final String methodName) {

		boolean isIssued = BackToClientLogicHandle.isCanIssued(methodName);
		if (!isIssued)
			return;

		if (ctx == null)
			return;
		try {

			int pcid = ctx.getPcid();
			if (pcid == 0)
				return;
			boolean isCan = ctx.session.getLeClan().getIsIssuedClan();
			if (!isCan)
				return;

			ctx.session.getLeClan().setIsIssuedClan(false);

			NClan clan = new NClan();
			ReturnStatus rst = new ReturnStatus();
			GameTcpHandle.service.onGetOwnClan(ctx, clan, rst);
			Map result = new NewMap();
			result.put(0, -18886970);
			result.put(1, rst.toMap());
			result.put(3056214, clan.toMap());
			ctx.send(result);
		} catch (Exception ex) {
			log.info(UtileTools.ex2s(ex));
		}
	}

	private static void issuedClanMember(GameTcpRepsonse ctx,
			final String methodName) {

		boolean isIssued = BackToClientLogicHandle.isCanIssued(methodName);
		if (!isIssued)
			return;

		if (ctx == null)
			return;
		try {
			int pcid = ctx.getPcid();
			if (pcid == 0)
				return;
			boolean isCan = ctx.session.getLeClan().getIsIssuedClanMember();
			if (!isCan)
				return;

			ctx.session.getLeClan().setIsIssuedClanMember(false);

			NClanMembers members = new NClanMembers();

			ReturnStatus rst = new ReturnStatus();
			GameTcpHandle.service.onGetOwnClanMember(ctx, members, rst);
			Map result = new NewMap();
			result.put(0, -762930944);
			result.put(1, rst.toMap());
			result.put(948881689, members.toMap());
			ctx.send(result);
		} catch (Exception ex) {
			log.info(UtileTools.ex2s(ex));
		}
	}

	private static void issuedClanRequest(GameTcpRepsonse ctx,
			final String methodName) {

		boolean isIssued = BackToClientLogicHandle.isCanIssued(methodName);
		if (!isIssued)
			return;

		if (ctx == null)
			return;
		try {

			int pcid = ctx.getPcid();
			if (pcid == 0)
				return;
			boolean isCan = ctx.session.getLeClan().getIsIssuedClanRequest();
			if (!isCan)
				return;

			ctx.session.getLeClan().setIsIssuedClanRequest(false);

			Player p = ctx.getPlayer();
			String ccid = p.getClancid();

			Clan_member member = Clan_memberEntity.getClanMember(ccid, pcid);
			if (member == null)
				return;

			int post = member.getPost();
			if (post == ConstantType.Type_ClanMember_Normal)
				return;

			NClanRequests clanreqes = new NClanRequests();

			ReturnStatus rst = new ReturnStatus();
			GameTcpHandle.service.onGetOwnClanRequest(ctx, clanreqes, rst);
			Map result = new NewMap();
			result.put(0, -2029211415);
			result.put(1, rst.toMap());
			result.put(-153257674, clanreqes.toMap());
			ctx.send(result);
		} catch (Exception ex) {
			log.info(UtileTools.ex2s(ex));
		}
	}

	public static void issuedClanAll(GameTcpRepsonse ctx,
			final String methodName) {
		issuedClan(ctx, methodName);
		issuedClanMember(ctx, methodName);
		issuedClanRequest(ctx, methodName);
	}
}
