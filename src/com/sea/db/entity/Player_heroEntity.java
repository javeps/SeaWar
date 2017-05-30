package com.sea.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import gen_b2g.serv.bean.ConstantType;
import gen_b2g.serv.bean.NHero;
import gen_b2g.serv.bean.NHeros;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.util.DateEx;
import com.sea.cache.process.ProHero;
import com.sea.db.bean.Player;
import com.sea.db.bean.Player_hero;
import com.sea.db.dao.Player_heroDAO;
import com.sea.db.internal.Player_heroInternal;
//import com.sea.content.AppContext;
import com.sea.logic.logicEntity.HeroMax;
import com.sea.logic.logicOperate.LogicAtomicInteger;
import com.sea.logic.logicOperate.LogicHero;

//seawar2_design - player_hero
@SuppressWarnings({ "static-access" })
public class Player_heroEntity extends Player_heroInternal {
	static Log log = LogFactory.getLog(Player_heroEntity.class);

	public static final Player_heroEntity my = new Player_heroEntity();

	static Player_heroDAO Player_heroDAO = null;

	public static Player_heroDAO Player_heroDAO() {
		if (Player_heroDAO == null)
			Player_heroDAO = new Player_heroDAO(com.sea.content.AppContext.ds());
		return Player_heroDAO;
	}

	// static Player_heroDAO Player_heroDAO99 = null;
	// public static Player_heroDAO Player_heroDAO99() {
	// if( Player_heroDAO99 == null)
	// Player_heroDAO99 = new Player_heroDAO(com.sea.content.AppContext.ds99());
	// return Player_heroDAO99;
	// }

