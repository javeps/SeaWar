package com.sea.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import gen_b2g.serv.bean.ConstantType;
import gen_b2g.serv.bean.NMail;
import gen_b2g.serv.bean.NMails;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.util.ListEx;
import com.sea.cache.jedis.game.MailJedis;
import com.sea.db.bean.Player;
import com.sea.db.bean.Player_mail;
import com.sea.db.dao.Attack_mailDAO;
import com.sea.db.dao.Player_mailDAO;
import com.sea.db.internal.Player_mailInternal;
//import com.sea.content.AppContext;
import com.sea.logic.logicOperate.LogicAtomicInteger;

//seawar_design - player_mail
@SuppressWarnings({ "static-access" })
public class Player_mailEntity extends Player_mailInternal {
	static Log log = LogFactory.getLog(Player_mailEntity.class);

	public static final Player_mailEntity my = new Player_mailEntity();

	static Player_mailDAO Player_mailDAO = null;

	public static Player_mailDAO Player_mailDAO() {
		if (Player_mailDAO == null)
			Player_mailDAO = new Player_mailDAO(com.sea.content.AppContext.ds());
		return Player_mailDAO;
	}

	// static Player_mailDAO Player_mailDAO99 = null;
	// public static Player_mailDAO Player_mailDAO99() {
	// if( Player_mailDAO99 == null)
	// Player_mailDAO99 = new Player_mailDAO(com.sea.content.AppContext.ds99());
	// return Player_mailDAO99;
	// }

	public static void insertMmTry(final Player_mail player_mail) {
		Player_mailDAO DAO = Player_mailDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asynchronousInsert(player_mail, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin
	// ================== 数据库操作 begin ==================
	// 插入数据
	public static void playerMailInsert(Player_mail r2) {
		if (r2 != null) {
			// r2 = r2.insert();
			MailJedis.setDataMail(r2);
		}
	}

	// 更新数据
	public static void playerMailUpdate(Player_mail r2) {
		if (r2 != null) {
			// r2 = r2.update();
			MailJedis.updateMail(r2);
		}
	}

	// 删除数据
	public static void playerMailDelete(Player_mail origin) {
		if (origin != null) {
			// int result = origin.delete();
			MailJedis.deleteMail(origin);
		}
	}

	public static Player_mail getMailByMcid(int pcid, int mcid, int ptype) {
		Player_mail r2 = null;
		r2 = MailJedis.getMail(pcid, mcid, ptype);
		if (r2 == null) {
			// r2 = getByMcid(mcid);
			// MailJedis.setHash(r2);
		}
		return r2;
	}

	public static List<Player_mail> getListSystem() {
		List<Player_mail> list = null;
		list = MailJedis.getListSystem();
		if (list == null || list.size() <= 0) {
			// list = getByType(ConstantType.Type_Mail_System);
			// MailJedis.setHash(list);
		}
		return list;
	}

	public static List<Player_mail> getListServer() {
		List<Player_mail> list = null;
		list = MailJedis.getListServer();
		if (list == null || list.size() <= 0) {
			// list = getByType(ConstantType.Type_Mail_Server);
			// MailJedis.setHash(list);
		}
		return list;
	}

	public static List<Player_mail> getListToPlayer(int pcid) {
		List<Player_mail> list = null;
		list = MailJedis.getListByPlayer(pcid);
		return list;
	}

	// ================== 数据库操作 end ==================

	public static Player_mail createNew(int type, String title, String content,
			long create_time, int fromId, String fromName, int toId,
			String toName) {
		boolean isRead = false;
		int mcid = LogicAtomicInteger
				.getValByType(LogicAtomicInteger.Type_Mcid);
		Player_mail r2 = Player_mail.newPlayer_mail(0, mcid, type, title,
				content, create_time, fromId, fromName, toId, toName, isRead);
		return r2;
	}

	public static Player_mail createNewInsert(int type, String title,
			String content, int fromId, String fromName, int toId, String toName) {
		long create_time = System.currentTimeMillis();
		Player_mail r2 = createNew(type, title, content, create_time, fromId,
				fromName, toId, toName);
		playerMailInsert(r2);
		return r2;
	}

	public static List<Player_mail> getListMailsToPlayer(Player p) {
		List<Player_mail> r2 = null;
		if (p == null)
			return r2;
		int pcid = p.getPcid();
		int type = p.getType();
		List<Player_mail> list_p = getListToPlayer(pcid);
		r2 = new ArrayList<Player_mail>();
		if (!ListEx.isEmpty(list_p))
			r2.addAll(list_p);

		if (type == ConstantType.Type_User_GM) {
			List<Player_mail> list_ser = getListServer();
			if (!ListEx.isEmpty(list_ser))
				r2.addAll(list_ser);
			return r2;
		}

		List<Player_mail> list_sys = getListSystem();
		if (!ListEx.isEmpty(list_sys))
			r2.addAll(list_sys);

		return r2;
	}

	// ================= 服务器数据数据 ==>> 客服端数据 ==================
	// 数据库对象，转换为客户端数据
	public static NMail toNMail(Player_mail e_) {
		if (e_ == null)
			return null;
		NMail r2 = new NMail();
		r2.mid = e_.getMcid();
		r2.isRead = e_.getIsread();
		r2.content = e_.content;
		r2.title = e_.title;
		r2.toPName = e_.toName;
		r2.type = e_.type;
		r2.createTime = e_.create_time;
		r2.fromPName = e_.fromName;
		return r2;
	}

	// 把数据对象集合List转换成客户端对象集合List
	public static List<NMail> toNMailList(List<Player_mail> list_) {
		List<NMail> r2 = new ArrayList<NMail>();
		if (list_ != null) {
			for (Player_mail item : list_) {
				NMail sup_a = toNMail(item);
				if (sup_a == null)
					continue;
				r2.add(sup_a);
			}
		}
		return r2;
	}

	// 取得角色的所有序列并转换成客户端需求的List集合对象
	public static NMails toNMails(List<Player_mail> paList, NMails supas) {
		if (paList == null)
			return supas;
		if (supas == null)
			supas = new NMails();
		supas.lists = toNMailList(paList);
		return supas;
	}

	// ===== 删除邮件

	static void delMailPlayerBySql(List<String> timeList) {
		if (timeList != null && timeList.size() > 0) {
			try {
				Player_mailDAO DAO = Player_mailDAO();
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
	// types end

}
