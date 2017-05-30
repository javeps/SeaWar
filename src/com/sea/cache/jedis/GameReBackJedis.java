package com.sea.cache.jedis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import com.bowlong.sql.mysql.BeanSupport;
import com.bowlong.third.redis.JedisEx;
import com.sea.cache.jedis.game.AssistUserPlayerJedis;
import com.sea.cache.jedis.game.BuildJedis;
import com.sea.cache.jedis.game.HeroJedis;
import com.sea.cache.jedis.game.ObstJedis;
import com.sea.cache.jedis.game.PlayerJedis;
import com.sea.cache.jedis.game.TechJedis;
import com.sea.cache.jedis.game._GameKeys;
import com.sea.cache.jedis.origin.JedisHash;
import com.sea.cache.jedis.origin.KeyMapEntity;
import com.sea.db.bean.Player;
import com.sea.db.bean.Player_build_obst;
import com.sea.db.bean.Player_buildings;
import com.sea.db.bean.Player_hero;
import com.sea.db.bean.Player_tech;
import com.sea.tools.UtileTools;

public class GameReBackJedis implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(GameReBackJedis.class);

	// //////////// 以前模式 ////////////
	static public void rebackByPcid(int pcid) {
		List<KeyMapEntity> list = getOriginByPcid(pcid);
		rebackToOtherServer(list);
	}

	// 写到另外一个区域
	static public void rebackToOtherServer(List<KeyMapEntity> list) {
		if (list == null || list.size() <= 0)
			return;
		try {
			int maxActive = 256;
			int maxIdle = 32;
			int minIdle = 1;
			int maxWait = 1000;
			String host = "112.124.56.63";
			int port = 5268;
			JedisPoolConfig config = JedisEx.newJedisPoolConfig(maxActive,
					maxIdle, minIdle, maxWait);
			JedisPool pool = JedisEx.getJedisPool(config, host, port);
			Jedis r = pool.getResource();
			Pipeline p = r.pipelined();
			for (KeyMapEntity item : list) {
				String key = item.getKey();
				if (key == null)
					continue;
				if ("".equals(key.trim()))
					continue;
				Map<String, String> value = item.getMapData();
				if (value == null || value.isEmpty())
					continue;
				p.hmset(key, value);
			}
			List<Object> v = p.syncAndReturnAll();
			pool.returnResource(r);
			pool.destroy();
			System.out.println(v);
		} catch (Exception e) {
			log.info(UtileTools.ex2s(e));
		}
	}

	// 从本地读取该用户下面的信息
	static public List<KeyMapEntity> getOriginByPcid(int pcid) {
		Player p = AssistUserPlayerJedis.getPlayerByPcid(pcid);
		if (p == null)
			return null;
		int ucid = p.getUcid();
		String key = PlayerJedis.getKeyPlayer(ucid, pcid);
		KeyMapEntity kmap = new KeyMapEntity(key, p.toBasicMap());
		List<KeyMapEntity> list = new ArrayList<KeyMapEntity>();
		list.add(kmap);
		List<KeyMapEntity> builds = getBuilds(p);
		List<KeyMapEntity> obsts = getObsts(p);
		List<KeyMapEntity> teches = getTechs(p);
		List<KeyMapEntity> heros = getHeros(p);

		if (builds != null && !builds.isEmpty())
			list.addAll(builds);

		if (obsts != null && !obsts.isEmpty())
			list.addAll(obsts);

		if (teches != null && !teches.isEmpty())
			list.addAll(teches);

		if (heros != null && !heros.isEmpty())
			list.addAll(heros);

		return list;
	}

	static private List<KeyMapEntity> getBuilds(Player p) {
		if (p == null)
			return null;
		int pcid = p.getPcid();
		String builds = p.getBuilds();
		List<Player_buildings> origin = BuildJedis.getList(pcid, builds);
		if (origin == null || origin.size() <= 0)
			return null;
		List<BeanSupport> list = new ArrayList<BeanSupport>();
		list.addAll(origin);
		return BuildJedis.getListDateToKMap(list);
	}

	static private List<KeyMapEntity> getObsts(Player p) {
		if (p == null)
			return null;
		int pcid = p.getPcid();
		String builds = p.getObstes();
		List<Player_build_obst> origin = ObstJedis.getList(pcid, builds);
		if (origin == null || origin.size() <= 0)
			return null;
		List<BeanSupport> list = new ArrayList<BeanSupport>();
		list.addAll(origin);
		return ObstJedis.getListDateToKMap(list);
	}

	static private List<KeyMapEntity> getTechs(Player p) {
		if (p == null)
			return null;
		int pcid = p.getPcid();
		String teches = p.getTeches();
		List<Player_tech> origin = TechJedis.getList(pcid, teches);
		if (origin == null || origin.size() <= 0)
			return null;
		List<BeanSupport> list = new ArrayList<BeanSupport>();
		list.addAll(origin);
		return TechJedis.getListDateToKMap(list);
	}

	static private List<KeyMapEntity> getHeros(Player p) {
		if (p == null)
			return null;
		int pcid = p.getPcid();
		String heroes = p.getHeroes();
		List<Player_hero> origin = HeroJedis.getList(pcid, heroes);
		if (origin == null || origin.size() <= 0)
			return null;
		List<BeanSupport> list = new ArrayList<BeanSupport>();
		list.addAll(origin);
		return HeroJedis.getListDateToKMap(list);
	}

	// //////////// 现在模式 ////////////
	static public void rebackByPcid4Cur(int pcid,int newucid,int newpcid) {
		try {
			Player pl = AssistUserPlayerJedis.getPlayerByPcid(pcid);
			if (pl == null)
				return;
			pl.setUcid(newucid);
			pl.setPname(pl.getPname()+"@叶子");
			pl.setPcid(newpcid);
			
			String key = "";
			key = PlayerJedis.getKeyPlayer(newucid, newpcid);
			
			KeyMapEntity kmap = new KeyMapEntity(key, pl.toBasicMap());
			Map<String, String> value = kmap.getMapData();

			String jedisKey = _GameKeys.fmtPinfoPcid(pcid);
			Map<String, String> mapPinfo = JedisHash.getMap(jedisKey);
			Map<String, String> mapNew = new HashMap<String, String>();
			for(Entry<String, String> entry : mapPinfo.entrySet()){
				String km = entry.getKey();
				String kv = entry.getValue();
				kv = kv.replaceAll(""+pcid, ""+newpcid);
				mapNew.put(km, kv);
			}

			int maxActive = 256;
			int maxIdle = 32;
			int minIdle = 1;
			int maxWait = 1000;
			String host = "112.124.56.63";
			int port = 5268;
			JedisPoolConfig config = JedisEx.newJedisPoolConfig(maxActive,
					maxIdle, minIdle, maxWait);
			JedisPool pool = JedisEx.getJedisPool(config, host, port);
			Jedis r = pool.getResource();
			Pipeline p = r.pipelined();

			
			jedisKey = _GameKeys.fmtPinfoPcid(newpcid);
			
			p.hmset(key, value);
			p.hmset(jedisKey, mapNew);

			List<Object> v = p.syncAndReturnAll();
			pool.returnResource(r);
			pool.destroy();
			System.out.println(v);
		} catch (Exception e) {
			log.info(UtileTools.ex2s(e));
		}
	}
}
