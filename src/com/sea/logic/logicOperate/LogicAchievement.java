package com.sea.logic.logicOperate;

import gen_b2g.serv.bean.NAchievement;
import gen_b2g.serv.bean.NAchievements;
import gen_b2g.serv.bean.NRewards;
import gen_b2g.serv.bean.ReturnStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.json.JSON;
import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.NewMap;
import com.sea.db.bean.Player;
import com.sea.handler.game.tcpGame.GameTcpHandle;
import com.sea.handler.game.tcpGame.GameTcpRepsonse;
import com.sea.logic.BackToClientLogicHandle;
import com.sea.logic.logicEntity.LEAchievement;
import com.sea.logic.session.LogicalSession;
import com.sea.logic.session.Session;
import com.sea.tools.UtileTools;
/**
 * 成就
 * @author Administrator
 *
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class LogicAchievement {

	static Log log = LogFactory.getLog(LogicAchievement.class);

	public static String toStrMarkInfo(List<NAchievement> list_) {
		String r2 = "";
		boolean isNull = ListEx.isEmpty(list_);
		if (isNull)
			return r2;
		StringBuffer sb = new StringBuffer("[");
		for (NAchievement item : list_) {
			sb.append("{");
			sb.append("\"loid\"").append(":").append(item.localId).append(",");
			sb.append("\"gid\"").append(":").append(item.gid).append(",");
			sb.append("\"val\"").append(":").append(item.val).append(",");
			sb.append("\"isDraw\"").append(":").append(item.isDraw);
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

	public static List<NAchievement> toListMarkInfo(String strInfo) {
		List<NAchievement> r2 = null;
		if (strInfo == null || "".equals(strInfo.trim()))
			return r2;
		try {
			List list = JSON.parseList(strInfo);
			r2 = new ArrayList<NAchievement>();
			for (Object obj : list) {
				Map m = (Map) obj;
				boolean isDraw = MapEx.getBoolean(m, "isDraw");
				int val = MapEx.getInt(m, "val");
				int id = MapEx.getInt(m, "loid");
				int gid = MapEx.getInt(m, "gid");
				NAchievement item = new NAchievement();
				item.isDraw = isDraw;
				item.localId = id;
				item.val = val;
				item.gid = gid;
				r2.add(item);
			}
		} catch (Exception e) {
			log.info(UtileTools.ex2s(e));
		}
		return r2;
	}

	public static List<NAchievement> getListMark(Player p) {
		List<NAchievement> r2 = null;
		if (p != null) {
			String markInfo = p.getMark();
			r2 = toListMarkInfo(markInfo);
		}
		return r2;
	}

	public static NAchievements toNAchievements(List<NAchievement> list_,
			NAchievements r2) {
		boolean isNull = ListEx.isEmpty(list_);
		if (isNull)
			return r2;
		r2.lists = list_;
		return r2;
	}

	private static LEAchievement getLeEntity(int pcid) {
		Session le = LogicalSession.getSessionByPcid(pcid);
		if (le == null)
			return null;
		return le.getLeMark();
	}

	public static void changeAchievements(Player p, long hasGold, long hasOil,
			int hasPoint, int breakNumHT, int winNum, int breakNumPJP) {
		if (p == null)
			return;
		int pcid = p.getPcid();
		LEAchievement le_ = getLeEntity(pcid);
		if (le_ == null)
			return;

		boolean isChanged = false;
		if (hasGold > 0) {
			int gidGid = 4;
			NAchievement nmark = le_.getNMarkByGid(gidGid);
			if (nmark != null && !nmark.isDraw) {
				nmark.val += hasGold;
				isChanged = true;
			}
		}
		if (hasOil > 0) {
			int gidOil = 6;
			NAchievement nmark = le_.getNMarkByGid(gidOil);
			if (nmark != null && !nmark.isDraw) {
				nmark.val += hasOil;
				isChanged = true;
			}
		}
		if (hasPoint > 0) {
			int gidPoint = 8;
			NAchievement nmark = le_.getNMarkByGid(gidPoint);
			if (nmark != null && !nmark.isDraw) {
				nmark.val += hasPoint;
				isChanged = true;
			}
		}
		if (breakNumHT > 0) {
			int gidBreakHT = 9;
			NAchievement nmark = le_.getNMarkByGid(gidBreakHT);
			if (nmark != null && !nmark.isDraw) {
				nmark.val += breakNumHT;
				isChanged = true;
			}
		}
		if (breakNumPJP > 0) {
			int gidWin = 10;
			NAchievement nmark = le_.getNMarkByGid(gidWin);
			if (nmark != null && !nmark.isDraw) {
				nmark.val += breakNumPJP;
				isChanged = true;
			}
		}
		if (winNum > 0) {
			int gidBreakPJP = 12;
			NAchievement nmark = le_.getNMarkByGid(gidBreakPJP);
			if (nmark != null && !nmark.isDraw) {
				nmark.val += winNum;
				isChanged = true;
			}
		}
		if (!isChanged)
			return;
		String markInfo = toStrMarkInfo(le_.getList());
		LogicPlayer.changeMarkInfo(p, markInfo);
	}

	public static void finishAchievementsGetRewards(Player p, int localCurId,
			int localNextId, NRewards lists) {
		if (p == null)
			return;
		int pcid = p.getPcid();
		LEAchievement le_ = getLeEntity(pcid);
		if (le_ == null)
			return;
		NAchievement nmark = le_.getNMarkByLid(localCurId);
		if (nmark == null || localCurId == 0)
			return;
		if (localNextId != 0 && localCurId >= localNextId)
			return;
		if (nmark.isDraw)
			return;
		nmark.localId = localNextId;
		nmark.isDraw = false;
		String markInfo = toStrMarkInfo(le_.getList());
		LogicPlayer.changeMarkInfo(p, markInfo);

		LogicReward.achievementReward(p,localCurId, lists);
	}

	public static void getNAchievements(Player p, NAchievements nach) {
		if (p == null)
			return;
		int pcid = p.getPcid();
		LEAchievement le_ = getLeEntity(pcid);
		if (le_ == null)
			return;
		List<NAchievement> list = le_.getList();
		if (list == null) {
			list = getListMark(p);
			if (list == null) {
				list = initListMark();
			}
			le_.setList(list);
		}
		le_.setIssued(false);
		toNAchievements(list, nach);
	}

	public static String initStrMarkInfo() {
		String r2 = "";
		List<NAchievement> list = initListMark();
		r2 = toStrMarkInfo(list);
		return r2;
	}

	public static List<NAchievement> initListMark() {
		List<NAchievement> r2 = new ArrayList<NAchievement>();
		int[] initId = { 1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34, 37, 38 };
		int len = initId.length;
		for (int i = 0; i < len; i++) {
			int loid＿ = initId[i];
			NAchievement item = newNMark(loid＿, i + 1);
			r2.add(item);
		}
		return r2;
	}

	public static NAchievement newNMark(int loid, int gid) {
		NAchievement r2 = new NAchievement();
		r2.isDraw = false;
		r2.localId = loid;
		r2.gid = gid;
		r2.val = 0;
		return r2;
	}

	// push info推送消息
	public static void issuedMarks(GameTcpRepsonse ctx, final String methodName) {

		boolean isIssued = BackToClientLogicHandle.isCanIssued(methodName);
		if (!isIssued)
			return;

		if (ctx == null)
			return;
		try {
			int pcid = ctx.getPcid();
			if (pcid == 0)
				return;
			
			boolean isCan = ctx.session.getLeMark().getIssued();
			if (!isCan)
				return;

			NAchievements marks = new NAchievements();
			ReturnStatus rst = new ReturnStatus();
			GameTcpHandle.service.onGetAchievements(ctx, marks, rst);
			Map result = new NewMap();
			result.put(0, -31856230);
			result.put(1, rst.toMap());
			result.put(103666502, marks.toMap());
			ctx.send(result);
		} catch (Exception ex) {
			log.info(UtileTools.ex2s(ex));
		}

	}
}
