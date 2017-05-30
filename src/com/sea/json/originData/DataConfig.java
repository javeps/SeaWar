package com.sea.json.originData;

import java.util.Map;

import com.sea.tools.UtileTools;

@SuppressWarnings({ "rawtypes"})
public class DataConfig {
//	public static final String Json_Path_Config = "json/data/config.json";
	public static final String Json_Path_Data = "json/data/data.json";

	public static Map readMapData() {
		return UtileTools.readStrMap(Json_Path_Data);
	}

	public static void writeMapData(Map map) {
		UtileTools.writeMapStr(Json_Path_Data, map);
	}
}
