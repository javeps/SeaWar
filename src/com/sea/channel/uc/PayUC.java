package com.sea.channel.uc;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.handler.codec.http.HttpRequest;

import com.bowlong.util.MapEx;
import com.sea.channel.ChnPayVerify;
import com.sea.handler.gate.GateConfig;
import com.sea.handler.response.ResponseWeb;
import com.sea.tools.UtileTools;

public class PayUC {

	static Log log = LogFactory.getLog(PayUC.class);

	static final String AppId = "37105";
	static final String AppKey = "d4b7e1b4b56d8070f0cd38d9ffac5fa1";

	public static String[] keyes = { "data", "sign" };

	static String[] keyes_data = { "amount", "callbackInfo", "failedDesc",
			"gameId", "orderId", "orderStatus", "payWay", "serverId", "ucid" };

	static String TRADE_SUCCESS = "S";

	public static String OUT_SUCCESS = "SUCCESS";
	static String OUT_FAILURE = "FAILURE";
	static String OUT_VERIFY = "verify_code";

	// 充值服务地址
	private static int svcid = 1000;

	public static String checkPay(HttpRequest request) {
		String r = OUT_FAILURE;
		String strUri = "";
		try {
			Map<String, String> bodyMap = ResponseWeb
					.getMapQueryByPostBody(request);
			Map<String, String> kvmap = new HashMap<String, String>();
			for (String key : keyes) {
				String val = MapEx.getString(bodyMap, key);
				kvmap.put(key, val);
			}
			Map<String, String> map = verify(kvmap);
			r = MapEx.getString(map, OUT_VERIFY);

			strUri = ResponseWeb.getStrQueryByMap(bodyMap);
			System.out.println("uc==" + strUri);
			if (OUT_SUCCESS.equals(r)) {
				String billNo = MapEx.getString(map, "callbackInfo");
				String chnBillNo = MapEx.getString(map, "orderId");
				double amount = MapEx.getDouble(map, "amount");// 单位：元。
				ChnPayVerify.issuePayToGameByFormula(GateConfig.TGG_Payment_UC,
						svcid, billNo, amount, chnBillNo, strUri);
			} else {
				log.info("===UC 充值失败====\n" + strUri);
			}
			r = OUT_SUCCESS;
		} catch (Exception e) {
			log.error(UtileTools.ex2s(e));
			log.info("===UC 充值失败====\n" + strUri);
		}
		return r;
	}

	@SuppressWarnings("rawtypes")
	public static Map<String, String> verify(Map<String, String> kvmap) {
		Map<String, String> map = new HashMap<String, String>();

		String r = OUT_FAILURE;
		map.put(OUT_VERIFY, r);
		String data = MapEx.getString(kvmap, "data");
		if ("".equals(data))
			return map;

		Map m = UtileTools.strToMap(data);
		String orderStatus = MapEx.getString(m, "orderStatus");
		if (!TRADE_SUCCESS.equals(orderStatus))
			return map;

		StringBuffer build = new StringBuffer();
		build.append(AppId);
		for (String key : keyes_data) {
			String v = MapEx.getString(m, key);
			map.put(key, v);
			build.append(key).append("=").append(v);
		}
		build.append(AppKey);
		String str = build.toString();
		String sign = getMD5Str(str);
		String signature = MapEx.getString(kvmap, "sign");
		if (sign.equalsIgnoreCase(signature)) {
			r = OUT_SUCCESS;
		}
		map.put(OUT_VERIFY, r);
		return map;
	}

	/**
	 * MD5 加密
	 */
	public static String getMD5Str(String str) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");

			messageDigest.reset();

			messageDigest.update(str.getBytes("UTF-8"));
		} catch (Exception e) {
			log.error(UtileTools.ex2s(e));
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}

		return md5StrBuff.toString();
	}
}
