package com.sea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;

import com.sea.cache.jedis.DCfgJedis;
import com.sea.cache.jedis.GameReBackJedis;
import com.sea.cache.jedis.game._GameKeys;
import com.sea.cache.jedis.origin.JedisHash;
import com.sea.cache.jedis.origin.JedisList;
import com.sea.cache.jedis.origin.JedisOrigin;
import com.sea.cache.jedis.origin.JedisPipeline;
import com.sea.cache.jedis.origin.JedisPipelineMap;
import com.sea.cache.jedis.origin.KeyMapEntity;
import com.sea.cache.jedis.origin.KeyValEntity;
import com.sea.content.AppContext;
import com.sea.content.JSONContext;
import com.sea.logic.logicOperate.LogicHero;
import com.sea.tools.UtileTools;

public class DemoJedis {

	public static void main(String[] args) {
		// clearNetWorkJedis();
		// seeJedisTime();
		// clearKey();
		// clearNetKeys();
		// delJedisByKey();
		// clearJedis();
		// clearJedisByKeys();
		// hash();
		// hashSort2();
		// list();
		// listSort();
		// timerVale();
		// DCfgJedis.getDCfgFromNewWork();
		// DCfgJedis.delKeysFromNetWork("k:config_data");
		// hashMap();
		// jedisHashMap();
		// handJsonToMap();
		// handJsonToMapP();
		// testHashJson();

		// testJsonPattern();

		// testMap();

		// rebackDateToNetWork();
		rebackDateToNetWork4Cur();

		// testSelectRedisDb();
		// testPRLen();
		// clearNetWorkJedisPattern();
		// clearNetWorkJedisPatterns();
		// clearPl();
	}

	static void clearNetWorkJedisPattern() {
		String pattern = "k:pv:20140726*";
		DCfgJedis.delKeysFromNetWorkBy(pattern);
	}

	static void clearNetWorkJedisPatterns() {
		String[] patterns = { "k:pv:20140727*", "k:pv:20140728*",
				"k:pv:20140729*", "k:pv:20140730*", "k:pv:20140731*",
				"k:pv:20140801*", "k:pv:20140802*", "k:pv:20140803*",
				"k:pv:res:20140719*", "k:pv:res:20140720*",
				"k:pv:res:20140721*", "k:pv:res:20140722*",
				"k:pv:res:20140723*", "k:pv:res:20140724*",
				"k:pv:res:20140725*", "k:pv:res:20140726*",
				"k:pv:res:20140727*", "k:pv:res:20140728*",
				"k:pv:res:20140729*", "k:pv:res:20140730*",
				"k:pv:res:20140731*", "k:pv:res:20140801*",
				"k:pv:res:20140802*", "k:pv:res:20140803*", };
		DCfgJedis.delKeysFromNetWorkByArray(patterns);
	}

	static void clearPl() {
		String[] keys = { "k:up:ucid:1366:pcid:1374" };
		DCfgJedis.clearPlNpcs(keys);
	}

	static void testPRLen() {
		List<String> keyes = JedisHash
				.getListKeysByPattern(_GameKeys.Pattern_Key_AllPlayer);
		List<String> keyren = JedisHash
				.getListKeysByPattern(_GameKeys.Pattern_Key_PRenown);
		System.out.println(keyes.size());
		System.out.println(keyren.size());
		for (String string : keyes) {
			String pcid = string.substring(string.lastIndexOf(":") + 1);
			pcid = "k:up:renown:pcid:" + pcid;
			if (!keyren.contains(pcid)) {
				// System.out.println(string);
			}
		}
	}

	static void clearNetWorkJedis() {
		DCfgJedis.clearNetWork();
	}

	static void seeJedisTime() {
		JedisOrigin.seeAllKeyTime();
	}

	static void clearJedis() {
		JedisOrigin.clearJedisAll();
	}

	static void clearJedisByKeys() {
		String key = "k:up:rank:player:*";
		JedisPipeline.delAllByPattern(key);
	}

	static void delJedisByKey() {
		JedisPipeline.delKeys(DCfgJedis.KeyConfig);
	}

