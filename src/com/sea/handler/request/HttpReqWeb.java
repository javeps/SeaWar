package com.sea.handler.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.apache.http.util.EntityUtils;

import com.bowlong.lang.NumEx;
import com.bowlong.lang.StrEx;
import com.bowlong.util.DateEx;

/***
 * 请求别人，并取得别人的相应信息类 [向其他地址发送请求，并取得对方响应信息]
 ****/
public class HttpReqWeb extends HttpReqEx {

	// 此处的res 是 response 的简称.
	// 此处的req 是 request 的简称.
	// ByPost 表示post 请求
	// ByGet 表示 get 请求

	static Log log = LogFactory.getLog(HttpReqWeb.class);

	private static String executeReq(HttpUriRequest req, String chaUTF) {
		String r = "";
		HttpClient client = null;
		try {
			client = new DefaultHttpClient();
			client.getParams().setParameter(
					ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
			HttpResponse res = client.execute(req);
			StringBuffer buildStr = new StringBuffer();
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity en = res.getEntity();
				InputStream ins = en.getContent();
				BufferedReader br = null;
				if (StrEx.isEmpty(chaUTF)) {
					br = new BufferedReader(new InputStreamReader(ins));
				} else {
					br = new BufferedReader(new InputStreamReader(ins, chaUTF));
				}
				String readLine = null;
				while ((readLine = br.readLine()) != null) {
					buildStr.append(readLine);
				}
				ins.close();
				br.close();
			}
			r = buildStr.toString();
		} catch (Exception e) {
			log.error(ExceptionEx.e2s(e));
		} finally {
			if (client != null) {
				client.getConnectionManager().shutdown();
			}
		}
		return r;
	}

	// ================= get 请求
	static public String sendGetStrByHC2(String host, String chaUTF,
			Map<String, String> parames) {
		String r = "";
		try {
			if (chaUTF == null || "".equals(chaUTF.trim()))
				chaUTF = "UTF-8";
			if (host == null || "".equals(host.trim()))
				return r;

			URL url = new URL(host);
			HttpGet get = new HttpGet(url.toURI());
			HttpParams params = new BasicHttpParams();
			// int len = 0;
			for (Entry<String, String> parame : parames.entrySet()) {
				String key = parame.getKey();
				String value = parame.getValue();
				params.setParameter(key, value);
				// len = parames.size();
			}
			get.setParams(params);
			get.setHeader("Accept", "*/*");
			get.setHeader("Connection", "Keep-Alive");
			// get.setHeader("Content-Length", String.valueOf(len));
			get.setHeader("User-Agent", UseAgent2);
			r = executeReq(get, chaUTF);
		} catch (Exception e) {
			log.error(ExceptionEx.e2s(e));
		}
		return r;
	}

	// ================= post 请求

	static public String sendPostStrByHC2(String host, String chaUTF,
			Map<String, String> parames) {
		String r = "";
		try {
			if (chaUTF == null || "".equals(chaUTF.trim()))
				chaUTF = "UTF-8";
			if (host == null || "".equals(host.trim()))
				return r;
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
			r = executeReq(post, chaUTF);
		} catch (Exception e) {
			log.error(ExceptionEx.e2s(e));
		}
		return r;
	}

	static public String sendPostJsonByHC2(String host, String chaUTF,
			Map<String, String> parames) {
		String r = "";
		try {
			if (chaUTF == null || "".equals(chaUTF.trim()))
				chaUTF = "UTF-8";
			if (host == null || "".equals(host.trim()))
				return r;

			URL url = new URL(host);
			HttpPost post = new HttpPost(url.toURI());
			String strJson = httpBuildJson2(parames);
			if (StrEx.isEmpty(strJson)) {
				HttpEntity httpEn = new StringEntity(strJson, chaUTF);
				post.setEntity(httpEn);
			}

			r = executeReq(post, chaUTF);
		} catch (Exception e) {
			log.error(ExceptionEx.e2s(e));
		}
		return r;
	}

	// 可以用
	// org.apache.commons.httpclient.methods.PostMethod;

	static public void getResByPost2(String host, String chaUTF,
			Map<String, String> parames) {

		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpPost httpPost = new HttpPost(host);

			if (parames != null && parames.size() > 0) {
				HttpParams params = new BasicHttpParams();
				for (Entry<String, String> parame : parames.entrySet()) {
					String key = parame.getKey();
					String value = parame.getValue();
					params.setParameter(key, value);
				}
				httpPost.setParams(params);
			}

			HttpResponse response = httpclient.execute(httpPost);
			HttpEntity responseEntity = response.getEntity();

			System.out.println("----------------------------------------");
			System.out.println(response.getStatusLine());
			if (responseEntity != null) {
				System.out.println("Response content length: "
						+ responseEntity.getContentLength());
			}

			String jsonResultString = EntityUtils.toString(responseEntity);
			EntityUtils.consume(responseEntity);
			System.out.println("----------------------------------------");
			System.out.println("result:");
			System.out.println(jsonResultString);
		} catch (Exception e) {
			log.error(ExceptionEx.e2s(e));
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
	}

	static public String sendPostXml(String host, String xmlInfo, String chaUTF) {
		String r = "";
		if (chaUTF == null || "".equals(chaUTF.trim()))
			chaUTF = "UTF-8";

		byte[] bes = new byte[0];
		try {
			bes = xmlInfo.getBytes(chaUTF);
		} catch (UnsupportedEncodingException e) {
			log.error(ExceptionEx.e2s(e));
		}
		r = sendPostXml(host, chaUTF, bes);
		return r;
	}

