package com.sea.cache.jedis.game;

import gen_b2g.serv.bean.ConstantType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.util.MapEx;
import com.sea.cache.jedis.origin.JedisHash;
import com.sea.cache.jedis.origin.KeyValEntity;
import com.sea.db.bean.Player_mail;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class MailJedis extends GameBaseJedis implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(MailJedis.class);

	public static Player_mail toObjByStrVal(String strVal) {
		Player_mail r = null;
		Map map_ = UtileTools.strToMap(strVal);
		int mcid = MapEx.getInt(map_, "mcid");
		if (mcid <= 0)
			return r;
		int id = MapEx.getInt(map_, "id");
		int type = MapEx.getInt(map_, "type");
		String title = MapEx.getString(map_, "title");
		String content = MapEx.getString(map_, "content");
		long create_time = MapEx.getLong(map_, "create_time");
		int fromId = MapEx.getInt(map_, "fromId");
		String fromName = MapEx.getString(map_, "fromName");

		int toId = MapEx.getInt(map_, "toId");
		String toName = MapEx.getString(map_, "toName");
		boolean isRead = MapEx.getBoolean(map_, "isRead");

		r = Player_mail.newPlayer_mail(id, mcid, type, title, content,
				create_time, fromId, fromName, toId, toName, isRead);
		return r;
	}

	// =========== method

	public static void setDataMail(Player_mail item) {
		if (item != null) {
			String key = getKey(item);
			String field = item.getMcid() + "";
			String val = getValStr(item);
			JedisHash.setKFV(key, field, val);
		}

		// 保存时间邮件关系
		setRelationMT(item);
	}

	public static void updateMail(Player_mail item) {
		setDataMail(item);
	}

	public static void deleteMail(Player_mail item) {
		if (item != null) {
			String key = getKey(item);
			String field = item.getMcid() + "";
			JedisHash.delByKFields(key, field);
		}
	}

	public static void setListMail(List<Player_mail> origin) {
		if (origin == null || origin.size() <= 0)
			return;
		List<KeyValEntity> list = new ArrayList<KeyValEntity>();
		for (Player_mail item : origin) {
			String key = getKey(item);
			String field = item.getMcid() + "";
			if (field == null || "".equals(field.trim()))
				continue;
			String val = getValStr(item);
			KeyValEntity k = new KeyValEntity(key, field, val);
			list.add(k);
		}
		setListDataByKFV(list);
	}

	public static Player_mail getMail(int pcid, int mcid, int ptype) {
		Player_mail r = null;
		String field = mcid + "";
		String key = getKeyMailToPlayer(pcid);
		String val = JedisHash.getValByKF(key, field);
		r = toObjByStrVal(val);
		if (r == null) {
			List<Player_mail> syslist = getListSystem();
			if (syslist != null) {
				for (Player_mail item : syslist) {
					int imcid = item.getMcid();
					if (mcid == imcid) {
						r = item;
						break;
					}
				}
			}
		}
		if (r == null) {
			if (ptype == ConstantType.Type_User_GM) {
				List<Player_mail> syslist = getListServer();
				if (syslist != null) {
					for (Player_mail item : syslist) {
						int imcid = item.getMcid();
						if (mcid == imcid) {
							r = item;
							break;
						}
					}
				}
			}
		}
		return r;
	}

	public static List<Player_mail> getListByType(int type) {
		List<Player_mail> r = null;
		String key = getKeyMailByType(type);
		List<String> valList = JedisHash.getListHashVals(key);
		if (valList != null && valList.size() > 0) {
			r = new ArrayList<Player_mail>();
			for (String val : valList) {
				Player_mail item = toObjByStrVal(val);
				if (item == null)
					continue;
				r.add(item);
			}
		}
		return r;
	}

	public static List<Player_mail> getListSystem() {
		List<Player_mail> r = null;
		List<Player_mail> origin = new ArrayList<Player_mail>();
		List<Player_mail> sysList = getListByType(ConstantType.Type_Mail_System);
		if (sysList != null)
			origin.addAll(sysList);
		if (origin.size() > 0)
			r = origin;
		return r;
	}

	public static List<Player_mail> getListServer() {
		List<Player_mail> r = null;
		List<Player_mail> origin = new ArrayList<Player_mail>();
		List<Player_mail> serList = getListByType(ConstantType.Type_Mail_Server);
		if (serList != null)
			origin.addAll(serList);
		if (origin.size() > 0)
			r = origin;
		return r;
	}

	public static List<Player_mail> getListByPlayer(int pcid) {
		List<Player_mail> r = null;
		String key = getKeyMailToPlayer(pcid);
		List<String> valList = JedisHash.getListHashVals(key);
		if (valList != null && valList.size() > 0) {
			r = new ArrayList<Player_mail>();
			for (String val : valList) {
				Player_mail item = toObjByStrVal(val);
				if (item == null)
					continue;
				r.add(item);
			}
		}
		return r;
	}

	public static List<Player_mail> getListAllByPcid(int pcid) {
		List<Player_mail> r = null;
		List<Player_mail> origin = new ArrayList<Player_mail>();
		List<Player_mail> sysList = getListByType(ConstantType.Type_Mail_System);
		List<Player_mail> pList = getListByPlayer(pcid);
		if (sysList != null)
			origin.addAll(sysList);
		if (pList != null)
			origin.addAll(pList);
		if (origin.size() > 0)
			r = origin;
		return r;
	}

	// 设置用户和时间的关系
	static void setRelationMT(Player_mail item) {
		if (item != null) {
			String valKey = getKey(item);
			String field = item.getMcid() + "";
			long bt = item.getCreate_time();
			String timeStr = UtileTools.getStrYYYYMMDDBy(bt);
			String key = getKeyMPTimeRelation(timeStr);
			JedisHash.setKFV(key, field, valKey);
		}
	}
}
