package com.sea.cache.jedis.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.sql.mysql.BeanSupport;
import com.bowlong.util.DateEx;
import com.bowlong.util.MapEx;
import com.sea.cache.jedis.GameDelJedis;
import com.sea.cache.jedis.origin.JedisHash;
import com.sea.cache.process.ProBuild;
import com.sea.cache.process.ProObst;
import com.sea.content.Svc;
import com.sea.db.bean.Player;
import com.sea.db.bean.Player_build_obst;
import com.sea.db.bean.Player_buildings;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class PlayerJedis extends GameBaseJedis implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(PlayerJedis.class);

	public static Player toObjByStrVal(String strVal) {
		Map map_ = UtileTools.strToMap(strVal);
		return toObjByMap(map_);
	}

	public static Player toObjByMap(Map map) {
		if (map == null)
			return null;
		int pcid = MapEx.getInt(map, "pcid");
		int ucid = MapEx.getInt(map, "ucid");
		if (pcid == 0) {
			String key_Player = getKeyPlayer(ucid, pcid);
			JedisHash.delKeys(key_Player);
			return null;
		}
		int id = MapEx.getInt(map, "id");

		int svcid = MapEx.getInt(map, "svcid");
		String pname = MapEx.getString(map, "pname");
		int type = MapEx.getInt(map, "type");
		int state = MapEx.getInt(map, "state");
		int icon = MapEx.getInt(map, "icon");
		int exp = MapEx.getInt(map, "exp");
		int crystal = MapEx.getInt(map, "crystal");
		int renown = MapEx.getInt(map, "renown");
		int weekRenown = MapEx.getInt(map, "weekRenown");

		int cur_npc = MapEx.getInt(map, "cur_npc");
		String npcs = MapEx.getString(map, "npcs");
		int all_money = MapEx.getInt(map, "all_money");
		int last_money = MapEx.getInt(map, "last_money");
		long last_pay_time = MapEx.getLong(map, "last_pay_time");
		int guid_step = MapEx.getInt(map, "guid_step");
		String clancid = MapEx.getString(map, "clancid");
		int clanPost = MapEx.getInt(map, "clanPost");
		String cname = MapEx.getString(map, "cname");
		int cicon = MapEx.getInt(map, "cicon");

		int maxBuidId = MapEx.getInt(map, "maxBuidId");
		int maxObstId = MapEx.getInt(map, "maxObstId");
		int stored_oil = MapEx.getInt(map, "stored_oil");
		int stored_gold = MapEx.getInt(map, "stored_gold");
		boolean isOnline = MapEx.getBoolean(map, "isOnline");
		long protectTime = MapEx.getLong(map, "protectTime");
		int maxAttMCId = MapEx.getInt(map, "maxAttMCId");
		int maxBONum = MapEx.getInt(map, "maxBONum");
		int curBONum = MapEx.getInt(map, "curBONum");
		String spells = MapEx.getString(map, "spells");
		long lastAddObst = MapEx.getLong(map, "lastAddObst");
		String mark = MapEx.getString(map, "mark");
		int curtownlvl = MapEx.getInt(map, "curtownlvl");
		int loginDay = MapEx.getInt(map, "loginDay");
		long lastLoginTime = MapEx.getLong(map, "lastLoginTime");
		boolean isRewardDay = MapEx.getBoolean(map, "isRewardDay");
		int firstPayStatus = MapEx.getInt(map, "firstPayStatus");
		int pieceHPNum = MapEx.getInt(map, "pieceHPNum");
		int pieceDamNum = MapEx.getInt(map, "pieceDamNum");
		int pieceAttSpeed = MapEx.getInt(map, "pieceAttSpeed");

		String channel = MapEx.getString(map, "channel");
		String dayTasks = MapEx.getString(map, "dayTasks");
		long lastLeaveClan = MapEx.getLong(map, "lastLeaveClan");
		long monCode = MapEx.getLong(map, "monthCode");
		boolean isMonCode = MapEx.getBoolean(map, "isMonCode");

		String builds = MapEx.getString(map, "builds");
		String obstes = MapEx.getString(map, "obstes");
		String teches = MapEx.getString(map, "teches");
		String heroes = MapEx.getString(map, "heroes");
		int share = MapEx.getInt(map, "share");
		double moneyActivity = MapEx.getDouble(map, "moneyActivity");
		int moneyActivityType = MapEx.getInt(map, "moneyActivityType");

		return Player.newPlayer(id, pcid, ucid, svcid, pname, type, state,
				channel, icon, exp, crystal, renown, weekRenown, cur_npc, npcs,
				all_money, last_money, last_pay_time, guid_step, clancid,
				clanPost, cname, cicon, maxBuidId, maxObstId, stored_oil,
				stored_gold, isOnline, protectTime, maxAttMCId, maxBONum,
				curBONum, spells, lastAddObst, mark, curtownlvl, loginDay,
				lastLoginTime, isRewardDay, firstPayStatus, pieceHPNum,
				pieceDamNum, pieceAttSpeed, dayTasks, lastLeaveClan, monCode,
				isMonCode, builds, obstes, teches, heroes, share,
				moneyActivity, moneyActivityType);
	}

	// =========== method
	public static Player getPlayerBy(String key) {
		Map<String, String> map = JedisHash.getMap(key);
		return toObjByMap(map);
	}

	public static Player getPlayerBy(int ucid, int pcid) {
		String key = getKeyPlayer(ucid, pcid);
		return getPlayerBy(key);
	}

	public static void setList(List<Player> origin) {
		if (origin == null || origin.size() <= 0)
			return;
		List<BeanSupport> list = new ArrayList<BeanSupport>();
		list.addAll(origin);
		setListDataByKMap(list);
	}

	private static List<Player> getListByNewKL(List<String> keyes) {
		if (keyes == null || keyes.isEmpty())
			return null;
		List<Map<String, String>> origin = JedisHash.getListMap(keyes);
		if (origin == null || origin.isEmpty())
			return null;

		List<Player> r = new ArrayList<Player>();
		for (Map<String, String> map : origin) {
			Player item = toObjByMap(map);
			if (item == null) {
				log.info("player 为空，map=" + map);
				continue;
			}
			r.add(item);
		}
		return r;
	}

	public static List<Player> getListAll() {
		List<String> keyes = JedisHash
				.getListKeysByPattern(_GameKeys.Pattern_Key_AllPlayer);
		return getListByNewKL(keyes);
	}

	public static List<Player> getList(String pcids, int ucid) {
		List<String> keys = null;
		if (pcids == null || "".equals(pcids.trim())) {
			String pattern = getPatternPlayer(ucid);
			keys = JedisHash.getListKeysByPattern(pattern);
		} else {
			keys = new ArrayList<String>();
			String[] bes = pcids.split(",");
			for (String item : bes) {
				if (item == null || "".equals(item.trim()))
					continue;
				int pcid = 0;
				try {
					pcid = Integer.parseInt(item);
				} catch (Exception e) {
				}
				if (pcid == 0)
					continue;
				String key = getKeyPlayer(ucid, pcid);
				keys.add(key);
			}
		}
		return getListByNewKL(keys);
	}

	public static void changeOtherPName(Player p) {
		if (p == null)
			return;
		String pname = p.getPname();
		int pcid = p.getPcid();
		String builds = p.getBuilds();
		List<Player_buildings> list_build = ProBuild
				.getListByPcid(pcid, builds);
		if (list_build != null) {
			for (Player_buildings item_build : list_build) {
				item_build.setPlayer_name(pname);
				ProBuild.upBuild(item_build);
			}
		}

		String obstes = p.getObstes();
		List<Player_build_obst> list_obst = ProObst.getListByPcid(pcid, obstes);
		if (list_obst != null) {
			for (Player_build_obst item_obst : list_obst) {
				item_obst.setPlayer_name(pname);
				ProObst.upBuild(item_obst);
			}
		}
	}

	public static void delPlayers(List<Player> origin, int max) {
		if (origin == null)
			origin = getListAll();
		delPlayersByOrigin(origin, max);
	}

	public static void delPlayersByOrigin(List<Player> origin, int maxDelLen) {
		if (origin == null || origin.isEmpty())
			return;
		List<Player> delList = new ArrayList<Player>();
		long diffTime = DateEx.TIME_DAY * 20;
		long nowTime = System.currentTimeMillis();

		int allLen = origin.size();
		int delLen = 0;
		int resLen = 0;
		if (maxDelLen < 0)
			maxDelLen = 100;

		for (int i = 0; i < allLen; i++) {
			if (maxDelLen <= 0)
				break;
			Player p = origin.get(i);

			if (p == null)
				continue;

			if (p.getIsonline())
				continue;

			int money = p.getAll_money();
			if (money > 0) {
				continue;
			}

			int thLvl = p.getCurtownlvl();
			if (thLvl > 3) {
				continue;
			}

			String ccid = p.getClancid();
			if (ccid != null) {
				ccid = ccid.trim();
				if (!Svc.isEmpty(ccid) && !"0".equals(ccid)) {
					continue;
				}
			}

			int guid = p.getGuid_step();
			if (guid < 100) {
				long ptime = p.getProtecttime();
				if (ptime <= nowTime) {
					delList.add(p);
					maxDelLen--;
					continue;
				}
			}

			long lastLogin = p.getLastlogintime();
			lastLogin += diffTime;
			if (lastLogin <= nowTime) {
				delList.add(p);
				maxDelLen--;
			}
		}

		delLen = delList.size();
		resLen = allLen - delLen;
		log.info("====总计====" + allLen + ",剩余:" + resLen + ",删除：" + delLen);

		delPlayersByDelList(delList);
	}

	public static void delPlayersByDelList(List<Player> delList) {
		if (delList == null || delList.isEmpty())
			return;
		int count = 0;
		for (Player player : delList) {
			count++;
			GameDelJedis.deletePlayer(player, true);
			System.out.println("删除用户:" + count);
		}
	}

	public static void resetAllPlayersByGroup(List<Player> origin) {
		if (origin == null || origin.isEmpty())
			return;

		int count = origin.size();
		int size = 0;
		int len = 3;
		size = count / len + 1;

		log.info("===group len:" + len + ",count=" + count + ",size:" + size);

		for (int i = 0; i < len; i++) {
			int beg = i * size;
			int end = (i + 1) * size;
			if (beg == count)
				break;
			if (end > count) {
				end = count;
			}
			List<Player> list = origin.subList(beg, end);
			setList(list);
		}
	}
}