	static void clearNetKeys() {
		// k:up:rank:player:20140406
		// k:up:pcid:*:hgid:*
		//
		// LogicHero.Hero_Gid_ElementsNaga;
		String[] keys = null;

		// keys = new String[]{
		// "k:up:pcid:1842264536:hgid:" + LogicHero.Hero_Gid_ElementsNaga,
		// "k:up:pcid:1842264536:hgid:" + LogicHero.Hero_Gid_LandDragon,
		// "k:up:pcid:1842264536:hgid:" + LogicHero.Hero_Gid_Machinery,
		// "k:up:pcid:1842264536:hgid:" + LogicHero.Hero_Gid_Sprite };

		keys = new String[] {
				"k:up:pcid:1472695661:hgid:" + LogicHero.Hero_Gid_ElementsNaga,
				"k:up:pcid:1472695661:hgid:" + LogicHero.Hero_Gid_LandDragon,
				"k:up:pcid:1472695661:hgid:" + LogicHero.Hero_Gid_Machinery,
				"k:up:pcid:1472695661:hgid:" + LogicHero.Hero_Gid_Sprite };
		DCfgJedis.delKeysFromNetWork(keys);
	}

	static void clearKey() {
		String key = "hs_d";
		JedisOrigin.deleteKey(key);
	}

	static void rpushx() {
		Jedis jedis = AppContext.getJedis();
		String key = "aaa";
		jedis.rpushx(key, "b");
		jedis.rpushx(key, "bb");
		jedis.rpushx(key, "bbb");
		jedis.rpushx(key, "ccc");
		List<String> val = jedis.lrange(key, 0, -1);
		for (String string : val) {
			System.out.println(string);
		}
		AppContext.returnJedis(jedis);
	}

	static void hash() {
		String key = "hs_d";
		JedisHash.setValHash(key, "a_1", "1");
		JedisHash.setValHash(key, "a_2", "2");
		JedisHash.setValHash(key, "a3", "3");
		JedisHash.setValHash(key, "a4", "4");
		JedisHash.setValHash(key, "a_5", "5");

		JedisHash.setValHash(key, "b_6", "11");
		JedisHash.setValHash(key, "b7", "22");
		JedisHash.setValHash(key, "b8", "33");
		JedisHash.setValHash(key, "b_9", "44");
		JedisHash.setValHash(key, "b_10", "55");

		List<String> list = JedisHash.getListHashKeys(key);
		for (String string : list) {
			System.out.println("keylist = " + string);
		}
		System.out.println("======= value ========");
		JedisHash.setValHash(key, "b_10", "100");
		List<String> values = JedisHash.getListHashVals(key);
		for (String string : values) {
			System.out.println("vallist = " + string);
		}
	}

	static void hashSort() {
		String keyList = "userlist";
		JedisList.setJedisInList("userlist", "33");
		JedisList.setJedisInList("userlist", "22");
		JedisList.setJedisInList("userlist", "55");
		JedisList.setJedisInList("userlist", "11");

		JedisHash.setValHash("user:11", "name", "24");
		JedisHash.setValHash("user:11", "add", "beijing");

		JedisHash.setValHash("user:22", "name", "79");
		JedisHash.setValHash("user:22", "add", "shanghai");

		JedisHash.setValHash("user:33", "name", "33");
		JedisHash.setValHash("user:33", "add", "guangzhou");

		JedisHash.setValHash("user:55", "add", "chongqing");
		JedisHash.setValHash("user:55", "name", "55");

		JedisHash.setValHash("user:66", "add", "xi'an");
		JedisHash.setValHash("user:66", "name", "66");

		SortingParams sortingParameters = new SortingParams();
		// 符号 "->" 用于分割哈希表的键名(key name)和索引域(hash field)，格式为 "key->field" 。
		sortingParameters.get("user:*->name");
		// sortingParameters.get("user:*->add");
		// sortingParameters.by("user:*->name");
		// sortingParameters.get("#");
		List<String> result = JedisList.getListBySort(keyList,
				sortingParameters);
		for (String item : result) {
			System.out.println("hash  val = " + item);
		}
	}

