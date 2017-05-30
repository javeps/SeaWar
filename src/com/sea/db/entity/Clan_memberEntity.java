package com.sea.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import gen_b2g.serv.bean.ConstantType;
import gen_b2g.serv.bean.NClanMember;
import gen_b2g.serv.bean.NClanMembers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sea.cache.process.ProClanMember;
import com.sea.db.bean.Clan_member;
import com.sea.db.bean.Player;
import com.sea.db.dao.Clan_memberDAO;
import com.sea.db.internal.Clan_memberInternal;
//import com.sea.content.AppContext;
import com.sea.tools.FormulaGame;

//seawar2_design - clan_member
@SuppressWarnings({ "static-access" })
public class Clan_memberEntity extends Clan_memberInternal {
	static Log log = LogFactory.getLog(Clan_memberEntity.class);

	public static final Clan_memberEntity my = new Clan_memberEntity();

	static Clan_memberDAO Clan_memberDAO = null;

	public static Clan_memberDAO Clan_memberDAO() {
		if (Clan_memberDAO == null)
			Clan_memberDAO = new Clan_memberDAO(com.sea.content.AppContext.ds());
		return Clan_memberDAO;
	}

	// static Clan_memberDAO Clan_memberDAO99 = null;
	// public static Clan_memberDAO Clan_memberDAO99() {
	// if( Clan_memberDAO99 == null)
	// Clan_memberDAO99 = new Clan_memberDAO(com.sea.content.AppContext.ds99());
	// return Clan_memberDAO99;
	// }

