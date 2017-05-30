package com.sea.cache.jedis.game;

import gen_b2g.serv.bean.ConstantType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bowlong.sql.mysql.BeanSupport;
import com.sea.cache.jedis.origin.JedisHash;
import com.sea.cache.jedis.origin.JedisList;
import com.sea.cache.jedis.origin.KeyMapEntity;
import com.sea.cache.jedis.origin.KeyValEntity;
import com.sea.db.bean.Attack_defense_info;
import com.sea.db.bean.Attack_mail;
import com.sea.db.bean.Clan;
import com.sea.db.bean.Clan_member;
import com.sea.db.bean.Clan_request;
import com.sea.db.bean.Player;
import com.sea.db.bean.Player_armys;
import com.sea.db.bean.Player_build_obst;
import com.sea.db.bean.Player_buildings;
import com.sea.db.bean.Player_hero;
import com.sea.db.bean.Player_mail;
import com.sea.db.bean.Player_producting;
import com.sea.db.bean.Player_tech;
import com.sea.tools.UtileTools;

public class GameBaseJedis implements Serializable {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	public static String getValStr(BeanSupport bean) {
		String r = "";
		if (bean == null)
			return r;
		Map map = bean.toBasicMap();
		r = UtileTools.mapToStr(map);
		return r;
	}

	// ===== 角色
	private static String getKeyPlayer(BeanSupport o) {
		Player item = (Player) o;
		int ucid = item.getUcid();
		int pcid = item.getPcid();
		String key = getKeyPlayer(ucid, pcid);
		return key;
	}

	public static String getPatternPlayer(int ucid) {
		return _GameKeys.fmt(_GameKeys.Pattern_Key_PUcid, ucid);
	}

	public static String getKeyPlayer(int ucid, int pcid) {
		return _GameKeys.fmt(_GameKeys.Pattern_Key_PUcid, ucid, pcid);
	}

	// ===== 建筑
	private static String getKeyBuild(BeanSupport o) {
		Player_buildings item = (Player_buildings) o;
		int bcid = item.getBcid();
		int pcid = item.getPcid();
		String key = getKeyBuild(pcid, bcid);
		return key;
	}

	public static String getKeyBuild(int pcid, int bcid) {
		return _GameKeys.fmt(_GameKeys.Pattern_Key_PBuild, pcid, bcid);
	}

	public static String getPatternBuild(int pcid) {
		return _GameKeys.fmt(_GameKeys.Pattern_Key_PBuild, pcid);
	}

	// ====== 障碍
	private static String getKeyObst(BeanSupport o) {
		Player_build_obst item = (Player_build_obst) o;
		int ocid = item.getBcid();
		int pcid = item.getPcid();
		String key = getKeyObst(pcid, ocid);
		return key;
	}

	public static String getKeyObst(int pcid, int ocid) {
		return _GameKeys.fmt(_GameKeys.Pattern_Key_PObst, pcid, ocid);
	}

	public static String getPatternObst(int pcid) {
		return _GameKeys.fmt(_GameKeys.Pattern_Key_PObst, pcid);
	}

	// ======= 舰船
	public static String getKeyArmyGid() {
		return _GameKeys.Key_ArmyGid;
	}

	private static String getKeyArmy(BeanSupport o) {
		Player_armys item = (Player_armys) o;
		int bcid = item.getBcid();
		int pcid = item.getPcid();
		int gid = item.getGid();
		String key = getKeyArmy(pcid, bcid, gid);
		return key;
	}
	
	private static String getKeyProduct(BeanSupport o) {
		Player_producting item = (Player_producting) o;
		int bcid = item.getBcid();
		int pcid = item.getPcid();
		int gid = item.getGid();
		String key = getKeyArmy(pcid, bcid, gid);
		return key;
	}

	public static String getKeyArmy(int pcid, int bcid, int gid) {
		return _GameKeys.fmt(_GameKeys.Pattern_Key_PArmy, pcid, bcid, gid);
	}

