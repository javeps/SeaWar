package com.sea.content;

import java.awt.Point;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.lang.MyThreadFactory;
import com.bowlong.lang.NumEx;
import com.bowlong.lang.RndEx;
import com.bowlong.objpool.ObjPool;
import com.bowlong.objpool.StringBufPool;
import com.bowlong.pinyin.PinYin;
import com.bowlong.util.DateEx;
import com.bowlong.util.NewDate;
import com.bowlong.util.StrBuilder;

@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class Svc {
	static Log log = getLog(Svc.class);

	// static ScheduledExecutorService SES = newScheduledThreadPool("TipToAll",
	// 2);

	public static final Log getLog(Class c) {
		return LogFactory.getLog(c);
	}

	// ///////////////////////////////////////////////////
	public static final Set newSet() {
		return Collections.synchronizedSet(new HashSet());
	}

	public static final Map newMap() {
		return Collections.synchronizedMap(new HashMap());
	}

	public static final List newList() {
		return Collections.synchronizedList(new ArrayList());
	}

	public static final <K, V> Map<K, V> newSortedMap() {
		return Collections.synchronizedMap(new TreeMap<K, V>());
	}

	public static final List copyList(List l) {
		if (l == null)
			return null;
		List l2 = new Vector(l.size());
		l2.addAll(l);
		return l2;
	}

	public static final List toList(Set m) {
		List ret = newList();
		ret.addAll(m);
		return ret;
	}

	public static final List toList(Map m) {
		List ret = newList();
		ret.addAll(m.values());
		return ret;
	}

	public static final List<Integer> toList(String s) {
		List<Integer> ret = newList();
		StringTokenizer st = new StringTokenizer(s, ",");
		while (st.hasMoreTokens()) {
			String str = st.nextToken();
			int e = NumEx.stringToInt(str.trim());
			if (e <= 0)
				continue;
			ret.add(e);
		}
		return ret;
	}

	public static final List<Integer> toList(int[] vars) {
		List ret = newList();
		for (int v : vars) {
			ret.add(v);
		}
		return ret;
	}

	public static final List<String> toList(String[] vars) {
		List ret = newList();
		for (String v : vars) {
			ret.add(v);
		}
		return ret;
	}

	public static final List listIt(Object... var) {
		List result = new Vector();
		for (Object e : var) {
			result.add(e);
		}
		return result;
	}

	public static final Map toMap(List l) {
		Map ret = newMap();
		int i = 1;
		for (Object o : l) {
			ret.put(i, o);
			i++;
		}
		return ret;
	}

	public static final Map toMap(String s) {
		Properties p = new Properties();
		try {
			StringReader sr = new StringReader(s);
			p.load(sr);
			return p;
		} catch (IOException e) {
			log.error(Svc.e2s(e));
		}
		return p;
	}

	public static int[] arr(int... o) {
		return o;
	}

	public static boolean in(int[] zbx, int id) {
		for (int i : zbx) {
			if (id == i)
				return true;
		}
		return false;
	}

	public static boolean in(List vars, int i) {
		if (vars.contains(i))
			return true;
		return false;
	}

	public static boolean in(Set vars, int[] arr) {
		for (int i : arr) {
			if (vars.contains(i))
				return true;
		}
		return false;
	}

	public static boolean in(int[] arr, Set<Integer> vars) {
		Iterator<Integer> it = vars.iterator();
		while (it.hasNext()) {
			int i = it.next();
			if (in(arr, i))
				return true;
		}
		return false;
	}

	// 是否存在交集
	public static final boolean isIntersected(List l1, List l2) {
		return Collections.disjoint(l1, l2);
	}

	public static final boolean intersectedPoints(List<Point> list, Point p) {
		if (list == null || list.isEmpty() || p == null)
			return false;

		Set<String> c = new HashSet<String>();
		for (Point p1 : list) {
			c.add(p1.toString());
		}
		return c.contains(p.toString());
	}

	// 求交集:小心参数1会被改变
	public static final List intersected(List l1, List l2) {
		l1.retainAll(l2);
		return l1;
	}

	// ///////////////////////////////////////////////////
	public static final ByteArrayOutputStream newStream() {
		return new ByteArrayOutputStream();
	}

	public static final InputStream newStream(byte[] b) {
		return new ByteArrayInputStream(b);
	}

	// ///////////////////////////////////////////////////

	public static final boolean nextBool() {
		return RndEx.nextBoolean();
	}

	public static final boolean nextBool(int max, int f) {
		int v = nextInt(max);
		return (v < f);
	}

	public static final <T> T rand(List objs) {
		if (objs == null || objs.isEmpty())
			return null;
		else if (objs.size() == 1)
			return (T) objs.get(0);

		int i = RndEx.nextInt(0, objs.size());
		return (T) objs.get(i);
	}

	public static final int max(List<Integer> list) {
		return Collections.max(list);
	}

	public static final int min(List<Integer> list) {
		return Collections.min(list);
	}

	public static final int nextInt(int max) {
		if (max <= 0)
			return 0;
		return RndEx.nextInt(max);
	}

	public static final int nextInt(int f, int t) {
		if (t <= f)
			return f;
		return RndEx.nextInt(t - f) + f;
	}

	// public static final String nextPassword() {
	// int v = 100000 + nextInt(800000);
	// return String.valueOf(v);
	// }

	// 对List进行打乱顺序
	public static final List random(List list) {
		Collections.shuffle(list);
		return list;
		// List ret = new Vector();
		// int num = list.size();
		// for (int n = num; n > 0; n--) {
		// int p = RndEx.nextInt(n);
		// Object e = list.remove(p);
		// ret.add(e);
		// }
		// return ret;
	}

	public static String pn(int n) {
		return n > 0 ? "+" + n : String.valueOf(n);
	}

	public static final List<Map> sort(List m1, final String key) {
		Collections.sort(m1, new Comparator<Map>() {
			public int compare(Map o1, Map o2) {
				Object v1 = o1.get(key);
				Object v2 = o2.get(key);
				if (v1 == null || v2 == null)
					return 0;
				return compareTo(v1, v2);
			}
		});
		return m1;
	}

	public static final int compareTo(Object v1, Object v2) {

		if (v1 == null || v2 == null)
			return 0;

		if (v1 instanceof Byte && v2 instanceof Byte) {
			Boolean i1 = (Boolean) v1;
			Boolean i2 = (Boolean) v2;
			return i1.compareTo(i2);
		} else if (v1 instanceof Byte && v2 instanceof Byte) {
			Byte i1 = (Byte) v1;
			Byte i2 = (Byte) v2;
			return i1.compareTo(i2);
		} else if (v1 instanceof Short && v2 instanceof Short) {
			Short i1 = (Short) v1;
			Short i2 = (Short) v2;
			return i1.compareTo(i2);
		} else if (v1 instanceof Integer && v2 instanceof Integer) {
			Integer i1 = (Integer) v1;
			Integer i2 = (Integer) v2;
			return i1.compareTo(i2);
		} else if (v1 instanceof Long && v2 instanceof Long) {
			Long i1 = (Long) v1;
			Long i2 = (Long) v2;
			return i1.compareTo(i2);
		} else if (v1 instanceof java.math.BigInteger
				&& v2 instanceof java.math.BigInteger) {
			java.math.BigInteger i1 = (java.math.BigInteger) v1;
			java.math.BigInteger i2 = (java.math.BigInteger) v2;
			return i1.compareTo(i2);
		} else if (v1 instanceof java.math.BigDecimal
				&& v2 instanceof java.math.BigDecimal) {
			java.math.BigDecimal i1 = (java.math.BigDecimal) v1;
			java.math.BigDecimal i2 = (java.math.BigDecimal) v2;
			return i1.compareTo(i2);
		} else if (v1 instanceof Float && v2 instanceof Float) {
			Float i1 = (Float) v1;
			Float i2 = (Float) v2;
			return i1.compareTo(i2);
		} else if (v1 instanceof Double && v2 instanceof Double) {
			Double i1 = (Double) v1;
			Double i2 = (Double) v2;
			return i1.compareTo(i2);
		} else if (v1 instanceof Date && v2 instanceof Date) {
			Date i1 = (Date) v1;
			Date i2 = (Date) v2;
			return i1.compareTo(i2);
		} else if (v1 instanceof java.sql.Date && v2 instanceof java.sql.Date) {
			java.sql.Date i1 = (java.sql.Date) v1;
			java.sql.Date i2 = (java.sql.Date) v2;
			return i1.compareTo(i2);
		} else if (v1 instanceof java.sql.Timestamp
				&& v2 instanceof java.sql.Timestamp) {
			java.sql.Timestamp i1 = (java.sql.Timestamp) v1;
			java.sql.Timestamp i2 = (java.sql.Timestamp) v2;
			return i1.compareTo(i2);
		} else if (v1 instanceof java.sql.Time && v2 instanceof java.sql.Time) {
			java.sql.Time i1 = (java.sql.Time) v1;
			java.sql.Time i2 = (java.sql.Time) v2;
			return i1.compareTo(i2);
		} else if (v1 instanceof String && v2 instanceof String) {
			String i1 = (String) v1;
			String i2 = (String) v2;
			return i1.compareTo(i2);
		} else if (v1 instanceof java.lang.Enum && v2 instanceof java.lang.Enum) {
			java.lang.Enum i1 = (java.lang.Enum) v1;
			java.lang.Enum i2 = (java.lang.Enum) v2;
			return i1.compareTo(i2);
		}
		return 0;
	}

	// ///////////////////////////////////////////////////
	public static final byte[] zip(byte[] b) throws IOException {
		ByteArrayOutputStream baos = ObjPool
				.borrowObject(ByteArrayOutputStream.class);
		try {
			GZIPOutputStream gos = new GZIPOutputStream(baos);
			gos.write(b);
			gos.finish();
			return baos.toByteArray();
		} catch (IOException e) {
			throw e;
		} finally {
			ObjPool.returnObject(baos);
		}
	}

	public static final byte[] unzip(byte[] b) throws IOException {
		ByteArrayOutputStream baos = ObjPool
				.borrowObject(ByteArrayOutputStream.class);
		try {
			int times = 1000;
			byte[] buff = new byte[4 * 1024];
			InputStream bais = newStream(b);
			GZIPInputStream gis = new GZIPInputStream(bais);
			while (true) {
				if (times-- <= 0)
					break;
				int len = gis.read(buff);
				if (len <= 0)
					break;
				baos.write(buff, 0, len);
			}
			return baos.toByteArray();
		} catch (IOException e) {
			throw e;
		} finally {
			ObjPool.returnObject(baos);
		}
	}

	public static final byte[] unzip(byte[] b, int srcLen) throws IOException {
		byte[] buff = new byte[srcLen];
		InputStream bais = newStream(b);
		GZIPInputStream gis = new GZIPInputStream(bais);
		gis.read(buff);
		return buff;
	}

	// ///////////////////////////////////////////////////
	// 文件读写
	public static final byte[] readFully(String fn) {
		File f = new File(fn);
		return readFully(f);
	}

	public static final byte[] readResource(String fn) {
		URL url = log.getClass().getResource(fn);
		String furl = url.getFile();
		File f = new File(furl);
		return readFully(f);
	}

	public static final byte[] readFully(File f) {
		try {
			if (f == null || !f.exists())
				return null;
			int len = (int) f.length();
			byte[] b = new byte[len];
			FileInputStream fis;
			fis = new FileInputStream(f);
			fis.read(b);
			fis.close();
			return b;
		} catch (Exception e) {
			log.error(Svc.e2s(e));
		}
		return null;
	}

	public static final byte[] readStream(InputStream is) {
		byte[] buf = new byte[1024];
		ByteArrayOutputStream out = newStream();
		int times = 1000;
		while (true) {
			try {
				if (times-- <= 0)
					break;
				int len = is.read(buf);
				if (len <= 0)
					break;
				out.write(buf, 0, len);
			} catch (Exception e) {
			}
		}
		byte[] b = out.toByteArray();
		return b;
	}

	public static final void writeFully(File f, byte[] b) throws IOException {
		if (f == null)
			return;
		FileOutputStream fos = new FileOutputStream(f, false);
		fos.write(b);
		fos.close();
	}

	public static final void writeFully(String fn, byte[] b) throws IOException {
		File f = new File(fn);
		writeFully(f, b);
	}

	// ///////////////////////////////////////////////////
	public static final String fmt_yyyy_MM_dd_HH_mm_ss_sss = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String fmt_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	public static final String fmt_yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
	public static final String fmt_yyyy_MM_dd_HH = "yyyy-MM-dd HH";
	public static final String fmt_yyyy_MM_dd = "yyyy-MM-dd";
	public static final String fmt_HH_mm_ss = "HH:mm:ss";
	public static final String fmt_HH_mm = "HH:mm";
	public static final String fmt_MM_dd_HH_mm = "MM-dd HH:mm";
	public static final String fmt_MM_dd = "MM-dd";
	public static final String fmtMMddHHmmss = "MMddHHmmss";
	public static final String fmtYyyy = "yyyy";
	public static final String fmtYyyyMm = "yyyyMM";
	public static final String fmtYyyyMmDd = "yyyyMMdd";
	public static final String fmtYyyyMmDdHHmmss = "yyyyMMddHHmmss";

	public static final Date parse(String fmt, String s) {
		try {
			SimpleDateFormat f = new SimpleDateFormat(fmt);
			return f.parse(s);
		} catch (Exception e) {
		}
		return new Date();
	}

	public static boolean isTimeout(long LASTTIME, long TIMEOUT) {
		if (TIMEOUT <= 0)
			return false;
		long l2 = System.currentTimeMillis();
		long t = l2 - LASTTIME;
		return (t > TIMEOUT);
	}

	public static boolean isTimeout(Date DLASTTIME, long TIMEOUT) {
		if (TIMEOUT <= 0)
			return false;
		long LASTTIME = DLASTTIME.getTime();
		long l2 = System.currentTimeMillis();
		long t = l2 - LASTTIME;
		return (t > TIMEOUT);
	}

	public static final long now() {
		return System.currentTimeMillis();
	}

	public static final String nowYearMMddHHmmss() {
		return new SimpleDateFormat(fmt_yyyy_MM_dd_HH_mm_ss).format(new Date());
	}

	public static final String tFmt(Date d) {
		return new SimpleDateFormat(fmt_yyyy_MM_dd_HH_mm_ss).format(d);
	}

	public static final String tFmt2(Date d) {
		return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(d);
	}

	public static final String nowHourMm() {
		return new SimpleDateFormat(fmt_HH_mm).format(new Date());
	}

	public static final String nowMonthDdHHmm() {
		return new SimpleDateFormat(fmt_MM_dd_HH_mm).format(new Date());
	}

	// 两个时间的时间差
	public static final long timeDifference(Date d1, Date d2) {
		long l1 = d1.getTime();
		long l2 = d2.getTime();
		return l2 - l1;
	}

	public static String difToStr(long dif) {
		if (dif <= 0) {
			return "";
		}
		long day = dif / (24 * 60 * 60 * 1000);
		long hour = (dif / (60 * 60 * 1000) - day * 24);
		long min = ((dif / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (dif / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		return "" + day + "天" + hour + "小时" + min + "分" + s + "秒";
	}

	public static final String s(String s, Object... args) {
		return String.format(s, args);
	}

	public static final void s(StringBuffer sb, String s, Object... args) {
		String s2 = String.format(s, args);
		sb.append(s2);
	}

	public static final String sn(String s, Object... args) {
		return String.format(s + "\r\n", args);
	}

	public static final void sn(StringBuffer sb, String s, Object... args) {
		s(sb, s + "\r\n", args);
	}

	public static final String fmt(String s, Object... args) {
		return String.format(s, args);
	}

	public static final String format(String s, Object... args) {
		return String.format(s, args);
	}

	// 带1位小数
	public static String n2s(int i) {
		if (i < 1000)
			return i + "";
		if (i < 1000 * 10)
			return String.format("%.1fK", ((double) i / 1000));
		if (i < 1000 * 1000)
			return String.format("%.1fW", ((double) i / 10000));
		if (i < 1000 * 1000 * 1000)
			return String.format("%.1fM", ((double) i / (1000 * 1000)));
		return String.format("%.1fG", ((double) i / (1000 * 1000 * 1000)));
	}

	// 自动识别是否带小数
	public static String n2sn(long i) {
		if (i < 1000)
			return i + "";
		if (i < 1000 * 10)
			return String.format("%.1fK", ((double) i / 1000));
		if (i < 1000 * 1000)
			return String.format("%.0fW", ((double) i / 10000));
		if (i < 1000 * 1000 * 1000)
			return String.format("%.0fM", ((double) i / (1000 * 1000)));
		return String.format("%.1fG", ((double) i / (1000 * 1000 * 1000)));
	}

	// 带小数,支持负数
	public static String n(int i) {
		boolean abs = false;
		if (i < 0) {
			i = -i;
			abs = true;
		}
		String s = n2s(i);
		String r = abs ? ('-' + s) : s;
		return r;
	}

	// ///////////////////////////////////////////////////
	// 计算两点间距离
	public final static int distance(Point a, Point b) {
		int x1 = a.x;
		int y1 = a.y;
		int x2 = b.x;
		int y2 = b.y;
		return distance(x1, y1, x2, y2);
	}

	// 计算直角距离
	public final static int distance90Angle(Point a, Point b) {
		int x1 = a.x;
		int y1 = a.y;
		int x2 = b.x;
		int y2 = b.y;
		return Math.abs(x2 - x1) + Math.abs(y2 - y1);
	}

	public final static int distance90Angle(int x1, int y1, int x2, int y2) {
		return Math.abs(x2 - x1) + Math.abs(y2 - y1);
	}

	public final static int distance(int x1, int y1, int x2, int y2) {
		double v = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
		return (int) v;
	}

	// ///////////////////////////////////////////////////
	// 计算两点间距离(直线，直角)
	public final static int distanceXy(int x1, int y1, int x2, int y2) {
		double v = Math.abs(x1 - x2) + Math.abs(y1 - y2);
		return (int) v;
	}

	// 周围4个点，按远近关系排序
	public final static List<Point> point4(final Point f, final Point t) {
		List<Point> p4 = new ArrayList<Point>();
		p4.add(new Point(t.x - 1, t.y));
		p4.add(new Point(t.x + 1, t.y));
		p4.add(new Point(t.x, t.y - 1));
		p4.add(new Point(t.x, t.y + 1));
		// 按距离排序
		Collections.sort(p4, new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				Integer d1 = distance90Angle(f, o1);
				Integer d2 = distance90Angle(f, o2);
				return d1.compareTo(d2);
			}
		});
		return p4;
	}

	// 近点
	public final static Point near(List<Point> p4) {
		return p4.get(1);
	}

	// 远点
	public final static Point far(List<Point> p4) {
		return p4.get(p4.size() - 1);
	}

	// 到达目标的最远点
	public final static Point p2(final Point f, final Point t, final int d) {
		int x2 = f.x;
		int y2 = f.y;

		int dist = distance90Angle(f, t);
		if (dist < d)
			return new Point(t.x, t.y);

		int d2 = d;
		int mx = Math.abs(t.x - f.x);
		int my = Math.abs(t.y - f.y);

		if (mx > my) { // 先走x边
			if (d2 > 0) {
				if (f.x > t.x)
					x2 = d2 > mx ? f.x - mx : f.x - d2;
				else
					x2 = d2 > mx ? f.x + mx : f.x + d2;
				d2 -= mx;
			}

			if (d2 > 0) {
				if (f.y > t.x)
					y2 = d2 > my ? f.y - my : f.y - d2;
				else
					y2 = d2 > my ? f.y + my : f.y + d2;
				d2 -= my;
			}
		} else { // 先走y边
			if (d2 > 0) {
				if (f.y > t.y) {
					y2 = d2 > my ? f.y - my : f.y - d2;
				} else {
					y2 = d2 > my ? f.y + my : f.y + d2;
				}
				d2 -= d2 > my ? my : d2;
			}

			if (d2 > 0) {
				if (f.x > t.x)
					x2 = d2 > mx ? f.x - mx : f.x - d2;
				else
					x2 = d2 > mx ? f.x + mx : f.x + d2;
				d2 -= d2 > mx ? mx : d2;
			}
		}

		Point p = new Point(x2, y2);
		return p;
	}

	public final static Point move2d(final Point f, final Point t, final int d,
			final int range, List<Point> occupys) {
		if (f == null || t == null)
			return null;
		if (occupys == null)
			occupys = new ArrayList<Point>();

		List<Point> p4 = point4(f, t);

		// 排除不能到达的点
		Iterator<Point> it = p4.iterator();
		while (it.hasNext()) {
			int dist = distance90Angle(f, it.next());
			if (dist > d)
				it.remove();
		}

		// 如果没有点了则找一个能够到达的最近的点
		if (p4.isEmpty()) {
			p4.add(p2(f, t, d));
		}

		if (occupys.isEmpty())
			return p4.get(0);

		// 找出没有船的点
		for (Point p : occupys) {
			if (intersectedPoints(occupys, p)) {
				continue;
			}
			return p;
		}

		// 没有找到，继续找新点
		return move2d(f, t, d - 1, range, occupys);
	}

	// 移动到指定位置
	// public final static Point moveXy(Point a, Point b, int d) {
	// Point result = new Point();
	// int mx = Math.abs(b.x - a.x);
	// int my = Math.abs(b.y - a.y);
	//
	// if (d < mx - 1) {
	// result.x = (b.x > a.x) ? (a.x + d) : (a.x - d);
	// result.y = a.y;
	// return result;
	// }
	//
	// int d2 = d - mx;
	// if (d2 < my - 1) {
	// result.x = b.x;
	// result.y = (b.y < a.y) ? (a.y - d2) : (a.y + d2);
	// return result;
	// }
	//
	// result = b;
	// if (a.y > b.y) // 根据上下位置关系决定最后位置
	// result.y = b.y + 1;
	// else
	// result.y = b.y - 1;
	// return result;
	//
	// }

	// 直线上的所有点
	public final static List<Point> points2(int x1, int y1, int x2, int y2) {
		List<Point> result = newList();
		Point a = new Point(x1, y1);
		Point b = new Point(x2, y2);
		if (x1 > x2) {
			Point x = a;
			a = b;
			b = x;
		}
		Set<String> e = newSet();
		// 循环x坐标
		for (int i = a.x + 1; i < b.x; i++) {
			// 计算斜率
			double k = ((double) (a.y - b.y)) / (a.x - b.x);
			// 根据斜率，计算y坐标
			double y = k * (i - a.x) + a.y;
			// 简单判断一下y是不是整数
			// double d = y - (int) y;
			int ey = (int) y;
			String key = i + "," + ey;
			if (e.contains(key))
				continue;
			e.add(key);
			result.add(new Point(i, ey));
			// if (0.001 > d && d > -0.001) {
			// // Console.Write("点的坐标：{0},{1}", i, y);
			// result.add(new Point(i, (int) y));
			// }
		}
		return result;
	}

	// 计算百分率
	public static final int percent(double v, double max) {
		if (v <= 0 || max <= 0)
			return 0;
		int r = (int) (v * 100 / max);
		return r > 100 ? 100 : r;
	}

	// ///////////////////////////////////////////////////
	public static final int argb(int a, int r, int g, int b) {
		return (r << 24) + (r << 16) + (g << 8) + b;
	}

	public static final int[] argb(long argb) {
		int a = (byte) ((argb >> 24) & 0xff);
		int r = (byte) ((argb >> 16) & 0xff);
		int g = (byte) ((argb >> 8) & 0xff);
		int b = (byte) ((argb >> 0) & 0xff);
		int[] v = { a, r, g, b };
		return v;
	}

	// ///////////////////////////////////////////////////
	public static final int rgb(int a, int r, int g, int b) {
		return (r << 16) + (g << 8) + b;
	}

	public static final int[] rgb(int rgb) {
		int r = (byte) ((rgb >> 16) & 0xff);
		int g = (byte) ((rgb >> 8) & 0xff);
		int b = (byte) ((rgb >> 0) & 0xff);
		int[] v = { r, g, b };
		return v;
	}

	// ///////////////////////////////////////////////////
	public static boolean isNull(Object o) {
		return o == null;
	}

	public static boolean isEmpty(byte[] o) {
		return o == null || o.length <= 0;
	}

	public static boolean isEmpty(List o) {
		return o == null || o.isEmpty();
	}

	public static boolean isEmpty(Set o) {
		return o == null || o.isEmpty();
	}

	public static boolean isEmpty(Map o) {
		return o == null || o.isEmpty();
	}

	public static boolean isEmpty(String o) {
		return o == null || o.isEmpty();
	}

	public static boolean notNull(Object o) {
		return o != null;
	}

	public static boolean notEmpty(byte[] o) {
		return o != null && o.length > 0;
	}

	public static boolean notEmpty(List o) {
		return o != null && !o.isEmpty();
	}

	public static boolean notEmpty(Map o) {
		return o != null && !o.isEmpty();
	}

	public static boolean notEmpty(String o) {
		return o != null && !o.isEmpty();
	}

	public static boolean isInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// 错误堆栈的内容
	// public static String e2s(Exception e) {
	// StringBuffer sb = StringBufPool.borrowObject();
	// try {
	// sb.append(e);
	// sb.append("\r\n");
	// for (StackTraceElement ste : e.getStackTrace()) {
	// sb.append("at ");
	// sb.append(ste);
	// sb.append("\r\n");
	// }
	// return sb.toString();
	// } finally {
	// StringBufPool.returnObject(sb);
	// }
	// }
	public static String e2s(Throwable e) {
		return e2s(e, null, new Object[0]);
	}

	public static String e2s(Throwable e, Object obj) {
		return e2s(e, String.valueOf(obj), new Object[0]);
	}

	public static String e2s(Throwable e, String fmt, Object... args) {
		StringBuffer sb = StringBufPool.borrowObject();
		try {
			sb.append(e);
			if (fmt != null && !fmt.isEmpty() && args.length <= 0)
				sb.append(" - ").append(fmt);
			if (fmt != null && !fmt.isEmpty() && args.length > 0) {
				String str = StrBuilder.builder().ap(fmt, args).str();
				sb.append(" - ").append(str);
			}
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

	// ///////////////////////////////////////////////////
	// public static final ExecutorService newSingleThreadExecutor() {
	// return Executors.newSingleThreadExecutor();
	// }

	// public static final ExecutorService newFixedThreadPool(int n) {
	// return Executors.newFixedThreadPool(n);
	// }

	public static final ScheduledExecutorService newScheduledThreadPool(
			String name, int n) {
		return Executors.newScheduledThreadPool(n, new MyThreadFactory(name,
				false));
	}

	// public static final ExecutorService singleThreadExecutor =
	// newSingleThreadExecutor();
	// public static final ExecutorService fixed8ThreadPool =
	// newFixedThreadPool(8);

	// public static final void executeSingle(Runnable r) {
	// singleThreadExecutor.execute(r);
	// }

	public static final void executeFixed(ExecutorService fixed8ThreadPool,
			Runnable r) {
		fixed8ThreadPool.execute(r);
	}

	// public static final <T> Future<T> submit4Fixed(Callable<T> task) {
	// return scheduled4Pool.submit(task);
	// }

	public static final ScheduledFuture<?> scheduled(
			ScheduledExecutorService threadPool, Runnable r, Date d) {
		long delay = d.getTime() - now();
		delay = delay <= 0 ? 1 : delay;
		return threadPool.schedule(r, delay, TimeUnit.MILLISECONDS);
	}

	public static final ScheduledFuture<?> scheduledFixedDelay(
			ScheduledExecutorService threadPool, Runnable r, Date d, long delay) {
		long initialDelay = d.getTime() - now();
		initialDelay = initialDelay <= 0 ? 1 : initialDelay;
		return scheduledFixedDelay(threadPool, r, initialDelay, delay);
	}

	// 延时执行
	public static final ScheduledFuture<?> scheduled(
			ScheduledExecutorService threadPool, Runnable r, long delay) {
		return threadPool.schedule(r, delay, TimeUnit.MILLISECONDS);
	}

	// 定间隔时间执行
	public static final ScheduledFuture<?> scheduledFixedDelay(
			ScheduledExecutorService threadPool, Runnable r, long initialDelay,
			long delay) {
		return threadPool.scheduleWithFixedDelay(r, initialDelay, delay,
				TimeUnit.MILLISECONDS);
	}

	// 确定时分秒，每日执行
	public static final ScheduledFuture<?> scheduledEveryDay(
			ScheduledExecutorService threadPool, Runnable r, int hour,
			int minute, int sec) {
		long now = now();
		long tomorrow = new NewDate().setHourMinuteSec(0, 0, 0).addDay(+1)
				.getTime();
		long h = DateEx.hour();
		long m = DateEx.minute();
		long s = DateEx.second();
		long initialDelay = 0;
		long e1 = h * DateEx.TIME_HOUR + m * DateEx.TIME_MINUTE + s
				* DateEx.TIME_SECOND;
		long e2 = hour * DateEx.TIME_HOUR + minute * DateEx.TIME_MINUTE + sec
				* DateEx.TIME_SECOND;
		if (e1 < e2) {
			initialDelay = e2 - e1;
		} else {
			initialDelay = tomorrow - now + e2;
		}
		long delay = DateEx.TIME_DAY;
		return threadPool.scheduleAtFixedRate(r, initialDelay, delay,
				TimeUnit.MILLISECONDS);
	}

	// 定时执行
	public static final ScheduledFuture<?> scheduled8FixedRate(
			ScheduledExecutorService threadPool, Runnable r, long initialDelay,
			long delay) {
		return threadPool.scheduleAtFixedRate(r, initialDelay, delay,
				TimeUnit.MILLISECONDS);
	}

	public static Map getMap(Map ps, Object key) {
		Object v = ps.get(key);
		if (v == null)
			v = newMap();
		return (Map) v;
	}

	public static List getList(Map ps, Object key) {
		Object v = ps.get(key);
		if (v == null)
			v = newList();
		return (List) v;
	}

	public static String getString(Map ps, String key) {
		String v = (String) ps.get(key);
		// if (v == null)
		// return "";
		return v;
	}

	public static boolean getBool(Map ps, String key) {
		try {
			Object o = ps.get(key);
			if (o == null)
				return false;
			if (o instanceof Boolean)
				return ((Boolean) o).booleanValue();
			else if (o instanceof String) {
				String v = (String) o;
				return Boolean.parseBoolean(v);
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public static int getInt(Map ps, String key) {
		try {
			Object o = ps.get(key);
			if (o == null)
				return 0;
			if (o instanceof Integer)
				return ((Integer) o).intValue();
			else if (o instanceof String) {
				String v = (String) o;
				return Integer.parseInt(v);
			}
			return 0;
		} catch (Exception e) {
			return 0;
		}
	}

	public static double getDouble(Map ps, String key) {
		try {
			Object o = ps.get(key);
			if (o == null)
				return 0;
			if (o instanceof Double)
				return ((Double) o).doubleValue();
			else if (o instanceof String) {
				String v = (String) o;
				return Double.parseDouble(v);
			} else if (o instanceof Integer) {
				Integer v = (Integer) o;
				return ((Integer) v).doubleValue();
			}
			return 0;
		} catch (Exception e) {
			return 0;
		}
	}

	public static void println(Object... vars) {
		print(vars);
		System.out.println();
	}

	public static void print(Object... vars) {
		StringBuffer sb = StringBufPool.borrowObject();
		try {
			for (Object o : vars) {
				sb.append(o);
			}
			System.out.print(sb);
		} finally {
			StringBufPool.returnObject(sb);
		}
	}

	// //////////////////////////////////////////////
	public static final String pinyin(String s) {
		return PinYin.pinYin(s);
	}

	public static final String py(String s) {
		return PinYin.shortPinYin(s);
	}

	// //////////////////////////////////////////////
	// public final int writeTo(SvcContext ctx, Map map) {
	// try {
	// return ctx.writeTo(map);
	// } catch (Exception e) {
	// log.error(e2s(e));
	// return 0;
	// }
	// }
	//
	// public final int writeTo(SvcContext ctx, byte[] b) {
	// try {
	// return ctx.writeTo(b);
	// } catch (Exception e) {
	// log.error(e2s(e));
	// return 0;
	// }
	// }

	// //////////////////////////////////////////////
	public static String getAppRoot() {
		return System.getProperty("user.dir");
	}

	public static final int TRUE = 0;
	public static final int FALSE = -1;
	public static final int OFFLINE = -2; // 离线
	public static final int VER_FAILE = -3; // 版本错误

	public static final int TECH_NOT_ENOUGH = -4; // 科技不足
	public static final int JEWELLERY_NOT_ENOUGH = -5; // 珠宝不足
	public static final int GEM_NOT_ENOUGH = -6; // 宝石不足
	public static final int NOT_CURR_PORT = -7; // 当前码头不对(码头列表)
	public static final int SHIP_NOT_ENOUGH = -8; // 舰船不足
	public static final int EXP_NOT_ENOUGH = -9; // 经验不足(经验书,打海盗,训练)
	public static final int NEED_PROP = -10; // 需要道具
	public static final int FORGET_ABILITY = -11; // 遗忘(能力，阵形)
	public static final int NEED_FORMATION_SKILL = -12; // 需要阵形(舰长,商城)
	public static final int NEED_KNIGHT = -13; // 需要爵位
	public static final int GOLD_NOT_ENOUGH = -14; // 需要金币
	public static final int EQUIP_NOT_ENOUGH = -15; // 需要装备
	public static final int SILVER_NOT_ENOUGH = -16; // 需要银币
	public static final int WAREHOUSE_NOT_ENOUGH = -17; // 仓库不足
	public static final int TEMPLE_NOT_ENOUGH = -18; // 神殿等级不足
	public static final int COOLDOWN_BUILDING = -19; // 建筑队列需要冷却
	public static final int COOLDOWN_TECH = -20; // 科技队列需要冷却
	public static final int COOLDOWN_FACTORY = -21; // 工厂需要冷却
	public static final int GOVERNOR_NOT_ENOUGH = -22; // 升级总督府
	// public static final int SPELL_NOT_ENOUGH = -23; // 法术不足
	public static final int PIRATE_FLAG_NOT_ENOUGH = -24; // 海盗旗(贡品不足)
	public static final int HONOR_NOT_ENOUGH = -25; // 荣誉不足
	public static final int VIP_NOT_ENOUGH = -26; // 需要VIP等级
	public static final int PRIVATEER_LICENSE_ENOUGH = -27; // 需要私掠许可证
	public static final int NEED_GRID_CAPTAIN = -28; // 需要布阵
	public static final int NEED_PROP_SPACE = -29; // 需要道具背包空间
	public static final int NEED_EQUIP_SPACE = -30; // 需要装备背包空间
	public static final int NEED_ASSEMBLYSHIP = -31; // 需要配置舰船

	public static int min(int... n) {
		int min = 0;
		for (int i : n) {
			if (i <= 0)
				continue;
			if (min == 0) {
				min = i;
			} else {
				min = min < i ? min : i;
			}
		}
		return min;
	}

	public static String reword(String s) {
		while (s.indexOf("[") >= 0) {
			s = s.replace("[", "(");
		}

		while (s.indexOf("]") >= 0) {
			s = s.replace("]", ")");
		}
		return s;
	}

	// 时间格式化为:h:m:s;
	public static final String tmStr(long ms) {// 将毫秒数换算成x天x时x分x秒x毫秒
		int ss = 1000;
		int mi = ss * 60;
		int hh = mi * 60;

		long hour = (ms) / hh;
		long minute = (ms - hour * hh) / mi;
		long second = (ms - hour * hh - minute * mi) / ss;

		StringBuffer sb = StringBufPool.borrowObject();
		try {
			if (hour > 0) {
				if (hour < 10)
					sb.append("0");
				sb.append(hour);
				sb.append(":");
			}
			if (minute > 0) {
				if (minute < 10)
					sb.append("0");
				sb.append(minute);
				sb.append(":");
			}
			if (second < 10)
				sb.append("0");
			sb.append(second).append("s");
			return sb.toString();
		} finally {
			StringBufPool.returnObject(sb);
		}
	}

	public static List many(Object o) {
		List ret = newList();
		ret.add(o);
		return ret;
	}

	public static <T> T single(List o) {
		if (o == null || o.isEmpty())
			return null;
		return (T) o.get(0);
	}

	public static final int interval(int v, int min, int max) {
		v = v < min ? min : v;
		v = v > max ? max : v;
		return v;
	}

	public static String nStr(long n) {
		if (n < -1000000)
			return ((int) (n / 1000000)) + "M";
		if (n < -10000)
			return ((int) (n / 10000)) + "W";
		if (n < -1000)
			return ((int) (n / 1000)) + "K";
		if (n < 0)
			return "" + n;
		else if (n < 1000)
			return "" + n;
		else if (n < 10000)
			return ((int) (n / 1000)) + "K";
		else if (n < 1000000)
			return ((int) (n / 10000)) + "W";
		else
			return ((int) (n / 1000000)) + "M";
	}
}