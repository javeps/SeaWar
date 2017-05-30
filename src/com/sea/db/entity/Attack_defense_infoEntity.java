package com.sea.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.json.JSON;
import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;
import com.sea.cache.jedis.game.MailFPlayerJedis;
import com.sea.db.bean.Attack_defense_info;
import com.sea.db.bean.Attack_mail;
import com.sea.db.bean.Player_armys;
import com.sea.db.bean.Player_build_obst;
import com.sea.db.bean.Player_buildings;
import com.sea.db.bean.Player_hero;
import com.sea.db.bean.Player_tech;
import com.sea.db.dao.Attack_defense_infoDAO;
import com.sea.db.internal.Attack_defense_infoInternal;
//import com.sea.content.AppContext;

//seawar_design - attack_defense_info
@SuppressWarnings({ "static-access", "rawtypes" })
public class Attack_defense_infoEntity extends Attack_defense_infoInternal {
	static Log log = LogFactory.getLog(Attack_defense_infoEntity.class);

	public static final Attack_defense_infoEntity my = new Attack_defense_infoEntity();

	static Attack_defense_infoDAO Attack_defense_infoDAO = null;

	public static Attack_defense_infoDAO Attack_defense_infoDAO() {
		if (Attack_defense_infoDAO == null)
			Attack_defense_infoDAO = new Attack_defense_infoDAO(
					com.sea.content.AppContext.ds());
		return Attack_defense_infoDAO;
	}

	// static Attack_defense_infoDAO Attack_defense_infoDAO99 = null;
	// public static Attack_defense_infoDAO Attack_defense_infoDAO99() {
	// if( Attack_defense_infoDAO99 == null)
	// Attack_defense_infoDAO99 = new
	// Attack_defense_infoDAO(com.sea.content.AppContext.ds99());
	// return Attack_defense_infoDAO99;
	// }

