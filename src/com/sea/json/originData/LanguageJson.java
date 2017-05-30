package com.sea.json.originData;

import java.util.Map;

import com.bowlong.util.MapEx;
import com.sea.channel.ChannelCfg;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class LanguageJson {

	static final String Json_Path_Zh = "json/language/zh.json";
	static final String Json_Path_ZhFt = "json/language/zhft.json";
	static final String Json_Path_En = "json/language/en.json";
	static final String Json_Path_Famv = "json/language/famv.json";

	public static final String Mail_title_sys = "mail_title_sys";
	public static final String Mail_title_toppl = "mail_title_toppl";
	public static final String Mail_title_topcl = "mail_title_topcl";
	public static final String Mail_title_pay1 = "mail_title_pay1";
	public static final String Mail_title_pay2 = "mail_title_pay2";
	public static final String Mail_title_pay3 = "mail_title_pay3";
	
	public static final String Mail_ms_pay1 = "mail_ms_pay1";
	public static final String Mail_ms_pay2 = "mail_ms_pay2";
	public static final String Mail_ms_pay3 = "mail_ms_pay3";
	public static final String Mail_ms_pay4 = "mail_ms_pay4";
	public static final String Mail_ms_pay5 = "mail_ms_pay5";
	
	public static final String Mail_ms_dam = "mail_ms_dam";
	public static final String Mail_ms_exp = "mail_ms_exp";
	public static final String Mail_ms_gold = "mail_ms_gold";
	public static final String Mail_ms_oil = "mail_ms_oil";
	public static final String Mail_ms_worker = "mail_ms_worker";
	public static final String Mail_ms_hero = "mail_ms_hero";
	public static final String Mail_ms_energy = "mail_ms_energy";
	public static final String Mail_ms_ship = "mail_ms_ship";
	public static final String Mail_ms_spell = "mail_ms_spell";
	public static final String Mail_ms_toppl = "mail_ms_toppl";
	public static final String Mail_title_toppl2 = "mail_title_toppl2";
	public static final String Mail_ms_topcl = "mail_ms_topcl";
	
	public static final String Mail_title_midAutumn = "mail_title_midAutumn";
	public static final String Mail_ms_midAutumn = "mail_ms_midAutumn";
	public static final String Mail_title_payEnergy = "mail_title_payEnergy";
	public static final String Mail_ms_payEnergy = "mail_ms_payEnergy";

	public static final String Hero_gid_ = "hero_gid_";
	public static final String Energy_gid_ = "energy_gid_";
	public static final String Spell_gid_ = "spell_gid_";
	public static final String Error_ = "error_";
	public static final String Ship_gid_ = "ship_gid_";

	static Map mapZh = null;
	static Map mapEn = null;
	static Map mapZhft = null;
	static Map mapYueNan = null;

	public static Map getMapDataByChannel(String strChn) {
		if(strChn == null)
			return null;
		strChn = strChn.trim();
		return getMapDataByChn(strChn);
	}
	public static Map getMapDataByChn(final String strChn) {
		Map map_ = null;
		switch (strChn) {
		case ChannelCfg.IOSGAT:
			if (mapZhft == null) {
				mapZhft = UtileTools.readStrMap(Json_Path_ZhFt);
			}
			map_ = mapZhft;
			break;
		case ChannelCfg.GOOGLEPLAY:
			if (mapEn == null) {
				mapEn = UtileTools.readStrMap(Json_Path_En);
			}
			map_ = mapEn;
			break;
		case ChannelCfg.Famv:
		case ChannelCfg.Famvios:
			if (mapYueNan == null) {
				mapYueNan = UtileTools.readStrMap(Json_Path_Famv);
			}
			map_ = mapYueNan;
			break;
		default:
			if (mapZh == null) {
				mapZh = UtileTools.readStrMap(Json_Path_Zh);
			}
			map_ = mapZh;
			break;
		}
		return map_;
	}
	
	public static String getStrValByChn(final String strChn,String key) {
		Map map  = getMapDataByChannel(strChn);
		String strVal = MapEx.getString(map, key);
		return strVal;
	}
}
