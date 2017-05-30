package com.sea.channel.xiaomi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.util.MapEx;
import com.sea.channel.ChnPayVerify;
import com.sea.handler.gate.GateConfig;
import com.sea.handler.response.ResponseWeb;
import com.sea.tools.UtileTools;

public class PayXiaoMi {

	static Log log = LogFactory.getLog(PayXiaoMi.class);

	static String TRADE_SUCCESS = "TRADE_SUCCESS";
	static String AppKey = "d247bef4-f41b-4a52-7c68-53478f27e5e0";

	// 返回给小米的code
	public static int Code_Success = 200;// 成功
	static int Code_False_CPOrder = 1506;
	static int Code_False_APPID = 1515;
	static int Code_False_UID = 1516;
	static int Code_False_Sign = 1525;// 签名错误
	static int Code_False_Tig = 3515;// 订单信息不一致

	// 充值服务地址
	private static int svcid = 1000;

	// payFee支付金额，单位为分
	// orderConsumeType订单类型： 10：普通订单 11：直充直消订单
	private static String[] keyes = { "appId", "cpOrderId", "cpUserInfo",
			"uid", "orderId", "orderStatus", "payFee", "productCode",
			"productName", "productCount", "payTime", "orderConsumeType",
			"signature" };

	private static String[] keyes_verify = { "appId", "cpOrderId",
			"cpUserInfo", "uid", "orderId", "orderStatus", "payFee",
			"productCode", "productName", "productCount", "payTime",
			"orderConsumeType" };

	public static String checkPay(String strUri) {
		String r = "";
		HashMap<String, String> parmets = (HashMap<String, String>) ResponseWeb
				.getMapByGetKeys(strUri, keyes);
		int code = verify(parmets);
		String msg = "充值失败";
		if (code == Code_Success) {
			String billNo = MapEx.getString(parmets, "cpOrderId");
			String chnBillNo = MapEx.getString(parmets, "orderId");
			double payFee = MapEx.getDouble(parmets, "payFee");// 价格单位:分
			double money = (payFee / 100);
			ChnPayVerify.issuePayToGame(GateConfig.TGG_Payment_Mi, svcid,
					billNo, money, chnBillNo, strUri);
			msg = "充值成功";
		}
		r = getVerifyBack(code, msg);
		return r;
	}

	private static String getVerifyBack(int code, String msg) {
		String r = "{\"errcode\":\"" + code + "\",\"errMsg\":\"" + msg + "\"}";
		return r;
	}

	private static int verify(HashMap<String, String> parmets) {
		int r = Code_False_Tig;
		String str = "";
		try {
			String result = MapEx.getString(parmets, "orderStatus");
			if (result == null || !TRADE_SUCCESS.equals(result.trim()))
				return r;
			List<String> verifyList = new ArrayList<String>();
			for (String key : keyes_verify) {
				verifyList.add(key);
			}
			Collections.sort(verifyList);
			StringBuffer build = new StringBuffer();
			for (String key : verifyList) {
				String val = MapEx.getString(parmets, key);
				if ("orderConsumeType".equals(key)) {
					if (!"10".equals(val) || !"11".equals(val))
						continue;
				}
				// val = URLEncoder.encode(val, "UTF-8");
				build.append(key).append("=").append(val).append("&");
			}
			str = build.toString();
			int len = str.length();
			if (len > 2) {
				str = str.substring(0, len - 1);
			}
			String sign = HmacSHA1Encryption.HmacSHA1Encrypt(str, AppKey);
			String signature = MapEx.getString(parmets, "signature");
			if (sign.equalsIgnoreCase(signature)) {
				r = Code_Success;
			}
		} catch (Exception e) {
			String strInfo = str + "\n==error==\n" + UtileTools.ex2s(e);
			log.error(strInfo);
		}
		return r;
	}
}
