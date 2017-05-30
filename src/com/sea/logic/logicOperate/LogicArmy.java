package com.sea.logic.logicOperate;

import gen_b2g.serv.bean.NArmys;
import gen_b2g.serv.bean.NProducte;
import gen_b2g.serv.bean.NProductes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sea.cache.process.ProProduct;
import com.sea.content.Svc;
import com.sea.db.bean.Player;
import com.sea.db.bean.Player_armys;
import com.sea.db.bean.Player_buildings;
import com.sea.db.bean.Player_producting;
import com.sea.db.entity.Player_armysEntity;
import com.sea.db.entity.Player_productingEntity;
import com.sea.logic.logicEntity.LEArmy;

//@SuppressWarnings({ "unchecked", "rawtypes" })
public class LogicArmy implements Serializable {

	static Log log = LogFactory.getLog(LogicArmy.class);

	private static final long serialVersionUID = 1L;

	// ===============兵工厂product
	private static Player_producting getProductArmyInMap(Player p, int bcid,
			int gid) {
		if (p == null)
			return null;
		int pcid = p.getPcid();
		return ProProduct.getArmy(pcid, bcid, gid);
	}

	// 判断建筑上面是否还在建造这种兵
	public static boolean isProducting(Player p, int bcid) {
		if (p == null)
			return false;
		int pcid = p.getPcid();
		return ProProduct.isProducting(pcid, bcid);
	}

	// 某个建筑上面造兵
	public static boolean producteArmy(Player p, int bcid, int gid, int num) {
		boolean r2 = false;
		if (num <= 0)
			return r2;

		boolean isGid = LEArmy.isHasAmryGid(gid);
		if (!isGid)
			return r2;

		Player_buildings p_b = LogicBuild.getBuildInMap(p, bcid);

		boolean isCanTrain = LogicBuild.isTrueArsenal(p_b);
		if (!isCanTrain)
			return r2;

		LogicBuild.changeProductArmyBTime(p, bcid, false);

		Player_producting p_p = getProductArmyInMap(p, bcid, gid);

		if (p_p == null) {
			p_p = Player_productingEntity
					.createNewProducteInsert(p_b, gid, num);
		} else {
			p_p = Player_productingEntity.addProducte(p_p, num);
		}
		r2 = true;
		return r2;
	}

	// 减少造兵
	public static int reduceProductArmy(Player p, int bcid, int gid, int num) {
		int r2 = 0;
		Player_producting p_p = getProductArmyInMap(p, bcid, gid);
		if (p_p == null)
			return r2;
		num = Math.abs(num);
		int curNum = p_p.getNum();
		if (curNum < num)
			num = curNum;
		int diff = curNum - num;
		if (diff > 0) {
			p_p.setNum(diff);
			Player_productingEntity.updateProducte(p_p);
		} else {
			Player_productingEntity.deleteProducte(p_p);
		}
		r2 = num;
		return r2;
	}

	// 将穿上来的兵转换为数据库对象
	private static List<Player_producting> toProductByNPList(int bcid,
			List<NProducte> list) {
		if (Svc.isEmpty(list))
			return null;

		Map<Integer, Player_producting> map = new HashMap<Integer, Player_producting>();
		for (NProducte item : list) {
			if (bcid != item.building_id)
				continue;

			Player_producting pitem = map.get(item.gid);
			if (pitem == null) {
				pitem = new Player_producting();
				pitem.setId(item.pid);
				pitem.setGid(item.gid);
				pitem.setNum(item.num);
				map.put(item.gid, pitem);
			} else {
				int num = pitem.getNum();
				num += item.num;
				pitem.setNum(num);
			}
		}

		if (map.isEmpty())
			return null;

		List<Player_producting> r2 = new ArrayList<Player_producting>();
		for (Entry<Integer, Player_producting> item : map.entrySet()) {
			r2.add(item.getValue());
		}
		return r2;
	}

	// 自然完成造兵
	public static boolean natureFinishProductArmy(Player p, int bcid,
			NProductes lists) {
		return finishProductArmy(p, bcid, lists);
	}

