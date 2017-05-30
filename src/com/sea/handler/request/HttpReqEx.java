package com.sea.handler.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bowlong.bio2.B2InputStream;
import com.bowlong.lang.StrEx;
import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;

/***
 * 请求别人，并取得别人的相应信息类 [向其他地址发送请求，并取得对方响应信息]
 ****/
@SuppressWarnings({ "rawtypes" })
public class HttpReqEx {

	// 此处的res 是 response 的简称.
	// 此处的req 是 request 的简称.
	// ByPost 表示post 请求
	// ByGet 表示 get 请求

	static Log log = LogFactory.getLog(HttpReqEx.class);

	static public InputStream execute(HttpUriRequest req) {
		HttpClient client = null;
		try {
			client = new DefaultHttpClient();
			client.getParams().setParameter(
					ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
			HttpResponse res = client.execute(req);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity en = res.getEntity();
				return en.getContent();
			}
		} catch (Exception e) {
			log.error(ExceptionEx.e2s(e));
		} finally {
			if (client != null) {
				client.getConnectionManager().shutdown();
			}
		}
		return null;
	}

	// ================= get 请求

	static String UseAgent1 = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; QQDownload 1.7; .NET CLR 1.1.4322; CIBA; .NET CLR 2.0.50727)";
	static String UseAgent2 = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)";

	/**
	 * 参数编码
	 * 
	 * @param data
	 * @return
	 */
	static public String httpBuildQuery(Map<String, String> data) {
		String ret = "";
		String k, v;
		Iterator<String> iterator = data.keySet().iterator();
		while (iterator.hasNext()) {
			k = iterator.next();
			v = data.get(k);
			try {
				ret += URLEncoder.encode(k, "utf8") + "="
						+ URLEncoder.encode(v, "utf8");
			} catch (UnsupportedEncodingException e) {
			}
			ret += "&";
		}
		return ret.substring(0, ret.length() - 1);
	}

	static public String httpBuildJson(Object data) {
		try {
			return JSON.toJSONString(data);
		} catch (Exception e) {
		}
		return "";
	}

	static public String httpBuildJson2(Map data) {
		try {
			if (MapEx.isEmpty(data))
				return "";
			JSONObject json = new JSONObject();
			List list = ListEx.keyToList(data);
			for (Object objKey : list) {
				String val = data.get(objKey).toString();
				String key = objKey.toString();
				json.put(key, val);
			}
			return json.toJSONString();
		} catch (Exception e) {
		}
		return "";
	}

	static public String inps2Str(InputStream ins, String chaUTF) {
		if (ins == null)
			return "";
		try {
			StringBuffer buff = new StringBuffer();
			BufferedReader br = null;
			if (StrEx.isEmpty(chaUTF)) {
				br = new BufferedReader(new InputStreamReader(ins));
			} else {
				br = new BufferedReader(new InputStreamReader(ins, chaUTF));
			}
			String readLine = null;
			while ((readLine = br.readLine()) != null) {
				buff.append(readLine);
			}
			ins.close();
			br.close();
			return buff.toString();
		} catch (Exception e) {
			return ExceptionEx.e2s(e);
		}
	}

	static public Object inps2Obj(InputStream ins) {
		if (ins == null)
			return null;
		try {
			return B2InputStream.readObject(ins);
		} catch (Exception e) {
		}
		return null;
	}

	static public Map inps2Map(InputStream ins) {
		if (ins == null)
			return new HashMap();
		try {
			Object obj = inps2Obj(ins);
			if (obj == null) {
				return new HashMap();
			}
			return (Map) obj;
		} catch (Exception e) {
		}
		return new HashMap();
	}

	static public Map inps2Map2(InputStream ins) {
		if (ins == null)
			return new HashMap();
		try {
			return B2InputStream.readMap(ins);
		} catch (Exception e) {
		}
		return new HashMap();
	}

	static public InputStream sendGetInpsByHC(String host, String chaUTF,
			Map<String, String> parames) {
		try {
			if (chaUTF == null || "".equals(chaUTF.trim()))
				chaUTF = "UTF-8";
			if (host == null || "".equals(host.trim()))
				return null;

			URL url = new URL(host);
			HttpGet get = new HttpGet(url.toURI());
			HttpParams params = new BasicHttpParams();
			// int len = 0;
			for (Entry<String, String> parame : parames.entrySet()) {
				String key = URLEncoder.encode(parame.getKey(), chaUTF);
				String value = URLEncoder.encode(parame.getValue(), chaUTF);
				params.setParameter(key, value);
				// len = parames.size();
			}
			get.setParams(params);
			get.setHeader("Accept", "*/*");
			get.setHeader("Connection", "Keep-Alive");
			// get.setHeader("Content-Length", String.valueOf(len));
			get.setHeader("User-Agent", UseAgent2);
			return execute(get);
		} catch (Exception e) {
			log.error(ExceptionEx.e2s(e));
		}
		return null;
	}

	static public String sendGetStrByHC(String host, String chaUTF,
			Map<String, String> parames) {
		InputStream inp = sendGetInpsByHC(host, chaUTF, parames);
		return inps2Str(inp, chaUTF);
	}

	// ================= post 请求

	static public InputStream sendPostInpsByHC(String host, String chaUTF,
			Map<String, String> parames) {
		try {
			if (chaUTF == null || "".equals(chaUTF.trim()))
				chaUTF = "UTF-8";
			if (host == null || "".equals(host.trim()))
				return null;
			URL url = new URL(host);
			HttpPost post = new HttpPost(url.toURI());
			// int len = 0;
			if (parames != null && parames.size() > 0) {
				List<NameValuePair> nvps = new ArrayList<NameValuePair>();
				for (Entry<String, String> parame : parames.entrySet()) {
					String name = parame.getKey();
					String value = parame.getValue();
					NameValuePair nvParame = new BasicNameValuePair(name, value);
					nvps.add(nvParame);
				}
				post.setEntity(new UrlEncodedFormEntity(nvps, chaUTF));
				// byte[] b = B2Helper.toBytes(parames);
				// len = b.length;
			}
			post.setHeader("Accept", "*/*");
			post.setHeader("Connection", "Keep-Alive");
			// post.setHeader("Content-Length", String.valueOf(len));
			post.setHeader("User-Agent", UseAgent2);
			return execute(post);
		} catch (Exception e) {
			log.error(ExceptionEx.e2s(e));
		}
		return null;
	}

	static public String sendPostStrByHC(String host, String chaUTF,
			Map<String, String> parames) {
		InputStream inp = sendPostInpsByHC(host, chaUTF, parames);
		return inps2Str(inp, chaUTF);
	}

	static public InputStream sendPostJsonByHC(String host, String chaUTF,
			Map<String, String> parames) {
		try {
			if (chaUTF == null || "".equals(chaUTF.trim()))
				chaUTF = "UTF-8";
			if (host == null || "".equals(host.trim()))
				return null;

			URL url = new URL(host);
			HttpPost post = new HttpPost(url.toURI());
			String strJson = httpBuildJson2(parames);
			if (StrEx.isEmpty(strJson)) {
				HttpEntity httpEn = new StringEntity(strJson, chaUTF);
				post.setEntity(httpEn);
			}

			return execute(post);
		} catch (Exception e) {
			log.error(ExceptionEx.e2s(e));
		}
		return null;
	}

	static public String sendPostJsonStrByHC(String host, String chaUTF,
			Map<String, String> parames) {
		InputStream inp = sendPostJsonByHC(host, chaUTF, parames);
		return inps2Str(inp, chaUTF);
	}

	static public InputStream sendGetInps(String url, String chaUTF,
			Map<String, String> data) {
		return sendGetInps(url, chaUTF, httpBuildQuery(data));
	}

	static public InputStream sendGetInps(String url, String chaUTF,
			String param) {
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", UseAgent2);
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			return connection.getInputStream();
		} catch (Exception e) {
			log.error(ExceptionEx.e2s(e));
		}
		return null;
	}

	static public Map sendGetMap(String url, String chaUTF,
			Map<String, String> data) {
		InputStream inps = null;
		try {
			inps = sendGetInps(url, chaUTF, data);
			return inps2Map(inps);
		} catch (Exception e) {
			return new HashMap();
		} finally {
			if (inps != null) {
				try {
					inps.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	static public String sendGetStr(String url, String chaUTF,
			Map<String, String> data) {
		return sendGetStr(url, chaUTF, httpBuildQuery(data));
	}

	static public String sendGetStr(String url, String chaUTF, String param) {
		InputStream inp = sendGetInps(url, chaUTF, param);
		return inps2Str(inp, chaUTF);
	}

	static public InputStream sendPostInps(String url, String chaUTF,
			Map<String, String> data) throws Exception {
		return sendPostInps(url, chaUTF, httpBuildQuery(data));
	}

	static public InputStream sendPostInps(String url, String chaUTF,
			String data) throws Exception {

		HttpURLConnection conn;
		try {
			// if GET....
			// URL requestUrl = new URL(url + "?" + httpBuildQuery(data));
			URL requestUrl = new URL(url);
			conn = (HttpURLConnection) requestUrl.openConnection();
		} catch (Exception e) {
			throw e;
		}

		conn.setRequestProperty("accept", "*/*");
		conn.setRequestProperty("connection", "Keep-Alive");
		conn.setRequestProperty("user-agent", UseAgent2);

		conn.setDoInput(true);
		conn.setDoOutput(true);

		PrintWriter writer = new PrintWriter(conn.getOutputStream());
		writer.print(data);
		writer.flush();
		writer.close();
		try {
			return conn.getInputStream();
		} catch (IOException e) {
			return conn.getErrorStream();
		}
	}

	static public Map sendPostMap(String url, String chaUTF,
			Map<String, String> data) throws Exception {
		InputStream inps = null;
		try {
			inps = sendPostInps(url, chaUTF, data);
			return inps2Map(inps);
		} catch (Exception e) {
			return new HashMap();
		} finally {
			if (inps != null) {
				inps.close();
			}
		}
	}

	static public String sendPostStr(String url, String chaUTF, String data)
			throws Exception {
		InputStream inps = sendPostInps(url, chaUTF, data);
		return inps2Str(inps, chaUTF);
	}

	static public String sendPostStr(String url, String chaUTF,
			Map<String, String> data) throws Exception {
		return sendPostStr(url, chaUTF, httpBuildJson(data));
	}

	static public String sendPostJson(String url, Map<String, String> data)
			throws Exception {
		return sendPostStr(url, "UTF-8", httpBuildJson(data));
	}
}
