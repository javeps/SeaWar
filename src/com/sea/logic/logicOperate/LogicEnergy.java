package com.sea.logic.logicOperate;

import gen_b2g.serv.bean.NEnergy;
import gen_b2g.serv.bean.NEnergys;
import gen_b2g.serv.bean.NInt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.lang.NumEx;
import com.bowlong.util.MapEx;
import com.sea.db.bean.Player;
import com.sea.db.bean.Player_hero;
import com.sea.db.entity.Player_heroEntity;
import com.sea.json.originData.ActivitiesJson;
import com.sea.json.originData.EnergyJson;
import com.sea.tools.FormulaGame;
import com.sea.tools.UtileTools;

public class LogicEnergy implements Serializable {

	static Log log = LogFactory.getLog(LogicEnergy.class);

	private static final long serialVersionUID = 1L;

	// ============= energy 能源片
	static final int allEnergyNum = 200000;
	static final int maxRandNum = 8;

	public static String toStrByEnergy(NEnergy e) {
		String r = "";
		if (e == null || e.num <= 0)
			return r;
		StringBuffer s = new StringBuffer();
		s.append("{");
		s.append("\"").append("egid").append("\"").append(":").append(e.egid)
				.append(",");
		s.append("\"").append("num").append("\"").append(":").append(e.num);
		s.append("}");
		r = s.toString();
		return r;
	}

	@SuppressWarnings("rawtypes")
	public static NEnergy toEnergyByStr(String e) {
		if (e == null || "".equals(e.trim()))
			return null;
		Map map = UtileTools.strToMap(e);
		NEnergy r = new NEnergy();
		r.egid = MapEx.getInt(map, "egid");
		r.num = MapEx.getInt(map, "num");
		return r;
	}

	// ===== 给英雄增加能量

	public static void addEnergy(Player_hero h, int egid, NInt addVal) {
		if (h == null)
			return;
		int cur = 0;
		int max = 0;
		int tmp = 0;
		int val = 0;
		double multiple = ActivitiesJson.getMultipleHeroAttr();// 活动倍数
		boolean isChange = false;
		int rate = UtileTools.randIntK();
		switch (egid) {
		case EnergyJson.Egid_HP:
			val = EnergyJson.getValByAddHP(rate);
			val = (int) (val * multiple);
			cur = h.getAddhp();
			max = h.getMaxhp();
			if (cur < max) {
				tmp = cur + val;
				tmp = Math.min(tmp, max);
				val = tmp - cur;
				h.setAddhp(tmp);
				isChange = true;
			}
			break;
		case EnergyJson.Egid_Damage:
			val = EnergyJson.getValByAddDam(rate);
			val = (int) (val * multiple);
			cur = h.getAdddamage();
			max = h.getMaxdamage();
			if (cur < max) {
				tmp = cur + val;
				tmp = Math.min(tmp, max);
				val = tmp - cur;
				h.setAdddamage(tmp);
				isChange = true;
			}
			break;
		case EnergyJson.Egid_AttSpeed:
			val = EnergyJson.getValByAddSpeed(rate);
			val = (int) (val * multiple);
			cur = h.getAddattspeed();
			max = h.getMaxattspeed();
			if (cur < max) {
				tmp = cur + val;
				tmp = Math.min(tmp, max);
				val = tmp - cur;
				h.setAddattspeed(tmp);
				isChange = true;
			}
			break;
		default:
			break;
		}

		if (isChange) {
			Player_heroEntity.updateHero(h);
		} else {
			val = 0;
		}
		addVal.val = val;
	}

	public static void addEnergy(Player p, int hgid, int egid, NInt egnum,
			NInt addVal) {
		if (p == null)
			return;
		int pcid = p.getPcid();
		Player_hero h = Player_heroEntity.getHeroBy(pcid, hgid);
		int num = LogicPlayer.getEnergyNum(p, egid);
		if (h != null && num >= 9) {
			addEnergy(h, egid, addVal);
			if (addVal.val > 0) {
				LogicPlayer.changeEnergyNum(p, egid, -9);
				num = num - 9;
			}
		}
		egnum.val = num;
	}

	// 获取能源片

	private static int getEnergyNumByRate(int rate, boolean isActivite) {
		int num = EnergyJson.getENumByRate(rate, isActivite);
		return num;
	}

	private static int getEnergyGid(boolean isGet) {
		int rate = UtileTools.randIntK();
		boolean isActivite = ActivitiesJson.getIsOpenEnergy();
		int egid = EnergyJson.getEgidByRate(rate, isGet, isActivite);
		return egid;
	}

	private static int getEnergyNumByEgid() {
		int rate = UtileTools.randIntK();
		boolean isActivite = ActivitiesJson.getIsOpenEnergy();
		int num = getEnergyNumByRate(rate, isActivite);
		return num;
	}

