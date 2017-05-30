package com.sea.cache.process;

import gen_b2g.serv.bean.ConstantType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.lang.PStr;
import com.sea.cache.jedis.game.ChatJedis;
import com.sea.content.Svc;
import com.sea.db.bean.Chat;

public class ProChat implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(ProChat.class);

	static final Map<String, Chat> chatCache = Svc.newSortedMap();
	static final Map<Integer, Set<String>> typeCache = new ConcurrentHashMap<Integer, Set<String>>();

	static final List<Chat> pblist = new CopyOnWriteArrayList<Chat>();

	static String getKey(Chat pb) {
		if (pb == null)
			return "";
		return getKey(pb.getType(), pb.getCreate_time());
	}

	static String getKey(int type, long ctime) {
		return PStr.b(type + "_").e(ctime);
	}

	static public void loadCache(List<Chat> origin) {
		if (Svc.isEmpty(origin))
			return;
		for (Chat item : origin) {
			loadCache(item);
		}
	}

	static public void loadCache(Chat pb) {
		if (pb == null)
			return;
		int type = pb.getType();
		String k = getKey(pb);
		chatCache.put(k, pb);
		Set<String> set = typeCache.get(type);
		if (set == null)
			set = new HashSet<String>();
		set.add(k);
		typeCache.put(type, set);
	}

	static public void clearCache(List<Chat> origin) {
		if (Svc.isEmpty(origin))
			return;
		for (Chat item : origin) {
			clearCache(item);
		}
	}

	static public void clearCache(Chat pb) {
		if (pb == null)
			return;
		int pcid = pb.getType();
		String k = getKey(pb);
		chatCache.remove(k);
		Set<String> set = typeCache.get(pcid);
		if (set == null || set.isEmpty())
			return;
		int len = set.size();
		if (len > 1) {
			set.remove(k);
			typeCache.put(pcid, set);
		} else {
			typeCache.remove(pcid);
		}
	}

	static public List<String> getListBySet(int type) {
		Set<String> set = typeCache.get(type);
		if (Svc.isEmpty(set))
			return null;
		List<String> list = new ArrayList<String>();
		list.addAll(set);
		return list;
	}

	// 取得数据

	public static List<Chat> getListByType(int type) {
		List<String> set = getListBySet(type);
		List<Chat> r = null;
		if (set != null) {
			r = new ArrayList<Chat>();
			Chat chat = null;
			for (String item : set) {
				chat = chatCache.get(item);
				if (chat == null)
					continue;
				r.add(chat);
			}
		} else {
			r = ChatJedis.getList(type);
			loadCache(r);
		}
		return r;
	}

	public static List<Chat> getList() {
		List<Chat> r = null;
		int priType = ConstantType.Type_Chat_Pri;
		List<Chat> priList = getListByType(priType);
		if (priList != null && priList.size() > 0) {
			r = new ArrayList<Chat>();
			r.addAll(priList);
		}
		int pubType = ConstantType.Type_Chat_Pub;
		List<Chat> pubList = getListByType(pubType);
		if (pubList != null && pubList.size() > 0) {
			if (r == null)
				r = new ArrayList<Chat>();
			r.addAll(pubList);
		}
		int clanType = ConstantType.Type_Chat_Clan;
		List<Chat> clanList = getListByType(clanType);
		if (clanList != null && clanList.size() > 0) {
			if (r == null)
				r = new ArrayList<Chat>();
			r.addAll(clanList);
		}
		return r;
	}

	public static List<Chat> getList(int pcid, int type, String clancid) {
		List<Chat> r = null;
		List<Chat> origin = getListByType(type);
		List<Chat> tmp = new ArrayList<Chat>();
		if (origin != null && origin.size() > 0) {
			for (Chat item : origin) {
				if (item == null)
					continue;
				switch (type) {
				case ConstantType.Type_Chat_Pri:
					int fpcid = item.getFromid();
					int tpcid = item.getToid();
					if (fpcid == pcid || tpcid == pcid) {
						tmp.add(item);
					}
					break;
				case ConstantType.Type_Chat_Pub:
					tmp.add(item);
					break;
				case ConstantType.Type_Chat_Clan:
					String ccid = item.getClancid();
					if (clancid.equals(ccid)) {
						tmp.add(item);
					}
					break;
				default:
					break;
				}
			}
		}
		if (tmp.size() > 0) {
			r = tmp;
		}
		return r;
	}

	static public void deleteDataChat(Chat pb) {
		clearCache(pb);
		boolean isHas = pblist.contains(pb);
		if (isHas)
			pblist.remove(pb);
		else
			ChatJedis.deleteDataChat(pb);
	}

	static public void setUpBuild(Chat pb) {
		if (pb == null)
			return;
		loadCache(pb);
		if (!pblist.contains(pb))
			pblist.add(pb);
	}

	static public void saveData() {
		if (Svc.isEmpty(pblist))
			return;
		ChatJedis.setListChat(pblist);
		pblist.clear();
	}

	static public void clearData() {
		saveData();
		chatCache.clear();
		typeCache.clear();
	}
}