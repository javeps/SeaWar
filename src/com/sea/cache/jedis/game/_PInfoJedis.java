package com.sea.cache.jedis.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sea.cache.jedis.origin.JedisHash;
import com.sea.cache.process.PInfoAll;
import com.sea.cache.process.ProAmry;
import com.sea.cache.process.ProBuild;
import com.sea.cache.process.ProHero;
import com.sea.cache.process.ProObst;
import com.sea.cache.process.ProProduct;
import com.sea.cache.process.ProTech;
import com.sea.content.Svc;
import com.sea.db.bean.Player;
import com.sea.db.bean.Player_armys;
import com.sea.db.bean.Player_build_obst;
import com.sea.db.bean.Player_buildings;
import com.sea.db.bean.Player_hero;
import com.sea.db.bean.Player_producting;
import com.sea.db.bean.Player_tech;
import com.sea.logic.session.LogicalSession;
import com.sea.logic.session.Session;

/**
 * 玩家信息表(建筑，科技，障碍，英雄，拥有兵，生产兵)
 * 
 * @author Administrator
 * 
 */
public class _PInfoJedis implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(_PInfoJedis.class);

	static public PInfoAll getEnByJedisKey(Player p) {
		if (p == null)
			return null;
		int pcid = p.getPcid();
		return getEnBy(pcid,false);
	}

	/** isreload 为true 时，重新加载数据到缓存中去 **/
	static public PInfoAll getEnBy(int pcid,boolean isReload) {
		if (pcid == 0)
			return null;
		PInfoAll pinfo = null;

		Session session = LogicalSession.getSessionByPcid(pcid);
		if (session != null) {
			pinfo = session.getPinfo();
		}
		if (pinfo != null && pinfo.isLoad())
			return pinfo;

		boolean isLoadBuild = isReload ? false : ProBuild.isHasPcid(pcid);
		boolean isLoadObsts = isReload ? false : ProObst.isHasPcid(pcid);
		boolean isLoadTech = isReload ? false : ProTech.isHasPcid(pcid);
		boolean isLoadHero = isReload ? false : ProHero.isHasPcid(pcid);
		boolean isLoadOwn = isReload ? false : ProAmry.isHasPcid(pcid);
		boolean isLoadMake = isReload ? false : ProProduct.isHasPcid(pcid);

		boolean isLoad = isLoadBuild && isLoadObsts;
		Map<String, String> map = null;

		if (!isLoad) {
			String jedisKey = _GameKeys.fmtPinfoPcid(pcid);
			map = JedisHash.getMap(jedisKey);
			isLoad = Svc.isEmpty(map);
		}

		List<Player_buildings> builds = new ArrayList<Player_buildings>();
		List<Player_build_obst> obsts = new ArrayList<Player_build_obst>();
		List<Player_tech> teches = new ArrayList<Player_tech>();
		List<Player_hero> heroes = new ArrayList<Player_hero>();
		List<Player_armys> ownArmys = new ArrayList<Player_armys>();
		List<Player_producting> products = new ArrayList<Player_producting>();

		if (isLoad) {
			isLoadBuild = true;
			isLoadObsts = true;
			isLoadTech = true;
			isLoadHero = true;
			isLoadOwn = true;
			isLoadMake = true;
		}

		if (isLoadBuild)
			builds = ProBuild.getListInCache(pcid);

		if (isLoadObsts)
			obsts = ProObst.getListInCache(pcid);

		if (isLoadTech)
			teches = ProTech.getListInCache(pcid);

		if (isLoadHero)
			heroes = ProHero.getListInCache(pcid);

		if (isLoadOwn)
			ownArmys = ProAmry.getListInCache(pcid);

		if (isLoadMake)
			products = ProProduct.getListInCache(pcid);

		if (!isLoad) {
			for (Entry<String, String> en : map.entrySet()) {
				String key = en.getKey();
				String val = en.getValue();
				if (key.indexOf("bcid") == 0) {
					if (isLoadBuild)
						continue;
					Player_buildings pb = BuildJedis.toObjByStrVal(val);
					if (pb == null)
						continue;
					builds.add(pb);
				} else if (key.indexOf("ocid") == 0) {
					if (isLoadObsts)
						continue;
					Player_build_obst pb = ObstJedis.toObjByStrVal(val);
					if (pb == null)
						continue;
					obsts.add(pb);
				} else if (key.indexOf("hgid") == 0) {
					if (isLoadHero)
						continue;
					Player_hero pb = HeroJedis.toObjByStrVal(val);
					if (pb == null)
						continue;
					heroes.add(pb);
				} else if (key.indexOf("tgid") == 0) {
					if (isLoadTech)
						continue;
					Player_tech pb = TechJedis.toObjByStrVal(val);
					if (pb == null)
						continue;
					teches.add(pb);
				} else if (key.indexOf("own") == 0) {
					if (isLoadOwn)
						continue;
					Player_armys pb = ArmyJedis.toObjByStrVal(val);
					if (pb == null)
						continue;
					ownArmys.add(pb);
				} else if (key.indexOf("make") == 0) {
					if (isLoadMake)
						continue;
					Player_producting pb = ProductJedis.toObjByStrVal(val);
					if (pb == null)
						continue;
					products.add(pb);
				} else {
					continue;
				}
			}
		}

		isLoadBuild = builds.size() >= 6;
		isLoadObsts = obsts.size() > 0;
		isLoadTech = teches.size() > 0;
		isLoadHero = heroes.size() > 0;
		isLoadOwn = ownArmys.size() > 0;
		isLoadMake = products.size() > 0;

		if (!isLoadBuild) {
			isLoadBuild = true;
			List<Player_buildings> list = BuildJedis.getListOld(pcid, "");
			if (!Svc.isEmpty(list)) {
				builds.addAll(list);
			}
		}

		if (!isLoadObsts) {
			isLoadObsts = true;
			List<Player_build_obst> list = ObstJedis.getListOld(pcid, "");
			if (!Svc.isEmpty(list)) {
				obsts.addAll(list);
			}
		}

		if (!isLoadTech) {
			isLoadTech = true;
			List<Player_tech> list = TechJedis.getListOld(pcid, "");
			if (!Svc.isEmpty(list)) {
				teches.addAll(list);
			}
		}

		if (!isLoadHero) {
			isLoadHero = true;
			List<Player_hero> list = HeroJedis.getListOld(pcid, "");
			if (!Svc.isEmpty(list)) {
				heroes.addAll(list);
			}
		}

		if (!isLoadOwn) {
			isLoadOwn = true;
			List<Player_armys> list = ArmyJedis.getListOld(pcid, "");
			if (!Svc.isEmpty(list)) {
				ownArmys.addAll(list);
			}
		}

		if (!isLoadMake) {
			isLoadMake = true;
			List<Player_producting> list = ProductJedis.getListOld(pcid, "");
			if (!Svc.isEmpty(list)) {
				products.addAll(list);
			}
		}

		ProBuild.loadCache(builds);
		ProObst.loadCache(obsts);
		ProTech.loadCache(teches);
		ProHero.loadCache(heroes);
		ProAmry.loadCache(ownArmys);
		ProProduct.loadCache(products);

		pinfo = new PInfoAll(pcid, builds, obsts, teches, heroes, ownArmys,
				products);

		pinfo.reload(isLoadBuild, isLoadObsts, isLoadTech, isLoadOwn,
				isLoadMake, isLoadHero);
		return pinfo;
	}

	/** 用于修复 jedis 数据，没有加入进程级缓存 **/
	// static public PInfoAll getEnInJedis(int pcid) {
	// if (pcid == 0)
	// return null;
	// PInfoAll pinfo = null;
	//
	// boolean isLoadBuild = false;
	// boolean isLoadObsts = false;
	// boolean isLoadTech = false;
	// boolean isLoadHero = false;
	// boolean isLoadOwn = false;
	// boolean isLoadMake = false;
	//
	// boolean isLoad = isLoadBuild && isLoadObsts;
	// Map<String, String> map = null;
	//
	// if (!isLoad) {
	// String jedisKey = _GameKeys.fmtPinfoPcid(pcid);
	// map = JedisHash.getMap(jedisKey);
	// isLoad = Svc.isEmpty(map);
	// }
	//
	// List<Player_buildings> builds = new ArrayList<Player_buildings>();
	// List<Player_build_obst> obsts = new ArrayList<Player_build_obst>();
	// List<Player_tech> teches = new ArrayList<Player_tech>();
	// List<Player_hero> heroes = new ArrayList<Player_hero>();
	// List<Player_armys> ownArmys = new ArrayList<Player_armys>();
	// List<Player_producting> products = new ArrayList<Player_producting>();
	//
	// if (isLoad) {
	// isLoadBuild = true;
	// isLoadObsts = true;
	// isLoadTech = true;
	// isLoadHero = true;
	// isLoadOwn = true;
	// isLoadMake = true;
	// }
	//
	// if (!isLoad) {
	// for (Entry<String, String> en : map.entrySet()) {
	// String key = en.getKey();
	// String val = en.getValue();
	// if (key.indexOf("bcid") == 0) {
	// if (isLoadBuild)
	// continue;
	// Player_buildings pb = BuildJedis.toObjByStrVal(val);
	// if (pb == null)
	// continue;
	// builds.add(pb);
	// } else if (key.indexOf("ocid") == 0) {
	// if (isLoadObsts)
	// continue;
	// Player_build_obst pb = ObstJedis.toObjByStrVal(val);
	// if (pb == null)
	// continue;
	// obsts.add(pb);
	// } else if (key.indexOf("hgid") == 0) {
	// if (isLoadHero)
	// continue;
	// Player_hero pb = HeroJedis.toObjByStrVal(val);
	// if (pb == null)
	// continue;
	// heroes.add(pb);
	// } else if (key.indexOf("tgid") == 0) {
	// if (isLoadTech)
	// continue;
	// Player_tech pb = TechJedis.toObjByStrVal(val);
	// if (pb == null)
	// continue;
	// teches.add(pb);
	// } else if (key.indexOf("own") == 0) {
	// if (isLoadOwn)
	// continue;
	// Player_armys pb = ArmyJedis.toObjByStrVal(val);
	// if (pb == null)
	// continue;
	// ownArmys.add(pb);
	// } else if (key.indexOf("make") == 0) {
	// if (isLoadMake)
	// continue;
	// Player_producting pb = ProductJedis.toObjByStrVal(val);
	// if (pb == null)
	// continue;
	// products.add(pb);
	// } else {
	// continue;
	// }
	// }
	// }
	//
	// isLoadBuild = builds.size() >= 6;
	// isLoadObsts = obsts.size() > 0;
	// isLoadTech = teches.size() > 0;
	// isLoadHero = heroes.size() > 0;
	// isLoadOwn = ownArmys.size() > 0;
	// isLoadMake = products.size() > 0;
	//
	// if (!isLoadBuild) {
	// isLoadBuild = true;
	// List<Player_buildings> list = BuildJedis.getListOld(pcid, "");
	// if (!Svc.isEmpty(list)) {
	// builds.addAll(list);
	// }
	// }
	//
	// if (!isLoadObsts) {
	// isLoadObsts = true;
	// List<Player_build_obst> list = ObstJedis.getListOld(pcid, "");
	// if (!Svc.isEmpty(list)) {
	// obsts.addAll(list);
	// }
	// }
	//
	// if (!isLoadTech) {
	// isLoadTech = true;
	// List<Player_tech> list = TechJedis.getListOld(pcid, "");
	// if (!Svc.isEmpty(list)) {
	// teches.addAll(list);
	// }
	// }
	//
	// if (!isLoadHero) {
	// isLoadHero = true;
	// List<Player_hero> list = HeroJedis.getListOld(pcid, "");
	// if (!Svc.isEmpty(list)) {
	// heroes.addAll(list);
	// }
	// }
	//
	// if (!isLoadOwn) {
	// isLoadOwn = true;
	// List<Player_armys> list = ArmyJedis.getListOld(pcid, "");
	// if (!Svc.isEmpty(list)) {
	// ownArmys.addAll(list);
	// }
	// }
	//
	// if (!isLoadMake) {
	// isLoadMake = true;
	// List<Player_producting> list = ProductJedis.getListOld(pcid, "");
	// if (!Svc.isEmpty(list)) {
	// products.addAll(list);
	// }
	// }
	//
	// pinfo = new PInfoAll(pcid, builds, obsts, teches, heroes, ownArmys,
	// products);
	//
	// pinfo.reload(isLoadBuild, isLoadObsts, isLoadTech, isLoadOwn,
	// isLoadMake, isLoadHero);
	// return pinfo;
	// }

	static public void loadPinfoByJedisKey(Player p) {
		if (p == null)
			return;
		int pcid = p.getPcid();
		loadPinfoByJedisKey(pcid);
	}

	static public PInfoAll loadPinfoByJedisKey(int pcid) {
		PInfoAll info = getEnBy(pcid,false);
		if (info == null)
			return null;
		return info;
	}
}