package com.sea.logic.logicOperate;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.util.DateEx;
import com.bowlong.util.NewMap;
import com.sea.cache.jedis.game.PVJedis;
import com.sea.db.bean.Player;
import com.sea.handler.game.tcpGame.GameTcpHandle;
import com.sea.json.originData.CfgJson;

@SuppressWarnings("rawtypes")
public class LogicPV implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(LogicPV.class);

	static Map<Integer, Long> mapPvGemMonitor = new HashMap<Integer, Long>();
	static Map<Integer, Long> mapPvResMonitor = new HashMap<Integer, Long>();
	static long monitorTime = DateEx.TIME_DAY * 3;
	static boolean isInit = false;

	static public void addMonitorGems(int pcid) {
		boolean isHas = mapPvGemMonitor.containsKey(pcid);
		if (isHas)
			return;

		long now_time = System.currentTimeMillis();
		now_time += monitorTime;
		mapPvGemMonitor.put(pcid, now_time);
	}

	static public void addMonitorRes(int pcid) {
		boolean isHas = mapPvResMonitor.containsKey(pcid);
		if (isHas)
			return;

		long now_time = System.currentTimeMillis();
		now_time += monitorTime;
		mapPvResMonitor.put(pcid, now_time);
	}

	static public void initMonitor() {
		if (isInit)
			return;
		isInit = true;
		// addMonitorGems(51373);// 文武 状元
		// addMonitorGems(1374);// 金刚战神
		// addMonitorGems(1472695661);// 金刚无情
		// addMonitorGems(1842264536);// 金刚无敌
		// addMonitorGems(-1235768002);// 天涯客
		// addMonitorGems(6547);// 金刚贾少(砖石很多2014-08-08)
		//2440 金刚*苍狼 ,33038 金剛心飛揚,3663 冬瓜,422 巫马恨 
		// 43345 疾风轮舞曲 ,48244 54088 ,50449 许，51368 ♀好好先生,51376 车 子 默,54998 欧阳依琪
		//5560 拽牛,824 逍遥cm朋克,1487 隔岸观猩猩，-404038181 老棒头,-1797135700 金刚永恒
		// 51930 元昊，-1088233807 金刚ソ浅鈊，-880915146 金刚_Mac，1498287356 金刚凌风
		// 1691513858 魔天堡 ，30480  豺狼，30515 金刚敢死队，3981 长孙妙，4133 翁大少
		// 43318 金刚-.坏坏,49596 金刚丶玲玲,52207 海鸟总霸主,52561 唯我独尊,52826 柏丝
		// 5328 征服,53437 司空乘,53439 司空三和,53442 金刚萌萌哒,55779 俞洋，56044 龙飞云
		// 56405 司空霜,6190 海盗王他爹,54590 凸b男卜丸
		addMonitorGems(-1614825154);// 金金
	}

	static public void exceMonitor(Player p, NewMap map) throws Exception {

		boolean isOpenMonitor = CfgJson.isOpenMonitor();
		if (!isOpenMonitor)
			return;

		initMonitor();

		if (p == null)
			return;
		String pvStr = GameTcpHandle.service._params(map);
		exceMonitorGems(p, pvStr);
		exceMonitorRes(p, pvStr);
	}

	/****** 监控 宝石 ******/
	static void exceMonitorGems(Player p, String pvStr) throws Exception {
		int pcid = p.getPcid();
		boolean isHas = mapPvGemMonitor.containsKey(pcid);
		if (!isHas) {
			int gems = p.getCrystal();
			if (gems >= 2000) {
				addMonitorGems(pcid);
			} else {
				return;
			}
		}
		long now_time = System.currentTimeMillis();
		long max_time = mapPvGemMonitor.get(pcid);
		if (max_time < now_time) {
			mapPvGemMonitor.remove(pcid);
			return;
		}

		PVJedis.setPv(pcid, pvStr, true);
	}

	/****** 监控 资源 ******/
	static void exceMonitorRes(Player p, String pvStr) throws Exception {
		int pcid = p.getPcid();
		int max = 1500000;
		boolean isHas = mapPvResMonitor.containsKey(pcid);
		if (!isHas) {
			int res = p.getStored_gold();
			if (res > max) {
				addMonitorRes(pcid);
			} else {
				res = p.getStored_oil();
				if (res > max) {
					addMonitorRes(pcid);
				} else {
					return;
				}
			}
		}
		long now_time = System.currentTimeMillis();
		long max_time = mapPvResMonitor.get(pcid);
		if (max_time < now_time) {
			mapPvResMonitor.remove(pcid);
			return;
		}

		PVJedis.setPv(pcid, pvStr, false);
	}

	// dateStr yyyyMMdd
	static public List<String> getListValBy(String dateStr, int pcid,
			boolean isGems) {
		return PVJedis.getListPvBy(dateStr, pcid, isGems);
	}

	static public List<String> getListAll() {
		return PVJedis.getListPvAll();
	}
	
}