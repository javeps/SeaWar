package com.sea.json.init;

import gen_b2g.serv.bean.ConstantType;
import gen_b2g.serv.bean.NNPCBeFighted;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bowlong.json.JSON;
import com.bowlong.util.DateEx;
import com.bowlong.util.MapEx;
import com.sea.channel.ChannelCfg;
import com.sea.db.bean.Player;
import com.sea.db.bean.Player_armys;
import com.sea.db.bean.Player_tech;
import com.sea.db.entity.PlayerEntity;
import com.sea.db.entity.Player_armysEntity;
import com.sea.db.entity.Player_build_obstEntity;
import com.sea.db.entity.Player_buildingsEntity;
import com.sea.db.entity.Player_techEntity;
import com.sea.logic.logicEntity.PlayerStatus;
import com.sea.logic.logicOperate.LogicAchievement;
import com.sea.logic.logicOperate.LogicPlayer;
import com.sea.tools.ReadWriteString;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class JsonImportInit {

	public static Player importJson(int ucid, int svid, final String channel) {
		int pcid = PlayerEntity.getNewPcid();
		Player p = importJson(ucid, pcid, svid, channel);
		return p;
	}

	public static Player importJson(int ucid, int pcid, int svcid,
			final String channel) {
		int index = 0;
		Player p = readJsonPlayer(index);
		if (p != null) {
			p.setUcid(ucid);
			p.setPcid(pcid);
			String pname = "pl" + ucid;
			p.setPname(pname);
			p.setSvcid(svcid);
			p.setChannel(channel);
			PlayerEntity.insertPlayer(p);
			readJsonListBuild(index, pcid, pname);
			readJsonListArmy(index, pcid, pname);
			readJsonListTech(index, pcid, pname);
			readJsonListObst(index, pcid, pname);
		}
		return p;
	}

	private static String getFirteNpc() {
		String r = "";
		NNPCBeFighted n = new NNPCBeFighted();
		n.gold = 500;
		n.oil = 500;
		n.star = 3;
		n.ncid = 1;
		List<NNPCBeFighted> list = new ArrayList<NNPCBeFighted>();
		list.add(n);
		r = LogicPlayer.toStrNNPCs(list);
		return r;
	}

	public static Player newPlayer(int maxBuidId, int maxObstId,
			int stored_gold, int stored_oil, int crystal, String mark) {
		int ucid = 0;
		int pcid = 0;
		int renown = 0;
		int server_id = 0;
		String pname = "";// LogicPlayer.randomName();
		int type = ConstantType.Type_User_Normal;
		int state = PlayerStatus.Normal;
		int icon = 0;
		int exp = 0;
		int cur_npc = 2;
		String npcs = getFirteNpc();
		int all_money = 0;
		int last_money = 0;
		long last_pay_time = 0l;
		int guid_step = 0;

		String clancid = "";
		int clanPost = 0;
		String cname = "";
		int cicon = 0;

		int maxAttMCId = 0;
		int maxBONum = 2;
		int curBONum = 0;
		boolean isOnline = false;
		long now_time = System.currentTimeMillis();
		long protectTime = now_time + DateEx.TIME_DAY;
		String spells = "";
		long lastAddObst = now_time;
		int weekRenown = renown;
		int curtownlvl = 2;
		int loginDay = 1;
		long lastLoginTime = now_time;
		boolean isRewardDay = true;
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
						lastAddObst, mark, curtownlvl, loginDay, lastLoginTime,
						isRewardDay, firstPayStatus, hp, dam, speed, dayTasks,
						lastLeaveClan, monCode, isMonCode, builds, obstes,
						teches, heroes, share, moneyActivity, moneyActivityType);
		return r;
	}

	public static Player readJsonPlayer(int index) {
		String path = JsonExportInit.getJsonPathPlayer(index,
				JsonExportInit.Type_Player);
		String strData = ReadWriteString.readStr(path);
		Map map_ = JSON.toMap(strData);
		// int renown = MapEx.getInt(map_, "renown");
		int maxBuidId = MapEx.getInt(map_, "maxBuidId");
		int maxObstId = MapEx.getInt(map_, "maxObstId");
		int crystal = MapEx.getInt(map_, "crystal");
		int stored_gold = MapEx.getInt(map_, "stored_gold");
		int stored_oil = MapEx.getInt(map_, "stored_oil");
		String mark = "";
		mark = LogicAchievement.initStrMarkInfo();

		Player r = newPlayer(maxBuidId, maxObstId, stored_gold, stored_oil,
				crystal, mark);
		UtileTools.clearMap(map_);
		return r;
	}

	public static void readJsonListBuild(int index, int pcid, String pname) {
		String path = JsonExportInit.getJsonPathPlayer(index,
				JsonExportInit.Type_Player_Build);
		if (pcid == 0)
			return;
		String strData = ReadWriteString.readStr(path);
		try {
			List list = JSON.parseList(strData);
			for (Object item : list) {
				Map map = (Map) item;
				// System.out.println(map);
				int gid = MapEx.getInt(map, "gid");
				long cooldown = 0;
				int lvl = MapEx.getInt(map, "lvl");
				int position = MapEx.getInt(map, "pos");
				int client_id = MapEx.getInt(map, "bcid");
				Player_buildingsEntity.createNewBuildInsert(pcid, pname, gid,
						cooldown, lvl, position, client_id);
			}
			UtileTools.clearList(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void readJsonListArmy(int index, int pcid, String pname) {
		String path = JsonExportInit.getJsonPathPlayer(index,
				JsonExportInit.Type_Player_Army);
		if (pcid == 0)
			return;
		String strData = ReadWriteString.readStr(path);
		try {
			List list = JSON.parseList(strData);
			for (Object item : list) {
				Map map = (Map) item;
				// System.out.println(map);
				int gid = MapEx.getInt(map, "gid");
				int lvl = MapEx.getInt(map, "lvl");
				int num = MapEx.getInt(map, "num");
				int bcid = MapEx.getInt(map, "bcid");
				Player_armys p_a = Player_armysEntity.createNewArmy(pcid,
						pname, gid, lvl, num, bcid);
				Player_armysEntity.insertArmy(p_a);
			}
			UtileTools.clearList(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void readJsonListTech(int index, int pcid, String pname) {
		String path = JsonExportInit.getJsonPathPlayer(index,
				JsonExportInit.Type_Player_Tech);
		if (pcid == 0)
			return;
		String strData = ReadWriteString.readStr(path);
		try {
			List list = JSON.parseList(strData);
			for (Object item : list) {
				Map map = (Map) item;
				// System.out.println(map);
				int gid = MapEx.getInt(map, "gid");
				int lvl = MapEx.getInt(map, "lvl");
				Player_tech p_t = Player_techEntity.createNewTech(gid, lvl,
						pcid, pname);
				Player_techEntity.insertTech(p_t);
			}
			UtileTools.clearList(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void readJsonListObst(int index, int pcid, String pname) {
		String path = JsonExportInit.getJsonPathPlayer(index,
				JsonExportInit.Type_Player_Obst);
		if (pcid == 0)
			return;
		String strData = ReadWriteString.readStr(path);
		try {
			List list = JSON.parseList(strData);
			for (Object item : list) {
				Map map = (Map) item;
				// System.out.println(map);
				int gid = MapEx.getInt(map, "gid");
				long cooldown = 0;
				int lvl = MapEx.getInt(map, "lvl");
				int position = MapEx.getInt(map, "pos");
				int client_id = MapEx.getInt(map, "bcid");
				Player_build_obstEntity.createNewInsert(pcid, pname, gid,
						cooldown, lvl, position, client_id);
			}
			UtileTools.clearList(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		importJson(1, 1, 1, ChannelCfg.DEMO);
	}
}
