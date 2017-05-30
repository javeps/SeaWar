package com.sea.json.originData;

import java.util.List;
import java.util.Map;

import com.bowlong.util.MapEx;
import com.sea.tools.ReadWriteString;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class SVJson {

	public static final String Json_Path_SV = "json/gate/sv.json";

	private static final String SVJ_SVCID = "svcid";
	static final String SVJ_NAME = "name";
	static final String SVJ_POST = "port";
	private static final String SVJ_HOST = "host";
	static final String BHJ_STATUS = "status";

	public static List readListData() {
		String path = Json_Path_SV;
		List r = UtileTools.readStrList(path);
		return r;
	}

	public static Map getSVMapByHost(String host) {
		Map map = null;
		List list = readListData();
		for (Object object : list) {
			Map tmp = (Map) object;
			String tmpHost = MapEx.getString(tmp, SVJ_HOST);
			if (tmpHost.equals(host)) {
				map = tmp;
				break;
			}
		}
		UtileTools.clearList(list);
		return map;
	}

	public static Map getSVMapBySvcid(int svcid) {
		Map map = null;
		List list = readListData();
		for (Object object : list) {
			Map tmp = (Map) object;
			int tmpSvcid = MapEx.getInt(tmp, SVJ_SVCID);
			if (tmpSvcid == svcid) {
				map = tmp;
				break;
			}
		}
		UtileTools.clearList(list);
		return map;
	}

	public static String getSVJsonByPath() {
		return ReadWriteString.readStr(Json_Path_SV);
	}

	// ===================

	public static int getSvcidByHost(String host) {
		Map map = getSVMapByHost(host);
		int v = MapEx.getInt(map, SVJ_SVCID); 
		UtileTools.clearMap(map);
		return v;
	}
}
