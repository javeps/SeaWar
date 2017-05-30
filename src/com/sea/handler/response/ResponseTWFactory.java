package com.sea.handler.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.QueryStringDecoder;
import org.jboss.netty.handler.codec.http.multipart.Attribute;
import org.jboss.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import org.jboss.netty.handler.codec.http.multipart.HttpDataFactory;
import org.jboss.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import org.jboss.netty.handler.codec.http.multipart.InterfaceHttpData;

import com.bowlong.util.MapEx;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class ResponseTWFactory implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(ResponseTWFactory.class);

	// 此处的res 是 response 的简称.
	// 此处的req 是 request 的简称.
	// ByPost 表示post 请求传参
	// ByGet 表示 get 请求传参

	public static void closeChannel(Channel chn) throws Exception {
		if (chn == null)
			return;
		chn.disconnect();
		chn.close();
	}

	public static String getStrDataByBuffer(HttpRequest request,
			String strCharset) {
		String str = "";
		try {
			if (strCharset == null || "".equals(strCharset.trim()))
				strCharset = "UTF-8";

			ChannelBuffer buffer = request.getContent();
			byte[] bytes = buffer.array();
			str = new String(bytes, strCharset);
		} catch (Exception e) {
			log.error(UtileTools.ex2s(e));
		}
		System.out.println("== getStrDataByBuffer = = " + str);
		return str;
	}

	public static String getStrQueryByMap(Map<String, String> map) {
		String r = "";
		if (map == null || map.isEmpty())
			return r;
		StringBuffer build = new StringBuffer();
		String key = "";
		String val = "";
		for (Entry<String, String> entry : map.entrySet()) {
			key = entry.getKey();
			val = entry.getValue();
			build.append(key).append("=").append(val).append("&");
		}
		r = build.toString();
		int len = r.length();
		int index = r.lastIndexOf("&");
		if (len > 1 && index == len - 1) {
			r = r.substring(0, index);
		}
		return r;
	}

	// =============== get 请求传参

	public static String getParamsVal(String query, String key) {
		String strCtr = "";
		if (key == null || "".equals(key.trim()))
			return strCtr;
		if (query != null && !"".equals(query.trim())) {
			boolean isFirst = query.indexOf("?") == 0;
			if (isFirst)
				query = query.substring(1);
			String[] params = query.split("&");
			for (String item : params) {
				if (item == null || "".equals(item.trim()))
					continue;
				int index = item.indexOf("=");
				if (index < 0)
					continue;
				String k = item.substring(0, index);
				if (key.equals(k)) {
					strCtr = item.substring(index + 1);
					break;
				}
			}
		}
		return strCtr;
	}

	// 根据uri取得get传递上来的所有参数集合的map对象
	static public Map<String, List<String>> getMapKVesByGet(String strUri) {
		Map<String, List<String>> v = null;
		if (strUri != null && !"".equals(strUri.trim())) {
			QueryStringDecoder qdec = new QueryStringDecoder(strUri);
			v = qdec.getParameters();
		}
		return v;
	}

	static public Map<String, String> getMapByGet(String strUri) {
		Map<String, String> result = null;
		if (strUri != null && !"".equals(strUri.trim())) {
			QueryStringDecoder qdec = new QueryStringDecoder(strUri);
			Map<String, List<String>> v = qdec.getParameters();
			if (v != null && !v.isEmpty()) {
				result = new ConcurrentHashMap<String, String>();
				for (Entry<String, List<String>> item : v.entrySet()) {
					List<String> tmp = item.getValue();
					if (tmp == null || tmp.isEmpty())
						continue;
					result.put(item.getKey(), tmp.get(0));
				}
			}
		}
		return result;
	}

	// 根据uri key 取得get 传递上来的key值所对应的value集合(一般情况下一个key对应一个value)
	private static List<String> getListValByGet(String strUri, String key) {
		List<String> v = new ArrayList<String>();
		Map<String, List<String>> map = getMapKVesByGet(strUri);
		List<String> tmp = null;
		if (map != null && !map.isEmpty()) {
			boolean isHasKey = map.containsKey(key);
			if (isHasKey) {
				tmp = map.get(key);
			}
		}
		if (tmp != null && !tmp.isEmpty()) {
			v.addAll(tmp);
		}
		return v;
	}

	// 根据parmetes 参数取得对应的value，value默认是key对应的所有值中的第一个值.
	public static Map<String, String> getMapByGetKeys(String strUri,
			String... keyes) {
		Map<String, String> v = new HashMap<String, String>();
		Map<String, List<String>> map = getMapKVesByGet(strUri);
		List<String> tmp = null;
		if (map != null && !map.isEmpty()) {
			for (String key : keyes) {
				boolean isHasKey = map.containsKey(key);
				if (!isHasKey) {
					v.put(key, "");
					continue;
				}
				tmp = map.get(key);
				if (tmp != null && !tmp.isEmpty()) {
					String val = tmp.get(0);
					v.put(key, val);
				}
			}

		}
		return v;
	}

	// 取得一个参数所对应的第一个参数值
	public static String getStrValByGetKey(String strUri, String key) {
		String strCtr = "";
		if (key == null || "".equals(key.trim()))
			return strCtr;
		List<String> vales = getListValByGet(strUri, key);
		if (vales.isEmpty())
			return strCtr;
		strCtr = vales.get(0);
		return strCtr;
	}

	// ==================== post 请求传参
	private static HttpPostRequestDecoder getDecoderByPost(HttpRequest request)
			throws Exception {
		HttpDataFactory factory = new DefaultHttpDataFactory(false);
		HttpPostRequestDecoder postDecoder = new HttpPostRequestDecoder(
				factory, request);
		return postDecoder;
	}

	private static Attribute getAttributeByPost(HttpRequest request,
			String strAttr) throws Exception {
		HttpPostRequestDecoder postDecoder = getDecoderByPost(request);
		Attribute r = getAttributeByPost(postDecoder, strAttr);
		return r;
	}

	private static Attribute getAttributeByPost(
			HttpPostRequestDecoder postDecoder, String strAttr)
			throws Exception {
		InterfaceHttpData data = postDecoder.getBodyHttpData(strAttr);
		Attribute r = (Attribute) data;
		return r;
	}

	private static String getStrValByPostKey(
			HttpPostRequestDecoder postDecoder, String key) throws Exception {
		String r = "";
		Attribute attr = getAttributeByPost(postDecoder, key);
		if (attr != null) {
			r = attr.getValue();
		}
		return r;
	}

	public static String getStrValByPostKey(HttpRequest request, String key)
			throws Exception {
		String r = "";
		Attribute attr = getAttributeByPost(request, key);
		if (attr != null) {
			r = attr.getValue();
		}
		return r;
	}

	private static Map<String, String> getMapByPostBuffer(HttpRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		String str = getStrDataByBuffer(request, "");
		Map mapData = UtileTools.strToMap(str);
		if (mapData == null || mapData.isEmpty())
			return map;
		for (Object item : mapData.keySet()) {
			String key = item.toString();
			String val = "";
			Object o = MapEx.get(mapData, key);
			if (o instanceof Map) {
				val = UtileTools.mapToStr((Map) o);
			} else if (o instanceof List) {
				val = UtileTools.listToStr((List) o);
			} else {
				val = o.toString();
			}
			map.put(key, val);
		}
		return map;
	}

	private static Map<String, String> getMapKVByPostBuffer(
			HttpRequest request, String... keys) {
		Map<String, String> map = new HashMap<String, String>();
		if (keys == null || keys.length <= 0)
			return map;
		Map<String, String> dataMap = getMapByPostBuffer(request);
		if (dataMap == null || dataMap.isEmpty())
			return map;
		for (String key : keys) {
			String val = MapEx.getString(dataMap, key);
			map.put(key, val);
		}
		return map;
	}

	public static Map<String, String> getMapKVByPost(HttpRequest request,
			String... keys) {
		Map<String, String> map = new HashMap<String, String>();
		if (keys == null || keys.length <= 0)
			return map;

		HttpPostRequestDecoder postDecoder = null;
		try {
			postDecoder = getDecoderByPost(request);
			for (String key : keys) {
				String val = getStrValByPostKey(postDecoder, key);
				map.put(key, val);
			}
		} catch (Exception e) {
			log.error(UtileTools.ex2s(e));
			map.clear();
		}

		if (postDecoder == null || map.isEmpty()) {
			map = getMapKVByPostBuffer(request, keys);
		}
		return map;
	}

	public static Map<String, String> getMapQueryByPostBody(HttpRequest request) {
		HttpPostRequestDecoder postDecoder = null;
		Map<String, String> map = new HashMap<String, String>();
		try {
			postDecoder = getDecoderByPost(request);
			List<InterfaceHttpData> datas = postDecoder.getBodyHttpDatas();
			for (InterfaceHttpData data : datas) {
				Attribute attr = (Attribute) data;
				String key = attr.getName();
				String val = attr.getValue();
				map.put(key, val);
			}
		} catch (Exception e) {
			log.error(UtileTools.ex2s(e));
		}

		if (map.isEmpty()) {
			map = getMapByPostBuffer(request);
		}

		return map;
	}

	public static String getStrQueryByPostBody(HttpRequest request) {
		String r = "";
		try {
			Map<String, String> dataMap = getMapQueryByPostBody(request);
			r = getStrQueryByMap(dataMap);
		} catch (Exception e) {
			log.error(UtileTools.ex2s(e));
		}
		return r;
	}

	public static String getStrSignByRequestPost(HttpRequest request)
			throws Exception {
		Map<String, String> map = getMapQueryByPostBody(request);
		if (map != null) {
			return MapEx.getString(map, "sign");
		}
		return "";
	}
}
