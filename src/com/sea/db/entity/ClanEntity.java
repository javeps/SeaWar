package com.sea.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import gen_b2g.serv.bean.ConstantType;
import gen_b2g.serv.bean.NClan;
import gen_b2g.serv.bean.NClans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.util.MapEx;
import com.sea.cache.jedis.DCfgJedis;
import com.sea.cache.process.ProAssistRank;
import com.sea.cache.process.ProClan;
import com.sea.content.Svc;
import com.sea.db.bean.Clan;
import com.sea.db.bean.Player;
import com.sea.db.dao.ClanDAO;
import com.sea.db.internal.ClanInternal;
import com.sea.json.originData.ClanJson;
import com.sea.logic.logicOperate.LogicAtomicInteger;
//import com.sea.content.AppContext;
import com.sea.tools.FormulaTools;

//seawar_design - clan
@SuppressWarnings({ "static-access", "rawtypes" })
public class ClanEntity extends ClanInternal {
	static Log log = LogFactory.getLog(ClanEntity.class);

	public static final ClanEntity my = new ClanEntity();

	static ClanDAO ClanDAO = null;

	public static ClanDAO ClanDAO() {
		if (ClanDAO == null)
			ClanDAO = new ClanDAO(com.sea.content.AppContext.ds());
		return ClanDAO;
	}

	// static ClanDAO ClanDAO99 = null;
	// public static ClanDAO ClanDAO99() {
	// if( ClanDAO99 == null)
	// ClanDAO99 = new ClanDAO(com.sea.content.AppContext.ds99());
	// return ClanDAO99;
	// }

