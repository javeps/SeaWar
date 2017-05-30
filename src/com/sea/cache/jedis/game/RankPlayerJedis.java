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
import com.sea.db.bean.Rank_player;
import com.sea.db.dao.Rank_playerDAO;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class RankPlayerJedis extends GameBaseJedis implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(RankPlayerJedis.class);

	public static Rank_player toObjByStrVal(String strVal) {
		Rank_player r = null;
		Map map_ = UtileTools.strToMap(strVal);
		int pcid = MapEx.getInt(map_, "pcid");
		if (pcid == 0)
			return r;
		int id = MapEx.getInt(map_, "id");
		int ucid = MapEx.getInt(map_, "ucid");
		String pname = MapEx.getString(map_, "pname");
		int pexp = MapEx.getInt(map_, "pexp");
		String clancid = MapEx.getString(map_, "clancid");
		int clanIcon = MapEx.getInt(map_, "clanIcon");
		String clanName = MapEx.getString(map_, "clanName");
		int currank = MapEx.getInt(map_, "currank");
		int renown = MapEx.getInt(map_, "renown");
		int weekAddRenown = MapEx.getInt(map_, "weekAddRenown");
		int type = MapEx.getInt(map_, "type");

		r = Rank_player.newRank_player(id, ucid, pcid, pname, pexp, clancid,
				clanIcon, clanName, currank, renown, weekAddRenown, type);
		return r;
	}

	public static void setDataReward(BeanSupport item) {
		String key = "";
		if (item != null && item instanceof Rank_player) {
			Rank_player r = (Rank_player) item;
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

	public static void deleteRankPlayerByDTime(List<String> timeList) {
		if (timeList == null || timeList.size() <= 0)
			return;
		int len = timeList.size();
		String[] keys = new String[len * 2];
		for (int i = 0; i < len; i++) {
			String t = timeList.get(i);
			String key_week = getKeyRankPlayerByWeek(t);
			keys[i] = key_week;
			String key_all = getKeyRankPlayerByAll(t);
			keys[i + len] = key_all;
		}
		JedisList.delKeys(keys);
	}

	// 目前没有用到
	static void setList(List<Rank_player> origin) {
		if (origin == null || origin.size() <= 0)
			return;
		List<BeanSupport> list = new ArrayList<BeanSupport>();
		list.addAll(origin);
		String key = getListCurKeyWeek();
		setDataInListBy(key, list);
	}

	// ===================个人周排行榜

	static String getListCurKeyWeek() {
		Date date = new Date();
		String dataStr = Rank_playerDAO.sdfDd.format(date);
		String r = getKeyRankPlayerByWeek(dataStr);
		return r;
	}

	public static boolean isHasWeek() {
		String key = getListCurKeyWeek();
		boolean r = JedisList.isHasKey(key);
		return r;
	}

	public static void deleteRewardWeek() {
		String key = getListCurKeyWeek();
		JedisList.delKeys(key);
	}

	public static List<Rank_player> getListCurWeek() {
		List<Rank_player> r = null;
		String key = getListCurKeyWeek();
		List<String> valList = JedisList.getListAll(key);
		if (valList != null && valList.size() > 0) {
			r = new ArrayList<Rank_player>();
			for (String val : valList) {
				Rank_player item = toObjByStrVal(val);
				if (item == null)
					continue;
				r.add(item);
			}
		}
		return r;
	}

	// ===================个人总排行榜
	public static void deleteRewardAll() {
		String key = getListCurKeyAll();
		JedisList.delKeys(key);
	}

	static String getListCurKeyAll() {
		Date date = new Date();
		String dataStr = Rank_playerDAO.sdfDd.format(date);
		String r = getKeyRankPlayerByAll(dataStr);
		return r;
	}

	public static boolean isHasAll() {
		String key = getListCurKeyAll();
		boolean r = JedisList.isHasKey(key);
		return r;
	}

	public static List<Rank_player> getListCurAll() {
		List<Rank_player> r = null;
		String key = getListCurKeyAll();
		List<String> valList = JedisList.getListAll(key);
		if (valList != null && valList.size() > 0) {
			r = new ArrayList<Rank_player>();
			for (String val : valList) {
				Rank_player item = toObjByStrVal(val);
				if (item == null)
					continue;
				r.add(item);
			}
		}
		return r;
	}
}
