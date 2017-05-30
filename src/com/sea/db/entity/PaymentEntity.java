package com.sea.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import static com.bowlong.sql.CacheModel.NO_CACHE;

import java.sql.ResultSet;
import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.util.MapEx;
import com.sea.cache.jedis.DCfgJedis;
import com.sea.content.Svc;
import com.sea.db.bean.Payment;
import com.sea.db.dao.PaymentDAO;
import com.sea.db.internal.PaymentInternal;
import com.sea.json.originData.PayOriginJson;
import com.sea.logic.logicOperate.LogicAtomicInteger;

//seawar2_design - payment
@SuppressWarnings({ "static-access" })
public class PaymentEntity extends PaymentInternal {

	static Log log = LogFactory.getLog(PaymentEntity.class);

	public static final PaymentEntity my = new PaymentEntity();

	static PaymentDAO PaymentDAO = null;

	public static PaymentDAO PaymentDAO() {
		if (PaymentDAO == null)
			PaymentDAO = new PaymentDAO(com.sea.content.AppContext.ds());
		return PaymentDAO;
	}

	// static PaymentDAO PaymentDAO99 = null;
	// public static PaymentDAO PaymentDAO99() {
	// if( PaymentDAO99 == null)
	// PaymentDAO99 = new PaymentDAO(com.sea.content.AppContext.ds99());
	// return PaymentDAO99;
	// }

	public static void insertMmTry(final Payment payment) {
		PaymentDAO DAO = PaymentDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asynchronousInsert(payment, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	static PaymentDAO pdao = null;

	public static PaymentDAO PayDAO() {
		if (pdao == null)
			pdao = new PaymentDAO(com.sea.content.AppContext.ds());
		return pdao;
	}

	public static void insertUpdateDaoTry(final Payment payment)
			throws Exception {
		PaymentDAO DAO = PayDAO();
		String TABLENAME2 = DAO.TABLENAME;
		boolean ew = DAO.exist_w(TABLENAME2);
		if (ew == false)
			createNoUniqueTable(DAO, TABLENAME2);
		int keyId = payment.getId();
		if (keyId <= 0) {
			DAO.insert(payment, TABLENAME2);
		} else {
			DAO.updateByKey(payment, TABLENAME2);
		}
	}

	// types begin
	// =============== sql
	public static void insertPayment(Payment pay) throws Exception {
		if (pay != null) {
			insertUpdateDaoTry(pay);
		}
	}

	public static void updatePayment(Payment pay) {
		if (pay != null) {
			try {
				insertUpdateDaoTry(pay);
			} catch (Exception e) {
			}
		}
	}

	public static void deletePayment(Payment pay) {
		if (pay != null) {
			asynchronousDelete(pay);
		}
	}

	public static Payment getEnityByUnid(String unid, int svcid) {
		Payment pay = null;
		PaymentDAO DAO = null;
		try {
			DAO = (cacheModel == NO_CACHE) ? DAO() : null;
			pay = getByUnidSvcid(DAO, unid, svcid, DAO.TABLENAME);
		} catch (Exception e) {
			log.info(e2s(e));
		}
		return pay;
	}

	// ================= method

	public static final int Pay_Status_Create = 0;
	public static final int Pay_Status_ChannelBack = 1;
	public static final int Pay_Status_SendToGame = 2;
	public static final int Pay_Status_Success = 3;
	public static final int Pay_Status_FailBuy = 4;// 购买失败

	@SuppressWarnings("rawtypes")
	public static Payment createNew(int svcid, int pcid, int status,
			String channel, String ideStr, String strBillNo,
			String strChnBillNo, String phoneInfo) {

		Map map = PayOriginJson.getMapBy(channel, ideStr);
		if (map == null) {
			log.info("购买宝石map==null,svicd = " + svcid + ",pcid=" + pcid
					+ ",chn=" + channel + ",iden=" + ideStr);
			return null;
		}
		int gem = MapEx.getInt(map, PayOriginJson.PJ_GEM);
		if (gem <= 0) {
			log.info("购买0宝石,svicd = " + svcid + ",pcid=" + pcid + ",chn="
					+ channel + ",iden=" + ideStr + ",billNo = " + strBillNo
					+ ",chnBillNo = " + strChnBillNo);
			return null;
		}
		if (phoneInfo == null)
			phoneInfo = "";

		String unid = strBillNo;
		String chid = strChnBillNo;

		int money = MapEx.getInt(map, PayOriginJson.PJ_MONEY);

		Date createtime = new Date();
		long finishtime = 0l;
		String query = "";
		double mney = money;

		Payment pay = Payment.newPayment(0, unid, chid, svcid, pcid, channel,
				mney, status, gem, createtime, finishtime, ideStr, query,
				phoneInfo);
		return pay;
	}

	@SuppressWarnings("rawtypes")
	public static Payment createNew(int svcid, int pcid, String channel,
			String ideStr, String phoneInfo) {
		final String strChn = channel;
		int gem = PayOriginJson.getGemBy(strChn, ideStr);
		if (gem <= 0) {
			log.info("购买0宝石,svicd = " + svcid + ",pcid=" + pcid + ",chn="
					+ strChn + ",iden=" + ideStr);
			return null;
		}
		Map map = DCfgJedis.getMapConfig();

		int puuid = LogicAtomicInteger
				.getValByType(LogicAtomicInteger.Type_Pslfuuid);
		String strBillNo = DCfgJedis.getStrValInMap(map, DCfgJedis.M_K_Pay_S_H)
				+ puuid;
		int cuuid = LogicAtomicInteger
				.getValByType(LogicAtomicInteger.Type_Pchnuuid);
		String chnBillNo = DCfgJedis.getStrValInMap(map, DCfgJedis.M_K_Pay_C_H)
				+ cuuid;
		int status = Pay_Status_Create;
		Payment pay = createNew(svcid, pcid, status, strChn, ideStr, strBillNo,
				chnBillNo, phoneInfo);
		return pay;
	}

	public static Payment createNewInsert(int svcid, int pcid, String channel,
			String ideStr, String phoneInfo) {
		Payment pay = null;
		try {
			pay = createNew(svcid, pcid, channel, ideStr, phoneInfo);
			insertPayment(pay);
		} catch (Exception e) {
			pay = null;
		}
		return pay;
	}

	public static Payment createNewByChnBillNo(int svcid, int pcid,
			String channel, String ideStr, String chnBillNo, String phoneInfo) {
		int status = Pay_Status_Create;
		Payment pay = createNew(svcid, pcid, status, channel, ideStr,
				chnBillNo, chnBillNo, phoneInfo);
		return pay;
	}

	public static void changePayStatusBy(String unid, int svcid, int status) {
		Payment pay = getEnityByUnid(unid, svcid);
		if (pay == null)
			return;
		pay.setStatus(status);
		updatePayment(pay);
	}

	/******* 查询订单号的最后一条(国内服务) *********/
	static public int getLastPaymentBySvcid(int svcid) {
		String sql = "SELECT unid FROM payment WHERE svcid = " + svcid
				+ " ORDER BY createtime DESC limit 0,1;";
		PaymentDAO DAO = PayDAO();
		String TABLENAME2 = DAO.TABLENAME;
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			String strV = "";
			if (ew) {
				ResultSet v = DAO.query(sql);
				if(v.next()){
					strV = v.getString(1);
				}
			}
			if (Svc.isEmpty(strV))
				return 0;
			int index = strV.indexOf("swar112");
			strV = strV.substring(index + 7);
			return Integer.parseInt(strV);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	// types end

}
