package com.sea.tools;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings("resource")
public class ReadWriteString implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(ReadWriteString.class);

	public static void writeStr(String path, String content) {
		File f = null;
		FileOutputStream out = null;
		PrintWriter pw = null;
		try {
			f = createFile(path);
			out = new FileOutputStream(f);
			pw = new PrintWriter(out);
			pw.write(content);
			pw.flush();
			pw.close();
			out.close();
			out = null;
			pw = null;
		} catch (IOException e) {
			log.info(UtileTools.ex2s(e));
		}
	}

	/**
	 * Mapped File way MappedByteBuffer 可以在处理大文件时，提升性能
	 * 
	 * @param path
	 * @return
	 */
	public static byte[] readBytes2(String path) {

		FileChannel fc = null;
		byte[] result = null;
		try {
			fc = new RandomAccessFile(path, "r").getChannel();
			MappedByteBuffer byteBuffer = fc.map(MapMode.READ_ONLY, 0,
					fc.size()).load();
			// System.out.println("Mapped File 处理大文件时，提升性能  isLoaded:" +
			// byteBuffer.isLoaded());
			result = new byte[(int) fc.size()];
			if (byteBuffer.remaining() > 0) {
				byteBuffer.get(result, 0, byteBuffer.remaining());
			}
		} catch (Exception e) {
			log.info(UtileTools.ex2s(e));
		} finally {
			try {
				fc.close();
			} catch (Exception e) {
				log.info(UtileTools.ex2s(e));
			}
		}
		return result;
	}

	public static String readStr2(String path) {
		String r = "";
		byte[] data = readBytes2(path);
		if (data == null)
			return r;
		r = new String(data);
		data = null;
		return r;
	}

	/**
	 * the traditional io way 传统的IO方式
	 * 
	 * @param path
	 * @return
	 */
	public static byte[] readBytes(String path) {
		byte[] r = null;
		File f = new File(path);
		r = readBytes(f);
		return r;
	}

	public static byte[] readBytes(File f) {
		byte[] r = null;
		FileInputStream read = null;
		BufferedInputStream inStream = null;
		ByteArrayOutputStream outStream = null;
		try {
			if (!f.exists()) {
				f = null;
				return r;
			}
			read = new FileInputStream(f);
			inStream = new BufferedInputStream(read);
			outStream = new ByteArrayOutputStream();
			byte[] btBuff = new byte[10240];
			int len = 0;
			while ((len = inStream.read(btBuff)) != -1) {
				outStream.write(btBuff, 0, len);
			}
			r = outStream.toByteArray();
			inStream.close();
			outStream.close();
			read.close();
			f = null;
			read = null;
			inStream = null;
			outStream = null;
		} catch (IOException e) {
			log.info(UtileTools.ex2s(e));
		}
		return r;
	}

	public static String readStr(String path) {
		String r = "";
		byte[] data = readBytes(path);
		if (data == null)
			return r;
		r = new String(data);
		data = null;
		return r;
	}

	public static File createFile(String path) {
		File f = null;
		f = new File(path);
		if (!f.exists()) {
			createFile(f);
		}
		return f;
	}

	public static void createFile(File f) {
		try {
			if (f == null)
				return;
			boolean isExi = f.exists();
			if (!isExi) {
				File pf = f.getParentFile();
				if (!pf.exists()) {
					createDire(pf);
				}
				f.createNewFile();
			}
		} catch (Exception e) {
			log.info(UtileTools.ex2s(e));
		}
	}

	public static void createDire(File f) {
		try {
			if (f == null)
				return;
			boolean isExi = f.exists();
			if (!isExi) {
				f.mkdirs();
			}
		} catch (Exception e) {
			log.info(UtileTools.ex2s(e));
		}
	}
}
