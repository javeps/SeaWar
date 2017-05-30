package com.sea.db;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.bowlong.io.FileEx;
import com.bowlong.pinyin.PinYin;
import com.bowlong.sql.builder.jdbc.mysql.BeanBuilder;
import com.bowlong.sql.builder.jdbc.mysql.DaoBuilder;
import com.bowlong.sql.builder.jdbc.mysql.EntityBuilder;
import com.bowlong.sql.builder.jdbc.mysql.InternalBuilder;
import com.bowlong.sql.builder.jdbc.mysql.RMIBuilder;
import com.bowlong.sql.builder.jdbc.mysql.RemoteBuilder;
import com.bowlong.util.ListEx;
import com.sea.content.AppContext;

public class ABuilder {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {

		DataSource ds = AppContext.ds2();
		String pkg = ABuilder.class.getPackage().getName();
		System.out.println(pkg);
		 List<String> tbs = com.bowlong.sql.SqlEx.getTables(ds);
		//
		// boolean isd = isDuplicate(tbs);
		// if (isd)
		// throw new IOException("table pinyin name duplicate.");

//		String[] tbs = { "order_" };
		List<String> tb = ListEx.toVector(tbs);
		boolean src = FileEx.exists("src");
		String appcontext = AppContext.class.getName();
		System.out.println(appcontext);
		boolean batch = true;

		for (String tablename : tb) {
			System.out.println("====================");
			System.out.println(tablename);
			BeanBuild(ds.getConnection(), tablename, pkg, src);
			DaoBuild(ds.getConnection(), tablename, pkg, src, batch);
			InternalBuild(ds.getConnection(), tablename, appcontext, pkg, src,
					batch);
			EntityBuild(ds.getConnection(), tablename, appcontext, pkg, src);
			// RMIBuild(ds.getConnection(), tablename, appcontext, pkg, src,
			// batch);
			// RemoteBuild(ds.getConnection(), tablename, appcontext, pkg, src,
			// batch);
		}
		System.exit(1);
	}

	public static void BeanBuild(Connection conn, String tablename, String pkg,
			boolean src) throws Exception {

		String sql = String.format("SELECT * FROM `%s` LIMIT 1", tablename);

		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		// BeanBuilder builder = new BeanBuilder();
		// String xml = builder.build(rs, pkg + "bean", true);
		String xml = BeanBuilder.build(conn, rs, pkg);
		System.out.println(xml);
		String filename = file(pkg, src, "bean", tablename, "java");
		File f = new File(filename);
		File parent = f.getParentFile();
		if (!parent.exists() || !parent.isDirectory()) {
			parent.mkdir();
		}
		writeFile(filename, xml);
		conn.close();
	}

	public static void DaoBuild(Connection conn, String tablename, String pkg,
			boolean src, boolean batch) throws Exception {

		String sql = String.format("SELECT * FROM `%s` LIMIT 1", tablename);

		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		// DaoBuilder builder = new DaoBuilder();
		// String xml = builder.build(conn, rs, pkg + "dao", pkg + "bean");
		String xml = DaoBuilder.build(conn, rs, pkg, batch);
		System.out.println(xml);
		String filename = file(pkg, src, "dao", tablename + "DAO", "java");
		File f = new File(filename);
		File parent = f.getParentFile();
		if (!parent.exists() || !parent.isDirectory()) {
			parent.mkdir();
		}
		writeFile(filename, xml);
		conn.close();

	}

	public static void InternalBuild(Connection conn, String tablename,
			String appcontext, String pkg, boolean src, boolean batch)
			throws Exception {
		boolean sorted = true;

		String sql = String.format("SELECT * FROM `%s` LIMIT 1", tablename);

		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		// InternalBuilder builder = new InternalBuilder();
		// String xml = builder.build(conn, rs, pkg + "internal", pkg + "bean",
		// pkg + "dao", pkg + "entity", appcontext, immediately);
		String xml = InternalBuilder.build(conn, rs, pkg, appcontext, batch,
				sorted);
		System.out.println(xml);
		String filename = file(pkg, src, "internal", tablename + "Internal",
				"java");
		File f = new File(filename);
		File parent = f.getParentFile();
		if (!parent.exists() || !parent.isDirectory()) {
			parent.mkdir();
		}
		writeFile(filename, xml);
		conn.close();
	}

