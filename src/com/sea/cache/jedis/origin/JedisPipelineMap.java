package com.sea.cache.jedis.origin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import com.sea.content.AppContext;

//import com.sea.tools.UtileTools;

@SuppressWarnings({ "unchecked" })
public class JedisPipelineMap extends JedisPipeline implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(JedisPipelineMap.class);

	public static void setKeyMapEntity(KeyMapEntity item) {
		if (item == null)
			return;
		Jedis jedis = null;
		try {
			jedis = AppContext.getJedis();
			if (jedis == null)
				return;
			Pipeline p = jedis.pipelined();
			String key = item.getKey();
			if (key == null)
				return;
			if ("".equals(key.trim()))
				return;
			Map<String, String> value = item.getMapData();
			if (value == null || value.isEmpty())
				return;
			p.hmset(key, value);
			p.syncAndReturnAll();
		} catch (Exception e) {
			// log.info(UtileTools.ex2s(e));
		} finally {
			AppContext.returnJedis(jedis);
		}
	}

	public static void setKeyMapList(List<KeyMapEntity> list) {
		if (list == null || list.size() <= 0)
			return;
		Jedis jedis = null;
		try {
			jedis = AppContext.getJedis();
			if (jedis == null)
				return;
			Pipeline p = jedis.pipelined();
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
			p.syncAndReturnAll();
		} catch (Exception e) {
			// log.info(UtileTools.ex2s(e));
		} finally {
			AppContext.returnJedis(jedis);
			clearList(list);
		}
	}

	public static List<Map<String, String>> getListMap(String... keys) {
		if (keys == null || keys.length <= 0)
			return null;
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		Jedis jedis = null;
		try {
			jedis = AppContext.getJedis();
			if (jedis == null)
				return null;
			Pipeline p = jedis.pipelined();
			for (String key : keys) {
				p.hgetAll(key);
			}
			List<Object> back = p.syncAndReturnAll();
			for (Object object : back) {
				Map<String, String> map = (Map<String, String>) object;
				if (map == null || map.isEmpty())
					continue;
				result.add(map);
			}
		} catch (Exception e) {
			result = null;
			// log.error(UtileTools.ex2s(e));
		} finally {
			AppContext.returnJedis(jedis);
		}
		return result;
	}

	public static List<Map<String, String>> getListMap(List<String> keys) {
		if (keys == null || keys.isEmpty())
			return null;
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		Jedis jedis = null;
		try {
			jedis = AppContext.getJedis();
			if (jedis == null)
				return null;
			Pipeline p = jedis.pipelined();
			for (String key : keys) {
				p.hgetAll(key);
			}
			List<Object> back = p.syncAndReturnAll();
			for (Object object : back) {
				Map<String, String> map = (Map<String, String>) object;
				if (map == null || map.isEmpty())
					continue;
				result.add(map);
			}
		} catch (Exception e) {
			result = null;
			// log.error(UtileTools.ex2s(e));
		} finally {
			AppContext.returnJedis(jedis);
		}
		return result;
	}

	public static Map<String, String> getMap(String key) {
		if (key == null || "".equals(key.trim()))
			return null;
		Map<String, String> result = null;
		Jedis jedis = null;
		try {
			jedis = AppContext.getJedis();
			if (jedis == null)
				return null;
			Pipeline p = jedis.pipelined();
			p.hgetAll(key);
			List<Object> back = p.syncAndReturnAll();
			Object obj = back.get(0);
			result = (Map<String, String>) obj;
		} catch (Exception e) {
			result = null;
			// log.error(UtileTools.ex2s(e));
		} finally {
			AppContext.returnJedis(jedis);
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	public static void setMapObj(String key, Map map) {
		if (key == null || "".equals(key.trim()))
			return;
		if (map == null)
			return;

		Map<String, String> mapStr = new HashMap<String, String>();
		for (Object mapKey : map.keySet()) {
			Object val = map.get(mapKey);
			if (val == null)
				continue;
			mapStr.put(mapKey.toString(), val.toString());
		}
		setMapStr(key, mapStr);
	}

	public static void setMapStr(String key, Map<String, String> map) {
		if (key == null || "".equals(key.trim()))
			return;
		Jedis jedis = null;
		try {
			jedis = AppContext.getJedis();
			if (jedis == null)
				return;
			Pipeline p = jedis.pipelined();
			p.hmset(key, map);
			p.syncAndReturnAll();
		} catch (Exception e) {
			// log.error(UtileTools.ex2s(e));
		} finally {
			AppContext.returnJedis(jedis);
		}
	}

	public static void setKFV(String key, String field, String val) {
		if (key == null || val == null || field == null)
			return;
		if ("".equals(key.trim()) || "".equals(val.trim())
				|| "".equals(field.trim()))
			return;
		Jedis jedis = null;
		try {
			jedis = AppContext.getJedis();
			if (jedis == null)
				return;
			Pipeline p = jedis.pipelined();
			p.hset(key, field, val);
			p.syncAndReturnAll();
		} catch (Exception e) {
			// log.info(UtileTools.ex2s(e));
		} finally {
			AppContext.returnJedis(jedis);
		}
	}

	/**
	 * field 对应map的key, key 对应map的 val
	 * 
	 * @param map
	 */
	public static void delByMapFK(Map<String, String> map) {
		if (map == null || map.size() <= 0)
			return;
		Jedis jedis = null;
		try {
			jedis = AppContext.getJedis();
			if (jedis == null)
				return;
			Pipeline p = jedis.pipelined();
			for (Entry<String, String> e : map.entrySet()) {
				String field = e.getKey();
				String key = e.getValue();
				p.hdel(key, field);
			}
			p.syncAndReturnAll();
		} catch (Exception e) {
			// log.info(UtileTools.ex2s(e));
		} finally {
			AppContext.returnJedis(jedis);
		}

		clearMap(map);
	}

	/**
	 * key 对应map的key, field 对应map的 val
	 * 
	 * @param map
	 */
	public static void delByMapKF(Map<String, String> map) {
		if (map == null || map.size() <= 0)
			return;
		Jedis jedis = null;
		try {
			jedis = AppContext.getJedis();
			if (jedis == null)
				return;
			Pipeline p = jedis.pipelined();
			for (Entry<String, String> e : map.entrySet()) {
				String key = e.getKey();
				String field = e.getValue();
				p.hdel(key, field);
			}
			p.syncAndReturnAll();
		} catch (Exception e) {
			// log.info(UtileTools.ex2s(e));
		} finally {
			AppContext.returnJedis(jedis);
		}

		clearMap(map);
	}

	public static void delByKFields(String key, String... fields) {
		if (key == null || fields == null)
			return;
		if ("".equals(key.trim()) || fields.length <= 0)
			return;
		Jedis jedis = null;
		try {
			jedis = AppContext.getJedis();
			if (jedis == null)
				return;
			Pipeline p = jedis.pipelined();
			p.hdel(key, fields);
			p.syncAndReturnAll();
		} catch (Exception e) {
			// log.info(UtileTools.ex2s(e));
		} finally {
			AppContext.returnJedis(jedis);
		}
	}

	static public void delByKFields(String key, List<String> fields) {
		if (key == null || fields == null)
			return;
		if ("".equals(key.trim()) || fields.isEmpty())
			return;
		String[] dfields = {};
		dfields = fields.toArray(dfields);
		delByKFields(key, dfields);
	}

	public static String getValByKFPipeline(String key, String field) {
		if (key == null || field == null)
			return "";
		if ("".equals(key.trim()) || "".equals(field.trim()))
			return "";
		String result = "";
		Jedis jedis = null;
		try {
			jedis = AppContext.getJedis();
			if (jedis == null)
				return "";
			Pipeline p = jedis.pipelined();
			p.hget(key, field);
			List<Object> listBack = p.syncAndReturnAll();
			if (listBack != null && !listBack.isEmpty()) {
				Object o = listBack.get(0);
				if (o != null)
					result = o.toString();
			}
			clearList(listBack);
		} catch (Exception e) {
			// log.info(UtileTools.ex2s(e));
		} finally {
			AppContext.returnJedis(jedis);
		}
		return result;
	}

	public static String getValByKF(String key, String field) {
		if (key == null || field == null)
			return "";
		if ("".equals(key.trim()) || "".equals(field.trim()))
			return "";
		String result = "";
		Jedis jedis = null;
		try {
			jedis = AppContext.getJedis();
			if (jedis == null)
				return "";
			result = jedis.hget(key, field);
		} catch (Exception e) {
			// log.info(UtileTools.ex2s(e));
		} finally {
			AppContext.returnJedis(jedis);
		}
		return result;
	}
}