	public static void insertMmTry(final Clan_member clan_member) {
		Clan_memberDAO DAO = Clan_memberDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asynchronousInsert(clan_member, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin
	public static void insertClanMember(Clan_member o) {
		if (o != null) {
			ProClanMember.inCmem(o);
		}
	}

	public static void updateClanMember(Clan_member o) {
		if (o != null) {
			ProClanMember.upCMem(o);
		}
	}

	public static void updateClanMember(List<Clan_member> o) {
		if (o == null || o.isEmpty())
			return;
		ProClanMember.upCMem(o);
	}

	public static void deleteClanMember(Clan_member o) {
		if (o != null) {
			ProClanMember.delCMem(o);
		}
	}

	public static Clan_member getEnityByCcid(int pcid) {
		Clan_member o = null;
		o = getByPcid(pcid);
		return o;
	}

	// ======== method
	public static Clan_member createClanMember(String ccid, String cname,
			int ucid, int pcid, String pname, int post, int renownAll,
			int renownWeek) {
		int id = 0;
		int donateGold = 0;
		int donateOil = 0;
		long lastDGold = 0;
		long lastDOil = 0;
		int curDGold = 0;
		int curDOil = 0;
		int daynumgold = 0;
		int daynumoil = 0;
		int maxdaynum = 1;// ClanJson.getDonateMaxNum();
		Clan_member r = Clan_member.newClan_member(id, ccid, cname, ucid, pcid,
				pname, post, donateGold, donateOil, curDGold, curDOil,
				lastDGold, lastDOil, renownAll, renownWeek, daynumgold,
				daynumoil, maxdaynum);
		return r;
	}

	public static Clan_member createClanMemberInsert(String ccid, String cname,
			int ucid, int pcid, String pname, int post, int renownAll,
			int renownWeek) {
		Clan_member r = createClanMember(ccid, cname, ucid, pcid, pname, post,
				renownAll, renownWeek);
		insertClanMember(r);
		return r;
	}

	public static Clan_member getClanMember(String ccid, int pcid) {
		return ProClanMember.getClanMember(ccid, pcid);
	}

	private static void sortListInWeek(List<Clan_member> o) {
		if (o == null || o.isEmpty())
			return;
		Comparator<Clan_member> c = new Comparator<Clan_member>() {
			@Override
			public int compare(Clan_member o1, Clan_member o2) {
				int w_1 = o1.getRenownweek();
				int w_2 = o2.getRenownweek();
				if (w_1 > w_2)
					return -1;
				else if (w_1 < w_2)
					return 1;
				return 0;
			}
		};
		Collections.sort(o, c);
	}

	static void sortListInAll(List<Clan_member> o) {
		if (o == null || o.isEmpty())
			return;
		Comparator<Clan_member> c = new Comparator<Clan_member>() {
			@Override
			public int compare(Clan_member o1, Clan_member o2) {
				int w_1 = o1.getRenownall();
				int w_2 = o2.getRenownall();
				if (w_1 > w_2)
					return -1;
				else if (w_1 < w_2)
					return 1;
				return 0;
			}
		};
		Collections.sort(o, c);
	}

	public static List<Clan_member> getListInClan(String ccid) {
		return ProClanMember.getListInClan(ccid);
	}

	public static List<Clan_member> getListInClanByPlayer(Player p) {
		List<Clan_member> r = null;
		if (p == null)
			return r;
		String ccid = p.getClancid();
		if (ccid == null || "".equals(ccid.trim()))
			return r;
		r = getListInClan(ccid);
		return r;
	}

	public static List<Clan_member> getListInClanWeek(String ccid) {
		List<Clan_member> o = getListInClan(ccid);
		sortListInWeek(o);
		return o;
	}

	public static Map<String, Integer> getMapScores(String ccid) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put(Rank_clanEntity.Score_All, 0);
		map.put(Rank_clanEntity.Score_Week, 0);
		List<Clan_member> list = getListInClan(ccid);
		if (list == null || list.isEmpty())
			return map;
		List<Integer> oall = new ArrayList<Integer>();
		List<Integer> oweek = new ArrayList<Integer>();
		for (Clan_member item : list) {
			int renownAll = item.getRenownall();
			int renownWeek = item.getRenownweek();
			oall.add(renownAll);
			oweek.add(renownWeek);
		}

		int all = FormulaGame.getClanRenown(oall);
		int week = FormulaGame.getClanRenown(oweek);
		map.put(Rank_clanEntity.Score_All, all);
		map.put(Rank_clanEntity.Score_Week, week);
		return map;
	}

	public static boolean changeMemberDonateRes(Clan_member m, String resType,
			int val) {
		if (val == 0)
			return false;
		if (m == null)
			return false;
		val = Math.abs(val);
		long now_time = System.currentTimeMillis();
		int cur = 0;
		int all = 0;
		int sumCur = 0;
		int sumAll = 0;
		boolean isChange = true;
		switch (resType) {
		case ConstantType.Type_Res_Gold:
			cur = m.getCurdgold();
			all = m.getDonategold();
			sumCur = cur + val;
			sumAll = all + val;

			m.setCurdgold(sumCur);
			m.setDonategold(sumAll);
			m.setLastdgold(now_time);
			break;
		case ConstantType.Type_Res_Oil:
			cur = m.getCurdoil();
			all = m.getDonateoil();
			sumCur = cur + val;
			sumAll = all + val;

			m.setCurdoil(sumCur);
			m.setDonateoil(sumAll);
			m.setLastdoil(now_time);
			break;
		default:
			isChange = false;
			break;
		}
		if (isChange) {
			updateClanMember(m);
		}
		return isChange;
	}

	public static void clearAllCurDonateInClan(String ccid, boolean isGold) {
		List<Clan_member> list = getListInClan(ccid);
		if (list == null || list.isEmpty())
			return;
		for (Clan_member item : list) {
			if (isGold) {
				item.setCurdgold(0);
			} else {
				item.setCurdoil(0);
			}
			updateClanMember(item);
		}
	}

	public static void delAllInClan(String ccid) {
		ProClanMember.delAllMemberInClan(ccid);
	}

	public static void getNClanmembers(Player p, NClanMembers ncms) {
		List<Clan_member> list = getListInClanByPlayer(p);
		toNClanMembers(list, ncms);
	}

	public static void getNClanmembersByCCid(String ccid, NClanMembers ncms) {
		List<Clan_member> list = getListInClan(ccid);
		toNClanMembers(list, ncms);
	}

	// ==============服务器 客户端数据转换
	public static NClanMember toNClanMember(Clan_member o) {
		NClanMember r = null;
		if (o == null)
			return r;
		r = new NClanMember();
		r.ccid = o.getCcid();
		r.donateGold = o.getDonategold();
		r.donateOil = o.getDonateoil();
		r.curDGold = o.getCurdgold();
		r.curDOil = o.getCurdoil();
		r.lastDGold = o.getLastdgold();
		r.lastDOil = o.getLastdoil();
		r.pcid = o.getPcid();
		r.pname = o.getPname();
		r.post = o.getPost();
		r.renownAll = o.getRenownall();
		r.renownWeek = o.getRenownweek();
		return r;
	}

	public static List<NClanMember> toNClanMemberList(List<Clan_member> o) {
		List<NClanMember> r = null;
		if (o == null || o.isEmpty())
			return r;
		r = new ArrayList<NClanMember>();
		for (Clan_member item : o) {
			NClanMember v = toNClanMember(item);
			if (v == null)
				continue;
			r.add(v);
		}
		return r;
	}

	public static NClanMembers toNClanMembers(List<Clan_member> o,
			NClanMembers ncms) {
		List<NClanMember> list = toNClanMemberList(o);
		if (list == null || list.isEmpty() || ncms == null)
			return ncms;
		ncms.list = list;
		return ncms;
	}
	// types end

}
