package com.sea.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import gen_b2g.serv.bean.NRankClan;
import gen_b2g.serv.bean.NRankClans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sea.cache.jedis.game.RankClanJedis;
import com.sea.db.bean.Clan;
import com.sea.db.bean.Rank_clan;
import com.sea.db.dao.Rank_clanDAO;
import com.sea.db.internal.Rank_clanInternal;
//import com.sea.content.AppContext;
import com.sea.tools.UtileTools;

//seawar2_design - rank_clan
@SuppressWarnings({ "static-access", "unchecked" })
public class Rank_clanEntity extends Rank_clanInternal {
	static Log log = LogFactory.getLog(Rank_clanEntity.class);

	public static final Rank_clanEntity my = new Rank_clanEntity();

	static Rank_clanDAO Rank_clanDAO = null;

	public static Rank_clanDAO Rank_clanDAO() {
		if (Rank_clanDAO == null)
			Rank_clanDAO = new Rank_clanDAO(com.sea.content.AppContext.ds());
		return Rank_clanDAO;
	}

	// static Rank_clanDAO Rank_clanDAO99 = null;
	// public static Rank_clanDAO Rank_clanDAO99() {
	// if( Rank_clanDAO99 == null)
	// Rank_clanDAO99 = new Rank_clanDAO(com.sea.content.AppContext.ds99());
	// return Rank_clanDAO99;
	// }

	public static void insertMmTry(final Rank_clan rank_clan) {
		Rank_clanDAO DAO = Rank_clanDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asynchronousInsert(rank_clan, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin
	// =================
	public static void insertRank(Rank_clan o) {
		if (o != null) {
			RankClanJedis.setDataReward(o);
		}
	}

	static void deleteCurRankInAll() {
		RankClanJedis.deleteRewardAll();
	}

	static void deleteCurRankInWeek() {
		RankClanJedis.deleteRewardWeek();
	}

	public static final String Score_All = "score_all";
	public static final String Score_Week = "score_week";

	public static void recordRank(int max, boolean isReset) {
		Map<String, List<Clan>> map = ClanEntity.getMapRank(isReset);
		List<Clan> all = map.get(Score_All);
		List<Clan> week = map.get(Score_Week);
		recordRankByList(all, true);
		recordRankByList(week, false);
	}

	private static void recordRankByList(List<Clan> list, boolean isAll) {
		if (list == null || list.isEmpty())
			return;
		if (isAll) {
			deleteCurRankInAll();
		} else {
			deleteCurRankInWeek();
		}
		int len = list.size();
		int type = isAll ? 1 : 0;
		for (int i = 0; i < len; i++) {
			Clan c = list.get(i);
			int id = 0;
			String ccid = c.getCcid();
			int icon = c.getIcon();
			String cname = c.getClan_name();
			int currank = i + 1;
			int renownAll = c.getRenownall();
			int renownWeek = c.getRenownweek();
			int curnum = c.getCurnum();
			int maxnum = c.getMaxnum();

			Rank_clan rc = Rank_clan.newRank_clan(id, ccid, icon, cname,
					currank, renownAll, renownWeek, type, curnum, maxnum);
			insertRank(rc);
		}
	}

	public static void deleteRCTable(List<String> timeList) {
		deleteTableByRedis(timeList);
		// deleteTableBySql(timeList);
	}

	public static void deleteTableByRedis(List<String> timeList) {
		RankClanJedis.deleteRankClanByDTime(timeList);
	}

	private static void sortRank(List<Rank_clan> list){
		if(list == null || list.isEmpty())
			return;
		
		Comparator<Rank_clan> c = new Comparator<Rank_clan>() {
			@Override
			public int compare(Rank_clan o1, Rank_clan o2) {
				int r1 = o1.getCurrank();
				int r2 = o2.getCurrank();
				if(r1 < r2)
					return -1;
				else if(r1 > r2)
					return 1;
				return 0;
			}
		};
		Collections.sort(list, c);
	}
	
	public static List<Rank_clan> getListRankByWeek(int page) {
		List<Rank_clan> r = null;
		List<Rank_clan> o = RankClanJedis.getListCurWeek();
		if (o == null || o.isEmpty())
			return r;
		sortRank(o);
		r = (List<Rank_clan>) UtileTools.getListPage(o, page);
		return r;
	}

	public static void getNRankClanByWeek(int page, NRankClans nrcs) {
		List<Rank_clan> list = getListRankByWeek(page);
		toNRanClans(list, nrcs);
	}

	public static List<Rank_clan> getListRankByAll(int page) {
		List<Rank_clan> r = null;
		List<Rank_clan> o = RankClanJedis.getListCurAll();
		if (o == null || o.isEmpty())
			return r;
		r = (List<Rank_clan>) UtileTools.getListPage(o, page);
		return r;
	}

	public static void getNRankClanByAll(int page, NRankClans nrcs) {
		List<Rank_clan> list = getListRankByAll(page);
		toNRanClans(list, nrcs);
	}

	// ==================客服端 服务器端
	public static NRankClan toNRankClan(Rank_clan o) {
		NRankClan r = null;
		if (o == null)
			return r;
		r = new NRankClan();
		r.ccid = o.getCcid();
		r.icon = o.getIcon();
		r.cname = o.getCname();
		r.currank = o.getCurrank();
		r.renownAll = o.getRenownall();
		r.renownWeek = o.getRenownweek();
		r.type = o.getType();
		r.curnum = o.getCurnum();
		r.maxnum = o.getMaxnum();
		return r;
	}

	public static List<NRankClan> toNRankClanList(List<Rank_clan> o) {
		List<NRankClan> r = null;
		if (o == null || o.isEmpty())
			return r;
		r = new ArrayList<NRankClan>();
		for (Rank_clan item : o) {
			NRankClan v = toNRankClan(item);
			if (v == null)
				continue;
			r.add(v);
		}
		return r;
	}

	public static NRankClans toNRanClans(List<Rank_clan> o, NRankClans nrc) {
		List<NRankClan> list = toNRankClanList(o);
		if (list == null || list.isEmpty() || nrc == null)
			return nrc;
		nrc.list = list;
		return nrc;
	}
	// types end

}
