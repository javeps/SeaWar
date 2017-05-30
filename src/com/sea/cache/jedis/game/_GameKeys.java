package com.sea.cache.jedis.game;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class _GameKeys implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(_GameKeys.class);

	static public String appendStr(Object... objects) {
		if (objects == null)
			return "";

		StringBuffer sb = new StringBuffer();
		for (Object o : objects) {
			if (o == null)
				continue;
			sb.append(o);
		}
		String r = sb.toString();
		return r;
	}

	public static String fmt(String origin, Object... args) {
		if (args == null)
			return origin;

		int len = args.length;
		if (len <= 0)
			return origin;

		int index = origin.indexOf("*");

		if (index < 0)
			return origin;

		for (Object v : args) {
			origin = origin.replaceFirst("\\*", v.toString());
		}
		return origin;
	}

	// ======= hash key head

	static public final String Key_ArmyGid = "k:agid";
	static public final String Key_HeroGid = "k:hgid";

	// 声望对象(用来做寻敌人)
	static public final String Pattern_Key_PRenown = "k:up:renown:pcid:*";
	static public final String Pattern_Key_Reward = "k:up:reward:*";
	static public final String Pattern_Key_Rank_PWeek = "k:up:rank:player:week:*";
	static public final String Pattern_Key_Rank_PAll = "k:up:rank:player:all:*";
	static public final String Pattern_Key_Rank_CWeek = "k:up:rank:clan:week:*";
	static public final String Pattern_Key_Rank_CAll = "k:up:rank:clan:all:*";
	static public final String Pattern_Key_Clan = "k:up:clan:ccid:*";
	static public final String Pattern_Key_Clan_Member = "k:up:clan:*:member:*";
	static public final String Pattern_Key_Clan_Request = "k:up:clan:*:reqjoin:*";

	static public final String Pattern_Key_AllPlayer = "k:up:ucid:*";
	static public final String Pattern_Key_PUcid = "k:up:ucid:*:pcid:*";
	static public final String Pattern_Key_PBuild = "k:up:pcid:*:bcid:*";
	static public final String Pattern_Key_PObst = "k:up:pcid:*:ocid:*";
	static public final String Pattern_Key_PTech = "k:up:pcid:*:techgid:*";
	// 军队:舰船[拥有，很正在建造]field:own,make
	static public final String Pattern_Key_PArmy = "k:up:pcid:*:bcid:*:agid:*";
	static public final String Pattern_Key_PHero = "k:up:pcid:*:hgid:*";

	static public final String Pattern_Key_Chat = "k:up:chat:type:*";

	static public final String Pattern_Key_Mail_Att_Day = "k:up:mail:att:*";
	static public final String Pattern_Key_Mail_Att_Bepcid = "k:up:mail:att:bepcid:*";
	static public final String Pattern_Key_Mail_Att_Pinfo = "k:up:mail:att:pinfo:*";

	static public final String Pattern_Key_Mail_Pl_Day = "k:up:mail:player:*";
	static public final String Pattern_Key_Mail_Pl_ToPcid = "k:up:mail:player:pcid:*";
	static public final String Pattern_Key_Mail_Pl_Type = "k:up:mail:player:type:*";

	static public final String Assist_Player_PCID = "k:assist:up:pcid";
	static public final String Assist_Player_Name = "k:assist:up:pname";
	static public final String Assist_Clan_Name = "k:assist:clan:name";
	// 用户
	static public final String Pattern_Key_User_List = "k:user:lgid_*";
	static public final String Pattern_Key_User = "k:user:lgid_*_pwd_*";
	static public final String Assist_User_Ucid = "k:assist:up:ucid";

	// ====== 第一处修改，将玩家辅助表renown声望
	static public final String Pattern_Pcid = "pcid:*";

	static public String fmtPcid(Object objPcid) {
		return fmt(Pattern_Pcid, objPcid);
	}

	static public final String Pattern_UcidPcid = "ucid:*:pcid:*";

	static public String fmtUcidPcid(Object objUcid, Object objPcid) {
		return fmt(Pattern_UcidPcid, objUcid, objPcid);
	}

	/** 玩家声望表 **/
	static public final String Key_PRenown = "k:up:renown";

	/** 玩家信息表(建筑，科技，障碍，英雄，拥有兵，生产兵) **/
	static public final String Pattern_Pinfo_Pcid = "k:up:pinfo:pcid:*";
	/** 玩家建筑 **/
	static public final String Pattern_Bcid = "bcid:*";
	/** 玩家建筑拥有兵 **/
	static public final String Pattern_Bcid_OArmy = "own:bcid:*:agid:*";
	/** 玩家建筑建造兵 **/
	static public final String Pattern_Bcid_MArmy = "make:bcid:*:agid:*";
	/** 玩家障碍 **/
	static public final String Pattern_Ocid = "ocid:*";
	/** 玩家英雄 **/
	static public final String Pattern_Hgid = "hgid:*";
	/** 玩家科技 **/
	static public final String Pattern_Tgid = "tgid:*";

	static public String fmtPinfoPcid(Object objPcid) {
		return fmt(Pattern_Pinfo_Pcid, objPcid);
	}

	static public String fmtBcid(Object objCid) {
		return fmt(Pattern_Bcid, objCid);
	}

	static public String fmtBcidOArmy(Object objCid, Object objGid) {
		return fmt(Pattern_Bcid_OArmy, objCid, objGid);
	}

	static public String fmtBcidMArmy(Object objCid, Object objGid) {
		return fmt(Pattern_Bcid_MArmy, objCid, objGid);
	}

	static public String fmtOcid(Object objCid) {
		return fmt(Pattern_Ocid, objCid);
	}

	static public String fmtHgid(Object objCid) {
		return fmt(Pattern_Hgid, objCid);
	}

	static public String fmtTgid(Object objCid) {
		return fmt(Pattern_Tgid, objCid);
	}
}