	public static String getPatternArmy(int pcid, int bcid) {
		return _GameKeys.fmt(_GameKeys.Pattern_Key_PArmy, pcid, bcid);
	}

	// ====== 科技
	private static String getKeyTech(BeanSupport o) {
		Player_tech item = (Player_tech) o;
		int gid = item.getGid();
		int pcid = item.getPcid();
		String key = getKeyTech(pcid, gid);
		return key;
	}

	public static String getKeyTech(int pcid, int gid) {
		return _GameKeys.fmt(_GameKeys.Pattern_Key_PTech, pcid, gid);
	}

	public static String getPatternTech(int pcid) {
		return _GameKeys.fmt(_GameKeys.Pattern_Key_PTech, pcid);
	}

	// ===== 奖励
	public static String getKeyReward(String dataStr) {
		return _GameKeys.fmt(_GameKeys.Pattern_Key_Reward, dataStr);
	}

	public static String getPatternReward() {
		return _GameKeys.Pattern_Key_Reward;
	}

	// ===== 排行榜 个人
	public static String getKeyRankPlayerByWeek(String dataStr) {
		return _GameKeys.fmt(_GameKeys.Pattern_Key_Rank_PWeek, dataStr);
	}

	public static String getPatternRankPlayerWeek() {
		return _GameKeys.Pattern_Key_Rank_PWeek;
	}

	public static String getKeyRankPlayerByAll(String dataStr) {
		return _GameKeys.fmt(_GameKeys.Pattern_Key_Rank_PAll, dataStr);
	}

	public static String getPatternRankPlayerAll() {
		return _GameKeys.Pattern_Key_Rank_PAll;
	}

	// ====== 聊天
	public static String getKeyChat(int type) {
		return _GameKeys.fmt(_GameKeys.Pattern_Key_Chat, type);
	}

	// ====== 战报邮件
	private static String getKeyMailFight(BeanSupport o) {
		Attack_mail item = (Attack_mail) o;
		int bepcid = item.getBeattpcid();
		String key = getKeyMailFight(bepcid);
		return key;
	}

	public static String getKeyMailFight(int bepcid) {
		return _GameKeys.fmt(_GameKeys.Pattern_Key_Mail_Att_Bepcid, bepcid);
	}

	// 战报 时间key 保存hahs 格式field对应的邮件的field , val 对应战报的key
	public static String getKeyMFTimeRelation(String timeStr) {
		return _GameKeys.fmt(_GameKeys.Pattern_Key_Mail_Att_Day, timeStr);
	}

	// ====== 战报邮件记录玩家信息
	private static String getKeyMailFightBePlayer(BeanSupport o) {
		Attack_defense_info item = (Attack_defense_info) o;
		String mcid = item.getAttmcid();
		String key = getKeyMailFightBePlayer(mcid);
		return key;
	}

	public static String getKeyMailFightBePlayer(String mcid) {
		return _GameKeys.fmt(_GameKeys.Pattern_Key_Mail_Att_Pinfo, mcid);
	}

	// ====== 个人邮件[系统邮件，和玩家邮件]

	private static String getKeyMail(BeanSupport o) {
		Player_mail item = (Player_mail) o;
		int type = item.getType();
		String key = "";
		switch (type) {
		case ConstantType.Type_Mail_Player:
		case ConstantType.Type_Mail_System_Player:
			int pcid = item.getToid();
			key = getKeyMailToPlayer(pcid);
			break;
		default:
			key = getKeyMailByType(type);
			break;
		}
		return key;
	}

	public static String getKeyMailToPlayer(int toPcid) {
		return _GameKeys.fmt(_GameKeys.Pattern_Key_Mail_Pl_ToPcid, toPcid);
	}

	public static String getKeyMailByType(int type) {
		return _GameKeys.fmt(_GameKeys.Pattern_Key_Mail_Pl_Type, type);
	}

	public static String getKeyMPTimeRelation(String timeStr) {
		return _GameKeys.fmt(_GameKeys.Pattern_Key_Mail_Pl_Day, timeStr);
	}

