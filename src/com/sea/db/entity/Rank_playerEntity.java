package com.sea.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import gen_b2g.serv.bean.NRankUser;
import gen_b2g.serv.bean.NRankUsers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sea.cache.jedis.game.RankPlayerJedis;
import com.sea.db.bean.Player;
import com.sea.db.bean.Rank_player;
import com.sea.db.dao.Rank_playerDAO;
import com.sea.db.internal.Rank_playerInternal;
import com.sea.tools.UtileTools;
//import com.sea.content.AppContext;

//seawar_design - rank_player
@SuppressWarnings({ "static-access", "unchecked" })
public class Rank_playerEntity extends Rank_playerInternal {
	static Log log = LogFactory.getLog(Rank_playerEntity.class);

	public static final Rank_playerEntity my = new Rank_playerEntity();

	static Rank_playerDAO Rank_playerDAO = null;

	public static Rank_playerDAO Rank_playerDAO() {
		if (Rank_playerDAO == null)
			Rank_playerDAO = new Rank_playerDAO(com.sea.content.AppContext.ds());
		return Rank_playerDAO;
	}

	// static Rank_playerDAO Rank_playerDAO99 = null;
	// public static Rank_playerDAO Rank_playerDAO99() {
	// if( Rank_playerDAO99 == null)
	// Rank_playerDAO99 = new Rank_playerDAO(com.sea.content.AppContext.ds99());
	// return Rank_playerDAO99;
	// }