	public static void insertMmTry(final Clan clan) {
		ClanDAO DAO = ClanDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asynchronousInsert(clan, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin

	// =============== sql
	public static void insertClan(Clan o) {
		if (o != null) {
			ProClan.inClan(o);
		}
	}

	public static void updateClan(Clan o) {
		if (o != null) {
			ProClan.upClan(o);
		}
	}

	public static void updateClan(List<Clan> o) {
		if (o != null) {
			ProClan.upClan(o);
		}
	}

	public static void deleteClan(Clan o) {
		if (o != null) {
			ProClan.deleteClan(o);
		}
	}

	// ============ method
	public static long getNextResOil(int curN) {
		// 计算公式
		long r = 0;
		Map map = ClanJson.readMapData();
		int cell = ClanJson.getValBykey(map, ClanJson.Key_NextResCell_Oil);
		int dval = ClanJson.getValBykey(map, ClanJson.Key_NextResDiff_Oil);
		int aval = ClanJson.getValBykey(map, ClanJson.Key_NextResFirst_Oil);
		int bval = ClanJson.getValBykey(map, ClanJson.Key_NextResBase_Oil);
		double diff = dval * cell;
		int a = aval * cell;
		int b = bval * cell;
		int nextN = curN + 1;
		r = FormulaTools.getValByDValueSameDifferToLong(diff, a, b, nextN);
		return r;
	}

	public static long getNextResGold(int curN) {
		// 计算公式
		long r = 0;
		Map map = ClanJson.readMapData();
		int cell = ClanJson.getValBykey(map, ClanJson.Key_NextResCell_Gold);
		int dval = ClanJson.getValBykey(map, ClanJson.Key_NextResDiff_Gold);
		int aval = ClanJson.getValBykey(map, ClanJson.Key_NextResFirst_Gold);
		int bval = ClanJson.getValBykey(map, ClanJson.Key_NextResBase_Gold);
		double diff = dval * cell;
		int a = aval * cell;
		int b = bval * cell;
		int nextN = curN + 1;
		r = FormulaTools.getValByDValueSameDifferToLong(diff, a, b, nextN);
		return r;
	}

	public static Clan createClan(String cname, int icon, String desc,
			int renownAll, int renownWeek) {
		int id = 0;
		String ccidHead = DCfgJedis.getCcidHead();
		int vCode = LogicAtomicInteger
				.getValByType(LogicAtomicInteger.Type_Chancid);
		String ccid = ccidHead + vCode;
		int max_num = 20;
		long create_time = System.currentTimeMillis();
		int pointAtt = 0;
		int pointHP = 0;
		int lvl = 0;
		int cur_num = 1;
		long nextHPGold = getNextResGold(0);
		long nextAttOil = getNextResOil(0);
		long curDonateGold = 0;
		long curDonateOil = 0;
		Clan r = Clan.newClan(id, ccid, cname, icon, lvl, desc, max_num,
				cur_num, create_time, pointHP, pointAtt, renownAll, renownWeek,
				nextHPGold, nextAttOil, curDonateGold, curDonateOil);
		return r;
	}

	public static Clan createClanInsert(String cname, int icon, String desc,
			int renownAll, int renownWeek) {
		Clan r = createClan(cname, icon, desc, renownAll, renownWeek);
		insertClan(r);
		return r;
	}

	public static void delByPlayer(Player p) {
		ProClan.delClanByPlayer(p);
	}

	public static Clan getClan(String ccid) {
		return ProClan.getClanByCcid(ccid);
	}

	public static Clan getClanByPlayer(Player p) {
		Clan r = null;
		if (p == null)
			return r;
		String ccid = p.getClancid();
		r = getClan(ccid);
		return r;
	}

	private static void changeClanRenown(List<Clan> origin) {
		if (origin == null || origin.isEmpty())
			return;
		for (Clan c : origin) {
			changeClanRenown(c);
		}
	}

	private static void changeClanRenown(Clan c) {
		if (c == null)
			return;
		String ccid = c.getCcid();
		Map map = Clan_memberEntity.getMapScores(ccid);
		int renownAll = MapEx.getInt(map, Rank_clanEntity.Score_All);
		int renownWeek = MapEx.getInt(map, Rank_clanEntity.Score_Week);
		c.setRenownall(renownAll);
		c.setRenownweek(renownWeek);
		updateClan(c);
	}

	public static boolean changeClanDonateRes(Clan c, String resType, int val) {
		if (val == 0)
			return false;
		if (c == null)
			return false;
		val = Math.abs(val);
		long cur = 0;
		long all = 0;
		int curPoint = 0;
		long sumCur = 0;
		boolean isChange = true;

		switch (resType) {
		case ConstantType.Type_Res_Gold:
			cur = c.getCurdonategold();
			sumCur = cur + val;
			all = c.getNexthpgold();
			if (sumCur >= all) {
				sumCur -= all;
				curPoint = c.getPointhp();
				curPoint++;
				all = getNextResGold(curPoint);
				c.setNexthpgold(all);
				c.setPointhp(curPoint);
			}
			c.setCurdonategold(sumCur);
			break;
		case ConstantType.Type_Res_Oil:
			cur = c.getCurdonateoil();
			sumCur = cur + val;
			all = c.getNextattoil();
			if (sumCur >= all) {
				sumCur -= all;
				curPoint = c.getPointatt();
				curPoint++;
				all = getNextResOil(curPoint);
				c.setNextattoil(all);
				c.setPointatt(curPoint);
			}
			c.setCurdonateoil(sumCur);
			break;
		default:
			isChange = false;
			break;
		}
		if (isChange) {
			updateClan(c);
		}
		return isChange;
	}

	public static void resetClanDonate() {
		List<Clan> origin = getListAll();
		if (Svc.isEmpty(origin))
			return;
		int len = origin.size();
		for (int i = 0; i < len; i++) {
			Clan clan = origin.get(i);
			if (clan == null)
				continue;
			int lvlOil = clan.getPointatt();
			int lvlGold = clan.getPointhp();
			lvlGold++;
			lvlOil++;
			long nextGold = getNextResGold(lvlGold);
			long nextOil = getNextResOil(lvlOil);
			clan.setNextattoil(nextOil);
			clan.setNexthpgold(nextGold);
			updateClan(clan);
		}
	}

	public static List<Clan> getListAll() {
		List<Clan> o = null;
		o = ProClan.getListAll();
		if (o == null || o.isEmpty())
			return o;
		return o;
	}

	private static void sortClanInRWeek(List<Clan> o) {
		if (o == null || o.isEmpty())
			return;
		Comparator<Clan> c = new Comparator<Clan>() {
			@Override
			public int compare(Clan o1, Clan o2) {
				int all_o1 = o1.getRenownweek();
				int all_o2 = o2.getRenownweek();
				if (all_o1 > all_o2)
					return -1;
				else if (all_o1 < all_o2)
					return 1;
				return 0;
			}
		};
		Collections.sort(o, c);
	}

	private static void sortClanInRAll(List<Clan> o) {
		if (o == null || o.isEmpty())
			return;
		Comparator<Clan> c = new Comparator<Clan>() {
			@Override
			public int compare(Clan o1, Clan o2) {
				int all_o1 = o1.getRenownall();
				int all_o2 = o2.getRenownall();
				if (all_o1 > all_o2)
					return -1;
				else if (all_o1 < all_o2)
					return 1;
				return 0;
			}
		};
		Collections.sort(o, c);
	}

	public static Map<String, List<Clan>> getMapRank(boolean isReset) {
		Map<String, List<Clan>> map = new HashMap<String, List<Clan>>();
		List<Clan> all = null;
		List<Clan> week = null;
		if (isReset) {
			List<Clan> o = getListAll();
			week = getListRank(o, false);
			all = getListRank(o, true);
		} else {
			week = getListRankInWeekByAssist();
			all = getListRankInAllByAssist();
		}

		map.put(Rank_clanEntity.Score_All, all);
		map.put(Rank_clanEntity.Score_Week, week);
		return map;
	}

	private static List<Clan> getListRank(List<Clan> o, boolean isAll) {
		List<Clan> list = null;
		if (o == null || o.isEmpty())
			return list;
		list = new ArrayList<Clan>();
		list.addAll(o);

		changeClanRenown(list);

		if (isAll) {
			sortClanInRAll(list);
		} else {
			sortClanInRWeek(list);
		}
		int len = list.size();
		if (len > 300) {
			list = list.subList(0, 300);
		}
		if (isAll) {
			ProAssistRank.setRankClanAll(list, true);
		} else {
			ProAssistRank.setRankClanWeek(list, true);
		}
		return list;
	}

	private static List<Clan> getListRankInWeekByAssist() {
		List<Clan> o = ProAssistRank.getRankClanWeek();
		changeClanRenown(o);
		sortClanInRWeek(o);
		return o;
	}

	private static List<Clan> getListRankInAllByAssist() {
		List<Clan> o = ProAssistRank.getRankClanAll();
		changeClanRenown(o);
		sortClanInRAll(o);
		return o;
	}

	public static void getNClan(Player p, NClan n) {
		Clan c = getClanByPlayer(p);
		if (c == null || n == null)
			return;
		toNClan(c, n);
	}

	public static void getNClansByName(String cname, NClans ncs) {
		List<Clan> list = ProClan.getListClanByName(cname);
		toNClans(list, ncs);
	}

	// ======= 客户端 服务器端
	public static NClan toNClan(Clan o) {
		NClan r = null;
		r = toNClan(o, r);
		return r;
	}

	public static NClan toNClan(Clan o, NClan r) {
		if (o == null)
			return r;

		if (r == null)
			r = new NClan();

		r.ccid = o.getCcid();
		r.cname = o.getClan_name();
		r.createTime = o.getCreate_time();
		r.desc = o.getDesc();
		r.icon = o.getIcon();
		r.maxNum = o.getMaxnum();
		r.curNum = o.getCurnum();
		r.pointAtt = o.getPointatt();
		r.pointHP = o.getPointhp();

		r.eachAtt = ClanJson.getEachRateAtt();
		r.eachHP = ClanJson.getEachRateHP();

		r.curGold = o.getCurdonategold();
		r.curOil = o.getCurdonateoil();
		r.maxGold = o.getNexthpgold();
		r.maxOil = o.getNextattoil();
		return r;
	}

	public static List<NClan> toNClanList(List<Clan> o) {
		List<NClan> r = null;
		if (o == null || o.isEmpty())
			return r;
		r = new ArrayList<NClan>();
		for (Clan item : o) {
			NClan v = toNClan(item);
			if (v == null)
				continue;
			r.add(v);
		}
		return r;
	}

	public static NClans toNClans(List<Clan> o, NClans nc) {
		List<NClan> list = toNClanList(o);
		if (list == null || list.isEmpty() || nc == null)
			return nc;
		nc.list = list;
		return nc;
	}
	// types end

}
