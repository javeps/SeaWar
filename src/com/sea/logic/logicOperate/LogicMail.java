package com.sea.logic.logicOperate;

import gen_b2g.serv.bean.ConstantType;
import gen_b2g.serv.bean.NAttMails;
import gen_b2g.serv.bean.NMail;
import gen_b2g.serv.bean.NMails;
import gen_b2g.serv.bean.ReturnStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.lang.StrEx;
import com.bowlong.util.NewMap;
import com.sea.content.Svc;
import com.sea.db.bean.Attack_mail;
import com.sea.db.bean.Player;
import com.sea.db.bean.Player_mail;
import com.sea.db.entity.Attack_mailEntity;
import com.sea.db.entity.PlayerEntity;
import com.sea.db.entity.Player_mailEntity;
import com.sea.handler.game.tcpGame.GameTcpHandle;
import com.sea.handler.game.tcpGame.GameTcpRepsonse;
import com.sea.json.originData.LanguageJson;
import com.sea.logic.BackToClientLogicHandle;
import com.sea.logic.logicEntity.LEMail;
import com.sea.logic.session.LogicalSession;
import com.sea.logic.session.Session;
import com.sea.tools.UtileTools;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class LogicMail implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(LogicMail.class);

	public static final int Type_Mail_Att = 1;
	public static final int Type_Mail_Player = 2;

	// -1是靠前，1是靠后
	private static void sortNMailAttByTime(List<Attack_mail> origin) {
		if (origin == null)
			return;
		Comparator<Attack_mail> com_p_p = new Comparator<Attack_mail>() {
			@Override
			public int compare(Attack_mail o1, Attack_mail o2) {
				long bo1 = o1.getBegin_time();
				long bo2 = o2.getBegin_time();
				if (bo1 > bo2)
					return -1;
				else if (bo1 < bo2)
					return 1;
				return 0;
			}
		};
		Collections.sort(origin, com_p_p);
	}

	private static int getIndexAttBy(List<Attack_mail> origin, String mcid) {
		int r2 = -1;
		if (origin == null)
			return r2;
		int len_ = origin.size();
		for (int i = 0; i < len_; i++) {
			Attack_mail item_ = origin.get(i);
			String item_mcid = item_.getAttcid();
			if (item_mcid.equals(mcid)) {
				r2 = i;
				break;
			}
		}
		return r2;
	}

	public static void getNMailAtts(Player p, NAttMails nattmail) {
		LEMail le_ = getLeEntity(p);
		if (le_ == null)
			return;
		boolean isCanSued_ = le_.getIssuedAtt();
		if (!isCanSued_)
			return;
		le_.setIssuedFalse(Type_Mail_Att);
		List<Attack_mail> list_ = null;
		List<Attack_mail> origin = Attack_mailEntity.getAttMails(p);
		if (Svc.isEmpty(origin))
			return;

		int maxLen = le_.getMaxLength(Type_Mail_Att);
		int allsize = origin.size();
		sortNMailAttByTime(origin);
		List<Attack_mail> result = new ArrayList<Attack_mail>();

		for (int i = 0; i < allsize; i++) {
			Attack_mail item = origin.get(i);
			if (i < maxLen) {
				result.add(item);
			} else {
				Attack_mailEntity.deleteMailFight(item);
			}
		}

		int index_ = -1;
		Attack_mail max_ = le_.getMaxMailAtt();
		if (max_ != null) {
			String mcid = max_.getAttcid();
			index_ = getIndexAttBy(result, mcid);
		}
		if (index_ != 0) {
			max_ = result.get(0);
			le_.setMaxMailAtt(max_);
		}
		if (index_ < 0) {
			list_ = result;
		} else if (index_ > 0) {
			list_ = result.subList(0, index_);
		}
		Attack_mailEntity.toSUAttMails(list_, nattmail);
	}

	// -1是靠前，1是靠后
	private static void sortNMailPlayer(List<Player_mail> origin) {
		if (origin == null)
			return;
		Comparator<Player_mail> com_p_p = new Comparator<Player_mail>() {
			@Override
			public int compare(Player_mail o1, Player_mail o2) {
				// int o1type = o1.getType();
				// int o2type = o2.getType();
				// if (o1type > o2type)
				// return 1;
				// else if (o1type < o2type)
				// return -1;

				long o1time = o1.getCreate_time();
				long o2time = o2.getCreate_time();
				if (o1time > o2time)
					return -1;
				else if (o1time < o2time)
					return 1;
				return 0;
			}
		};
		Collections.sort(origin, com_p_p);
	}

	public static void sendSystemMailToPlayer(String pname, String strTit,
			String content) {
		if (content == null)
			return;
		if ("".equals(content.toString()))
			return;
		if (strTit == null)
			strTit = "";
		Player p = PlayerEntity.getPlayer(pname);
		sendSystemMailToPlayer(p, strTit, content);
	}

	public static void sendSystemMailToPlayer(Player p, String strTit,
			String content) {
		if (p == null || content == null)
			return;
		if ("".equals(content.toString()))
			return;

		if (strTit == null)
			strTit = "";
		else if (!"".equals(strTit.trim()))
			strTit = "-" + strTit;

		String strChn = p.getChannel();
		String strTTmp = LanguageJson.getStrValByChn(strChn,
				LanguageJson.Mail_title_sys);
		strTit = StrEx.fmt(strTTmp, strTit);

		String title = strTit;
		int pcid = p.getPcid();
		int type = ConstantType.Type_Mail_System_Player;
		String pname = p.getPname();
		Player_mailEntity.createNewInsert(type, title, content, 0, "", pcid,
				pname);

		LogicalSession.refreshLEMailTrueForOther(0, type, pcid);
	}

	public static void sendSystemMailToAllPlayer(String strTit, String content) {
		if (content == null || "".equals(content.toString()))
			return;

		if (strTit == null)
			strTit = "";
		else if (!"".equals(strTit.trim()))
			strTit = "-" + strTit;

		String strChn = "";
		String strTTmp = LanguageJson.getStrValByChn(strChn,
				LanguageJson.Mail_title_sys);
		strTit = StrEx.fmt(strTTmp, strTit);

		int type = ConstantType.Type_Mail_System;
		String title = strTit;
		int pcid = 0;
		String pname = "";
		Player_mailEntity.createNewInsert(type, title, content, 0, "", pcid,
				pname);

		LogicalSession.refreshLEMailTrueForOther(0, type, pcid);
	}

	public static boolean sendMail(Player p, NMail newMail) {
		if (p == null)
			return false;
		if (newMail == null)
			return false;
		int type_ = newMail.type;
		if (type_ < 0 || type_ > 5)
			return false;
		int fromPcid = p.getPcid();
		String pname = p.getPname();
		int toId = 0;
		String toName = "";
		boolean isTopname = false;
		if (type_ == ConstantType.Type_Mail_Player) {
			toName = newMail.toPName;
			isTopname = true;
		}

		if (isTopname) {
			Player toPlayer = PlayerEntity.getPlayer(toName);
			if (toPlayer != null)
				toId = toPlayer.getPcid();

			if (toId == 0 && type_ == ConstantType.Type_Mail_Player)
				return false;
		}

		Player_mailEntity.createNewInsert(type_, newMail.title,
				newMail.content, fromPcid, pname, toId, toName);

		LogicalSession.refreshLEMailTrueForOther(fromPcid, type_, toId);

		return true;
	}

	private static int getIndexBy(List<Player_mail> origin, int mailCid) {
		int r2 = -1;
		if (origin == null)
			return r2;
		int len_ = origin.size();
		for (int i = 0; i < len_; i++) {
			Player_mail item_ = origin.get(i);
			int mcid = item_.getMcid();
			if (mailCid == mcid) {
				r2 = i;
				break;
			}
		}
		return r2;
	}

	private static LEMail getLeEntity(Player p) {
		if (p == null)
			return null;
		int pcid = p.getPcid();
		Session le = LogicalSession.getSessionByPcid(pcid);
		if (le == null)
			return null;
		return le.getLeMail();
	}

	public static void getNMails(Player p, NMails nmails) {
		LEMail le_ = getLeEntity(p);
		if (le_ == null)
			return;
		boolean isCanSued_ = le_.getIssuedPlayer();
		if (!isCanSued_)
			return;
		le_.setIssuedFalse(Type_Mail_Player);
		List<Player_mail> list_ = null;
		List<Player_mail> origin = Player_mailEntity.getListMailsToPlayer(p);
		if (Svc.isEmpty(origin))
			return;

		int maxLen = le_.getMaxLength(Type_Mail_Player);
		int allsize = origin.size();
		sortNMailPlayer(origin);

		List<Player_mail> result = new ArrayList<Player_mail>();
		for (int i = 0; i < allsize; i++) {
			Player_mail item = origin.get(i);
			if (i < maxLen) {
				result.add(item);
			} else {
				Player_mailEntity.playerMailDelete(item);
			}
		}

		Player_mail recordMaxMail_ = le_.getMaxMailPlayer();
		int index_ = -1;
		if (recordMaxMail_ != null) {
			int mmcid = recordMaxMail_.getMcid();
			index_ = getIndexBy(result, mmcid);
		}
		if (index_ != 0) {
			recordMaxMail_ = result.get(0);
			le_.setMaxMailPlayer(recordMaxMail_);
		}
		if (index_ < 0) {
			list_ = result;
		} else if (index_ > 0) {
			list_ = result.subList(0, index_);
		}
		Player_mailEntity.toNMails(list_, nmails);
	}

	public static void readMail(Player p, int mcid) {
		if (p == null)
			return;
		int pcid = p.getPcid();
		int ptype = p.getType();
		Player_mail mail_ = Player_mailEntity.getMailByMcid(pcid, mcid, ptype);
		if (mail_ == null)
			return;
		if (mail_.getType() == ConstantType.Type_Mail_System)
			return;

		boolean isRead = mail_.getIsread();
		if (!isRead) {
			mail_.setIsread(true);
			Player_mailEntity.playerMailUpdate(mail_);
		}
	}

	// push info推送消息
	private static void issuedMailPlayer(GameTcpRepsonse ctx,
			final String methodName) {

		boolean isIssued = BackToClientLogicHandle.isCanIssued(methodName);
		if (!isIssued)
			return;

		if (ctx == null)
			return;
		try {
			int pcid = ctx.getPcid();
			if (pcid == 0)
				return;
			boolean isCan = ctx.session.getLeMail().getIssuedPlayer();
			if (!isCan)
				return;
			NMails nmails = new NMails();
			ReturnStatus rst = new ReturnStatus();
			GameTcpHandle.service.onGetNMails(ctx, nmails, rst);
			Map result = new NewMap();
			result.put(0, 624359620);
			result.put(1, rst.toMap());
			result.put(-1042102802, nmails.toMap());
			ctx.send(result);
		} catch (Exception ex) {
			log.info(UtileTools.ex2s(ex));
		}

	}

	// push info推送消息
	private static void issuedMailAtt(GameTcpRepsonse ctx,
			final String methodName) {

		boolean isIssued = BackToClientLogicHandle.isCanIssued(methodName);
		if (!isIssued)
			return;

		if (ctx == null)
			return;
		try {
			int pcid = ctx.getPcid();
			if (pcid == 0)
				return;
			boolean isCan = ctx.session.getLeMail().getIssuedAtt();
			if (!isCan)
				return;
			NAttMails attMails = new NAttMails();
			ReturnStatus rst = new ReturnStatus();
			GameTcpHandle.service.onGetAttMails(ctx, attMails, rst);
			Map result = new NewMap();
			result.put(0, -1696857871);
			result.put(1, rst.toMap());
			result.put(520209275, attMails.toMap());
			ctx.send(result);
		} catch (Exception ex) {
			log.info(UtileTools.ex2s(ex));
		}

	}

	public static void issuedMail(GameTcpRepsonse ctx, final String methodName) {
		issuedMailAtt(ctx, methodName);
		issuedMailPlayer(ctx, methodName);
	}
}
