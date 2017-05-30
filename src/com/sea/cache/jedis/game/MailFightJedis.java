package com.sea.cache.jedis.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.util.MapEx;
import com.sea.cache.jedis.origin.JedisHash;
import com.sea.cache.jedis.origin.KeyValEntity;
import com.sea.db.bean.Attack_mail;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class MailFightJedis extends GameBaseJedis implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(MailFightJedis.class);

	public static Attack_mail toObjByStrVal(String strVal) {
		Map map_ = UtileTools.strToMap(strVal);
		return toObjByMap(map_);
	}

	public static Attack_mail toObjByMap(Map map) {
		if (map == null)
			return null;
		String attcid = MapEx.getString(map, "attcid");
		if (attcid == null || "".equals(attcid.trim()))
			return null;
		int id = MapEx.getInt(map, "id");
		int attPcid = MapEx.getInt(map, "attPcid");
		String attPname = MapEx.getString(map, "attPname");
		int beAttPcid = MapEx.getInt(map, "beAttPcid");
		String beAttPname = MapEx.getString(map, "beAttPname");
		long begin_time = MapEx.getLong(map, "begin_time");
		long end_time = MapEx.getLong(map, "end_time");
		int star = MapEx.getInt(map, "star");
		int preGold = MapEx.getInt(map, "preGold");
		int preOil = MapEx.getInt(map, "preOil");
		int preAttRenown = MapEx.getInt(map, "preAttRenown");
		int attRenown = MapEx.getInt(map, "attRenown");
		int attGold = MapEx.getInt(map, "attGold");
		int attOil = MapEx.getInt(map, "attOil");
		boolean isHitBack = MapEx.getBoolean(map, "isHitBack");

		String clancid = MapEx.getString(map, "clancid");
		String cname = MapEx.getString(map, "cname");
		int cicon = MapEx.getInt(map, "cicon");
		int offenHP = MapEx.getInt(map, "offenHP");
		int offenAtt = MapEx.getInt(map, "offenAtt");

		String defccid = MapEx.getString(map, "defccid");
		String defcname = MapEx.getString(map, "defcname");
		int defcicon = MapEx.getInt(map, "defcicon");
		int defenseHP = MapEx.getInt(map, "defenseHP");
		int defenseAtt = MapEx.getInt(map, "defenseAtt");

		String attInfos = MapEx.getString(map, "attInfos");
		int beRewon = MapEx.getInt(map, "beRewon");
		int egid = MapEx.getInt(map, "egid");
		int num = MapEx.getInt(map, "num");
		String attHeros = MapEx.getString(map, "attHeros");

		return Attack_mail.newAttack_mail(id, attcid, attPcid, attPname,
				beAttPcid, beAttPname, begin_time, end_time, star, preGold,
				preOil, preAttRenown, attRenown, attGold, attOil, isHitBack,
				clancid, cname, cicon, offenHP, offenAtt, defccid, defcname,
				defcicon, defenseHP, defenseAtt, attInfos, beRewon, egid, num,
				attHeros);
	}

	// =========== Method

	public static void setDataFight(Attack_mail item) {
		if (item != null) {
			int bepcid = item.getBeattpcid();
			String key = getKeyMailFight(bepcid);
			String field = item.getAttcid();
			String val = getValStr(item);
			JedisHash.setKFV(key, field, val);
		}

		// 关系
		setRelationMT(item);
	}

	public static void upDataFight(Attack_mail item) {
		setDataFight(item);
	}

	public static void deleteFight(Attack_mail item) {
		if (item != null) {
			int bepcid = item.getBeattpcid();
			String key = getKeyMailFight(bepcid);
			String field = item.getAttcid();
			JedisHash.delByKFields(key, field);

			// 该邮件下面记录的玩家战斗信息
			MailFPlayerJedis.delBy(field);
		}
	}

	public static void setListFight(List<Attack_mail> origin) {
		if (origin == null || origin.size() <= 0)
			return;
		List<KeyValEntity> list = new ArrayList<KeyValEntity>();
		for (Attack_mail item : origin) {
			int bepcid = item.getBeattpcid();
			String key = getKeyMailFight(bepcid);
			String field = item.getAttcid();

			if (field == null || "".equals(field.trim()))
				continue;

			String val = getValStr(item);
			KeyValEntity k = new KeyValEntity(key, field, val);
			list.add(k);
		}
		setListDataByKFV(list);
	}

	public static Attack_mail getAttMail(int pcid, String field) {
		String key = getKeyMailFight(pcid);
		String val = JedisHash.getValByKF(key, field);
		Attack_mail r = toObjByStrVal(val);
		return r;
	}

	public static List<Attack_mail> getList(int pcid) {
		List<Attack_mail> r = null;
		String key = getKeyMailFight(pcid);
		List<String> valList = JedisHash.getListHashVals(key);
		boolean isHas = JedisHash.isHasKey(key);
		if (isHas) {
			r = new ArrayList<Attack_mail>();
		}
		if (valList != null && valList.size() > 0) {
			if (r == null) {
				r = new ArrayList<Attack_mail>();
			}
			for (String val : valList) {
				Attack_mail item = toObjByStrVal(val);
				if (item == null)
					continue;
				r.add(item);
			}
		}
		return r;
	}

	// 设置战报和时间的关系
	static void setRelationMT(Attack_mail item) {
		if (item != null) {
			int bepcid = item.getBeattpcid();
			String valKey = getKeyMailFight(bepcid);
			String field = item.getAttcid();
			long bt = item.getBegin_time();
			String timeStr = UtileTools.getStrYYYYMMDDBy(bt);
			String key = getKeyMFTimeRelation(timeStr);
			JedisHash.setKFV(key, field, valKey);
		}
	}
}
