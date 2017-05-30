package com.sea.cache.jedis.origin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.SortingParams;

import com.sea.content.AppContext;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class JedisPipeline extends JedisOrigin implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(JedisPipeline.class);

	public static void setValByKeyVTime(String key, String val, int vtSeconds) {
		if (key == null || val == null)
			return;
		if ("".equals(key.trim()) || "".equals(val.trim()))
			return;
		if (vtSeconds <= 0) {
			setValByKey(key, val);
		} else {
			Jedis jedis = null;
			try {
				jedis = AppContext.getJedis();
				if (jedis == null)
					return;
				Pipeline p = jedis.pipelined();
				p.setex(key, vtSeconds, val);
				p.syncAndReturnAll();
			} catch (Exception e) {
				// log.info(UtileTools.ex2s(e));
			} finally {
				AppContext.returnJedis(jedis);
			}
		}
	}

	public static void delKeysByList(List<String> keys) {
		if (keys == null || keys.size() <= 0)
			return;
		List<String> origin = new ArrayList<String>();
		for (String key : keys) {
			if (key == null || "".equals(key.trim()))
				continue;
			origin.add(key);
		}

		String[] str = {};
		str = origin.toArray(str);

		clearList(keys);
		clearList(origin);

		delKeys(str);
	}

	public static void delKeys(String... keys) {
		if (keys == null || keys.length <= 0)
			return;
		Jedis jedis = null;
		try {
			jedis = AppContext.getJedis();
			if (jedis == null)
				return;
			Pipeline p = jedis.pipelined();
			p.del(keys);
			p.syncAndReturnAll();
		} catch (Exception e) {
			// log.error(UtileTools.ex2s(e));
		} finally {
			AppContext.returnJedis(jedis);
			keys = null;
		}
	}

	// 取得key val 类型的list
	public static List<String> getListValsByPattern(String pattern) {
		List<String> r = null;
		List<String> keys = getListKeysByPattern(pattern);
		r = getListValsByKeys(keys);
		return r;
	}

	// 取得key val 类型的list
	public static List<String> getListValsByKeys(List<String> keys) {
		List<String> r = null;
		if (keys == null || keys.size() <= 0)
			return r;
		r = new ArrayList<String>();
		Jedis jedis = null;
		try {
			jedis = AppContext.getJedis();
			if (jedis == null)
				return null;
			Pipeline p = jedis.pipelined();
			for (String key : keys) {
				p.get(key);
			}
			List<Object> listBackVals = p.syncAndReturnAll();
			for (Object val : listBackVals) {
				if (val == null)
					continue;
				String e = val.toString();
				r.add(e);
			}
			clearList(listBackVals);
		} catch (Exception e) {
			// log.info(UtileTools.ex2s(e));
		} finally {
			AppContext.returnJedis(jedis);
		}
		return r;
	}

	// 取得key val 类型的list
	public static List<String> getListValsByKeys(String... keys) {
		List<String> r = null;
		if (keys == null || keys.length <= 0)
			return r;
		r = new ArrayList<String>();
		Jedis jedis = null;
		try {
			jedis = AppContext.getJedis();
			if (jedis == null)
				return null;
			Pipeline p = jedis.pipelined();
			for (String key : keys) {
				p.get(key);
			}
			List<Object> listBackVals = p.syncAndReturnAll();
			for (Object val : listBackVals) {
				if (val == null)
					continue;
				String e = val.toString();
				r.add(e);
			}
			clearList(listBackVals);
		} catch (Exception e) {
			// log.info(UtileTools.ex2s(e));
		} finally {
			AppContext.returnJedis(jedis);
		}
		return r;
	}

	// 取得 key 通过p,v
	public static String getKeyByValPattern(String pattern, String val) {
		String r = "";
		if (val == null || "".equals(val.trim()))
			return r;
		List<String> keys = getListKeysByPattern(pattern);
		if (keys == null || keys.size() <= 0)
			return r;
		for (String key : keys) {
			String kv = getValByKey(key);
			if (val.equals(kv)) {
				r = key;
				break;
			}
		}

		clearList(keys);

		return r;
	}

	// 保存key val 类型的list
	public static void setKeyValList(List<KeyValEntity> list) {
		if (list == null || list.size() <= 0)
			return;
		Jedis jedis = null;
		try {
			jedis = AppContext.getJedis();
			if (jedis == null)
				return;
			Pipeline p = jedis.pipelined();
			for (KeyValEntity item : list) {
				String key = item.getKey();
				String value = item.getVal();
				if (key == null || value == null)
					continue;
				if ("".equals(key.trim()) || "".equals(value.trim()))
					continue;
				String field = item.getField();
				if (field == null || "".equals(field.trim())) {
					p.set(key, value);
				} else {
					p.hset(key, field, value);
				}

			}
			p.syncAndReturnAll();
		} catch (Exception e) {
			// log.info(UtileTools.ex2s(e));
		} finally {
			AppContext.returnJedis(jedis);
		}
	}

	// 保存key val
	public static void setValByKey(String key, String val) {
		if (key == null || val == null)
			return;
		if ("".equals(key.trim()) || "".equals(val.trim()))
			return;
		Jedis jedis = null;
		try {
			jedis = AppContext.getJedis();
			if (jedis == null)
				return;
			Pipeline p = jedis.pipelined();
			p.set(key, val);
			// p.persist(key);
			p.syncAndReturnAll();
		} catch (Exception e) {
			// log.info(UtileTools.ex2s(e));
		} finally {
			AppContext.returnJedis(jedis);
		}
	}

	// 删除 pattern 的所有key
	public static void delAllByPattern(String pattern) {
		List<String> keys = getListKeysByPattern(pattern);
		if (keys == null || keys.size() <= 0)
			return;

		Jedis jedis = null;
		try {
			jedis = AppContext.getJedis();
			if (jedis == null)
				return;
			Pipeline p = jedis.pipelined();
			for (String key : keys) {
				p.del(key);
			}
			p.syncAndReturnAll();
		} catch (Exception e) {
			// log.info(UtileTools.ex2s(e));
		} finally {
			AppContext.returnJedis(jedis);
			clearList(keys);
		}
	}

	// 通过筛选取得数据
	public static List<String> getValsHashByPattern(String listKey,
			String patterns) {
		List<String> r = null;
		if (listKey == null || patterns == null)
			return r;

		if ("".equals(listKey.trim()) || "".equals(patterns.trim()))
			return r;
		Jedis jedis = null;
		try {
			jedis = AppContext.getJedis();
			if (jedis == null)
				return null;
			Pipeline p = jedis.pipelined();
			SortingParams sortingParameters = new SortingParams();
			sortingParameters.get(patterns);
			p.sort(listKey, sortingParameters);
			List<Object> listBackVals = p.syncAndReturnAll();
			r = new ArrayList<String>();
			for (Object val : listBackVals) {
				String e = val.toString();
				List list = UtileTools.strToList(e);
				for (Object li : list) {
					if (li == null)
						continue;
					Map map_ = (Map) li;
					String v = UtileTools.mapToStr(map_);
					r.add(v);
				}
			}
			clearList(listBackVals);
		} catch (Exception e) {
			// log.info(UtileTools.ex2s(e));
		} finally {
			AppContext.returnJedis(jedis);
		}
		return r;
	}
}
