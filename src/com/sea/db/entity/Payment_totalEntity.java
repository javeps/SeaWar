package com.sea.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sea.db.bean.Payment_total;
import com.sea.db.dao.Payment_totalDAO;
import com.sea.db.internal.Payment_totalInternal;
//import com.sea.content.AppContext;

//seawar2_design - payment_total
@SuppressWarnings({ "static-access" })
public class Payment_totalEntity extends Payment_totalInternal {
	static Log log = LogFactory.getLog(Payment_totalEntity.class);

	public static final Payment_totalEntity my = new Payment_totalEntity();

	static Payment_totalDAO Payment_totalDAO = null;

	public static Payment_totalDAO Payment_totalDAO() {
		if (Payment_totalDAO == null)
			Payment_totalDAO = new Payment_totalDAO(
					com.sea.content.AppContext.ds());
		return Payment_totalDAO;
	}

	// static Payment_totalDAO Payment_totalDAO99 = null;
	// public static Payment_totalDAO Payment_totalDAO99() {
	// if( Payment_totalDAO99 == null)
	// Payment_totalDAO99 = new
	// Payment_totalDAO(com.sea.content.AppContext.ds99());
	// return Payment_totalDAO99;
	// }

	public static void insertMmTry(final Payment_total payment_total) {
		Payment_totalDAO DAO = Payment_totalDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asynchronousInsert(payment_total, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// ===============日志

	static Payment_totalDAO pydao = null;

	public static Payment_totalDAO PaymentLogDAO() {
		if (pydao == null)
			pydao = new Payment_totalDAO(com.sea.content.AppContext.logDS());
		return pydao;
	}

	// =============== 按照年来创建表

	public static void insertUpdateYYYYByLog(final Payment_total payment_total) {
		if (payment_total == null)
			return;

		Payment_totalDAO DAO = PaymentLogDAO();
		String TABLENAME2 = DAO.TABLEYY();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			int id = payment_total.getId();
			if (id <= 0) {
				DAO.asynchronousInsert(payment_total, TABLENAME2);
			} else {
				DAO.asynchronousUpdate(payment_total, TABLENAME2);
			}
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	public static Payment_total getEnityFromLogBySVChn(final int svcid,
			final String channel) {
		Payment_total r = null;
		if (channel == null)
			return r;

		Payment_totalDAO DAO = PaymentLogDAO();
		String TABLENAME2 = DAO.TABLEYY();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false) {
				createNoUniqueTable(DAO, TABLENAME2);
				return r;
			}
			r = DAO.selectBySvcidChannel(svcid, channel, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
		return r;
	}

	// types begin

	// ======= sql 操作
	public static void insertPTotal(Payment_total pt) {
		if (pt != null) {

		}
	}

	public static void updatePTotal(Payment_total pt) {
		if (pt != null) {

		}
	}

	public static void recordInLog(Payment_total pt) {
		if (pt != null) {
			insertUpdateYYYYByLog(pt);
		}
	}

	public static Payment_total getEnityByChannel(int svcid, String channel) {
		Payment_total r = getEnityFromLogBySVChn(svcid, channel);
		return r;
	}

	// ======= method 方法

	static Payment_total changeMoneyByMonth(Payment_total pt, int mval,
			int month) {
		if (pt == null || mval <= 0)
			return pt;
		switch (month) {
		case 1:
			int january = pt.getJanuary();
			january += mval;
			pt.setJanuary(january);
			break;
		case 2:
			int february = pt.getFebruary();
			february += mval;
			pt.setFebruary(february);
			break;
		case 3:
			int march = pt.getMarch();
			march += mval;
			pt.setMarch(march);
			break;
		case 4:
			int april = pt.getApril();
			april += mval;
			pt.setApril(april);
			break;
		case 5:
			int may = pt.getMay();
			may += mval;
			pt.setMay(may);
			break;
		case 6:
			int june = pt.getJune();
			june += mval;
			pt.setJune(june);
			break;
		case 7:
			int july = pt.getJuly();
			july += mval;
			pt.setJuly(july);
			break;
		case 8:
			int august = pt.getAugust();
			august += mval;
			pt.setAugust(august);
			break;
		case 9:
			int september = pt.getSeptember();
			september += mval;
			pt.setSeptember(september);
			break;
		case 10:
			int october = pt.getOctober();
			october += mval;
			pt.setOctober(october);
			break;
		case 11:
			int november = pt.getNovember();
			november += mval;
			pt.setNovember(november);
			break;
		case 12:
			int december = pt.getDecember();
			december += mval;
			pt.setDecember(december);
			break;
		case 0:
			break;
		default:
			break;
		}
		return pt;
	}

	public static Payment_total createNew(int svcid, String channel, int money,
			final int curMonth) {
		int january = 0;
		int february = 0;
		int march = 0;
		int april = 0;
		int may = 0;
		int june = 0;
		int july = 0;
		int august = 0;
		int september = 0;
		int october = 0;
		int november = 0;
		int december = 0;
		Payment_total pt = Payment_total.newPayment_total(0, svcid, channel,
				january, february, march, april, may, june, july, august,
				september, october, november, december);
		pt = changeMoneyByMonth(pt, money, curMonth);
		return pt;
	}

	public static void recordPTotal(int svcid, String channel, int val,
			int month) {
		Payment_total pt = null;
		pt = getEnityByChannel(svcid, channel);
		if (pt == null) {
			pt = createNew(svcid, channel, val, month);
		} else {
			pt = changeMoneyByMonth(pt, val, month);
		}
		recordInLog(pt);
	}
	// types end

}
