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
public class DemoMain extends Svc {

	public static void main(String[] args) throws Exception {
		// importNpc();
		// exportNpc();
		// timelong();
		// timeWeek();
		// timeFmt();
		// testLvlByExp();
		// testExpByLvl();
		// comTo();
		// rateNum();
		// errorByteRead();
		// testEnergyEgid();
		// testEnergyNum();
		testIosIAP();
		// baiDutest();
		// payTest();
		// testGot();
		// testPlayerName();
		// testEnZhName();
		// qh360();
		// qh360Pay();
		// testFormulaPay();
		// nd91VerifyHashToMD5Hex("91SDK");
		// nd91Pay();
		// xiaomiPay();
		// formulaW();
		// formulaK();
		// formulaGame();
		// ucPay();
		// ucPayD();
		// chinaStr();
		// testListToArray();
		// testVectory();
		// testUrlEncode();
		// testReadDb();
		// testActivities();
		// testNPCPath();
		// testReplace();
		// testDownLine();
		// testVerifyIAP();
		// AtomicIntegerTest.testTreadAtomicInteger();
		// googlePlay();
		// testList();
		// testYu();
		// testTime();
		// testAppHostList();
		// testMMReadXML2();
		// testReadInt();
		// readPv();
		// readPvOne();
		// String v = PayTY.MD5("00", "20131206demo668");
		// System.out.println(v);
		// testTreeMap();
		// testListRm();
		// woPay();
		// yuenanPlay();
		// yuenanPlay2();
		// subStr();
		// payMMCheck();
		// payWoCheck();
		// yuenanCheck();
		// resetHero();
		// testSignTime();
		// testFormulaWar();
		// testNpcRenown();
	}

	static void subStr() throws Exception {
		String strV = "swar11227736";
		int index = strV.indexOf("swar112");
		strV = strV.substring(index + 7);
		System.out.println(Integer.parseInt(strV));
	}

	static void yuenanPlay() throws Exception {
		String host = "";
		host = "http://112.124.56.63:10000/payFamv";
		host = "http://210.211.121.5:10000/payFAMV";
		String json = "";
		Map<String, String> parames = new HashMap<String, String>();
		long now_time = System.currentTimeMillis();
		String sign = EnAndDecryption.encoder(now_time + "",
				ConstantType.Type_Sign_Key);
		sign = sign.replaceAll("\\+", "a");
		sign = sign.replaceAll("\\-", "r");
		System.out.println(sign);
		parames.put("sign", sign);
		parames.put("svcid", "4000");
		parames.put("pcid", "1001");// 1027
		parames.put("chn", ChannelCfg.Famv);
		parames.put("iden", "4");
		parames.put("gems", "120");
		String v = HttpReqWeb.sendPostStr(host, "", parames);
		System.out.println(v);
	}

	static void yuenanPlay2() throws Exception {
		String host = "";
		host = "http://112.124.56.63:10000/payAppvnios";
		host = "http://210.211.121.5:10000/payAppvnios";
		String json = "";
		Map<String, String> parames = new HashMap<String, String>();
		long now_time = System.currentTimeMillis();
		String sign = EnAndDecryption.encoder(now_time + "",
				ConstantType.Type_Sign_Key);
		sign = sign.replaceAll("\\+", "a");
		sign = sign.replaceAll("\\-", "r");
		System.out.println(sign);
		parames.put("sign", sign);
		parames.put("svcid", "4000");
		parames.put("pcid", "7058");// 1027
		parames.put("chn", ChannelCfg.Appvnios_card);
		parames.put("chnBillNo", "C925481150A56DBF");
		String v = HttpReqWeb.sendPostStr(host, "", parames);
		System.out.println(v);
	}

	static void yuenanCheck() throws Exception {
		String quey = FileEx.readAll("json/origin.xml");
		String strSign = ResponseWeb.getParamsVal(quey, "sign");
		boolean v = PayFamv.checkPayAppvnios(quey, strSign);
		System.out.println(v);
	}

	static void woPay() {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><callbackReq><orderid>0000000000000swar1127788</orderid><ordertime>20140812142941</ordertime><cpid>90199874965</cpid><appid>9019987496520140730231936904400</appid><fid>12243</fid><consumeCode>9019987496520140730231936904400001</consumeCode><payfee>100</payfee><payType>1</payType><hRet>0</hRet><status>00000</status><signMsg>a578c4c033c06737e7e8804a9f47e21b</signMsg></callbackReq>";
		String v = PayUnicom.checkPay(xml);
		System.out.println(v);
	}

	static void testTreeMap() {
		Map<Integer, String> map = newSortedMap();
		map.put(1, "a");
		map.put(2, "b");
		map.put(3, "c");
		map.put(4, "d");
		map.put(5, "e");
		map.put(6, "a");
		List<String> list = new ArrayList<String>();
		list.addAll(map.values());
		for (String string : list) {
			System.out.println(string);
		}
	}

	static void importNpc() {
		NpcImport.getBeAttNPC(null, null, null, null, null);
	}

	static void exportNpc() {
		int townlvl = 9;
		int pos = 4;
		boolean isOpen = false;
		NpcExport.exportOne(townlvl, pos, isOpen);
	}

	static void rename() {
		String path = "json/top/";
		File f = new File(path);
		File[] list = f.listFiles();
		if (list != null && list.length > 0) {
			for (File file : list) {
				String name = file.getName();
				String rname = name.replaceAll("NPC", "Top");
				String rpath = path + rname;
				File rf = new File(rpath);
				file.renameTo(rf);
			}
		}
	}

	static void errorByteRead() {
		// byte[] bytes="ccc".getBytes();
		RecordByte demo = RecordByte.getInstanse();
		try {
			// demo.write(bytes);
			List<byte[]> result = demo.readAll();
			if (!result.isEmpty()) {
				int len = 0;
				for (byte[] _bytes : result) {
					len++;
					try {
						System.out.println("========");
						NewMap map = com.toosNets.bio2.B2Helper.toMap(_bytes);
						System.out.println(map);
					} catch (Exception e) {
						System.out.println("========" + len);
					}
				}
			}
		} catch (Exception e) {
			System.out.println((UtileTools.ex2s(e)));
		}
	}

	static void timelong() {
		long cur_time = 1400069246048l;
		Date d = new Date(cur_time);
		System.out.println(tFmt(d));
	}

	static void timeFmt() {
		String time = "2014-08-25 10:00:00";
		Date d = DateEx.parse(time, fmt_yyyy_MM_dd_HH_mm);
		System.out.println(tFmt(d));
		System.out.println(d.getTime());
	}

	static void timeWeek() {
		long n = System.currentTimeMillis();
		int d = 1;
		n += DateEx.TIME_DAY * d;
		Date now_time = new Date(n);
		int week = DateEx.week(now_time);
		System.out.println("星期:" + week);
	}

	static void time() {
		long Time_Hour = DateEx.TIME_HOUR;
		long Time_Minute = DateEx.TIME_MINUTE;
		long Time_Second = DateEx.TIME_SECOND;

		Date now_date = new Date();
		int year = DateEx.year(now_date);
		int month = DateEx.month(now_date);
		int h = DateEx.hour(now_date);
		int m = DateEx.minute(now_date);
		int s = DateEx.second(now_date);

		long curTime = h * Time_Hour + m * Time_Minute + s * Time_Second;

		int nextH = ((h / 2) + 1) * 2;
		long initialDelay = nextH * Time_Hour - curTime;
		long delay = 2 * Time_Hour;

		int maxDay = DateEx.dayNum(year, 12);
		System.out.println(initialDelay + "=====" + delay + "====" + maxDay
				+ "====" + month);
	}

	static void keySecret() {
		String s = System.currentTimeMillis() + "";
		System.out.println(s);
		String key = "123";
		String en = EnAndDecryption.encoder(s, key);
		System.out.println(en);
		String outS = EnAndDecryption.decoder(en, key);
		String testS = EnAndDecryption
				.decoder(
						"11812442207194555555555455-+2+4+6+8+10+12+14+15902689088913+3127925",
						ConstantType.Type_Sign_Key);
		System.out.println(testS);
	}

	static void testMap() {
		Map<Integer, Integer> map__ = new HashMap<Integer, Integer>();
		map__.put(1, 1);
		map__.put(2, 1);
		map__.put(3, 1);
		map__.put(4, 1);
		map__.put(5, 1);
		map__.put(1, 1);
		System.out.println(map__.size());
	}

	static void testLvlByExp() {
		int lvl = LogicPlayer.getLvl(200, 0);
		System.out.println(lvl);
	}

	static void testExpByLvl() {
		int lvl = 500;
		int exp = 0;
		for (int i = lvl; i > 0; i--) {
			exp += i * 50;
		}
		System.out.println(exp);
	}

	static void comTo() {
		String s = "0.8.1";
		String r = "0.9";
		int c = s.compareToIgnoreCase(r);
		System.out.println(c);
	}