	static void hashSort2() {
		Jedis jedis = AppContext.getJedis();
		jedis.sadd("tom:friend:list", "123"); // tom的好友列表
		jedis.sadd("tom:friend:list", "456");
		jedis.sadd("tom:friend:list", "789");
		jedis.sadd("tom:friend:list", "101");

		jedis.set("score:uid:123", "1000"); // 好友对应的成绩
		jedis.set("score:uid:456", "6000");
		jedis.set("score:uid:789", "100");
		jedis.set("score:uid:101", "5999");

		jedis.set("uid:123", "{'uid':123,'name':'lucy'}"); // 好友的详细信息
		jedis.set("uid:456", "{'uid':456,'name':'jack'}");
		jedis.set("uid:789", "{'uid':789,'name':'jay'}");
		jedis.set("uid:101", "{'uid':101,'name':'jolin'}");

		System.out.println("====len==" + jedis.keys("score:uid:*").size());
		SortingParams sortingParameters = new SortingParams();

		sortingParameters.desc();
		// sortingParameters.limit(0, 2);
		// 注意GET操作是有序的，GET user_name_* GET user_password_*
		// 和 GET user_password_* GET user_name_*返回的结果位置不同
		// GET 还有一个特殊的规则—— "GET #"
		// ，用于获取被排序对象(我们这里的例子是 user_id )的当前元素。
		sortingParameters.get("#");
		sortingParameters.get("uid:*");
		sortingParameters.get("score:uid:*");
		sortingParameters.by("score:uid:*");
		// 对应的redis 命令是./redis-cli sort tom:friend:list by score:uid:* get # get
		// uid:* get score:uid:*
		List<String> result = jedis.sort("tom:friend:list", sortingParameters);
		for (String item : result) {
			System.out.println("item..." + item);
		}
		AppContext.returnJedis(jedis);
	}

	static void hashSort3() {
		String keyList = "pcid";
		JedisList.setJedisInList(keyList, "1");
		JedisList.setJedisInList(keyList, "2");
		// JedisList.setJedisInList(keyList, "3");
		// JedisList.setJedisInList(keyList, "4");

		JedisHash.setValHash("KH:USER", "ship", "{num:\"1\"}");
		JedisHash.setValHash("KH:USER:PLAYER", "ship", "{num:\"1\"}");

		JedisHash.setValHash("pcid:1:bcid:1:armygid8", "ship", "{num:\"1\"}");
		JedisHash.setValHash("pcid:1:bcid:1:armygid8", "prod", "{num:\"2\"}");
		JedisHash.setValHash("pcid:1:bcid:1:armygid8", "ship", "{num:\"2\"}");

		JedisHash.setValHash("pcid:1:bcid:1:armygid9", "ship", "{num:\"1\"}");
		JedisHash.setValHash("pcid:1:bcid:1:armygid9", "prod", "{num:\"2\"}");

		JedisHash.setValHash("pcid:1:bcid:1:armygid1", "ship", "{num:\"1\"}");
		JedisHash.setValHash("pcid:1:bcid:1:armygid1", "prod", "{num:\"2\"}");

		JedisHash.setValHash("pcid:1:bcid:1:armygid2", "ship", "{num:\"1\"}");
		JedisHash.setValHash("pcid:1:bcid:1:armygid2", "prod", "{num:\"2\"}");

		SortingParams sortingParameters = new SortingParams();
		// 符号 "->" 用于分割哈希表的键名(key name)和索引域(hash field)，格式为 "key->field" 。
		sortingParameters.get("pcid:1:bcid:1:armygid*->ship");
		// sortingParameters.get("pcid:*->prod");
		// sortingParameters.by("pcid:*->ship");
		// sortingParameters.get("#");
		List<String> result = JedisList.getListBySort(keyList,
				sortingParameters);
		for (String item : result) {
			System.out.println("hash  val = " + item);
		}
	}

