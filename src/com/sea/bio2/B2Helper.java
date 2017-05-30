package com.sea.bio2;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.buffer.ChannelBuffer;

import com.bowlong.lang.ByteEx;
import com.bowlong.netty.objpool.ChannelBufferPool;
import com.bowlong.util.NewList;
import com.bowlong.util.NewMap;

@SuppressWarnings("rawtypes")
public class B2Helper {
	static Log log = LogFactory.getLog(B2Helper.class);

	public static final byte[] toBytes(Map o) throws Exception {
		ChannelBuffer os = ChannelBufferPool.borrowObject();
		try {
			toBytes(os, o);
			int offset = os.writerIndex();
			byte[] result = new byte[offset];
			os.getBytes(0, result, 0, offset);
			return result;
		} catch (Exception e) {
			throw e;
		} finally {
			ChannelBufferPool.returnObject(os);
		}
	}

	public static final void toBytes(ChannelBuffer os, Map o) {
		try {
			B2OutputStream.writeObject(os, o);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public static final NewMap toMap(ChannelBuffer is) {
		try {
			NewMap v = (NewMap) B2InputStream.readObject(is);
			return v;
		} catch (Exception e) {
			log.error(e.getMessage() + "bytes:"
					+ ByteEx.bytesToString(is.array()));
		}
		return null;
	}

	// ////////////////////
	public static final byte[] toBytes(List o) {
		ChannelBuffer os = ChannelBufferPool.borrowObject();
		try {
			toBytes(os, o);
			int offset = os.writerIndex();
			byte[] result = new byte[offset];
			os.getBytes(0, result, 0, offset);
			return result;
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			ChannelBufferPool.returnObject(os);
		}
		return null;
	}

	public static final void toBytes(ChannelBuffer os, List o) {
		try {
			B2OutputStream.writeObject(os, o);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public static final NewList toList(ChannelBuffer is) throws Exception {
		try {
			NewList v = (NewList) B2InputStream.readObject(is);
			return v;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}
}
