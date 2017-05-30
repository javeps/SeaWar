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
import com.sea.db.bean.Rank_clan;
import com.sea.db.dao.Rank_clanDAO;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class RankClanJedis extends GameBaseJedis implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(RankClanJedis.class);

	public static Rank_clan toObjByStrVal(String strVal) {
		Rank_clan r = null;
		Map map_ = UtileTools.strToMap(strVal);
		String ccid = MapEx.getString(map_, "ccid");
		if (ccid == null || "".equals(ccid.trim()))
			return r;
		int id = MapEx.getInt(map_, "id");
		int icon = MapEx.getInt(map_, "icon");
		String cname = MapEx.getString(map_, "cname");
		int currank = MapEx.getInt(map_, "currank");
		int renownAll = MapEx.getInt(map_, "renownAll");
		int renownWeek = MapEx.getInt(map_, "renownWeek");
		int type = MapEx.getInt(map_, "type");
		int curnum = MapEx.getInt(map_, "curnum");
		int maxnum = MapEx.getInt(map_, "maxnum");

		r = Rank_clan.newRank_clan(id, ccid, icon, cname, currank, renownAll,
				renownWeek, type, curnum, maxnum);
		return r;
	}

	public static void setDataReward(BeanSupport item) {
		String key = "";
		if (item != null && item instanceof Rank_clan) {
			Rank_clan r = (Rank_clan) item;
			int type = r.getType();
			switch (type) {
			case 1:
				key = getListCurKeyAll();
				break;
			default:
				key = getListCurKeyWeek();
				break;
			}
		}
		setDataInListBy(key, item);
	}

	public static void deleteRankClanByDTime(List<String> timeList) {
		if (timeList == null || timeList.size() <= 0)
			return;
		int len = timeList.size();
		String[] keys = new String[len * 2];
		for (int i = 0; i < len; i++) {
			String t = timeList.get(i);
			String key_week = getKeyRankClanByWeek(t);
			keys[i] = key_week;
			String key_all = getKeyRankClanByAll(t);
			keys[i + len] = key_all;
		}
		JedisList.delKeys(keys);
	}

	// =================== 周排行榜

	static String getListCurKeyWeek() {
		Date date = new Date();
		String dataStr = Rank_clanDAO.sdfDd.format(date);
		String r = getKeyRankClanByWeek(dataStr);
		return r;
	}

	public static void deleteRewardWeek() {
		String key = getListCurKeyWeek();
		JedisList.delKeys(key);
	}

	public static List<Rank_clan> getListCurWeek() {
		List<Rank_clan> r = null;
		String key = getListCurKeyWeek();
		List<String> valList = JedisList.getListAll(key);
		if (valList != null && valList.size() > 0) {
			r = new ArrayList<Rank_clan>();
			for (String val : valList) {
				Rank_clan item = toObjByStrVal(val);
				if (item == null)
					continue;
				r.add(item);
			}
		}
		return r;
	}

	// =================== 总排行榜
	static String getListCurKeyAll() {
		Date date = new Date();
		String dataStr = Rank_clanDAO.sdfDd.format(date);
		String r = getKeyRankClanByAll(dataStr);
		return r;
	}

	public static void deleteRewardAll() {
		String key = getListCurKeyAll();
		JedisList.delKeys(key);
	}

	public static List<Rank_clan> getListCurAll() {
		List<Rank_clan> r = null;
		String key = getListCurKeyAll();
		List<String> valList = JedisList.getListAll(key);
		if (valList != null && valList.size() > 0) {
			r = new ArrayList<Rank_clan>();
			for (String val : valList) {
				Rank_clan item = toObjByStrVal(val);
				if (item == null)
					continue;
				r.add(item);
			}
		}
		return r;
	}
}
