package com.sea.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import gen_b2g.serv.bean.NPlayers;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sea.cache.process.ProUser;
import com.sea.db.bean.User;
import com.sea.db.dao.UserDAO;
import com.sea.db.internal.UserInternal;
import com.sea.logic.logicOperate.LogicAtomicInteger;
//import com.sea.content.AppContext;
import com.sea.logic.logicOperate.LogicPlayer;

//seawar_design - user
@SuppressWarnings({ "static-access" })
public class UserEntity extends UserInternal {
	static Log log = LogFactory.getLog(UserEntity.class);

	public static final UserEntity my = new UserEntity();

	static UserDAO UserDAO = null;

	public static UserDAO UserDAO() {
		if (UserDAO == null)
			UserDAO = new UserDAO(com.sea.content.AppContext.ds());
		return UserDAO;
	}

	// static UserDAO UserDAO99 = null;
	// public static UserDAO UserDAO99() {
	// if( UserDAO99 == null)
	// UserDAO99 = new UserDAO(com.sea.content.AppContext.ds99());
	// return UserDAO99;
	// }

	public static void insertMmTry(final User user) {
		UserDAO DAO = UserDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asynchronousInsert(user, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin
	// ================== 数据库操作 begin ==================
	// 插入数据
	public static void insertUser(User r2) {
		if (r2 != null) {
			// r2 = r2.insert();
			ProUser.inUser(r2);
		}
	}

	// 更新数据
	public static void updateUser(User r2) {
		if (r2 != null) {
			ProUser.upUser(r2);
		}
	}

	// 删除数据
	public static void deleteUserPlayer(User origin) {
		if (origin != null) {
			int ucid = origin.getUcid();
			String pcids = origin.getPcids();
			PlayerEntity.deleteListByUcid(pcids, ucid);
		}
	}

	public static void deleteUserOnly(User origin) {
		if (origin != null) {
			ProUser.delUser(origin);
		}
	}

	// ================== 数据库操作 end ==================

	// 取得用户对象
	public static User getUser(String uid, String pwd) throws Exception {
		return ProUser.getUser(uid, pwd);
	}

	public static List<User> getUsers(String uid, String pwd) throws Exception {
		return ProUser.getUsers(uid, pwd);
	}

	public static User getUser(String uid, String uuid, String pwd,
			String name, String model, String version) {
		User u = null;
		try {
			u = getUser(uid, pwd);
		} catch (Exception e) {
			return null;
		}
		if (u == null) {
			u = createNewUserInsert(uid, uuid, pwd, name, model, version);
		} else {
			u.setModel(model);
			u.setUuid(uuid);
			u.setVersion(version);
			long now_time = System.currentTimeMillis();
			u.setLogin_time(now_time);
			updateUser(u);
		}
		return u;
	}

	public static User getUserByUcid(int ucid) {
		User u = null;
		u = ProUser.getUserByUcid(ucid);
		return u;
	}

	// 创建新的用户
	public static int getUcidByLguid() {
		return LogicAtomicInteger.getValByType(LogicAtomicInteger.Type_Ucid);
	}

	public static User createNewUser(String uid, String uuid, String pwd,
			String name, String model, String version) {
		int uhashcode = getUcidByLguid();
		if (uhashcode == 0)
			return null;
		int ucid = uhashcode;
		long login_time = System.currentTimeMillis();
		String remain = "";
		String pcids = "";
		User r2 = User.newUser(0, ucid, pwd, name, uid, uuid, login_time,
				model, version, remain, pcids);
		return r2;
	}

	// 创建新的用户并且插入数据库
	public static User createNewUserInsert(String uid, String uuid, String pwd,
			String name, String model, String version) {
		User r2 = createNewUser(uid, uuid, pwd, name, model, version);
		insertUser(r2);
		return r2;
	}

	// 登录用户
	public static User onLoginUser(String uid, String uuid, String pwd,
			String name, final String channel, String model, String version,
			int servid, NPlayers players) {
		User u = getUser(uid, uuid, pwd, name, model, version);
		if (u == null)
			return u;
		LogicPlayer.getNPlayersByUser(u, servid, channel, players);
		return u;
	}

	//

	// types end
}