	static void testEnergyEgid() {
		/*
		 * for (int i = 100; i < 1000;) { if (i < 500) i += 50; else if (i <
		 * 900) i += 20; else i += 2; int v = EnergyJson.getValByAddDam(i);
		 * System.out.println("dam =====" + v);
		 * 
		 * int vv = EnergyJson.getValByAddHP(i); System.out.println("HP  ====="
		 * + vv);
		 * 
		 * int vvv = EnergyJson.getValByAddSpeed(i);
		 * System.out.println("Spd =====" + vvv); }
		 */
		int rate = 1000;
		boolean isGetEnergy = true;
		int egid = EnergyJson.getEgidByRate(rate, isGetEnergy, false);
		System.out.println("===egid==" + egid);
	}

	static void testIosIAP() throws Exception {
		String base64Code = "ewoJInNpZ25hdHVyZSIgPSAiQXEzZkR3Y0JwOHlkUU9PS2E4Y05HRWNucGZ"
				+ "kTGo3bWhHeUMwb2psaHBzTlc1eDFKeUtLZWpsU2JYL0NsTEJrUHNTY3Z5R1BldGtYajVG"
				+ "YWpNbFIyK0xMOWhHcDQ5Q3NnZ0ZZZjhhMkJoNmRmZ2tGTDViY1JlRUQxVU5YL2hjb1RLc"
				+ "GZPclJJb2NmU0Q2eUdpRVk1RCtDMDhpT1NDR1AwT3AydndVNzhCeUFnK0FBQURWekNDQT"
				+ "FNd2dnSTdvQU1DQVFJQ0NHVVVrVTNaV0FTMU1BMEdDU3FHU0liM0RRRUJCUVVBTUg4eEN"
				+ "6QUpCZ05WQkFZVEFsVlRNUk13RVFZRFZRUUtEQXBCY0hCc1pTQkpibU11TVNZd0pBWURW"
				+ "UVFMREIxQmNIQnNaU0JEWlhKMGFXWnBZMkYwYVc5dUlFRjFkR2h2Y21sMGVURXpNREVHQ"
				+ "TFVRUF3d3FRWEJ3YkdVZ2FWUjFibVZ6SUZOMGIzSmxJRU5sY25ScFptbGpZWFJwYjI0Z1"
				+ "FYVjBhRzl5YVhSNU1CNFhEVEE1TURZeE5USXlNRFUxTmxvWERURTBNRFl4TkRJeU1EVTF"
				+ "ObG93WkRFak1DRUdBMVVFQXd3YVVIVnlZMmhoYzJWU1pXTmxhWEIwUTJWeWRHbG1hV05o"
				+ "ZEdVeEd6QVpCZ05WQkFzTUVrRndjR3hsSUdsVWRXNWxjeUJUZEc5eVpURVRNQkVHQTFVR"
				+ "UNnd0tRWEJ3YkdVZ1NXNWpMakVMTUFrR0ExVUVCaE1DVlZNd2daOHdEUVlKS29aSWh2Y0"
				+ "5BUUVCQlFBRGdZMEFNSUdKQW9HQkFNclJqRjJjdDRJclNkaVRDaGFJMGc4cHd2L2NtSHM"
				+ "4cC9Sd1YvcnQvOTFYS1ZoTmw0WElCaW1LalFRTmZnSHNEczZ5anUrK0RyS0pFN3VLc3Bo"
				+ "TWRkS1lmRkU1ckdYc0FkQkVqQndSSXhleFRldngzSExFRkdBdDFtb0t4NTA5ZGh4dGlJZ"
				+ "ERnSnYyWWFWczQ5QjB1SnZOZHk2U01xTk5MSHNETHpEUzlvWkhBZ01CQUFHamNqQndNQX"
				+ "dHQTFVZEV3RUIvd1FDTUFBd0h3WURWUjBqQkJnd0ZvQVVOaDNvNHAyQzBnRVl0VEpyRHR"
				+ "kREM1RllRem93RGdZRFZSMFBBUUgvQkFRREFnZUFNQjBHQTFVZERnUVdCQlNwZzRQeUdV"
				+ "akZQaEpYQ0JUTXphTittVjhrOVRBUUJnb3Foa2lHOTJOa0JnVUJCQUlGQURBTkJna3Foa"
				+ "2lHOXcwQkFRVUZBQU9DQVFFQUVhU2JQanRtTjRDL0lCM1FFcEszMlJ4YWNDRFhkVlhBZV"
				+ "ZSZVM1RmFaeGMrdDg4cFFQOTNCaUF4dmRXLzNlVFNNR1k1RmJlQVlMM2V0cVA1Z204d3J"
				+ "Gb2pYMGlreVZSU3RRKy9BUTBLRWp0cUIwN2tMczlRVWU4Y3pSOFVHZmRNMUV1bVYvVWd2"
				+ "RGQ0TndOWXhMUU1nNFdUUWZna1FRVnk4R1had1ZIZ2JFL1VDNlk3MDUzcEdYQms1MU5QT"
				+ "TN3b3hoZDNnU1JMdlhqK2xvSHNTdGNURXFlOXBCRHBtRzUrc2s0dHcrR0szR01lRU41Ly"
				+ "tlMVFUOW5wL0tsMW5qK2FCdzdDMHhzeTBiRm5hQWQxY1NTNnhkb3J5L0NVdk02Z3RLc21"
				+ "uT09kcVRlc2JwMGJzOHNuNldxczBDOWRnY3hSSHVPTVoydG04bnBMVW03YXJnT1N6UT09"
				+ "IjsKCSJwdXJjaGFzZS1pbmZvIiA9ICJld29KSW05eWFXZHBibUZzTFhCMWNtTm9ZWE5sT"
				+ "FdSaGRHVXRjSE4wSWlBOUlDSXlNREUwTFRBekxUSTRJREF6T2pFeU9qRTNJRUZ0WlhKcF"
				+ "kyRXZURzl6WDBGdVoyVnNaWE1pT3dvSkluVnVhWEYxWlMxcFpHVnVkR2xtYVdWeUlpQTl"
				+ "JQ0prTkRZeE5UVXlaREJpWkdSbE5qZ3pZVE01TkdKa01tTmpNamMyTTJGaE5qUXhNMkZq"
				+ "T1RKaElqc0tDU0p2Y21sbmFXNWhiQzEwY21GdWMyRmpkR2x2YmkxcFpDSWdQU0FpTVRBd"
				+ "01EQXdNREV3TmpFeE1UTXlPQ0k3Q2draVluWnljeUlnUFNBaU1DNDVNU0k3Q2draWRISm"
				+ "hibk5oWTNScGIyNHRhV1FpSUQwZ0lqRXdNREF3TURBeE1EWXhNVEV6TWpnaU93b0pJbkY"
				+ "xWVc1MGFYUjVJaUE5SUNJeElqc0tDU0p2Y21sbmFXNWhiQzF3ZFhKamFHRnpaUzFrWVhS"
				+ "bExXMXpJaUE5SUNJeE16azJNREF4TlRNM01UQTRJanNLQ1NKMWJtbHhkV1V0ZG1WdVpHO"
				+ "XlMV2xrWlc1MGFXWnBaWElpSUQwZ0lqbEVOamhGTUROR0xUaEJNRVl0TkVJNU15MUNRel"
				+ "UyTFRaQ01ESkJORU5CTmpVMk9TSTdDZ2tpY0hKdlpIVmpkQzFwWkNJZ1BTQWlZMjl0TG1"
				+ "OdmIyeGhjR1V1YVhOc1lXNWtkMkZ5TG1jeElqc0tDU0pwZEdWdExXbGtJaUE5SUNJNE5E"
				+ "VTVPREkyT1RZaU93b0pJbUpwWkNJZ1BTQWlZMjl0TG1OdmIyeGhjR1V1YVhOc1lXNWtkM"
				+ "kZ5TG1kaGRDSTdDZ2tpY0hWeVkyaGhjMlV0WkdGMFpTMXRjeUlnUFNBaU1UTTVOakF3TV"
				+ "RVek56RXdPQ0k3Q2draWNIVnlZMmhoYzJVdFpHRjBaU0lnUFNBaU1qQXhOQzB3TXkweU9"
				+ "DQXhNRG94TWpveE55QkZkR012UjAxVUlqc0tDU0p3ZFhKamFHRnpaUzFrWVhSbExYQnpk"
				+ "Q0lnUFNBaU1qQXhOQzB3TXkweU9DQXdNem94TWpveE55QkJiV1Z5YVdOaEwweHZjMTlCY"
				+ "m1kbGJHVnpJanNLQ1NKdmNtbG5hVzVoYkMxd2RYSmphR0Z6WlMxa1lYUmxJaUE5SUNJeU"
				+ "1ERTBMVEF6TFRJNElERXdPakV5T2pFM0lFVjBZeTlIVFZRaU93cDkiOwoJImVudmlyb25"
				+ "tZW50IiA9ICJTYW5kYm94IjsKCSJwb2QiID0gIjEwMCI7Cgkic2lnbmluZy1zdGF0dXMi"
				+ "ID0gIjAiOwp9";
		base64Code = "ewoJInNpZ25hdHVyZSIgPSAiQXBkeEpkdE53UFUyckE1L2NuM2tJTzFPVGsyNWZlREthMGFhZ3l5UnZlV2xjRmxnbHY2UkY2em5raUJTM3VtOVVjN3BWb2IrUHFaUjJUOHd5VnJITnBsb2YzRFgzSXFET2xXcSs5MGE3WWwrcXJSN0E3ald3dml3NzA4UFMrNjdQeUhSbmhPL0c3YlZxZ1JwRXI2RXVGeWJpVTFGWEFpWEpjNmxzMVlBc3NReEFBQURWekNDQTFNd2dnSTdvQU1DQVFJQ0NHVVVrVTNaV0FTMU1BMEdDU3FHU0liM0RRRUJCUVVBTUg4eEN6QUpCZ05WQkFZVEFsVlRNUk13RVFZRFZRUUtEQXBCY0hCc1pTQkpibU11TVNZd0pBWURWUVFMREIxQmNIQnNaU0JEWlhKMGFXWnBZMkYwYVc5dUlFRjFkR2h2Y21sMGVURXpNREVHQTFVRUF3d3FRWEJ3YkdVZ2FWUjFibVZ6SUZOMGIzSmxJRU5sY25ScFptbGpZWFJwYjI0Z1FYVjBhRzl5YVhSNU1CNFhEVEE1TURZeE5USXlNRFUxTmxvWERURTBNRFl4TkRJeU1EVTFObG93WkRFak1DRUdBMVVFQXd3YVVIVnlZMmhoYzJWU1pXTmxhWEIwUTJWeWRHbG1hV05oZEdVeEd6QVpCZ05WQkFzTUVrRndjR3hsSUdsVWRXNWxjeUJUZEc5eVpURVRNQkVHQTFVRUNnd0tRWEJ3YkdVZ1NXNWpMakVMTUFrR0ExVUVCaE1DVlZNd2daOHdEUVlKS29aSWh2Y05BUUVCQlFBRGdZMEFNSUdKQW9HQkFNclJqRjJjdDRJclNkaVRDaGFJMGc4cHd2L2NtSHM4cC9Sd1YvcnQvOTFYS1ZoTmw0WElCaW1LalFRTmZnSHNEczZ5anUrK0RyS0pFN3VLc3BoTWRkS1lmRkU1ckdYc0FkQkVqQndSSXhleFRldngzSExFRkdBdDFtb0t4NTA5ZGh4dGlJZERnSnYyWWFWczQ5QjB1SnZOZHk2U01xTk5MSHNETHpEUzlvWkhBZ01CQUFHamNqQndNQXdHQTFVZEV3RUIvd1FDTUFBd0h3WURWUjBqQkJnd0ZvQVVOaDNvNHAyQzBnRVl0VEpyRHRkREM1RllRem93RGdZRFZSMFBBUUgvQkFRREFnZUFNQjBHQTFVZERnUVdCQlNwZzRQeUdVakZQaEpYQ0JUTXphTittVjhrOVRBUUJnb3Foa2lHOTJOa0JnVUJCQUlGQURBTkJna3Foa2lHOXcwQkFRVUZBQU9DQVFFQUVhU2JQanRtTjRDL0lCM1FFcEszMlJ4YWNDRFhkVlhBZVZSZVM1RmFaeGMrdDg4cFFQOTNCaUF4dmRXLzNlVFNNR1k1RmJlQVlMM2V0cVA1Z204d3JGb2pYMGlreVZSU3RRKy9BUTBLRWp0cUIwN2tMczlRVWU4Y3pSOFVHZmRNMUV1bVYvVWd2RGQ0TndOWXhMUU1nNFdUUWZna1FRVnk4R1had1ZIZ2JFL1VDNlk3MDUzcEdYQms1MU5QTTN3b3hoZDNnU1JMdlhqK2xvSHNTdGNURXFlOXBCRHBtRzUrc2s0dHcrR0szR01lRU41LytlMVFUOW5wL0tsMW5qK2FCdzdDMHhzeTBiRm5hQWQxY1NTNnhkb3J5L0NVdk02Z3RLc21uT09kcVRlc2JwMGJzOHNuNldxczBDOWRnY3hSSHVPTVoydG04bnBMVW03YXJnT1N6UT09IjsKCSJwdXJjaGFzZS1pbmZvIiA9ICJld29KSW05eWFXZHBibUZzTFhCMWNtTm9ZWE5sTFdSaGRHVXRjSE4wSWlBOUlDSXlNREV5TFRBM0xURXlJREExT2pVME9qTTFJRUZ0WlhKcFkyRXZURzl6WDBGdVoyVnNaWE1pT3dvSkluQjFjbU5vWVhObExXUmhkR1V0YlhNaUlEMGdJakV6TkRJd09UYzJOelU0T0RJaU93b0pJbTl5YVdkcGJtRnNMWFJ5WVc1ellXTjBhVzl1TFdsa0lpQTlJQ0l4TnpBd01EQXdNamswTkRrME1qQWlPd29KSW1KMmNuTWlJRDBnSWpFdU5DSTdDZ2tpWVhCd0xXbDBaVzB0YVdRaUlEMGdJalExTURVME1qSXpNeUk3Q2draWRISmhibk5oWTNScGIyNHRhV1FpSUQwZ0lqRTNNREF3TURBeU9UUTBPVFF5TUNJN0Nna2ljWFZoYm5ScGRIa2lJRDBnSWpFaU93b0pJbTl5YVdkcGJtRnNMWEIxY21Ob1lYTmxMV1JoZEdVdGJYTWlJRDBnSWpFek5ESXdPVGMyTnpVNE9ESWlPd29KSW1sMFpXMHRhV1FpSUQwZ0lqVXpOREU0TlRBME1pSTdDZ2tpZG1WeWMybHZiaTFsZUhSbGNtNWhiQzFwWkdWdWRHbG1hV1Z5SWlBOUlDSTVNRFV4TWpNMklqc0tDU0p3Y205a2RXTjBMV2xrSWlBOUlDSmpiMjB1ZW1Wd2RHOXNZV0l1WTNSeVltOXVkWE11YzNWd1pYSndiM2RsY2pFaU93b0pJbkIxY21Ob1lYTmxMV1JoZEdVaUlEMGdJakl3TVRJdE1EY3RNVElnTVRJNk5UUTZNelVnUlhSakwwZE5WQ0k3Q2draWIzSnBaMmx1WVd3dGNIVnlZMmhoYzJVdFpHRjBaU0lnUFNBaU1qQXhNaTB3TnkweE1pQXhNam8xTkRvek5TQkZkR012UjAxVUlqc0tDU0ppYVdRaUlEMGdJbU52YlM1NlpYQjBiMnhoWWk1amRISmxlSEJsY21sdFpXNTBjeUk3Q2draWNIVnlZMmhoYzJVdFpHRjBaUzF3YzNRaUlEMGdJakl3TVRJdE1EY3RNVElnTURVNk5UUTZNelVnUVcxbGNtbGpZUzlNYjNOZlFXNW5aV3hsY3lJN0NuMD0iOwoJInBvZCIgPSAiMTciOwoJInNpZ25pbmctc3RhdHVzIiA9ICIwIjsKfQ==";
		base64Code = "ewoJInNpZ25hdHVyZSIgPSAiQWg5bXRWMnlNdTQ0UmF6M3RHVllOaXFxRlBtbTczaVRQV2FkMmZKWGhQejdVS2hjNmY5djR4c0lGY1ZkTEFLYVM2bHVlZ1FFRkYwZ0QxVm1DZzA0OXgzOStWc1JNdXRoMG1BajFIdUYvQUNScXVPVTR1RUlFT3JLU2xFV0Y5UmFFeS8rdEM1WGxPWEhzUnpULzVCRG1JeDlGdTh6RklkQlFpRUpCaUNkVDhsSUFBQURWekNDQTFNd2dnSTdvQU1DQVFJQ0NHVVVrVTNaV0FTMU1BMEdDU3FHU0liM0RRRUJCUVVBTUg4eEN6QUpCZ05WQkFZVEFsVlRNUk13RVFZRFZRUUtEQXBCY0hCc1pTQkpibU11TVNZd0pBWURWUVFMREIxQmNIQnNaU0JEWlhKMGFXWnBZMkYwYVc5dUlFRjFkR2h2Y21sMGVURXpNREVHQTFVRUF3d3FRWEJ3YkdVZ2FWUjFibVZ6SUZOMGIzSmxJRU5sY25ScFptbGpZWFJwYjI0Z1FYVjBhRzl5YVhSNU1CNFhEVEE1TURZeE5USXlNRFUxTmxvWERURTBNRFl4TkRJeU1EVTFObG93WkRFak1DRUdBMVVFQXd3YVVIVnlZMmhoYzJWU1pXTmxhWEIwUTJWeWRHbG1hV05oZEdVeEd6QVpCZ05WQkFzTUVrRndjR3hsSUdsVWRXNWxjeUJUZEc5eVpURVRNQkVHQTFVRUNnd0tRWEJ3YkdVZ1NXNWpMakVMTUFrR0ExVUVCaE1DVlZNd2daOHdEUVlKS29aSWh2Y05BUUVCQlFBRGdZMEFNSUdKQW9HQkFNclJqRjJjdDRJclNkaVRDaGFJMGc4cHd2L2NtSHM4cC9Sd1YvcnQvOTFYS1ZoTmw0WElCaW1LalFRTmZnSHNEczZ5anUrK0RyS0pFN3VLc3BoTWRkS1lmRkU1ckdYc0FkQkVqQndSSXhleFRldngzSExFRkdBdDFtb0t4NTA5ZGh4dGlJZERnSnYyWWFWczQ5QjB1SnZOZHk2U01xTk5MSHNETHpEUzlvWkhBZ01CQUFHamNqQndNQXdHQTFVZEV3RUIvd1FDTUFBd0h3WURWUjBqQkJnd0ZvQVVOaDNvNHAyQzBnRVl0VEpyRHRkREM1RllRem93RGdZRFZSMFBBUUgvQkFRREFnZUFNQjBHQTFVZERnUVdCQlNwZzRQeUdVakZQaEpYQ0JUTXphTittVjhrOVRBUUJnb3Foa2lHOTJOa0JnVUJCQUlGQURBTkJna3Foa2lHOXcwQkFRVUZBQU9DQVFFQUVhU2JQanRtTjRDL0lCM1FFcEszMlJ4YWNDRFhkVlhBZVZSZVM1RmFaeGMrdDg4cFFQOTNCaUF4dmRXLzNlVFNNR1k1RmJlQVlMM2V0cVA1Z204d3JGb2pYMGlreVZSU3RRKy9BUTBLRWp0cUIwN2tMczlRVWU4Y3pSOFVHZmRNMUV1bVYvVWd2RGQ0TndOWXhMUU1nNFdUUWZna1FRVnk4R1had1ZIZ2JFL1VDNlk3MDUzcEdYQms1MU5QTTN3b3hoZDNnU1JMdlhqK2xvSHNTdGNURXFlOXBCRHBtRzUrc2s0dHcrR0szR01lRU41LytlMVFUOW5wL0tsMW5qK2FCdzdDMHhzeTBiRm5hQWQxY1NTNnhkb3J5L0NVdk02Z3RLc21uT09kcVRlc2JwMGJzOHNuNldxczBDOWRnY3hSSHVPTVoydG04bnBMVW03YXJnT1N6UT09IjsKCSJwdXJjaGFzZS1pbmZvIiA9ICJld29KSW05eWFXZHBibUZzTFhCMWNtTm9ZWE5sTFdSaGRHVXRjSE4wSWlBOUlDSXlNREUwTFRBMUxUSXlJREl4T2pJeE9qSXhJRUZ0WlhKcFkyRXZURzl6WDBGdVoyVnNaWE1pT3dvSkluQjFjbU5vWVhObExXUmhkR1V0YlhNaUlEMGdJakUwTURBNE1UZzRPREV4T1RFaU93b0pJblZ1YVhGMVpTMXBaR1Z1ZEdsbWFXVnlJaUE5SUNKa05EWXhOVFV5WkRCaVpHUmxOamd6WVRNNU5HSmtNbU5qTWpjMk0yRmhOalF4TTJGak9USmhJanNLQ1NKdmNtbG5hVzVoYkMxMGNtRnVjMkZqZEdsdmJpMXBaQ0lnUFNBaU1UQXdNREF3TURFeE1UYzVPVE01T0NJN0Nna2lZblp5Y3lJZ1BTQWlNUzR4SWpzS0NTSmhjSEF0YVhSbGJTMXBaQ0lnUFNBaU9EUTFPVEk0TWpVNUlqc0tDU0owY21GdWMyRmpkR2x2YmkxcFpDSWdQU0FpTVRBd01EQXdNREV4TVRjNU9UTTVPQ0k3Q2draWNYVmhiblJwZEhraUlEMGdJakVpT3dvSkltOXlhV2RwYm1Gc0xYQjFjbU5vWVhObExXUmhkR1V0YlhNaUlEMGdJakUwTURBNE1UZzRPREV4T1RFaU93b0pJblZ1YVhGMVpTMTJaVzVrYjNJdGFXUmxiblJwWm1sbGNpSWdQU0FpTXpnd1JUQXlORUl0TlVNNU55MDBPRFF3TFRrM01ERXROREZFUkVSR05qUXdNakExSWpzS0NTSnBkR1Z0TFdsa0lpQTlJQ0k0TkRVNU9ESTJPVFlpT3dvSkluWmxjbk5wYjI0dFpYaDBaWEp1WVd3dGFXUmxiblJwWm1sbGNpSWdQU0FpTkRnME16WXlOalEzSWpzS0NTSndjbTlrZFdOMExXbGtJaUE5SUNKamIyMHVZMjl2YkdGd1pTNXBjMnhoYm1SM1lYSXVaekVpT3dvSkluQjFjbU5vWVhObExXUmhkR1VpSUQwZ0lqSXdNVFF0TURVdE1qTWdNRFE2TWpFNk1qRWdSWFJqTDBkTlZDSTdDZ2tpYjNKcFoybHVZV3d0Y0hWeVkyaGhjMlV0WkdGMFpTSWdQU0FpTWpBeE5DMHdOUzB5TXlBd05Eb3lNVG95TVNCRmRHTXZSMDFVSWpzS0NTSmlhV1FpSUQwZ0ltTnZiUzVqYjI5c1lYQmxMbWx6YkdGdVpIZGhjaTVuWVhRaU93b0pJbkIxY21Ob1lYTmxMV1JoZEdVdGNITjBJaUE5SUNJeU1ERTBMVEExTFRJeUlESXhPakl4T2pJeElFRnRaWEpwWTJFdlRHOXpYMEZ1WjJWc1pYTWlPd3A5IjsKCSJlbnZpcm9ubWVudCIgPSAiU2FuZGJveCI7CgkicG9kIiA9ICIxMDAiOwoJInNpZ25pbmctc3RhdHVzIiA9ICIwIjsKfQ==";
		String tran_id = "1000000111799398";// iosgat1000000111799398
		Map<String, String> parames = new HashMap<String, String>();
		parames.put("receipt-data", base64Code);
		String host = "https://sandbox.itunes.apple.com/verifyReceipt";
		String chaUTF = "";
		String rJosn = HttpReqWeb.sendPostJson(host, parames);
		System.out.println("=============================");
		System.out.println(tran_id);
		System.out.println("=========== json sandbox back======");
		System.out.println(rJosn);
		System.out.println("=========== json sandbox back end======");

		host = "https://buy.itunes.apple.com/verifyReceipt";

		rJosn = HttpReqWeb.sendPostJson(host, parames);
		System.out.println("=========== json back======");
		System.out.println(rJosn);
		System.out.println("=========== json back end======");

		// String rDefa = HttpReqWeb.sendPostStr(host, chaUTF,
		// parames);
		// System.out.println("=========== rDefa back======");
		// System.out.println(rDefa);
		// System.out.println("=========== rDefa back end======");
	}

