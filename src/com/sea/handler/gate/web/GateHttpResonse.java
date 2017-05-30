package com.sea.handler.gate.web;

import java.io.Serializable;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.handler.codec.http.HttpRequest;

import com.alibaba.fastjson.JSON;
import com.bowlong.util.MapEx;
import com.sea.cache.jedis.admin.AdminJson;
import com.sea.channel.ChannelCfg;
import com.sea.channel.apple.PayApple;
import com.sea.channel.downjoy.PayDownjoy;
import com.sea.channel.famv.PayFamv;
import com.sea.channel.gp.PayGooglePay;
import com.sea.channel.he.PayMoblieHe;
import com.sea.channel.mm.PayMoblieMM;
import com.sea.channel.mumayi.PayMuMaYi;
import com.sea.channel.nd91.PayNd91;
import com.sea.channel.pp.Logical25PP;
import com.sea.channel.pp.Pay25PP;
import com.sea.channel.qihu360.PayQiHu360;
import com.sea.channel.qihu360.VerifyPayCallQH360;
import com.sea.channel.ty.PayTY;
import com.sea.channel.ty.PayTYKJ;
import com.sea.channel.uc.PayUC;
import com.sea.channel.wo.PayUnicom;
import com.sea.channel.xiaomi.PayXiaoMi;
import com.sea.content.AppContext;
import com.sea.content.Svc;
import com.sea.db.bean.Admin;
import com.sea.handler.gate.GateConfig;
import com.sea.handler.gate.tcp.NotifyGateGame;
import com.sea.handler.request.HttpReqWeb;
import com.sea.handler.response.ResponseWeb;
import com.sea.json.originData.AdminAccountJson;
import com.sea.json.originData.NoticeJson;
import com.sea.json.originData.VerJson;
import com.sea.tools.UtileTools;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class GateHttpResonse implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(GateHttpResonse.class);

	private GateHttpResonse() {
	}

	static GateHttpResonse _self;

	public static GateHttpResonse getInstance() {
		if (_self == null)
			_self = new GateHttpResonse();

		return _self;
	}

	static String channelKey = "chl";

	public void _dispIssue(HttpRequest httpRequest, Channel channel) {
		try {
			String strUri = httpRequest.getUri();
			System.out.println("=uri=" + strUri);
			System.out.println("====================================");

			URI uri = new URI(strUri);

			final String path = uri.getPath();
			if (path == null || "".equals(path)) {
				log.info("==path == null,uri=" + strUri);
				ResponseWeb.send(channel, "error");
				return;
			}

			String query = uri.getQuery();
			String strChannel = ResponseWeb.getParamsVal(query, channelKey);
			switch (path) {
			case "/timeToStr":
				changeLongTimeToStr(query, channel);
				break;
			case "/version":
				getVersion(strChannel, channel);
				break;
			case "/login25PP":
				login25PP(query, channel);
				break;
			case "/notice":
				getNotice(strChannel, channel);
				break;
			case "/sves":
				getSv(channel, strChannel);
				break;
			case "/upver":
				getUpVerUrl(strChannel, channel);
				break;
			case "/chat":
				sendChatToGame(query, channel);
				break;
			case "/mail":
				sendMailToGame(query, channel);
				break;
			case "/addres":
				changePlayerResInNames(query, channel);
				break;
			case "/ver_dirtyword":
				getDirtywordVersion(channel);
				break;
			case "/dirtyword":
				getDirtywordContent(channel);
				break;
			case "/ver_cfgActivity":
				getActivitiesVersion(channel);
				break;
			case "/cfgActivity":
				getActivitiesContent(channel, strChannel);
				break;
			case "/ver_cfgWarUnit":
				getArmyDataVersion(channel);
				break;
			case "/cfgWarUnit":
				getArmyLocalData(channel);
				break;
			case "/ver_cfgBuilding":
				getBuildDataVersion(channel);
				break;
			case "/cfgBuilding":
				getBuildLocalData(channel);
				break;
			case "/searchPname":
				searchPname(query, channel);
				break;
			case "/searchListPnames":
				searchListPnames(query, channel);
				break;
			case "/searchPcid":
				searchPcid(query, channel);
				break;
			case "/searchLogin":
				searchLogin(query, channel);
				break;
			case "/changeUcid":
				changeUcid(query, channel);
				break;
			case "/sendAllReward":
				sendAllReward(query, channel);
				break;
			case "/delByUcid":
				deleteByUcid(query, channel);
				break;
			case "/delByPcid":
				deleteByPcid(query, channel);
				break;
			case "/statusPname":
				changePlayerStatusByPname(query, channel);
				break;
			case "/statusPcid":
				changePlayerStatusByPcid(query, channel);
				break;
			case "/typePname":
				changePlayerTypeByPname(query, channel);
				break;
			case "/typePcid":
				changePlayerTypeByPcid(query, channel);
				break;
			case "/renownPname":
				changePlayerRenownByPname(query, channel);
				break;
			case "/renownPcid":
				changePlayerRenownByPcid(query, channel);
				break;
			case "/techPname":
				changePlayerTechByPname(query, channel);
				break;
			case "/techPcid":
				changePlayerTechByPcid(query, channel);
				break;
			case "/heroPname":
				changePlayerHeroByPname(query, channel);
				break;
			case "/heroPcid":
				changePlayerHeroByPcid(query, channel);
				break;
			case "/energyPcid":
				changePlayerEnergyByPcid(query, channel);
				break;
			case "/reloadPInfoPcid":
				reloadPInfoPcid(query, channel);
				break;
			case "/onlineNum":
				onlineNum(query, channel);
				break;
			case "/handPayByPcid":
				handPayByPcid(query, channel);
				break;
			case "/setOpenEvent":
				setOpenEvent(query, channel);
				break;
			case "/pay25pp":
				pay25ppBack(httpRequest, channel);
				break;
			case "/payIos":
				payIosBack(httpRequest, channel);
				break;
			case "/payMMY":
				payMMYBack(httpRequest, channel);
				break;
			case "/payGP":
				payGPBack(httpRequest, channel);
				break;
			case "/payQH360":
				payQH360Back(strUri, channel);
				break;
			case "/payDownjoy":
				payDownjoyBack(strUri, channel);
				break;
			case "/payMi":
				payMiBack(strUri, channel);
				break;
			case "/payNd91And":
				payNd91AndBack(strUri, channel);
				break;
			case "/payNd91Ios":
				payNd91IosBack(strUri, channel);
				break;
			case "/payUC":
				payUC(httpRequest, channel);
				break;
			case "/payMM":
				payMM(httpRequest, channel);
				break;
			case "/payTY":
				payTY(httpRequest, channel);
				break;
			case "/payTYKJ":
				payTYKJ(httpRequest, channel);
				break;
			case "/loginHE":
				loginHE(strUri, channel);
				break;
			case "/payHE":
				payHE(httpRequest, channel);
				break;
			case "/payFAMV":
				payFamv(httpRequest, channel);
				break;
			case "/notifyAppvnios":
				notifyAppvnios(httpRequest, channel);
				break;
			case "/payAppvnios":
				payAppvnios(httpRequest, channel);
				break;
			case "/payWO":
				payWo(httpRequest, channel);
				break;
			case "/lgAdmin":
				lgAdminJson(httpRequest, channel);
				break;
			case "/chnInfo":
				adminChnInfoJson(httpRequest, channel);
				break;
			case "/reloadActive":
				reloadActive(httpRequest, channel);
				break;
			case "/reloadTHall":
				reloadTHall(httpRequest, channel);
				break;
			case "/resetHeroAttr":
				resetHeroAttr(httpRequest, channel);
				break;
			case "/resetClanAdmin":
				resetClanAdmin(httpRequest, channel);
				break;
			case "/rebackPcid2Ucid":
				rebackPcid2Ucid(httpRequest, channel);
				break;
			case "/reward":
				reward(httpRequest, channel);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			String err = Svc.e2s(e);
			log.error(err);
			try {
				ResponseWeb.send(channel, err);
			} catch (Exception e1) {
			}
		}

	}

	// 转变timeLong to YYYY-MM-DD HH:mm:ss
	void changeLongTimeToStr(String quStr, Channel chn) throws Exception {
		String str = ResponseWeb.getParamsVal(quStr, "time");
		try {
			long sub = Long.parseLong(str);
			Date d = new Date(sub);
			String t = Svc.tFmt(d);
			ResponseWeb.send(chn, "时间:" + t);
		} catch (Exception e) {
			log.error(UtileTools.ex2s(e));
			ResponseWeb.send(chn, "Fail");
			return;
		}
	}

	// 取得公告信息
	private void getNotice(String strChannel, Channel channel) throws Exception {
		String c = NoticeJson.getContNotice(strChannel, true);
		ResponseWeb.send(channel, c);
	}

	// 取得版本信息
	private void getVersion(final String strChannel, Channel channel)
			throws Exception {
		String ver = "0.9";
		ver = VerJson.getVerByChn(strChannel);
		if (ver == null || "".equals(ver.trim()))
			ver = "0.9";
		ResponseWeb.send(channel, ver);
	}

	// 取得版本信息
	private void login25PP(final String query, Channel channel)
			throws Exception {
		String chnStr = ResponseWeb.getParamsVal(query, "chl");
		boolean is25pp = ChannelCfg.PP.equalsIgnoreCase(chnStr);
		String result = "";
		if (is25pp) {
			String sidStr = ResponseWeb.getParamsVal(query, "sid");
			try {
				result = Logical25PP.getAccountId(sidStr);
			} catch (Exception e) {
				result = "";
			}
		}
		ResponseWeb.send(channel, result);
	}

	// 取得屏蔽字版本信息
	private void getDirtywordVersion(Channel channel) throws Exception {
		int r = VerJson.getVerDirtyword();
		ResponseWeb.send(channel, r + "");
	}

	// 取得屏蔽字 内容
	private void getDirtywordContent(Channel channel) throws Exception {
		String strTmp = VerJson.getContDirtyword();
		ResponseWeb.sendByChunked(channel, strTmp);
	}

	// 取得活动版本信息
	private void getActivitiesVersion(Channel channel) throws Exception {
		int r = VerJson.getVerActiviites();
		ResponseWeb.send(channel, r + "");
	}

	// 取得活动 内容
	private void getActivitiesContent(Channel channel, String strChn)
			throws Exception {
		strChn = strChn == null ? "" : strChn;
		byte[] r = VerJson.getContByteActivities(strChn);
		ResponseWeb.sendByChunked(channel, r);
	}

	// 取得活动版本信息
	private void getArmyDataVersion(Channel channel) throws Exception {
		int r = VerJson.getVerAmryData();
		ResponseWeb.send(channel, r + "");
	}

	// 取得兵种英雄属性
	private void getArmyLocalData(Channel channel) throws Exception {
		byte[] r = VerJson.getContByteAmryData();
		ResponseWeb.sendByChunked(channel, r);
	}

	// 取得建筑版本信息
	private void getBuildDataVersion(Channel channel) throws Exception {
		int r = VerJson.getVerBuildData();
		ResponseWeb.send(channel, r + "");
	}

	// 取得建筑属性
	private void getBuildLocalData(Channel channel) throws Exception {
		byte[] r = VerJson.getContByteBuildData();
		ResponseWeb.sendByChunked(channel, r);
	}

	// 取得服务器列表
	private void getSv(Channel channel, String strChn) throws Exception {
		String strSv = VerJson.getSvesByChn(strChn);
		ResponseWeb.send(channel, strSv);
	}

	// 取得版本更新地址
	private void getUpVerUrl(final String strChannel, Channel channel)
			throws Exception {
		String strUrl = "";
		strUrl = VerJson.getStrUrlForUpVer(strChannel);
		ResponseWeb.send(channel, strUrl);
	}

	// 下发聊天信息到游戏中
	void sendChatToGame(String quStr, Channel chn) throws Exception {
		String strSub = ResponseWeb.getParamsVal(quStr, "sub");
		String strCont = ResponseWeb.getParamsVal(quStr, "cont");
		String strSvcid = ResponseWeb.getParamsVal(quStr, "svcid");
		int svcid = 0;
		try {
			svcid = Integer.parseInt(strSvcid);
		} catch (Exception e) {
			svcid = 0;
		}
		try {
			int sub = Integer.parseInt(strSub);
			NotifyGateGame.getInstance().sendToGame(GateConfig.TGG_Chat, svcid,
					sub, strCont);
		} catch (Exception e) {
			log.error(UtileTools.ex2s(e));
			ResponseWeb.send(chn, "Fail");
			return;
		}
		ResponseWeb.send(chn, "Success");
	}

	// 下发邮件信息到游戏中
	void sendMailToGame(String quStr, Channel chn) throws Exception {
		String strType = ResponseWeb.getParamsVal(quStr, "type");
		String strSub = ResponseWeb.getParamsVal(quStr, "sub");
		String strNames = ResponseWeb.getParamsVal(quStr, "names");
		String strTitle = ResponseWeb.getParamsVal(quStr, "title");
		String strCont = ResponseWeb.getParamsVal(quStr, "cont");
		String strSvcid = ResponseWeb.getParamsVal(quStr, "svcid");
		int svcid = 0;
		try {
			svcid = Integer.parseInt(strSvcid);
		} catch (Exception e) {
			svcid = 0;
		}
		try {
			int type = Integer.parseInt(strType);
			int sub = Integer.parseInt(strSub);
			NotifyGateGame.getInstance().sendToGame(GateConfig.TGG_Mail, svcid,
					type, sub, strTitle, strCont, strNames);
		} catch (Exception e) {
			log.error(UtileTools.ex2s(e));
			ResponseWeb.send(chn, "Fail");
			return;
		}
		ResponseWeb.send(chn, "Success");
	}

	// 改变用户资源
	void changePlayerResInNames(String quStr, Channel chn) throws Exception {
		String sign = ResponseWeb.getParamsVal(quStr, "sign");
		String outStr = "Fail";
		if ("sw".equals(sign)) {
			// type[1表示名字,2表示PCID]
			String strSvcid = ResponseWeb.getParamsVal(quStr, "svcid");
			int svcid = 0;
			try {
				svcid = Integer.parseInt(strSvcid);
			} catch (Exception e) {
				svcid = 0;
			}
			if (svcid == 0) {
				svcid = 1000;
			}
			String strType = ResponseWeb.getParamsVal(quStr, "type");
			String strList = ResponseWeb.getParamsVal(quStr, "strList");
			String strResType = ResponseWeb.getParamsVal(quStr, "resType");
			String strResVal = ResponseWeb.getParamsVal(quStr, "resVal");
			try {
				int resVal = Integer.parseInt(strResVal);
				int type = Integer.parseInt(strType);
				NotifyGateGame.getInstance().sendToGame(
						GateConfig.TGG_AddPlayerRes, type, strList, strResType,
						resVal, svcid);
				outStr = "Success";
			} catch (Exception e) {
				log.error(UtileTools.ex2s(e));
			}
		}

		String callback = ResponseWeb.getParamsVal(quStr, "callback");
		if (Svc.isEmpty(callback)) {
			ResponseWeb.send(chn, outStr);
		} else {
			Map outMap = new HashMap();
			outMap.put("status", 1);
			outMap.put("val", outStr);
			sendJsonJScript(chn, outMap, callback);
		}
	}

	private void searchPname(String quStr, Channel chn) throws Exception {
		String strSvcid = ResponseWeb.getParamsVal(quStr, "svcid");
		int svcid = 0;
		try {
			svcid = Integer.parseInt(strSvcid);
		} catch (Exception e) {
			svcid = 0;
		}
		if (svcid == 0) {
			svcid = 1000;
		}
		String host = AppContext.GAME_WEB_HOST(svcid);
		if ("".equals(host)) {
			ResponseWeb.send(chn, "Fail:Host is null(游戏服务器地址为空)");
			return;
		}
		host += "search_pname";
		String strName = ResponseWeb.getParamsVal(quStr, "name");
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", strName);
		String outStr = "";
		// outStr = HttpReqWeb.sendPostStr(host, "", map);
		Map mapGet = HttpReqWeb.sendPostMap(host, "", map);
		if (mapGet == null) {
			mapGet = new HashMap();
		}

		String callback = ResponseWeb.getParamsVal(quStr, "callback");
		if (Svc.isEmpty(callback)) {
			outStr = UtileTools.mapToStr(mapGet);
			ResponseWeb.send(chn, outStr);
		} else {
			mapGet.put("status", 1);
			sendJsonJScript(chn, mapGet, callback);
		}
	}

	private void searchListPnames(String quStr, Channel chn) throws Exception {
		String strSvcid = ResponseWeb.getParamsVal(quStr, "svcid");
		int svcid = 0;
		try {
			svcid = Integer.parseInt(strSvcid);
		} catch (Exception e) {
			svcid = 0;
		}
		if (svcid == 0) {
			svcid = 1000;
		}
		String host = AppContext.GAME_WEB_HOST(svcid);
		if ("".equals(host)) {
			ResponseWeb.send(chn, "Fail:Host is null(游戏服务器地址为空)");
			return;
		}
		host += "search_list_pname";
		String strName = ResponseWeb.getParamsVal(quStr, "name");
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", strName);
		String outStr = HttpReqWeb.sendPostStr(host, "", map);

		String callback = ResponseWeb.getParamsVal(quStr, "callback");
		if (Svc.isEmpty(callback)) {
			ResponseWeb.send(chn, outStr);
		} else {
			Map outMap = new HashMap();
			outMap.put("status", 1);
			outMap.put("val", outStr);
			sendJsonJScript(chn, outMap, callback);
		}
	}

	private void searchPcid(String quStr, Channel chn) throws Exception {
		String strSvcid = ResponseWeb.getParamsVal(quStr, "svcid");
		int svcid = 0;
		try {
			svcid = Integer.parseInt(strSvcid);
		} catch (Exception e) {
			svcid = 0;
		}
		if (svcid == 0) {
			svcid = 1000;
		}
		String host = AppContext.GAME_WEB_HOST(svcid);
		if ("".equals(host)) {
			ResponseWeb.send(chn, "Fail:Host is null(游戏服务器地址为空)");
			return;
		}
		host += "search_pcid";
		String strPcid = ResponseWeb.getParamsVal(quStr, "pcid");
		Map<String, String> map = new HashMap<String, String>();
		map.put("pcid", strPcid);
		String outStr = "";
		// outStr = HttpReqWeb.sendPostStr(host, "", map);
		Map mapGet = HttpReqWeb.sendPostMap(host, "", map);

		String callback = ResponseWeb.getParamsVal(quStr, "callback");
		if (Svc.isEmpty(callback)) {
			outStr = UtileTools.mapToStr(mapGet);
			ResponseWeb.send(chn, outStr);
		} else {
			if (mapGet == null) {
				mapGet = new HashMap();
			}
			mapGet.put("status", 1);
			sendJsonJScript(chn, mapGet, callback);
		}
	}

	private void searchLogin(String quStr, Channel chn) throws Exception {
		String strSvcid = ResponseWeb.getParamsVal(quStr, "svcid");
		int svcid = 0;
		try {
			svcid = Integer.parseInt(strSvcid);
		} catch (Exception e) {
			svcid = 0;
		}
		if (svcid == 0) {
			svcid = 1000;
		}
		String host = AppContext.GAME_WEB_HOST(svcid);
		if ("".equals(host)) {
			ResponseWeb.send(chn, "Fail:Host is null(游戏服务器地址为空)");
			return;
		}
		host += "search_login";
		String strLgid = ResponseWeb.getParamsVal(quStr, "lgid");
		String strLgpwd = ResponseWeb.getParamsVal(quStr, "lgpwd");
		Map<String, String> map = new HashMap<String, String>();
		map.put("lgid", strLgid);
		map.put("lgpwd", strLgpwd);
		String outStr = HttpReqWeb.sendPostStr(host, "", map);
		ResponseWeb.send(chn, outStr);
	}

	private void changeUcid(String quStr, Channel chn) throws Exception {
		String strSvcid = ResponseWeb.getParamsVal(quStr, "svcid");
		int svcid = 0;
		try {
			svcid = Integer.parseInt(strSvcid);
		} catch (Exception e) {
			svcid = 0;
		}
		if (svcid == 0) {
			svcid = 1000;
		}
		String host = AppContext.GAME_WEB_HOST(svcid);
		if ("".equals(host)) {
			ResponseWeb.send(chn, "Fail:Host is null(游戏服务器地址为空)");
			return;
		}
		host += "change_ucid";
		String strLgid = ResponseWeb.getParamsVal(quStr, "lgid");
		String strLgpwd = ResponseWeb.getParamsVal(quStr, "lgpwd");
		String strUcid = ResponseWeb.getParamsVal(quStr, "ucid");
		String strDel = ResponseWeb.getParamsVal(quStr, "isDel");
		if (strDel == null || "".equals(strDel.trim()))
			strDel = "false";
		Map<String, String> map = new HashMap<String, String>();
		map.put("ucid", strUcid);
		map.put("lgid", strLgid);
		map.put("lgpwd", strLgpwd);
		map.put("isDel", strDel);
		String outStr = HttpReqWeb.sendPostStr(host, "", map);
		ResponseWeb.send(chn, outStr);
	}

	private void deleteByUcid(String quStr, Channel chn) throws Exception {
		String strSvcid = ResponseWeb.getParamsVal(quStr, "svcid");
		int svcid = 0;
		try {
			svcid = Integer.parseInt(strSvcid);
		} catch (Exception e) {
			svcid = 0;
		}
		if (svcid == 0) {
			svcid = 1000;
		}
		String host = AppContext.GAME_WEB_HOST(svcid);
		if ("".equals(host)) {
			ResponseWeb.send(chn, "Fail:Host is null(游戏服务器地址为空)");
			return;
		}
		host += "del_ucid";
		String strUcid = ResponseWeb.getParamsVal(quStr, "ucid");
		Map<String, String> map = new HashMap<String, String>();
		map.put("ucid", strUcid);
		String outStr = HttpReqWeb.sendPostStr(host, "", map);
		ResponseWeb.send(chn, outStr);
	}

	private void deleteByPcid(String quStr, Channel chn) throws Exception {
		String strSvcid = ResponseWeb.getParamsVal(quStr, "svcid");
		int svcid = 0;
		try {
			svcid = Integer.parseInt(strSvcid);
		} catch (Exception e) {
			svcid = 0;
		}
		if (svcid == 0) {
			svcid = 1000;
		}
		String host = AppContext.GAME_WEB_HOST(svcid);
		if ("".equals(host)) {
			ResponseWeb.send(chn, "Fail:Host is null(游戏服务器地址为空)");
			return;
		}
		host += "del_pcid";
		String strPcid = ResponseWeb.getParamsVal(quStr, "pcid");
		String strIsDel = ResponseWeb.getParamsVal(quStr, "isDelUser");
		if (strIsDel == null || "".equals(strIsDel.trim()))
			strIsDel = "false";
		Map<String, String> map = new HashMap<String, String>();
		map.put("pcid", strPcid);
		map.put("isDelUser", strIsDel);
		String outStr = HttpReqWeb.sendPostStr(host, "", map);
		ResponseWeb.send(chn, outStr);
	}

	// status[0正常,11封号]
	private void changePlayerStatusByPcid(String quStr, Channel chn)
			throws Exception {
		String strSvcid = ResponseWeb.getParamsVal(quStr, "svcid");
		int svcid = 0;
		try {
			svcid = Integer.parseInt(strSvcid);
		} catch (Exception e) {
			svcid = 0;
		}
		if (svcid == 0) {
			svcid = 1000;
		}
		String host = AppContext.GAME_WEB_HOST(svcid);
		if ("".equals(host)) {
			ResponseWeb.send(chn, "Fail:Host is null(游戏服务器地址为空)");
			return;
		}
		host += "pstatus_pcid";
		String strPcid = ResponseWeb.getParamsVal(quStr, "pcid");
		String strStatus = ResponseWeb.getParamsVal(quStr, "status");
		Map<String, String> map = new HashMap<String, String>();
		map.put("pcid", strPcid);
		map.put("status", strStatus);
		String outStr = HttpReqWeb.sendPostStr(host, "", map);
		ResponseWeb.send(chn, outStr);
	}

	// status[0正常,11封号]
	private void changePlayerStatusByPname(String quStr, Channel chn)
			throws Exception {
		String strSvcid = ResponseWeb.getParamsVal(quStr, "svcid");
		int svcid = 0;
		try {
			svcid = Integer.parseInt(strSvcid);
		} catch (Exception e) {
			svcid = 0;
		}
		if (svcid == 0) {
			svcid = 1000;
		}
		String host = AppContext.GAME_WEB_HOST(svcid);
		if ("".equals(host)) {
			ResponseWeb.send(chn, "Fail:Host is null(游戏服务器地址为空)");
			return;
		}
		host += "pstatus_pname";
		String strPname = ResponseWeb.getParamsVal(quStr, "name");
		String strStatus = ResponseWeb.getParamsVal(quStr, "status");
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", strPname);
		map.put("status", strStatus);
		String outStr = HttpReqWeb.sendPostStr(host, "", map);
		ResponseWeb.send(chn, outStr);
	}

	// type[0一般玩家,1NPC玩家,2游戏GM]
	private void changePlayerTypeByPname(String quStr, Channel chn)
			throws Exception {
		String strSvcid = ResponseWeb.getParamsVal(quStr, "svcid");
		int svcid = 0;
		try {
			svcid = Integer.parseInt(strSvcid);
		} catch (Exception e) {
			svcid = 0;
		}
		if (svcid == 0) {
			svcid = 1000;
		}
		String host = AppContext.GAME_WEB_HOST(svcid);
		if ("".equals(host)) {
			ResponseWeb.send(chn, "Fail:Host is null(游戏服务器地址为空)");
			return;
		}
		host += "ptype_pname";
		String strPname = ResponseWeb.getParamsVal(quStr, "name");
		String strStatus = ResponseWeb.getParamsVal(quStr, "type");
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", strPname);
		map.put("type", strStatus);
		String outStr = HttpReqWeb.sendPostStr(host, "", map);
		ResponseWeb.send(chn, outStr);
	}

	// type[0一般玩家,1NPC玩家,2游戏GM]
	private void changePlayerTypeByPcid(String quStr, Channel chn)
			throws Exception {
		String strSvcid = ResponseWeb.getParamsVal(quStr, "svcid");
		int svcid = 0;
		try {
			svcid = Integer.parseInt(strSvcid);
		} catch (Exception e) {
			svcid = 0;
		}
		if (svcid == 0) {
			svcid = 1000;
		}
		String host = AppContext.GAME_WEB_HOST(svcid);
		if ("".equals(host)) {
			ResponseWeb.send(chn, "Fail:Host is null(游戏服务器地址为空)");
			return;
		}
		host += "ptype_pcid";
		String strPcid = ResponseWeb.getParamsVal(quStr, "pcid");
		String strStatus = ResponseWeb.getParamsVal(quStr, "type");
		Map<String, String> map = new HashMap<String, String>();
		map.put("pcid", strPcid);
		map.put("type", strStatus);
		String outStr = HttpReqWeb.sendPostStr(host, "", map);
		ResponseWeb.send(chn, outStr);
	}

	// tech[改变当前值]
	private void changePlayerTechByPname(String quStr, Channel chn)
			throws Exception {
		String strSvcid = ResponseWeb.getParamsVal(quStr, "svcid");
		int svcid = 0;
		try {
			svcid = Integer.parseInt(strSvcid);
		} catch (Exception e) {
			svcid = 0;
		}
		if (svcid == 0) {
			svcid = 1000;
		}
		String host = AppContext.GAME_WEB_HOST(svcid);
		if ("".equals(host)) {
			ResponseWeb.send(chn, "Fail:Host is null(游戏服务器地址为空)");
			return;
		}
		host += "change_tech_pname";
		String strPname = ResponseWeb.getParamsVal(quStr, "name");
		String strStatus = ResponseWeb.getParamsVal(quStr, "tgid");
		String lvl = ResponseWeb.getParamsVal(quStr, "lvl");
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", strPname);
		map.put("tgid", strStatus);
		map.put("lvl", lvl);
		String outStr = HttpReqWeb.sendPostStr(host, "", map);
		ResponseWeb.send(chn, outStr);
	}

	// tech[改变当前值]
	private void changePlayerTechByPcid(String quStr, Channel chn)
			throws Exception {
		String strSvcid = ResponseWeb.getParamsVal(quStr, "svcid");
		int svcid = 0;
		try {
			svcid = Integer.parseInt(strSvcid);
		} catch (Exception e) {
			svcid = 0;
		}
		if (svcid == 0) {
			svcid = 1000;
		}
		String host = AppContext.GAME_WEB_HOST(svcid);
		if ("".equals(host)) {
			ResponseWeb.send(chn, "Fail:Host is null(游戏服务器地址为空)");
			return;
		}
		host += "change_tech_pcid";
		String strPcid = ResponseWeb.getParamsVal(quStr, "pcid");
		String strStatus = ResponseWeb.getParamsVal(quStr, "tgid");
		String lvl = ResponseWeb.getParamsVal(quStr, "lvl");
		Map<String, String> map = new HashMap<String, String>();
		map.put("pcid", strPcid);
		map.put("tgid", strStatus);
		map.put("lvl", lvl);
		String outStr = HttpReqWeb.sendPostStr(host, "", map);
		ResponseWeb.send(chn, outStr);
	}

	// hero[改变当前值]
	private void changePlayerHeroByPname(String quStr, Channel chn)
			throws Exception {
		String strSvcid = ResponseWeb.getParamsVal(quStr, "svcid");
		int svcid = 0;
		try {
			svcid = Integer.parseInt(strSvcid);
		} catch (Exception e) {
			svcid = 0;
		}
		if (svcid == 0) {
			svcid = 1000;
		}
		String host = AppContext.GAME_WEB_HOST(svcid);
		if ("".equals(host)) {
			ResponseWeb.send(chn, "Fail:Host is null(游戏服务器地址为空)");
			return;
		}
		host += "change_hero_pname";
		String strPname = ResponseWeb.getParamsVal(quStr, "name");
		String strStatus = ResponseWeb.getParamsVal(quStr, "hgid");
		String speed = ResponseWeb.getParamsVal(quStr, "speed");
		String damage = ResponseWeb.getParamsVal(quStr, "damage");
		String hp = ResponseWeb.getParamsVal(quStr, "hp");
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", strPname);
		map.put("hgid", strStatus);
		map.put("speed", speed);
		map.put("damage", damage);
		map.put("hp", hp);
		String outStr = HttpReqWeb.sendPostStr(host, "", map);
		ResponseWeb.send(chn, outStr);
	}

	// hero[改变当前值]
	private void changePlayerHeroByPcid(String quStr, Channel chn)
			throws Exception {
		String strSvcid = ResponseWeb.getParamsVal(quStr, "svcid");
		int svcid = 0;
		try {
			svcid = Integer.parseInt(strSvcid);
		} catch (Exception e) {
			svcid = 0;
		}
		if (svcid == 0) {
			svcid = 1000;
		}
		String host = AppContext.GAME_WEB_HOST(svcid);
		if ("".equals(host)) {
			ResponseWeb.send(chn, "Fail:Host is null(游戏服务器地址为空)");
			return;
		}
		host += "change_hero_pcid";
		String strPcid = ResponseWeb.getParamsVal(quStr, "pcid");
		String strStatus = ResponseWeb.getParamsVal(quStr, "hgid");
		String speed = ResponseWeb.getParamsVal(quStr, "speed");
		String damage = ResponseWeb.getParamsVal(quStr, "damage");
		String hp = ResponseWeb.getParamsVal(quStr, "hp");
		Map<String, String> map = new HashMap<String, String>();
		map.put("pcid", strPcid);
		map.put("hgid", strStatus);
		map.put("speed", speed);
		map.put("damage", damage);
		map.put("hp", hp);
		String outStr = HttpReqWeb.sendPostStr(host, "", map);
		ResponseWeb.send(chn, outStr);
	}

	private void changePlayerEnergyByPcid(String quStr, Channel chn)
			throws Exception {
		String strSvcid = ResponseWeb.getParamsVal(quStr, "svcid");
		int svcid = 0;
		try {
			svcid = Integer.parseInt(strSvcid);
		} catch (Exception e) {
			svcid = 0;
		}
		if (svcid == 0) {
			svcid = 1000;
		}
		String host = AppContext.GAME_WEB_HOST(svcid);
		if ("".equals(host)) {
			ResponseWeb.send(chn, "Fail:Host is null(游戏服务器地址为空)");
			return;
		}
		host += "penergy_pcid";
		String strPcid = ResponseWeb.getParamsVal(quStr, "pcid");
		String speed = ResponseWeb.getParamsVal(quStr, "addSpeed");
		String damage = ResponseWeb.getParamsVal(quStr, "addDam");
		String hp = ResponseWeb.getParamsVal(quStr, "addHP");
		Map<String, String> map = new HashMap<String, String>();
		map.put("pcid", strPcid);
		map.put("addSpeed", speed);
		map.put("addDam", damage);
		map.put("addHP", hp);
		String outStr = HttpReqWeb.sendPostStr(host, "", map);
		ResponseWeb.send(chn, outStr);
	}

	private void reloadPInfoPcid(String quStr, Channel chn) throws Exception {
		String strSvcid = ResponseWeb.getParamsVal(quStr, "svcid");
		int svcid = 0;
		try {
			svcid = Integer.parseInt(strSvcid);
		} catch (Exception e) {
			svcid = 0;
		}
		if (svcid == 0) {
			svcid = 1000;
		}
		String host = AppContext.GAME_WEB_HOST(svcid);
		if ("".equals(host)) {
			ResponseWeb.send(chn, "Fail:Host is null(游戏服务器地址为空)");
			return;
		}
		host += "reloadPInfo_pcid";
		String strPcid = ResponseWeb.getParamsVal(quStr, "pcid");
		Map<String, String> map = new HashMap<String, String>();
		map.put("pcid", strPcid);
		String outStr = HttpReqWeb.sendPostStr(host, "", map);
		ResponseWeb.send(chn, outStr);
	}

	// renow[改变当前值]
	private void changePlayerRenownByPname(String quStr, Channel chn)
			throws Exception {
		String strSvcid = ResponseWeb.getParamsVal(quStr, "svcid");
		int svcid = 0;
		try {
			svcid = Integer.parseInt(strSvcid);
		} catch (Exception e) {
			svcid = 0;
		}
		if (svcid == 0) {
			svcid = 1000;
		}
		String host = AppContext.GAME_WEB_HOST(svcid);
		if ("".equals(host)) {
			ResponseWeb.send(chn, "Fail:Host is null(游戏服务器地址为空)");
			return;
		}
		host += "change_renown_pname";
		String strPname = ResponseWeb.getParamsVal(quStr, "name");
		String strStatus = ResponseWeb.getParamsVal(quStr, "renown");
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", strPname);
		map.put("renown", strStatus);
		String outStr = HttpReqWeb.sendPostStr(host, "", map);
		ResponseWeb.send(chn, outStr);
	}

	// renow[改变当前值]
	private void changePlayerRenownByPcid(String quStr, Channel chn)
			throws Exception {
		String strSvcid = ResponseWeb.getParamsVal(quStr, "svcid");
		int svcid = 0;
		try {
			svcid = Integer.parseInt(strSvcid);
		} catch (Exception e) {
			svcid = 0;
		}
		if (svcid == 0) {
			svcid = 1000;
		}
		String host = AppContext.GAME_WEB_HOST(svcid);
		if ("".equals(host)) {
			ResponseWeb.send(chn, "Fail:Host is null(游戏服务器地址为空)");
			return;
		}
		host += "change_renown_pcid";
		String strPcid = ResponseWeb.getParamsVal(quStr, "pcid");
		String strStatus = ResponseWeb.getParamsVal(quStr, "renown");
		Map<String, String> map = new HashMap<String, String>();
		map.put("pcid", strPcid);
		map.put("renown", strStatus);
		String outStr = HttpReqWeb.sendPostStr(host, "", map);
		ResponseWeb.send(chn, outStr);
	}

	// 全服发资源
	private void sendAllReward(String quStr, Channel chn) throws Exception {
		String strSvcid = ResponseWeb.getParamsVal(quStr, "svcid");
		int svcid = 0;
		try {
			svcid = Integer.parseInt(strSvcid);
		} catch (Exception e) {
			svcid = 0;
		}
		if (svcid == 0) {
			svcid = 1000;
		}
		String host = AppContext.GAME_WEB_HOST(svcid);
		if ("".equals(host)) {
			ResponseWeb.send(chn, "Fail:Host is null(游戏服务器地址为空)");
			return;
		}
		host += "sendAllReward";
		String resType = ResponseWeb.getParamsVal(quStr, "resType");
		String val = ResponseWeb.getParamsVal(quStr, "val");
		Map<String, String> map = new HashMap<String, String>();
		map.put("resType", resType);
		map.put("val", val);
		String outStr = HttpReqWeb.sendPostStr(host, "", map);
		ResponseWeb.send(chn, outStr);
	}

	// 当前在线人数
	private void onlineNum(String quStr, Channel chn) throws Exception {
		String strSvcid = ResponseWeb.getParamsVal(quStr, "svcid");
		int svcid = 0;
		try {
			svcid = Integer.parseInt(strSvcid);
		} catch (Exception e) {
			svcid = 0;
		}
		if (svcid == 0) {
			svcid = 1000;
		}
		String host = AppContext.GAME_WEB_HOST(svcid);
		if ("".equals(host)) {
			ResponseWeb.send(chn, "Fail:Host is null(游戏服务器地址为空)");
			return;
		}
		host += "onlineNum";
		String outStr = HttpReqWeb.sendPostStr(host, "", "");
		ResponseWeb.send(chn, outStr);
	}

	// 人工处理充值
	private void handPayByPcid(String quStr, Channel chn) throws Exception {
		String strSvcid = ResponseWeb.getParamsVal(quStr, "svcid");
		int svcid = 0;
		try {
			svcid = Integer.parseInt(strSvcid);
		} catch (Exception e) {
			svcid = 0;
		}
		if (svcid == 0) {
			svcid = 1000;
		}
		String host = AppContext.GAME_WEB_HOST(svcid);
		if ("".equals(host)) {
			ResponseWeb.send(chn, "Fail:Host is null(游戏服务器地址为空)");
			return;
		}
		host += "handPayByPcid";
		String strPcid = ResponseWeb.getParamsVal(quStr, "pcid");
		String strMoney = ResponseWeb.getParamsVal(quStr, "money");
		String strIsMonth = ResponseWeb.getParamsVal(quStr, "isMonth");
		if (strIsMonth == null || "".equals(strIsMonth.trim()))
			strIsMonth = "false";
		String strGems = ResponseWeb.getParamsVal(quStr, "gems");
		Map<String, String> map = new HashMap<String, String>();
		map.put("pcid", strPcid);
		map.put("money", strMoney);
		map.put("isMonth", strIsMonth);
		map.put("gems", strGems);
		String outStr = HttpReqWeb.sendPostStr(host, "", map);

		String callback = ResponseWeb.getParamsVal(quStr, "callback");
		if (Svc.isEmpty(callback)) {
			ResponseWeb.send(chn, outStr);
		} else {
			Map outMap = new HashMap();
			outMap.put("status", 1);
			outMap.put("val", outStr);
			sendJsonJScript(chn, outMap, callback);
		}
	}

	private void setOpenEvent(String quStr, Channel chn) throws Exception {
		String strSvcid = ResponseWeb.getParamsVal(quStr, "svcid");
		int svcid = 0;
		try {
			svcid = Integer.parseInt(strSvcid);
		} catch (Exception e) {
			svcid = 0;
		}
		if (svcid == 0) {
			svcid = 1000;
		}
		String host = AppContext.GAME_WEB_HOST(svcid);
		if ("".equals(host)) {
			ResponseWeb.send(chn, "Fail:Host is null(游戏服务器地址为空)");
			return;
		}
		host += "setOpenEvent";
		String isOpenStr = ResponseWeb.getParamsVal(quStr, "isOpen");
		String edtimeStr = ResponseWeb.getParamsVal(quStr, "edtimeStr");
		String testPcid = ResponseWeb.getParamsVal(quStr, "testPcid");

		Map<String, String> map = new HashMap<String, String>();
		map.put("isOpen", isOpenStr);
		map.put("edtimeStr", edtimeStr);
		map.put("testPcid", testPcid);

		String outStr = HttpReqWeb.sendPostStr(host, "", map);
		ResponseWeb.send(chn, outStr);
	}

	// 25pp 充值回掉
	void pay25ppBack(HttpRequest request, Channel chn) throws Exception {
		String sign = ResponseWeb.getStrSignByRequestPost(request);
		String quStr = ResponseWeb.getStrQueryByPostBody(request);
		boolean isOk = Pay25PP.checkPay(quStr, sign);
		if (isOk) {
			ResponseWeb.send(chn, "success");
		} else {
			ResponseWeb.send(chn, "fail");
			log.info(quStr);
		}
	}

	// ios 充值回掉
	void payIosBack(HttpRequest request, Channel chn) throws Exception {
		String sign = ResponseWeb.getStrSignByRequestPost(request);
		String quStr = ResponseWeb.getStrQueryByPostBody(request);
		boolean isOk = PayApple.checkPay(quStr, sign);
		if (isOk) {
			ResponseWeb.send(chn, "success");
		} else {
			ResponseWeb.send(chn, "fail");
			log.info(quStr);
		}
	}

	// 木蚂蚁充值回掉
	void payMMYBack(HttpRequest request, Channel chn) throws Exception {
		boolean isOk = PayMuMaYi.checkPay(request);
		if (isOk) {
			ResponseWeb.send(chn, "success");
		} else {
			ResponseWeb.send(chn, "fail");
		}
	}

	// google play 充值回掉
	void payGPBack(HttpRequest request, Channel chn) throws Exception {
		String quStr = ResponseWeb.getStrQueryByPostBody(request);
		String sign = ResponseWeb.getStrSignByRequestPost(request);
		boolean isOk = PayGooglePay.checkPay(quStr, sign);
		if (isOk) {
			ResponseWeb.send(chn, "success");
		} else {
			ResponseWeb.send(chn, "fail");
			log.info(quStr);
		}
	}

	// 奇虎360 充值回掉
	void payQH360Back(String strUri, Channel chn) throws Exception {
		String strBack = PayQiHu360.checkPay(strUri);
		ResponseWeb.send(chn, strBack);
		if (!VerifyPayCallQH360.Code_Success.equals(strBack)) {
			log.info(strUri);
		}
	}

	// 当乐 充值回掉
	void payDownjoyBack(String strUri, Channel chn) throws Exception {
		String strBack = PayDownjoy.checkPay(strUri);
		ResponseWeb.send(chn, strBack);
		if (!PayDownjoy.Code_Success.equals(strBack)) {
			log.info(strUri);
		}
	}

	// 小米 充值回掉
	void payMiBack(String strUri, Channel chn) throws Exception {
		String strBack = PayXiaoMi.checkPay(strUri);
		ResponseWeb.send(chn, strBack);
		String strSuccess = PayXiaoMi.Code_Success + "";
		if (strBack.indexOf(strSuccess) == -1) {
			log.info(strUri);
		}
	}

	// 91 and充值回掉
	void payNd91AndBack(String strUri, Channel chn) throws Exception {
		String strBack = PayNd91.checkPay(strUri, true);
		ResponseWeb.send(chn, strBack);
		String strSuccess = PayNd91.Code_Success + "";
		if (strBack.indexOf(strSuccess) == -1) {
			log.info("91 安卓====" + strUri);
		}
	}

	// 91 ios充值回掉
	void payNd91IosBack(String strUri, Channel chn) throws Exception {
		String strBack = PayNd91.checkPay(strUri, false);
		ResponseWeb.send(chn, strBack);
		String strSuccess = PayNd91.Code_Success + "";
		if (strBack.indexOf(strSuccess) == -1) {
			log.info("91 IOS====" + strUri);
		}
	}

	// 木蚂蚁充值回掉
	void payUC(HttpRequest request, Channel chn) throws Exception {
		String strBack = PayUC.checkPay(request);
		ResponseWeb.send(chn, strBack);
	}

	// 移动MM 充值回掉
	void payMM(HttpRequest request, Channel chn) throws Exception {
		String xml = ResponseWeb.getStrDataByBuffer(request, "UTF-8");
		String isOk = PayMoblieMM.checkPay(xml);
		ResponseWeb.send(chn, isOk);
	}

	// 移动和渠道 充值回掉get
	static boolean isRecordLgHE = false;

	void loginHE(String strUri, Channel chn) throws Exception {
		String isOk = PayMoblieHe.loginHe(strUri);
		ResponseWeb.sendTxt(chn, isOk);
		if (isRecordLgHE)
			return;
		isRecordLgHE = true;
		log.info(strUri);
	}

	// 移动和渠道 充值回掉post
	static boolean isRecordPayHE = false;

	void payHE(HttpRequest request, Channel chn) throws Exception {
		String xml = ResponseWeb.getStrDataByBuffer(request, "UTF-8");
		String isOk = PayMoblieHe.checkPay(xml);
		ResponseWeb.send(chn, isOk);
		if (isRecordPayHE)
			return;
		isRecordPayHE = true;
		log.info(xml);
		log.info(isOk);
	}

	// 爱游戏充值回掉
	void payTY(HttpRequest request, Channel chn) throws Exception {
		String head = PayTY.checkPay(request);
		ResponseWeb.sendHead(chn, head);
	}

	// 天翼 充值回掉
	void payTYKJ(HttpRequest request, Channel chn) throws Exception {
		String json = PayTYKJ.checkPay(request);
		ResponseWeb.sendJson(chn, json);
	}

	// 联通手机用户 充值回掉
	void payWo(HttpRequest request, Channel chn) throws Exception {
		String xml = ResponseWeb.getStrDataByBuffer(request, "UTF-8");
		int index = -1;
		if (xml != null)
			index = xml.indexOf("callbackReq");
		String back = "";
		if (index == -1) {
			back = PayUnicom.verify(xml);
		} else {
			back = PayUnicom.checkPay(xml);
		}

		// log.info(xml + " \n " + back);

		ResponseWeb.sendJson(chn, back);
	}

	// 越南 充值回掉
	void payFamv(HttpRequest request, Channel chn) throws Exception {
		String sign = ResponseWeb.getStrSignByRequestPost(request);
		String quStr = ResponseWeb.getStrQueryByPostBody(request);
		boolean isOk = PayFamv.checkPay(quStr, sign);
		if (isOk) {
			ResponseWeb.send(chn, "success");
		} else {
			ResponseWeb.send(chn, "Fail : " + quStr);
			log.info(quStr);
		}
	}

	// 越南 appvnios 充值通知，创建订单
	void notifyAppvnios(HttpRequest request, Channel chn) throws Exception {
		PayFamv.notifyAppvnios(request);
		ResponseWeb.send(chn, "success");
	}

	void payAppvnios(HttpRequest request, Channel chn) throws Exception {
		String sign = ResponseWeb.getStrSignByRequestPost(request);
		String quStr = ResponseWeb.getStrQueryByPostBody(request);
		boolean isOk = PayFamv.checkPayAppvnios(quStr, sign);
		if (isOk) {
			ResponseWeb.send(chn, "success");
		} else {
			ResponseWeb.send(chn, "Fail : " + quStr);
			log.info(quStr);
		}
	}

	// ==================================== 后台管理请求
	private Map resultStatusJson(Map res, int status, String tip) {
		if (res == null)
			res = new HashMap();
		res.put("status", status);
		res.put("tip", tip);
		return res;
	}

	private void sendJsonJScript(Channel chn, String mapStr, String callBackFun)
			throws Exception {
		if ("".equals(callBackFun)) {
			ResponseWeb.send(chn, "error");
			return;
		}
		if (Svc.isEmpty(mapStr)) {
			mapStr = JSON.toJSONString(resultStatusJson(null, -1, "map为空，错误"));
		}
		ResponseWeb.sendJson(chn, callBackFun + "(" + mapStr + ")");
	}

	private void sendJsonJScript(Channel chn, Map map, String callBackFun)
			throws Exception {
		String json = "";
		if ("".equals(callBackFun)) {
			ResponseWeb.send(chn, "error");
			return;
		}
		if (map != null && !map.isEmpty()) {
			json = JSON.toJSONString(map);
		}
		sendJsonJScript(chn, json, callBackFun);
	}

	private void lgAdminJson(HttpRequest request, Channel chn) throws Exception {
		String strUri = request.getUri();
		Map<String, String> map = ResponseWeb.getMapByGet(strUri);
		String callBackFun = MapEx.getString(map, "callback");
		String lgId = MapEx.getString(map, "lgid");
		String lgpwd = MapEx.getString(map, "lgpwd");
		Map e = AdminAccountJson.getAccount(lgId, lgpwd);
		if (!Svc.isEmpty(e)) {
			e.put("session", MapEx.getInt(e, "id"));
		} else {
			e = resultStatusJson(e, -1, "帐号密码不正确!");
		}
		sendJsonJScript(chn, e, callBackFun);
	}

	void adminChnInfoJson(HttpRequest request, Channel chn) throws Exception {
		String strUri = request.getUri();
		Map<String, String> map = ResponseWeb.getMapByGet(strUri);
		String callBackFun = MapEx.getString(map, "callback");
		String idStr = MapEx.getString(map, "session");
		String chnStr = MapEx.getString(map, "chn");
		int svcid = MapEx.getInt(map, "svcid");
		Admin admin = AdminJson.getAdmin(idStr);
		Map mapJson = AdminJson.getInfoByChn(admin, chnStr, svcid);
		if (mapJson != null) {
			mapJson.put("session", idStr);
		} else {
			mapJson = resultStatusJson(mapJson, -1, "session过期!");
		}
		sendJsonJScript(chn, mapJson, callBackFun);
	}

	private void reloadActive(HttpRequest request, Channel chn)
			throws Exception {
		String strUri = request.getUri();
		Map<String, String> map = ResponseWeb.getMapByGet(strUri);
		String strSvcid = MapEx.getString(map, "svcid");
		int svcid = 0;
		try {
			svcid = Integer.parseInt(strSvcid);
		} catch (Exception e) {
			svcid = 0;
		}
		if (svcid == 0) {
			svcid = 1000;
		}
		String host = AppContext.GAME_WEB_HOST(svcid);
		if ("".equals(host)) {
			ResponseWeb.send(chn, "Fail:Host is null(游戏服务器地址为空)");
			return;
		}
		host += "reloadActive";
		String outStr = HttpReqWeb.sendPostStr(host, "", map);
		ResponseWeb.send(chn, outStr);
	}

	private void reloadTHall(HttpRequest request, Channel chn) throws Exception {
		String strUri = request.getUri();
		Map<String, String> map = ResponseWeb.getMapByGet(strUri);
		String strSvcid = MapEx.getString(map, "svcid");
		int svcid = 0;
		try {
			svcid = Integer.parseInt(strSvcid);
		} catch (Exception e) {
			svcid = 0;
		}
		if (svcid == 0) {
			svcid = 1000;
		}
		String host = AppContext.GAME_WEB_HOST(svcid);
		if ("".equals(host)) {
			ResponseWeb.send(chn, "Fail:Host is null(游戏服务器地址为空)");
			return;
		}
		host += "reloadTHall";
		String outStr = HttpReqWeb.sendPostStr(host, "", map);
		ResponseWeb.send(chn, outStr);
	}

	private void resetHeroAttr(HttpRequest request, Channel chn)
			throws Exception {
		String strUri = request.getUri();
		Map<String, String> map = ResponseWeb.getMapByGet(strUri);
		String strSvcid = MapEx.getString(map, "svcid");
		int svcid = 0;
		try {
			svcid = Integer.parseInt(strSvcid);
		} catch (Exception e) {
			svcid = 0;
		}
		if (svcid == 0) {
			svcid = 1000;
		}
		String host = AppContext.GAME_WEB_HOST(svcid);
		if ("".equals(host)) {
			ResponseWeb.send(chn, "Fail:Host is null(游戏服务器地址为空)");
			return;
		}
		host += "resetHeroAttr";
		String outStr = HttpReqWeb.sendPostStr(host, "", map);
		ResponseWeb.send(chn, outStr);
	}

	private void resetClanAdmin(HttpRequest request, Channel chn)
			throws Exception {
		String strUri = request.getUri();
		Map<String, String> map = ResponseWeb.getMapByGet(strUri);
		String strSvcid = MapEx.getString(map, "svcid");
		int svcid = 0;
		try {
			svcid = Integer.parseInt(strSvcid);
		} catch (Exception e) {
			svcid = 0;
		}
		if (svcid == 0) {
			svcid = 1000;
		}
		String host = AppContext.GAME_WEB_HOST(svcid);
		if ("".equals(host)) {
			ResponseWeb.send(chn, "Fail:Host is null(游戏服务器地址为空)");
			return;
		}
		host += "resetClanAdmin";
		String outStr = HttpReqWeb.sendPostStr(host, "", map);
		ResponseWeb.send(chn, outStr);
	}

	private void rebackPcid2Ucid(HttpRequest request, Channel chn)
			throws Exception {
		String strUri = request.getUri();
		Map<String, String> map = ResponseWeb.getMapByGet(strUri);
		String strSvcid = MapEx.getString(map, "svcid");
		int svcid = 0;
		try {
			svcid = Integer.parseInt(strSvcid);
		} catch (Exception e) {
			svcid = 0;
		}
		if (svcid == 0) {
			svcid = 1000;
		}
		String host = AppContext.GAME_WEB_HOST(svcid);
		if ("".equals(host)) {
			ResponseWeb.send(chn, "Fail:Host is null(游戏服务器地址为空)");
			return;
		}
		host += "rebackPcid2Ucid";
		String outStr = HttpReqWeb.sendPostStr(host, "", map);
		ResponseWeb.send(chn, outStr);
	}

	// 排行奖励
	private void reward(HttpRequest request, Channel chn) throws Exception {
		String strUri = request.getUri();
		Map<String, String> map = ResponseWeb.getMapByGet(strUri);
		String strSvcid = MapEx.getString(map, "svcid");
		int svcid = 0;
		try {
			svcid = Integer.parseInt(strSvcid);
		} catch (Exception e) {
			svcid = 0;
		}
		if (svcid == 0) {
			svcid = 1000;
		}
		String host = AppContext.GAME_WEB_HOST(svcid);
		if ("".equals(host)) {
			ResponseWeb.send(chn, "Fail:Host is null(游戏服务器地址为空)");
			return;
		}
		host += "reward";
		String outStr = HttpReqWeb.sendPostStr(host, "", map);
		ResponseWeb.send(chn, outStr);
	}
}