	static void hashSort4() {
		String keyList = "kl:temp";
		JedisList.setJedisInList(keyList, "1");
		// JedisList.setJedisInList(keyList, "2");
		// JedisList.setJedisInList(keyList, "3");
		// JedisList.setJedisInList(keyList, "4");

		String keyUser = _GameKeys.Key_ArmyGid;
		JedisHash.setValHash(keyUser, "uid1", "{uid:1}");

		String keyPlayer = _GameKeys.fmt(_GameKeys.Pattern_Key_PUcid, 1, 1);

		JedisHash.setValHash(keyPlayer, "val", "{pcid:1}");

		String kb = "k:up:pcid:" + 1 + ":bcid:";

		String kb1 = _GameKeys.fmt(_GameKeys.Pattern_Key_PBuild, 1, 1);
		JedisHash.setValHash(kb1, "val", "{bcid:1}");

		String kb2 = _GameKeys.fmt(_GameKeys.Pattern_Key_PBuild, 1, 2);
		JedisHash.setValHash(kb2, "val", "{bcid:2}");

		// String kb1army = GameKeys.getHashKeyHeadArmy(1, 1);
		String kb1army1 = _GameKeys.fmt(_GameKeys.Pattern_Key_PTech, 1, 1, 1);
		JedisHash.setValHash(kb1army1, "own", "{num:1}");
		String kb1army2 = _GameKeys.fmt(_GameKeys.Pattern_Key_PTech, 1, 1, 2);
		JedisHash.setValHash(kb1army2, "own", "{num:2}");

		String kb1army3 = _GameKeys.fmt(_GameKeys.Pattern_Key_PTech, 1, 1, 1);
		JedisHash.setValHash(kb1army3, "make", "{num:3}");
		String kb1army4 = _GameKeys.fmt(_GameKeys.Pattern_Key_PTech, 1, 1, 2);
		JedisHash.setValHash(kb1army4, "make", "{num:4}");

		// /====== player 2
		String kb21 = _GameKeys.fmt(_GameKeys.Pattern_Key_PBuild, 2, 2);
		JedisHash.setValHash(kb21, "val", "{bcid:3}");

		String kb22 = _GameKeys.fmt(_GameKeys.Pattern_Key_PBuild, 2, 2);
		JedisHash.setValHash(kb22, "val", "{bcid:3}");

		String ko21 = _GameKeys.fmt(_GameKeys.Pattern_Key_PBuild, 2, 1);
		JedisHash.setValHash(ko21, "val", "{ocid:3}");

		String ko22 = _GameKeys.fmt(_GameKeys.Pattern_Key_PBuild, 2, 2);
		JedisHash.setValHash(ko22, "val", "{ocid:3}");

		SortingParams sortingParameters = new SortingParams();
		// 符号 "->" 用于分割哈希表的键名(key name)和索引域(hash field)，格式为 "key->field" 。
		sortingParameters.get(kb + "*->val");
		// sortingParameters.get(kb1army+"*->make");
		// sortingParameters.by(kb+"*->val");
		// sortingParameters.by("pcid:*->ship");
		// sortingParameters.get("#");
		List<String> result = JedisList.getListBySort(keyList,
				sortingParameters);
		for (String item : result) {
			System.out.println("hash  val = " + item);
		}
	}

	static void list() {
		String key = "odj";
		JedisList.deleteKey(key);
		JedisList.setJedisInList(key, "2");
		JedisList.setJedisInList(key, "21");
		JedisList.setJedisInList(key, "2");
		JedisList.setJedisInList(key, "22");
		JedisList.setJedisInList(key, "2");
		JedisList.setJedisInList(key, "23");
		JedisList.setJedisInList(key, "2");
		JedisList.setJedisInList(key, "24");
		JedisList.setJedisInList(key, "2");
		JedisList.setJedisInList(key, "25");
		JedisList.setJedisInList(key, "2");
		JedisList.setJedisInList(key, "26");
		JedisList.setJedisInList(key, "2");
		JedisList.setJedisInList(key, "27");
		JedisList.setJedisInList(key, "2");
		JedisList.setJedisInList(key, "28");
		JedisList.setJedisInList(key, "2");
		JedisList.setJedisInList(key, "29");
		JedisList.setJedisInList(key, "2");
		JedisList.setJedisInList(key, "30");

		long index = JedisList.getListFirstIndex(key, "22");
		System.out.println("===index==" + index);
		System.out.println(JedisList.getOneValInList(key, index));

		System.out.println("========取得列表 begin ,end=======");
		List<String> list = null;
		list = JedisList.getListBy(key, 0, 3);
		for (String string : list) {
			System.out.println(string);
		}

		System.out.println("========删除一个 数据2后=======");
		JedisList.deleteListOneVal(key, "2");
		list = JedisList.getListAllBy(key);
		for (String string : list) {
			System.out.println(string);
		}
		System.out.println("========删除所有对象为2后=======");
		JedisList.deleteListAllVal(key, "2");
		list = JedisList.getListAllBy(key);
		for (String string : list) {
			System.out.println(string);
		}

		System.out.println("========保留 start(包含),end(包含)之间的值=======");
		JedisList.retainList(key, 1, 5);
		list = JedisList.getListAllBy(key);
		for (String string : list) {
			System.out.println(string);
		}
	}

