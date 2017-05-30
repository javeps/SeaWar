package com.sea.cache.jedis.admin;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sea.cache.jedis.origin.JedisHash;
import com.sea.db.dao.AdminDAO;

public class AdminRecord implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(AdminRecord.class);

	// 注册人数
	static public final String key_regist = "k:admin:record:registnum";

	static public int getRegistNum(String chnStr) {
		int val = 0;
		try {
			String valStr = JedisHash.getValHash(key_regist, chnStr);
			if ("".equals(valStr))
				return val;
			val = Integer.parseInt(valStr);
		} catch (Exception e) {
		}
		return val;
	}

	static public void addRegistNum(String chnStr) {
		if (chnStr == null || "".equals(chnStr.trim()))
			return;
		int val = getRegistNum(chnStr);
		val++;
		JedisHash.setValHash(key_regist, chnStr, String.valueOf(val));
		addDayRegistNum(chnStr);
	}

	static public final String key_regist_day = "k:admin:record:dayregnum:";

	static public int getDayRegistNum(String key, String chnStr) {
		int val = 0;
		try {
			String valStr = JedisHash.getValHash(key, chnStr);
			if ("".equals(valStr))
				return val;
			val = Integer.parseInt(valStr);
		} catch (Exception e) {
		}
		return val;
	}

	static public void addDayRegistNum(String chnStr) {
		if (chnStr == null || "".equals(chnStr.trim()))
			return;
		String key = key_regist_day + AdminDAO.TABLEDD();
		int val = getDayRegistNum(key, chnStr);
		val++;
		JedisHash.setValHash(key, chnStr, String.valueOf(val));
	}

	// 完成了新手引导的人数
	static public final String key_finish_guid = "k:admin:record:realnum";

	static public int getRealNum(String chnStr) {
		int val = 0;
		try {
			String valStr = JedisHash.getValHash(key_finish_guid, chnStr);
			if ("".equals(valStr))
				return val;
			val = Integer.parseInt(valStr);
		} catch (Exception e) {
		}
		return val;
	}

	static public void addRealNum(String chnStr) {
		if (chnStr == null || "".equals(chnStr.trim()))
			return;
		int val = getRealNum(chnStr);
		val++;
		JedisHash.setValHash(key_finish_guid, chnStr, String.valueOf(val));
	}
}