	// ====== 英雄
	public static String getKeyHeroGid() {
		return _GameKeys.Key_HeroGid;
	}

	private static String getKeyHero(BeanSupport o) {
		Player_hero item = (Player_hero) o;
		int pcid = item.getPcid();
		int hgid = item.getHgid();
		String key = getKeyHero(pcid, hgid);
		return key;
	}

	public static String getPatternHero(int pcid) {
		return _GameKeys.fmt(_GameKeys.Pattern_Key_PHero, pcid);
	}

	public static String getKeyHero(int pcid, int hgid) {
		return _GameKeys.fmt(_GameKeys.Pattern_Key_PHero, pcid, hgid);
	}

	// ====== 联盟
	public static String getPatternClan() {
		return _GameKeys.Pattern_Key_Clan;
	}

	public static String getKeyClan(String ccid) {
		return _GameKeys.fmt(_GameKeys.Pattern_Key_Clan, ccid);
	}

	private static String getKeyClan(BeanSupport o) {
		Clan item = (Clan) o;
		String ccid = item.getCcid();
		String key = getKeyClan(ccid);
		return key;
	}

	// ====== 联盟请求
	public static String getPatternClanReq(String ccid) {
		return _GameKeys.fmt(_GameKeys.Pattern_Key_Clan_Request, ccid);
	}

	public static String getKeyClanReq(String ccid, int pcid) {
		return _GameKeys.fmt(_GameKeys.Pattern_Key_Clan_Request, ccid, pcid);
	}

	private static String getKeyClanReq(BeanSupport o) {
		Clan_request item = (Clan_request) o;
		String ccid = item.getCcid();
		int pcid = item.getPcid();
		String key = getKeyClanReq(ccid, pcid);
		return key;
	}

	// ====== 联盟成员
	public static String getPatternClanMember(String ccid) {
		return _GameKeys.fmt(_GameKeys.Pattern_Key_Clan_Member, ccid);
	}

	public static String getKeyClanMember(String ccid, int pcid) {
		return _GameKeys.fmt(_GameKeys.Pattern_Key_Clan_Member, ccid, pcid);
	}

	private static String getKeyClanMember(BeanSupport o) {
		Clan_member item = (Clan_member) o;
		String ccid = item.getCcid();
		int pcid = item.getPcid();
		String key = getKeyClanMember(ccid, pcid);
		return key;
	}

	// ===== 排行榜 联盟
	public static String getKeyRankClanByWeek(String dataStr) {
		return _GameKeys.fmt(_GameKeys.Pattern_Key_Rank_CWeek, dataStr);
	}

	public static String getPatternRankClanWeek() {
		return _GameKeys.Pattern_Key_Rank_CWeek;
	}

	public static String getKeyRankClanByAll(String dataStr) {
		return _GameKeys.fmt(_GameKeys.Pattern_Key_Rank_CAll, dataStr);
	}

	public static String getPatternRankClanAll() {
		return _GameKeys.Pattern_Key_Rank_CAll;
	}

	// ====== 根据实体对象取得相应的key

	public static String getKey(BeanSupport bean) {
		if (bean == null)
			return "";
		if (bean instanceof Player) {
			return getKeyPlayer(bean);
		} else if (bean instanceof Player_buildings) {
			return getKeyBuild(bean);
		} else if (bean instanceof Player_build_obst) {
			return getKeyObst(bean);
		} else if (bean instanceof Player_armys) {
			return getKeyArmy(bean);
		} else if (bean instanceof Player_producting) {
			return getKeyProduct(bean);
		} else if (bean instanceof Player_tech) {
			return getKeyTech(bean);
		} else if (bean instanceof Attack_mail) {
			return getKeyMailFight(bean);
		} else if (bean instanceof Attack_defense_info) {
			return getKeyMailFightBePlayer(bean);
		} else if (bean instanceof Player_mail) {
			return getKeyMail(bean);
		} else if (bean instanceof Player_hero) {
			return getKeyHero(bean);
		} else if (bean instanceof Clan) {
			return getKeyClan(bean);
		} else if (bean instanceof Clan_member) {
			return getKeyClanMember(bean);
		} else if (bean instanceof Clan_request) {
			return getKeyClanReq(bean);
		}
		return "";
	}

