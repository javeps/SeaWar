package com.sea.db;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.bowlong.Toolkit;
import com.bowlong.io.FileEx;
import com.bowlong.lang.StrEx;
import com.bowlong.pinyin.PinYin;
import com.sea.content.AppContext;

public class LoadAll extends Toolkit {

	/**
	 * @param args
	 * @throws Exception
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException, Exception {
		String appcontext = AppContext.class.getName();
		String fn = pkg2Path(appcontext) + ".java";
		boolean src = FileEx.exists("src");
		if (src)
			fn = "src/" + fn;
		File f = new File(fn);
		System.out.println(fn);
		if (!f.exists()) {
			System.out.println(fn + "not found!");
			return;
		}

		String xml = readFully(f, "GBK");
		int p1 = xml.indexOf("// loadAll begin");
		int p2 = xml.indexOf("// loadAll end");
		if (p1 <= 0 || p2 <= 0)
			return;

		StringBuffer sb = new StringBuffer();

		DataSource ds = AppContext.ds();
		String pkg = ABuilder.class.getPackage().getName() + ".";
		System.out.println(pkg);
		List<String> tbs = com.bowlong.sql.SqlEx.getTables(ds);
		// String[] tbs = { "系统公告"};
		// List<String> tb = ListEx.toVector(tbs);
		for (String t : tbs) {
			String s = PinYin.getShortPinYin(t);
			s = StrEx.upperFirst(s);
			s = s + "Entity";
			sn(sb, "        // " + t);
			sn(sb, "        log.info(\"" + t + "\");");
			sn(sb, "        " + s + ".cacheModel = FULL_CACHE;");
			sn(sb, "        " + s + ".loadAll();");
		}

		StringBuffer result = new StringBuffer();
		result.append(xml.substring(0, p1));
		result.append("// loadAll begin\r\n");
		result.append(sb.toString());
		result.append("\r\n    ");
		result.append(xml.substring(p2, xml.length()));

		System.out.println(result);
		writeFile(fn, result.toString());
	}

	public static void writeFile(String f, String s) throws Exception {
		FileOutputStream fos = new FileOutputStream(f);
		fos.write(s.getBytes());
		fos.close();
	}

	public static String pkg2Path(String pkg) {
		return pkg.replaceAll("\\.", "/");
	}

	public static String file(String pkg, boolean src, String type,
			String tablename, String ext) {
		String path = pkg2Path(pkg);
		if (src)
			path = "src/" + path;
		path = path + "/" + type + "/"
				+ StrEx.upperFirst(PinYin.getShortPinYin(tablename)) + "."
				+ ext;
		return path;
	}

}