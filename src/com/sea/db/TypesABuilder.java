package com.sea.db;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.bowlong.Toolkit;
import com.bowlong.io.FileEx;
import com.bowlong.lang.StrEx;
import com.bowlong.pinyin.PinYin;
import com.bowlong.sql.SqlEx;
import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;
import com.sea.content.AppContext;

public class TypesABuilder extends Toolkit {

	@SuppressWarnings({ "unchecked" })
	public static void main(String[] args) throws SQLException, Exception {
		DataSource ds = AppContext.ds();

		String pkg = ABuilder.class.getPackage().getName();
		System.out.println(pkg);
		String[] tbs = {
				"海洋",
				"岛屿类型",
				"建筑类型" , 
				"指令类型" ,
				"港口类型" ,
				"用户类型",
				"科技类型",
				"舰船类型",
				"船长类型",
				"道具类型",
				"邮件类型",
				"身体部位",
				"队列类型",
				"建筑",
				"科技",
				"舰船",
				"探险奖励类型",
				"探险类型",
				"技能",
			};
		List<String> tb = ListEx.toVector(tbs);
		boolean src = FileEx.exists("src");
		String appcontext = AppContext.class.getName();
		System.out.println(appcontext);

		for (String tablename : tb) {
			System.out.println("====================");
			System.out.println(tablename);
			EntityBuild(ds.getConnection(), tablename, appcontext, pkg, src);
		}
	}

	@SuppressWarnings("rawtypes")
	public static void EntityBuild(Connection conn, String tablename,
			String appContext, String pkg, boolean src) throws Exception {
		String filename = file(pkg, src, "entity", tablename + "Entity", "java");
		File f = new File(filename);
		if (!f.exists())
			return;
		String xml = readFully(f, "GBK");
		int p1 = xml.indexOf("// types begin");
		int p2 = xml.indexOf("// types end");
		if (p1 <= 0 || p2 <= 0)
			return;

		String sql = "SELECT * FROM " + tablename + " ORDER BY id";
		ResultSet rs = SqlEx.executeQuery(conn, sql);

		StringBuffer sb = new StringBuffer();

		List<Map<String, Object>> columns = SqlEx.getColumns(rs);
		String catalogName = (String) columns.get(0).get("catalogName");
		String table = (String) columns.get(0).get("tableName");
		String pytable = PinYin.getShortPinYin(table);
		String PYTABLE = pytable.toUpperCase();
		sn(sb, "    // %s - %s(%s)", catalogName, table, PYTABLE);

		while (rs.next()) {
			Map e = SqlEx.toMap(rs);
			int id = MapEx.getInt(e, "id");
			String mz = MapEx.getString(e, "名字");
			String pymz = PinYin.getShortPinYin(mz);
			String PYMZ = pymz.toUpperCase();
			sn(sb, "    public static final int %s_%d = %d; \t // %s", PYMZ,
					id, id, mz);
		}

		StringBuffer result = new StringBuffer();
		result.append(xml.substring(0, p1));
		result.append("// types begin\r\n");
		result.append(sb.toString());
		result.append("\r\n    ");
		result.append(xml.substring(p2, xml.length()));

		System.out.println(result);
		writeFile(filename, result.toString());
		conn.close();
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

	public static void writeFile(String f, String s) throws Exception {
		FileOutputStream fos = new FileOutputStream(f);
		fos.write(s.getBytes());
		fos.close();
	}

}