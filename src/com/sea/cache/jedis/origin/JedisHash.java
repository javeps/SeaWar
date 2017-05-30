package com.sea.cache.jedis.origin;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;

import com.sea.content.AppContext;

// import com.sea.tools.UtileTools;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class JedisHash extends JedisPipelineMap implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(JedisHash.class);

	// 取得长度
	public static long getLenHash(String key) {
		Jedis res = null;
		try {
			res = AppContext.getJedis();
			if (res == null)
				return 0;
			long r = res.hlen(key);
			return r;
		} catch (Exception e) {
			// log.error(UtileTools.ex2s(e));
			return 0;
		} finally {
			AppContext.returnJedis(res);
		}

	}

	// 是否拥有
	public static boolean isHasHash(String key, String field) {
		Jedis res = null;
		try {
			res = AppContext.getJedis();
			if (res == null)
				return true;
			boolean r = res.hexists(key, field);
			return r;
		} catch (Exception e) {
			// log.error(UtileTools.ex2s(e));
			return true;
		} finally {
			AppContext.returnJedis(res);
		}

	}

	// 取得值
	public static String getValHashPip(String key, String field) {
		return getValByKFPipeline(key, field);
	}

	// 取得值
	public static String getValHash(String key, String field) {
		return getValByKF(key, field);
	}

	// 设置值
	public static void setValHash(String key, String field, String value) {
		setKFV(key, field, value);
	}

	// 取得keys;
	public static List<String> getListHashKeys(String key) {
		Jedis res = null;
		try {
			res = AppContext.getJedis();
			if (res == null)
				return null;
			Set<String> keys = res.hkeys(key);
			List<String> r = changeSetToList(keys);
			return r;
		} catch (Exception e) {
			// log.error(UtileTools.ex2s(e));
			return null;
		} finally {
			AppContext.returnJedis(res);
		}

	}

	// 取得values by fields
	public static List<String> getListHashVals(String key, String... fields) {
		Jedis res = null;
		try {
			res = AppContext.getJedis();
			if (res == null)
				return null;
			List<String> vals = res.hmget(key, fields);
			return vals;
		} catch (Exception e) {
			// log.error(UtileTools.ex2s(e));
			return null;
		} finally {
			AppContext.returnJedis(res);
		}

	}

	// 取得values by key
	public static List<String> getListHashVals(String key) {
		Jedis res = null;
		try {
			res = AppContext.getJedis();
			if (res == null)
				return null;
			List<String> vals = res.hvals(key);
			return vals;
		} catch (Exception e) {
			// log.error(UtileTools.ex2s(e));
			return null;
		} finally {
			AppContext.returnJedis(res);
		}

	}

	// 取得values by key
	public static Map getMapListVals(String key) {
		Jedis res = null;
		Map map = new HashMap();
		map.put("isNull", true);
		try {
			res = AppContext.getJedis();
			if (res == null)
				return map;
			List<String> vals = res.hvals(key);
			map.put("list", vals);
			map.put("isNull", false);
			return map;
		} catch (Exception e) {
			// log.error(UtileTools.ex2s(e));
			return map;
		} finally {
			AppContext.returnJedis(res);
		}

	}
}
