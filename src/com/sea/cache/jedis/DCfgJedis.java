package com.sea.cache.jedis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import com.bowlong.third.redis.JedisEx;
import com.bowlong.util.DateEx;
import com.bowlong.util.MapEx;
import com.sea.cache.jedis.game.PlayerJedis;
import com.sea.cache.jedis.origin.JedisHash;
import com.sea.cache.jedis.origin.KeyMapEntity;
import com.sea.content.AppContext;
import com.sea.db.bean.Player;
import com.sea.json.originData.DataConfig;
import com.sea.tools.UtileTools;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class DCfgJedis implements Serializable {

	static private final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(DCfgJedis.class);

	static public String KeyConfig = "k:config_data";

	static public final String M_K_Pay_C_H = "chnuuidHead";
	static public final String M_K_Pay_C_U = "chnuuid";
	static public final String M_K_Pay_S_H = "payuuidHead";
	static public final String M_K_Pay_S_U = "payuuid";
	static public final String M_K_Clan_H = "chancidHead";
	static public final String M_K_Clan_C = "clancid";
	static public final String M_K_R_T_MS = "rankTime";
	static public final String M_K_R_T_Str = "rankTimeFmt";
	static public final String M_K_PMcid = "pmcid";
	static public final String M_K_Pcid = "pcid";
	static public final String M_K_Ucid = "ucid";
	static public final String M_K_Hcid = "hcid";
	static public final String M_K_Chatcid = "chatcid";

	static private Map _map = null;

	public static Map getMapConfig() {
		if (_map == null || _map.isEmpty()) {
			_map = JedisHash.getMap(KeyConfig);
			if (_map == null || _map.isEmpty()) {
				_map = DataConfig.readMapData();
				setMapConfig(_map);
			}
		}
		return _map;
	}

	public static void delMapCfg() {
		String v = JedisHash.getValByKey(KeyConfig);
		if (v == null || "".equals(v.trim()))
			return;
		JedisHash.deleteKey(KeyConfig);
		Map map = UtileTools.strToMap(v);
		setMapConfig(map);
	}

	public static void setMapConfig(Map map) {
		if (map == null)
			return;
		JedisHash.setMapObj(KeyConfig, map);
	}

	public static void recordInFile() {
		Map map = getMapConfig();
		DataConfig.writeMapData(map);
	}

	// ===================
	public static int getIntValInMap(Map map, String key) {
		return MapEx.getInt(map, key);
	}

	public static String getStrValInMap(Map map, String key) {
		return MapEx.getString(map, key);
	}

	public static int getChatCID() {
		Map map = getMapConfig();
		String key = "chatcid";
		int value = MapEx.getInt(map, key);
		value++;
		map.put(key, value);
		setMapConfig(map);
		return value;
	}

	public static String getCcidHead() {
		Map map = getMapConfig();
		String keyHead = "clancidHead";
		String vh = MapEx.getString(map, keyHead);
		return vh;
	}

	public static int getMailCID() {
		Map map = getMapConfig();
		String key = "pmcid";
		int value = MapEx.getInt(map, key);
		value++;
		map.put(key, value);
		setMapConfig(map);
		return value;
	}

	public static long getRankReward() {
		Map map = getMapConfig();
		String key = "rankTime";
		long value = MapEx.getLong(map, key);
		return value;
	}

	public static void resetRankWhenStart(int ev) {
		if (ev < 1)
			ev = 1;
		long r = getRankReward();
		long now_time = System.currentTimeMillis();
		long week_time = DateEx.TIME_WEEK;
		if (now_time >= r) {
			long diff = (now_time - r) / week_time + ev;
			changeRankReward((int) diff);
		}
	}

	public static void changeRankReward(int multiple) {
		if (multiple <= 1)
			multiple = 1;
		Map map = getMapConfig();
		String key = "rankTime";
		long value = MapEx.getLong(map, key);
		value += multiple * DateEx.TIME_WEEK;
		map.put(key, value);
		setMapConfig(map);
		setRankRewardTimeFmt(value);

		// 记录到data.json数据中去
		// DataConfig.changeRankReward(value);
		recordInFile();
	}

	public static void setRankRewardTimeFmt(long rankVal) {
		Map map = getMapConfig();
		Date d = new Date(rankVal);
		String key = "rankTimeFmt";
		String value = DateEx.formatString(d, DateEx.fmt_yyyy_MM_dd_HH_mm);
		map.put(key, value);
		setMapConfig(map);
	}

	public static String getRankRewardTimeFmt() {
		Map map = getMapConfig();
		String key = "rankTimeFmt";
		String value = MapEx.getString(map, key);
		return value;
	}

	public static int getHeroCID() {
		Map map = getMapConfig();
		String key = "hcid";
		int value = MapEx.getInt(map, key);
		value++;
		map.put(key, value);
		setMapConfig(map);
		return value;
	}

	// 充值零时生成的标识
	public static String getPayUUIDHead() {
		Map map = getMapConfig();
		String key = "payuuidHead";
		String value = MapEx.getString(map, key);
		return value;
	}

	public static int getTmpPayUUID() {
		Map map = getMapConfig();
		String key = "payuuid";
		int value = MapEx.getInt(map, key);
		value++;
		map.put(key, value);
		setMapConfig(map);
		return value;
	}

	public static String getPayUUID(String val) {
		String head = getPayUUIDHead();

		int cur = getTmpPayUUID();
		val = UtileTools.getNStrByLen(10, cur);

		if (val == null || "".equals(val.trim()))
			val = DateEx.now(DateEx.fmt_yyyy_MM_dd_HH_mm_ss_sss);

		String r = head + val;

		return r;
	}

	// 充值标识
	public static String getTmpChannelUUID(String val) {
		Map map = getMapConfig();
		String keyHead = "chnuuidHead";
		String valHead = MapEx.getString(map, keyHead);

		String key = "chnuuid";
		int value = MapEx.getInt(map, key);
		value++;
		map.put(key, value);
		setMapConfig(map);

		val = value + "";

		if (val == null || "".equals(val.trim()))
			val = DateEx.now(DateEx.fmt_yyyy_MM_dd_HH_mm_ss_sss);

		return valHead + val;
	}

	// 是否为沙盒充值(ios pay)
	public static boolean isSandBox() {
		Map map = getMapConfig();
		String key = "iosSandBox";
		boolean r = MapEx.getBoolean(map, key);
		return r;
	}

	// ===== clear data
	public static void clearNetWork() {
		String host = "112.124.56.63";
		clearNetWork(host);
	}

	public static void clearNetWork(String host) {
		int maxActive = 2;
		int maxIdle = 2;
		int minIdle = 1;
		int maxWait = 3000;
		int port = AppContext.getJedisPort();
		JedisPoolConfig config = JedisEx.newJedisPoolConfig(maxActive, maxIdle,
				minIdle, maxWait);
		JedisPool pool = JedisEx.getJedisPool(config, host, port);
		Jedis r = pool.getResource();
		r.flushAll();
		pool.returnResource(r);
		pool.destroy();
	}

	public static void delKeysFromNetWork(String... keys) {
		int maxActive = 2;
		int maxIdle = 2;
		int minIdle = 1;
		int maxWait = 3000;
		String host = "112.124.56.63";
		int port = AppContext.getJedisPort();
		JedisPoolConfig config = JedisEx.newJedisPoolConfig(maxActive, maxIdle,
				minIdle, maxWait);
		JedisPool pool = JedisEx.getJedisPool(config, host, port);
		Jedis r = pool.getResource();
		Pipeline pipeline = r.pipelined();
		pipeline.del(keys);
		pipeline.syncAndReturnAll();
		pool.returnResource(r);
		pool.destroy();
	}

	public static void delKeysFromNetWorkByArray(String... patterns) {
		int maxActive = 2;
		int maxIdle = 2;
		int minIdle = 1;
		int maxWait = 3000;
		String host = "112.124.56.63";
		int port = AppContext.getJedisPort();
		port = 5268;
		JedisPoolConfig config = JedisEx.newJedisPoolConfig(maxActive, maxIdle,
				minIdle, maxWait);
		JedisPool pool = JedisEx.getJedisPool(config, host, port);
		Jedis r = pool.getResource();

		List<String> list = new ArrayList<String>();

		for (String pattern : patterns) {
			Set<String> set = r.keys(pattern);
			if (set != null) {
				list.addAll(set);
			}
		}

		if (!list.isEmpty()) {
			List<String> keylist = new ArrayList<String>();

			for (String string : list) {
				if (string != null && !"".equals(string.trim()))
					keylist.add(string);
			}

			String[] keys = {};
			keys = keylist.toArray(keys);
			Pipeline pipeline = r.pipelined();
			pipeline.del(keys);
			pipeline.syncAndReturnAll();

		}
		pool.returnResource(r);
		pool.destroy();
	}

	public static void delKeysFromNetWorkBy(String pattern) {
		int maxActive = 2;
		int maxIdle = 2;
		int minIdle = 1;
		int maxWait = 3000;
		String host = "112.124.56.63";
		int port = AppContext.getJedisPort();
		port = 5268;
		JedisPoolConfig config = JedisEx.newJedisPoolConfig(maxActive, maxIdle,
				minIdle, maxWait);
		JedisPool pool = JedisEx.getJedisPool(config, host, port);
		Jedis r = pool.getResource();
		Set<String> set = r.keys(pattern);
		if (set != null) {
			List<String> list = new ArrayList<String>();
			for (String string : set) {
				if (string != null && !"".equals(string.trim()))
					list.add(string);
			}
			String[] keys = {};
			keys = list.toArray(keys);
			Pipeline pipeline = r.pipelined();
			pipeline.del(keys);
			pipeline.syncAndReturnAll();

		}
		pool.returnResource(r);
		pool.destroy();
	}

	public static void getDCfgFromNewWork() {
		int maxActive = 2;
		int maxIdle = 2;
		int minIdle = 1;
		int maxWait = 3000;
		String host = "112.124.56.63";
		int port = AppContext.getJedisPort();
		JedisPoolConfig config = JedisEx.newJedisPoolConfig(maxActive, maxIdle,
				minIdle, maxWait);
		JedisPool pool = JedisEx.getJedisPool(config, host, port);
		Jedis r = pool.getResource();
		Pipeline pipeline = r.pipelined();
		pipeline.hgetAll(KeyConfig);
		List<Object> v = pipeline.syncAndReturnAll();
		pool.returnResource(r);
		pool.destroy();
		if (v == null || v.isEmpty())
			return;
		Object obj = v.get(0);
		if (obj == null)
			return;
		System.out.println(obj);
		DataConfig.writeMapData((Map) obj);
		// ReadWriteString.writeStr(DataConfig.Json_Path_Data, obj.toString());
	}

	public static void clearPlNpcs(String... keys) {
		int maxActive = 2;
		int maxIdle = 2;
		int minIdle = 1;
		int maxWait = 3000;
		String host = "112.124.56.63";
		int port = AppContext.getJedisPort();
		port = 5268;
		JedisPoolConfig config = JedisEx.newJedisPoolConfig(maxActive, maxIdle,
				minIdle, maxWait);
		JedisPool pool = JedisEx.getJedisPool(config, host, port);
		Jedis r = pool.getResource();

		if (keys != null && keys.length > 0) {
			List<String> keylist = new ArrayList<String>();

			for (String string : keys) {
				if (string != null && !"".equals(string.trim()))
					keylist.add(string);
			}

			for (String key : keylist) {
				Map<String, String> obj = r.hgetAll(key);
				if (obj != null) {
					Player pl = PlayerJedis.toObjByMap(obj);
					pl.setNpcs("");
					pl.setCur_npc(1);
					KeyMapEntity kmap = new KeyMapEntity(key, pl.toBasicMap());
					r.hmset(key, kmap.getMapData());
				}
			}
		}
		pool.returnResource(r);
		pool.destroy();
	}
}
