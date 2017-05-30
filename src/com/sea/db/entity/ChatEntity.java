package com.sea.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import gen_b2g.serv.bean.ConstantType;
import gen_b2g.serv.bean.NChat;
import gen_b2g.serv.bean.NChats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sea.cache.process.ProChat;
import com.sea.db.bean.Chat;
import com.sea.db.bean.Player;
import com.sea.db.dao.ChatDAO;
import com.sea.db.internal.ChatInternal;
//import com.sea.content.AppContext;
import com.sea.logic.logicOperate.LogicAtomicInteger;

//seawar_design - chat
@SuppressWarnings({ "static-access" })
public class ChatEntity extends ChatInternal {
	static Log log = LogFactory.getLog(ChatEntity.class);

	public static final ChatEntity my = new ChatEntity();

	static ChatDAO ChatDAO = null;

	public static ChatDAO ChatDAO() {
		if (ChatDAO == null)
			ChatDAO = new ChatDAO(com.sea.content.AppContext.ds());
		return ChatDAO;
	}

	// static ChatDAO ChatDAO99 = null;
	// public static ChatDAO ChatDAO99() {
	// if( ChatDAO99 == null)
	// ChatDAO99 = new ChatDAO(com.sea.content.AppContext.ds99());
	// return ChatDAO99;
	// }

	public static void insertMmTry(final Chat chat) {
		ChatDAO DAO = ChatDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asynchronousInsert(chat, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	static ChatDAO ChatLogDao = null;

	public static ChatDAO ChatLogDao() {
		if (ChatLogDao == null)
			ChatLogDao = new ChatDAO(com.sea.content.AppContext.logDS());
		return ChatLogDao;
	}

	public static void insertChatLog(final Chat chat) {
		ChatDAO DAO = ChatLogDao();
		String TABLENAME2 = DAO.TABLEDD();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asynchronousInsert(chat, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin
	// ================== 数据库操作 begin ==================
	// 插入数据
	public static void insertChat(Chat r2) {
		if (r2 != null) {
			ProChat.setUpBuild(r2);
		}
	}

	// 更新数据
	public static void updateChat(Chat r2) {
		if (r2 != null) {
			ProChat.setUpBuild(r2);
		}
	}

	// 删除数据
	public static void deleteChat(Chat origin) {
		if (origin != null) {
			ProChat.deleteDataChat(origin);
		}
	}

	public static List<Chat> getListAll() {
		List<Chat> r = null;
		r = ProChat.getList();
		return r;
	}

	public static List<Chat> getList(int pcid, int type, String ccid) {
		List<Chat> r = null;
		r = ProChat.getList(pcid, type, ccid);
		return r;
	}

	public static List<Chat> getListChat(final int type, Player p) {
		List<Chat> r2 = null;
		if (p == null)
			return r2;
		int pcid = p.getPcid();
		String ccid = p.getClancid();
		if (ccid == null)
			ccid = "";
		r2 = getList(pcid, type, ccid);
		sortListChat(r2);
		return r2;
	}

	// ================== 数据库操作 end ==================

	public static Chat createNew(int ccid, int type, String content,
			long create_time, int fromId, String fromName, int toId,
			String toName, String clancid) {
		Chat r2 = Chat.newChat(0, ccid, type, content, create_time, fromId,
				fromName, toId, toName, clancid);
		return r2;
	}

	public static void createNewInsert(Player p, int type, String content,
			int toId, String toName) {
		if (p == null)
			return;
		int fpcid = p.getPcid();
		String fname = p.getPname();
		String clancid = p.getClancid();
		createNewInsert(fpcid, fname, type, content, toId, toName, clancid);
	}

	public static void createNewInsert(int fpcid, String fname, int type,
			String content, int toId, String toName, String clancid) {
		int ccid = LogicAtomicInteger
				.getValByType(LogicAtomicInteger.Type_Chatcid);
		long time_now = System.currentTimeMillis();
		Chat c_ = createNew(ccid, type, content, time_now, fpcid, fname, toId,
				toName, clancid);
		insertChat(c_);
	}

	public static boolean isHasChatType(final int type) {
		boolean r2 = false;
		switch (type) {
		case ConstantType.Type_Chat_Pub:
		case ConstantType.Type_Chat_Pri:
		case ConstantType.Type_Chat_Clan:
			r2 = true;
			break;
		default:
			break;
		}
		return r2;
	}

	private static void sortListChat(List<Chat> list_) {
		if (list_ == null)
			return;
		Comparator<Chat> com_ = new Comparator<Chat>() {
			@Override
			public int compare(Chat o1, Chat o2) {
				long o1cc = o1.getCreate_time();
				long o2cc = o2.getCreate_time();
				if (o1cc > o2cc)
					return 1;
				else if (o1cc < o2cc)
					return -1;
				return 0;
			}
		};
		Collections.sort(list_, com_);
	}

	// ================= 服务器数据数据 ==>> 客服端数据 ==================
	// 数据库对象，转换为客户端数据
	public static NChat toNChat(Chat c_) {
		if (c_ == null)
			return null;
		NChat r2 = new NChat();
		r2.ncid = c_.getId();
		r2.uuid = c_.getCcid();
		r2.content = c_.content;
		r2.toPName = c_.toName;
		r2.type = c_.type;
		r2.createTime = c_.create_time;
		r2.fromPName = c_.fromName;
		return r2;
	}

	// 把数据对象集合List转换成客户端对象集合List
	public static List<NChat> toNChatList(List<Chat> list_) {
		List<NChat> r2 = new ArrayList<NChat>();
		if (list_ != null) {
			for (Chat item : list_) {
				NChat sup_a = toNChat(item);
				if (sup_a == null)
					continue;
				r2.add(sup_a);
			}
		}
		return r2;
	}

	// 取得角色的所有序列并转换成客户端需求的List集合对象
	public static NChats toNChats(List<Chat> paList, NChats supas) {
		if (paList == null)
			return supas;
		if (supas == null)
			supas = new NChats();
		supas.lists = toNChatList(paList);
		return supas;
	}
	// types end

}
