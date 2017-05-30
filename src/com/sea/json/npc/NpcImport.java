package com.sea.json.npc;

import gen_b2g.serv.bean.ConstantType;
import gen_b2g.serv.bean.NArmys;
import gen_b2g.serv.bean.NBuilds;
import gen_b2g.serv.bean.NPlayer;
import gen_b2g.serv.bean.NTeches;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bowlong.util.MapEx;
import com.sea.cache.process.ProAssistFight;
import com.sea.channel.ChannelCfg;
import com.sea.db.bean.Player;
import com.sea.db.bean.Player_armys;
import com.sea.db.bean.Player_build_obst;
import com.sea.db.bean.Player_buildings;
import com.sea.db.bean.Player_tech;
import com.sea.db.entity.PlayerEntity;
import com.sea.db.entity.Player_armysEntity;
import com.sea.db.entity.Player_build_obstEntity;
import com.sea.db.entity.Player_buildingsEntity;
import com.sea.db.entity.Player_techEntity;
import com.sea.logic.logicEntity.LEBuild;
import com.sea.logic.logicEntity.PlayerStatus;
import com.sea.logic.logicOperate.LogicPlayer;
import com.sea.tools.ReadWriteString;
import com.sea.tools.UtileTools;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class NpcImport {

	static int posIndex = 1;
	/*** 最大主城等级 **/
	static int maxTHLev = 10;

	static private int getMinMax(Map origin) {
		String minKey = NpcExport.Key_Min;
		String maxKey = NpcExport.Key_Max;

		int cur = 0;
		int min = MapEx.getInt(origin, minKey);
		int max = MapEx.getInt(origin, maxKey);

		if (max == 1) {
			cur = 1;
		} else {
			int diff = max - min;
			if (diff <= 0) {
				cur = max;
			} else {
				min = min + diff / 2;
				cur = UtileTools.randIntContainMax(min, max);
			}
		}
		return cur;
	}

	static private int getLvl(Map origin) {
		int cur = 1;
		String keyLvl = "lvl";
		String lvlStr = MapEx.getString(origin, keyLvl);
		Map lvlMap = UtileTools.strToMap(lvlStr);
		cur = getMinMax(lvlMap);
		return cur;
	}

	private static int getNum(Map origin) {
		int cur = 1;
		String keyLvl = "num";
		String lvlStr = MapEx.getString(origin, keyLvl);
		Map lvlMap = UtileTools.strToMap(lvlStr);
		cur = getMinMax(lvlMap);
		return cur;
	}

	public static int getRenownRandom(int townLvl, int renown) {

		if (renown <= 200 || renown >= 10000)
			return 0;

		int lvlRan = getTownLvlByRenown(renown);
		int min = 0;
		int max = 0;
		boolean isMaxLvl = lvlRan == maxTHLev && townLvl == lvlRan;
		if (isMaxLvl) {
			min = renown - 1000;
			max = renown - 400;
		} else {
			if (lvlRan > townLvl) {
				min = renown - 800;
				max = renown - 200;
			} else {
				double k = townLvl / (double) lvlRan;
				min = renown - (int) (300 * k);
				max = renown + (int) (100 / k);
			}
		}
		min = min <= 0 ? 0 : min;

		return UtileTools.randInt(min, max);
	}

	public static Player importJson(String strChn, int townLvl, int pcid,
			int renownAll, NPlayer beAttPlayer, NTeches bePTeches,
			NArmys bePArmys, NBuilds bePBuilds) {
		if (townLvl < 0)
			return null;
		townLvl %= 10;
		Player p = readJsonNPC(townLvl, renownAll);
		if (p == null)
			return null;

		String pname = LogicPlayer.randomName(strChn);
		if ("".equals(pname)) {
			pname = p.getPname();
		}
		p.setPname(pname);

		List<Player_buildings> list_build = readJsonNPCBuild(townLvl, pname);
		List<Player_build_obst> list_obst = readJsonNPCObst(townLvl, pname);
		List<Player_armys> list_army = readJsonNPCArmy(townLvl, pname);
		List<Player_tech> list_tech = readJsonNPCTech(townLvl, pname);

		// System.out.println(p.toString());

		ProAssistFight.setBePlayer(pcid, p.getPcid());

		PlayerEntity.getNPlayer(p, beAttPlayer);
		Player_buildingsEntity.toSUPBuilds(list_build, bePBuilds);
		Player_build_obstEntity.toSUPBuilds(list_obst, bePBuilds);
		Player_armysEntity.toSUPArmys(list_army, bePArmys);
		Player_techEntity.toSUPTeches(list_tech, bePTeches);
		return p;
	}

	static Player newNPC(int townLvl, int renown, int exp, int stored_gold,
			int stored_oil) {
		int ucid = 0;
		int pcid = 0;
		int server_id = 0;
		String pname = "npc" + townLvl;
		int type = ConstantType.Type_User_NPC;
		int state = PlayerStatus.Normal;
		int icon = 0;
		int crystal = 0;
		int cur_npc = 0;
		String npcs = "";
		int all_money = 0;
		int last_money = 0;
		long last_pay_time = 0l;
		int guid_step = 200;
		String clancid = "";
		int clanPost = 0;
		String cname = "";
		int cicon = 0;

		int maxAttMCId = 0;
		int maxObstId = 0;
		int maxBONum = 0;
		int curBONum = 0;
		int maxBuidId = 0;
		boolean isOnline = false;
		long protectTime = 0l;
		String spells = "";
		long lastAddObst = 0l;
		String mark = "";
		int weekRenown = 0;
		int loginDay = 0;
		long lastLoginTime = 0;
		boolean isRewardDay = false;
		int firstPayStatus = 0;
		int hp = 0;
		int dam = 0;
		int speed = 0;
		String channel = ChannelCfg.DEMO;
		String dayTasks = "";
		long lastLeaveClan = 0;
		long monCode = 0;
		boolean isMonCode = false;

		String builds = "";
		String obstes = "";
		String teches = "";
		String heroes = "";
		int share = 0;
		double moneyActivity = 0;
		int moneyActivityType = 0;

		Player r = Player
				.newPlayer(0, pcid, ucid, server_id, pname, type, state,
						channel, icon, exp, crystal, renown, weekRenown,
						cur_npc, npcs, all_money, last_money, last_pay_time,
						guid_step, clancid, clanPost, cname, cicon, maxBuidId,
						maxObstId, stored_oil, stored_gold, isOnline,
						protectTime, maxAttMCId, maxBONum, curBONum, spells,
						lastAddObst, mark, townLvl, loginDay, lastLoginTime,
						isRewardDay, firstPayStatus, hp, dam, speed, dayTasks,
						lastLeaveClan, monCode, isMonCode, builds, obstes,
						teches, heroes, share, moneyActivity, moneyActivityType);
		return r;
	}

	private static Player readJsonNPC(int townLvl, int renownAll) {
		String path = NpcExport.getJsonPathPlayer(townLvl,
				NpcExport.Type_Player);
		String strData = ReadWriteString.readStr(path);
		Map map_ = UtileTools.strToMap(strData);

		// String strRenown = MapEx.getString(map_, "renown");
		// Map renownMap = UtileTools.strToMap(strRenown);

		String strExp = MapEx.getString(map_, "exp");
		Map expMap = UtileTools.strToMap(strExp);

		String strGold = MapEx.getString(map_, "gold");
		Map goldMap = UtileTools.strToMap(strGold);

		String strOld = MapEx.getString(map_, "oil");
		Map oilMap = UtileTools.strToMap(strOld);

		int renown = getRenownRandom(townLvl, renownAll);

		int exp = getMinMax(expMap);

		int gold = getMinMax(goldMap);

		int oil = getMinMax(oilMap);

		Player r = newNPC(townLvl, renown, exp, gold, oil);

		UtileTools.clearMap(map_);
		return r;
	}

	static Map getPosBuild(int townLvl) {
		String pathBuildPos = NpcExport.getJsonPathPlayer(townLvl,
				NpcExport.Type_Player_Build_Pos);
		Map mapPos_ = UtileTools.readStrMap(pathBuildPos);
		int len = MapEx.getInt(mapPos_, "len");
		int index = 1;
		if (len > 1) {
			index = UtileTools.randIntContainMax(1, len);
		}
		posIndex = index;

		String posVal = MapEx.getString(mapPos_, "pos_" + posIndex);

		UtileTools.clearMap(mapPos_);

		Map r = UtileTools.strToMap(posVal);
		return r;
	}

	static Map getBuildAttriMap(Map map_) {
		String keyLvl = "lvl";
		int curLvl = getLvl(map_);
		map_.put(keyLvl, curLvl);
		return map_;
	}

	private static List<Player_buildings> readJsonNPCBuild(int townLvl,
			String pname) {

		List<Player_buildings> r = new ArrayList<Player_buildings>();

		Map mapPos = getPosBuild(townLvl);
		String pathBuild = NpcExport.getJsonPathPlayer(townLvl,
				NpcExport.Type_Player_Build);
		Map mapBuild = UtileTools.readStrMap(pathBuild);

		int pcid = 0;

		for (Object o : mapBuild.keySet()) {
			int bcid = Integer.parseInt(o.toString());
			int pos = MapEx.getInt(mapPos, bcid);

			Map val = MapEx.getMap(mapBuild, o);

			Map attMap = getBuildAttriMap(val);
			int gid = MapEx.getInt(attMap, "gid");
			int lvl = townLvl;
			if (gid != LEBuild.GID_Headquarters) {
				lvl = MapEx.getInt(attMap, "lvl");
			}

			Player_buildings pbuild = Player_buildingsEntity.createNewBuild(
					pcid, pname, gid, 0, lvl, pos, bcid);

			r.add(pbuild);
		}
		UtileTools.clearMap(mapBuild);
		UtileTools.clearMap(mapPos);
		return r;
	}

	static Map getPosObst(int townLvl) {
		String pos_ = NpcExport.getJsonPathPlayer(townLvl,
				NpcExport.Type_Player_Obst_Pos);
		Map mapPos_ = UtileTools.readStrMap(pos_);
		String posVal = MapEx.getString(mapPos_, "pos_" + posIndex);

		UtileTools.clearMap(mapPos_);

		Map r = UtileTools.strToMap(posVal);
		return r;
	}

	static Map getObstAttriMap(Map map_) {
		String keyLvl = "lvl";
		int curLvl = getLvl(map_);
		map_.put(keyLvl, curLvl);
		return map_;
	}

	private static int getObstWallLvl(int townLvl) {
		int ranK = UtileTools.randIntK();
		int r = 2;
		switch (townLvl) {
		case 2:
			if (ranK < 650) {
				r = 2;
			} else {
				r = 3;
			}
			break;
		case 3:
			if (ranK < 400) {
				r = 2;
			} else if (ranK < 800) {
				r = 3;
			} else {
				r = 4;
			}
			break;
		case 4:
			if (ranK < 200) {
				r = 2;
			} else if (ranK < 500) {
				r = 3;
			} else if (ranK < 800) {
				r = 4;
			} else {
				r = 5;
			}
			break;
		default:
			if (ranK < 100) {
				r = 2;
			} else if (ranK < 300) {
				r = 3;
			} else if (ranK < 500) {
				r = 4;
			} else if (ranK < 800) {
				r = 5;
			} else {
				r = 6;
			}
			break;
		}
		return r;
	}

	private static List<Player_build_obst> readJsonNPCObst(int townLvl,
			String pname) {

		List<Player_build_obst> r = new ArrayList<Player_build_obst>();

		Map mapPos = getPosObst(townLvl);

		String pathBuild = NpcExport.getJsonPathPlayer(townLvl,
				NpcExport.Type_Player_Obst);
		Map mapBuild = UtileTools.readStrMap(pathBuild);

		int pcid = 0;
		int lvlWall = getObstWallLvl(townLvl);

		for (Object o : mapBuild.keySet()) {
			int bcid = Integer.parseInt(o.toString());
			int pos = MapEx.getInt(mapPos, bcid);

			Map val = MapEx.getMap(mapBuild, o);

			Map attMap = getObstAttriMap(val);
			int lvl = 1;
			int gid = MapEx.getInt(attMap, "gid");
			if (gid == LEBuild.GID_Wall) {
				// lvl = MapEx.getInt(attMap, "lvl");
				lvl = lvlWall;
			}

			Player_build_obst pbuild = Player_build_obstEntity.createNew(pcid,
					pname, gid, 0, lvl, pos, bcid);

			r.add(pbuild);
		}
		UtileTools.clearMap(mapBuild);
		UtileTools.clearMap(mapPos);
		return r;
	}

	private static List<Player_armys> readJsonNPCArmy(int townLvl, String pname) {
		List<Player_armys> r = new ArrayList<Player_armys>();
		String path = NpcExport.getJsonPathPlayer(townLvl,
				NpcExport.Type_Player_Army);
		int pcid = 0;
		List list = UtileTools.readStrList(path);
		for (Object item : list) {
			Map map = (Map) item;
			int num = getNum(map);
			int lvl = getLvl(map);
			int bcid = MapEx.getInt(map, "bcid");
			int gid = MapEx.getInt(map, "gid");
			Player_armys armyItem = Player_armysEntity.createNewArmy(pcid,
					pname, gid, lvl, num, bcid);
			r.add(armyItem);
		}
		UtileTools.clearList(list);
		return r;
	}

	private static List<Player_tech> readJsonNPCTech(int townLvl, String pname) {
		List<Player_tech> r = new ArrayList<Player_tech>();
		String path = NpcExport.getJsonPathPlayer(townLvl,
				NpcExport.Type_Player_Tech);
		int pcid = 0;
		List list = UtileTools.readStrList(path);
		for (Object item : list) {
			Map map = (Map) item;
			int lvl = getLvl(map);
			int gid = MapEx.getInt(map, "gid");
			Player_tech tecnItem = Player_techEntity.createNewTech(gid, lvl,
					pcid, pname);
			r.add(tecnItem);
		}
		UtileTools.clearList(list);
		return r;
	}

	// =====================
	private static int getTownLvlByRenown(int renown) {
		int r = 2;
		if (renown > 10000) {
			r = 10;
		} else if (renown > 6000) {
			r = 9;
		} else if (renown > 4000) {
			r = 8;
		} else if (renown > 3000) {
			r = 7;
		} else if (renown > 2600) {
			r = 6;
		} else if (renown > 1800) {
			r = 5;
		} else if (renown > 1300) {
			r = 4;
		} else if (renown > 800) {
			r = 3;
		} else {
			r = 2;
		}
		return r;
	}

	public static int getTownLvlBy(int lvl, int renown) {

		int randK = UtileTools.randIntK();
		int lvlRenown = getTownLvlByRenown(renown);

		if (lvlRenown > lvl && randK < 400)
			return lvlRenown;

		if (lvl <= 2) {
			if (randK <= 600) {
				return 2;
			} else {
				return 3;
			}
		}

		int tmpLvl = lvl + 2;
		tmpLvl = tmpLvl >= 9 ? 9 : tmpLvl;

		if (randK <= 250) {
			return (tmpLvl - 3);
		} else if (randK <= 650) {
			return (tmpLvl - 2);
		} else if (randK <= 850) {
			return (tmpLvl - 1);
		} else {
			return tmpLvl;
		}
	}

	public static int getNPCTownLvl(Player p) {
		int lvl = 0;
		int renown = 0;
		if (p != null) {
			lvl = p.getCurtownlvl();
			renown = p.getRenown();
		}
		return getTownLvlBy(lvl, renown);
	}

	public static Player getBeAttNPC(Player attPlayer, NPlayer beAttPlayer,
			NTeches bePTeches, NArmys bePArmys, NBuilds bePBuilds) {
		if (attPlayer == null)
			return null;
		int pcid = attPlayer.getPcid();
		int townLvl = getNPCTownLvl(attPlayer);
		String strChn = attPlayer.getChannel();
		int renownAll = attPlayer.getRenown();

		return importJson(strChn, townLvl, pcid, renownAll, beAttPlayer,
				bePTeches, bePArmys, bePBuilds);
	}
}