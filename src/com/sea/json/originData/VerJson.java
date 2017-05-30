package com.sea.json.originData;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bowlong.util.MapEx;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class VerJson {

	private static final String Json_Path_VER = "json/gate/ver.json";

	private static final String VJ_VER = "ver";
	private static final String VJ_SVCIDS = "svcids";
	private static final String VJ_UPURL = "upurl";

	public static Map readMapData() {
		String path = Json_Path_VER;
		Map r = UtileTools.readStrMap(path);
		return r;
	}

	public static Map getMapByChn(String chn) {
		Map map = null;
		Map origin = readMapData();
		String strChnMap = MapEx.getString(origin, chn);
		map = UtileTools.strToMap(strChnMap);
		UtileTools.clearMap(origin);
		return map;
	}

	// ===================

	public static String getVerByChn(String chn) {
		Map map = getMapByChn(chn);
		String v = MapEx.getString(map, VJ_VER); 
		UtileTools.clearMap(map);
		return v;
	}

	@SuppressWarnings("unchecked")
	public static String getSvesByChn(String chn) {
		String r = "";
		Map map = getMapByChn(chn);
		List list = MapEx.getList(map, VJ_SVCIDS);
		List svesList = new ArrayList();
		for (Object object : list) {
			int ids = (int) object;
			Map svmap = SVJson.getSVMapBySvcid(ids);
			if (svmap == null)
				continue;
			svesList.add(svmap);
		}
		r = UtileTools.listToStr(svesList);
		UtileTools.clearMap(map);
		UtileTools.clearList(list);
		UtileTools.clearList(svesList);
		return r;
	}

	public static String getStrUrlForUpVer(String chn) {
		Map map = getMapByChn(chn);
		String r = MapEx.getString(map, VJ_UPURL);
		if (!"".equals(r)) {
			try {
				r = URLDecoder.decode(r, "utf-8");
			} catch (UnsupportedEncodingException e) {
			}
		}
		UtileTools.clearMap(map);
		return r;
	}

	// =======游戏中需要验证版本的资源

	private static final String Json_Path_VERSION_CONTENT = "json/gate/version_content.json";
	private static final String Ver_Dirtyword = "ver_dirtyword";
	private static final String Ver_Activities = "ver_activities";
	private static final String Ver_AmryData = "ver_AmryData";
	private static final String Ver_BuildData = "ver_BuildData";
	private static final String Path_Cont_Activities = ActivitiesJson.Path_Head
			+ "cfgActivity";

	private static final String Paht_Cont_Dirtyword = "json/gate/dirtyword.txt";

	public static Map readContentMapData() {
		String path = Json_Path_VERSION_CONTENT;
		Map r = UtileTools.readStrMap(path);
		return r;
	}

	public static int getVerDirtyword() {
		Map map = readContentMapData();
		int r = MapEx.getInt(map, Ver_Dirtyword);
		UtileTools.clearMap(map);
		return r;
	}

	public static String getContDirtyword() {
		String r = "";
		r = UtileTools.readStr2(Paht_Cont_Dirtyword);
		return r;
	}

	public static int getVerActiviites() {
		Map map = readContentMapData();
		int r = MapEx.getInt(map, Ver_Activities);
		UtileTools.clearMap(map);
		return r;
	}

	public static byte[] getContByteActivities(String strChn) {
		String path = Path_Cont_Activities + strChn;
		byte[] r = UtileTools.readBytes(path);
		if (r == null) {
			r = new byte[0];
		}
		return r;
	}

	public static int getVerAmryData() {
		Map map = readContentMapData();
		int r = MapEx.getInt(map, Ver_AmryData);
		UtileTools.clearMap(map);
		return r;
	}

	public static byte[] getContByteAmryData() {
		String path = "json/game/local/cfgWarUnit";
		byte[] r = UtileTools.readBytes2(path);
		if (r == null) {
			r = new byte[0];
		}
		return r;
	}
	
	public static int getVerBuildData() {
		Map map = readContentMapData();
		int r = MapEx.getInt(map, Ver_BuildData);
		UtileTools.clearMap(map);
		return r;
	}

	public static byte[] getContByteBuildData() {
		String path = "json/game/local/cfgBuilding";
		byte[] r = UtileTools.readBytes2(path);
		if (r == null) {
			r = new byte[0];
		}
		return r;
	}
}
