package com.sea.cache.jedis.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.util.ListEx;
import com.sea.cache.jedis.GameDelJedis;
import com.sea.cache.jedis.origin.JedisList;
import com.sea.db.dao.RewardDAO;

public class PVJedis implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(PVJedis.class);

	static final String keyPvGems = "k:pv:*:*";
	static final String keyPvRes = "k:pv:res:*:*";

	static String getListKeyGems(String dateStr, int pcid) {
		// "k:pv:" + dateStr + ":" + pcid;
		return _GameKeys.fmt(keyPvGems, dateStr, pcid);
	}

	static String getListKeyRes(String dateStr, int pcid) {
		// "k:pv:res:" + dateStr + ":" + pcid;
		return _GameKeys.fmt(keyPvRes, dateStr, pcid);
	}

	static public void setPv(int pcid, String v, boolean isGems) {
		Date date = new Date();
		String dateStr = RewardDAO.sdfDd.format(date);
		String key = "";
		if (isGems)
			key = getListKeyGems(dateStr, pcid);
		else
			key = getListKeyRes(dateStr, pcid);

		JedisList.setValsInList(key, v);
	}

	/**
	 * 
	 * @param dateStr
	 *            yyyyMMdd
	 * @param pcid
	 * @return
	 */
	static public List<String> getListPvBy(String dateStr, int pcid,
			boolean isGems) {
		String key = "";
		if (isGems)
			key = getListKeyGems(dateStr, pcid);
		else
			key = getListKeyRes(dateStr, pcid);
		return JedisList.getListAll(key);
	}

	static public List<String> getListPvAll() {
		List<String> list = new ArrayList<String>();
		List<String> keys = JedisList.getListKeysByPattern("k:pv:*");
		if (keys == null || keys.isEmpty())
			return list;
		for (String key : keys) {
			List<String> tmpList = JedisList.getListAll(key);
			if (tmpList == null || tmpList.isEmpty())
				continue;
			for (String tmp : tmpList) {
				String[] arrs = tmp.split(",");
				if (arrs.length <= 1)
					continue;
				for (String arr : arrs) {
					if (arr.indexOf("val") == -1)
						continue;
					int index = arr.indexOf("=");
					arr = arr.substring(index + 1);
					try {
						index = Math.abs(Integer.parseInt(arr));
						if (index > 2000) {
							list.add("key = " + key + ",val = " + tmp);
						}
					} catch (Exception e) {

					}
				}
			}
			// list.addAll(tmpList);
		}
		return list;
	}

	// 删除日志
	static public void delPV(List<String> timeList) {
		if (ListEx.isEmpty(timeList))
			return;
		List<String> delParr = new ArrayList<String>();
		int len = timeList.size();
		for (int i = 0; i < len; i++) {
			String d = timeList.get(i);
			String pg = _GameKeys.fmt(keyPvGems, d);
			String pr = _GameKeys.fmt(keyPvRes, d);
			delParr.add(pr);
			delParr.add(pg);
		}
		GameDelJedis.delAllByPatternKey(null, delParr);
	}
}