	public static void insertMmTry(final Rank_player rank_player) {
		Rank_playerDAO DAO = Rank_playerDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asynchronousInsert(rank_player, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	static Rank_playerDAO logDaoRankPlayer = null;

	public static Rank_playerDAO logDaoRankPlayer() {
		if (logDaoRankPlayer == null)
			logDaoRankPlayer = new Rank_playerDAO(
					com.sea.content.AppContext.logDS());
		return logDaoRankPlayer;
	}

	public static void insertLog(final Rank_player entity) {
		String TABLENAME2 = Rank_playerDAO.TABLEDD();
		try {
			Rank_playerDAO DAO = logDaoRankPlayer();
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asynchronousInsert(entity, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	static void deleteTableBySql(List<String> timeList) {
		if (timeList != null && timeList.size() > 0) {
			try {
				Rank_playerDAO DAO = logDaoRankPlayer();
				StringBuffer sb = new StringBuffer();
				for (String t : timeList) {
					String table = Rank_playerDAO.TABLE + t;
					sb.append("DROP TABLE `").append(table).append("`;");
				}
				String sql = sb.toString();
				DAO.execute(sql);
			} catch (Exception e) {
				log.info(e2s(e));
			}
		}
	}

	static void clearDataBySql() {
		String TABLENAME2 = Rank_playerDAO.TABLEDD();
		Rank_playerDAO DAO = logDaoRankPlayer();
		String sql = "TRUNCATE TABLE `" + TABLENAME2 + "`;";
		try {
			DAO.execute(sql);
		} catch (SQLException e) {
			log.info(e2s(e));
		}
	}

	public static boolean isHasTableByWeek() {
		boolean r = true;
		try {
			r = RankPlayerJedis.isHasWeek();
			if (!r) {
				// Rank_playerDAO DAO = logDaoRankPlayer();
				// r = DAO.exist_r(TABLENAME2);
			}
		} catch (Exception e) {
			log.info(e2s(e));
		}
		return r;
	}

	public static boolean isHasTableByAll() {
		boolean r = true;
		try {
			r = RankPlayerJedis.isHasAll();
			if (!r) {
				// Rank_playerDAO DAO = logDaoRankPlayer();
				// r = DAO.exist_r(TABLENAME2);
			}
		} catch (Exception e) {
			log.info(e2s(e));
		}
		return r;
	}

	static List<Rank_player> getRankListBySql(int page) {
		List<Rank_player> r = null;
		Rank_playerDAO DAO = logDaoRankPlayer();
		String TABLENAME2 = DAO.TABLEDD();
		boolean isHas_ = DAO.exist_w(TABLENAME2);
		if (!isHas_) {
			log.info("== 排名日志表" + TABLENAME2 + "还没有！ ==");
			return r;
		}
		int size = 10;
		int begin = (page - 1) * size;
		begin = begin < 0 ? 0 : begin;
		String sql = "SELECT * FROM " + TABLENAME2 + " LIMIT " + begin + ","
				+ size;
		try {
			r = DAO.queryForList(sql, Rank_player.class);
		} catch (Exception e) {
			log.info(e2s(e));
		}
		return r;
	}

	public static void deleteRPTable(List<String> timeList) {
		deleteTableByRedis(timeList);
		// deleteTableBySql(timeList);
	}

	public static void deleteTableByRedis(List<String> timeList) {
		RankPlayerJedis.deleteRankPlayerByDTime(timeList);
	}

	// types begin
	public static void insertRankLog(Rank_player o) {
		if (o != null) {
			// insertLog(o);
			RankPlayerJedis.setDataReward(o);
		}
	}

	public static final String Score_All = "score_all";
	public static final String Score_Week = "score_week";

	private static void clearDataInWeek() {
		RankPlayerJedis.deleteRewardWeek();
		// clearDataBySql();
	}

	private static void clearDataInAll() {
		RankPlayerJedis.deleteRewardAll();
	}

	public static void recordRank(int maxNum, boolean isReset) {
		Map<String, List<Player>> map = PlayerEntity
				.getMapRank(maxNum, isReset);
		List<Player> all = map.get(Score_All);
		List<Player> week = map.get(Score_Week);
		recordRankByList(all, true);
		recordRankByList(week, false);
	}

	private static void recordRankByList(List<Player> list, boolean isAll) {
		if (list == null || list.isEmpty())
			return;

		if (isAll) {
			clearDataInAll();
		} else {
			clearDataInWeek();
		}
		int type = isAll ? 1 : 0;
		int backIndex = 0;

		int len = list.size();
		for (int i = 0; i < len; i++) {
			Player item = list.get(i);
			boolean isInGuid = item.getGuid_step() < 100;
			if (isInGuid) {
				backIndex++;
				continue;
			}
			int rankNum = i + 1 - backIndex;
			int pcid = item.getPcid();
			int ucid = item.getUcid();
			String pname = item.getPname();
			int exp = item.getExp();

			String clancid = item.getClancid();
			int cicon = 0;
			String cname = "";
			if (!"".equals(clancid.trim())) {
				// cicon = item.getClan_icon();
				// cname = item.getClan_name();
			}
			int renown = item.getRenown();
			int weekAdd = item.getWeekrenown();

			Rank_player o = Rank_player.newRank_player(0, ucid, pcid, pname,
					exp, clancid, cicon, cname, rankNum, renown, weekAdd, type);
			insertRankLog(o);
		}
	}

	private static void sortRank(List<Rank_player> list) {
		if (list == null || list.isEmpty())
			return;

		Comparator<Rank_player> c = new Comparator<Rank_player>() {
			@Override
			public int compare(Rank_player o1, Rank_player o2) {
				int r1 = o1.getCurrank();
				int r2 = o2.getCurrank();
				if (r1 < r2)
					return -1;
				else if (r1 > r2)
					return 1;
				return 0;
			}
		};
		Collections.sort(list, c);
	}

	public static List<Rank_player> getListRankByWeek(int page) {
		List<Rank_player> r = null;
		List<Rank_player> allList = RankPlayerJedis.getListCurWeek();
		if (allList == null || allList.isEmpty())
			return r;
		sortRank(allList);
		r = (List<Rank_player>) UtileTools.getListPage(allList, page);
		return r;
	}

	public static void getNRankUsersByWeek(int page, NRankUsers rankUses) {
		List<Rank_player> list_ = getListRankByWeek(page);
		toNRankUsers(list_, rankUses);
	}

	public static List<Rank_player> getListRankByAll(int page) {
		List<Rank_player> r = null;
		List<Rank_player> allList = RankPlayerJedis.getListCurAll();
		if (allList == null || allList.isEmpty())
			return r;
		sortRank(allList);
		r = (List<Rank_player>) UtileTools.getListPage(allList, page);
		return r;
	}

	public static void getNRankUsersByAll(int page, NRankUsers rankUses) {
		List<Rank_player> list_ = getListRankByAll(page);
		toNRankUsers(list_, rankUses);
	}

	// ================= 服务器数据数据 ==>> 客服端数据 ==================
	// 数据库对象，转换为客户端数据
	public static NRankUser toNRankUser(Rank_player rp) {
		if (rp == null)
			return null;
		NRankUser r2 = new NRankUser();
		r2.clanIcon = rp.getClanicon();
		r2.clancid = rp.getClancid();
		r2.clanName = rp.getClanname();
		r2.pexp = rp.getPexp();
		r2.pid = rp.getPcid();
		r2.pname = rp.getPname();
		r2.rankIndex = rp.getCurrank();
		r2.renown = rp.getRenown();
		r2.weekRenown = rp.getWeekaddrenown();
		r2.type = rp.getType();
		return r2;
	}

	// 把数据对象集合List转换成客户端对象集合List
	public static List<NRankUser> toNRankUserList(List<Rank_player> list_) {
		List<NRankUser> r2 = new ArrayList<NRankUser>();
		if (list_ != null) {
			for (Rank_player item : list_) {
				NRankUser toVal = toNRankUser(item);
				if (toVal == null)
					continue;
				r2.add(toVal);
			}
		}
		return r2;
	}

	// 取得角色的所有序列并转换成客户端需求的List集合对象
	public static NRankUsers toNRankUsers(List<Rank_player> paList,
			NRankUsers supas) {
		if (paList == null)
			return supas;
		if (supas == null)
			supas = new NRankUsers();
		supas.lists = toNRankUserList(paList);
		return supas;
	}
	// types end

}
