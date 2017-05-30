package com.sea.tools;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.json.JSON;
import com.bowlong.lang.NumEx;
import com.bowlong.objpool.StringBufPool;
import com.bowlong.util.DateEx;

@SuppressWarnings({ "rawtypes" })
public class UtileTools implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(UtileTools.class);

	// 导出异常
	public static String ex2s(Exception e) {
		StringBuffer sb = StringBufPool.borrowObject();
		try {
			sb.append(e);
			sb.append("\r\n");
			for (StackTraceElement ste : e.getStackTrace()) {
				sb.append("at ");
				sb.append(ste);
				sb.append("\r\n");
			}
			return sb.toString();
		} finally {
			StringBufPool.returnObject(sb);
		}
	}

	// str转换 map
	public static Map strToMap(String strVal) {
		Map map_ = JSON.toMap(strVal);
		return map_;
	}

	// map转换str
	public static String mapToStr(Map map) {
		String r = "";
		try {
			r = JSON.formatString(map);
		} catch (IOException e) {
			log.info(ex2s(e));
		}
		return r;
	}

	// str转换list
	public static List strToList(String strVal) {
		List list_ = JSON.toList(strVal);
		return list_;
	}

	// list转换str
	public static String listToStr(List list) {
		String r = "";
		try {
			r = JSON.formatString(list);
		} catch (IOException e) {
			log.info(ex2s(e));
		}
		return r;
	}

	// 读取路径path 并将读取内容转为map
	public static Map readStrMap(String path) {
		String strData = ReadWriteString.readStr(path);
		Map map_ = strToMap(strData);
		return map_;
	}

	// 写文件map 对象（是重置，不是追加）
	public static void writeMapStr(String path, Map map) {
		String content = mapToStr(map);
		ReadWriteString.writeStr(path, content);
	}

	// 读取路径path 并将读取内容转为list
	public static List readStrList(String path) {
		String strData = ReadWriteString.readStr(path);
		List list_ = strToList(strData);
		return list_;
	}

	public static String readStr(String path) {
		String r = ReadWriteString.readStr(path);
		return r;
	}

	public static byte[] readBytes(String path) {
		byte[] r = ReadWriteString.readBytes(path);
		return r;
	}
	
	public static String readStr2(String path) {
		String r = ReadWriteString.readStr2(path);
		return r;
	}

	public static byte[] readBytes2(String path) {
		byte[] r = ReadWriteString.readBytes2(path);
		return r;
	}

	// 写文件map 对象（是重置，不是追加）
	public static void writeListStr(String path, List list) {
		String content = listToStr(list);
		ReadWriteString.writeStr(path, content);
	}

	// 随机数 不包含max
	public static int randIntNotContain(int maxCont) {
		return randInt(0, maxCont);
	}

	// 随机数 包含max
	public static int randIntContain(int maxCont) {
		return randIntContainMax(0, maxCont);
	}

	// 随机数 包含min max
	public static int randIntContainMax(int min, int maxCont) {
		maxCont++;
		return randInt(min, maxCont);
	}

	// 随机数 包含min,不保护max
	public static int randInt(int min, int max) {
		if (max <= min) {
			return min;
		}
		int r = NumEx.nextInt(min, max);
		return r;
	}

	// 随机数 1000
	public static int randIntK() {
		return randIntContainMax(1, 1000);
	}

	// 随机数 10000
	public static int randIntW() {
		return randIntContainMax(1, 10000);
	}

	// 返回时间字符串 20130201
	public static String getStrYYYYMMDDBy(int yyyy, int mm, int dd) {
		StringBuffer sb = StringBufPool.borrowObject();
		sb.append(yyyy);
		if (mm < 10)
			sb.append("0");
		sb.append(mm);
		if (dd < 10)
			sb.append("0");
		sb.append(dd);
		String r = sb.toString();
		StringBufPool.returnObject(sb);
		return r;
	}

	public static String getStrYYYYMMDDBy(Date date) {
		int yyyy = DateEx.year(date);
		int mm = DateEx.month(date);
		int day = DateEx.day(date);
		return getStrYYYYMMDDBy(yyyy, mm, day);
	}

	public static String getStrYYYYMMDDBy(long timeLong) {
		Date date = new Date(timeLong);
		int yyyy = DateEx.year(date);
		int mm = DateEx.month(date);
		int day = DateEx.day(date);
		return getStrYYYYMMDDBy(yyyy, mm, day);
	}

	public static List<String> getDelWeekTimerList(Date date) {
		List<String> r = getDelTimerList(date, 7);
		return r;
	}

	public static List<String> getDelTimerList(Date date, int dayDiff) {
		List<String> r = getDiffTimerListByDay(date, dayDiff);
		return r;
	}

	// 格式是20120203
	public static List<String> getDiffTimerListByDay(Date date, int diffDayNum) {
		if (date == null)
			date = new Date();

		List<String> r = new ArrayList<String>();
		int yyyy = DateEx.year(date);
		int mm = DateEx.month(date);
		int day = DateEx.day(date);

		int diffDay = day - diffDayNum;
		if (diffDay <= 0) {
			int premm = mm - 1;
			if (premm < 1) {
				yyyy -= 1;
				premm = 12;
			}
			int maxPreDay = DateEx.dayNum(yyyy, premm - 1);
			int remainDay = maxPreDay + diffDay;
			for (int i = 1; i <= remainDay; i++) {
				String val = getStrYYYYMMDDBy(yyyy, premm, i);
				r.add(val);
			}
		} else {
			for (int i = 1; i <= diffDay; i++) {
				String val = getStrYYYYMMDDBy(yyyy, mm, i);
				r.add(val);
			}
		}
		return r;
	}

	public static String getNStrByLen(int len, int cur) {
		String r = cur + "";
		int clen = r.length();
		if (len > clen) {
			int diflen = len - clen;
			StringBuffer build = new StringBuffer();
			for (int i = 0; i < diflen; i++) {
				build.append("0");
			}
			build.append(cur);
			r = build.toString();
		}
		return r;
	}

	public static List getListPage(List o, int page) {
		if (o == null || o.isEmpty())
			return o;
		page = page <= 1 ? 1 : page;
		int size = 10;
		int fromIndex = (page - 1) * size;
		fromIndex = fromIndex < 0 ? 0 : fromIndex;
		int toIndex = fromIndex + size;
		int len = o.size();
		if (fromIndex >= len) {
			return null;
		}

		if (toIndex >= len) {
			toIndex = len;
		}
		o = o.subList(fromIndex, toIndex);
		return o;
	}

	// 清空 数据
	public static void clearList(List origin) {
		if (origin == null)
			return;
		origin.clear();
		origin = null;
	}

	public static void clearMap(Map origin) {
		if (origin == null)
			return;
		origin.clear();
		origin = null;
	}

	// 是否是周末
	public static boolean isWeek(long nowtime) {
		if (nowtime == 0)
			return false;
		Date dt = new Date(nowtime);
		return isWeek(dt);
	}

	public static boolean isWeek(Date dt) {
		if (dt == null)
			return false;
		int week = DateEx.week(dt);
		boolean r = week == 0 || week == 6;
		return r;
	}

	public static boolean isWeekNow() {
		Date dt = new Date();
		return isWeek(dt);
	}
}