	static void listSort() {
		String key = "KH_AttFight";
		JedisList.deleteKey(key);

		JedisList.setJedisInList(key, "2");
		JedisList.setJedisInList(key, "21");
		JedisList.setJedisInList(key, "2");
		JedisList.setJedisInList(key, "22");
		JedisList.setJedisInList(key, "2");
		JedisList.setJedisInList(key, "23");
		JedisList.setJedisInList(key, "2");
		JedisList.setJedisInList(key, "24");
		JedisList.setJedisInList(key, "2");
		JedisList.setJedisInList(key, "25");
		List<String> vas = null;

		vas = JedisList.getListBySortAsc(key);
		System.out.println("=====len = " + vas.size());
		for (String v : vas) {
			System.out.println("====" + v);
		}
		System.out
				.println("=============  desc  ================" + vas.size());
		vas = JedisList.getListBySortDesc(key);
		for (String v : vas) {
			System.out.println("====" + v);
		}
	}

	static void timerVale() {
		String key = "KH_AttFight";
		// JedisOrigin.deleteKey(key);
		// JedisOrigin.setValBykey(key, "1");
		// JedisOrigin.setKeyValidTime(key, 10);
		long t = JedisOrigin.getKeyValidTime(key);
		System.out.println("time ==== " + t);
	}

	static void hashMap() {
		Jedis jedis = AppContext.getJedis();
		// 哈希表 Hash 主要用于批量修改和获取
		Map<String, String> pairs = new HashMap<String, String>();
		pairs.put("name", "克瑞斯");
		pairs.put("age", "20");
		pairs.put("sex", "男");
		jedis.hmset("young", pairs); // 设置hash的值
		List<String> values = jedis.hmget("young", new String[] { "name",
				"age", "sex" });
		System.out.println(values);
		Set<String> setValues = jedis.hkeys("young"); // 获取young变量里面的标签
														// name、age和sex
		System.out.println(setValues);
		values = jedis.hvals("young"); // 获取young变量里面的标签 name、age和sex所对应的值
		System.out.println(values);
		pairs = jedis.hgetAll("young"); //
		System.out.println(pairs);
		AppContext.returnJedis(jedis);
	}

	static void jedisHashMap2() {
		Jedis jedis = AppContext.getJedis();
		// 哈希表 Hash 主要用于批量修改和获取
		Map<String, String> pairs = new HashMap<String, String>();
		pairs.put("name", "克瑞斯");
		pairs.put("age", "10");
		pairs.put("sex", "男");
		jedis.hmset("young", pairs); // 设置hash的值

		pairs.clear();

		pairs.put("name", "克瑞斯");
		pairs.put("age", "20");
		pairs.put("sex", "男");
		jedis.hmset("young2", pairs); // 设置hash的值

		pairs.put("name", "克瑞斯");
		pairs.put("age", "30");
		pairs.put("sex", "男");
		jedis.hmset("young3", pairs); // 设置hash的值

		List<Map<String, String>> list = JedisPipelineMap.getListMap("young",
				"young1", "young2", "young3", "young");
		for (Map<String, String> map : list) {
			System.out.println(map);
		}
		AppContext.returnJedis(jedis);

	}