	public static void insertMmTry(final Player_hero player_hero) {
		Player_heroDAO DAO = Player_heroDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asynchronousInsert(player_hero, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin

	public static void insertHero(Player_hero h) {
		if (h != null) {
			ProHero.inBuild(h);
		}
	}

	public static void updateHero(Player_hero h) {
		if (h != null) {
			ProHero.upBuild(h);
		}
	}

	public static void deleteHero(Player_hero h) {
		if (h != null) {
			ProHero.deletePB(h);
		}
	}

	public static List<Player_hero> getHerosByPcid(int pcid, String heroes) {
		List<Player_hero> r = null;
		if (pcid == 0)
			return r;
		r = ProHero.getListByPcid(pcid, heroes);
		return r;
	}

	public static List<Player_hero> getHerosByPlayer(Player p) {
		List<Player_hero> r = null;
		if (p == null)
			return r;
		int pcid = p.getPcid();
		String heroes = p.getHeroes();
		r = getHerosByPcid(pcid, heroes);
		return r;
	}

	public static Player_hero getHeroBy(int pcid, int hgid) {
		Player_hero r = ProHero.getHeroByPcidHgid(pcid, hgid);
		return r;
	}

	// =========== method

	public static boolean isHasHero(Player p, int hgid) {
		boolean r = false;
		if (p == null)
			return r;
		int pcid = p.getPcid();
		if (pcid == 0)
			return r;
		r = ProHero.isHasHero(pcid, hgid);
		return r;
	}

	public static Player_hero createNew(int hcid, int pcid, String pname,
			int hgid, int maxHP, int maxAttSpeed, int maxDamage) {
		String hname = LogicHero.getHeroName(hgid);
		int addAttSpeed = 0;
		int addHP = 0;
		int addDamage = 0;
		int statues = ConstantType.Type_Hero_Live;
		long createTime = System.currentTimeMillis();
		long deadTime = 0;
		int skillGid = 0;
		int fpos = 0;
		Player_hero r = Player_hero.newPlayer_hero(0, hcid, pcid, pname, hname,
				hgid, addDamage, maxDamage, addHP, maxHP, addAttSpeed,
				maxAttSpeed, statues, createTime, deadTime, skillGid, fpos);
		return r;
	}

	public static void createNewInsert(Player p, int hgid) {
		HeroMax le = LogicHero.getMaxByPlayer(p, hgid);
		if (le == null || le.getMaxHP() <= 0)
			return;

		int maxHP = le.getMaxHP();
		int maxAttSpeed = le.getMaxSpeed();
		int maxDamage = le.getMaxDam();

		int pcid = p.getPcid();
		String pname = p.getPname();
		int hcid = LogicAtomicInteger
				.getValByType(LogicAtomicInteger.Type_Hcid);
		Player_hero r = createNew(hcid, pcid, pname, hgid, maxHP, maxAttSpeed,
				maxDamage);

		insertHero(r);
	}

	// 复活英雄
	public static void liveHero(Player p, int hgid) {
		if (p == null)
			return;
		int pcid = p.getPcid();
		Player_hero r = ProHero.getHeroByPcidHgid(pcid, hgid);
		if (r == null)
			return;
		r.setDeadtime(0);
		r.setStatues(ConstantType.Type_Hero_Live);
		updateHero(r);
	}

	// 英雄死亡
	public static void deadHero(Player p, int hgid) {
		if (p == null)
			return;
		int pcid = p.getPcid();
		Player_hero r = ProHero.getHeroByPcidHgid(pcid, hgid);
		if (r == null)
			return;
		long deatTime = System.currentTimeMillis();
		r.setDeadtime(deatTime);
		r.setStatues(ConstantType.Type_Hero_Dead);
		updateHero(r);
	}

	// =================== 转变问网路对象
	public static NHero getNHero(Player_hero origin) {
		NHero r = null;
		if (origin == null || origin.getHcid() <= 0)
			return r;
		r = new NHero();
		r.damage = origin.getAdddamage();
		r.deadTime = origin.getDeadtime();
		r.hcid = origin.getHcid();
		r.hgid = origin.getHgid();
		r.hp = origin.getAddhp();
		r.maxDamage = origin.getMaxdamage();
		r.maxHp = origin.getMaxhp();
		r.maxSpeed = origin.getMaxattspeed();
		r.skillGid = origin.getSkillgid();
		r.speed = origin.getAddattspeed();
		r.status = origin.getStatues();
		r.fightPos = origin.getFpos();
		return r;
	}

	private static long Diff_Time_Live = DateEx.TIME_MINUTE * 40;

	public static List<NHero> getNHeroList(List<Player_hero> list) {
		List<NHero> r = null;
		if (list == null || list.isEmpty())
			return r;
		r = new ArrayList<NHero>();
		long now_time = System.currentTimeMillis();
		for (Player_hero item : list) {
			if (item == null)
				continue;
			long deadTime = item.getDeadtime();
			long diff = deadTime + Diff_Time_Live;
			if (deadTime > 0 && now_time > diff) {
				item.setDeadtime(0);
			}
			NHero nh = getNHero(item);
			if (nh == null)
				continue;
			r.add(nh);
		}
		return r;
	}

	public static void getNHeros(List<Player_hero> list, NHeros heros) {
		List<NHero> list_hero = getNHeroList(list);
		if (list_hero == null || list_hero.isEmpty())
			return;
		heros.lists = list_hero;
	}

	public static List<Player_hero> getHerosBy(Player beAtt, NHeros heros) {
		List<Player_hero> r = null;
		if (heros == null || heros.lists == null || heros.lists.isEmpty())
			return r;
		r = new ArrayList<Player_hero>();
		int pcid = 0;
		String pname = "";
		if (beAtt != null) {
			pcid = beAtt.getPcid();
			pname = beAtt.getPname();
		}
		for (NHero item : heros.lists) {
			Player_hero v = Player_hero.newPlayer_hero(0, item.hcid, pcid,
					pname, "", item.hgid, item.damage, item.maxDamage, item.hp,
					item.maxHp, item.speed, item.maxSpeed, item.status, 0l,
					item.deadTime, item.skillGid, item.fightPos);
			r.add(v);
		}
		return r;
	}

	// types end

}
