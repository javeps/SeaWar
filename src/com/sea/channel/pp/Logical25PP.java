package com.sea.channel.pp;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.security.MD5;
import com.bowlong.util.MapEx;
import com.sea.content.Svc;
import com.sea.handler.request.HttpReqWeb;
import com.sea.tools.UtileTools;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Logical25PP {

	static Log log = LogFactory.getLog(Logical25PP.class);

	// ============= 登录
	static String host = "http://passport_i.25pp.com:8080/account?tunnel-command=2852126760";
	static final String appKey = "a7f750213a46f8bbb81a08fbe4cfed89";
	static final int gameId = 3105;

	static public String getAccountId(String sid) throws Exception {
		long nt = System.currentTimeMillis();
		String sign = MD5.MD5Encode("sid=" + sid + appKey);
		Map parames = new HashMap();
		parames.put("id", nt);
		parames.put("service", "account.verifySession");

		Map data = new HashMap();
		data.put("sid", sid);
		parames.put("data", data);

		Map game = new HashMap();
		game.put("gameId", gameId);
		parames.put("game", game);

		parames.put("encrypt", "md5");
		parames.put("sigin", sign);
		String vStr = "";
		vStr = HttpReqWeb.sendPostJson(host, parames);
		return passParm(vStr);
	}

	static private String passParm(String json) {
		Map map = UtileTools.strToMap(json);
		Map mapStatus = MapEx.getMap(map, "state");
		int code = MapEx.getInt(mapStatus, "code");
		if (code == 1) {
			Map mapData = MapEx.getMap(map, "data");
			String result = MapEx.getString(mapData, "accountId");
			if (Svc.isEmpty(result)) {
				log.info(json);
				return "";
			}
			return result;
		} else {
			log.info(json);
			return "";
		}
	}

	public static void main(String[] args) throws Exception {
		String sid = "asdfdasgasdfdasgasdfdasgasdfdasg";
		String v = getAccountId(sid);
		System.out.println(v);
	}
}