	static void jedisHashMap() {
		List<KeyMapEntity> origin = new ArrayList<KeyMapEntity>();

		// 哈希表 Hash 主要用于批量修改和获取
		Map<String, String> pairs = new HashMap<String, String>();
		pairs.put("name", "克瑞斯1");
		pairs.put("age", "10");
		pairs.put("sex", "男1");
		KeyMapEntity ee1 = new KeyMapEntity("young", pairs);

		Map<String, String> pairs2 = new HashMap<String, String>();
		pairs2.put("name", "克瑞斯2");
		pairs2.put("age", "20");
		pairs2.put("sex", "男2");
		KeyMapEntity ee2 = new KeyMapEntity("young2", pairs2);

		Map<String, String> pairs3 = new HashMap<String, String>();
		pairs3.put("name", "克瑞斯3");
		pairs3.put("age", "30");
		pairs3.put("sex", "男3");
		KeyMapEntity ee3 = new KeyMapEntity("young3", pairs3);
		origin.add(ee1);
		origin.add(ee2);
		origin.add(ee3);
		JedisPipelineMap.setKeyMapList(origin);

		List<Map<String, String>> list = JedisPipelineMap.getListMap("young",
				"young1", "young2", "young3", "young");
		for (Map<String, String> map : list) {
			System.out.println(map);
		}
	}

	static public void handJsonToMap() {

	}

	static void handJsonToMapP() {
		long bg = System.currentTimeMillis();

		long ed = System.currentTimeMillis();
		long df = ed - bg;
		System.out.println("======end==== time :" + df + "MS");
	}

	static void testHashJson() {
		for (int i = 0; i < 10; i++) {
			testSetHashJson(i);
		}
	}

	static void testSetHashJson(int index) {
		long bg = System.currentTimeMillis();

		String key_Hash = "Test:Hash";
		String key_Json = "Test:Josn";
		List<KeyValEntity> list = new ArrayList<>();
		List<KeyMapEntity> listMap = new ArrayList<>();
		int beg = 100000 * index;
		int end = 100000 * (index + 1);
		for (int i = beg; i < end; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("" + i, "v_" + i);
			KeyMapEntity kmap = new KeyMapEntity(key_Hash, map);
			KeyValEntity kJson = new KeyValEntity(key_Json + ":" + i,
					UtileTools.mapToStr(map));
			list.add(kJson);
			listMap.add(kmap);
		}
		JedisPipeline.setKeyValList(list);
		JedisPipelineMap.setKeyMapList(listMap);

		long ed = System.currentTimeMillis();
		long df = ed - bg;
		System.out.println("======end==== time :" + df + "MS");
	}

	static void testMap() {
		JSONContext.getJedisPool();
		long bg = System.currentTimeMillis();

		String key_Hash = "Test:Hash";

		// JedisHash.getListHashKeys(key_Hash);

		JedisHash.getValHash(key_Hash, "28660");

		long ed = System.currentTimeMillis();
		long df = ed - bg;
		System.out.println("======end map==== time :" + df + "MS");
	}

	static void testJsonPattern() {
		JSONContext.getJedisPool();
		long bg = System.currentTimeMillis();

		JedisPipeline.getListKeysByPattern("Test:Josn*");

		JedisPipeline.getValByKey("Test:Josn:28660");

		JedisPipeline.getListKeysByPattern("k:up:pcid:1347:bcid:*");
		// JedisPipeline.getListKeysByPattern("k:up:pcid:1347:ocid:*");
		// JedisPipeline.getListKeysByPattern("k:up:pcid:1347:techgid:*");
		// JedisPipeline.getListKeysByPattern("k:up:pcid:1347:hgid:*");
		long ed = System.currentTimeMillis();
		long df = ed - bg;
		System.out.println("======end pattern==== time :" + df + "MS");
	}

	static void rebackDateToNetWork() {
		int pcid = 30515;
		GameReBackJedis.rebackByPcid(pcid);
	}
	
	static void rebackDateToNetWork4Cur() {
		int pcid = 316860;//[ "苗鹰=316860"]
		// 新帐号 "ucid":416309,[ "叶片=416452"]
		GameReBackJedis.rebackByPcid4Cur(pcid,416309,416452);
	}

	static void testSelectRedisDb() {
		Jedis redis = AppContext.getJedis();
		int dbIndex = 1;
		String v = redis.select(dbIndex);
		Long size = redis.dbSize();
		System.out.println(v + "," + size);
		redis.set("hello", "to you");
		AppContext.returnJedis(redis);
	}
}