	public static void EntityBuild(Connection conn, String tablename,
			String appContext, String pkg, boolean src) throws Exception {
		String filename = file(pkg, src, "entity", tablename + "Entity", "java");
		File f = new File(filename);
		if (f.exists())
			return;
		File parent = f.getParentFile();
		if (!parent.exists() || !parent.isDirectory()) {
			parent.mkdir();
		}

		String sql = String.format("SELECT * FROM `%s` LIMIT 1", tablename);

		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		// EntityBuilder builder = new EntityBuilder();
		// String xml = builder.build(conn, rs, pkg + "entity", pkg + "bean",
		// pkg
		// + "dao", pkg + "internal", appcontext, immediately);
		String xml = EntityBuilder.build(conn, rs, pkg, appContext);
		System.out.println(xml);
		// String filename = file(pkg, src, "entity", tablename + "Entity",
		// "java");
		// File f = new File(filename);
		// if (!f.exists())
		writeFile(filename, xml);
		conn.close();
	}

	public static void RMIBuild(Connection conn, String tablename,
			String appContext, String pkg, boolean src, boolean batch)
			throws Exception {
		String filename = file(pkg, src, "rmi", tablename + "RMI", "java");
		File f = new File(filename);
		if (f.exists())
			return;
		File parent = f.getParentFile();
		if (!parent.exists() || !parent.isDirectory()) {
			parent.mkdir();
		}

		// File f = new File(filename);
		// if (f.exists())
		// return;
		String sql = String.format("SELECT * FROM `%s` LIMIT 1", tablename);

		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		// EntityBuilder builder = new EntityBuilder();
		// String xml = builder.build(conn, rs, pkg + "entity", pkg + "bean",
		// pkg
		// + "dao", pkg + "internal", appcontext, immediately);

		String xml = RMIBuilder.build(conn, rs, pkg, appContext, batch);
		System.out.println(xml);
		// String filename = file(pkg, src, "entity", tablename + "Entity",
		// "java");
		// File f = new File(filename);
		// if (!f.exists())
		writeFile(filename, xml);
		conn.close();
	}

	public static void RemoteBuild(Connection conn, String tablename,
			String appContext, String pkg, boolean src, boolean batch)
			throws Exception {
		String filename = file(pkg, src, "remote", tablename + "Remote", "java");
		File f = new File(filename);
		if (f.exists())
			return;
		File parent = f.getParentFile();
		if (!parent.exists() || !parent.isDirectory()) {
			parent.mkdir();
		}

		// File f = new File(filename);
		// if (f.exists())
		// return;
		String sql = String.format("SELECT * FROM `%s` LIMIT 1", tablename);

		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		// EntityBuilder builder = new EntityBuilder();
		// String xml = builder.build(conn, rs, pkg + "entity", pkg + "bean",
		// pkg
		// + "dao", pkg + "internal", appcontext, immediately);

		String xml = RemoteBuilder.build(conn, rs, pkg, appContext, batch);
		System.out.println(xml);
		// String filename = file(pkg, src, "entity", tablename + "Entity",
		// "java");
		// File f = new File(filename);
		// if (!f.exists())
		writeFile(filename, xml);
		conn.close();
	}

	public static String file(String pkg, boolean src, String type,
			String tablename, String ext) {
		String path = pkg2Path(pkg);
		if (src)
			path = "src/" + path;
		path = path + "/" + type + "/"
				+ upperFirst(PinYin.getShortPinYin(tablename)) + "." + ext;
		return path;
	}

	public static void writeFile(String f, String s) throws Exception {
		FileOutputStream fos = new FileOutputStream(f);
		fos.write(s.getBytes());
		fos.close();
	}

	public static String pkg2Path(String pkg) {
		return pkg.replaceAll("\\.", "/");
	}

	public static boolean isDuplicate(List<String> tables) {
		Map<String, String> m = new HashMap<String, String>();
		for (String s : tables) {
			String s2 = PinYin.shortPinYin(s);
			String t = m.get(s2);
			if (t != null) {
				System.out.println(s + " duplicate with:" + t);
				return true;
			}
			m.put(s2, s);
		}
		return false;
	}

	public static final String upperFirst(String s) {
		int len = s.length();
		if (len <= 0)
			return "";

		StringBuffer sb = new StringBuffer();
		sb.append(s.substring(0, 1).toUpperCase());
		sb.append(s.substring(1, len));

		return sb.toString();
	}
}