	// 快速完成造兵
	public static boolean fastFinishProducteArmy(Player p, int bcid,
			NProductes lists) {
		return finishProductArmy(p, bcid, lists);
	}

	private static boolean finishProductArmy(Player p, int bcid,
			NProductes lists) {
		boolean r2 = false;
		if (p == null)
			return r2;

		if (lists == null)
			return r2;

		List<Player_producting> origin = toProductByNPList(bcid,
				lists.army_productings);

		if (Svc.isEmpty(origin))
			return r2;

		int len = origin.size();

		for (Player_producting item : origin) {
			int gid = item.getGid();
			int num = item.getNum();
			r2 = finishProductArmy(p, bcid, gid, num);
			if (len < 2) {
				if (!r2)
					break;
			}
		}
		origin.clear();
		origin = null;
		return r2;
	}

	// 完成造兵
	private static boolean finishProductArmy(Player p, int bcid, int gid,
			int num) {
		boolean r2 = false;
		int addNum = reduceProductArmy(p, bcid, gid, num);
		r2 = addOrNewArmy(p, bcid, gid, addNum);
		if (addNum > 0)
			// 判断这种
			LogicBuild.changeProductArmyBTime(p, bcid, true);
		return r2;
	}

	// ================product 取得数据
	public static List<Player_producting> getList(Player p) {
		if (p == null)
			return null;
		return Player_productingEntity
				.getListByPcid(p.getPcid(), p.getBuilds());
	}

	public static void getNProductArmys(Player p, NProductes sup_pro) {
		List<Player_producting> list_ = getList(p);
		Player_productingEntity.toSUProductes(list_, sup_pro);
	}

	// ===============拥有兵 army
	static private boolean addOrNewArmy(Player p, int bcid, int gid, int num) {
		boolean r2 = false;
		if (num <= 0)
			return r2;
		boolean isHasGid = LEArmy.isHasAmryGid(gid);
		if (!isHasGid)
			return r2;
		Player_buildings p_b = LogicBuild.getBuildInMap(p, bcid);
		boolean isCanTrain = LogicBuild.isTrueArsenal(p_b);
		if (!isCanTrain)
			return r2;

		addOrNewArmy(p_b, gid, num);
		r2 = true;
		return r2;
	}

	public static Player_armys addOrNewArmy(Player_buildings p_b, int gid,
			int num) {
		Player_armys r2 = null;
		if (num <= 0)
			return r2;
		boolean isHasGid = LEArmy.isHasAmryGid(gid);
		if (!isHasGid)
			return r2;
		boolean isCanTrain = LogicBuild.isTrueArsenal(p_b);
		if (!isCanTrain)
			return r2;
		int pcid = p_b.getPcid();
		int bcid = p_b.getBcid();

		r2 = Player_armysEntity.getArmy(pcid, gid, bcid);
		if (r2 == null) {
			r2 = Player_armysEntity.createNewArmyInsert(p_b, gid, 1, num);
		} else {
			int curNum = r2.getNum();
			curNum += num;
			r2.setNum(curNum);
			Player_armysEntity.updateArmy(r2);
		}
		return r2;
	}

	// ================army 取得数据
	public static List<Player_armys> getListOwnArmyInData(Player p) {
		List<Player_armys> r2 = Player_armysEntity.getPlayerArmyList(p);
		return r2;
	}

	public static void getNArmysInData(Player p, NArmys back) {
		List<Player_armys> list = LogicArmy.getListOwnArmyInData(p);
		Player_armysEntity.toSUPArmys(list, back);
	}

	public static List<Player_armys> getListOwnArmy(Player p) {
		List<Player_armys> r2 = Player_armysEntity.getPlayerArmyList(p);
		return r2;
	}

	public static void getNOwnArmys(Player absChn, NArmys supas) {
		List<Player_armys> list = getListOwnArmy(absChn);
		supas = Player_armysEntity.toSUPArmys(list, supas);
	}
}
