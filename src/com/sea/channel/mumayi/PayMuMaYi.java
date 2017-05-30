package com.sea.channel.mumayi;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.handler.codec.http.HttpRequest;

import com.bowlong.util.MapEx;
import com.sea.channel.ChnPayVerify;
import com.sea.handler.gate.GateConfig;
import com.sea.handler.response.ResponseWeb;
import com.sea.tools.UtileTools;

public class PayMuMaYi {

	static Log log = LogFactory.getLog(PayMuMaYi.class);

	static final String AppKey = "3927edf7c55947a20H6K0ixRVk3uMvsBZwV6CmqNqg4MNuMvbc4m7lAFgHv2Zt8";

	public static String[] keyes = { "orderID", "productDesc", "tradeSign",
			"tradeState" };

	// 充值服务地址
	private static int svcid = 1000;

	private static boolean verify(String sign, String appKey, String orderId)
			throws Exception {
		if (sign.length() < 14) {
			return false;
		}
		String verityStr = sign.substring(0, 8);
		sign = sign.substring(8);
		String temp = MD5Util.toMD5(sign);
		if (!verityStr.equals(temp.substring(0, 8))) {
			return false;
		}
		String keyB = sign.substring(0, 6);

		String randKey = keyB + appKey;

		randKey = MD5Util.toMD5(randKey);

		byte[] signB = Base64.decodeFast(sign.substring(6));
		int signLength = signB.length;
		String verfic = "";
		for (int i = 0; i < signLength; i++) {
			char b = (char) (signB[i] ^ randKey.getBytes()[i % 32]);
			verfic += String.valueOf(b);
		}
		return verfic.equals(orderId);
	}

	private static boolean verify(String sign, String orderId) {
		boolean r = false;
		try {
			r = verify(sign, AppKey, orderId);
		} catch (Exception e) {
			log.error(UtileTools.ex2s(e));
		}
		return r;
	}

	public static boolean checkPay(HttpRequest request) {
		String keyOrderId = "orderID";
		String keyBillNo = "productDesc";
		String keySign = "tradeSign";
		String keyTradeState = "tradeState";
		String strStatus = "";
		String sign = "";
		String chnBillNo = "";
		String billno = "";
		String quereStr = "";
		try {
			Map<String, String> map = ResponseWeb
					.getMapKVByPost(request, keyes);
			strStatus = MapEx.getString(map, keyTradeState);
			if (strStatus != null && "success".equals(strStatus.trim())) {
				log.info("========= 木蚂蚁支付成功 ===========");
				sign = MapEx.getString(map, keySign);
				chnBillNo = MapEx.getString(map, keyOrderId);
				billno = MapEx.getString(map, keyBillNo);
				boolean r = verify(sign, chnBillNo);
				if (r) {
					quereStr = "chnBNo=" + chnBillNo + "&billNo=" + billno
							+ "&sign=" + sign;
					boolean issue = ChnPayVerify.issuePayToGame(
							GateConfig.TGG_Payment_MMY, svcid, billno, 0,
							chnBillNo, quereStr);
					return issue;
				}
			}
		} catch (Exception e) {
			log.error(UtileTools.ex2s(e));
		}
		log.info("========= 木蚂蚁支付失败  ===========");
		log.info("===木蚂蚁 status=" + strStatus + ",chbl=" + chnBillNo
				+ ",billNo=" + billno + ",sigin=" + sign);
		return false;
	}
}
