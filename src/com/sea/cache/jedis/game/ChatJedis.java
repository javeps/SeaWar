package com.sea.cache.jedis.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.util.MapEx;
import com.sea.cache.jedis.origin.JedisList;
import com.sea.db.bean.Chat;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class ChatJedis extends GameBaseJedis implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(ChatJedis.class);

	public static Chat getValObj(String strVal) {
		Chat r = null;
		Map map_ = UtileTools.strToMap(strVal);
		int ccid = MapEx.getInt(map_, "ccid");
		if (ccid <= 0)
			return r;
		int id = MapEx.getInt(map_, "id");
		int type = MapEx.getInt(map_, "type");
		String content = MapEx.getString(map_, "content");
		long create_time = MapEx.getLong(map_, "create_time");
		int fromId = MapEx.getInt(map_, "fromId");
		String fromName = MapEx.getString(map_, "fromName");
		int toId = MapEx.getInt(map_, "toId");
		String toName = MapEx.getString(map_, "toName");
		String clancid = MapEx.getString(map_, "clancid");
		
		r = Chat.newChat(id, ccid, type, content, create_time, fromId,
				fromName, toId, toName,clancid);
		return r;
	}

	// =========== method

	public static void setDataChat(Chat item) {
		if (item != null) {
			int type = item.getType();
			String key = getKeyChat(type);
			setDataInListBy(key, item);
		}
	}

	public static void setListChat(List<Chat> origin) {
		if (origin != null && origin.size() > 0) {
			for (Chat item : origin) {
				setDataChat(item);
			}
		}
	}

	public static void deleteDataChat(Chat item) {
		if (item != null) {
			int type = item.getType();
			String key = getKeyChat(type);
			String vals = getValStr(item);
			JedisList.delValsInList(key, vals);
		}
	}
	
	private static List<Chat> getList(String key) {
		List<Chat> r = null;
		boolean isHasKey = JedisList.isHasKey(key);
		if (isHasKey) {
			r = new ArrayList<Chat>();
		}
		List<String> list = JedisList.getListAllBy(key);
		if (list != null && list.size() > 0) {
			if (r != null) {
				r = new ArrayList<Chat>();
			}
			for (String itemKey : list) {
				Chat item = getValObj(itemKey);
				if (item == null)
					continue;
				r.add(item);
			}
		}
		return r;
	}

	public static List<Chat> getList(int type) {
		String key = getKeyChat(type);
		List<Chat> r = getList(key);
		return r;
	}
}
