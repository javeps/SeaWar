package com.sea;

import gen_b2g.serv.bean.ConstantType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import org.dom4j.io.XMLResult;

import com.bowlong.io.FileEx;
import com.bowlong.lang.NumEx;
import com.bowlong.lang.StrEx;
import com.bowlong.security.MD5;
import com.bowlong.util.DateEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.NewMap;
import com.sea.cache.jedis.game._GameKeys;
import com.sea.cache.jedis.origin.JedisList;
import com.sea.channel.ChannelCfg;
import com.sea.channel.famv.PayFamv;
import com.sea.channel.mm.PayMoblieMM;
import com.sea.channel.nd91.HexBin;
import com.sea.channel.nd91.PayNd91;
import com.sea.channel.qihu360.OAuth360;
import com.sea.channel.qihu360.PayQiHu360;
import com.sea.channel.uc.PayUC;
import com.sea.channel.wo.PayUnicom;
import com.sea.channel.xiaomi.PayXiaoMi;
import com.sea.content.AppContext;
import com.sea.content.Svc;
import com.sea.db.entity.ClanEntity;
import com.sea.handler.request.HttpReqWeb;
import com.sea.handler.response.ResponseWeb;
import com.sea.json.npc.NpcExport;
import com.sea.json.npc.NpcImport;
import com.sea.json.originData.ActivitiesJson;
import com.sea.json.originData.BaseHeroJson;
import com.sea.json.originData.ClanJson;
import com.sea.json.originData.EnergyJson;
import com.sea.json.originData.IAPVerify;
import com.sea.json.originData.LanguageJson;
import com.sea.json.originData.SVJson;
import com.sea.json.recordError.RecordByte;
import com.sea.localEntry.DBBuilding;
import com.sea.logic.logicEntity.HeroMax;
import com.sea.logic.logicOperate.LogicHero;
import com.sea.logic.logicOperate.LogicPV;
import com.sea.logic.logicOperate.LogicPlayer;
import com.sea.tools.CounterInteger;
import com.sea.tools.EnAndDecryption;
import com.sea.tools.FormulaGame;
import com.sea.tools.FormulaPay;
import com.sea.tools.FormulaTools;
import com.sea.tools.FormulaWar;
import com.sea.tools.NameRandom;
import com.sea.tools.ReadWriteString;
import com.sea.tools.UtileTools;

@SuppressWarnings("all")
public class DemoData extends Svc {

	public static void main(String[] args) throws Exception {
		readReward4Rank();
		// readReward4Gems();
	}

	static void readReward4Rank() {
		String dataString = "20150514";
		String key = _GameKeys.fmt(_GameKeys.Pattern_Key_Reward, dataString);
		List<String> allVals = JedisList.getListAllBy(key);
		System.out.println("====== lens =====" + allVals.size());
		String url = "http://112.124.56.63:10000/addres?sign=sw&type=2&svcid=1000&resType=c&resVal=${1}&strList=${2}";
		for (String item : allVals) {
			if (item.indexOf("您在本次") != -1) {
				Map map = UtileTools.strToMap(item);
				int pcid = MapEx.getInt(map, "pcid");
				String reward = MapEx.getString(map, "reward");
				int index = reward.indexOf("宝石x");
				if (index != -1) {
					reward = reward.substring(index + 3);
					String urlTmp = StrEx.fmt(url, reward, pcid);
					System.out.println("====== 奖励发钻石 begin ======");
					String result = HttpReqWeb.sendGetStrByHC2(urlTmp, "utf-8", new HashMap<String, String>());
					System.out.println(urlTmp);
					System.out.println(result);
					System.out.println("====== 奖励发钻石  end ======");
				}
			}
		}
	}

	static void readReward4Gems() {
		String dataString = "20150514";
		String key = _GameKeys.fmt(_GameKeys.Pattern_Key_Reward, dataString);
		List<String> allVals = JedisList.getListAllBy(key);
		System.out.println("====== lens =====" + allVals.size());
		for (String item : allVals) {
			Map map = UtileTools.strToMap(item);
			int pcid = MapEx.getInt(map, "pcid");
			String reward = MapEx.getString(map, "reward");
			int index = reward.indexOf("宝石x");
			if (index != -1) {
				reward = reward.substring(index + 3);
				System.out.println(map);
			}
		}
	}
}