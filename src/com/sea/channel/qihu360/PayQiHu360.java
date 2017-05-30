package com.sea.channel.qihu360;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.util.MapEx;
import com.sea.channel.ChnPayVerify;
import com.sea.handler.gate.GateConfig;
import com.sea.handler.response.ResponseWeb;
import com.sea.tools.UtileTools;

public class PayQiHu360 {

	static Log log = LogFactory.getLog(PayQiHu360.class);

	public static final int appId = 201384941;
	public static final String appKey = "286bda794c83b31348fb08b533c20689";
	public static final String appSecret = "c8fde41175eb42b1bc5b10bb3cb1dd2a";

	public static String keyes[] = { "app_key", "product_id", "app_uid",
			"order_id", "sign_type", "gateway_flag", "sign", "sign_return",
			"amount", "app_order_id", "app_ext1", "user_id" };

	public static String[] removeKeyes = {};

	// 充值服务地址
	private static int svcid = 1000;

	// 充值验证
	public static String checkPay(String strUri) {
		String r = "";
		HashMap<String, String> parmets = (HashMap<String, String>) ResponseWeb
				.getMapByGetKeys(strUri, keyes);

		String billNo = MapEx.getString(parmets, "app_order_id");
		r = VerifyPayCallQH360.getInstance().processRequest(parmets);
		if (VerifyPayCallQH360.Code_Success.equals(r)) {
			String chnBillNo = MapEx.getString(parmets, "order_id");
			int amount = MapEx.getInt(parmets, "amount");// 价格单位:分
			double money = amount / 100;
			ChnPayVerify.issuePayToGame(GateConfig.TGG_Payment_QH360, svcid,
					billNo, money, chnBillNo, strUri);
		}
		return r;
	}

	// 取得用户信息
	public static Map<String, String> getQH360UIDAndTokenByCode(String strCode) {
		Map<String, String> r = new HashMap<String, String>();
		try {
			OAuth360 oa = OAuth360.getInstance();

			HashMap<String, Object> tokenMap = oa.getAccessTokenByCode(strCode,
					null);
			Object objToken = tokenMap.get(OAuth360.Key_Access_Token);
			String strToken = objToken.toString();
			HashMap<String, Object> userMap = oa.getUserInfoByToken(strToken);
			Object objUserId = userMap.get(OAuth360.Key_User_ID);
			String strUserID = objUserId.toString();
			r.put(OAuth360.Key_Token, strToken);
			r.put(OAuth360.Key_User_ID, strUserID);
		} catch (Exception e) {
			log.info(UtileTools.ex2s(e));
		}
		return r;
	}
}