	static public String sendPostXml(String host, String chaUTF, byte[] bes) {
		String r = "";
		if (bes == null)
			return r;
		try {
			URL url = new URL(host);
			URLConnection con = url.openConnection();
			con.setDoOutput(true);
			con.setRequestProperty("Pragma:", "no-cache");
			con.setRequestProperty("Cache-Control", "no-cache");
			con.setRequestProperty("Content-Type", "text/xml");

			OutputStreamWriter out = new OutputStreamWriter(
					con.getOutputStream());

			out.write(new String(bes));
			out.flush();
			out.close();
			InputStream inStream = con.getInputStream();
			BufferedReader br = null;
			if (StrEx.isEmpty(chaUTF)) {
				br = new BufferedReader(new InputStreamReader(inStream));
			} else {
				br = new BufferedReader(new InputStreamReader(inStream, chaUTF));
			}
			StringBuffer buff = new StringBuffer();
			String line = "";
			while ((line = br.readLine()) != null) {
				buff.append(line);
			}
			r = buff.toString();
		} catch (Exception e) {
			log.error(ExceptionEx.e2s(e));
		}
		return r;
	}

	// URLConnection HttpURLConnection
	static public String sendGetStr2(String url, String chaUTF,
			HashMap<String, String> data) throws Exception {
		return sendGetStr2(url, chaUTF, httpBuildQuery(data));
	}

	static public String sendGetStr2(String url, String chaUTF, String param) {
		String result = "";
		BufferedReader in = null;
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
			InputStream inStream = connection.getInputStream();
			if (StrEx.isEmpty(chaUTF)) {
				in = new BufferedReader(new InputStreamReader(inStream));
			} else {
				in = new BufferedReader(new InputStreamReader(inStream, chaUTF));
			}
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			log.error(ExceptionEx.e2s(e));
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	static public String sendPostStr2(String url, String chaUTF,
			Map<String, String> data) throws Exception {
		return sendPostStr2(url, chaUTF, httpBuildQuery(data));
	}

	static public String sendPostJson2(String url, String chaUTF,
			Map<String, String> data) throws Exception {
		return sendPostStr2(url, chaUTF, httpBuildJson(data));
	}

	static public String sendPostStr2(String url, String chaUTF, String data)
			throws Exception {

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

		String line;
		BufferedReader buffRead;
		StringBuilder sb = new StringBuilder();
		InputStreamReader streamReader = null;
		boolean isNullCTF = StrEx.isEmpty(chaUTF);

		try {
			InputStream inStream = conn.getInputStream();
			if (isNullCTF) {
				streamReader = new InputStreamReader(inStream);
			} else {
				streamReader = new InputStreamReader(inStream, chaUTF);
			}
		} catch (IOException e) {
			/*
			 * Boolean ret2 = true; if (ret2) { return e.getMessage(); }
			 */
			InputStream errStream = conn.getErrorStream();
			if (isNullCTF) {
				streamReader = new InputStreamReader(errStream);
			} else {
				streamReader = new InputStreamReader(errStream, chaUTF);
			}
		} finally {
			if (streamReader != null) {
				buffRead = new BufferedReader(streamReader);
				sb = new StringBuilder();
				while ((line = buffRead.readLine()) != null) {
					sb.append(line);
				}
			}
		}
		return sb.toString();
	}

	static public String getSpeed(String pingUrl, String charUTF) {
		Charset cset = null;
		if (!StrEx.isEmpty(charUTF)) {
			cset = Charset.forName(charUTF);
		}
		return getSpeed(pingUrl, cset);
	}

	static public String getSpeed(String pingUrl, Charset cset) {
		try {
			String line = null;
			Process pro = Runtime.getRuntime().exec(
					"ping " + pingUrl + " -l 1000 -n 4");

			InputStream inStream = pro.getInputStream();
			BufferedReader buf = null;
			if (cset == null) {
				buf = new BufferedReader(new InputStreamReader(inStream));
			} else {
				buf = new BufferedReader(new InputStreamReader(inStream, cset));
			}
			int len = 0;
			String vEn = "Average";
			String vCn = "平均";
			while ((line = buf.readLine()) != null) {
				int position = line.indexOf(vEn);
				if (position == -1) {
					position = line.indexOf(vCn);
					len = vCn.length();
				} else {
					len = vEn.length();
				}
				if (position != -1) {
					System.out.println(line);
					String value = line.substring(position + len,
							line.lastIndexOf("ms"));
					value = value.replaceAll("=", "");
					value = value.trim();
					double speed = (1000d / Integer.parseInt(value)) / 1024
							* DateEx.TIME_SECOND;
					double lineMB = (1024 * 1.25);
					String v = "";
					if (speed > lineMB) {
						speed /= 1024;
						v = NumEx.formatDouble(speed) + "MB/s";
					} else {
						v = NumEx.formatDouble(speed) + "KB/s";
					}
					System.out.println("下载速度:" + v);
					return v;
				}
			}
		} catch (Exception e) {
		}
		return "0KB/s";
	}

	static public void main(String[] args) {
		System.out.println(getSpeed("www.baidu.com", "gbk"));
	}
}
