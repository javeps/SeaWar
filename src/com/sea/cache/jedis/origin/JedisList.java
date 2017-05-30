package com.sea.cache.jedis.origin;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;

import com.sea.content.AppContext;
// import com.sea.tools.UtileTools;

public class JedisList extends JedisPipelineList implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(JedisList.class);

	// 取得长度
	public static long getListLen(String key) {
		Jedis res = null;
		try {
			res = AppContext.getJedis();
			if(res == null)
				return 0;
			long r = res.llen(key);
			return r;
		} catch (Exception e) {
			// log.error(UtileTools.ex2s(e));
			return 0;
		} finally {
			AppContext.returnJedis(res);
		}

	}

	// 取得第一个对象的位置index
	public static long getListFirstIndex(String key, String val) {
		long len = getListLen(key);
		List<String> list = getListBy(key, 0, len);
		long r = -1;
		if (list != null && list.size() > 0) {
			r = list.indexOf(val);
		}
		return r;
	}

	// 删除count个值为delValue
	public static void deleteListVal(String key, long count, String delValue) {
		Jedis res = null;
		try {
			res = AppContext.getJedis();
			if(res == null)
				return;
			res.lrem(key, count, delValue);
		} catch (Exception e) {
			// log.error(UtileTools.ex2s(e));
		} finally {
			AppContext.returnJedis(res);
		}
	}

	// 删除一个val
	public static void deleteListOneVal(String key, String delValue) {
		deleteListVal(key, 1, delValue);
	}

	// 删除所有的val对象
	public static void deleteListAllVal(String key, String delValue) {
		long len = getListLen(key);
		if (len > 0) {
			deleteListVal(key, len, delValue);
		}
	}

	// 保留 start(包含),end(包含)之间的值
	public static void retainList(String key, long start, long end) {
		Jedis res = null;
		try {
			res = AppContext.getJedis();
			if(res == null)
				return;
			res.ltrim(key, start, end);
		} catch (Exception e) {
			// log.error(UtileTools.ex2s(e));
		} finally {
			AppContext.returnJedis(res);
		}
	}

	// 重新设置val
	public static void resetOneValInList(String key, long index, String val) {
		Jedis res = null;
		try {
			res = AppContext.getJedis();
			if(res == null)
				return;
			res.lset(key, index, val);
		} catch (Exception e) {
			// log.error(UtileTools.ex2s(e));
		} finally {
			AppContext.returnJedis(res);
		}
	}

	// 取得一个值
	public static String getOneValInList(String key, long index) {
		Jedis res = null;
		try {
			res = AppContext.getJedis();
			if(res == null)
				return "";
			String r = res.lindex(key, index);
			return r;
		} catch (Exception e) {
			// log.error(UtileTools.ex2s(e));
			return "";
		} finally {
			AppContext.returnJedis(res);
		}

	}

	// 设置值
	public static void setJedisInList(String key, String itemVal) {
		setValsInList(key, itemVal);
	}

	// 设置一个值
	public static void setJedisInList(String key, String oldVal, String newVal) {
		long index = getListFirstIndex(key, oldVal);
		if (index > -1) {
			resetOneValInList(key, index, newVal);
		} else {
			setJedisInList(key, newVal);
		}
	}

	// 取得值列表，包含start,包含end
	public static List<String> getListBy(String key, long start, long end) {
		Jedis res = null;
		try {
			res = AppContext.getJedis();
			if(res == null)
				return null;
			List<String> r = res.lrange(key, start, end);
			return r;
		} catch (Exception e) {
			// log.error(UtileTools.ex2s(e));
			return null;
		} finally {
			AppContext.returnJedis(res);
		}

	}

	// 取得所有值列表
	public static List<String> getListAll(String key) {
		long len = getListLen(key);
		if (len <= 0)
			return null;
		List<String> r = getListBy(key, 0, len);
		return r;
	}

	// 取得所有值列表
	public static List<String> getListAllBy(String key) {
		List<String> r = getListBy(key, 0, -1);
		return r;
	}

	// 取得值列表，通过排序
	public static List<String> getListBySort(String key, SortingParams so) {
		Jedis res = null;
		try {
			res = AppContext.getJedis();
			if(res == null)
				return null;
			List<String> r = res.sort(key, so);
			return r;
		} catch (Exception e) {
			// log.error(UtileTools.ex2s(e));
			return null;
		} finally {
			AppContext.returnJedis(res);
		}

	}

	// 取得值列表，通过参数，并且Asc
	public static List<String> getListBySortAsc(String key) {
		SortingParams so = new SortingParams();
		so.asc();
		List<String> r = getListBySort(key, so);
		return r;
	}

	// 取得值列表，通过参数，并且Desc
	public static List<String> getListBySortDesc(String key) {
		SortingParams so = new SortingParams();
		so.desc();
		List<String> r = getListBySort(key, so);
		return r;
	}
}
