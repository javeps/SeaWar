package com.sea.cache.jedis.origin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;

import com.sea.content.AppContext;
// import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class JedisOrigin implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(JedisOrigin.class);

	public static void clearList(List origin) {
		if (origin == null)
			return;
		origin.clear();
		origin = null;
	}

	public static void clearMap(Map origin) {
		if (origin == null)
			return;
		origin.clear();
		origin = null;
	}

	// 清空数据DB
	public static void clearJedisDB() {
		Jedis res = null;
		try {
			res = AppContext.getJedis();
			if(res == null)
				return;
			res.flushDB();
		} catch (Exception e) {
			// log.error(UtileTools.ex2s(e));
		} finally {
			AppContext.returnJedis(res);
		}

	}

	// 清空所有数据
	public static void clearJedisAll() {
		Jedis res = null;
		try {
			res = AppContext.getJedis();
			if(res == null)
				return;
			res.flushAll();
		} catch (Exception e) {
			// log.error(UtileTools.ex2s(e));
		} finally {
			AppContext.returnJedis(res);
		}
	}

	// 删除Key
	public static void deleteKey(String key) {
		Jedis res = null;
		try {
			res = AppContext.getJedis();
			if(res == null)
				return;
			res.del(key);
		} catch (Exception e) {
			// log.error(UtileTools.ex2s(e));
		} finally {
			AppContext.returnJedis(res);
		}
	}

	// 设置值
	public static void setValBykey(String key, String value) {
		Jedis res = null;
		try {
			res = AppContext.getJedis();
			if(res == null)
				return;
			res.set(key, value);
		} catch (Exception e) {
			// log.error(UtileTools.ex2s(e));
		} finally {
			AppContext.returnJedis(res);
		}
	}

	// 取值
	public static String getValByKey(String key) {
		Jedis res = null;
		try {
			res = AppContext.getJedis();
			if(res == null)
				return "";
			String r = res.get(key);
			return r;
		} catch (Exception e) {
			// log.error(UtileTools.ex2s(e));
			return "";
		} finally {
			AppContext.returnJedis(res);
		}

	}

	// 判断是否保护key
	public static boolean isHasKey(String key) {
		Jedis res = null;
		try {
			res = AppContext.getJedis();
			if (res == null)
				return true;
			boolean isHas = res.exists(key);
			return isHas;
		} catch (Exception e) {
			// log.error(UtileTools.ex2s(e));
			return true;
		} finally {
			AppContext.returnJedis(res);
		}

	}

	// 重命名key
	public static void renameKey(String old, String newKey) {
		Jedis res = null;
		try {
			res = AppContext.getJedis();
			if(res == null)
				return;
			res.renamenx(old, newKey);
		} catch (Exception e) {
			// log.error(UtileTools.ex2s(e));
		} finally {
			AppContext.returnJedis(res);
		}
	}

	// 设置key的有效时间
	public static void setKeyValidTime(String key, int valSecond) {
		boolean isHas = isHasKey(key);
		if (isHas) {
			Jedis res = null;
			try {
				res = AppContext.getJedis();
				if(res == null)
					return;
				res.expire(key, valSecond);
			} catch (Exception e) {
				// log.error(UtileTools.ex2s(e));
			} finally {
				AppContext.returnJedis(res);
			}
		}
	}

	// 取得key的有效时间
	public static long getKeyValidTime(String key) {
		long r = 0;
		Jedis res = null;
		try {
			res = AppContext.getJedis();
			if(res == null)
				return 0;
			r = res.ttl(key);
			return r;
		} catch (Exception e) {
			// log.error(UtileTools.ex2s(e));
			return 0;
		} finally {
			AppContext.returnJedis(res);
		}

	}

	// 取消key的有效时间
	public static void cancelValidTime(String key) {
		Jedis res = null;
		try {
			res = AppContext.getJedis();
			if(res == null)
				return;
			res.persist(key);
		} catch (Exception e) {
			// log.error(UtileTools.ex2s(e));
		} finally {
			AppContext.returnJedis(res);
		}
	}

	// 取得key set
	public static Set<String> getSetKeysByPattern(String pattern) {
		if (pattern == null || "".equals(pattern.trim()))
			return null;
		Jedis res = null;
		try {
			res = AppContext.getJedis();
			if (res == null)
				return null;
			Set<String> r = res.keys(pattern);
			return r;
		} catch (Exception e) {
			// log.error(UtileTools.ex2s(e));
			return null;
		} finally {
			AppContext.returnJedis(res);
		}

	}

	// 取得List
	public static List<String> changeSetToList(Set<String> sets) {
		List<String> r = new ArrayList<String>();
		if (sets != null && !sets.isEmpty()) {
			r.addAll(sets);
		}
		return r;
	}

	// 取得 满足条件的keys list
	public static List<String> getListKeysByPattern(String pattern) {
		Set<String> sets = getSetKeysByPattern(pattern);
		List<String> r = changeSetToList(sets);
		return r;
	}

	// 查看所有key的有效时间
	public static void seeAllKeyTime() {
		Jedis res = null;
		try {
			res = AppContext.getJedis();
			if(res == null)
				return;
			Set<String> keys = res.keys("*");
			List<String> keylist = changeSetToList(keys);
			for (String string : keylist) {
				System.out.println(string + "有效时间:" + getKeyValidTime(string));
			}
		} catch (Exception e) {
			// log.error(UtileTools.ex2s(e));
		} finally {
			AppContext.returnJedis(res);
		}

	}
}
