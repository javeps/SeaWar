package com.sea.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import gen_b2g.serv.bean.ConstantType;
import gen_b2g.serv.bean.NClanRequest;
import gen_b2g.serv.bean.NClanRequests;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sea.cache.process.ProClanReq;
import com.sea.db.bean.Clan_request;
import com.sea.db.bean.Player;
import com.sea.db.dao.Clan_requestDAO;
import com.sea.db.internal.Clan_requestInternal;
//import com.sea.content.AppContext;

//seawar2_design - clan_request
@SuppressWarnings({ "static-access" })
public class Clan_requestEntity extends Clan_requestInternal {
	static Log log = LogFactory.getLog(Clan_requestEntity.class);

	public static final Clan_requestEntity my = new Clan_requestEntity();

	static Clan_requestDAO Clan_requestDAO = null;

	public static Clan_requestDAO Clan_requestDAO() {
		if (Clan_requestDAO == null)
			Clan_requestDAO = new Clan_requestDAO(
					com.sea.content.AppContext.ds());
		return Clan_requestDAO;
	}

	// static Clan_requestDAO Clan_requestDAO99 = null;
	// public static Clan_requestDAO Clan_requestDAO99() {
	// if( Clan_requestDAO99 == null)
	// Clan_requestDAO99 = new
	// Clan_requestDAO(com.sea.content.AppContext.ds99());
	// return Clan_requestDAO99;
	// }

	public static void insertMmTry(final Clan_request clan_request) {
		Clan_requestDAO DAO = Clan_requestDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asynchronousInsert(clan_request, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin

	// =============== sql
	public static void insertClanReq(Clan_request o) {
		if (o != null) {
			ProClanReq.inCmem(o);
		}
	}

	public static void updateClanReq(Clan_request o) {
		if (o != null) {
			ProClanReq.upCMem(o);
		}
	}

	public static void deleteClanReq(Clan_request o) {
		if (o != null) {
			ProClanReq.delCMem(o);
		}
	}

	public static Clan_request getEnityByCcid(String ccid, int pcid) {
		Clan_request o = null;
		o = getByCcidPcid(ccid, pcid);
		return o;
	}

	// ============ method

	public static Clan_request createClanReq(String ccid, int pcid,
			String pname, int renown) {
		int id = 0;
		Clan_request r = Clan_request.newClan_request(id, ccid, pcid, pname,
				renown);
		return r;
	}

	public static Clan_request createClanReqInsert(String ccid, int pcid,
			String pname, int renown) {
		Clan_request r = createClanReq(ccid, pcid, pname, renown);
		insertClanReq(r);
		return r;
	}

	public static Clan_request getClanReq(String ccid, int pcid) {
		return ProClanReq.getClanReq(ccid, pcid);
	}

	public static List<Clan_request> getListInClan(String ccid) {
		return ProClanReq.getListInClan(ccid);
	}

	public static List<Clan_request> getListInClanByPlayer(Player p) {
		List<Clan_request> r = null;
		if (p == null)
			return r;
		String ccid = p.getClancid();
		if (ccid == null || "".equals(ccid.trim()))
			return r;
		int post = p.getClanpost();
		if (post == ConstantType.Type_ClanMember_Normal)
			return r;
		r = ProClanReq.getListInClan(ccid);
		return r;
	}

	public static void delAllInClan(String ccid) {
		ProClanReq.delClanReqInClan(ccid);
	}

	public static void getNClanRequests(Player p, NClanRequests nc) {
		List<Clan_request> list = getListInClanByPlayer(p);
		toNClanRequestes(list, nc);
	}

	// =========== 数据 客户端 服务器
	public static NClanRequest toNClanRequest(Clan_request o) {
		NClanRequest r = null;
		if (o == null)
			return r;
		r = new NClanRequest();
		r.ccid = o.getCcid();
		r.pcid = o.getPcid();
		r.pname = o.getPname();
		r.renown = o.getRenown();
		return r;
	}

	public static List<NClanRequest> toNClanRequestList(List<Clan_request> o) {
		List<NClanRequest> r = null;
		if (o == null || o.isEmpty())
			return r;
		r = new ArrayList<NClanRequest>();
		for (Clan_request item : o) {
			NClanRequest v = toNClanRequest(item);
			if (v == null)
				continue;
			r.add(v);
		}
		return r;
	}

	public static NClanRequests toNClanRequestes(List<Clan_request> o,
			NClanRequests nc) {
		List<NClanRequest> list = toNClanRequestList(o);
		if (list == null || list.isEmpty() || nc == null)
			return nc;
		nc.list = list;
		return nc;
	}
	// types end

}
