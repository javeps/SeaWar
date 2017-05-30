package com.sea.logic.logicOperate;

import gen_b2g.serv.bean.ConstantType;
import gen_b2g.serv.bean.NChat;
import gen_b2g.serv.bean.NChats;
import gen_b2g.serv.bean.ReturnStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.util.NewMap;
import com.sea.db.bean.Chat;
import com.sea.db.bean.Player;
import com.sea.db.entity.ChatEntity;
import com.sea.db.entity.PlayerEntity;
import com.sea.handler.game.tcpGame.GameTcpHandle;
import com.sea.handler.game.tcpGame.GameTcpRepsonse;
import com.sea.logic.BackToClientLogicHandle;
import com.sea.logic.logicEntity.LEChat;
import com.sea.logic.session.LogicalSession;
import com.sea.logic.session.Session;
import com.sea.tools.UtileTools;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class LogicChat {

	static Log log = LogFactory.getLog(LogicChat.class);

	public static void sendChat(int fpcid, String fname, int type,
			String content, int toPcid, String toName, String ccid) {
		if (!ChatEntity.isHasChatType(type))
			return;
		ChatEntity.createNewInsert(fpcid, fname, type, content, toPcid, toName,
				ccid);
		LogicalSession.refreshLEChatTrueForOther(fpcid, type);
	}

	public static void sendChat(Player fromPlayer, NChat newChat) {
		if (newChat == null || fromPlayer == null)
			return;
		int type = newChat.type;
		String content = newChat.content;

		int fpcid = fromPlayer.getPcid();
		String fname = fromPlayer.getPname();
		String fccid = fromPlayer.getClancid();
		if (fccid == null)
			fccid = "";
		String toPname = newChat.toPName;
		int tpcid = 0;

		if (type == ConstantType.Type_Chat_Pri) {
			Player toPlayer = PlayerEntity.getPlayer(toPname);
			if (toPlayer == null)
				return;
			tpcid = toPlayer.getPcid();
			toPname = toPlayer.getPname();
			if (tpcid == fpcid)
				return;

		}
		sendChat(fpcid, fname, type, content, tpcid, toPname, fccid);
	}

	public static void sendSystemChatToPub(String title, String strCont) {
		if (title == null)
			return;

		if (strCont == null)
			return;

		if ("".equals(title.trim()))
			return;

		if ("".equals(strCont.trim()))
			return;

		int type = ConstantType.Type_Chat_Pub;
		sendChat(0, title, type, strCont, 0, "", "");
	}

	private static List<Chat> getListChat(final int type, Player p, LEChat le_) {
		List<Chat> r2 = null;
		if (p == null || le_ == null)
			return r2;

		List<Chat> origin = ChatEntity.getListChat(type, p);
		int maxLen = le_.getMaxLength(type);
		long last = le_.getLast(type);
		if (origin != null) {
			int len = origin.size();
			int diff = len - maxLen;
			if (diff > 0) {
				for (int i = 0; i < diff; i++) {
					Chat c = origin.get(i);
					ChatEntity.deleteChat(c);
				}
				// 注意文件可能会出错
				origin.subList(0, diff).clear();
			}
			len = origin.size();
			r2 = new ArrayList<Chat>();
			long tempLast = last;
			for (int i = 0; i < len; i++) {
				Chat c = origin.get(i);
				long cctime = c.getCreate_time();
				if (cctime > last) {
					if (tempLast < cctime) {
						tempLast = cctime;
					}
					if (last > 0) {
						int fpcid = c.getFromid();
						int curpcid = p.getPcid();
						if (fpcid == curpcid) {
							continue;
						}
					}
					r2.add(c);
				}
			}
			le_.setMaxId(type, tempLast);
		}
		return r2;
	}

	public static List<Chat> getListChat(LEChat le_, Player p) {
		List<Chat> r2 = new ArrayList<Chat>();
		if (p == null)
			return r2;
		if (le_ == null || !le_.getIssued())
			return r2;

		if (le_.isIssuedPub) {
			List<Chat> pub_ = getListChat(ConstantType.Type_Chat_Pub, p, le_);
			if (pub_ != null && pub_.size() > 0) {
				r2.addAll(pub_);
			}
		}
		if (le_.isIssuedPri) {
			List<Chat> pri_ = getListChat(ConstantType.Type_Chat_Pri, p, le_);
			if (pri_ != null && pri_.size() > 0) {
				r2.addAll(pri_);
			}
		}
		if (le_.isIssuedClan) {
			List<Chat> clan_ = getListChat(ConstantType.Type_Chat_Clan, p, le_);
			if (clan_ != null && clan_.size() > 0) {
				r2.addAll(clan_);
			}
		}
		le_.setIssuedAll(false);
		return r2;
	}

	private static LEChat getLeEntity(Player p) {
		if (p == null)
			return null;
		int pcid = p.getPcid();
		Session le = LogicalSession.getSessionByPcid(pcid);
		if (le == null)
			return null;
		return le.getLeChat();
	}

	public static void getNChats(Player p, NChats nchats) {
		LEChat le_ = getLeEntity(p);
		List<Chat> list = getListChat(le_, p);
		nchats = ChatEntity.toNChats(list, nchats);
	}

	// push info推送消息
	public static void issuedChat(GameTcpRepsonse ctx, final String methodName) {
		boolean isIssued = BackToClientLogicHandle.isCanIssued(methodName);
		if (!isIssued)
			return;

		if (ctx == null)
			return;
		try {
	
			int pcid = ctx.getPcid();
			if (pcid == 0)
				return;
			boolean isCan = ctx.session.getLeChat().getIssued();
			if (!isCan)
				return;
			NChats nchats = new NChats();
			ReturnStatus rst = new ReturnStatus();
			GameTcpHandle.service.onGetNChats(ctx, nchats, rst);
			Map result = new NewMap();
			result.put(0, 615325507);
			result.put(1, rst.toMap());
			result.put(-1051136915, nchats.toMap());
			ctx.send(result);
		} catch (Exception ex) {
			log.info(UtileTools.ex2s(ex));
		}

	}
}
