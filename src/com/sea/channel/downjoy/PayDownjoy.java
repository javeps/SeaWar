package com.sea.channel.downjoy;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.util.MapEx;
import com.sea.channel.ChnPayVerify;
import com.sea.handler.gate.GateConfig;
import com.sea.handler.response.ResponseWeb;

public class PayDownjoy {

	static Log log = LogFactory.getLog(PayDownjoy.class);

	public static final String Code_Success = "success";
	private static final String Code_Fail = "fail";

	public static final String PAYMENT_KEY = "uyWvoSMTvkco";

	public static final String pay_success = "1";

	public static String keyes[] = { "result", "money", "order", "mid", "time",
			"signature", "ext" };

	public static String keyes_verify[] = { "order", "money", "mid", "time",
			"result", "ext", "key" };
	
	//充值服务地址
	private static int svcid = 1000;
	// 充值验证
	public static String checkPay(String strUri) {
		String r = Code_Fail;
		

		HashMap<String, String> parmets = (HashMap<String, String>) ResponseWeb
				.getMapByGetKeys(strUri, keyes);
		parmets.put("key", PAYMENT_KEY);

		boolean isOk = verify(parmets);
		if (isOk) {
			String billNo = MapEx.getString(parmets, "ext");
			String chnBillNo = MapEx.getString(parmets, "order");
			int money = MapEx.getInt(parmets, "money");// 价格单位:元
			ChnPayVerify.issuePayToGame(GateConfig.TGG_Payment_Downjoy, svcid,
					billNo, money, chnBillNo, strUri);
			r = Code_Success;
		}
		return r;
	}

	private static boolean verify(HashMap<String, String> parmets) {
		boolean r = false;
		String result = MapEx.getString(parmets, "result");
		if (result == null || !pay_success.equals(result.trim()))
			return r;
		String str = "";
		StringBuffer build = new StringBuffer();
		for (String key : keyes_verify) {
			String val = MapEx.getString(parmets, key);
			build.append(key).append("=").append(val).append("&");
		}
		str = build.toString();
		int len = str.length();
		if (len > 2) {
			str = str.substring(0, len - 1);
		}
		String sign = MD5.MD5Encode(str).toLowerCase();
		String signature = MapEx.getString(parmets, "signature");
		if (sign.equalsIgnoreCase(signature)) {
			r = true;
		}
		return r;
	}
}