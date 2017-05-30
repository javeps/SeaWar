package com.sea.channel.qihu360;

import java.security.MessageDigest;

public class CalPrivateKey {
	public static void main(String[] args) {
		String appKey = PayQiHu360.appKey;
		String appSecret = PayQiHu360.appSecret;
		String privateKey = getHash(appSecret + "#" + appKey);
		System.out.println(privateKey);
	}

	public static String getHash(String uri) {
		MessageDigest mDigest;
		try {
			mDigest = MessageDigest.getInstance("MD5");
			mDigest.update(uri.getBytes());
			byte d[] = mDigest.digest();
			return toHexString(d);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uri;
	}

	private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static String toHexString(byte[] b) {
		// String to byte
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
			sb.append(HEX_DIGITS[b[i] & 0x0f]);
		}
		return sb.toString();
	}
}
