package com.sea.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import gen_b2g.serv.bean.NProducte;
import gen_b2g.serv.bean.NProductes;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sea.cache.process.ProProduct;
import com.sea.db.bean.Player_buildings;
import com.sea.db.bean.Player_producting;
import com.sea.db.dao.Player_productingDAO;
import com.sea.db.internal.Player_productingInternal;
//import com.sea.content.AppContext;

//seawar_design - player_producting
@SuppressWarnings({ "static-access" })
public class Player_productingEntity extends Player_productingInternal {
	static Log log = LogFactory.getLog(Player_productingEntity.class);

	public static final Player_productingEntity my = new Player_productingEntity();

	static Player_productingDAO Player_productingDAO = null;

	public static Player_productingDAO Player_productingDAO() {
		if (Player_productingDAO == null)
			Player_productingDAO = new Player_productingDAO(
					com.sea.content.AppContext.ds());
		return Player_productingDAO;
	}

	// static Player_productingDAO Player_productingDAO99 = null;
	// public static Player_productingDAO Player_productingDAO99() {
	// if( Player_productingDAO99 == null)
	// Player_productingDAO99 = new
	// Player_productingDAO(com.sea.content.AppContext.ds99());
	// return Player_productingDAO99;
	// }

	public static void insertMmTry(final Player_producting player_producting) {
		Player_productingDAO DAO = Player_productingDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asynchronousInsert(player_producting, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin
	// ================== 数据库操作 begin ==================

	// 插入数据
	public static void insertProducte(Player_producting r2) {
		if (r2 != null) {
			ProProduct.inEntity(r2);
		}
	}

	// 更新数据
	public static void updateProducte(Player_producting r2) {
		if (r2 != null) {
			ProProduct.upEntity(r2);
		}
	}

	// 删除数据
	public static void deleteProducte(Player_producting origin) {
		if (origin != null) {
			ProProduct.deleteData(origin);
		}
	}

	static public List<Player_producting> getListByPcid(int pcid, String bcids) {
		List<Player_producting> r2 = null;
		r2 = ProProduct.getListByPcid(pcid, bcids);
		return r2;
	}

	// ================== 数据库操作 end ==================
	// 创建并返回新的造的产品
	public static Player_producting createNewProducte(int gid, int num,
			int bcid, int pcid) {
		Player_producting p_p = Player_producting.newPlayer_producting(0, gid,
				num, bcid, pcid);
		return p_p;
	}

	// 创建新的产品并插入数据库
	public static Player_producting createNewProducteInsert(
			Player_buildings p_b, int gid, int num) {
		Player_producting r2 = null;
		if (num > 0) {
			r2 = createNewProducte(gid, num, p_b.getBcid(), p_b.getPcid());
			insertProducte(r2);
		}
		return r2;
	}

	// 产品数量的增减
	private static Player_producting changeNum(Player_producting p_p, int num) {
		if (p_p != null && num != 0) {
			int cur_num = p_p.getNum();
			int change_num = cur_num + num;
			if (change_num < 0) {
				change_num = 0;
			}
			p_p.setNum(change_num);
		}
		return p_p;
	}

	// 增加产品数量
	public static Player_producting addProducte(Player_producting p_p, int num) {
		if (p_p != null && num > 0) {
			p_p = changeNum(p_p, num);
			updateProducte(p_p);
		}
		return p_p;
	}

	// ================= 服务器数据数据 ==>> 客服端数据 ==================
	// 数据库对象转为客服端下发对象
	public static NProducte toSUProducte(Player_producting p_p) {
		if (p_p == null)
			return null;
		NProducte r2 = new NProducte();
		r2.building_id = p_p.getBcid();
		r2.gid = p_p.getGid();
		r2.num = p_p.num;
		r2.pid = p_p.id;
		return r2;
	}

	// 把数据对象集合List转换成客户端对象集合List
	public static List<NProducte> toSUProducteList(
			List<Player_producting> p_p_list) {
		List<NProducte> r2 = new ArrayList<NProducte>();
		if (p_p_list == null)
			return r2;
		for (Player_producting item : p_p_list) {
			NProducte su_p = toSUProducte(item);
			if (su_p == null)
				continue;
			r2.add(su_p);
		}
		return r2;
	}

	// 取得角色的所有序列并转换成客户端需求的List集合对象
	public static NProductes toSUProductes(List<Player_producting> p_p_list,
			NProductes sup) {
		if (p_p_list == null || sup == null)
			return sup;
		sup.army_productings = toSUProducteList(p_p_list);
		return sup;
	}
	// types end

}