	static void baiDutest() throws Exception {
		Map<String, String> parames = new HashMap<String, String>();
		parames.put("wd", "65");
		parames.put("type", "1");
		String host = "http://hw.baidu.com/";
		String chaUTF = "";
		String rJosn = HttpReqWeb.sendPostJson(host, parames);
		System.out.println("=========== json back======");
		System.out.println(rJosn);
		System.out.println("=========== json back end======");

		String rDefa = HttpReqWeb.sendPostStr(host, chaUTF, parames);
		System.out.println("=========== rDefa back======");
		System.out.println(rDefa);
		System.out.println("=========== rDefa back end======");
	}

	static void payTest() {
		int svcid = 1000;
		int pcid = 2;
		String channel = ChannelCfg.PP;
		String idenStr = "com.coolape.islandwar.g1";
		// String oid = LogicOrder.createNewOrder(svcid, pcid, channel,
		// idenStr);
	}

	static void testGot() {
		String key = "mail_ms_pay2";
		String chn = ChannelCfg.IOSGAT;
		String val = LanguageJson.getStrValByChn(chn, key);
		System.out.println(StrEx.fmt(val, 500));
		val = LanguageJson.getStrValByChn(chn, "mail_ms_pay1");
		System.out.println(StrEx.fmt(val, 1000));
	}

