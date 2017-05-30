package com.sea.logic.logicOperate;

import gen_b2g.serv.bean.NHero;
import gen_b2g.serv.bean.NHeros;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sea.db.bean.Player;
import com.sea.db.bean.Player_hero;
import com.sea.db.entity.Attack_defense_infoEntity;
import com.sea.db.entity.PlayerEntity;
import com.sea.db.entity.Player_heroEntity;
import com.sea.json.originData.BaseHeroJson;
import com.sea.logic.logicEntity.HeroMax;
import com.sea.logic.session.LogicalEvent;
import com.sea.logic.session.LogicalSession;

public class LogicHero implements Serializable {

	static Log log = LogFactory.getLog(LogicHero.class);

	private static final long serialVersionUID = 1L;

	public static final int maxLvl = 150;

	public static final int Hero_Gid_LandDragon = 1020;// 大地暴龙
	public static final int Hero_Gid_ElementsNaga = 1021;// 元素纳迦
	public static final int Hero_Gid_Machinery = 1022;// X号-3
	public static final int Hero_Gid_Sprite = 1023;// 精灵
	public static final int Hero_Gid_Fire = 1024;// 火精灵
	public static final int Hero_Gid_Jelly = 1025;// 霜火水母

	static public final int Gem_LandDragon = 380;// 大地暴龙
	static public final int Gem_ElementsNaga = 860;// 元素纳迦
	static public final int Gem_Machinery = 1980;// X号-3
	static public final int Gem_Sprite = 2280;// 精灵
	static public final int Gem_Fire = 2999;// 火精灵
	static public final int Gem_Jelly = 3680;// 霜火水母

	public static String getHeroName(int hgid) {
		return BaseHeroJson.getHeroName(hgid);
	}

	public static HeroMax getMaxByLvl(int hgid, int lvl) {
		lvl = lvl >= maxLvl ? maxLvl : lvl;

		HeroMax r = new HeroMax();
		r.setHgid(hgid);
		int hp_ = BaseHeroJson.getHP(hgid) * lvl;
		int dam_ = BaseHeroJson.getDam(hgid) * lvl;

		int speedMax = BaseHeroJson.getSpeedMax(hgid);
		int speed_ = BaseHeroJson.getSpeed(hgid) * lvl;
		speed_ = speed_ > speedMax ? speedMax : speed_;

		r.setMaxHP(hp_);
		r.setMaxDam(dam_);
		r.setMaxSpeed(speed_);
		return r;
	}

	public static HeroMax getMaxByPlayer(Player p, int hgid) {
		HeroMax r = null;
		if (p == null)
			return r;
		int exp = p.getExp();
		int lvl = LogicPlayer.getLvl(exp, 0);
		r = getMaxByLvl(hgid, lvl);
		return r;
	}

	// ====== method

	public static boolean createHero(Player p, int hgid, int gems) {
		boolean isHas = Player_heroEntity.isHasHero(p, hgid);
		if (isHas)
			return false;
		int nedGems = getGems(hgid);
		boolean isEvent = LogicalEvent.isActiveBuyHero(p);
		if (isEvent) {
			nedGems = (int) Math.ceil(nedGems * 0.7);
		}
		boolean isEough = gems >= nedGems - 3;
		if (!isEough)
			return false;

		Player_heroEntity.createNewInsert(p, hgid);
		String heroes = p.getHeroes();
		p.setHeroes(heroes + hgid + ",");
		PlayerEntity.updatePlayer(p);
		return true;
	}

	public static boolean liveHero(Player p, int hgid) {
		boolean isHas = Player_heroEntity.isHasHero(p, hgid);
		if (!isHas)
			return false;
		Player_heroEntity.liveHero(p, hgid);
		return true;
	}

	public static boolean deadHero(Player p, int hgid) {
		boolean isHas = Player_heroEntity.isHasHero(p, hgid);
		if (!isHas)
			return false;
		Player_heroEntity.deadHero(p, hgid);
		return true;
	}

	public static void refreshHerosMax(Player p) {
		List<Player_hero> list = getHeros(p);
		if (list == null || list.size() <= 0)
			return;
		int lvl = LogicPlayer.getPLvl(p);
		boolean isCanIssureHero = false;
		for (Player_hero item : list) {
			if (item == null)
				continue;
			int hgid = item.getHgid();
			HeroMax le = getMaxByLvl(hgid, lvl);
			int maxHp = le.getMaxHP();
			if (maxHp <= 0)
				continue;
			int maxSpeed = le.getMaxSpeed();
			int maxAtt = le.getMaxDam();

			item.setMaxattspeed(maxSpeed);
			item.setMaxhp(maxHp);
			item.setMaxdamage(maxAtt);

			Player_heroEntity.updateHero(item);
			isCanIssureHero = true;
		}
		if (isCanIssureHero) {
			int toId = p.getPcid();
			LogicalSession.refreshLEPHeroTrue(toId);
		}
	}

	public static List<Player_hero> getHeros(Player p) {
		if (p == null)
			return null;

		List<Player_hero> r = Player_heroEntity.getHerosByPlayer(p);
		String heros = p.getHeroes();
		if ((heros == null || "".equals(heros.trim())) && r != null
				&& !r.isEmpty()) {
			StringBuffer buffer = new StringBuffer();
			for (Player_hero item : r) {
				if (item != null)
					buffer.append(item.getHgid()).append(",");
			}
			heros = buffer.toString();
			p.setHeroes(heros);
			PlayerEntity.updatePlayer(p);
		}
		return r;
	}

	public static List<Player_hero> getHeros(Player p, NHeros heros) {
		List<Player_hero> r = getHeros(p);
		if (heros != null && heros.lists != null && heros.lists.size() > 0) {
			for (Player_hero item : r) {
				int igid = item.getHgid();
				for (NHero nh : heros.lists) {
					int ngid = nh.hgid;
					int pos = nh.fightPos;
					if (igid == ngid) {
						item.setFpos(pos);
					}
				}
			}
		}
		return r;
	}

	public static void getNHeros(Player p, NHeros heros) {
		List<Player_hero> list = getHeros(p);
		Player_heroEntity.getNHeros(list, heros);
	}

	public static String getStrHeros(Player p) {
		List<Player_hero> lh = getHeros(p);
		return Attack_defense_infoEntity.toStrDefenseHeroInfo(lh);
	}

	public static String getStrHeros(Player p, NHeros heros) {
		List<Player_hero> lh = getHeros(p, heros);
		return Attack_defense_infoEntity.toStrDefenseHeroInfo(lh);
	}

	public static List<Player_hero> getListHeroByStr(String strHeros) {
		return Attack_defense_infoEntity.toListDefenseHeroInfo(strHeros);
	}

	/**
	 * 添加了一个取得英雄价格的
	 * 
	 * @param hgid
	 * @return
	 */
	static public int getGems(int hgid) {
		switch (hgid) {
		case Hero_Gid_ElementsNaga:
			return Gem_ElementsNaga;
		case Hero_Gid_LandDragon:
			return Gem_LandDragon;
		case Hero_Gid_Machinery:
			return Gem_Machinery;
		case Hero_Gid_Sprite:
			return Gem_Sprite;
		case Hero_Gid_Jelly:
			return Gem_Jelly;
		default:
			return Gem_Fire;
		}
	}
}
