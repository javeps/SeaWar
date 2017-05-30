package com.sea.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import gen_b2g.serv.bean.ConstantType;
import gen_b2g.serv.bean.NPlayer;
import gen_b2g.serv.bean.NPlayers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.lang.RndEx;
import com.bowlong.util.DateEx;
import com.sea.cache.jedis.DCfgJedis;
import com.sea.cache.jedis.GameDelJedis;
import com.sea.cache.jedis.game.AssistUserPlayerJedis;
import com.sea.cache.process.ProAssistRank;
import com.sea.cache.process.ProPlayer;
import com.sea.content.Svc;
import com.sea.db.bean.Player;
import com.sea.db.dao.PlayerDAO;
import com.sea.db.internal.PlayerInternal;
import com.sea.logic.logicOperate.LogicAtomicInteger;
import com.sea.logic.logicOperate.LogicPlayer;
//import com.sea.content.AppContext;
import com.sea.tools.UtileTools;

//seawar_design - player
@SuppressWarnings({ "static-access" })
public class PlayerEntity extends PlayerInternal {
	static Log log = LogFactory.getLog(PlayerEntity.class);

	public static final PlayerEntity my = new PlayerEntity();

	static PlayerDAO PlayerDAO = null;

	public static PlayerDAO PlayerDAO() {
		if (PlayerDAO == null)
			PlayerDAO = new PlayerDAO(com.sea.content.AppContext.ds());
		return PlayerDAO;
	}

	// static PlayerDAO PlayerDAO99 = null;
	// public static PlayerDAO PlayerDAO99() {
	// if( PlayerDAO99 == null)
	// PlayerDAO99 = new PlayerDAO(com.sea.content.AppContext.ds99());
	// return PlayerDAO99;
	// }