	static void testPlayerName() {
		String path = "xing.txt";
		String t = ReadWriteString.readStr(path);
		String[] tes = t.split("\r");

		Map<String, String> map = new HashMap<String, String>();
		for (String str : tes) {
			if (str == null || "".equals(str.trim()) || "\r".equals(str))
				continue;
			str = str.trim();
			System.out.println("[" + str + "]");
			map.put(str, str);
		}
		Set<String> keys = map.keySet();
		List<String> list = new ArrayList<String>();
		list.addAll(keys);
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				int len1 = o1.length();
				int len2 = o2.length();
				if (len1 == len2) {
					return o1.compareTo(o2);
				} else if (len1 > len2) {
					return 1;
				} else if (len1 < len2) {
					return -1;
				}
				return 0;
			}
		});
		StringBuilder build = new StringBuilder();
		for (String k : list) {
			build.append(k).append("\r");
		}
		String nt = build.toString();
		ReadWriteString.writeStr("xing2.txt", nt);
	}

	static void testEnZhName() {
		String strChn = ChannelCfg.MI;
		int k = 1000;
		int w = 10000;
		int len = w;
		for (int i = 0; i < len; i++) {
			String v = NameRandom.getInstance().newName(strChn);
			System.out.println(v);
		}
	}

	static void qh360() {
		try {
			String code = "397576549f30eedcf9d78cab3d8aca0c9ce8746c18cd6d60d";
			Map map = OAuth360.getInstance().getAccessTokenByCode(code, "");
			System.out.println(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void qh360Pay() {
		try {
			String strUri = "/payQH360?order_id=1404094232588458581&app_key=286bda794c83b31348fb08b533c20689&product_id=1&amount=1&app_uid=30&app_ext1=swar1120000000037&user_id=397576549&sign_type=md5&app_order_id=swar1120000000037&gateway_flag=success&sign=f44587ca928a74b9b1cf7e56c62c86a4&sign_return=9382ccf4488bab88c8caba83b8e23944";
			PayQiHu360.checkPay(strUri);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	static void testFormulaPay() {
		// for (int i = 0; i < 1000; i += 5) {
		// System.out.println(FormulaPay.getGemByRate(i));
		// switch (i) {
		// case 10:
		// System.out.println("====" + FormulaPay.getGemByRate(6));
		// break;
		// case 30:
		// System.out.println("====" + FormulaPay.getGemByRate(30));
		// break;
		// case 70:
		// System.out.println("====" + FormulaPay.getGemByRate(68));
		// break;
		// case 130:
		// System.out.println("====" + FormulaPay.getGemByRate(128));
		// break;
		// case 330:
		// System.out.println("====" + FormulaPay.getGemByRate(328));
		// break;
		// case 650:
		// System.out.println("====" + FormulaPay.getGemByRate(648));
		// break;
		// default:
		// break;
		// }
		// }
		int money = 10;
		double rt = FormulaPay.getGemByRate(money);
		double nm = FormulaPay.getGemNormer(money);
		double df = rt - nm;
		// rt = rt * 1.3;
		System.out.println(rt);
		System.out.println(nm);
		System.out.println(df);
	}

	static void nd91VerifyHashToMD5Hex(String str) {
		String k = HexBin.md5(str);
		System.out.println("k====" + k);
	}

	static void nd91Pay() {
		try {
			String AppKey = "1442b88fdc03a1e1207c49cd3e7bf6b692badc741a861ffe";
			String strUrl = "/payNd91And?AppId=113522&ProductId=113522&Act=1&ProductName=%e6%b5%b7%e5%b2%9b%e5%a4%a7%e4%bd%9c%e6%88%98%e3%81%ae%e6%b5%b7%e5%b2%9b%e5%a5%87%e5%85%b5&ConsumeStreamId=5-34364-20140413193722-700-8484&CooOrderSerial=000000000000000swar1120000000135&Uin=203765390&GoodsId=0&GoodsInfo=%e5%ae%9d%e7%9f%b3&GoodsCount=105&OriginalMoney=7.00&OrderMoney=7.00&Note=000000000000000swar1&PayStatus=1&CreateTime=2014-04-13%2019:37:09&Sign=35c35d8157b78c945a4e9c1f80ecec18";
			HashMap<String, String> parmets = (HashMap<String, String>) ResponseWeb
					.getMapByGetKeys(strUrl, PayNd91.keyes);
			// parmets.put("AppKey", AppKey);

			String AppId = MapEx.getString(parmets, "AppId");
			String Act = MapEx.getString(parmets, "Act");
			String ProductName = MapEx.getString(parmets, "ProductName");
			String ConsumeStreamId = MapEx
					.getString(parmets, "ConsumeStreamId");
			String CooOrderSerial = MapEx.getString(parmets, "CooOrderSerial");
			String Uin = MapEx.getString(parmets, "Uin");
			String GoodsId = MapEx.getString(parmets, "GoodsId");
			String GoodsInfo = MapEx.getString(parmets, "GoodsInfo");
			String GoodsCount = MapEx.getString(parmets, "GoodsCount");
			String OriginalMoney = MapEx.getString(parmets, "OriginalMoney");
			String OrderMoney = MapEx.getString(parmets, "OrderMoney");
			String Note = MapEx.getString(parmets, "Note");
			String PayStatus = MapEx.getString(parmets, "PayStatus");
			String CreateTime = MapEx.getString(parmets, "CreateTime");
			String sign = MapEx.getString(parmets, "Sign");
			String val = String
					.format("{0}{1}{2}{3}{4}{5}{6}{7}{8}{9:0.00}{10:0.00}{11}{12}{13:yyyy-MM-dd HH:mm:ss}{14}",
							AppId, Act, ProductName, ConsumeStreamId,
							CooOrderSerial, Uin, GoodsId, GoodsInfo,
							GoodsCount, OriginalMoney, OrderMoney, Note,
							PayStatus, CreateTime, AppKey);
			System.out.println(val);
			val = AppId + Act + ProductName + ConsumeStreamId + CooOrderSerial
					+ Uin + GoodsId + GoodsInfo + GoodsCount + OriginalMoney
					+ OrderMoney + Note + PayStatus + CreateTime + AppKey;
			System.out.println(val);
			nd91VerifyHashToMD5Hex(val);
			System.out.println("====" + MD5.MD5Encode(val));
			System.out.println(sign);
			// PayNd91.checkPay(strUrl, true);
		} catch (Exception e) {
		}
	}

	static void xiaomiPay() {
		try {
			String strUri = "/payMi?appId=25048&cpOrderId=swar1120000000165&cpUserInfo=%E6%9D%8E%E7%BE%9E&orderId=21139746296934628866&orderStatus=TRADE_SUCCESS&payFee=100&payTime=2014-04-14+16%3A09%3A34&productCode=01&productCount=10&productName=%E5%AE%9D%E7%9F%B3&uid=5392922&signature=1f06bd856bed543cf96b5fe9ac6435d9a825a323";
			PayXiaoMi.checkPay(strUri);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	static void testListRm() {
		List<String> list = new CopyOnWriteArrayList<String>();
		list.add("abcd");
		list.add("abc2");
		list.add("abcd2");
		list.remove("abcd");
		for (String string : list) {
			System.out.println(string);
		}
	}

	static void formulaW() {
		int w = 10000;
		double diff = 20 * w;
		int da = 20 * w;
		int db = 30 * w;
		int ra = 2;
		int rb = 3;
		double rate = 1.15;
		for (int n = 1; n <= 200; n++) {
			int d = FormulaTools.getValByDValueSameDifferToInt(diff, da, db, n);
			int r = FormulaTools.getValByDValueSameRateToInt(rate, ra, rb, n);
			System.out
					.println("==等差==" + d + ",===等比===" + r + ",===n====" + n);
		}
	}

	static void formulaK() {
		int k = 1000;
		double diff = 45 * k;
		int da = 20 * k;
		int db = 30 * k;
		int ra = 2;
		int rb = 3;
		double rate = 1.05;
		long sumDiff = 0;

		int curMem = 20;
		int min = 0;
		int mid = 0;
		int max = 0;
		int sumDayNum = 0;
		int curDayNum = 0;

		int lvl = 40;
		int minMax = k * 50;
		int chushu = 1;
		for (int n = 0; n <= lvl; n++) {
			int nextN = n + 1;
			long d = FormulaTools.getValByDValueSameDifferToLong(diff, da, db,
					nextN);

			int cur = (int) d;

			min = (int) (cur * 0.01);
			min = min >= minMax ? minMax : min;
			mid = 2 * min;
			max = 5 * min;

			int dogval = max;

			chushu = (dogval * curMem);

			curDayNum = (int) Math.ceil(((double) cur / chushu));
			sumDayNum += curDayNum;
			sumDiff += d;
			long r = FormulaTools.getValByDValueSameRateToLong(rate, ra, rb, n);
			System.out.println("=等差=" + d + ",=等比=" + r + ",=n=" + n
					+ ",=nextN=" + nextN + ",需要天数:" + curDayNum + ",每人捐献:"
					+ dogval);
		}
		System.out.println("=====等差总和====" + sumDiff);
		System.out.println("=====升级到" + lvl + "级所需要的天数=====" + sumDayNum);

	}

	static void formulaGame() {
		Integer[] origin = { 100, 500, 1000, 3000, 5000, 300, 2000, 200, 100,
				500, 1000, 3000, 5000, 300, 2000, 200 };
		int v = FormulaGame.getClanRenown(origin);
		System.out.println(v);
	}

	static void ucPay() {
		String str = "109amount=100.00callbackInfo=custominfo=xxxxx#user=xxxxfailedDesc=gameId=123orderId=abcf1330orderStatus=SpayWay=1serverId=654ucid=123456202cb962234w4ers2aaa";
		String outStr = PayUC.getMD5Str(str);
		System.out.println(outStr);
		System.out.println(outStr.equals("e49bd00c3cf0744c7049e73e16ae8acd"));
	}

	static void ucPayD() {
		String s = "{\"sign\":\"4b52ff1d42dce0ba8af92d44f482ae4e\",\"data\":{\"failedDesc\":\"\",\"amount\":\"50.00\",\"callbackInfo\":\"swar1120000000287\",\"ucid\":\"201065281\",\"gameId\":\"539423\",\"payWay\":\"301\",\"serverId\":\"2963\",\"orderStatus\":\"S\",\"orderId\":\"201404162031186309401\"}}";
		s = "{\"sign\":\"b07673084a1ede925eb12b22b1d8df67\",\"data\":{\"amount\":\"1.00\",\"failedDesc\":\"\",\"ucid\":\"1080822\",\"callbackInfo\":\"swar1120000000860\",\"gameId\":\"539423\",\"payWay\":\"101\",\"serverId\":\"2963\",\"orderStatus\":\"S\",\"orderId\":\"201404211130406305418\"}}";
		Map<String, String> map = new HashMap<String, String>();
		Map mapData = UtileTools.strToMap(s);
		if (mapData == null || mapData.isEmpty()) {
			System.out.println(mapData);
			return;
		}
		System.out.println(mapData);
		for (String key : PayUC.keyes) {
			String val = "";
			Object o = MapEx.get(mapData, key);
			if (o instanceof Map) {
				val = UtileTools.mapToStr((Map) o);
			} else if (o instanceof List) {
				val = UtileTools.listToStr((List) o);
			} else {
				val = o.toString();
			}
			map.put(key, val);
		}
		System.out.println(map);
		System.out.println(PayUC.verify(map));
	}

	static void chinaStr() throws Exception {

	}

	static void testListToArray() throws Exception {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		String[] a = {};
		a = list.toArray(a);
		System.out.println(a.length);
		for (String item : a) {
			System.out.println(item);
		}
	}

	static void testVectory() throws Exception {
		for (int i = 0; i < 10; i++) {
			System.out.println(1 << i);
		}
		int symbol = 4;
		symbol |= 256;
		System.out.println(symbol);
		System.out.println(symbol & 4);
	}

	static void testUrlEncode() throws Exception {
		String url = "https://play.google.com/store/apps/details?id=com.coolape.islandwar.gplay";
		String enc = "utf-8";
		String v = URLEncoder.encode(url, enc);
		System.out.println(v);
	}

	static void testReadDb() throws Exception {
		List<DBBuilding> v = DBBuilding.getListInDB();
		for (DBBuilding item : v) {
			System.out.println(item.GID + "," + item.HeadquartersLevel + ","
					+ item.Lev);
		}
	}

	static void testActivities() throws Exception {
		boolean isPay = ActivitiesJson.getIsOpenPay2();
		boolean isE = ActivitiesJson.getIsOpenEnergy();
		double rate = ActivitiesJson.getMultipleHeroAttr();
		double rateBox = ActivitiesJson.getMultipleBoxEnergy();
		System.out.println("isEnergy = " + isE + ",isPay = " + isPay + ","
				+ rate + ",box = " + rateBox);
		// System.out.println(SVJson.getSvcidByHost("112.124.56.63"));
		// for (int i = 0; i < 20; i++) {
		// int hp = ClanJson.getEachRateHP();
		// int att = ClanJson.getEachRateAtt();
		// System.out.println(hp + "," + i + "," + att);
		// long gold = ClanEntity.getNextResGold(i);
		// long oil = ClanEntity.getNextResOil(i);
		// System.out.println("g=" + gold + "," + oil);
		// }
	}

	static void testEnergyNum() throws Exception {
		boolean isActivite = ActivitiesJson.getIsOpenEnergy();
		int len = 100;
		int sum = 0;
		int sumGid = 0;
		for (int i = 0; i < len; i++) {
			int rate = UtileTools.randIntK();
			int rateGid = UtileTools.randIntK();
			int gid = EnergyJson.getEgidByRate(rateGid, false, isActivite);
			int num = EnergyJson.getENumByRate(rate, isActivite);
			if (gid > 0)
				sumGid++;
			if (num > 0)
				sum++;

			System.out.println(num);
		}

		double d = (sum / 100.0) * (sumGid / 100.0);
		System.out.println("sum = " + sum + ",sumgid=" + sumGid + "," + d);
	}

	static void testNPCPath() throws Exception {
		for (int i = 2; i <= 9; i++) {
			String path = NpcExport.getJsonPathPlayer(i, NpcExport.Type_Player);
			System.out.println(path);
		}
	}

	static void testReplace() throws Exception {
		final String Pattern_Key_Clan = "k:user:lgid_*_pwd_*";
		String tmp = Pattern_Key_Clan.replaceFirst("\\*", "1");
		System.out.println(tmp.indexOf("*"));
		System.out.println(tmp);
		tmp = tmp.replaceFirst("\\*", "2");
		System.out.println(tmp);

		System.out.println("=================");
		String v = _GameKeys.fmt(Pattern_Key_Clan, "2", "38");
		System.out.println(v);
	}

	static void testDownLine() throws Exception {
		Map<String, Long> map = newMap();
		map.put("key_1_1", 100l);
		map.put("key_1_2", 200l);
		map.put("key_1_3", 300l);
		map.put("key_1_4", 400l);
		map.put("key_1_5", 500l);
		map.put("key_1_6", 600l);
		map.put("key_1_7", 700l);
		map.put("key_1_8", 800l);
		map.put("key_1_9", 900l);
		map.put("key_1_10", 1000l);
		map.put("key_1_11", 1100l);
		map.put("key_1_10", 1200l);
		List<String> fields = new ArrayList<String>();
		fields.add("key_1_11");
		fields.add("key_1_12");
		fields.add("key_1_1");
		fields.add("key_1_2");
		fields.add("key_1_3");
		for (String string : fields) {
			map.remove(string);
		}
		long v = map.get("key_1_123");
		System.out.println(v);
	}

	static void testVerifyIAP() throws Exception {
		List<String> list = IAPVerify.getListVerify();
		for (String string : list) {
			System.out.println(string);
		}
		String base64Code = "ewoJInNpZ25hdHVyZSIgPSAiQXBkeEpkdE53UFUyckE1L2NuM2tJTzFPVGsyNWZlREthMGFhZ3l5UnZlV2xjRmxnbHY2UkY2em5raUJTM3VtOVVjN3BWb2IrUHFaUjJUOHd5VnJITnBsb2YzRFgzSXFET2xXcSs5MGE3WWwrcXJSN0E3ald3dml3NzA4UFMrNjdQeUhSbmhPL0c3YlZxZ1JwRXI2RXVGeWJpVTFGWEFpWEpjNmxzMVlBc3NReEFBQURWekNDQTFNd2dnSTdvQU1DQVFJQ0NHVVVrVTNaV0FTMU1BMEdDU3FHU0liM0RRRUJCUVVBTUg4eEN6QUpCZ05WQkFZVEFsVlRNUk13RVFZRFZRUUtEQXBCY0hCc1pTQkpibU11TVNZd0pBWURWUVFMREIxQmNIQnNaU0JEWlhKMGFXWnBZMkYwYVc5dUlFRjFkR2h2Y21sMGVURXpNREVHQTFVRUF3d3FRWEJ3YkdVZ2FWUjFibVZ6SUZOMGIzSmxJRU5sY25ScFptbGpZWFJwYjI0Z1FYVjBhRzl5YVhSNU1CNFhEVEE1TURZeE5USXlNRFUxTmxvWERURTBNRFl4TkRJeU1EVTFObG93WkRFak1DRUdBMVVFQXd3YVVIVnlZMmhoYzJWU1pXTmxhWEIwUTJWeWRHbG1hV05oZEdVeEd6QVpCZ05WQkFzTUVrRndjR3hsSUdsVWRXNWxjeUJUZEc5eVpURVRNQkVHQTFVRUNnd0tRWEJ3YkdVZ1NXNWpMakVMTUFrR0ExVUVCaE1DVlZNd2daOHdEUVlKS29aSWh2Y05BUUVCQlFBRGdZMEFNSUdKQW9HQkFNclJqRjJjdDRJclNkaVRDaGFJMGc4cHd2L2NtSHM4cC9Sd1YvcnQvOTFYS1ZoTmw0WElCaW1LalFRTmZnSHNEczZ5anUrK0RyS0pFN3VLc3BoTWRkS1lmRkU1ckdYc0FkQkVqQndSSXhleFRldngzSExFRkdBdDFtb0t4NTA5ZGh4dGlJZERnSnYyWWFWczQ5QjB1SnZOZHk2U01xTk5MSHNETHpEUzlvWkhBZ01CQUFHamNqQndNQXdHQTFVZEV3RUIvd1FDTUFBd0h3WURWUjBqQkJnd0ZvQVVOaDNvNHAyQzBnRVl0VEpyRHRkREM1RllRem93RGdZRFZSMFBBUUgvQkFRREFnZUFNQjBHQTFVZERnUVdCQlNwZzRQeUdVakZQaEpYQ0JUTXphTittVjhrOVRBUUJnb3Foa2lHOTJOa0JnVUJCQUlGQURBTkJna3Foa2lHOXcwQkFRVUZBQU9DQVFFQUVhU2JQanRtTjRDL0lCM1FFcEszMlJ4YWNDRFhkVlhBZVZSZVM1RmFaeGMrdDg4cFFQOTNCaUF4dmRXLzNlVFNNR1k1RmJlQVlMM2V0cVA1Z204d3JGb2pYMGlreVZSU3RRKy9BUTBLRWp0cUIwN2tMczlRVWU4Y3pSOFVHZmRNMUV1bVYvVWd2RGQ0TndOWXhMUU1nNFdUUWZna1FRVnk4R1had1ZIZ2JFL1VDNlk3MDUzcEdYQms1MU5QTTN3b3hoZDNnU1JMdlhqK2xvSHNTdGNURXFlOXBCRHBtRzUrc2s0dHcrR0szR01lRU41LytlMVFUOW5wL0tsMW5qK2FCdzdDMHhzeTBiRm5hQWQxY1NTNnhkb3J5L0NVdk02Z3RLc21uT09kcVRlc2JwMGJzOHNuNldxczBDOWRnY3hSSHVPTVoydG04bnBMVW03YXJnT1N6UT09IjsKCSJwdXJjaGFzZS1pbmZvIiA9ICJld29KSW05eWFXZHBibUZzTFhCMWNtTm9ZWE5sTFdSaGRHVXRjSE4wSWlBOUlDSXlNREV5TFRBM0xURXlJREExT2pVME9qTTFJRUZ0WlhKcFkyRXZURzl6WDBGdVoyVnNaWE1pT3dvSkluQjFjbU5vWVhObExXUmhkR1V0YlhNaUlEMGdJakV6TkRJd09UYzJOelU0T0RJaU93b0pJbTl5YVdkcGJtRnNMWFJ5WVc1ellXTjBhVzl1TFdsa0lpQTlJQ0l4TnpBd01EQXdNamswTkRrME1qQWlPd29KSW1KMmNuTWlJRDBnSWpFdU5DSTdDZ2tpWVhCd0xXbDBaVzB0YVdRaUlEMGdJalExTURVME1qSXpNeUk3Q2draWRISmhibk5oWTNScGIyNHRhV1FpSUQwZ0lqRTNNREF3TURBeU9UUTBPVFF5TUNJN0Nna2ljWFZoYm5ScGRIa2lJRDBnSWpFaU93b0pJbTl5YVdkcGJtRnNMWEIxY21Ob1lYTmxMV1JoZEdVdGJYTWlJRDBnSWpFek5ESXdPVGMyTnpVNE9ESWlPd29KSW1sMFpXMHRhV1FpSUQwZ0lqVXpOREU0TlRBME1pSTdDZ2tpZG1WeWMybHZiaTFsZUhSbGNtNWhiQzFwWkdWdWRHbG1hV1Z5SWlBOUlDSTVNRFV4TWpNMklqc0tDU0p3Y205a2RXTjBMV2xrSWlBOUlDSmpiMjB1ZW1Wd2RHOXNZV0l1WTNSeVltOXVkWE11YzNWd1pYSndiM2RsY2pFaU93b0pJbkIxY21Ob1lYTmxMV1JoZEdVaUlEMGdJakl3TVRJdE1EY3RNVElnTVRJNk5UUTZNelVnUlhSakwwZE5WQ0k3Q2draWIzSnBaMmx1WVd3dGNIVnlZMmhoYzJVdFpHRjBaU0lnUFNBaU1qQXhNaTB3TnkweE1pQXhNam8xTkRvek5TQkZkR012UjAxVUlqc0tDU0ppYVdRaUlEMGdJbU52YlM1NlpYQjBiMnhoWWk1amRISmxlSEJsY21sdFpXNTBjeUk3Q2draWNIVnlZMmhoYzJVdFpHRjBaUzF3YzNRaUlEMGdJakl3TVRJdE1EY3RNVElnTURVNk5UUTZNelVnUVcxbGNtbGpZUzlNYjNOZlFXNW5aV3hsY3lJN0NuMD0iOwoJInBvZCIgPSAiMTciOwoJInNpZ25pbmctc3RhdHVzIiA9ICIwIjsKfQ==";
		boolean isHas = IAPVerify.isLimitVerify(base64Code);
		System.out.println(isHas);
	}

	static void googlePlay() throws Exception {
		String host = "";
		host = "http://112.124.56.63:10000/payGP";
		host = "http://192.168.1.111:10000/payGP";
		String json = "";
		Map<String, String> parames = new HashMap<String, String>();
		long now_time = System.currentTimeMillis();
		String sign = EnAndDecryption.encoder(now_time + "",
				ConstantType.Type_Sign_Key);
		sign = sign.replaceAll("\\+", "a");
		sign = sign.replaceAll("\\-", "r");
		System.out.println(sign);
		parames.put("sign", sign);
		parames.put("svcid", "2000");
		parames.put("pcid", "45145");
		parames.put("chn", ChannelCfg.GOOGLEPLAY);
		parames.put("iden", "com.coolape.islandwar.g1");
		parames.put("json", "{" + "\"orderId\":\"domegplay." + now_time + "\","
				+ "\"packageName\":\"com.coolape.islandwar.gplay\","
				+ "\"productId\":\"com.coolape.islandwar.g1\","
				+ "\"purchaseTime\":" + now_time + "," + "\"purchaseState\":0,"
				+ "\"developerPayload\":\"ColorSelector\","
				+ "\"purchaseToken\":\"\"" + "}");
		String v = HttpReqWeb.sendPostStr(host, "", parames);
		System.out.println(v);
	}

	static void testYu() throws Exception {
		int b = -5;
		int v = b % 3;
		System.out.println(v);
	}

	static void testList() throws Exception {
		List<Integer> origin = new ArrayList<Integer>();
		origin.add(1);
		origin.add(2);
		origin.add(3);
		origin.add(4);
		origin.add(5);
		origin.add(6);
		origin.add(7);
		origin.add(8);
		origin.add(9);
		origin.add(10);
		int size = 4;
		int len = (int) Math.ceil(10.0 / size);
		for (int i = 0; i < len; i++) {
			int beg = i * size;
			int end = (i + 1) * size;
			if (beg == 10)
				break;
			if (end > 10) {
				end = 10;
			}
			List<Integer> list = origin.subList(beg, end);
			for (Integer integer : list) {
				System.out.println(integer);
			}
		}
	}

	static void testTime() throws Exception {
		Date v = new Date(2014 - 1900, 06, 12, 00, 00, 00);
		int y = DateEx.year(v);
		int m = DateEx.month(v);
		int d = DateEx.day(v);
		int h = DateEx.hour(v);
		int mm = DateEx.minute(v);
		int s = DateEx.second(v);
		System.out
				.println(y + "-" + m + "-" + d + " " + h + ":" + mm + ":" + s);
		try {
			Map map = new HashMap();
			System.out.println(map.get("a"));
			map.remove("a");
			System.out.println("===begin==");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			return;
		} finally {
			System.out.println("===end==");
		}
	}

	static void testAppHostList() throws Exception {
		String h = "";
		h = AppContext.GAME_WEB_HOST(1000);
		System.err.println(h);
		h = AppContext.GAME_WEB_HOST(2000);
		System.err.println(h);
	}

	static void mmReadXML() throws Exception {
		String path = "sql/MM.xml";
		File f = new File(path);
		OutputStream out = new FileOutputStream(f, false);
		XMLResult xml = new XMLResult(out);
	}

	static void mmReadXML2() throws Exception {
		// System.setProperty("entityExpansionLimit", "640000");
		String path = "sql/MM.xml";
		// JAXBContext context = JAXBContext.newInstance(SyncAppOrderReq.class);
		//
		// Marshaller marshaller = context.createMarshaller();
		// Unmarshaller unmarshaller = context.createUnmarshaller();
		//
		// SyncAppOrderReq boy = new SyncAppOrderReq();
		// marshaller.marshal(boy, System.out);
		// System.out.println();
		//
		// SyncAppOrderReq boy2 = (SyncAppOrderReq) unmarshaller.unmarshal(new
		// StringReader(path));
		// System.out.println(boy2.getDest_Address().getDeviceID());
		// SyncAppOrderReq boy = JaxbReadXml.readString(SyncAppOrderReq.class,
		// path);
		// System.out.println(boy.getAppID());
		// System.out.println(boy.getSend_Address().getDeviceID());
		String xml = UtileTools.readStr(path);// "<?xml version='1.0' encoding='utf-8'?><SyncAppOrderReq xmlns='http://www.monternet.com/dsmp/schemas/'><TransactionID>CSSP16122856</TransactionID>		<MsgType>SyncAppOrderReq</MsgType>		<Version>1.0.0</Version>		<Send_Address>			<DeviceType>200</DeviceType>			<DeviceID>CSSP</DeviceID>		</Send_Address>		<Dest_Address>			<DeviceType>400</DeviceType>			<DeviceID>SPSYN</DeviceID>		</Dest_Address>		<OrderID>11130619144434998192</OrderID>		<CheckID>0</CheckID>		<ActionTime>20130619144435</ActionTime>		<ActionID>1</ActionID>		<MSISDN></MSISDN>		<FeeMSISDN>ECAD2EVFADF3AE2A</FeeMSISDN>		<AppID>300001489326</AppID>		<PayCode>30000148932601</PayCode>		<TradeID>L0IF7AF2J4L5IF1B</TradeID>		<Price>100</Price>		<TotalPrice>100</TotalPrice>		<SubsNumb>1</SubsNumb>		<SubsSeq>1</SubsSeq>		<ChannelID>2000000032</ChannelID>		<ExData></ExData>		<OrderType>1</OrderType>		<MD5Sign>ABCDEFGHIKDFIEJFLAKDJFSIDF</MD5Sign>	</SyncAppOrderReq>";
		String out = PayMoblieMM.checkPay(xml);
		System.out.println(out);
	}

	static void payMMCheck() throws Exception {
		PayMoblieMM.mmReadXML();
	}

	static void payWoCheck() throws Exception {
		PayUnicom.woReadXML();
	}

	static void testReadInt() throws Exception {
		String path = "112.3";
		int v = Integer.parseInt(path);
		System.out.println(v);
	}

	static void readPvOne() throws Exception {
		String dateStr = "20150509";
		int pcid = 169545;
		// 1079,1472695661,1842264536,1886703670,48328
		// 51352,51376,51444,51570,51573,51574,51576
		// 6547,824
		boolean isGems = false;
		List<String> list = LogicPV.getListValBy(dateStr, pcid, isGems);
		if (list == null)
			return;

		Map<String, Integer> map = Svc.newSortedMap();
		StringBuffer buff = new StringBuffer();
		for (String str : list) {
			int v = 1;
			String head = str.substring(0, str.indexOf("("));
			if (map.containsKey(head)) {
				v = map.get(head);
				v++;
			}
			map.put(head, v);

			// if (str.indexOf("addHeroEnergy") == -1
			// && str.indexOf("produceArmy") == -1)
			// continue;

			if (str.indexOf("heart") != -1)
				continue;

			// if (str.indexOf("sendChat") == -1)
			// continue;

			buff.append(str).append("\n");
			System.out.println(str);
		}
		ReadWriteString.writeStr("jinjin.txt", buff.toString());
		ReadWriteString.writeStr("jinjin2.txt", UtileTools.mapToStr(map));
	}

	static void readPv() throws Exception {
		List<String> list = LogicPV.getListAll();
		if (list == null)
			return;

		StringBuffer buff = new StringBuffer();
		for (String str : list) {
			if (str.indexOf("eward") != -1) {
				buff.append(str).append("\n");
				System.out.println(str);
			}
		}
		ReadWriteString.writeStr("reward.txt", buff.toString());
	}

	static void resetHero() throws Exception {
		int[] hgids = { LogicHero.Hero_Gid_ElementsNaga,
				LogicHero.Hero_Gid_Fire, LogicHero.Hero_Gid_Jelly,
				LogicHero.Hero_Gid_LandDragon, LogicHero.Hero_Gid_Machinery,
				LogicHero.Hero_Gid_Sprite };
		hgids = new int[] { LogicHero.Hero_Gid_Fire,
				LogicHero.Hero_Gid_Machinery };
		int lvl = 150;
		for (int hgid : hgids) {
			HeroMax max = LogicHero.getMaxByLvl(hgid, lvl);
			int att = max.getMaxDam();
			int hp = max.getMaxHP();
			int spd = max.getMaxSpeed();
			String name = BaseHeroJson.getHeroName(hgid);
			System.out.println(name + ",hp = " + hp + ",att = " + att
					+ "，speed=" + spd);
		}

	}

	static void testSignTime() {
		String[] signTimes = new String[] {
				"57601674530294555555555555-+2+4+6+8+10+12+14+15902689088913+3127925",
				"14011615530294555555555555-+2+4+6+8+10+12+14+15902689088913+3127925",
				"71151615530294555555555555-+2+4+6+8+10+12+14+15902689088913+3127925",
				"94116065530294555555555554-+2+4+6+8+10+12+14+15902689088913+3127925",
				"40456707530294555555555555-+2+4+6+8+10+12+14+15902689088913+3127925",
				"04872538630294555545555455-+2+4+6+8+10+12+14+15902689088913+3127925", };
		for (String str : signTimes) {
			if (Svc.isEmpty(str))
				continue;
			String v = EnAndDecryption.decoder(str, ConstantType.Type_Sign_Key);
			long vl = NumEx.stringToLong(v);
			v = DateEx.format(new Date(vl), DateEx.fmt_yyyy_MM_dd_HH_mm_ss);
			System.out.println(v);
		}
	}

	static void testFormulaWar() {
		boolean isOffense = true;
		int Ra = 10;
		int Rb = 100;
		int offerWin = FormulaWar.getCups(Ra, Rb, 3, true);
		int offerFail = FormulaWar.getCups(Ra, Rb, 0, true);
		int deferWin = FormulaWar.getCups(Ra, Rb, 1, false);
		int deferFail = FormulaWar.getCups(Ra, Rb, 0, false);

		System.out.println(offerWin + " == " + deferFail + " == " + offerFail
				+ " == " + deferWin);
		List<Integer> list = FormulaWar.reckonWarGetCups(Ra, Rb, 3);
		for (Integer val : list) {
			System.out.println("====" + val);
		}
		list = FormulaWar.reckonWarGetCups(Ra, Rb, 0);
		for (Integer val : list) {
			System.out.println("====" + val);
		}
	}

	static void testNpcRenown() {
		int v = NpcImport.getRenownRandom(10, 100);
		System.out.println(v);
		v = NpcImport.getRenownRandom(10, 600);
		System.out.println(v);
		v = NpcImport.getRenownRandom(10, 1200);
		System.out.println(v);
		v = NpcImport.getRenownRandom(10, 1800);
		System.out.println(v);
		v = NpcImport.getRenownRandom(10, 2400);
		System.out.println(v);
		v = NpcImport.getRenownRandom(10, 3100);
		System.out.println(v);
		v = NpcImport.getRenownRandom(10, 3600);
		System.out.println(v);
		v = NpcImport.getRenownRandom(10, 5000);
		System.out.println(v);
		v = NpcImport.getRenownRandom(10, 7000);
		System.out.println(v);
		v = NpcImport.getRenownRandom(10, 9000);
		System.out.println(v);
		v = NpcImport.getRenownRandom(10, 12400);
		System.out.println(v);
	}
}

@SuppressWarnings("rawtypes")
class AtomicIntegerTest {
	public static void testTreadAtomicInteger() throws InterruptedException {

		final CounterInteger counter = new CounterInteger();
		testMyThread2(counter);
	}

	static void testMyThread(CounterInteger counter)
			throws InterruptedException {
		// create 1000 threads
		ArrayList<MyThread> threads = new ArrayList<MyThread>();
		for (int x = 0; x < 1000; x++) {
			threads.add(new MyThread(counter));
		}

		// start all of the threads
		Iterator i1 = threads.iterator();
		while (i1.hasNext()) {
			MyThread mt = (MyThread) i1.next();
			mt.start();
		}

		// wait for all the threads to finish
		Iterator i2 = threads.iterator();
		while (i2.hasNext()) {
			MyThread mt = (MyThread) i2.next();
			mt.join();
		}

		System.out.println("Count: " + counter.get());
	}

	static void testMyThread2(CounterInteger counter)
			throws InterruptedException {
		// create 1000 threads
		ArrayList<MyThread2> threads = new ArrayList<MyThread2>();
		for (int x = 0; x < 1000; x++) {
			threads.add(new MyThread2(counter));
		}

		// start all of the threads
		Iterator i1 = threads.iterator();
		while (i1.hasNext()) {
			MyThread2 mt = (MyThread2) i1.next();
			mt.start();
		}

		// wait for all the threads to finish
		Iterator i2 = threads.iterator();
		while (i2.hasNext()) {
			MyThread2 mt = (MyThread2) i2.next();
			mt.join();
		}

		System.out.println("Count: " + counter.get());
	}
}

// thread that increments the counter 100000 times.
class MyThread extends Thread {
	CounterInteger counter;

	MyThread(CounterInteger counter) {
		this.counter = counter;
	}

	public void run() {
		for (int x = 0; x < 1; x++) {
			System.out.println("cur = " + counter.get());
			int ig = counter.incrementAndGet();
			System.out.println("ig = " + ig + "cur = " + counter.get());
			int gi = counter.getAndIncrement();
			System.out.println("gi = " + gi + "cur = " + counter.get());
			int dg = counter.decrementAndGet();
			System.out.println("dg = " + dg + "cur = " + counter.get());
			int gd = counter.getAndDecrement();
			System.out.println("gd = " + gd + "cur = " + counter.get());
			int ag = counter.addAndGet(2);
			System.out.println("ag = " + ag + "cur = " + counter.get());
			int ga = counter.getAndAdd(2);
			System.out.println("ga = " + ga + "cur = " + counter.get());
		}
		System.out.println("==============one end=============");
	}
}

class MyThread2 extends Thread {
	CounterInteger counter;

	MyThread2(CounterInteger counter) {
		this.counter = counter;
	}

	public void run() {
		for (int x = 0; x < 1; x++) {
			// System.out.println("cur = " + counter.get());
			int ig = counter.incrementAndGet();
			System.out.println("ig = " + ig + "cur = " + counter.get());
		}
		System.out.println("==============one end=============");
	}
}