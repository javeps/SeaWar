package com.toosNets.bio2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.bowlong.bio2.B2InputStream;
import com.bowlong.bio2.B2OutputStream;
import com.bowlong.bio2.B2Size;
import com.bowlong.lang.Offset;
import com.bowlong.objpool.OutputArrayPool;
import com.bowlong.util.NewList;
import com.bowlong.util.NewMap;

@SuppressWarnings("rawtypes")
public class B2Helper {
	public static final byte[] toBytes(Map o) throws Exception {
		ByteArrayOutputStream os = OutputArrayPool.borrowObject();
		try {
			toBytes(os, o);
			return os.toByteArray();
		} catch (Exception e) {
			throw e;
		} finally {
			OutputArrayPool.returnObject(os);
		}
	}

	public static final int length(Map o) throws Exception {
		Offset offset = new Offset();
		length(offset, o);
		return offset.writer;
	}
	
	public static final void toBytes(OutputStream os, Map o) throws Exception {
		B2OutputStream.writeObject(os, o);
	}

	public static final void length(Offset offset, Map o) throws Exception {
		B2Size.sizeMap(offset, o);
	}

	public static final NewMap toMap(byte[] b) throws Exception {
		ByteArrayInputStream bais = new ByteArrayInputStream(b);
		return toMap(bais);
	}

	public static final NewMap toMap(InputStream is) throws Exception {
		NewMap v = (NewMap) B2InputStream.readObject(is);
		return v;
	}

	//////////////////////
	public static final byte[] toBytes(List o) throws Exception {
		ByteArrayOutputStream os = OutputArrayPool.borrowObject();
		try {
			toBytes(os, o);
			return os.toByteArray();
		} catch (Exception e) {
			throw e;
		} finally {
			OutputArrayPool.returnObject(os);
		}
	}

	public static final int length(List o) throws Exception {
		Offset offset = new Offset();
		length(offset, o);
		return offset.writer;
	}

	public static final void toBytes(OutputStream os, List o) throws Exception {
		B2OutputStream.writeObject(os, o);
	}

	public static final void length(Offset offset, List o) throws Exception {
		B2Size.sizeVector(offset, o);
	}

	public static final NewList toList(byte[] b) throws Exception {
		if(b==null)
			return null;
		ByteArrayInputStream bais = new ByteArrayInputStream(b);
		return toList(bais);
	}

	public static final NewList toList(InputStream is) throws Exception {
		NewList v = (NewList) B2InputStream.readObject(is);
		return v;
	}
}