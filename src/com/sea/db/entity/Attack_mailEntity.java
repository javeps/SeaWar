package com.sea.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import gen_b2g.serv.bean.NAttMail;
import gen_b2g.serv.bean.NAttMails;
import gen_b2g.serv.bean.NAttackInfo;
import gen_b2g.serv.bean.NAttackInfos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.json.JSON;
import com.bowlong.util.MapEx;
import com.sea.cache.jedis.game.MailFightJedis;
import com.sea.db.bean.Attack_mail;
import com.sea.db.bean.Clan;
import com.sea.db.bean.Player;
import com.sea.db.dao.Attack_mailDAO;
import com.sea.db.internal.Attack_mailInternal;
import com.sea.logic.logicOperate.LogicHero;

//seawar_design - attack_mail
@SuppressWarnings({ "static-access" })
public class Attack_mailEntity extends Attack_mailInternal {
	static Log log = LogFactory.getLog(Attack_mailEntity.class);

	public static final Attack_mailEntity my = new Attack_mailEntity();

	static Attack_mailDAO Attack_mailDAO = null;

	public static Attack_mailDAO Attack_mailDAO() {
		if (Attack_mailDAO == null)
			Attack_mailDAO = new Attack_mailDAO(com.sea.content.AppContext.ds());
		return Attack_mailDAO;
	}

	// static Attack_mailDAO Attack_mailDAO99 = null;
	// public static Attack_mailDAO Attack_mailDAO99() {
	// if( Attack_mailDAO99 == null)
	// Attack_mailDAO99 = new Attack_mailDAO(com.sea.content.AppContext.ds99());
	// return Attack_mailDAO99;
	// }

