package com.sea.channel.qihu360;

import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.ParseException;

import com.alibaba.druid.support.json.JSONParser;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class OAuth360 {

	static Log log = LogFactory.getLog(OAuth360.class);

	public static final String HOST = "https://openapi.360.cn";
	private static final String HOST_TOKEN = HOST + "/oauth2/access_token";
	private static final String HOST_AutoCode = HOST + "/oauth2/authorize";
	
	private static final String SCOPE_BASIC = "basic";
	private static final String REDIRECT_URL = "oob";
	
	public static final String Key_Token = "token";
	public static final String Key_Access_Token = "access_token";
	public static final String Key_User = "user";
	public static final String Key_User_ID = "id";	

	private static OAuth360 _self;

	private OAuth360() {
	}

	private String _scope = "";

	public void setScope(String val) {
		this._scope = val;
	}

	public String getScope() {
		return this._scope;
	}

	public static OAuth360 getInstance() {
		if (_self == null) {
			_self = new OAuth360();
			_self.setScope(SCOPE_BASIC);
		}

		return _self;
	}

	/**
	 * 通过code获得用户信息和token信息(包括access_token, refresh_token)
	 * 
	 * @param code
	 * @return
	 * @throws QException
	 */
	public HashMap<String, HashMap<String, Object>> getInfoByCode(String code)
			throws QException {
		if (code == null) {
			throw new QException(QException.CODE_BAD_PARAM, "需要传code");
		}
		HashMap<String, Object> token = this.getAccessTokenByCode(code, null);
		HashMap<String, Object> user = this.getUserInfoByToken(token.get(Key_Access_Token)
				.toString());

		HashMap<String, HashMap<String, Object>> ret = new HashMap();
		ret.put(Key_Token, token);
		ret.put(Key_User, user);
		return ret;
	}
	
	// 取得code
	public HashMap<String, Object> getQH360Code() throws Exception{
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("client_id", PayQiHu360.appKey);
		params.put("response_type", "code");
		params.put("redirect_uri", REDIRECT_URL);
		params.put("scope", SCOPE_BASIC);
		return this._request(HOST_AutoCode, params);
	}

	/**
	 * 通过code换取token(包括access token和 refresh token)
	 * 
	 * @param code
	 * @param redirectUri
	 * @return
	 * @throws QException
	 */
	public HashMap<String, Object> getAccessTokenByCode(String code,
			String redirectUri) throws QException {
		if (redirectUri == null || redirectUri.isEmpty()) {
			redirectUri = REDIRECT_URL;
		}

		if (code == null) {
			throw new QException(QException.CODE_BAD_PARAM, "需要传code");
		}

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("grant_type", "authorization_code");
		params.put("code", code);
		params.put("client_id", PayQiHu360.appKey);
		params.put("client_secret", PayQiHu360.appSecret);
		params.put("redirect_uri", redirectUri);
		params.put("scope", this.getScope());
		return this._request(HOST_TOKEN, params);
	}

	/**
	 * 通过access token
	 * 
	 * @param tokenStr
	 * @return
	 * @throws QException
	 */
	public HashMap<String, Object> getUserInfoByToken(String tokenStr) throws QException {
		if (tokenStr == null) {
			throw new QException(QException.CODE_BAD_PARAM, "需要传入token");
		}
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("access_token", tokenStr);
		return this._request(HOST + "/user/me.json", params);
	}

	/**
	 * 通过refreshToken刷新得到新的token信息(包括新的access_token和refresh_token)
	 * 
	 * @param refreshToken
	 * @return
	 * @throws QException
	 */
	public HashMap<String, Object> getAccessTokenByRefreshToken(
			String refreshToken) throws QException {
		if (refreshToken == null) {
			throw new QException(QException.CODE_BAD_PARAM, "需要传入refresh_token");
		}
		HashMap<String, String> data = new HashMap();
		data.put("grant_type", "refresh_token");
		data.put("refresh_token", refreshToken);
		data.put("client_id", PayQiHu360.appKey);
		data.put("client_secret", PayQiHu360.appSecret);
		data.put("scope", this.getScope());
		return this._request(HOST_TOKEN, data);
	}

	/**
	 * 查询token信息(app_key 和 360用户id)
	 * 
	 * @param tokenStr
	 *            access_token
	 * @return
	 * @throws QException
	 */
	public HashMap<String, Object> getTokenInfo(String tokenStr)
			throws QException {
		if (tokenStr == null) {
			throw new QException(QException.CODE_BAD_PARAM, "需要传入token");
		}
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("access_token", tokenStr);
		return this._request(HOST + "/oauth2/get_token_info.json", params);
	}
	
	/**
	 * 向360服务器发送请求
	 * 
	 * @param url
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @return HashMap 请求结果
	 * @throws QException
	 */
	private HashMap<String, Object> _request(String url,
			HashMap<String, String> params) throws QException {

		String fullUrl = url + "?" + Util.httpBuildQuery(params);
		log.info(fullUrl);
		String jsonStr;
		try {
			jsonStr = Util.requestUrl(url, params);
		} catch (IOException e) {
			throw new QException(QException.CODE_NET_ERROR, e.getMessage()
					+ "\r\n" + fullUrl);
		}

		if (jsonStr.isEmpty()) {
			throw new QException(QException.CODE_NET_ERROR, fullUrl);
		}

		JSONParser jsonParser = new JSONParser(jsonStr);
		HashMap<String, Object> obj;
		try {
			obj = (HashMap<String, Object>) jsonParser.parseMap();
		} catch (ParseException e) {
			throw new QException(QException.CODE_JSON_ERROR, jsonStr);
		} catch (Exception e1) {
			throw new QException(QException.CODE_JSON_ERROR, jsonStr);
		}

		log.info(obj.toString());
		String errorCode = (String) obj.get("error_code");
		if (errorCode != null && !errorCode.isEmpty()) {
			String err = (String) obj.get("error");
			throw new QException(errorCode, err);
		}
		return obj;
	}
}
