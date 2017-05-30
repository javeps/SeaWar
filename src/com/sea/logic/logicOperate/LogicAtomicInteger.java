package com.sea.logic.logicOperate;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sea.cache.jedis.DCfgJedis;
import com.sea.db.entity.PaymentEntity;
import com.sea.db.entity.PlayerEntity;
import com.sea.tools.CounterInteger;

//用于数据下发
@SuppressWarnings({ "rawtypes", "unchecked" })
public class LogicAtomicInteger implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(LogicAtomicInteger.class);

	static final CounterInteger counter_ucid = new CounterInteger(0);
	static final CounterInteger counter_pcid = new CounterInteger(0);
	static final CounterInteger counter_hcid = new CounterInteger(0);
	static final CounterInteger counter_mcid = new CounterInteger(0);
	static final CounterInteger counter_chancid = new CounterInteger(0);
	static final CounterInteger counter_chatcid = new CounterInteger(0);
	static final CounterInteger counter_pchnuuid = new CounterInteger(0);
	static final CounterInteger counter_pslfuuid = new CounterInteger(0);

	static public final int Type_Ucid = 1;
	static public final int Type_Pcid = 2;
	static public final int Type_Hcid = 3;
	static public final int Type_Mcid = 4;
	static public final int Type_Chancid = 5;
	static public final int Type_Chatcid = 6;
	static public final int Type_Pchnuuid = 7;
	static public final int Type_Pslfuuid = 8;

	static boolean isInit = false;

	public static void init() {
		if (isInit)
			return;
		isInit = true;

		Map map = DCfgJedis.getMapConfig();
		int ucid = DCfgJedis.getIntValInMap(map, DCfgJedis.M_K_Ucid);
		int pcid = DCfgJedis.getIntValInMap(map, DCfgJedis.M_K_Pcid);
		int hcid = DCfgJedis.getIntValInMap(map, DCfgJedis.M_K_Hcid);
		int mcid = DCfgJedis.getIntValInMap(map, DCfgJedis.M_K_PMcid);
		int chancid = DCfgJedis.getIntValInMap(map, DCfgJedis.M_K_Clan_C);
		int chatcid = DCfgJedis.getIntValInMap(map, DCfgJedis.M_K_Chatcid);
		int pchnuuid = DCfgJedis.getIntValInMap(map, DCfgJedis.M_K_Pay_C_U);
		int pslfuuid = DCfgJedis.getIntValInMap(map, DCfgJedis.M_K_Pay_S_U);

		counter_chancid.getAndSet(chancid);
		counter_chatcid.getAndSet(chatcid);
		counter_hcid.getAndSet(hcid);
		counter_mcid.getAndSet(mcid);
		counter_pchnuuid.getAndSet(pchnuuid);
		counter_pcid.getAndSet(pcid);
		counter_pslfuuid.getAndSet(pslfuuid);
		counter_ucid.getAndSet(ucid);
	}

	public static void save() {

		saveMapInFile();

		Map map = DCfgJedis.getMapConfig();
		int ucid = counter_ucid.get();
		int pcid = counter_pcid.get();
		int hcid = counter_hcid.get();
		int mcid = counter_mcid.get();
		int chancid = counter_chancid.get();
		int chatcid = counter_chatcid.get();
		int pchnuuid = counter_pchnuuid.get();
		int pslfuuid = counter_pslfuuid.get();

		map.put(DCfgJedis.M_K_Ucid, ucid);
		map.put(DCfgJedis.M_K_Pcid, pcid);
		map.put(DCfgJedis.M_K_Hcid, hcid);
		map.put(DCfgJedis.M_K_PMcid, mcid);
		map.put(DCfgJedis.M_K_Clan_C, chancid);
		map.put(DCfgJedis.M_K_Chatcid, chatcid);
		map.put(DCfgJedis.M_K_Pay_C_U, pchnuuid);
		map.put(DCfgJedis.M_K_Pay_S_U, pslfuuid);

		DCfgJedis.setMapConfig(map);
	}

	static void saveMapInFile() {
		DCfgJedis.recordInFile();
	}

	public static int getValByType(int type) {
		init();
		int r = 0;
		switch (type) {
		case Type_Chancid:
			r = counter_chancid.incrementAndGet();
			break;
		case Type_Chatcid:
			r = counter_chatcid.incrementAndGet();
			break;
		case Type_Hcid:
			r = counter_hcid.incrementAndGet();
			break;
		case Type_Mcid:
			r = counter_mcid.incrementAndGet();
			break;
		case Type_Pchnuuid:
			r = counter_pchnuuid.incrementAndGet();
			break;
		case Type_Pcid:
			r = counter_pcid.incrementAndGet();
			break;
		case Type_Pslfuuid:
			r = counter_pslfuuid.incrementAndGet();
			break;
		case Type_Ucid:
			r = counter_ucid.incrementAndGet();
			break;
		default:
			break;
		}
		return r;
	}

	static public void resetPcid() {
		int maxId = PlayerEntity.getMaxPcid();
		int curId = counter_pcid.get();
		if (maxId > curId) {
			counter_pcid.set(maxId);
			counter_ucid.set(maxId);
		}
	}

	static public void resetPayUUid(int svcid) {
		if (svcid <= 0)
			return;
		int maxId = PaymentEntity.getLastPaymentBySvcid(svcid);
		int curId = counter_pslfuuid.get();
		if (maxId > curId) {
			counter_pslfuuid.set(maxId);
			counter_pchnuuid.set(maxId);
		}
	}

	/** 服务器ID <= 0 时，不会修复充值的标识  **/
	static public void resetAll(int svcid) {
		resetPcid();
		resetPayUUid(svcid);
	}
}
