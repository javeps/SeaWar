package com.sea.content;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sea.tools.UtileTools;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@SuppressWarnings("all")
public class AppContext extends JSONContext {
	static Log log = LogFactory.getLog(AppContext.class);

	public static void loadAll() throws Exception {
		// //////////////////////////////////////////
		// Apps Init begin
		// Apps Init end
		// ////////////////////////
	}

	// //////////////////// //////////////////// ////////////////////
	// 系统数据库
	public static DataSource ds() {
		return dataSource().get("ds");
	}

	public static DataSource ds2() {
		return dataSourceDesign().get("design");
	}

	public static DataSource logDS() {
		return dataSource().get("log");
	}

	public static String NAME() {
		return getString(APP(), "NAME");
	}

	public static double VER() {
		return getDouble(APP(), "VER");
	}

	public static int WEB_PORT() {
		return getInt(APP(), "WEB_PORT");
	}

	public static int SHUTDOWNPORT() {
		return getInt(APP(), "SHUTDOWNPORT");
	}

	public static String NGINX() {
		return getString(APP(), "NGINX");
	}

	public static Vector CACHESERVER() {
		return (Vector) APP().get("CACHE");
	}

	public static int TCP_PORT() {
		return getInt(APP(), "TCP_PORT");
	}

	public static String GATE_SERVER() {
		return getString(APP(), "GATE_SERVER");
	}

	public static int GATE_PORT_WEB() {
		return getInt(APP(), "GATE_PORT_WEB");
	}

	public static int GATE_PORT_TCP() {
		return getInt(APP(), "GATE_PORT_TCP");
	}

	public static int SHUTDOWNPORT_GATE() {
		return getInt(APP(), "SHUTDOWNPORT_GATE");
	}

	public static int getJedisPort() {
		return getInt(RedisMap(), "port");
	}

	public static String GAME_WEB_HOST(int svcid) {
		List list = GAME_WEB_HOSTS();
		if (list == null || list.isEmpty())
			return "";
		try {
			String host = "";
			for (Object obj : list) {
				Map map = (Map) obj;
				host = getString(map, "" + svcid);
				if (host != null && !"".equals(host.trim()))
					break;

			}
			return host;
		} catch (Exception e) {
			return "";
		}
	}

	public static List GAME_WEB_HOSTS() {
		return getList(APP(), "GAME_WEB_HOSTS");
	}

	public static Jedis getJedis() {
		Jedis result = null;
		JedisPool pool = getJedisPool();
		int exceNum = 0;
		while (result == null) {
			try {
				exceNum++;
				result = pool.getResource();
			} catch (Exception e) {
				returnJedisBroken(pool, result);
				// log.error(UtileTools.ex2s(e));
			}
			if (exceNum >= 100) {
				// log.info("取得Jedis循环执行了:" + exceNum);
				exceNum = 0;
				break;
			}
		}
		return result;
	}

	public static void returnJedis(Jedis resource) {
		JedisPool pool = getJedisPool();
		if (resource != null) {
			pool.returnResource(resource);
		}
	}

	public static void returnJedisBroken(JedisPool pool, Jedis result) {
		try {
			if (pool == null)
				pool = getJedisPool();
			pool.returnBrokenResource(result);
		} catch (Exception e) {
		}
	}
}