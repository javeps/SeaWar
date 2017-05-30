package com.sea.handler.response;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;

import com.bowlong.netty.objpool.ChannelBufferPool;
import com.sea.bio2.B2OutputStream;

public class ResponseTcp extends ResponseTWFactory {
	
	private static final long serialVersionUID = 1L;
	
	static Log log = LogFactory.getLog(ResponseTcp.class);

	static void sendBack(Channel chn, Map<Object, Object> params)
			throws Exception {
		ChannelBuffer cb = ChannelBufferPool.borrowObject();
		try {
			com.bowlong.netty.bio2.B2Helper.toBytes(cb, params);
			// int length = cb.writerIndex();
			// byte[] dst = new byte[length];
			// cb.getBytes(0, dst, 0, length);
			// System.out.println(params);
			ChannelBuffer buf = ChannelBuffers.dynamicBuffer();
			B2OutputStream.writeInt(buf, cb.writerIndex());
			buf.writeBytes(cb, cb.writerIndex());
			// B2OutputStream.writeInt(buf, len);
			chn.write(buf.copy(0, buf.writerIndex()));
		} finally {
			ChannelBufferPool.returnObject(cb);
		}
	}

	public static void sendDefault(Channel chn, Map<Object, Object> params)
			throws Exception {

		ChannelBuffer cb = ChannelBufferPool.borrowObject();
		try {
			com.bowlong.netty.bio2.B2Helper.toBytes(cb, params);
			int length = cb.writerIndex();
			byte[] dst = new byte[length];
			cb.getBytes(0, dst, 0, length);
			sendDefault(chn, dst);
		} finally {
			ChannelBufferPool.returnObject(cb);
		}
	}

	// 直接写内容
	public static void sendDefault(Channel chn, byte[] buff) throws Exception {
		ChannelBuffer cb = ChannelBufferPool.borrowObject();
		try {
			cb.writeBytes(buff);
			chn.write(cb.copy(0, cb.writerIndex()));
		} finally {
			ChannelBufferPool.returnObject(cb);
		}
	}

	public static void send(Channel chn, Map<Object, Object> params)
			throws Exception {

		ChannelBuffer cb = ChannelBufferPool.borrowObject();
		try {
			com.bowlong.netty.bio2.B2Helper.toBytes(cb, params);
			int length = cb.writerIndex();
			byte[] dst = new byte[length];
			cb.getBytes(0, dst, 0, length);
			send(chn, dst);
		} finally {
			ChannelBufferPool.returnObject(cb);
		}
	}

	// 先写长度在写内容
	public static void send(Channel chn, byte[] buff) throws Exception {

		ChannelBuffer cb = ChannelBufferPool.borrowObject();
		try {
			B2OutputStream.writeInt(cb, buff.length);
			cb.writeBytes(buff);
			chn.write(cb.copy(0, cb.writerIndex()));
		} finally {
			ChannelBufferPool.returnObject(cb);
		}
	}

}
