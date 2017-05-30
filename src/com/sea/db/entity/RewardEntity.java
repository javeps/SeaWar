package com.sea.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sea.cache.jedis.game.RewardJedis;
import com.sea.db.bean.Reward;
import com.sea.db.dao.RewardDAO;
import com.sea.db.internal.RewardInternal;
//import com.sea.content.AppContext;

//seawar2_design - reward
@SuppressWarnings({ "static-access" })
public class RewardEntity extends RewardInternal {
	static Log log = LogFactory.getLog(RewardEntity.class);

	public static final RewardEntity my = new RewardEntity();

	static RewardDAO RewardDAO = null;

	public static RewardDAO RewardDAO() {
		if (RewardDAO == null)
			RewardDAO = new RewardDAO(com.sea.content.AppContext.ds());
		return RewardDAO;
	}

	// static RewardDAO RewardDAO99 = null;
	// public static RewardDAO RewardDAO99() {
	// if( RewardDAO99 == null)
	// RewardDAO99 = new RewardDAO(com.sea.content.AppContext.ds99());
	// return RewardDAO99;
	// }

	public static void insertMmTry(final Reward reward) {
		RewardDAO DAO = RewardDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asynchronousInsert(reward, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	static RewardDAO RewardLogDAO = null;

	public static RewardDAO logRewardDAO() {
		if (RewardLogDAO == null)
			RewardLogDAO = new RewardDAO(com.sea.content.AppContext.logDS());
		return RewardLogDAO;
	}

	public static void insertLogMmTry(final Reward reward) {
		RewardDAO DAO = logRewardDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asynchronousInsert(reward, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin
	// ======================
	public static void insertReward(Reward r) {
		if (r != null) {
			RewardJedis.setDataReward(r);
		}
	}

	public static void deleteReward(Reward r) {
		if (r != null) {
			RewardJedis.deleteData(r);
		}
	}

	public static void deleteTableBySql(List<String> tableTimeList) {
		if (tableTimeList != null && tableTimeList.size() > 0) {
			try {
				RewardDAO DAO = logRewardDAO();
				StringBuffer sb = new StringBuffer();
				for (String tableTimer : tableTimeList) {
					String table = RewardDAO.TABLE + tableTimer;
					sb.append("DROP TABLE `").append(table).append("`;");
				}
				String sql = sb.toString();
				DAO.execute(sql);
			} catch (Exception e) {
				log.info(e2s(e));
			}
		}
	}

	// ======================
	public static Reward createNew(int pcid, String pname, int type,
			String reward) {
		long createtime = System.currentTimeMillis();
		Reward r = Reward.newReward(0, pcid, pname, type, reward, createtime);
		return r;
	}

	public static void createNewInsert(int pcid, String pname, int type,
			String reward) {
		Reward r = createNew(pcid, pname, type, reward);
		insertReward(r);
	}

	public static void deleteRewardTable(List<String> tableTimeList) {
		deleteTableByRedis(tableTimeList);
		// deleteTableBySql(tableList);
	}

	private static void deleteTableByRedis(List<String> tableTimeList) {
		RewardJedis.deleteReward(tableTimeList);
	}
	// types end

}