	public static void insertMmTry(final Attack_defense_info attack_defense_info) {
		Attack_defense_infoDAO DAO = Attack_defense_infoDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asynchronousInsert(attack_defense_info, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin

	// ================== 数据库操作 begin ==================

	// 插入数据
	public static void insertDefenseInfo(Attack_defense_info r2) {
		if (r2 != null) {
			// r2 = r2.insert();
			MailFPlayerJedis.setData(r2);
		}
	}

	// 更新数据
	public static void updateDefenseInfo(Attack_defense_info r2) {
		if (r2 != null) {
			// r2 = r2.update();
			MailFPlayerJedis.updataData(r2);
		}
	}

	// 删除数据
	public static void deleteDefenseInfo(Attack_defense_info origin) {
		if (origin != null) {
			// int result = origin.delete();
			MailFPlayerJedis.delData(origin);
		}
	}

	public static Attack_defense_info getDefenseInfo(String attmcid) {
		Attack_defense_info r2 = null;
		r2 = MailFPlayerJedis.getAttMail(attmcid);
		if (r2 == null) {
			// r2 = getByAttmcid(attmcid);
			// MailFPlayerJedis.setHash(r2);
		}
		return r2;
	}

	// ================== 数据库操作 end ==================
	public static Attack_defense_info createNew(String attcid, String builds,
			String obsts, String amrys, String teches, String heros) {
		Attack_defense_info r2 = Attack_defense_info.newAttack_defense_info(0,
				attcid, builds, obsts, amrys, teches, heros);
		return r2;
	}

	public static Attack_defense_info createNewInsert(String attcid,
			String builds, String obsts, String amrys, String teches,
			String heros) {
		Attack_defense_info r2 = createNew(attcid, builds, obsts, amrys,
				teches, heros);
		insertDefenseInfo(r2);
		return r2;
	}

	public static Attack_defense_info getDefenseInfo(Attack_mail attMail) {
		Attack_defense_info r2 = null;
		if (attMail == null)
			return r2;
		r2 = getDefenseInfo(attMail.getAttcid());
		return r2;
	}

	// ================= 服务器数据数据 ==>> 客服端数据 ==================
	public static List<Player_buildings> toListDefenseBuildInfo(String strInfo) {
		List<Player_buildings> r2 = null;
		if (strInfo == null || "".equals(strInfo.trim()))
			return r2;
		try {
			List list = JSON.parseList(strInfo);
			r2 = new ArrayList<Player_buildings>();
			for (Object obj : list) {
				Map m = (Map) obj;
				int type = MapEx.getInt(m, "type");
				int gid = MapEx.getInt(m, "gid");
				int bcid = MapEx.getInt(m, "bcid");
				int lvl = MapEx.getInt(m, "lvl");
				long cooldown = MapEx.getLong(m, "cooldown");
				int pos = MapEx.getInt(m, "pos");
				int statue = MapEx.getInt(m, "state");
				Player_buildings item = Player_buildings.newPlayer_buildings(0,
						bcid, "", 0, "", gid, lvl, cooldown, pos, statue, type,
						0L, 0L, 0, 0L, 0L);
				r2.add(item);
			}
		} catch (Exception e) {
			log.error(e);
		}
		return r2;
	}

	public static String toStrDefenseBuildInfo(List<Player_buildings> list) {
		String r2 = "";
		boolean isNull = ListEx.isEmpty(list);
		if (isNull)
			return r2;
		StringBuffer sb = new StringBuffer("[");
		for (Player_buildings item : list) {
			sb.append("{");
			// sb.append("\"pid\"").append(":").append(item.player_id).append(",");
			sb.append("\"bcid\"").append(":").append(item.getBcid())
					.append(",");
			sb.append("\"type\"").append(":").append(item.type).append(",");
			sb.append("\"gid\"").append(":").append(item.gid).append(",");
			sb.append("\"lvl\"").append(":").append(item.lvl).append(",");
			sb.append("\"cooldown\"").append(":").append(item.getCooldown_ms())
					.append(",");
			sb.append("\"pos\"").append(":").append(item.getPosition_index())
					.append(",");
			sb.append("\"state\"").append(":").append(item.getState());
			sb.append("}").append(",");
		}
		r2 = sb.toString();
		int len = r2.length();
		if (len > 2) {
			r2 = r2.substring(0, len - 1);
		}
		r2 += "]";
		return r2;
	}

	public static List<Player_armys> toListDefenseArmyInfo(String strInfo) {
		List<Player_armys> r2 = null;
		if (strInfo == null || "".equals(strInfo.trim()))
			return r2;
		try {
			List list = JSON.parseList(strInfo);
			r2 = new ArrayList<Player_armys>();
			for (Object obj : list) {
				Map m = (Map) obj;
				int num = MapEx.getInt(m, "num");
				int gid = MapEx.getInt(m, "gid");
				int bcid = MapEx.getInt(m, "bcid");
				int lvl = MapEx.getInt(m, "lvl");
				Player_armys item = Player_armys.newPlayer_armys(0, "", 0, "",
						gid, lvl, num, bcid);
				r2.add(item);
			}
		} catch (Exception e) {
			log.error(e);
		}
		return r2;
	}

	public static String toStrDefenseArmyInfo(List<Player_armys> list) {
		String r2 = "";
		boolean isNull = ListEx.isEmpty(list);
		if (isNull)
			return r2;
		StringBuffer sb = new StringBuffer("[");
		for (Player_armys item : list) {
			sb.append("{");
			// sb.append("\"pid\"").append(":").append(item.player_id).append(",");
			sb.append("\"bcid\"").append(":").append(item.getBcid())
					.append(",");
			sb.append("\"gid\"").append(":").append(item.gid).append(",");
			sb.append("\"lvl\"").append(":").append(item.lvl).append(",");
			sb.append("\"num\"").append(":").append(item.num);
			sb.append("}").append(",");
		}
		r2 = sb.toString();
		int len = r2.length();
		if (len > 2) {
			r2 = r2.substring(0, len - 1);
		}
		r2 += "]";
		return r2;
	}

	public static List<Player_tech> toListDefenseTechesInfo(String strInfo) {
		List<Player_tech> r2 = null;
		if (strInfo == null || "".equals(strInfo.trim()))
			return r2;
		try {
			List list = JSON.parseList(strInfo);
			r2 = new ArrayList<Player_tech>();
			for (Object obj : list) {
				Map m = (Map) obj;
				int gid = MapEx.getInt(m, "gid");
				int lvl = MapEx.getInt(m, "lvl");
				Player_tech item = Player_tech.newPlayer_tech(0, "", 0, "",
						gid, lvl);
				r2.add(item);
			}
		} catch (Exception e) {
			log.error(e);
		}
		return r2;
	}

	public static String toStrDefenseTechesInfo(List<Player_tech> list) {
		String r2 = "";
		boolean isNull = ListEx.isEmpty(list);
		if (isNull)
			return r2;
		StringBuffer sb = new StringBuffer("[");
		for (Player_tech item : list) {
			sb.append("{");
			// sb.append("\"pid\"").append(":").append(item.player_id).append(",");
			sb.append("\"gid\"").append(":").append(item.gid).append(",");
			sb.append("\"lvl\"").append(":").append(item.lvl).append(",");
			sb.append("}").append(",");
		}
		r2 = sb.toString();
		int len = r2.length();
		if (len > 2) {
			r2 = r2.substring(0, len - 1);
		}
		r2 += "]";
		return r2;
	}

	public static List<Player_buildings> toListDefenseObstInfo(String strInfo) {
		List<Player_buildings> r2 = null;
		if (strInfo == null || "".equals(strInfo.trim()))
			return r2;
		try {
			List list = JSON.parseList(strInfo);
			r2 = new ArrayList<Player_buildings>();
			for (Object obj : list) {
				Map m = (Map) obj;
				int type = MapEx.getInt(m, "type");
				int gid = MapEx.getInt(m, "gid");
				int bcid = MapEx.getInt(m, "bcid");
				int lvl = MapEx.getInt(m, "lvl");
				long cooldown = MapEx.getLong(m, "cooldown");
				int pos = MapEx.getInt(m, "pos");
				int statue = MapEx.getInt(m, "state");
				Player_buildings item = Player_buildings.newPlayer_buildings(0,
						bcid, "", 0, "", gid, lvl, cooldown, pos, statue, type,
						0L, 0L, 0, 0L, 0L);
				r2.add(item);
			}
		} catch (Exception e) {
			log.error(e);
		}
		return r2;
	}

	public static String toStrDefenseObstInfo(List<Player_build_obst> list) {
		String r2 = "";
		boolean isNull = ListEx.isEmpty(list);
		if (isNull)
			return r2;
		StringBuffer sb = new StringBuffer("[");
		for (Player_build_obst item : list) {
			sb.append("{");
			sb.append("\"bcid\"").append(":").append(item.getBcid())
					.append(",");
			sb.append("\"type\"").append(":").append(item.getType())
					.append(",");
			sb.append("\"gid\"").append(":").append(item.getGid()).append(",");
			sb.append("\"lvl\"").append(":").append(item.getLvl()).append(",");
			sb.append("\"cooldown\"").append(":").append(item.getCooldown_ms())
					.append(",");
			sb.append("\"pos\"").append(":").append(item.getPosition_index())
					.append(",");
			sb.append("\"state\"").append(":").append(item.getState());
			sb.append("}").append(",");
		}
		r2 = sb.toString();
		int len = r2.length();
		if (len > 2) {
			r2 = r2.substring(0, len - 1);
		}
		r2 += "]";
		return r2;
	}

	public static List<Player_hero> toListDefenseHeroInfo(String strInfo) {
		List<Player_hero> r2 = null;
		if (strInfo == null || "".equals(strInfo.trim()))
			return r2;
		try {
			List list = JSON.parseList(strInfo);
			r2 = new ArrayList<Player_hero>();
			for (Object obj : list) {
				Map m = null;
				if (obj instanceof Map) {
					m = (Map) obj;
				} else if (obj instanceof String) {
					m = JSON.parseMap(obj.toString());
				} else {
					continue;
				}
				int hgid = MapEx.getInt(m, "hgid");
				if (hgid <= 0)
					continue;
				int hcid = MapEx.getInt(m, "hcid");
				int pcid = MapEx.getInt(m, "pcid");
				int addDamage = MapEx.getInt(m, "damage");
				int statues = MapEx.getInt(m, "statues");
				int addHP = MapEx.getInt(m, "hp");
				int addAttSpeed = MapEx.getInt(m, "speed");
				int skillGid = MapEx.getInt(m, "kgid");
				int fpos = MapEx.getInt(m, "fpos");
				String pname = "";
				String hname = "";
				int maxDamage = 0;
				int maxHP = 0;
				int maxAttSpeed = 0;
				long createTime = 0l;
				long deadTime = 0l;

				Player_hero item = Player_hero.newPlayer_hero(0, hcid, pcid,
						pname, hname, hgid, addDamage, maxDamage, addHP, maxHP,
						addAttSpeed, maxAttSpeed, statues, createTime,
						deadTime, skillGid, fpos);
				r2.add(item);
			}
		} catch (Exception e) {
			log.error(e);
		}
		return r2;
	}

	public static String toStrDefenseHeroInfo(List<Player_hero> list) {
		String r2 = "";
		boolean isNull = ListEx.isEmpty(list);
		if (isNull)
			return r2;
		StringBuffer sb = new StringBuffer("[");
		for (Player_hero item : list) {
			sb.append("{");
			sb.append("\"hcid\"").append(":").append(item.getHcid())
					.append(",");
			sb.append("\"pcid\"").append(":").append(item.getPcid())
					.append(",");
			sb.append("\"hgid\"").append(":").append(item.getHgid())
					.append(",");
			sb.append("\"damage\"").append(":").append(item.getAdddamage())
					.append(",");
			sb.append("\"statues\"").append(":").append(item.getStatues())
					.append(",");
			sb.append("\"hp\"").append(":").append(item.getAddhp()).append(",");
			sb.append("\"speed\"").append(":").append(item.getAddattspeed())
					.append(",");
			sb.append("\"kgid\"").append(":").append(item.getSkillgid())
					.append(",");
			sb.append("\"fpos\"").append(":").append(item.getFpos());
			sb.append("}").append(",");
		}
		r2 = sb.toString();
		int len = r2.length();
		if (len > 2) {
			r2 = r2.substring(0, len - 1);
		}
		r2 += "]";
		return r2;
	}
	// types end

}