	public static void insertMmTry(final Attack_mail attack_mail) {
		Attack_mailDAO DAO = Attack_mailDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asynchronousInsert(attack_mail, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin
	// ================== 数据库操作 begin ==================
	// 插入数据
	public static void insertMailFight(Attack_mail r2) {
		if (r2 != null) {
			// r2 = r2.insert();
			MailFightJedis.setDataFight(r2);
		}
	}

	// 更新数据
	public static void updateMailFight(Attack_mail r2) {
		if (r2 != null) {
			// r2 = r2.update();
			MailFightJedis.upDataFight(r2);
		}
	}

	// 删除数据
	public static void deleteMailFight(Attack_mail origin) {
		if (origin != null) {
			// int result = origin.delete();
			MailFightJedis.deleteFight(origin);
		}
	}

	public static Attack_mail getMail(Player p, String attmcid) {
		Attack_mail r2 = null;
		int pcid = p.getPcid();
		r2 = MailFightJedis.getAttMail(pcid, attmcid);
		if (r2 == null) {
			// r2 = getByAttcid(attmcid);
			// MailFightJedis.setDataFight(r2);
		}
		return r2;
	}

	public static List<Attack_mail> getAttMails(Player beAtt) {
		List<Attack_mail> r2 = null;
		if (beAtt == null)
			return r2;
		int pcid = beAtt.getPcid();
		r2 = MailFightJedis.getList(pcid);
		if (r2 == null) {
			// r2 = getByBeattpcid(beAtt.getPcid());
			// MailFightJedis.setListFight(r2);
		}
		return r2;
	}

	static void delMailFightBySql(List<String> timeList) {
		if (timeList != null && timeList.size() > 0) {
			try {
				Attack_mailDAO DAO = Attack_mailDAO();
				StringBuffer sb = new StringBuffer();
				for (String t : timeList) {
					String table = Attack_mailDAO.TABLE + t;
					sb.append("DROP TABLE `").append(table).append("`;");
				}
				String sql = sb.toString();
				DAO.execute(sql);
			} catch (Exception e) {
				log.info(e2s(e));
			}
		}
	}

	// ================== 数据库操作 end ==================
	// 创建新战报，默认的
	public static Attack_mail createNew(String attcid, int attPcid,
			String attPname, int beAttPcid, String beAttPname, long begin_time,
			long end_time, int star, int preGold, int preOil, int preAttRenown,
			int attRenown, int attGold, int attOil, boolean isHitBack,
			String clancid, String cname, int cicon, int offenHP, int offenAtt,
			String defccid, String defcname, int defcicon, int defenseHP,
			int defenseAtt, String attInfos, int beRewon, int egid, int num,
			String strAttHero) {

		int id = 0;
		Attack_mail r2 = Attack_mail.newAttack_mail(id, attcid, attPcid,
				attPname, beAttPcid, beAttPname, begin_time, end_time, star,
				preGold, preOil, preAttRenown, attRenown, attGold, attOil,
				isHitBack, clancid, cname, cicon, offenHP, offenAtt, defccid,
				defcname, defcicon, defenseHP, defenseAtt, attInfos, beRewon,
				egid, num, strAttHero);

		return r2;
	}

	// 创建对象并插入数据库
	public static Attack_mail createNewInsert(String attcid, Player att,
			Player beAtt, boolean isHitBack, int egid, int num) {
		Attack_mail r2 = null;
		if (att == null)
			return r2;

		int pcid = att.getPcid();
		String pname = att.getPname();
		String clancid = att.getClancid();
		if (clancid == null)
			clancid = "";
		int cicon = 0;
		String cname = "";
		int renown = att.getRenown();
		int gold = (int) att.getStored_gold();
		int oil = (int) att.getStored_oil();
		long begin_time = System.currentTimeMillis();
		long end_time = 0;
		int star = 0;
		int attRenown = 0;
		int attGold = 0;
		int attOil = 0;
		int offenHP = 0;
		int offenAtt = 0;
		Clan attClan = null;
		clancid = clancid.trim();
		attClan = ClanEntity.getClan(clancid);
		if (attClan != null) {
			cicon = att.getCicon();
			cname = att.getCname();

			offenHP = attClan.getPointhp();

			offenAtt = attClan.getPointatt();
		}

		int bpcid = 0;
		String bpname = "";
		int defenseHP = 0;
		int defenseAtt = 0;
		String defccid = "";
		String defcname = "";
		int defcicon = 0;
		int beRewon = 0;
		Clan beClan = null;
		if (beAtt != null) {
			bpcid = beAtt.getPcid();
			bpname = beAtt.getPname();
			defccid = beAtt.getClancid();
			if (defccid == null) {
				defccid = "";
			}
			defccid = defccid.trim();
			beClan = ClanEntity.getClan(defccid);
			if (beClan != null) {
				defcname = beAtt.getCname();
				defcicon = beAtt.getCicon();

				defenseHP = beClan.getPointhp();

				defenseAtt = beClan.getPointatt();
			}
		}

		String attInfos = "";
		String strAttHero = LogicHero.getStrHeros(att);
		if (strAttHero == null)
			strAttHero = "";

		r2 = createNew(attcid, pcid, pname, bpcid, bpname, begin_time,
				end_time, star, gold, oil, renown, attRenown, attGold, attOil,
				isHitBack, clancid, cname, cicon, offenHP, offenAtt, defccid,
				defcname, defcicon, defenseHP, defenseAtt, attInfos, beRewon,
				egid, num, strAttHero);

		insertMailFight(r2);
		return r2;
	}

	// 修改
	public static boolean upAttMail(Attack_mail att_mail, String attInfo,
			String clanArmys, int attGold, int attOil, int star, int attRenown) {
		boolean r2 = false;
		if (att_mail == null)
			return r2;
		att_mail.setAttinfos(attInfo);
		att_mail.setAttgold(attGold);
		att_mail.setAttoil(attOil);
		att_mail.setStar(star);
		att_mail.setAttrenown(attRenown);
		updateMailFight(att_mail);
		r2 = true;
		return r2;
	}

	public static boolean upAttMailHitBack(Attack_mail att_mail) {
		boolean r2 = false;
		if (att_mail == null)
			return r2;
		att_mail.setIshitback(true);
		updateMailFight(att_mail);
		r2 = true;
		return r2;
	}
	// ================= 服务器数据数据 ==>> 客服端数据 ==================
	public static String toAttMailInfos(Attack_mail attMail, NAttackInfos sua) {
		String r2 = "";
		if (attMail != null) {
			r2 = attMail.attInfos;
		}
		if (sua == null || sua.infos == null || sua.infos.size() == 0)
			return r2;
		StringBuffer sb = new StringBuffer();

		int last_Index = r2.lastIndexOf("]");
		if (last_Index != -1) {
			r2 = r2.substring(0, last_Index);
			sb.append(r2).append(",");
		} else {
			sb.append("[");
		}
		for (int i = 0, len = sua.infos.size(); i < len; ++i) {
			NAttackInfo item = sua.infos.get(i);
			if (item.type == 0)
				continue;
			sb.append("{");
			sb.append("\"type\"").append(":").append(item.type).append(",");
			sb.append("\"gid\"").append(":").append(item.gid).append(",");
			sb.append("\"bcid\"").append(":").append(item.bcid).append(",");
			sb.append("\"x\"").append(":").append(item.x).append(",");
			sb.append("\"y\"").append(":").append(item.y).append(",");
			sb.append("\"lvl\"").append(":").append(item.lvl).append(",");
			sb.append("\"diffTime\"").append(":").append(item.diffTime);
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

	public static NAttackInfo getSUAInfo(NAttackInfos infos, int type, int gid) {
		NAttackInfo r2 = null;
		if (infos != null && infos.infos != null) {
			for (NAttackInfo item : infos.infos) {
				if (item.type == type && item.gid == gid) {
					r2 = item;
					break;
				}
			}
		}
		return r2;
	}

	@SuppressWarnings("rawtypes")
	public static NAttackInfos toSUAInfos(Attack_mail attMail,
			boolean isRePlay, NAttackInfos r2) {
		if (r2 == null || attMail == null)
			r2 = new NAttackInfos();
		String infoStr = attMail.attInfos;
		if (infoStr == null || "".equals(infoStr))
			return r2;
		try {
			List list = JSON.parseList(infoStr);
			for (Object obj : list) {
				Map m = (Map) obj;
				int type = MapEx.getInt(m, "type");
				int gid = MapEx.getInt(m, "gid");
				int bcid = MapEx.getInt(m, "bcid");
				int lvl = MapEx.getInt(m, "lvl");
				double x = MapEx.getDouble(m, "x");
				double y = MapEx.getDouble(m, "y");
				long diffTime = MapEx.getLong(m, "diffTime");
				if (type <= 0)
					continue;
				NAttackInfo sua = getSUAInfo(r2, type, gid);
				if (!isRePlay && sua != null) {
					sua.num++;
				} else {
					sua = new NAttackInfo();
					sua.diffTime = diffTime;
					sua.type = type;
					sua.x = x;
					sua.y = y;
					sua.lvl = lvl;
					sua.num = 1;
					sua.gid = gid;
					sua.bcid = bcid;
					r2.infos.add(sua);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return r2;
	}

	// 数据库对象转为客服端下发对象
	public static NAttMail toSUAttMail(Attack_mail a_m) {
		if (a_m == null)
			return null;
		NAttMail r2 = new NAttMail();
		r2.mid = a_m.getId();
		r2.mcid = a_m.getAttcid();
		r2.attGold = a_m.attGold;
		r2.attName = a_m.attPname;
		r2.attOil = a_m.attOil;
		r2.attPid = a_m.getAttpcid();
		r2.attRenown = a_m.beRewon;
		r2.clanIcon = a_m.getCicon();
		r2.clancid = a_m.getClancid();
		r2.clanName = a_m.getCname();
		r2.preRenown = a_m.preAttRenown;
		r2.star = a_m.star;
		r2.isHitBack = a_m.isHitBack;
		r2.createTime = a_m.begin_time;
		r2.endTime = a_m.end_time;
		r2.attInfo = toSUAInfos(a_m, false, r2.attInfo);
		r2.egid = a_m.getEgid();
		r2.num = a_m.getNum();
		return r2;
	}

	// 把数据对象集合List转换成客户端对象集合List
	public static List<NAttMail> toSUAttMailList(List<Attack_mail> attMailList) {
		List<NAttMail> r2 = new ArrayList<NAttMail>();
		if (attMailList != null) {
			for (Attack_mail item : attMailList) {
				NAttMail sua = toSUAttMail(item);
				if (sua == null)
					continue;
				r2.add(sua);
			}
		}
		return r2;
	}

	// 取得角色的所有序列并转换成客户端需求的List集合对象
	public static NAttMails toSUAttMails(List<Attack_mail> attMailList,
			NAttMails suas) {
		if (attMailList == null || suas == null)
			return suas;
		suas.attMails = toSUAttMailList(attMailList);
		return suas;
	}
	// types end

}
