package com.sea.cache.jedis.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.sql.mysql.BeanSupport;
import com.bowlong.util.MapEx;
import com.sea.cache.jedis.origin.JedisList;
import com.sea.db.bean.Reward;
import com.sea.db.dao.RewardDAO;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class RewardJedis extends GameBaseJedis implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(RewardJedis.class);

	public static Reward toObjByStrVal(String strVal) {
		Reward r = null;
		Map map_ = UtileTools.strToMap(strVal);
		long createtime = MapEx.getLong(map_, "createtime");
		if (createtime <= 0)
			return r;

		int pcid = MapEx.getInt(map_, "pcid");
		int id = MapEx.getInt(map_, "id");
		String pname = MapEx.getString(map_, "pname");
		int type = MapEx.getInt(map_, "type");
		String reward = MapEx.getString(map_, "reward");
		r = Reward.newReward(id, pcid, pname, type, reward, createtime);
		return r;
	}

	public static boolean isHas(String strAll) {
		String key = getKeyReward(strAll);
		boolean r = JedisList.isHasKey(key);
		return r;
	}

	public static boolean isHas() {
		String key = getListCurKey();
		boolean r = JedisList.isHasKey(key);
		return r;
	}

	// =========== Method

	static String getListCurKey() {
		Date date = new Date();
		String dataStr = RewardDAO.sdfDd.format(date);
		String r = getKeyReward(dataStr);
		return r;
	}

	public static void setDataReward(BeanSupport item) {
		String key = getListCurKey();
		setDataInListBy(key, item);
	}

	public static void setList(List<Reward> origin) {
		if (origin == null || origin.size() <= 0)
			return;
		List<BeanSupport> list = new ArrayList<BeanSupport>();
		list.addAll(origin);
		String key = getListCurKey();
		setDataInListBy(key, list);
	}

	public static void deleteReward(List<String> timeList) {
		if (timeList == null || timeList.size() <= 0)
			return;
		int len = timeList.size();
		String[] keys = new String[len];
		for (int i = 0; i < len; i++) {
			String t = timeList.get(i);
			String key = getKeyReward(t);
			keys[i] = key;
		}
		JedisList.delKeys(keys);
	}

	public static void deleteReward(String dateStr) {
		String key = getKeyReward(dateStr);
		JedisList.delKeys(key);
	}

	public static void deleteData(Reward item) {
		String key = getListCurKey();
		String vals = getValStr(item);
		JedisList.delValsInList(key, vals);
	}

	public static List<Reward> getListCur() {
		List<Reward> r = null;
		String key = getListCurKey();
		List<String> valList = JedisList.getListAll(key);
		if (valList != null && valList.size() > 0) {
			r = new ArrayList<Reward>();
			for (String val : valList) {
				Reward item = toObjByStrVal(val);
				if (item == null)
					continue;
				r.add(item);
			}
		}
		return r;
	}
}
