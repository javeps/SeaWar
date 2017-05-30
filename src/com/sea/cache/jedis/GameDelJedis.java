package com.sea.cache.jedis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import com.sea.cache.jedis.game.AssistUserPlayerJedis;
import com.sea.cache.jedis.game._GameKeys;
import com.sea.cache.jedis.origin.JedisHash;
import com.sea.cache.process.ProAmry;
import com.sea.cache.process.ProBuild;
import com.sea.cache.process.ProClan;
import com.sea.cache.process.ProHero;
import com.sea.cache.process.ProObst;
import com.sea.cache.process.ProPlayer;
import com.sea.cache.process.ProProduct;
import com.sea.cache.process.ProTech;
import com.sea.cache.process.ProUser;
import com.sea.content.AppContext;
import com.sea.content.Svc;
import com.sea.db.bean.Player;
import com.sea.db.entity.PlayerEntity;
import com.sea.tools.UtileTools;

public class GameDelJedis implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(GameDelJedis.class);

	/***** 删除所有的patterns例如(k:up:reward:*) *********/
	static public void delAllByPatternKey(Jedis r, List<String> listPatt) {
		if (listPatt == null || listPatt.isEmpty())
			return;
		String[] patterns = {};
		patterns = listPatt.toArray(patterns);
		delAllByPatternKey(r, patterns);
	}

	/***** 删除所有的patterns例如(k:up:reward:*) *********/
	static public void delAllByPatternKey(Jedis r, String... patterns) {
		if (patterns == null || patterns.length <= 0)
			return;
		boolean isNew = false;
		try {
			if (r == null) {
				isNew = true;
				r = AppContext.getJedis();
			}
			List<String> list = new ArrayList<String>();

			for (String pattern : patterns) {
				Set<String> set = r.keys(pattern);
				if (set != null) {
					list.addAll(set);
				}
			}

			if (!list.isEmpty()) {
				Pipeline pipeline = r.pipelined();
				delAllByKeys(pipeline, list);
			}
		} catch (Exception e) {
		} finally {
			if (isNew)
				AppContext.returnJedis(r);
		}
	}

	/***** 删除所有的key例如(k:up:reward:12) *********/
	static public void delAllByKeys(Pipeline pipeline, List<String> keys) {
		if (Svc.isEmpty(keys))
			return;
		String[] keyarr = {};
		keyarr = keys.toArray(keyarr);
		delAllByKeys(pipeline, keyarr);
	}

	static public void delAllByKeys(Pipeline pipeline, String... keys) {
		if (keys == null || keys.length <= 0)
			return;
		Jedis r = null;
		boolean isNew = false;
		try {
			if (pipeline == null) {
				r = AppContext.getJedis();
				pipeline = r.pipelined();
				isNew = true;
			}
			pipeline.del(keys);
			pipeline.syncAndReturnAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (isNew)
				AppContext.returnJedis(r);
		}
	}

	/***** 删除所有的key,fiel例如(Map k ,v为filed) *********/
	static public void delAllByMap(Jedis r, Map<String, String> delMap) {
		if (Svc.isEmpty(delMap))
			return;
		boolean isNew = false;
		try {
			if (r == null) {
				r = AppContext.getJedis();
				isNew = true;
			}
			delAllByMapPip(r.pipelined(), delMap);
		} catch (Exception e) {
		} finally {
			if (isNew)
				AppContext.returnJedis(r);
		}
	}

	/** 删除 map的value为redis数据中的key,map的key为redis数据中的field */
	static public void delAllByMapPipByVKey(Pipeline pipeline,
			Map<String, String> vkeyMap) {
		if (Svc.isEmpty(vkeyMap))
			return;
		Jedis r = null;
		boolean isNew = false;
		try {
			if (pipeline == null) {
				r = AppContext.getJedis();
				pipeline = r.pipelined();
				isNew = true;
			}
			for (Entry<String, String> e : vkeyMap.entrySet()) {
				String field = e.getKey();
				String key = e.getValue();
				pipeline.hdel(key, field);
			}

			pipeline.syncAndReturnAll();
		} catch (Exception e) {
		} finally {
			if (isNew)
				AppContext.returnJedis(r);
		}
	}

	/** 删除 map的key为redis数据中的key,map的value为redis数据中的field */
	static public void delAllByMapPip(Pipeline pipeline,
			Map<String, String> delMap) {
		if (Svc.isEmpty(delMap))
			return;
		Jedis r = null;
		boolean isNew = false;
		try {
			if (pipeline == null) {
				r = AppContext.getJedis();
				pipeline = r.pipelined();
				isNew = true;
			}
			for (Entry<String, String> e : delMap.entrySet()) {
				String key = e.getKey();
				String field = e.getValue();
				pipeline.hdel(key, field);
			}

			pipeline.syncAndReturnAll();
		} catch (Exception e) {
		} finally {
			if (isNew)
				AppContext.returnJedis(r);
		}
	}

	// ===================
	static void deleteFromUser(Player p, boolean isDelUser) {
		ProUser.delUserByPlayer(p, isDelUser);
	}

	private static void deleteFromClan(Player p) {
		ProClan.delClanByPlayer(p);
	}

	public static void deletePlayer(String pname, boolean isDelUser) {
		Player p = PlayerEntity.getPlayer(pname);
		deletePlayer(p, isDelUser);
	}

	public static void deletePlayer(Player p, boolean isDelUser) {
		if (p == null)
			return;
		int pcid = p.getPcid();

		ProBuild.deleteAllBuildByPlayer(p);
		ProObst.deleteAllBuildByPlayer(p);
		ProTech.deleteAllByPlayer(p);
		ProHero.deleteAllByPlayer(p);
		AssistUserPlayerJedis.deleteUPVal(p);

		ProAmry.clearCache(pcid);
		ProProduct.clearCache(pcid);
		ProPlayer.delCache(p);

		Jedis r = null;
		try {
			r = AppContext.getJedis();
			deletePlayerInfo(r, p, isDelUser);
		} catch (Exception e) {
			AppContext.returnJedisBroken(null, r);
			log.error(UtileTools.ex2s(e));
		} finally {
			AppContext.returnJedis(r);
		}
		deleteFromUser(p, isDelUser);
	}

	static private void deletePlayerInfo(Jedis r, Player p, boolean isDelUser) {
		if (p == null)
			return;
		if (r == null)
			return;
		int pcid = p.getPcid();
		// ========删除pattern
		String patt_tech = _GameKeys.fmt(_GameKeys.Pattern_Key_PTech, pcid);
		String patt_build = _GameKeys.fmt(_GameKeys.Pattern_Key_PBuild, pcid);
		String patt_Obst = _GameKeys.fmt(_GameKeys.Pattern_Key_PObst, pcid);
		String patt_Hero = _GameKeys.fmt(_GameKeys.Pattern_Key_PHero, pcid);
		delAllByPatternKey(r, patt_build, patt_Hero, patt_Obst, patt_tech);

		// ========删除 jedisKey
		Pipeline pip = r.pipelined();
		List<String> delKeys = new ArrayList<String>();
		int ucid = p.getUcid();
		String key_player = _GameKeys.fmt(_GameKeys.Pattern_Key_PUcid, ucid,
				pcid);
		String key_mailPl = _GameKeys.fmt(_GameKeys.Pattern_Key_Mail_Pl_ToPcid,
				pcid);
		String key_npinfo = _GameKeys.fmtPinfoPcid(pcid);
		// 战报
		String key_fight = _GameKeys.fmt(_GameKeys.Pattern_Key_Mail_Att_Bepcid,
				pcid);

		delKeys.add(key_npinfo);
		delKeys.add(key_mailPl);
		delKeys.add(key_player);
		delKeys.add(key_fight);

		Set<String> fightFields = r.hkeys(key_fight);
		if (!Svc.isEmpty(fightFields)) {
			List<String> fflist = JedisHash.changeSetToList(fightFields);
			for (String mcid : fflist) {
				delKeys.add(_GameKeys.fmt(_GameKeys.Pattern_Key_Mail_Att_Pinfo,
						mcid));
			}
		}
		delAllByKeys(pip, delKeys);

		// ========删除 jedisKey field
		Map<String, String> map = new HashMap<String, String>();
		map.put(_GameKeys.Assist_Player_PCID, pcid + "");
		map.put(_GameKeys.Assist_Player_Name, p.getPname());

		delAllByMapPip(pip, map);

		String ccid = p.getClancid();
		if (!Svc.isEmpty(ccid)) {
			deleteFromClan(p);
		}
	}

	// ================== 删除邮件
	static private void delMailPlayer(Jedis jedis, List<String> timeList) {
		if (Svc.isEmpty(timeList))
			return;
		boolean isNew = false;
		try {
			if (jedis == null) {
				jedis = AppContext.getJedis();
				isNew = true;
			}

			if (jedis == null)
				return;
			Map<String, String> delMap = new HashMap<String, String>();

			List<String> delKeys = new ArrayList<String>();

			for (String item : timeList) {
				String key = _GameKeys.fmt(_GameKeys.Pattern_Key_Mail_Pl_Day,
						item);
				boolean isHasKey = jedis.exists(key);
				if (!isHasKey)
					continue;

				delKeys.add(key);

				Map<String, String> map = jedis.hgetAll(key);
				if (Svc.isEmpty(map)) {
					continue;
				}

				delMap.putAll(map);
			}

			Pipeline pipeline = jedis.pipelined();

			delAllByMapPipByVKey(pipeline, delMap);
			delAllByKeys(pipeline, delKeys);
		} catch (Exception e) {
			AppContext.returnJedisBroken(null, jedis);
			log.error(UtileTools.ex2s(e));
		} finally {
			if (isNew) {
				AppContext.returnJedis(jedis);
			}
		}

	}

	static private void delMailFight(Jedis jedis, List<String> timeList) {
		if (Svc.isEmpty(timeList))
			return;
		boolean isNew = false;
		try {
			if (jedis == null) {
				jedis = AppContext.getJedis();
				isNew = true;
			}
			Map<String, String> delMap = new HashMap<String, String>();

			List<String> delKeys = new ArrayList<String>();

			for (String item : timeList) {
				String key = _GameKeys.fmt(_GameKeys.Pattern_Key_Mail_Att_Day,
						item);
				boolean isHasKey = jedis.exists(key);
				if (!isHasKey)
					continue;

				delKeys.add(key);

				Map<String, String> map = jedis.hgetAll(key);
				if (Svc.isEmpty(map)) {
					continue;
				}

				delMap.putAll(map);

				for (String f : map.keySet()) {
					String strPIKey = _GameKeys.fmt(
							_GameKeys.Pattern_Key_Mail_Att_Pinfo, f);
					delKeys.add(strPIKey);
				}
			}

			Pipeline pipeline = jedis.pipelined();
			delAllByMapPipByVKey(pipeline, delMap);
			delAllByKeys(pipeline, delKeys);
		} catch (Exception e) {
			AppContext.returnJedisBroken(null, jedis);
			log.error(UtileTools.ex2s(e));
		} finally {
			if (isNew) {
				AppContext.returnJedis(jedis);
			}
		}
	}

	static public void delMail(Date date) {
		List<String> timeList = UtileTools.getDelTimerList(date, 3);
		Jedis r = null;
		try {
			r = AppContext.getJedis();
			delMailPlayer(r, timeList);
			delMailFight(r, timeList);
		} catch (Exception e) {
			AppContext.returnJedisBroken(null, r);
			log.error(UtileTools.ex2s(e));
		} finally {
			AppContext.returnJedis(r);
		}
	}
}
