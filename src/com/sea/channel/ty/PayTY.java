package com.sea.channel.ty;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.handler.codec.http.HttpRequest;

import com.bowlong.util.DateEx;
import com.bowlong.util.MapEx;
import com.sea.channel.ChnPayVerify;
import com.sea.channel.mumayi.MD5Util;
import com.sea.handler.gate.GateConfig;
import com.sea.handler.response.ResponseWeb;

/**
 * 爱游戏
 * 
 * @author Administrator
 * 
 */
public class PayTY {

	static Log log = LogFactory.getLog(PayTY.class);

	static final String key_result = "resultCode";
	static final String key_cpparam = "cpparam";
	static final String key_msg = "resultMsg";
	static final String key_cost = "cost";// 单位元
	static final String key_payType = "payType";
	static final String key_md5 = "validatecode";// MD5(resultCode + cpparam)
	static final String key_time = "requestTime";// yyyy-MM-dd HH:mm:ss

	static final String success = "00";// 成功

	static final int svcid = 3000;

	static final long Diff_Time = DateEx.TIME_MINUTE * 20;

	static public String checkPay(HttpRequest request) {
		// Map<String, String> bodyMap = ResponseWeb
		// .getMapQueryByPostBody(request);
		// String strUri = ResponseWeb.getStrQueryByMap(bodyMap);

		String strUri = request.getUri();
		Map<String, String> bodyMap = ResponseWeb.getMapByGet(strUri);
		String cpparam = MapEx.getString(bodyMap, key_cpparam).trim();
		String code = MapEx.getString(bodyMap, key_result).trim();
		String md5 = MapEx.getString(bodyMap, key_md5);
		String md5v = MD5(code, cpparam);

		boolean isMustInfo = true;
		if (!"".equals(cpparam)) {
			if (success.equals(code)) {
				if (md5v.equalsIgnoreCase(md5)) {
					int cost = MapEx.getInt(bodyMap, key_cost);
					isMustInfo = !ChnPayVerify.issuePayToGame(
							GateConfig.TGG_Payment_Ty, svcid, cpparam, cost,
							cpparam, strUri);
				}
			}
		}
		if (isMustInfo) {
			log.info("爱游戏充值回掉：" + strUri);
		}
		return cpparam;
	}

	static public String MD5(String code, String cpparam) {
		return MD5Util.toMD5(code + cpparam);
	}
}
