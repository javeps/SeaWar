package com.sea.channel.qihu360;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.alibaba.druid.support.json.JSONParser;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class VerifyPayCallQH360 {
	private VerifyPayCallQH360(){}
	private static VerifyPayCallQH360 _self;
	public static VerifyPayCallQH360 getInstance(){
		if(_self == null){
			_self = new VerifyPayCallQH360();
		}
		return _self;
	}
	
	private static final String VERIFY_URL = "http://msdk.mobilem.360.cn/pay/order_verify.json";
	private static final String VERIFIED = "verified";
	public static final String Code_Success = "ok";
	public static final String Code_Fail = "verify failed";
	public static final String Code_Invalid_Request = "invalid request";
	
	
	private String _errorMsg = "";
	
	/**
	 * 处理从360过来的支付订单通知请求
	 * @param params
	 * @return 
	 */
	public String processRequest(HashMap<String, String> params) {
		if (!this._isValidRequest(params)) {
			if(!this._errorMsg.isEmpty())
			{
				return this._errorMsg;
			}
			return Code_Invalid_Request;
		}

		if (!this._verifyOrder(params)) {
			if(!this._errorMsg.isEmpty())
			{
				return this._errorMsg;
			}
			return Code_Fail;
		}
		return Code_Success;
	}

	/**
	 * 向360服务器发起请求验证订单是否有效
	 * @param params
	 * @return Boolean 是否有效
	 */
	private Boolean _verifyOrder(HashMap<String, String> params) {
		String url = VERIFY_URL;
		HashMap<String, String> requestParams = new HashMap();

		String field;
		Iterator<String> iterator = params.keySet().iterator();
		while (iterator.hasNext()) {
			field = iterator.next();
			if (field.equals("gateway_flag") 
                    || field.equals("sign")
                    || field.contains("sign_return")
                ) 
            {
				continue;
			}
			requestParams.put(field, params.get(field));
		}
        requestParams.put("sign", Util.getSign(requestParams,PayQiHu360.appSecret));

		String ret;
		try {
			ret = Util.requestUrl(url, requestParams);
		} catch (IOException e) {
			this._errorMsg = e.toString();
			return false;
		} catch (Exception e1) {
			this._errorMsg = e1.toString();
			return false;
		}

		JSONParser jsonParser = new JSONParser(ret);
		Map<String, Object> obj;
		try {
			obj = (Map<String, Object>) jsonParser.parseMap();
			
			Boolean verified =  obj.get("ret").equals(VERIFIED);
			if(!verified)
			{
				this._errorMsg = obj.get("ret").toString();
			}
			return verified;
		} catch (Exception e) {
			this._errorMsg = e.toString();
			return false;
		}
	}

	/**
	 * 检查request完整性
	 * @param params
	 * @return Boolean
	 */
	private Boolean _isValidRequest(HashMap params) {
		String arrFields[] = PayQiHu360.keyes;
		ArrayList fields = new ArrayList(Arrays.asList(arrFields));

		String key;
		String value;
		Iterator iterator = fields.iterator();
		while (iterator.hasNext()) {
			key = (String) iterator.next();
			value = (String) params.get(key);
			if (value == null || value.equals("")) {
				return false;
			}
		}
		for (String reKey : PayQiHu360.removeKeyes) {
			boolean isCon = params.containsKey(reKey);
			if(isCon)
				params.remove(reKey);
		}
		if(!params.get("app_key").equals(PayQiHu360.appKey)){
			this._errorMsg = "not my order";
			return false;
		}

		String sign = Util.getSign(params,PayQiHu360.appSecret);
		String paramSign = (String) params.get("sign");
		return sign.equals(paramSign);
	}
}
