package com.sea.channel.nd91;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.util.MapEx;
import com.sea.cache.jedis.DCfgJedis;
import com.sea.channel.ChnPayVerify;
import com.sea.handler.gate.GateConfig;
import com.sea.handler.response.ResponseWeb;
import com.sea.tools.UtileTools;

public class PayNd91 {

	static Log log = LogFactory.getLog(PayNd91.class);

	final static String Pay_Status = "1";
	final static String Pay_Act = "1";

	// android
	final static String AppId_And = "113522";
	final static String AppKey_And = "1442b88fdc03a1e1207c49cd3e7bf6b692badc741a861ffe";

	// ios
	final static String AppId_Ios = "113513";
	final static String AppKey_Ios = "b83b0d1fd3c807a60a5ca41aed38448990c7ac80ba0f9f35";

	// 返回的code
	public static int Code_Success = 1;// 成功
	static int Code_False = 0;// 失败

	// 充值服务地址
	private static int svcid = 1000;

	// OriginalMoney原始总价(格式：0.00),OrderMoney实际总价(格式：0.00)
	public static String[] keyes = { "AppId", "Act", "ProductName",
			"ConsumeStreamId", "CooOrderSerial", "Uin", "GoodsId", "GoodsInfo",
			"GoodsCount", "OriginalMoney", "OrderMoney", "Note", "PayStatus",
			"CreateTime", "Sign" };

	private static String[] keyes_verify = { "AppId", "Act", "ProductName",
			"ConsumeStreamId", "CooOrderSerial", "Uin", "GoodsId", "GoodsInfo",
			"GoodsCount", "OriginalMoney", "OrderMoney", "Note", "PayStatus",
			"CreateTime", "AppKey" };

	public static String checkPay(String strUri, boolean isAnd) {
		String r = "";
		HashMap<String, String> parmets = (HashMap<String, String>) ResponseWeb
				.getMapByGetKeys(strUri, keyes);

		if (isAnd) {
			parmets.put("AppKey", AppKey_And);
		} else {
			parmets.put("AppKey", AppKey_Ios);
		}

		int code = verify(parmets, isAnd);
		String msg = "充值失败";
		if (code == Code_Success) {
			String billNo = MapEx.getString(parmets, "CooOrderSerial");
			String headBNo = DCfgJedis.getPayUUIDHead();
			int indexHead = billNo.indexOf(headBNo);
			if (indexHead > 0) {
				billNo = billNo.substring(indexHead);
			}
			String chnBillNo = billNo;
			double payFee = MapEx.getDouble(parmets, "OrderMoney");// 价格单位:元
			double money = (payFee);
			ChnPayVerify.issuePayToGameByFormula(GateConfig.TGG_Payment_Nd91,
					svcid, billNo, money, chnBillNo, strUri);
			msg = "充值成功";
		}
		r = getVerifyBack(code, msg);
		return r;
	}

	private static String getVerifyBack(int code, String msg) {
		String r = "{\"ErrorCode\":\"" + code + "\",\"ErrorDesc\":\"" + msg
				+ "\"}";
		return r;
	}

	private static int verify(HashMap<String, String> parmets, boolean isAnd) {
		int r = Code_False;
		String str = "";
		try {
			String result = MapEx.getString(parmets, "PayStatus");
			if (result == null || !Pay_Status.equals(result.trim()))
				return r;

			String Act = MapEx.getString(parmets, "Act");
			if (Act == null || !Pay_Act.equals(Act.trim()))
				return r;

			String strAppId = MapEx.getString(parmets, "AppId");
			String vAppId = AppId_Ios;
			if (isAnd) {
				vAppId = AppId_And;
			}
			if (strAppId == null || !vAppId.equals(strAppId.trim()))
				return r;

			StringBuffer build = new StringBuffer();
			for (String key : keyes_verify) {
				String val = MapEx.getString(parmets, key);
				if ("OriginalMoney".equals(key) || "OrderMoney".equals(key)) {
					val = fillData(val);
				}
				build.append(val);
			}
			str = build.toString();

			String sign = HexBin.md5(str);
			String signature = MapEx.getString(parmets, "Sign");
			if (sign.equalsIgnoreCase(signature)) {
				r = Code_Success;
			}
		} catch (Exception e) {
			String strInfo = str + "\n==error==\n" + UtileTools.ex2s(e);
			log.error(strInfo);
		}
		return r;
	}

	private static String fillData(String val) {
		String r = "";
		int index_d = val.lastIndexOf(".");
		if (index_d == -1) {
			r = val + ".00";
		} else {
			String strBeg = val.substring(0, index_d);
			String strEnd = val.substring(index_d + 1);
			int lenEnd = strEnd.length();
			if (lenEnd == 0) {
				strEnd = "00";
			} else if (lenEnd == 1) {
				strEnd = "0" + strEnd;
			} else if (lenEnd > 2) {
				strEnd = strEnd.substring(0, 2);
			}
			r = strBeg + "." + strEnd;
		}
		return r;
	}
}
