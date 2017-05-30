package com.sea.tools;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sea.channel.ChannelCfg;

public class NameRandom implements Serializable {
	private static final long serialVersionUID = 1L;
	static Log log = LogFactory.getLog(NameRandom.class);

	private NameRandom() {
	}

	private static NameRandom _self = null;

	public static NameRandom getInstance() {
		if (_self == null) {
			_self = new NameRandom();
			_self.initDate();
		}
		return _self;
	}

	private List<String> sortStrByMap(Map<String, String> map) {
		List<String> r = new ArrayList<String>();
		if (map == null || map.isEmpty())
			return r;
		Set<String> keys = map.keySet();
		r.addAll(keys);
		r = sortStrByList(r);
		return r;
	}

	private List<String> sortStrByList(List<String> r) {
		if (r == null || r.isEmpty())
			return new ArrayList<String>();
		Collections.sort(r, new Comparator<String>() {
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
		return r;
	}

	// 中文名字
	// 简体
	static final String Path_Zh_FirName = "names/zh_fir.txt";
	static final String Path_Zh_EndName = "names/zh_end.txt";

	List<String> zh_firname = new ArrayList<String>();
	List<String> zh_secname = new ArrayList<String>();
	List<String> zh_endname = new ArrayList<String>();

	private void initZhNames() {
		// ============ 第一个名字
		String fisrtName = ReadWriteString.readStr(Path_Zh_FirName);
		String[] fnes = fisrtName.split("\r");
		Map<String, String> map = new HashMap<String, String>();
		for (String str : fnes) {
			if (str == null)
				continue;
			str = str.trim();
			if ("".equals(str) || "\r".equals(str))
				continue;
			// System.out.println("[" + str + "]");
			map.put(str, str);
		}
		List<String> sortListFirst = sortStrByMap(map);
		zh_firname.addAll(sortListFirst);

		// ============ 第二,三个名字
		// System.out.println("============ 第二,三中文个名字================");
		String secthame = ReadWriteString.readStr(Path_Zh_EndName);
		String[] sntes = secthame.split("\r");

		Map<String, String> secmap = new HashMap<String, String>();
		Map<String, String> thrmap = new HashMap<String, String>();
		String strSec = "";
		String strThr = "";
		for (String str : sntes) {
			if (str == null)
				continue;
			str = str.trim();
			if ("".equals(str) || "\r".equals(str))
				continue;
			// System.out.println("[" + str + "]");
			strSec = str.substring(0, 1);
			strThr = str.substring(1);
			secmap.put(strSec, strSec);
			thrmap.put(strThr, strThr);
		}

		List<String> sortListSec = sortStrByMap(secmap);
		zh_secname.addAll(sortListSec);

		List<String> sortListEnd = sortStrByMap(thrmap);
		zh_endname.addAll(sortListEnd);
	}

	// 繁体
	static final String Path_ZhFt_FirName = "names/zhft_fir.txt";
	static final String Path_ZhFt_EndName = "names/zhft_end.txt";
	List<String> zhft_fir = new ArrayList<String>();
	List<String> zhft_sec = new ArrayList<String>();
	List<String> zhft_end = new ArrayList<String>();

	private void initZhFtNames() {
		// ============ 第一个名字
		String fisrtName = ReadWriteString.readStr(Path_ZhFt_FirName);
		String[] fnes = fisrtName.split("\r");
		Map<String, String> map = new HashMap<String, String>();
		for (String str : fnes) {
			if (str == null)
				continue;
			str = str.trim();
			if ("".equals(str) || "\r".equals(str))
				continue;
			// System.out.println("[" + str + "]");
			map.put(str, str);
		}
		List<String> sortListFirst = sortStrByMap(map);
		zhft_fir.addAll(sortListFirst);

		// ============ 第二,三个名字
		// System.out.println("============ 第二,三中文个名字================");
		String secthame = ReadWriteString.readStr(Path_ZhFt_EndName);
		String[] sntes = secthame.split("\r");

		Map<String, String> secmap = new HashMap<String, String>();
		Map<String, String> thrmap = new HashMap<String, String>();
		String strSec = "";
		String strThr = "";
		for (String str : sntes) {
			if (str == null)
				continue;
			str = str.trim();
			if ("".equals(str) || "\r".equals(str))
				continue;
			// System.out.println("[" + str + "]");
			strSec = str.substring(0, 1);
			strThr = str.substring(1);
			secmap.put(strSec, strSec);
			thrmap.put(strThr, strThr);
		}

		List<String> sortListSec = sortStrByMap(secmap);
		zhft_sec.addAll(sortListSec);

		List<String> sortListEnd = sortStrByMap(thrmap);
		zhft_end.addAll(sortListEnd);
	}

	// 英文名字
	static final String Path_En_FirName = "names/en_fir.txt";
	static final String Path_En_EndName = "names/en_end.txt";

	List<String> en_firname = new ArrayList<String>();
	// List<String> en_secname = new ArrayList<String>();
	List<String> en_endname = new ArrayList<String>();

	private void initEnglishNames() {
		// ============ 第一个名字
		String fisrtName = ReadWriteString.readStr(Path_En_FirName);
		String[] fnes = fisrtName.split("\r");
		Map<String, String> map = new HashMap<String, String>();
		for (String str : fnes) {
			if (str == null)
				continue;
			str = str.trim();
			if ("".equals(str) || "\r".equals(str))
				continue;
			// System.out.println("[" + str + "]");
			map.put(str, str);
		}
		List<String> sortListFirst = sortStrByMap(map);
		en_firname.addAll(sortListFirst);

		// System.out.println("============ end 英文名字================");
		String endname = ReadWriteString.readStr(Path_En_EndName);
		String[] enes = endname.split("\r");
		Map<String, String> thrmap = new HashMap<String, String>();
		for (String str : enes) {
			if (str == null)
				continue;
			str = str.trim();
			if ("".equals(str) || "\r".equals(str))
				continue;
			// System.out.println("[" + str + "]");
			thrmap.put(str, str);
		}

		List<String> sortListEnd = sortStrByMap(thrmap);
		en_endname.addAll(sortListEnd);
	}

	void initDate() {
		try {
			System.out.println("======名字加载 begin=======");
			initZhNames();
			initZhFtNames();
			initEnglishNames();
			System.out.println("======名字加载 end=======");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void initNames() {
		getInstance();
	}

	private String newNameByEn() {
		StringBuilder build = new StringBuilder();
		int lenFir = 0;
		// int lenSec = 0;
		int lenEnd = 0;
		int index = 0;
		String strFir = "";
		String strSec = "";
		String strEnd = "";
		strSec = ".";
		lenFir = en_firname.size();
		lenEnd = en_endname.size();
		index = UtileTools.randIntNotContain(lenFir);
		strFir = en_firname.get(index);
		index = UtileTools.randIntNotContain(lenEnd);
		strEnd = en_endname.get(index);
		build.append(strFir).append(strSec).append(strEnd);
		String r = build.toString();
		return r;
	}

	private String newNameByZh() {
		StringBuilder build = new StringBuilder();
		int lenFir = 0;
		int lenSec = 0;
		int lenEnd = 0;
		int index = 0;
		String strFir = "";
		String strSec = "";
		String strEnd = "";

		int randK = UtileTools.randIntK();

		lenFir = zh_firname.size();
		index = UtileTools.randIntNotContain(lenFir);
		strFir = zh_firname.get(index);

		if (randK < 750) {
			lenSec = zh_secname.size();
			index = UtileTools.randIntNotContain(lenSec);
			strSec = zh_secname.get(index);
		}

		if (randK > 400) {
			lenEnd = zh_endname.size();
			index = UtileTools.randIntNotContain(lenEnd);
			strEnd = zh_endname.get(index);
		}

		build.append(strFir).append(strSec).append(strEnd);
		String r = build.toString();
		return r;
	}

	private String newNameByZhFt() {
		StringBuilder build = new StringBuilder();
		int lenFir = 0;
		int lenSec = 0;
		int lenEnd = 0;
		int index = 0;
		String strFir = "";
		String strSec = "";
		String strEnd = "";

		int randK = UtileTools.randIntK();

		lenFir = zhft_fir.size();
		index = UtileTools.randIntNotContain(lenFir);
		strFir = zhft_fir.get(index);

		if (randK < 750) {
			lenSec = zhft_sec.size();
			index = UtileTools.randIntNotContain(lenSec);
			strSec = zhft_sec.get(index);
		}

		if (randK > 400) {
			lenEnd = zhft_end.size();
			index = UtileTools.randIntNotContain(lenEnd);
			strEnd = zhft_end.get(index);
		}

		build.append(strFir).append(strSec).append(strEnd);
		String r = build.toString();
		return r;
	}

	public String newName(final String strChn) {
		String r = "";
		int type = 0;// [1:繁体,2:en,默认:简体]
		switch (strChn) {
		case ChannelCfg.IOSGAT:
			type = 1;
			break;
		case ChannelCfg.APPSTORE:
		case ChannelCfg.GOOGLEPLAY:
			type = 2;
			break;
		default:
			break;
		}
		switch (type) {
		case 1:
			r = newNameByZhFt();
			break;
		case 2:
			r = newNameByEn();
			break;
		default:
			r = newNameByZh();
			break;
		}
		return r;
	}
}