	private static int getEnergyNumByBeFighter(int num, int egid, Player att,
			Player beAtt) {
		boolean isNpc = LogicPlayer.isNpc(beAtt);
		if (isNpc) {
			num = Math.min(num, 3);
			return num;
		}

		if (beAtt == null)
			return 0;

		int ahp = att.getPiecehpnum();
		int adam = att.getPiecedamnum();
		int aspeed = att.getPieceattspeed();

		int allNum = ahp + adam + aspeed;
		if (allNum >= allEnergyNum)
			return 0;

		int maxFall = allEnergyNum - allNum;

		int numHp = beAtt.getPiecehpnum();
		int numDam = beAtt.getPiecedamnum();
		int numSpeed = beAtt.getPieceattspeed();
		int[] list = { maxFall, num, maxRandNum };
		num = NumEx.min(list);
		switch (egid) {
		case EnergyJson.Egid_HP:
			num = Math.min(numHp, num);
			break;
		case EnergyJson.Egid_Damage:
			num = Math.min(numDam, num);
			break;
		case EnergyJson.Egid_AttSpeed:
			num = Math.min(numSpeed, num);
			break;
		default:
			num = 0;
			break;
		}
		return num;
	}

	public static void getEnergyByFight(Player att, Player beAtt, NEnergy energy) {
		int egid = EnergyJson.Egid_None;
		boolean isNpc = LogicPlayer.isNpc(beAtt);
		if (isNpc) {
			egid = getEnergyGid(false);
			if (egid == EnergyJson.Egid_None)
				return;
		} else {
			egid = getEnergyGid(true);
		}

		int num = getEnergyNumByEgid();
		num = getEnergyNumByBeFighter(num, egid, att, beAtt);

		if (num > 0) {
			energy.egid = egid;
			energy.num = num;
		}
	}

	private static int getMinEnergyNumByPlayer(int num, Player p) {
		if (p == null || num <= 0)
			return 0;
		int ahp = p.getPiecehpnum();
		int adam = p.getPiecedamnum();
		int aspeed = p.getPieceattspeed();

		int allNum = ahp + adam + aspeed;
		if (allNum >= allEnergyNum)
			return 0;

		int maxFall = allEnergyNum - allNum;

		int[] list = { maxFall, num, maxRandNum };
		num = NumEx.min(list);
		return num;
	}

	public static void getNEnergyBy(Player p, int min, NEnergy energy) {
		if (p == null || energy == null)
			return;

		boolean isGet = min > 0;

		int egid = getEnergyGid(isGet);

		if (egid == EnergyJson.Egid_None)
			return;

		int num = getEnergyNumByEgid();
		num = Math.max(num, min);
		num = getMinEnergyNumByPlayer(num, p);
		if (num > 0) {
			energy.egid = egid;
			energy.num = num;
		}
	}

	public static void getNEnergysByBox(Player p, int num, NEnergys energys) {
		if (p == null || energys == null || num < 1)
			return;

		energys.list = new ArrayList<NEnergy>();

		NEnergy hp = new NEnergy();
		hp.egid = EnergyJson.Egid_HP;
		NEnergy dam = new NEnergy();
		dam.egid = EnergyJson.Egid_Damage;
		NEnergy speed = new NEnergy();
		speed.egid = EnergyJson.Egid_AttSpeed;

		NEnergy tmp = new NEnergy();
		int min = 1;
		for (int i = 0; i < num; i++) {
			getNEnergyBy(p, min, tmp);
			int tmpgid = tmp.egid;
			int addNum = tmp.num;
			switch (tmpgid) {
			case EnergyJson.Egid_HP:
				hp.num += addNum;
				break;
			case EnergyJson.Egid_Damage:
				dam.num += addNum;
				break;
			case EnergyJson.Egid_AttSpeed:
				speed.num += addNum;
				break;
			default:
				break;
			}
		}

		double multiple = ActivitiesJson.getMultipleBoxEnergy();// 活动倍数

		if (hp.num > 0) {
			hp.num = (int) Math.ceil(hp.num * multiple);
			energys.list.add(hp);
		}
		if (dam.num > 0) {
			dam.num = (int) Math.ceil(dam.num * multiple);
			energys.list.add(dam);
		}
		if (speed.num > 0) {
			speed.num = (int) Math.ceil(speed.num * multiple);
			energys.list.add(speed);
		}
	}

	/** 判断花费资源是否足够 **/
	static public boolean isEnough(int gold, int oil, int gems) {
		int min = 10000;
		int costGems = 0;
		int diff = 0;
		if (gold < min) {
			diff = min - gold;
			costGems += FormulaGame.resToGems(diff);
		}

		if (oil < min) {
			diff = min - oil;
			costGems += FormulaGame.resToGems(diff);
		}

		int minGems = costGems - 5;
		if (gems < minGems)
			return false;

		return true;
	}
}
