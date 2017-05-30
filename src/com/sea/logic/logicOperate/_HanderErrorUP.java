package com.sea.logic.logicOperate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.util.DateEx;
import com.sea.cache.jedis.GameDelJedis;
import com.sea.cache.jedis.game.BuildJedis;
import com.sea.cache.jedis.game.HeroJedis;
import com.sea.cache.jedis.game.ObstJedis;
import com.sea.cache.jedis.game.PlayerJedis;
import com.sea.cache.jedis.game.TechJedis;
import com.sea.cache.jedis.game.UserJedis;
import com.sea.cache.jedis.game._GameKeys;
import com.sea.cache.jedis.game._PInfoJedis;
import com.sea.cache.process.PInfoAll;
import com.sea.cache.process.ProTech;
import com.sea.content.Svc;
import com.sea.db.bean.Player;
import com.sea.db.bean.Player_build_obst;
import com.sea.db.bean.Player_buildings;
import com.sea.db.bean.Player_hero;
import com.sea.db.bean.Player_tech;
import com.sea.db.bean.User;
import com.sea.db.entity.PlayerEntity;
import com.sea.tools.ReadWriteString;
import com.sea.tools.UtileTools;

/***
 * 处理错误信息
 * 
 * @author Administrator
 * 
 */
public class _HanderErrorUP implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(_HanderErrorUP.class);

	static protected String appendStr(Object... objects) {
		if (objects == null)
			return "";

		StringBuffer sb = new StringBuffer();
		for (Object o : objects) {
			if (o == null)
				continue;
			sb.append(o);
		}
		String r = sb.toString();
		return r;
	}

	static List<String> delKeys = new ArrayList<String>();

	static public void handlerAll() {
		List<User> list = UserJedis.getListAllByNew();
		if (Svc.isEmpty(list))
			return;

		UserJedis.resetAssistUser(list);

		/** 中间map对象 ***/
		Map<Integer, String> mapTmpUcid = new HashMap<Integer, String>();
		Map<String, String> mapTmpPcid = new HashMap<String, String>();

		/** 相同对象 ***/
		Map<Integer, String> mapSameUcid = new HashMap<Integer, String>();
		Map<String, String> mapSamePcid = new HashMap<String, String>();

		int len = list.size();
		for (int i = 0; i < len; i++) {
			User en = list.get(i);
			if (en == null)
				continue;
			int ucid = en.getUcid();
			String v = mapTmpUcid.get(ucid);
			if (Svc.isEmpty(v)) {
				v = appendStr("", i);
				mapTmpUcid.put(ucid, v);
			} else {
				v = appendStr(v, ",", i);
				mapSameUcid.put(ucid, v);
			}

			String pcids = en.getPcids();
			String[] arr = pcids.split(",");
			for (String pcid : arr) {
				if (Svc.isEmpty(pcid))
					continue;

				String v2 = mapTmpPcid.get(pcid);

				if (Svc.isEmpty(v2)) {
					v2 = appendStr("", i);
					mapTmpPcid.put(pcid, v2);
				} else {
					v2 = appendStr(v2, ",", i);
					mapSamePcid.put(pcid, v2);
				}
			}
		}

		String info = "";

		// 相同的ucid
		for (Entry<Integer, String> en : mapSameUcid.entrySet()) {
			int ucid = en.getKey();
			String strInd = en.getValue();
			String[] arr = strInd.split(",");
			String v = "===== 相同的ucid:" + ucid;
			String tmp = "";
			for (String ind : arr) {
				if (Svc.isEmpty(ind))
					continue;
				int index = Integer.parseInt(ind);
				User user = list.get(index);
				if (user == null)
					continue;

				String delKey = UserJedis.getUKey(user.getLogin_uid(),
						user.getLogin_pwd());
				if (Svc.isEmpty(user.getPcids())) {
					delKeys.add(delKey);
				}

				tmp = appendStr(tmp, "\n ", delKey, ",pcids:", user.getPcids());
			}

			info = appendStr(info, v, tmp, "\n");
		}

		info = appendStr(info, "=======相同的ucid len:=====", delKeys.size(), "\n");

		info = appendStr(info, "======ucid size========", mapSameUcid.size());

		// 相同的pcid
		for (Entry<String, String> en : mapSamePcid.entrySet()) {
			String pcid = en.getKey();
			String strInd = en.getValue();
			String[] arr = strInd.split(",");
			String v = "=======相同的pcid:" + pcid;
			String tmp = "";
			for (String ind : arr) {
				if (Svc.isEmpty(ind))
					continue;
				int index = Integer.parseInt(ind);
				User user = list.get(index);
				if (user == null)
					continue;

				tmp = repairSamePcid(user, pcid, tmp);

			}

			info = appendStr(info, v, "\n", tmp, "\n");
		}

		info = appendStr(info, "======pcid size========", mapSamePcid.size());

		info = appendStr(info, "====删除的键值对====len:", delKeys.size(), "\n",
				UtileTools.listToStr(delKeys));

		ReadWriteString.writeStr("error/info.txt", info);

		GameDelJedis.delAllByKeys(null, delKeys);
	}

	/****** 删除 pcid 相同的玩家 ******/
	static String repairSamePcid(User user, String pcidStr, String info) {
		if (user == null || Svc.isEmpty(pcidStr))
			return info;

		int pcid = 0;
		try {
			pcid = Integer.parseInt(pcidStr);
		} catch (Exception e) {
		}
		if (pcid == 0)
			return info;

		Player p = PlayerJedis.getPlayerBy(user.getUcid(), pcid);
		if (p == null) {
			String delKey = UserJedis.getUKey(user.getLogin_uid(),
					user.getLogin_pwd());
			delKeys.add(delKey);
			return info;
		}

		boolean isSame = (p.getUcid() == user.getUcid());
		String lgId = user.getLogin_uid();
		String uChn = lgId.substring(0, lgId.indexOf("_"));

		isSame = isSame && uChn.equals(p.getChannel());
		if (isSame) {
			long lastLgU = user.getLogin_time();
			long lagtLgP = p.getLastlogintime();
			long dff = Math.abs(lagtLgP - lastLgU);
			if (dff < DateEx.TIME_MINUTE * 20) {
				repairSamePcidInfos(p, user);
				info = appendStr(info, "same,修改前pcid:", pcid, ",ucid:",
						user.getUcid(), ",修改后pcid:", p.getPcid(), ",",
						user.getLogin_uid(), ",", user.getLogin_pwd(), "\n");
			} else {
				String delKeyU = UserJedis.getUKey(user.getLogin_uid(),
						user.getLogin_pwd());
				delKeys.add(delKeyU);

				String delKey = _GameKeys.fmt(_GameKeys.Pattern_Key_PUcid,
						user.getUcid(), pcid);
				delKeys.add(delKey);
			}
		} else {

			String delKeyU = UserJedis.getUKey(user.getLogin_uid(),
					user.getLogin_pwd());
			delKeys.add(delKeyU);

			String delKey = _GameKeys.fmt(_GameKeys.Pattern_Key_PUcid,
					user.getUcid(), pcid);
			delKeys.add(delKey);
		}
		return info;
	}

	static Player clonePlayer(Player p, int ucid, int npcid, String channel) {
		if (p == null)
			return null;

		int svcid = p.getSvcid();
		String pname = p.getPname() + npcid;
		int type = p.getType();
		int state = p.getState();
		int icon = p.getIcon();
		int exp = p.getExp();
		int crystal = p.getCrystal();
		int renown = p.getRenown();
		int weekRenown = p.getWeekrenown();
		int cur_npc = p.getCur_npc();
		String npcs = p.getNpcs();
		int all_money = p.getAll_money();
		int last_money = p.getLast_money();
		long last_pay_time = p.getLast_pay_time();
		int guid_step = p.getGuid_step();
		String clancid = "";
		int clanPost = 0;
		String cname = "";
		int cicon = 0;
		int maxBuidId = p.getMaxbuidid();
		int stored_gold = p.getStored_gold();
		int maxAttMCId = p.getMaxattmcid();
		int stored_oil = p.getStored_oil();
		int maxBONum = p.getMaxbonum();
		int maxObstId = p.getMaxobstid();
		boolean isOnline = false;
		long protectTime = 0;
		int curBONum = p.getCurbonum();
		int curtownlvl = p.getCurtownlvl();
		String spells = p.getSpells();
		long lastAddObst = p.getLastaddobst();
		String mark = p.getMark();
		int loginDay = p.getLoginday();
		long lastLoginTime = p.getLastlogintime();
		boolean isRewardDay = p.getIsrewardday();
		int firstPayStatus = 0;
		int pieceAttSpeed = p.getPieceattspeed();
		int pieceDamNum = p.getPiecedamnum();
		int pieceHPNum = p.getPiecehpnum();
		String dayTasks = p.getDaytasks();
		long lastLeaveClan = 0;
		boolean isMonCode = p.getIsmoncode();
		long monthCode = p.getMonthcode();
		String builds = "";
		String obstes = "";
		String teches = "";
		String heroes = "";
		int share = 0;
		double moneyActivity = 0;
		int moneyActivityType = 0;

		return Player.newPlayer(0, npcid, ucid, svcid, pname, type, state,
				channel, icon, exp, crystal, renown, weekRenown, cur_npc, npcs,
				all_money, last_money, last_pay_time, guid_step, clancid,
				clanPost, cname, cicon, maxBuidId, maxObstId, stored_oil,
				stored_gold, isOnline, protectTime, maxAttMCId, maxBONum,
				curBONum, spells, lastAddObst, mark, curtownlvl, loginDay,
				lastLoginTime, isRewardDay, firstPayStatus, pieceHPNum,
				pieceDamNum, pieceAttSpeed, dayTasks, lastLeaveClan, monthCode,
				isMonCode, builds, obstes, teches, heroes, share,
				moneyActivity, moneyActivityType);
	}

	static final Map<Integer, PInfoAll> pinfoMap = new ConcurrentHashMap<Integer, PInfoAll>();

	static void repairSamePcidInfos(Player p, User u) {
		if (p == null)
			return;
		int pcid = p.getPcid();
		PInfoAll info = pinfoMap.get(pcid);
		if (info == null) {
			info = _PInfoJedis.getEnBy(pcid, true);
			pinfoMap.put(pcid, info);
			return;
		}

		String delKey = _GameKeys.fmt(_GameKeys.Pattern_Key_PUcid, u.getUcid(),
				pcid);
		delKeys.add(delKey);

		int npcid = PlayerEntity.getNewPcid();
		p.setPcid(npcid);
		PlayerJedis.setData(p);

		u.setPcids(npcid + ",");
		UserJedis.setUser(u);
		if (!Svc.isEmpty(info.getBuilds())) {
			int len = info.getBuilds().size();
			for (int i = 0; i < len; i++) {
				Player_buildings en = info.getBuilds().get(i);
				en.setPcid(npcid);
			}
			BuildJedis.setList(info.getBuilds());
		}

		if (!Svc.isEmpty(info.getObsts())) {
			int len = info.getObsts().size();
			for (int i = 0; i < len; i++) {
				Player_build_obst en = info.getObsts().get(i);
				en.setPcid(npcid);
			}
			ObstJedis.setList(info.getObsts());
		}

		if (!Svc.isEmpty(info.getHeroes())) {
			int len = info.getHeroes().size();
			for (int i = 0; i < len; i++) {
				Player_hero en = info.getHeroes().get(i);
				en.setPcid(npcid);
			}

			HeroJedis.setList(info.getHeroes());
		}

		if (!Svc.isEmpty(info.getTeches())) {
			int len = info.getTeches().size();
			for (int i = 0; i < len; i++) {
				Player_tech en = info.getTeches().get(i);
				en.setPcid(npcid);
				ProTech.upBuild(en);
			}
			TechJedis.setList(info.getTeches());
		}
	}

	public static void main(String[] args) {
		handlerAll();
	}
}