	// ======= method

	public static void setListDataByKFV(List<BeanSupport> list, String field) {
		List<KeyValEntity> list_data = getListDateToKFV(list, field);
		setListDataByKFV(list_data);
	}

	public static void setListDataByKFV(List<KeyValEntity> list) {
		JedisHash.setKeyValList(list);
	}

	private static List<KeyValEntity> getListDateToKFV(List<BeanSupport> list,
			String field) {
		if (list == null || list.size() <= 0)
			return null;
		List<KeyValEntity> r = new ArrayList<KeyValEntity>();
		for (BeanSupport bean : list) {
			String key = getKey(bean);
			if ("".equals(key))
				continue;
			String val = getValStr(bean);
			KeyValEntity e = null;
			if (field == null || "".equals(field.trim())) {
				e = new KeyValEntity(key, val);
			} else {
				e = new KeyValEntity(key, field, val);
			}
			r.add(e);
		}
		return r;
	}

	// = == map
	public static void setListDataByKMap(List<BeanSupport> origin) {
		List<KeyMapEntity> list = getListDateToKMap(origin);
		setListDataByKMaps(list);
	}

	public static void setListDataByKMaps(List<KeyMapEntity> list) {
		JedisHash.setKeyMapList(list);
	}

	public static List<KeyMapEntity> getListDateToKMap(List<BeanSupport> list) {
		if (list == null || list.size() <= 0)
			return null;
		List<KeyMapEntity> r = new ArrayList<KeyMapEntity>();
		for (BeanSupport bean : list) {
			if (bean == null)
				continue;
			String key = getKey(bean);
			if ("".equals(key))
				continue;
			KeyMapEntity e = new KeyMapEntity(key, bean.toBasicMap());
			r.add(e);
		}
		return r;
	}

	// ======= key val 数据对象的处理[添加，更新，移除，以及根据patten来取得vals]

	public static void setData(BeanSupport bean) {
		String key = getKey(bean);
		if ("".equals(key))
			return;
		JedisHash.deleteKey(key);
		// String val = getValStr(bean);
		// JedisHash.setValByKey(key, val);
		KeyMapEntity kme = new KeyMapEntity(key, bean.toBasicMap());
		JedisHash.setKeyMapEntity(kme);
	}

	public static void updataData(BeanSupport bean) {
		setData(bean);
	}

	public static void delData(BeanSupport bean) {
		String key = getKey(bean);
		if ("".equals(key))
			return;
		JedisHash.delKeys(key);
	}

	public static void delListData(String pattern) {
		if (pattern == null || "".equals(pattern))
			return;
		JedisHash.delAllByPattern(pattern);
	}

	public static List<String> getListValStr(String pattern) {
		return JedisHash.getListValsByPattern(pattern);
	}

	// ======= key list 数据对象的处理[添加，更新，移除]

	public static void setDataInListBy(String key, BeanSupport item) {
		if (key == null || item == null)
			return;
		if ("".equals(key.trim()))
			return;

		String vals = getValStr(item);
		JedisList.setValsInList(key, vals);
	}

	public static void setDataInListBy(String key, List<BeanSupport> list) {
		if (key == null || list == null)
			return;
		if ("".equals(key.trim()) || list.size() <= 0)
			return;
		JedisHash.delKeys(key);
		int len = list.size();
		String[] vals = new String[len];
		for (int i = 0; i < len; i++) {
			BeanSupport item = list.get(i);
			String v = getValStr(item);
			vals[i] = v;
		}
		JedisList.setValsInList(key, vals);
	}

	// ======= key field val 数据对象的处理[添加，更新，移除]
}
