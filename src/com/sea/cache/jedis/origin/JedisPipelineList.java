package com.sea.cache.jedis.origin;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import com.sea.content.AppContext;
// import com.sea.tools.UtileTools;

public class JedisPipelineList extends JedisPipeline implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(JedisPipelineList.class);

	public static void delValsInList(String key, String... vals) {
		if (key == null || vals == null)
			return;
		if ("".equals(key.trim()) || vals.length <= 0)
			return;
		Jedis jedis = null;
		try {
			jedis = AppContext.getJedis();
			if(jedis == null)
				return;
			long len = jedis.llen(key);

			Pipeline p = jedis.pipelined();
			for (String v : vals) {
				if (v == null || "".equals(v.trim()))
					continue;
				p.lrem(key, len, v);
			}
			p.syncAndReturnAll();
		} catch (Exception e) {
			// log.info(UtileTools.ex2s(e));
		} finally {
			AppContext.returnJedis(jedis);
		}
	}

	public static void setValsInList(String key, String... vals) {
		if (key == null || vals == null)
			return;
		if ("".equals(key.trim()) || vals.length <= 0)
			return;
		Jedis jedis = null;
		try {
			jedis = AppContext.getJedis();
			if(jedis == null)
				return;
			Pipeline p = jedis.pipelined();
			for (String v : vals) {
				if (v == null || "".equals(v.trim()))
					continue;
				// rpush 从尾部压入一个元素 先进先出
				// lpush 从头部压入一个元素 先进后出
				p.rpush(key, v);
			}
			p.syncAndReturnAll();
		} catch (Exception e) {
			// log.info(UtileTools.ex2s(e));
		} finally {
			AppContext.returnJedis(jedis);
		}
	}

	public static void setValsInList(String key, List<String> vals) {
		if (key == null || vals == null)
			return;
		if ("".equals(key.trim()) || vals.isEmpty())
			return;
		Jedis jedis = null;
		try {
			jedis = AppContext.getJedis();
			if(jedis == null)
				return;
			Pipeline p = jedis.pipelined();
			for (String v : vals) {
				if (v == null || "".equals(v.trim()))
					continue;
				// rpush 从尾部压入一个元素 先进先出
				// lpush 从头部压入一个元素 先进后出
				p.rpush(key, v);
			}
			p.syncAndReturnAll();
		} catch (Exception e) {
			// log.info(UtileTools.ex2s(e));
		} finally {
			AppContext.returnJedis(jedis);
		}
	}
}
