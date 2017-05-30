package com.sea.cache.process;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sea.cache.jedis.origin.JedisList;
import com.sea.content.Svc;
import com.sea.db.bean.Clan;
import com.sea.db.bean.Player;

public class ProAssistRank implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(ProAssistRank.class);

	static final String Assist_Rank_Player_All = "k:assist:rank:player:all";
	static final String Assist_Rank_Player_Week = "k:assist:rank:player:week";
	// =========== 缓存
	static final List<Player> listPlAll = new CopyOnWriteArrayList<Player>();
	static final List<Player> listPlWeek = new CopyOnWriteArrayList<Player>();

	// =========== Method

	public static void setRankPlayerAll(List<Player> origin, boolean isReset) {
		if (Svc.isEmpty(origin))
			return;
		if (isReset) {
			JedisList.delKeys(Assist_Rank_Player_All);
			List<String> list = new ArrayList<String>();
			for (Player renown : origin) {
				if (renown == null)
					continue;
				String pcid = String.valueOf(renown.getPcid());
				list.add(pcid);
			}
			JedisList.setValsInList(Assist_Rank_Player_All, list);
		}
		listPlAll.clear();
		listPlAll.addAll(origin);
	}

	static boolean isLoadPlAll = false;

	public static List<Player> getRankPlayerAll() {
		List<Player> result = new ArrayList<Player>();
		if (!isLoadPlAll && listPlAll.isEmpty()) {
			isLoadPlAll = true;
			List<String> list = JedisList.getListAll(Assist_Rank_Player_All);
			if (Svc.isEmpty(list))
				return listPlAll;
			for (String item : list) {
				int pcid = 0;
				try {
					pcid = Integer.parseInt(item);
				} catch (Exception e) {
				}
				Player r = ProPlayer.getPlayerByPcid(pcid);
				if (r == null)
					continue;
				listPlAll.add(r);
			}
		}
		result.addAll(listPlAll);
		return result;
	}

	public static void setRankPlayerWeek(List<Player> origin, boolean isReset) {

		if (Svc.isEmpty(origin))
			return;
		if (isReset) {
			JedisList.delKeys(Assist_Rank_Player_Week);
			List<String> list = new ArrayList<String>();
			for (Player renown : origin) {
				if (renown == null)
					continue;
				String pcid = String.valueOf(renown.getPcid());
				list.add(pcid);
			}
			JedisList.setValsInList(Assist_Rank_Player_Week, list);
		}
		listPlWeek.clear();
		listPlWeek.addAll(origin);
	}

	static boolean isLoadPlWek = false;

	public static List<Player> getRankPlayerWeek() {
		List<Player> result = new ArrayList<Player>();
		if (!isLoadPlWek && listPlWeek.isEmpty()) {
			isLoadPlWek = true;
			List<String> list = JedisList.getListAll(Assist_Rank_Player_Week);
			if (Svc.isEmpty(list))
				return listPlWeek;
			for (String item : list) {
				int pcid = 0;
				try {
					pcid = Integer.parseInt(item);
				} catch (Exception e) {
				}
				Player r = ProPlayer.getPlayerByPcid(pcid);
				if (r == null)
					continue;
				listPlWeek.add(r);
			}
		}
		result.addAll(listPlWeek);
		return result;
	}

	static final String Assist_Rank_Clan_All = "k:assist:rank:clan:all";
	static final String Assist_Rank_Clan_Week = "k:assist:rank:clan:week";

	static final List<Clan> listClAll = new CopyOnWriteArrayList<Clan>();
	static final List<Clan> listClWeek = new CopyOnWriteArrayList<Clan>();

	public static void setRankClanAll(List<Clan> origin, boolean isReset) {
		if (Svc.isEmpty(origin))
			return;
		if (isReset) {
			JedisList.delKeys(Assist_Rank_Clan_All);
			List<String> list = new ArrayList<String>();
			for (Clan c : origin) {
				if (c == null)
					continue;
				String ccid = c.getCcid();
				list.add(ccid);
			}
			JedisList.setValsInList(Assist_Rank_Clan_All, list);
		}
		listClAll.clear();
		listClAll.addAll(origin);
	}

	public static void setRankClanWeek(List<Clan> origin, boolean isReset) {
		if (Svc.isEmpty(origin))
			return;
		if (isReset) {
			JedisList.delKeys(Assist_Rank_Clan_Week);
			List<String> list = new ArrayList<String>();
			for (Clan c : origin) {
				if (c == null)
					continue;
				String ccid = c.getCcid();
				list.add(ccid);
			}
			JedisList.setValsInList(Assist_Rank_Clan_Week, list);
		}
		listClWeek.clear();
		listClWeek.addAll(origin);
	}

	static boolean isLoadClAll = false;

	public static List<Clan> getRankClanAll() {
		List<Clan> result = new ArrayList<Clan>();
		if (!isLoadClAll && listClAll.isEmpty()) {
			isLoadClAll = true;
			List<String> list = JedisList.getListAll(Assist_Rank_Clan_All);
			if (Svc.isEmpty(list))
				return listClAll;
			for (String item : list) {
				Clan r = ProClan.getClanByCcid(item);
				if (r == null)
					continue;
				listClAll.add(r);
			}
		}
		result.addAll(listClAll);
		return result;
	}

	static boolean isLoadClWek = false;

	public static List<Clan> getRankClanWeek() {
		List<Clan> result = new ArrayList<Clan>();
		if (!isLoadClWek && listClWeek.isEmpty()) {
			isLoadClWek = true;
			List<String> list = JedisList.getListAll(Assist_Rank_Clan_Week);
			if (Svc.isEmpty(list))
				return listClWeek;
			for (String item : list) {
				Clan r = ProClan.getClanByCcid(item);
				if (r == null)
					continue;
				listClWeek.add(r);
			}
		}
		result.addAll(listClWeek);
		return result;
	}
}