	public static void insertMmTry(final Player player) {
		PlayerDAO DAO = PlayerDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asynchronousInsert(player, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin
	// ================== 数据库操作 begin ==================
	// 插入数据
	public static void insertPlayer(Player r2) {
		if (r2 != null) {
			// r2 = r2.insert();
			ProPlayer.setPlayer(r2);
		}
	}

	// 更新数据
	public static void updatePlayer(Player r2) {
		if (r2 != null) {
			// r2 = r2.update();
			String ccid = r2.getClancid();
			if (ccid == null || "0".equals(ccid.trim())) {
				ccid = "";
				r2.setClancid(ccid);
			}
			ProPlayer.upPlayer(r2);
		}
	}

	// 删除数据
	public static void deletePlayer(Player origin, boolean isDelUser) {
		if (origin != null) {
			GameDelJedis.deletePlayer(origin, isDelUser);
		}
	}

	private static String getWeekRankSql(int maxNum) {
		maxNum = maxNum < 1 ? 1 : maxNum;
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM `");
		sb.append(Player.TABLENAME);
		sb.append("`  WHERE `type` != ");
		sb.append(ConstantType.Type_User_NPC);
		sb.append(" ORDER BY `weekRenown` DESC,`renown` DESC,`exp` DESC,`id` ASC LIMIT 0,");
		sb.append(maxNum);
		String r = sb.toString();
		return r;
	}

	static List<Player> getWeekRankListBySql(int maxNum) {
		maxNum = maxNum < 1 ? 1 : maxNum;
		PlayerDAO pDao = PlayerDAO();
		String sql = getWeekRankSql(maxNum);
		List<Player> list_ = null;
		try {
			list_ = pDao.queryForList(sql, Player.class);
		} catch (Exception e) {
			log.info(e2s(e));
		}
		return list_;
	}

	private static String getRenownRankSql(int maxNum) {
		maxNum = maxNum < 1 ? 1 : maxNum;
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM `");
		sb.append(Player.TABLENAME);
		sb.append("`  WHERE `type` != ");
		sb.append(ConstantType.Type_User_NPC);
		sb.append(" ORDER BY `renown` DESC,`exp` DESC,`id` ASC LIMIT 0,");
		sb.append(maxNum);
		String r = sb.toString();
		return r;
	}

	static List<Player> getRenownRankListBySql(int maxNum) {
		maxNum = maxNum < 1 ? 1 : maxNum;
		PlayerDAO pDao = PlayerDAO();
		String sql = getRenownRankSql(maxNum);
		List<Player> list_ = null;
		try {
			list_ = pDao.queryForList(sql, Player.class);
		} catch (Exception e) {
			log.info(e2s(e));
		}
		return list_;
	}

	// 取得玩家的角色列表
	public static List<Player> getListByUcid(String pcids, int ucid) {
		if (ucid == 0)
			return null;
		List<Player> r2 = ProPlayer.getListByUcid(ucid, pcids);
		return r2;
	}

	// 取得玩家的角色列表
	public static List<Player> getListByUcidSvid(String pcids, int ucid,
			int svid) {
		List<Player> r2 = null;
		if (ucid == 0)
			return r2;
		r2 = getListByUcid(pcids, ucid);
		return r2;
	}

	public static Player getPlayer(int pcid) {
		return ProPlayer.getPlayerByPcid(pcid);
	}

	// 取得用户的角色对象
	public static Player getPlayer(String pname) {
		return ProPlayer.getPlayerByPname(pname);
	}

	public static void deleteListByUcid(String pcids, int ucid) {
		List<Player> list = getListByUcid(pcids, ucid);
		if (list == null || list.isEmpty())
			return;

		for (Player player : list) {
			GameDelJedis.deletePlayer(player, true);
		}
	}

	// ================== 数据库操作 end ==================

	public static int getNewPcid() {
		return LogicAtomicInteger.getValByType(LogicAtomicInteger.Type_Pcid);
	}

	// 取得用户的角色对象
	public static Player getPlayer(int ucid, int pcid) {
		if (pcid == 0)
			return null;
		Player r2 = ProPlayer.getPlayerByPcidUcid(pcid, ucid);
		if (r2 == null) {
			r2 = getPlayer(pcid);
		}
		return r2;
	}

	// 取得客服端所需对象
	public static void getNPlayerByFight(Player p, NPlayer sup) {
		toSUPlayer(p, sup);
		if (sup != null && p != null) {
			int htlvl = p.getCurtownlvl();
			int defval = 1000 * htlvl;
			if (sup.stored_gold <= 1000) {
				sup.stored_gold = defval;
			}
			if (sup.stored_oil <= 1000) {
				sup.stored_oil = defval;
			}
		}
	}

	public static void getNPlayer(Player p, NPlayer sup) {
		toSUPlayer(p, sup);
	}

	public static Map<String, List<Player>> getMapRank(int maxNum,
			boolean isReset) {
		Map<String, List<Player>> map = new HashMap<String, List<Player>>();
		Map<String, List<Player>> map_o = PlayerEntity.getListRank(isReset);
		List<Player> origin_all = map_o.get(Rank_playerEntity.Score_All);
		List<Player> origin_week = map_o.get(Rank_playerEntity.Score_Week);
		List<Player> all = getListRankByList(origin_all, maxNum);
		List<Player> week = getListRankByList(origin_week, maxNum);

		map.put(Rank_playerEntity.Score_All, all);
		map.put(Rank_playerEntity.Score_Week, week);
		return map;
	}

	private static List<Player> getListRankByList(List<Player> origin,
			int maxNum) {
		List<Player> r = new ArrayList<Player>();
		if (origin == null || origin.isEmpty())
			return r;
		maxNum = maxNum < 1 ? 1 : maxNum;
		int len = origin.size();
		if (len > maxNum) {
			origin = origin.subList(0, maxNum);
		}

		for (Player item : origin) {
			int ucid = item.getUcid();
			int pcid = item.getPcid();
			Player pitem = getPlayer(ucid, pcid);
			if (pitem == null)
				continue;
			int ptype = pitem.getType();
			if (ptype == ConstantType.Type_User_GM)
				continue;
			if (pitem.getState() == 11)
				continue;
			r.add(pitem);
		}
		return r;
	}

	public static List<Player> getListRankByNum(int num) {
		Map<String, List<Player>> map = getMapRank(num, true);
		return map.get(Rank_playerEntity.Score_Week);
	}

	// ================= 服务器数据数据 ==>> 客服端数据 ==================
	// 数据库对象，转换为客户端数据
	public static NPlayer toSUPlayer(Player p, NPlayer r2) {
		if (p == null)
			return r2;
		if (r2 == null)
			r2 = new NPlayer();
		r2.all_pay_momey = p.getAll_money();
		r2.crystal = p.crystal;
		r2.cur_npc_local_id = p.cur_npc;
		r2.guid_step = p.guid_step;
		r2.icon = p.icon;
		r2.last_pay_money = p.last_money;
		r2.last_pay_time = p.getLast_pay_time();
		r2.lvl = p.exp;

		r2.clancid = p.getClancid();
		r2.cicon = p.getCicon();
		r2.cname = p.getCname();

		r2.pid = p.getPcid();
		r2.pname = p.pname;
		r2.type = p.getType();
		r2.renown = p.renown;
		r2.serverId = p.getSvcid();
		r2.uid = p.getUcid();
		r2.maxBuidId = p.maxBuidId;
		r2.stored_gold = p.getStored_gold();
		r2.stored_oil = p.getStored_oil();
		r2.maxAttMailId = p.getMaxattmcid();
		r2.protectTime = p.getProtecttime();
		r2.maxOrderNum = p.getMaxbonum();
		r2.lastAddObst = p.getLastaddobst();
		r2.maxObstId = p.getMaxobstid();
		r2.isReward = p.getIsrewardday();
		r2.dayLogin = p.getLoginday();

		r2.firstPayStatus = p.getFirstpaystatus();

		r2.sys_time = System.currentTimeMillis();

		r2.rankRewardTime = DCfgJedis.getRankReward();

		r2.pieceAttNum = p.getPiecedamnum();
		r2.pieceHPNum = p.getPiecehpnum();
		r2.pieceSpeedNum = p.getPieceattspeed();
		r2.dayTasks = p.getDaytasks();
		r2.lastLeaveClan = p.getLastleaveclan();
		r2.monCode = p.getMonthcode();

		return r2;
	}

	// 数据库对象，转换为客户端数据
	public static NPlayer toSUPlayer(Player p) {
		if (p == null)
			return null;
		NPlayer r2 = new NPlayer();
		r2 = toSUPlayer(p, r2);
		return r2;
	}

	// 把数据库角色列表List转换为客户端列表List
	public static List<NPlayer> toSUPlayerList(List<Player> plist) {
		List<NPlayer> r2 = new ArrayList<NPlayer>();
		if (plist == null)
			return r2;
		for (Player player : plist) {
			NPlayer s = toSUPlayer(player);
			if (s == null)
				continue;
			r2.add(s);
		}
		return r2;
	}

	// 取得角色的所有序列并转换成客户端需求的List集合对象
	public static NPlayers toSUPlayers(List<Player> plist, NPlayers sups) {
		if (plist == null || sups == null)
			return sups;
		sups.uplayers = toSUPlayerList(plist);
		return sups;
	}

	// ================================
	// 判断是否拥有名字
	public static boolean isHasName(String uname) {
		return AssistUserPlayerJedis.isHasName(uname);
	}

	// types end

	/*** 不需要辅助类Player ****/

	private static List<Player> getListRanRecord(List<Player> origin,
			boolean isWeek) {
		if (Svc.isEmpty(origin))
			return origin;
		List<Player> result = null;
		result = new ArrayList<Player>();
		result.addAll(origin);
		if (isWeek) {
			sortRenownInWeek(result);
		} else {
			sortRenownInAll(result);
		}

		int len = result.size();
		if (len > 300) {
			result = result.subList(0, 300);
		}
		if (isWeek) {
			ProAssistRank.setRankPlayerWeek(result, true);
		} else {
			ProAssistRank.setRankPlayerAll(result, true);
		}
		return result;
	}

	public static Map<String, List<Player>> getListRank(boolean isReset) {
		Map<String, List<Player>> map = new HashMap<String, List<Player>>();
		List<Player> all = null;
		List<Player> week = null;
		if (isReset) {
			List<Player> orign = ProPlayer.getListAll();
			all = getListRanRecord(orign, false);
			week = getListRanRecord(orign, true);
		} else {
			all = getListRankInAllAssist();
			week = getListRanInWeekAssist();
		}
		map.put(Rank_playerEntity.Score_All, all);
		map.put(Rank_playerEntity.Score_Week, week);
		return map;
	}

	private static List<Player> getListRanInWeekAssist() {
		List<Player> r2 = ProAssistRank.getRankPlayerWeek();
		sortRenownInWeek(r2);
		return r2;
	}

	private static List<Player> getListRankInAllAssist() {
		List<Player> r2 = ProAssistRank.getRankPlayerAll();
		sortRenownInAll(r2);
		return r2;
	}

	/******** 玩家排序规则 -1是靠前，1是靠后 *********/
	static int comarePlayer(Player p1, Player p2, boolean isAll) {
		if (p1 == null || p2 == null)
			return 0;
		int guid_o1 = p1.getGuid_step();
		int guid_o2 = p2.getGuid_step();
		if (guid_o1 < 100 && guid_o2 < 100)
			return 0;
		if (guid_o1 > 100 && guid_o2 > 100) {
			int renown_o1 = 0;
			int renown_o2 = 0;
			if (isAll) {
				renown_o1 = p1.getRenown();
				renown_o2 = p2.getRenown();
			} else {
				renown_o1 = p1.getWeekrenown();
				renown_o2 = p2.getWeekrenown();
			}
			if (renown_o1 > renown_o2) {
				return -1;
			} else if (renown_o1 < renown_o2) {
				return 1;
			}
			int townLvl1 = p1.getCurtownlvl();
			int townLvl2 = p2.getCurtownlvl();
			if (townLvl1 > townLvl2) {
				return -1;
			} else if (townLvl1 < townLvl2) {
				return 1;
			}
		} else {
			if (guid_o1 > guid_o2)
				return -1;
			if (guid_o1 < guid_o2)
				return 1;
		}
		return 0;
	}

	private static void sortRenownInWeek(List<Player> origin) {
		if (origin == null || origin.size() <= 0)
			return;
		Comparator<Player> comparator = new Comparator<Player>() {
			@Override
			public int compare(Player o1, Player o2) {
				return comarePlayer(o1, o2, false);
			}
		};
		Collections.sort(origin, comparator);
	}

	private static void sortRenownInAll(List<Player> origin) {
		if (origin == null || origin.size() <= 0)
			return;
		Comparator<Player> comparator = new Comparator<Player>() {

			@Override
			public int compare(Player o1, Player o2) {
				return comarePlayer(o1, o2, true);
			}
		};
		Collections.sort(origin, comparator);
	}

	// 战斗查询到的玩家
	static String Fight_List_DiffMin = "Fight_List_DiffMin";
	static String Fight_List_DiffMid = "Fight_List_DiffMid";
	static String Fight_List_DiffMax = "Fight_List_DiffMax";

	// 取得过滤掉新手，在线，或者保护时间的玩家列表
	static List<Player> getListFilter() {
		List<Player> origin = ProPlayer.getListAll();
		if (Svc.isEmpty(origin))
			return null;
		List<Player> r2 = new ArrayList<Player>();
		long now_time = System.currentTimeMillis();
		int len = origin.size();
		for (int i = 0; i < len; i++) {
			Player item = origin.get(i);
			if (item == null)
				continue;

			int guid = item.getGuid_step();
			if (guid < 100)
				continue;

			boolean isOnline = item.getIsonline();
			if (isOnline)
				continue;
			long ptime_ = item.getProtecttime();
			if (ptime_ > now_time)
				continue;

			r2.add(item);
		}
		return r2;
	}

	/*** 0 相近的5天，1超出5天,-1直接返回null **/
	static int randNeed() {
		int k = UtileTools.randIntK();
		int result = 0;
		if (k <= 650) {
			result = 0;
		} else if (k <= 950) {
			result = 1;
		} else {
			result = -1;
		}
		return result;
	}

	public static Player getBeFightPlayer(int pcid, int townLvl,
			int renown_week, int renown_all) {
		Player r = null;

		int term = randNeed();
		if (term == -1)
			return r;

		Map<String, List<Player>> map = getMapBetweenVal(pcid, townLvl,
				renown_week, renown_all);

		List<Player> origin = map.get(Fight_List_DiffMid);
		if (origin != null && origin.isEmpty()) {
			boolean k = RndEx.nextBoolean();
			if (k) {
				origin = map.get(Fight_List_DiffMin);
			} else {
				origin = map.get(Fight_List_DiffMax);
			}
		}

		r = randRenown(origin, term);

		return r;
	}

	static Map<String, List<Player>> getMapBetweenVal(int pcid, int townLvl,
			int renown_week, int renown_all) {
		boolean isRenownAll = true;
		int renown_origin = 0;

		if (renown_all < 200) {
			renown_origin = renown_all;
		} else {
			double d = (double) renown_week / renown_all;
			if (d > 0.65) {
				if (renown_week < 150) {
					renown_origin = renown_all;
				} else {
					renown_origin = renown_week;
					isRenownAll = false;
				}
			} else {
				renown_origin = renown_all;
			}
		}

		int minRenown = renown_origin - 1000;
		int maxRenown = renown_origin + 1000;

		if (isRenownAll) {
			minRenown = minRenown <= 0 ? 0 : minRenown;
		}

		int minLvl = townLvl - 1;
		int maxLvl = townLvl + 2;

		minLvl = minLvl <= 2 ? 2 : minLvl;
		maxLvl = maxLvl >= 10 ? 10 : maxLvl;

		Map<String, List<Player>> map = new HashMap<String, List<Player>>();

		List<Player> min = new ArrayList<Player>();
		List<Player> mid = new ArrayList<Player>();
		List<Player> max = new ArrayList<Player>();

		List<Player> plist = getListFilter();

		if (!Svc.isEmpty(plist)) {
			int len = plist.size();
			for (int i = 0; i < len; i++) {
				Player item = plist.get(i);
				if (item == null)
					continue;
				int ipcid = item.getPcid();
				if (ipcid == pcid)
					continue;

				int curLvl = item.getCurtownlvl();
				if (curLvl < minLvl || curLvl > maxLvl)
					continue;

				int renown = 0;
				if (isRenownAll) {
					renown = item.getRenown();
				} else {
					renown = item.getWeekrenown();
				}

				if (renown < minRenown || renown > maxRenown)
					continue;

				int floor_min = renown_origin - 600;
				int floor_mid = renown_origin + 600;

				if (renown < floor_min)
					min.add(item);
				else if (renown < floor_mid)
					mid.add(item);
				else
					max.add(item);
			}
		}

		map.put(Fight_List_DiffMin, min);
		map.put(Fight_List_DiffMid, mid);
		map.put(Fight_List_DiffMax, max);
		return map;
	}

	static Player randRenown(List<Player> origin, int term) {
		Player r = null;

		if (origin == null || origin.isEmpty())
			return r;

		List<Player> result = new ArrayList<Player>();

		long now_time = System.currentTimeMillis();
		long day_time = DateEx.TIME_DAY;

		for (Player item : origin) {
			long lastLogin = item.getLastlogintime();
			long difftime = now_time - lastLogin;
			long v = difftime / day_time;

			if (term == 1) {
				if (v > 5)
					result.add(item);
			} else {
				if (v <= 5)
					result.add(item);
			}
		}

		if (result.isEmpty())
			return r;

		r = (Player) Svc.rand(result);

		return r;
	}

	// 判断是否可以攻击
	public static boolean isCanBeFight(Player p) {
		boolean r2 = false;
		if (p == null)
			return r2;
		boolean isNpc = LogicPlayer.isNpc(p);
		if (isNpc)
			return true;
		boolean isOnline = p.getIsonline();
		if (isOnline)
			return false;
		long ptime = p.getProtecttime();
		long now_time = System.currentTimeMillis();
		if (ptime > now_time)
			return false;
		return true;
	}

	/******** 取得最大用户数据ID ************/
	static public int getMaxPcid() {
		List<Player> list = ProPlayer.getListAll();
		if (Svc.isEmpty(list))
			return 0;
		int len = list.size();
		int result = 0;
		for (int i = 0; i < len; i++) {
			Player en = list.get(i);
			int pcid = en.getPcid();
			if (pcid > 5000000)
				continue;
			if (pcid > result)
				result = pcid;
		}
		return result;
	}
}
