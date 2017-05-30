package com.sea.json.originData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bowlong.util.MapEx;
import com.sea.content.Svc;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class AdminAccountJson {

	static List listAccount = null;

	public static List readListData() {
		if (listAccount == null) {
			String path = "json/gate/account.json";
			listAccount = UtileTools.readStrList(path);
		}
		return listAccount;
	}

	static public Map getAccount(String lgId, String pwd) {
		List list = readListData();
		if (Svc.isEmpty(list))
			return new HashMap();
		int len = list.size();
		for (int i = 0; i < len; i++) {
			Map vMap = (Map) list.get(i);
			String vLgId = MapEx.getString(vMap, "lgID");
			String vLgPwd = MapEx.getString(vMap, "lgPwd");
			if (vLgId.equalsIgnoreCase(lgId) && vLgPwd.equalsIgnoreCase(pwd))
				return vMap;
		}
		return new HashMap();
	}

	static public Map getAccountBySession(String session) {
		List list = readListData();
		if (Svc.isEmpty(list))
			return new HashMap();
		int len = list.size();
		for (int i = 0; i < len; i++) {
			Map vMap = (Map) list.get(i);
			String vSes = MapEx.getString(vMap, "session");
			if (vSes.equalsIgnoreCase(session))
				return vMap;
		}
		return new HashMap();
	}
}