package com.sea.handler.game.web;

import gen_b2g.serv.bean.ConstantType;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.handler.codec.http.HttpRequest;

import com.alibaba.fastjson.JSON;
import com.bowlong.lang.StrEx;
import com.bowlong.util.MapEx;
import com.sea.cache.jedis.admin.AdminJson;
import com.sea.cache.jedis.game.AssistUserPlayerJedis;
import com.sea.cache.jedis.game.ClanJedis;
import com.sea.cache.jedis.game.GameBaseJedis;
import com.sea.cache.process.ProHero;
import com.sea.cache.process.ProPlayer;
import com.sea.cache.process.ProTech;
import com.sea.cache.process.ProUser;
import com.sea.content.Svc;
import com.sea.db.bean.Admin;
import com.sea.db.bean.Clan;
import com.sea.db.bean.Player;
import com.sea.db.bean.Player_hero;
import com.sea.db.bean.Player_tech;
import com.sea.db.bean.User;
import com.sea.db.entity.PlayerEntity;
import com.sea.db.entity.Player_heroEntity;
import com.sea.db.entity.Player_techEntity;
import com.sea.db.entity.UserEntity;
import com.sea.handler.response.ResponseWeb;
import com.sea.json.originData.ActivitiesJson;
import com.sea.json.originData.TownHallJson;
import com.sea.logic.logicOperate.LogicClan;
import com.sea.logic.logicOperate.LogicHero;
import com.sea.logic.logicOperate.LogicPlayer;
import com.sea.logic.session.LogicalEvent;
import com.sea.logic.session.LogicalSession;
import com.sea.timer.TimerRank;
import com.sea.tools.UtileTools;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class GameHttpResonse implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(GameHttpResonse.class);

	private GameHttpResonse() {
	}

	static GameHttpResonse _self;

	public static GameHttpResonse getInstance() {
		if (_self == null)
			_self = new GameHttpResonse();

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

			switch (path) {
			case "/search_pname":
				searchPname(httpRequest, channel);
				break;
			case "/search_list_pname":
				searchListPname(httpRequest, channel);
				break;
			case "/search_pcid":
				searchPcid(httpRequest, channel);
				break;
			case "/search_login":
				searchLogin(httpRequest, channel);
				break;
			case "/change_ucid":
				changeUcidForUser(httpRequest, channel);
				break;
			case "/del_pcid":
				deleteByPcid(httpRequest, channel);
				break;
			case "/del_ucid":
				deleteByUcid(httpRequest, channel);
				break;
			case "/pstatus_pname":
				changePlayerStatusByPname(httpRequest, channel);
				break;
			case "/pstatus_pcid":
				changePlayerStatusByPcid(httpRequest, channel);
				break;
			case "/ptype_pname":
				changePlayerTypeByPname(httpRequest, channel);
				break;
			case "/ptype_pcid":
				changePlayerTypeByPcid(httpRequest, channel);
				break;
			case "/change_renown_pname":
				changePlayerRenowByPname(httpRequest, channel);
				break;
			case "/change_renown_pcid":
				changePlayerRenowByPcid(httpRequest, channel);
				break;
			case "/change_tech_pname":
				changePTechByPname(httpRequest, channel);
				break;
			case "/change_tech_pcid":
				changePTechByPcid(httpRequest, channel);
				break;
			case "/change_hero_pname":
				changePHeroByPname(httpRequest, channel);
				break;
			case "/change_hero_pcid":
				changePHeroByPcid(httpRequest, channel);
				break;
			case "/penergy_pcid":
				changePEnergyByPcid(httpRequest, channel);
				break;
			case "/reloadPInfo_pcid":
				reloadPInfo(httpRequest, channel);
				break;
			case "/sendAllReward":
				sendAllReward(httpRequest, channel);
				break;
			case "/onlineNum":
				onlineNum(channel);
				break;
			case "/handPayByPcid":
				handPayByPcid(httpRequest, channel);
				break;
			case "/lgAdmin":
				lgAdminJson(httpRequest, channel);
				break;
			case "/chnInfo":
				adminChnInfoJson(httpRequest, channel);
				break;
			case "/setOpenEvent":
				setOpenEvent(httpRequest, channel);
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
				ResponseWeb.send(channel, "request received");
				break;
			}
		} catch (Exception e) {
			String strErr = UtileTools.ex2s(e);
			log.error(strErr);
			try {
				ResponseWeb.send(channel, strErr);
			} catch (Exception e1) {
			}
		}

	}

	private void searchListPname(HttpRequest request, Channel chn)
			throws Exception {
		String outStr = "";
		Map<String, String> bodyMap = ResponseWeb
				.getMapQueryByPostBody(request);

		String strName = MapEx.getString(bodyMap, "name");
		List<String> result = AssistUserPlayerJedis.getListNamesByLike(strName);
		if (result == null || result.isEmpty()) {
			ResponseWeb.send(chn, outStr);
			return;
		}
		outStr = UtileTools.listToStr(result);
		ResponseWeb.send(chn, outStr);
	}

	void searchPlayerBack(Player p, Channel chn) throws Exception {
		Map map = new HashMap();
		Map pmap = null;
		Map umap = null;
		if (p != null) {
			pmap = p.toBasicMap();
			int ucid = p.getUcid();
			User u = UserEntity.getUserByUcid(ucid);
			if (u != null) {
				umap = u.toBasicMap();
			}
		}
		if (pmap == null)
			pmap = new HashMap();

		if (umap == null)
			umap = new HashMap();
		map.put("p", pmap);
		map.put("u", umap);
		ResponseWeb.send(chn, map);
	}

	private void searchPname(HttpRequest request, Channel chn) throws Exception {
		Map<String, String> bodyMap = ResponseWeb
				.getMapQueryByPostBody(request);
		String strName = MapEx.getString(bodyMap, "name");
		Player p = PlayerEntity.getPlayer(strName);
		searchPlayerBack(p, chn);
	}

	private void searchPcid(HttpRequest request, Channel chn) throws Exception {
		Map<String, String> bodyMap = ResponseWeb
				.getMapQueryByPostBody(request);
		String strPcid = MapEx.getString(bodyMap, "pcid");
		int pcid = 0;
		try {
			pcid = Integer.parseInt(strPcid);
		} catch (Exception e) {
		}
		Player p = PlayerEntity.getPlayer(pcid);
		searchPlayerBack(p, chn);
	}

	private void searchLogin(HttpRequest request, Channel chn) throws Exception {
		Map<String, String> bodyMap = ResponseWeb
				.getMapQueryByPostBody(request);
		String strLoginId = MapEx.getString(bodyMap, "lgid");
		String strLoginPwd = MapEx.getString(bodyMap, "lgpwd");

		List<User> ulist = UserEntity.getUsers(strLoginId, strLoginPwd);
		if (ulist == null || ulist.isEmpty()) {
			ResponseWeb.send(chn, "Fail:User is Null，用户为空");
			return;
		}
		List<Player> pllist = new ArrayList<Player>();

		StringBuffer buffer = new StringBuffer();
		for (User user : ulist) {
			String str = GameBaseJedis.getValStr(user);
			buffer.append(str).append("\n</br>");
			String pcids = user.getPcids();
			int ucid = user.getUcid();
			List<Player> ptmp = PlayerEntity.getListByUcid(pcids, ucid);
			if (ptmp != null && !ptmp.isEmpty()) {
				pllist.addAll(ptmp);
			}
		}

		for (Player player : pllist) {
			String str = GameBaseJedis.getValStr(player);
			buffer.append(str).append("\n</br>");
		}

		ResponseWeb.send(chn, buffer.toString());
	}

	private void changeUcidForUser(HttpRequest request, Channel chn)
			throws Exception {
		String outStr = "Fail";
		Map<String, String> bodyMap = ResponseWeb
				.getMapQueryByPostBody(request);
		String strLoginId = MapEx.getString(bodyMap, "lgid");
		String strLoginPwd = MapEx.getString(bodyMap, "lgpwd");

		User u = UserEntity.getUser(strLoginId, strLoginPwd);
		if (u == null) {
			ResponseWeb.send(chn, outStr);
			return;
		}

		String strUcid = MapEx.getString(bodyMap, "ucid");
		int ucid = 0;
		try {
			ucid = Integer.parseInt(strUcid);
		} catch (Exception e) {
		}

		if (ucid == u.getUcid()) {
			ResponseWeb.send(chn, outStr);
			return;
		}
		boolean isDelUP = MapEx.getBoolean(bodyMap, "isDel");
		if (isDelUP) {
			UserEntity.deleteUserPlayer(u);
		} else {
			UserEntity.deleteUserOnly(u);
		}

		u.setPcids("");
		u.setUcid(ucid);
		UserEntity.updateUser(u);
		outStr = "Success";
		ResponseWeb.send(chn, outStr);
	}

	private void deleteByPcid(HttpRequest request, Channel chn)
			throws Exception {
		Map<String, String> bodyMap = ResponseWeb
				.getMapQueryByPostBody(request);
		String strPcid = MapEx.getString(bodyMap, "pcid");
		int pcid = 0;
		try {
			pcid = Integer.parseInt(strPcid);
		} catch (Exception e) {
		}

		Player p = PlayerEntity.getPlayer(pcid);
		if (p != null) {
			boolean isDelUP = MapEx.getBoolean(bodyMap, "isDelUser");
			PlayerEntity.deletePlayer(p, isDelUP);
			ResponseWeb.send(chn, "Success");
		} else {
			ResponseWeb.send(chn, "Fails没有该用户");
		}
	}

	private void deleteByUcid(HttpRequest request, Channel chn)
			throws Exception {
		Map<String, String> bodyMap = ResponseWeb
				.getMapQueryByPostBody(request);
		String strUcid = MapEx.getString(bodyMap, "ucid");
		int ucid = 0;
		try {
			ucid = Integer.parseInt(strUcid);
		} catch (Exception e) {
		}
		PlayerEntity.deleteListByUcid("", ucid);
		ResponseWeb.send(chn, "Success");
	}

	void changePStatus(Player p, int newStatus, Channel chn) throws Exception {
		if (p != null) {
			int pstatus = p.getState();
			if (pstatus != newStatus) {
				p.setState(newStatus);
				PlayerEntity.updatePlayer(p);
				ResponseWeb.send(chn, "Success");
				return;
			}
		}
		ResponseWeb.send(chn, "Fail");
	}

	private void changePlayerStatusByPcid(HttpRequest request, Channel chn)
			throws Exception {
		Map<String, String> bodyMap = ResponseWeb
				.getMapQueryByPostBody(request);
		String strPcid = MapEx.getString(bodyMap, "pcid");
		int pcid = 0;
		try {
			pcid = Integer.parseInt(strPcid);
		} catch (Exception e) {
		}

		Player p = PlayerEntity.getPlayer(pcid);

		int status = 0;
		String strStatus = MapEx.getString(bodyMap, "status");
		try {
			status = Integer.parseInt(strStatus);
		} catch (Exception e) {
		}

		changePStatus(p, status, chn);
	}

	private void changePlayerStatusByPname(HttpRequest request, Channel chn)
			throws Exception {
		Map<String, String> bodyMap = ResponseWeb
				.getMapQueryByPostBody(request);
		String strName = MapEx.getString(bodyMap, "name");
		Player p = PlayerEntity.getPlayer(strName);

		int status = 0;
		String strStatus = MapEx.getString(bodyMap, "status");
		try {
			status = Integer.parseInt(strStatus);
		} catch (Exception e) {
		}

		changePStatus(p, status, chn);
	}

	private void sendAllReward(HttpRequest request, Channel chn)
			throws Exception {
		Map<String, String> bodyMap = ResponseWeb
				.getMapQueryByPostBody(request);
		final String strResType = MapEx.getString(bodyMap, "resType");
		boolean isCan = false;
		switch (strResType) {
		case ConstantType.Type_Res_Crystal:
		case ConstantType.Type_Res_Gold:
		case ConstantType.Type_Res_Oil:
			isCan = true;
			break;

		default:
			break;
		}
		int val = 0;
		String strVal = MapEx.getString(bodyMap, "val");
		try {
			val = Integer.parseInt(strVal);
		} catch (Exception e) {
		}
		if (!isCan || val == 0) {
			ResponseWeb.send(chn, "Fail");
			return;
		}

		List<Player> list = AssistUserPlayerJedis.getListByPcid();
		for (Player player : list) {
			LogicPlayer.addPlayerRes(player, strResType, val);
			LogicalSession.refreshPInfoByPlayer(player);
		}
		ResponseWeb.send(chn, "Success");
	}

	private void onlineNum(Channel chn) throws Exception {
		int v = LogicalSession.getNumOnline();
		ResponseWeb.send(chn, "当前在线人数:" + v);
	}

	private void handPayByPcid(HttpRequest request, Channel chn)
			throws Exception {
		Map<String, String> bodyMap = ResponseWeb
				.getMapQueryByPostBody(request);
		String strPcid = MapEx.getString(bodyMap, "pcid");
		int pcid = 0;
		try {
			pcid = Integer.parseInt(strPcid);
		} catch (Exception e) {
		}

		Player p = PlayerEntity.getPlayer(pcid);
		if (p == null) {
			ResponseWeb.send(chn, "Fails用户为空pcid:" + strPcid);
			return;
		}

		String strMoney = MapEx.getString(bodyMap, "money");
		int money = 0;
		try {
			money = Integer.parseInt(strMoney);
		} catch (Exception e) {
		}
		if (money == 0) {
			ResponseWeb.send(chn, "Fails金额为0 pcid:" + strPcid);
			return;
		}

		boolean isMonth = MapEx.getBoolean(bodyMap, "isMonth");
		if (isMonth) {
			LogicPlayer.handlePayMoneyBuyMonCode(p, money);
		} else {
			String strGems = MapEx.getString(bodyMap, "gems");
			int buyCryVal = 0;
			try {
				buyCryVal = Integer.parseInt(strGems);
			} catch (Exception e) {
			}
			LogicPlayer.handlePayMoney(p, money, buyCryVal);
		}

		// ========== 处理 中秋节活动
		LogicPlayer.handlePayActivity(p, money);
		LogicPlayer.handlePayHuodong(p, money);

		ResponseWeb.send(chn, "Success");
	}

	void changePType(Player p, int type, Channel chn) throws Exception {
		if (p != null) {
			int ptype = p.getType();
			if (ptype != type) {
				p.setType(type);
				PlayerEntity.updatePlayer(p);
				ResponseWeb.send(chn, "Success");
				return;
			}
		}
		ResponseWeb.send(chn, "Fail");
	}

	private void changePlayerTypeByPcid(HttpRequest request, Channel chn)
			throws Exception {
		Map<String, String> bodyMap = ResponseWeb
				.getMapQueryByPostBody(request);
		String strPcid = MapEx.getString(bodyMap, "pcid");
		int pcid = 0;
		try {
			pcid = Integer.parseInt(strPcid);
		} catch (Exception e) {
		}

		Player p = PlayerEntity.getPlayer(pcid);

		int type = 0;
		String strStatus = MapEx.getString(bodyMap, "type");
		try {
			type = Integer.parseInt(strStatus);
		} catch (Exception e) {
		}

		changePType(p, type, chn);
	}

	private void changePlayerTypeByPname(HttpRequest request, Channel chn)
			throws Exception {
		Map<String, String> bodyMap = ResponseWeb
				.getMapQueryByPostBody(request);
		String strName = MapEx.getString(bodyMap, "name");
		Player p = PlayerEntity.getPlayer(strName);
		int type = 0;
		String strStatus = MapEx.getString(bodyMap, "type");
		try {
			type = Integer.parseInt(strStatus);
		} catch (Exception e) {
		}

		changePType(p, type, chn);
	}

	void changePRenown(Player p, int renown, Channel chn) throws Exception {
		if (p != null) {
			int ptype = p.getRenown();
			int diff = ptype - renown;
			if (diff != 0) {
				p.setRenown(renown);
				int w = p.getWeekrenown();
				p.setWeekrenown(w - diff);

				PlayerEntity.updatePlayer(p);
				ResponseWeb.send(chn, "Success");
				return;
			}
		}

		ResponseWeb.send(chn, "Fail");
	}

	private void changePlayerRenowByPcid(HttpRequest request, Channel chn)
			throws Exception {
		Map<String, String> bodyMap = ResponseWeb
				.getMapQueryByPostBody(request);
		String strPcid = MapEx.getString(bodyMap, "pcid");
		int pcid = 0;
		try {
			pcid = Integer.parseInt(strPcid);
		} catch (Exception e) {
		}

		Player p = PlayerEntity.getPlayer(pcid);

		int val = 0;
		String strStatus = MapEx.getString(bodyMap, "renown");
		try {
			val = Integer.parseInt(strStatus);
		} catch (Exception e) {
		}

		changePRenown(p, val, chn);
	}

	private void changePlayerRenowByPname(HttpRequest request, Channel chn)
			throws Exception {
		Map<String, String> bodyMap = ResponseWeb
				.getMapQueryByPostBody(request);
		String strName = MapEx.getString(bodyMap, "name");
		Player p = PlayerEntity.getPlayer(strName);
		int val = 0;
		String strStatus = MapEx.getString(bodyMap, "renown");
		try {
			val = Integer.parseInt(strStatus);
		} catch (Exception e) {
		}

		changePRenown(p, val, chn);
	}

	void changePTech(Player_tech tech, int lvl, Channel chn) throws Exception {
		if (tech != null) {
			int curlvl = tech.getLvl();
			int diff = curlvl - lvl;
			if (diff != 0 && lvl >= 1) {
				tech.setLvl(lvl);
				Player_techEntity.updateTech(tech);
				ProTech.saveData();
				ResponseWeb.send(chn, "Success");
				return;
			}
		}

		ResponseWeb.send(chn, "Fail");
	}

	private void changePTechByPcid(HttpRequest request, Channel chn)
			throws Exception {
		Map<String, String> bodyMap = ResponseWeb
				.getMapQueryByPostBody(request);
		String strPcid = MapEx.getString(bodyMap, "pcid");
		int pcid = 0;
		try {
			pcid = Integer.parseInt(strPcid);
		} catch (Exception e) {
		}

		String strtgid = MapEx.getString(bodyMap, "tgid");
		int tgid = 0;
		try {
			tgid = Integer.parseInt(strtgid);
		} catch (Exception e) {
		}

		Player_tech tech = Player_techEntity.getPlayerTech(pcid, tgid);

		int val = 0;
		String strStatus = MapEx.getString(bodyMap, "lvl");
		try {
			val = Integer.parseInt(strStatus);
		} catch (Exception e) {
		}

		changePTech(tech, val, chn);
	}

	private void changePTechByPname(HttpRequest request, Channel chn)
			throws Exception {
		Map<String, String> bodyMap = ResponseWeb
				.getMapQueryByPostBody(request);
		String strName = MapEx.getString(bodyMap, "name");
		Player p = PlayerEntity.getPlayer(strName);

		String strtgid = MapEx.getString(bodyMap, "tgid");
		int tgid = 0;
		try {
			tgid = Integer.parseInt(strtgid);
		} catch (Exception e) {
		}

		Player_tech tech = Player_techEntity.getPlayerTech(p, tgid);

		int val = 0;
		String strStatus = MapEx.getString(bodyMap, "lvl");
		try {
			val = Integer.parseInt(strStatus);
		} catch (Exception e) {
		}

		changePTech(tech, val, chn);
	}

	void changePHero(Player_hero hero, int spd, int dam, int nhp, Channel chn)
			throws Exception {
		if (hero != null) {
			boolean isChange = false;
			if (spd >= 0) {
				int speed = hero.getAddattspeed();
				if (speed != spd) {
					hero.setAddattspeed(spd);
					isChange = true;
				}
			}

			if (dam >= 0) {
				int damage = hero.getAdddamage();
				if (dam != damage) {
					hero.setAdddamage(dam);
					isChange = true;
				}
			}

			if (nhp >= 0) {
				int hp = hero.getAddhp();
				if (hp != nhp) {
					hero.setAddhp(nhp);
					isChange = true;
				}
			}

			if (isChange) {
				Player_heroEntity.updateHero(hero);
				ProHero.saveData();
				ResponseWeb.send(chn, "Success");
				return;
			}
		}

		ResponseWeb.send(chn, "Fail");
	}

	private void changePHeroByPcid(HttpRequest request, Channel chn)
			throws Exception {
		Map<String, String> bodyMap = ResponseWeb
				.getMapQueryByPostBody(request);
		String strPcid = MapEx.getString(bodyMap, "pcid");
		int pcid = 0;
		try {
			pcid = Integer.parseInt(strPcid);
		} catch (Exception e) {
		}

		String strtgid = MapEx.getString(bodyMap, "hgid");
		int hgid = 0;
		try {
			hgid = Integer.parseInt(strtgid);
		} catch (Exception e) {
		}

		Player_hero hero = Player_heroEntity.getHeroBy(pcid, hgid);

		String strval = "";
		int speed = 0;
		strval = MapEx.getString(bodyMap, "speed");
		try {
			speed = Integer.parseInt(strval);
		} catch (Exception e) {
			speed = -1;
		}

		int dam = 0;
		strval = MapEx.getString(bodyMap, "damage");
		try {
			dam = Integer.parseInt(strval);
		} catch (Exception e) {
			dam = -1;
		}

		int hp = 0;
		strval = MapEx.getString(bodyMap, "hp");
		try {
			hp = Integer.parseInt(strval);
		} catch (Exception e) {
			hp = -1;
		}

		changePHero(hero, speed, dam, hp, chn);
	}

	private void changePHeroByPname(HttpRequest request, Channel chn)
			throws Exception {
		Map<String, String> bodyMap = ResponseWeb
				.getMapQueryByPostBody(request);
		String strName = MapEx.getString(bodyMap, "name");
		Player p = PlayerEntity.getPlayer(strName);

		if (p == null) {
			changePHero(null, 0, 0, 0, chn);
			return;
		}
		String strtgid = MapEx.getString(bodyMap, "hgid");
		int hgid = 0;
		try {
			hgid = Integer.parseInt(strtgid);
		} catch (Exception e) {
		}

		Player_hero hero = Player_heroEntity.getHeroBy(p.getPcid(), hgid);

		String strval = "";
		int speed = 0;
		strval = MapEx.getString(bodyMap, "speed");
		try {
			speed = Integer.parseInt(strval);
		} catch (Exception e) {
			speed = -1;
		}

		int dam = 0;
		strval = MapEx.getString(bodyMap, "damage");
		try {
			dam = Integer.parseInt(strval);
		} catch (Exception e) {
			dam = -1;
		}

		int hp = 0;
		strval = MapEx.getString(bodyMap, "hp");
		try {
			hp = Integer.parseInt(strval);
		} catch (Exception e) {
			hp = -1;
		}

		changePHero(hero, speed, dam, hp, chn);
	}

	void changePEnergy(Player p, int addHP, int addDam, int addSpeed,
			Channel chn) throws Exception {
		if (p != null) {
			if (addHP == 0 && addDam == 0 && addSpeed == 0) {
				ResponseWeb.send(chn, "Fail:所增加数量均为0");
				return;
			}
			LogicPlayer.changeEnergyNum(p, addHP, addDam, addSpeed);
			ResponseWeb.send(chn, "Success");
			return;
		}
		ResponseWeb.send(chn, "Fail");
	}

	private void changePEnergyByPcid(HttpRequest request, Channel chn)
			throws Exception {
		Map<String, String> bodyMap = ResponseWeb
				.getMapQueryByPostBody(request);
		String strPcid = MapEx.getString(bodyMap, "pcid");
		int pcid = 0;
		try {
			pcid = Integer.parseInt(strPcid);
		} catch (Exception e) {
		}

		Player p = PlayerEntity.getPlayer(pcid);

		int addHP = 0;
		int addDam = 0;
		int addSpeed = 0;
		String str = "";
		try {
			str = MapEx.getString(bodyMap, "addHP");
			addHP = Integer.parseInt(str);

			str = MapEx.getString(bodyMap, "addDam");
			addDam = Integer.parseInt(str);

			str = MapEx.getString(bodyMap, "addSpeed");
			addSpeed = Integer.parseInt(str);
		} catch (Exception e) {
		}

		changePEnergy(p, addHP, addDam, addSpeed, chn);
	}

	void reloadPInfo(Player p, Channel chn) throws Exception {
		if (p != null) {
			LogicPlayer.reloadPInfo(p);
			ResponseWeb.send(chn, "Success");
			return;
		}
		ResponseWeb.send(chn, "Fail");
	}

	private void reloadPInfo(HttpRequest request, Channel chn) throws Exception {
		Map<String, String> bodyMap = ResponseWeb
				.getMapQueryByPostBody(request);
		String strPcid = MapEx.getString(bodyMap, "pcid");
		int pcid = 0;
		try {
			pcid = Integer.parseInt(strPcid);
		} catch (Exception e) {
		}

		Player p = PlayerEntity.getPlayer(pcid);
		reloadPInfo(p, chn);
	}

	// === 后台管理 json数据
	Map resultStatusJson(Map res, int status, String tip) {
		if (res == null)
			res = new HashMap();
		res.put("status", status);
		res.put("tip", tip);
		return res;
	}

	void sendJson(Channel chn, Map map, String callBackFun) throws Exception {
		String json = "";
		if ("".equals(callBackFun)) {
			ResponseWeb.send(chn, "error");
			return;
		}
		if (map != null && !map.isEmpty()) {
			json = JSON.toJSONString(map);
		} else {
			json = JSON.toJSONString(resultStatusJson(null, -1, "map为空，错误"));
		}
		ResponseWeb.sendJson(chn, callBackFun + "(" + json + ")");
	}

	void lgAdminJson(HttpRequest request, Channel chn) throws Exception {
		String strUri = request.getUri();
		Map<String, String> map = ResponseWeb.getMapByGet(strUri);
		String callBackFun = MapEx.getString(map, "callback");
		String lgId = MapEx.getString(map, "lgid");
		String lgpwd = MapEx.getString(map, "lgpwd");
		Map mapJson = new HashMap();
		Admin admin = AdminJson.getAdmin(lgId, lgpwd);
		if (admin != null) {
			mapJson.put("session", admin.getId());
			mapJson.put("name", admin.getName());
		} else {
			mapJson = resultStatusJson(mapJson, -1, "帐号密码不正确!");
		}
		sendJson(chn, mapJson, callBackFun);
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
		sendJson(chn, mapJson, callBackFun);
	}

	void setOpenEvent(HttpRequest request, Channel chn) throws Exception {
		Map<String, String> map = ResponseWeb.getMapQueryByPostBody(request);
		boolean isOpen = MapEx.getBoolean(map, "isOpen");
		String edtimeStr = MapEx.getString(map, "edtimeStr");
		LogicalEvent.isOpenEvent = isOpen;
		if (!StrEx.isEmpty(edtimeStr))
			LogicalEvent.endTimeStr = edtimeStr;
		int testPcid = MapEx.getInt(map, "testPcid");
		if (testPcid > 0)
			LogicalEvent.testPcid = testPcid;

		ResponseWeb.send(chn, "okey:isopen:" + isOpen + ",endtime:" + edtimeStr
				+ ",testpcid:" + testPcid);
	}

	void reloadActive(HttpRequest request, Channel chn) throws Exception {
		ActivitiesJson.reloadMapData();
		ResponseWeb.send(chn, "okey,reloadActive Now!!!");
	}

	void reloadTHall(HttpRequest request, Channel chn) throws Exception {
		TownHallJson.reloadTHall();
		ResponseWeb.send(chn, "okey,reloadTHall Now!!!");
	}

	void resetHeroAttr(HttpRequest request, Channel chn) throws Exception {
		Map<String, String> bodyMap = ResponseWeb
				.getMapQueryByPostBody(request);
		String strPcid = MapEx.getString(bodyMap, "pcid");
		String strPname = MapEx.getString(bodyMap, "name");
		Player p = null;
		if (!Svc.isEmpty(strPcid)) {
			int pcid = 0;
			try {
				pcid = Integer.parseInt(strPcid);
			} catch (Exception e) {
			}
			p = PlayerEntity.getPlayer(pcid);
		}
		if (p == null) {
			if (!Svc.isEmpty(strPname)) {
				p = PlayerEntity.getPlayer(strPname);
			}
		}

		String info = UtileTools.mapToStr(bodyMap);
		if (p != null) {
			LogicHero.refreshHerosMax(p);
			ResponseWeb.send(chn, "okey,resetHeroAttr Now!!!" + info);
		} else {
			ResponseWeb.send(chn, "fail,player is null!" + info);
		}
	}

	static void resetClanAdmin(HttpRequest request, Channel chn)
			throws Exception {
		Map<String, String> bodyMap = ResponseWeb
				.getMapQueryByPostBody(request);
		String strClanId = MapEx.getString(bodyMap, "ccid");
		Clan c = ClanJedis.getClanByCcid(strClanId);
		if (c == null) {
			ResponseWeb
					.send(chn, "fail,clan is null! clan_ccid = " + strClanId);
			return;
		}

		String strPcid = MapEx.getString(bodyMap, "pcid");
		String strPname = MapEx.getString(bodyMap, "name");

		Player p = null;
		if (!Svc.isEmpty(strPcid)) {
			int pcid = 0;
			try {
				pcid = Integer.parseInt(strPcid);
			} catch (Exception e) {
			}
			p = PlayerEntity.getPlayer(pcid);
		}
		if (p == null) {
			if (!Svc.isEmpty(strPname)) {
				p = PlayerEntity.getPlayer(strPname);
			}
		}

		if (p == null) {
			ResponseWeb.send(chn, "fail,player is null! play_pcid = " + strPcid
					+ ",pname = " + strPname);
			return;
		}

		String ccid = p.getClancid();
		if (ccid.equals(strClanId)) {
			ResponseWeb.send(chn, "fail,clan is same! 联盟相同不能转换 ");
		} else {
			LogicClan.exitClan(p);
			int v = LogicClan.createClanMember(p, strClanId,
					ConstantType.Type_ClanMember_Admin);
			ResponseWeb.send(chn, "操作状态值 =" + v);
		}
	}

	static void rebackPcid2Ucid(HttpRequest request, Channel chn)
			throws Exception {
		Map<String, String> bodyMap = ResponseWeb
				.getMapQueryByPostBody(request);

		String strUcid = MapEx.getString(bodyMap, "ucid");
		User u = null;
		int ucid = 0;
		if (!Svc.isEmpty(strUcid)) {
			try {
				ucid = Integer.parseInt(strUcid);
			} catch (Exception e) {
			}
			u = ProUser.getUserByUcid(ucid);
			if (u == null) {
				ResponseWeb.send(chn, "fail,user is null! ucid = " + strUcid);
				return;
			}
		}

		String strPcid = MapEx.getString(bodyMap, "pcid");
		Player p = null;
		if (!Svc.isEmpty(strPcid)) {
			int pcid = 0;
			try {
				pcid = Integer.parseInt(strPcid);
			} catch (Exception e) {
			}
			p = PlayerEntity.getPlayer(pcid);
			if (p == null) {
				ResponseWeb.send(chn, "fail,player is null! play_pcid = "
						+ strPcid);
				return;
			}
		}

		int pucid = p.getUcid();
		if (pucid != ucid) {
			User puser = ProUser.getUserByUcid(pucid);
			if (puser != null) {
				boolean isDelUser = MapEx.getBoolean(bodyMap, "isDelUser");
				if (isDelUser) {
					ProUser.delUser(puser);
				} else {
					puser.setPcids("");
					ProUser.upUser(puser);
				}
			}
		}

		u.setPcids(strPcid);

		String strChn = MapEx.getString(bodyMap, "chn");
		if (!Svc.isEmpty(strChn)) {
			p.setChannel(strChn);
		}
		p.setUcid(ucid);
		ProPlayer.upPlayer(p);
		ResponseWeb.send(chn, "Success,ucid:" + ucid + ",pcid:" + strPcid);
	}

	static void reward(HttpRequest request, Channel chn) throws Exception {
		Map<String, String> bodyMap = ResponseWeb
				.getMapQueryByPostBody(request);
		boolean isNewReward = MapEx.getBoolean(bodyMap, "isNewReward");
		TimerRank.sendReward(new Date(), isNewReward);
		ResponseWeb.send(chn, "Success,Send Reward");
	}
}
