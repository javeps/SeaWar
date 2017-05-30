package com.sea.cache.jedis.admin;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.util.MapEx;
import com.bowlong.util.NewMap;
import com.sea.cache.jedis.origin.JedisHash;
import com.sea.content.Svc;
import com.sea.db.bean.Admin;
import com.sea.db.bean.Payment_total;
import com.sea.db.entity.Payment_totalEntity;
import com.sea.logic.session.LogicalSession;
import com.sea.tools.UtileTools;

@SuppressWarnings({"rawtypes","unchecked"})
public class AdminJson implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(AdminJson.class);

	static final public String key_admin = "k:admin";
	static final public String key_admin_lgid = "k:admin:lgid";

	public static Admin toObjByStrVal(String strVal) {
		Map map_ = UtileTools.strToMap(strVal);
		return toObjByMap(map_);
	}

	public static Admin toObjByMap(Map map) {
		if (map == null)
			return null;
		int id = MapEx.getInt(map, "id");
		if (id == 0)
			return null;

		String name = MapEx.getString(map, "name");
		String lgID = MapEx.getString(map, "lgID");
		String lgPwd = MapEx.getString(map, "lgPwd");
		int post = MapEx.getInt(map, "post");
		String powers = MapEx.getString(map, "powers");
		Date lastLogin = MapEx.getDate(map, "lastLogin");
		int status = MapEx.getInt(map, "status");
		return Admin.newAdmin(id, name, lgID, lgPwd, post, powers, lastLogin,
				status);
	}

	static public void initSomeAdmin() {
		if (JedisHash.isHasHash(key_admin, "1"))
			return;
		addAdmin(1, "admin", "admin", "12345670", 0, "", 0);
		addAdmin(2, "九越", "jiuyue", "jiuyue123", 1, "", 0);
	}

	static public void saveAdmin(Admin admin) {
		if(admin == null)
			return;
		Date lastLogin = new Date();
		admin.setLastlogin(lastLogin);
		
		String lgId = admin.getLgid();
		String field = admin.getId() + "";
		Map mapAdmin = admin.toBasicMap();
		mapAdmin.put("lastLogin", admin.getLastlogin().getTime());
		String val = UtileTools.mapToStr(mapAdmin);
		JedisHash.setKFV(key_admin, field, val);
		JedisHash.setKFV(key_admin_lgid, lgId, field);
	}
	
	static public void addAdmin(int id, String name, String lgId, String lgPwd,
			int post, String powers, int status) {
		Date lastLogin = new Date();
		Admin admin = Admin.newAdmin(id, name, lgId, lgPwd, post, powers,
				lastLogin, status);
		String field = admin.getId() + "";
		Map mapAdmin = admin.toBasicMap();
		mapAdmin.put("lastLogin", admin.getLastlogin().getTime());
		String val = UtileTools.mapToStr(mapAdmin);
		JedisHash.setKFV(key_admin, field, val);
		JedisHash.setKFV(key_admin_lgid, lgId, field);
	}

	static public Admin getAdmin(String lgId, String lgpwd) {
		String idStr = JedisHash.getValHash(key_admin_lgid, lgId);
		Admin a = getAdmin(idStr);
		if (a != null) {
			if (a.getLgpwd().equals(lgpwd)) {
				saveAdmin(a);
				return a;
			}
		}
		return null;
	}

	static public Admin getAdmin(String idStr) {
		String valStr = JedisHash.getValHash(key_admin, idStr);
		Admin a = toObjByStrVal(valStr);
		if (a != null) {
			return a;
		}
		return null;
	}

	static public Map<String, Object> getInfoByChn(Admin admin, String chnStr,
			int svcid) {
		if (admin == null)
			return null;

		int v = LogicalSession.getNumOnline();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("now_time", Svc.nowYearMMddHHmmss());
		result.put("online", v);
		result.put("regist", AdminRecord.getRealNum(chnStr));
		try {
			Payment_total ptotal = Payment_totalEntity.getEnityFromLogBySVChn(
					svcid, chnStr);
			if (ptotal == null) {
				result.put("totalMap", NewMap.create());
			} else {
				result.put("totalMap", ptotal.toBasicMap());
			}
		} catch (Exception e) {
			log.error(UtileTools.ex2s(e));
		}
		
		return result;
	}
}
