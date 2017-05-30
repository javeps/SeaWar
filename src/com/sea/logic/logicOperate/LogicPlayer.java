package com.sea.logic.logicOperate;

import gen_b2g.serv.bean.ConstantType;
import gen_b2g.serv.bean.NHeros;
import gen_b2g.serv.bean.NNPCBeFighted;
import gen_b2g.serv.bean.NNPCBeFighteds;
import gen_b2g.serv.bean.NPInfo;
import gen_b2g.serv.bean.NPlayer;
import gen_b2g.serv.bean.NPlayers;
import gen_b2g.serv.bean.NSpell;
import gen_b2g.serv.bean.NSpells;
import gen_b2g.serv.bean.ReturnStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.json.JSON;
import com.bowlong.lang.StrEx;
import com.bowlong.util.DateEx;
import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.NewMap;
import com.sea.cache.jedis.game.AssistUserPlayerJedis;
import com.sea.cache.jedis.game.PlayerJedis;
import com.sea.cache.jedis.game._PInfoJedis;
import com.sea.cache.process.ProPlayer;
import com.sea.channel.ChannelCfg;
import com.sea.content.Svc;
import com.sea.db.bean.Clan_member;
import com.sea.db.bean.Player;
import com.sea.db.bean.User;
import com.sea.db.entity.Clan_memberEntity;
import com.sea.db.entity.PlayerEntity;
import com.sea.db.entity.UserEntity;
import com.sea.handler.game.tcpGame.GameTcpHandle;
import com.sea.handler.game.tcpGame.GameTcpRepsonse;
import com.sea.json.init.JsonImportInit;
import com.sea.json.originData.ActivitiesJson;
import com.sea.json.originData.EnergyJson;
import com.sea.json.originData.LanguageJson;
import com.sea.json.originData.TownHallJson;
import com.sea.logic.BackToClientLogicHandle;
import com.sea.logic.session.LogicalEvent;
import com.sea.logic.session.LogicalSession;
import com.sea.logic.session.Session;
import com.sea.tools.FormulaGame;
import com.sea.tools.FormulaPay;
import com.sea.tools.NameRandom;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class LogicPlayer implements Serializable {

	static Log log = LogFactory.getLog(LogicPlayer.class);

	private static final long serialVersionUID = 1L;

	/*** 根据用户对象取得用户的操作对象 ***/
	static public Session getLeWrap(Player p) {
		if (p == null)
			return null;
		int pcid = p.getPcid();
		return LogicalSession.getSessionByPcid(pcid);
	}

	// 取得resType类型资源的值
	public static int getCurResVal(Player p, String resType) {
		int r2 = 0;
		if (p == null)
			return r2;
		switch (resType) {
		case ConstantType.Type_Res_Crystal:
			r2 = p.getCrystal();
			break;
		case ConstantType.Type_Res_Gold:
			r2 = p.getStored_gold();
			break;
		case ConstantType.Type_Res_Oil:
			r2 = p.getStored_oil();
			break;
		default:
			break;
		}
		return r2;
	}

	// 判断resType类型资源是否足够
	public static boolean isEnoughRes(Player p, String resType, long val) {
		if (val < 0 || p == null)
			return false;
		long cur_val = getCurResVal(p, resType);
		return cur_val >= val;
	}

	// 改变用户资源
	public static Player changeRes(Player p, String resType, int val,
			boolean isCanAddGems) {
		if (p == null)
			return p;
		if (val == 0)
			return p;
		int cur_val = 0;
		int pre_val = 0;
		boolean ischange = false;
		switch (resType) {
		case ConstantType.Type_Res_Crystal:
			if (isCanAddGems || val < 0) {
				pre_val = p.getCrystal();
				cur_val = pre_val + val;
				if (cur_val < 0)
					cur_val = 0;
				p.setCrystal(cur_val);
				ischange = cur_val != pre_val;
			}
			break;
		case ConstantType.Type_Res_Gold:
			pre_val = p.getStored_gold();
			cur_val = pre_val + val;
			if (cur_val < 0)
				cur_val = 0;
			p.setStored_gold(cur_val);
			ischange = cur_val != pre_val;
			break;
		case ConstantType.Type_Res_Oil:
			pre_val = p.getStored_oil();
			cur_val = pre_val + val;
			if (cur_val < 0)
				cur_val = 0;
			p.setStored_oil(cur_val);
			ischange = cur_val != pre_val;
			break;
		default:
			break;
		}
		if (ischange) {
			PlayerEntity.updatePlayer(p);
		}
		return p;
	}

	public static Player changeResByName(String pname, String resType, int val) {
		Player p = PlayerEntity.getPlayer(pname);
		p = changeRes(p, resType, val, true);
		LogicalSession.refreshPInfoByPlayer(p);
		return p;
	}

	public static Player changeResByPcid(int pcid, String resType, int val) {
		Player p = PlayerEntity.getPlayer(pcid);
		p = changeRes(p, resType, val, true);
		LogicalSession.refreshPInfoByPlayer(p);
		return p;
	}

	// 增加角色的资源
	public static Player addPlayerRes(Player p, String resType, int val) {
		if (val <= 0)
			return p;
		return changeRes(p, resType, val, false);
	}

	// 减少角色的资源
	public static Player reducePlayerRes(Player p, String resType, int val) {
		if (val == 0)
			return p;
		val = Math.abs(val);
		val = -val;
		return changeRes(p, resType, val, false);
	}

	// 清空角色的resType资源
	public static boolean clearCurResVal(Player p, String resType) {
		boolean r2 = false;
		if (p == null)
			return r2;
		int cur_val = getCurResVal(p, resType);
		if (cur_val > 0) {
			reducePlayerRes(p, resType, cur_val);
		}
		r2 = true;
		return r2;
	}

	// 增加金币，增加油
	public static void addGoidAndOil(Player p, int addGoid, int addOil) {
		if (p == null)
			return;
		if (addGoid <= 0 && addOil <= 0)
			return;
		int cur_gold_val = p.getStored_gold();
		int cur_oil_val = p.getStored_oil();
		cur_gold_val += addGoid;
		cur_oil_val += addOil;
		p.setStored_gold(cur_gold_val);
		p.setStored_oil(cur_oil_val);
		PlayerEntity.updatePlayer(p);
	}

	// 记录战斗结束攻击者的金币，油，声望改变
	public static void recordFightPInfoForAtt(Player p, int addGoid,
			int addOil, int maxGold, int maxOil, int addRenow) {
		if (p == null)
			return;
		int cur_gold_val = p.getStored_gold();
		int cur_oil_val = p.getStored_oil();
		int diffGold = maxGold - cur_gold_val;
		int diffOil = maxOil - cur_oil_val;
		if (diffGold > 0) {
			addGoid = diffGold > addGoid ? addGoid : diffGold;
		} else {
			addGoid = 0;
		}

		if (diffOil > 0) {
			addOil = diffOil > addOil ? addOil : diffOil;
		} else {
			addOil = 0;
		}
		recordFightPInfo(p, addGoid, addOil, addRenow);
	}

	// 记录战斗结束的金币，油，声望改变
	public static void recordFightPInfo(Player p, int addGoid, int addOil,
			int addRenow) {
		if (p == null)
			return;
		int cur_gold_val = p.getStored_gold();
		int cur_oil_val = p.getStored_oil();
		int cur_renown = p.getRenown();
		int week_renown = p.getWeekrenown();

		cur_gold_val += addGoid;
		cur_oil_val += addOil;
		cur_renown += addRenow;
		week_renown += addRenow;

		if (cur_gold_val <= 0)
			cur_gold_val = 0;
		if (cur_renown <= 0)
			cur_renown = 0;
		if (cur_oil_val <= 0)
			cur_oil_val = 0;

		p.setStored_gold(cur_gold_val);
		p.setStored_oil(cur_oil_val);
		p.setRenown(cur_renown);
		p.setWeekrenown(week_renown);

		PlayerEntity.updatePlayer(p);

		// 更新联盟成员数据
		String ccid = p.getClancid();
		if (ccid != null && !"".equals(ccid.trim())) {
			int pcid = p.getPcid();
			String pname = p.getPname();
			Clan_member m = Clan_memberEntity.getClanMember(ccid, pcid);
			if (m != null) {
				m.setPcid(pcid);
				m.setPname(pname);
				m.setRenownall(cur_renown);
				m.setRenownweek(week_renown);
				Clan_memberEntity.updateClanMember(m);
			}
		}
	}

	// 登录时候，改变用户的状态
	public static void changeStatusWhenLogin(Player p) {
		if (p == null || isNpc(p))
			return;
		p.setIsonline(true);
		changeLoginInfo(p);
		resetPayActivity(p);
	}

	// 更新角色的状态(在线,日常任务)
	public static void changeLoginInfo(Player p) {
		if (p == null || isNpc(p))
			return;

		Date now_day = new Date();
		long now_time = now_day.getTime();

		long lastLoginTime = p.getLastlogintime();
		Date last_day = new Date(lastLoginTime);

		int dlog = DateEx.day(last_day);
		int dnow = DateEx.day(now_day);
		int mlog = DateEx.month(last_day);
		int mnow = DateEx.month(now_day);
		boolean isCan = false;
		int daycout = 1;
		if (mlog == mnow) {
			int ddiff = dnow - dlog;
			isCan = ddiff > 0;
			if (isCan) {
				if (ddiff == 1) {
					daycout = p.getLoginday();
					daycout++;
				}
				boolean isRewardDay = p.getIsrewardday();
				if (!isRewardDay) {
					p.setIsrewardday(true);
				}
			}
		} else {
			isCan = true;
			int year = DateEx.year(last_day);
			int maxLogDays = DateEx.dayNum(year, mlog);
			if (maxLogDays == dlog && dnow == 1) {
				daycout = p.getLoginday();
				daycout++;
			}
			p.setIsrewardday(true);
		}
		if (isCan) {
			p.setLoginday(daycout);
			p.setDaytasks("");

			// 每日的月卡
			boolean isMonCode = p.getIsmoncode();
			if (isMonCode) {
				p.setIsmoncode(false);
			}
		}
		p.setLastlogintime(now_time);
		PlayerEntity.updatePlayer(p);
	}

	// 更新角色每日奖励领取的状态(领取了)
	public static void changeLoginRewardStatus(Player p) {
		if (p == null || isNpc(p))
			return;
		boolean isRD = p.getIsrewardday();
		if (isRD) {
			p.setIsrewardday(false);
			PlayerEntity.updatePlayer(p);
		}
	}

	// 玩家下线
	public static boolean downLine(Player p) {
		boolean r2 = false;
		if (p == null)
			return r2;
		boolean isOnline = p.getIsonline();
		if (isOnline) {
			p.setIsonline(false);
			PlayerEntity.updatePlayer(p);
			r2 = true;
		}
		return r2;
	}

	// 更新角色的状态(保护时间)
	public static Player upPTime(Player p, long addTime) {
		if (p == null || addTime < 0 || isNpc(p))
			return p;
		long cur_p = p.getProtecttime();
		long now_time = System.currentTimeMillis();
		boolean isUp = false;
		if (addTime > 0) {
			if (now_time > cur_p) {
				addTime += now_time;
			} else {
				addTime += cur_p;
			}
			isUp = true;
		} else {
			isUp = cur_p != addTime;
		}
		if (isUp) {
			p.setProtecttime(addTime);
			PlayerEntity.updatePlayer(p);
		}
		return p;
	}

	// 增加用户的保护时间
	public static boolean changePTime(Player p, long addTime) {
		boolean r = false;
		if (p != null && addTime > 0) {
			long now_time = System.currentTimeMillis();
			long cur_p = p.getProtecttime();
			long max = now_time + DateEx.TIME_DAY;
			if (cur_p > now_time) {
				long diff = max - cur_p;
				if (diff > 0) {
					addTime = diff - addTime > 0 ? addTime : diff;
				} else {
					addTime = 0;
				}
			}
			if (addTime > 0) {
				upPTime(p, addTime);
				r = true;
			}
		}
		return r;
	}

	// 更新角色的新手步驟
	public static void upGuid(Player p, int guid) {
		if (p == null)
			return;
		int cur_guid = p.getGuid_step();
		if (cur_guid != guid) {
			p.setGuid_step(guid);
			PlayerEntity.updatePlayer(p);
		}
	}

	// 更新角色的联盟唯一标识
	public static void upClanCid(int pcid, String ccid, int post, String cname,
			int cicon) {
		Player p = PlayerEntity.getPlayer(pcid);
		upClanCid(p, ccid, post, cname, cicon);
	}

	public static void upClanCid(Player p, String ccid, int post, String cname,
			int cicon) {
		if (p == null)
			return;
		if (ccid == null)
			ccid = "";
		boolean isHasClan = "".equals(ccid.trim());
		if (isHasClan) {
			long lastLeaveClan = System.currentTimeMillis();
			p.setLastleaveclan(lastLeaveClan);
		}
		p.setClancid(ccid);
		p.setClanpost(post);
		p.setCicon(cicon);
		p.setCname(cname);
		PlayerEntity.updatePlayer(p);
	}

	// 随机名称不重复的名称
	public static boolean isHasName(String uname) {
		boolean isHas = false;
		isHas = PlayerEntity.isHasName(uname);
		return isHas;
	}

	public static String randomName(String strChn) {
		String r2 = "";
		int count = 0;
		while (true) {
			r2 = NameRandom.getInstance().newName(strChn);
			boolean isHas = isHasName(r2);
			if (count > 10000 || !isHas)
				break;
			count++;
		}
		return r2;
	}

	// 设置名称
	public static boolean rename(Player p, String uname) {
		boolean r2 = false;
		if (p == null)
			return r2;
		String _name = p.getPname();
		if (_name.equals(uname))
			return r2;

		boolean isHas = isHasName(uname);
		if (isHas)
			return r2;

		try {
			AssistUserPlayerJedis.deleteUPname(p);
			p.setPname(uname);
			PlayerEntity.updatePlayer(p);
			PlayerJedis.changeOtherPName(p);
			AssistUserPlayerJedis.setUPVal(p);
			r2 = true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info(uname + "===名字重复!");
		}
		return r2;
	}

	static int workerGems(int num) {
		switch (num) {
		case 1:
			return 0;
		case 2:
			return 250;
		case 3:
			return 500;
		case 4:
			return 1000;
		default:
			return 2000;
		}
	}

	// 购买了建筑序列
	public static boolean buyBuildOrder(Player p, int gems) {
		if (gems < 500)
			return false;
		int curNum = p.getMaxbonum();
		int needGems = workerGems(curNum + 1);
		if (gems != needGems)
			return false;

		boolean r2 = changeWokerNum(p, 1);
		return r2;
	}

	public static int changeWokerNumNotUp(Player p, int num) {
		if (p == null)
			return 2;

		int curnum = p.getMaxbonum();
		if (num == 0)
			return curnum;

		curnum += num;

		if (curnum > 5)
			curnum = 5;
		else if (curnum < 2)
			curnum = 2;

		return curnum;
	}

	private static boolean changeWokerNum(Player p, int num) {
		boolean r2 = false;
		if (p == null)
			return r2;
		int prenum = p.getMaxbonum();
		int curnum = changeWokerNumNotUp(p, num);
		r2 = curnum != prenum;
		if (r2) {
			p.setMaxbonum(curnum);
			PlayerEntity.updatePlayer(p);
		}
		return r2;
	}

	// 判断建筑工人是否足够
	public static boolean isEnoughBuildWorker(Player p) {
		boolean r = false;
		if (p == null)
			return r;
		int cur = p.getCurbonum();
		int max = p.getMaxbonum();
		r = max > cur;
		return r;
	}

	public static void changeWorkerCurNum(Player p, int num) {
		if (num == 0)
			return;
		if (p == null)
			return;
		int cur = p.getCurbonum();
		cur += num;
		if (cur < 0)
			return;

		if (num > 0) {
			int max = p.getMaxbonum();
			if (cur > max)
				return;
		}

		p.setCurbonum(cur);
		PlayerEntity.updatePlayer(p);
	}

	// 改变玩家打NPC的信息
	public static boolean isChangeNpcs(Player p, int npc_loc_id,
			NNPCBeFighteds npcs) {
		boolean r2 = false;
		if (p == null)
			return r2;

		int cur_npc_id = p.getCur_npc();
		if (npc_loc_id > cur_npc_id) {
			p.setCur_npc(npc_loc_id);
		}
		String npcStr = "";
		if (npcs != null)
			npcStr = toStrNNPCs(npcs.lists);
		p.setNpcs(npcStr);
		PlayerEntity.updatePlayer(p);
		r2 = true;
		return r2;
	}

	public static boolean isHasSpell(Player p, int gid) {
		boolean r = true;
		if (p == null)
			return r;
		int htlvl = p.getCurtownlvl();
		int maxNum = TownHallJson.getNumByTHLvlGid(htlvl, gid);
		if (maxNum == 0)
			return r;
		String strInfo = p.getSpells();
		List<NSpell> origin = toListNSpell(strInfo);
		NSpell spell = getNSpellBy(origin, gid);
		if (spell != null) {
			r = spell.num >= maxNum;
		} else {
			r = false;
		}
		return r;
	}

	// 增加药水
	public static void addPlayerSpell(Player p, int gid) {
		int num = 1;
		addPlayerSpell(p, gid, num);
	}

	// 增加药水
	public static void addPlayerSpell(Player p, int gid, int num) {
		if (num <= 0)
			return;
		if (p != null) {
			String sp = toStrSpells(p.getSpells(), gid, num);
			p.setSpells(sp);
			PlayerEntity.updatePlayer(p);
		}
	}

	// 减少药水
	public static void reducePlayerSpell(Player p, int gid, int num) {
		if (p != null) {
			if (num > 0)
				num = -num;
			String sp = toStrSpells(p.getSpells(), gid, num);
			p.setSpells(sp);
			PlayerEntity.updatePlayer(p);
		}
	}

	// 更新用户的最后一次增加障碍时间
	public static void changeLastAddObst(Player p) {
		if (p == null)
			return;

		long now_time = System.currentTimeMillis();
		p.setLastaddobst(now_time);
		PlayerEntity.updatePlayer(p);
	}

	// 更新用户的成就信息
	public static void changeMarkInfo(Player p, String markInfo) {
		if (p == null || markInfo == null || "".equals(markInfo.trim()))
			return;
		p.setMark(markInfo);
		PlayerEntity.updatePlayer(p);
	}

	// 改变用户的最大战报ID
	public static void changeMaxAttCID(Player p, int attcid) {
		if (p == null || attcid <= 0)
			return;
		p.setMaxattmcid(attcid);
		PlayerEntity.updatePlayer(p);
	}

	// 改变个人主基地等级
	public static void changeTownLvl(Player p, int townLvl) {
		if (p == null)
			return;
		int curTownLvl = p.getCurtownlvl();
		if (curTownLvl < 2)
			return;
		if (curTownLvl < townLvl) {
			p.setCurtownlvl(townLvl);
			PlayerEntity.updatePlayer(p);
		}
	}

	// 清空周荣耀
	public static void clearWeekRenown() {
		List<Player> list = ProPlayer.getListAll();
		if (Svc.isEmpty(list))
			return;

		int len = list.size();
		for (int i = 0; i < len; i++) {
			Player item = list.get(i);
			if (item == null)
				continue;
			if (item.getWeekrenown() == 0)
				continue;
			item.setWeekrenown(0);
			ProPlayer.upPlayer(item);
		}
	}

	// 发送系统邮件
	private static void sendMailToPlayer(Player p, String title, String cont) {
		LogicMail.sendSystemMailToPlayer(p, title, cont);
	}

	// 充值改变状态
	public static void handlePayMoney(Player p, int money, int buyCryVal) {
		if (p == null || buyCryVal <= 0)
			return;
		String strChn = p.getChannel();
		Map map = LanguageJson.getMapDataByChannel(strChn);
		String title = "";
		String cont = "";

		int status = p.getFirstpaystatus();
		int addCry = buyCryVal;
		int dim_cur = 0;
		int dim_add = 0;

		if (status < 1) {
			status = 1;
			p.setFirstpaystatus(status);
			title = MapEx.getString(map, LanguageJson.Mail_title_pay2);
			addCry = buyCryVal * 3;
			switch (strChn) {
			case ChannelCfg.ND91IOS:
			case ChannelCfg.ND91AND:
			case ChannelCfg.UC:
				dim_cur = (int) FormulaPay.getGemNormer(money);
				dim_add = addCry - dim_cur;
				cont = MapEx.getString(map, LanguageJson.Mail_ms_pay3);
				cont = StrEx.fmt(cont, dim_cur, dim_add, addCry);
				break;
			default:
				cont = MapEx.getString(map, LanguageJson.Mail_ms_pay2);
				cont = StrEx.fmt(cont, addCry);
				break;
			}

			// 首次送工人
			int curWorker = p.getMaxbonum();
			if (curWorker < 5) {
				int ucid = p.getUcid();
				User u = UserEntity.getUserByUcid(ucid);
				String ver = u.getVersion();
				boolean isCanAddWorker = false;
				try {
					double dd = Double.parseDouble(ver);
					double def = 0.91;
					isCanAddWorker = dd > def;
				} catch (Exception e) {
				}

				if (isCanAddWorker) {
					curWorker++;
					p.setMaxbonum(curWorker);
					String strWork = MapEx.getString(map,
							LanguageJson.Mail_ms_worker);
					strWork = StrEx.fmt(strWork, 1);
					cont += "," + strWork;
				}
			}
		} else {
			boolean isPay2 = ActivitiesJson.getIsOpenPay2();
			if (isPay2) {
				addCry = buyCryVal * 2;
			}

			int rateGems = ActivitiesJson.getPayGemsRate();
			if (rateGems > 0) {
				addCry += (int) (addCry * rateGems * 0.01d);
			}

			title = MapEx.getString(map, LanguageJson.Mail_title_pay1);
			switch (strChn) {
			case ChannelCfg.ND91IOS:
			case ChannelCfg.ND91AND:
			case ChannelCfg.UC:
				dim_cur = (int) FormulaPay.getGemNormer(money);
				dim_add = addCry - dim_cur;
				cont = MapEx.getString(map, LanguageJson.Mail_ms_pay3);
				cont = StrEx.fmt(cont, dim_cur, dim_add, addCry);
				break;
			default:
				cont = MapEx.getString(map, LanguageJson.Mail_ms_pay1);
				cont = StrEx.fmt(cont, addCry);
				break;
			}
		}

		p.changeCrystal(addCry);

		long now_time = System.currentTimeMillis();
		p.setLast_pay_time(now_time);
		p.setLast_money(money);

		p.changeAll_money(money);

		PlayerEntity.updatePlayer(p);
		int toId = p.getPcid();
		LogicalSession.refreshPInfo(toId);

		sendMailToPlayer(p, title, cont);
	}

	// 购买月卡
	public static void handlePayMoneyBuyMonCode(Player p, int money) {
		if (p == null)
			return;

		String strChn = p.getChannel();
		Map map = LanguageJson.getMapDataByChannel(strChn);
		String title = "";
		String cont = "";
		String conMs1 = "";
		String conMs2 = "";

		Date now_date = new Date();
		long now_time = now_date.getTime();
		long end_time = now_time + DateEx.TIME_DAY * 30;
		Date end_date = new Date(end_time);
		String strEnd = DateEx.format(end_date, DateEx.fmt_yyyy_MM_dd);

		int defCry = 200;
		int monCodeVal = 60;

		int addCry = defCry + monCodeVal;
		p.changeCrystal(addCry);

		p.setLast_pay_time(now_time);
		p.setLast_money(money);
		p.setMonthcode(end_time);
		p.setIsmoncode(true);

		p.changeAll_money(money);

		PlayerEntity.updatePlayer(p);

		int toId = p.getPcid();
		LogicalSession.refreshPInfo(toId);

		title = MapEx.getString(map, LanguageJson.Mail_title_pay3);
		conMs1 = MapEx.getString(map, LanguageJson.Mail_ms_pay4);
		conMs1 = StrEx.fmt(conMs1, defCry);
		conMs2 = MapEx.getString(map, LanguageJson.Mail_ms_pay5);
		conMs2 = StrEx.fmt(conMs2, strEnd, monCodeVal);
		cont = conMs1 + "," + conMs2;
		sendMailToPlayer(p, title, cont);
	}

	static public boolean resetPayActivity(Player p) {
		boolean isMidAutumn = ActivitiesJson.getIsOpenMidAutumn();
		if (!isMidAutumn) {
			if (p.getMoneyactivity() > 0) {
				p.setMoneyactivity(0);
				p.setMoneyactivitytype(0);
				PlayerEntity.updatePlayer(p);
			}
		}
		return isMidAutumn;
	}

	/****** 充值活动(中秋活动，8.15得能源(每个)x8，81.5每个x88,815每个x888) ******/
	static public void handlePayActivity(Player p, double money) {
		if (p == null)
			return;
		try {
			boolean isMidAutumn = resetPayActivity(p);
			if (!isMidAutumn) {
				return;
			}

			if (p.getMoneyactivitytype() >= 3)
				return;

			String strChn = p.getChannel();
			Map map = LanguageJson.getMapDataByChannel(strChn);

			p.changeMoneyactivity(money);
			int num = getEnergyNumByPayActivity(p);
			p.changePieceattspeed(num);
			p.changePiecedamnum(num);
			p.changePiecehpnum(num);
			PlayerEntity.updatePlayer(p);
			String title = MapEx.getString(map,
					LanguageJson.Mail_title_midAutumn);
			String cont = MapEx.getString(map, LanguageJson.Mail_ms_midAutumn);
			cont = StrEx.fmt(cont, num);
			sendMailToPlayer(p, title, cont);
			// LogicalSession.refreshPInfo(p.getPcid());
		} catch (Exception e) {

		}
	}

	/****** 充值活动(充值多少元，得多少个) ******/
	static public void handlePayHuodong(Player p, double money) {
		if (p == null)
			return;
		try {
			boolean isMidAutumn = ActivitiesJson.getIsOpenPayEnergy();
			if (!isMidAutumn) {
				return;
			}

			int num = (int) money;
			p.changePieceattspeed(num);
			p.changePiecedamnum(num);
			p.changePiecehpnum(num);
			PlayerEntity.updatePlayer(p);

			String strChn = p.getChannel();
			Map map = LanguageJson.getMapDataByChannel(strChn);
			String title = MapEx.getString(map,
					LanguageJson.Mail_title_payEnergy);
			String cont = MapEx.getString(map, LanguageJson.Mail_ms_payEnergy);
			cont = StrEx.fmt(cont, num);
			sendMailToPlayer(p, title, cont);
			// LogicalSession.refreshPInfo(p.getPcid());
		} catch (Exception e) {

		}
	}

	static private int getEnergyNumByPayActivity(Player p) {
		if (p == null)
			return 0;
		int res = 0;
		double money = p.getMoneyactivity();
		int type = p.getMoneyactivitytype();
		if (money >= 8.15) {
			if (type == 0) {
				res += 8;
				type = 1;
			}
		}
		if (money >= 81.5) {
			if (type == 1) {
				res += 88;
				type = 2;
			}
		}

		if (money >= 815) {
			if (type == 2) {
				res += 888;
				type = 3;
			}
		}

		p.setMoneyactivitytype(type);

		return res;
	}

	public static void changeStatusMonCode(Player p) {
		if (p == null)
			return;
		boolean isMonCode = p.getIsmoncode();
		if (isMonCode)
			return;
		long code_time = p.getMonthcode();
		if (code_time <= 0)
			return;

		p.setIsmoncode(true);

		String fmt = DateEx.fmt_yyyy_MM_dd;
		long now_time = System.currentTimeMillis();
		Date code_date = new Date(code_time);
		String str_code = DateEx.format(code_date, fmt);

		if (now_time > code_time) {
			Date now_date = new Date(now_time);
			String str_now = DateEx.format(now_date, fmt);
			if (!str_code.equals(str_now)) {
				return;
			}
		}

		String strChn = p.getChannel();
		Map map = LanguageJson.getMapDataByChannel(strChn);
		String title = "";
		String cont = "";

		int monCodeVal = 60;
		int curCry = p.getCrystal();
		curCry += monCodeVal;
		p.setCrystal(curCry);
		PlayerEntity.updatePlayer(p);

		int toId = p.getPcid();
		LogicalSession.refreshPInfo(toId);

		title = MapEx.getString(map, LanguageJson.Mail_title_pay3);
		cont = MapEx.getString(map, LanguageJson.Mail_ms_pay5);
		cont = StrEx.fmt(cont, str_code, monCodeVal);
		sendMailToPlayer(p, title, cont);
	}

	// 领取首充奖励，改变首次充值的状态为:首次充值成功，已经领取
	public static void changeFirstPayWhenReward(Player p) {
		if (p == null)
			return;
		int status = p.getFirstpaystatus();
		if (status == 1) {
			status = 2;
			p.setFirstpaystatus(status);
			PlayerEntity.updatePlayer(p);
		}
	}

	// 取得个人信息
	public static void getNPInfos(Player p, NPInfo re) {
		if (p == null || re == null)
			return;
		re.crystal = p.getCrystal();
		re.maxOrderNum = p.getMaxbonum();
		re.pcid = p.getPcid();
		re.protectTime = p.getProtecttime();
		re.storedGold = p.getStored_gold();
		re.storedOil = p.getStored_oil();
		re.firstPayStatus = p.getFirstpaystatus();
	}

	// npc
	public static boolean isNpc(int type) {
		return type == ConstantType.Type_User_NPC;
	}

	public static boolean isNpc(Player p) {
		if (p == null)
			return true;
		return p.getType() == ConstantType.Type_User_NPC;
	}

	// 取得用户等级LVL

	public static int getExpByLvl(int lvl) {
		if (lvl < 2)
			return 0;
		return (lvl - 1) * 50;
	}

	public static int getLvl(int exp, int lvl) {
		if (lvl < 2)
			lvl = 2;
		int needExp = getExpByLvl(lvl);
		if (needExp > exp)
			return lvl;
		lvl++;
		exp -= needExp;
		return getLvl(exp, lvl);
	}

	public static int getPLvl(Player p) {
		int lvl = 2;
		if (p == null)
			return lvl;
		int exp = p.getExp();
		lvl = getLvl(exp, lvl);
		return lvl;
	}

	// 增加玩家经验

	public static void addExp(Player p, int addVal) {
		if (p == null || addVal <= 0)
			return;
		int exp_ = p.getExp();
		int curLvl = getLvl(exp_, 0);
		exp_ += addVal;
		p.setExp(exp_);
		PlayerEntity.updatePlayer(p);
		int lvl = getLvl(exp_, 0);
		if (lvl > curLvl) {
			LogicHero.refreshHerosMax(p);
		}
	}

	public static int getEnergyNum(Player p, int egid) {
		if (p == null)
			return 0;
		switch (egid) {
		case EnergyJson.Egid_HP:
			return p.getPiecehpnum();
		case EnergyJson.Egid_Damage:
			return p.getPiecedamnum();
		case EnergyJson.Egid_AttSpeed:
			return p.getPieceattspeed();
		}
		return 0;
	}

	// 增加玩家的能源片数量
	public static void changeEnergyNum(Player p, int egid, int num) {
		if (num == 0 || p == null)
			return;
		switch (egid) {
		case EnergyJson.Egid_HP:
			p.changePiecehpnumWithMin(num, 0);
			break;
		case EnergyJson.Egid_Damage:
			p.changePiecedamnumWithMin(num, 0);
			break;
		case EnergyJson.Egid_AttSpeed:
			p.changePieceattspeedWithMin(num, 0);
			break;
		default:
			break;
		}
		PlayerEntity.updatePlayer(p);
	}

	public static void changeEnergyNum(Player p, int numHP, int numDam,
			int numSpeed) {
		if (p == null)
			return;
		if (numDam == 0 && numHP == 0 && numSpeed == 0)
			return;

		if (numSpeed != 0) {
			p.changePieceattspeedWithMin(numSpeed, 0);
		}

		if (numDam != 0) {
			p.changePiecedamnumWithMin(numDam, 0);
		}

		if (numHP != 0) {
			p.changePiecehpnumWithMin(numHP, 0);
		}

		PlayerEntity.updatePlayer(p);
	}

	/***** 重新加载用户的信息 ******/
	static public void reloadPInfo(Player p) {
		if (p == null)
			return;
		Session session = getLeWrap(p);
		boolean isInit = false;
		if (session != null) {
			isInit = session.resetPInfo();
		}

		if (!isInit) {
			int pcid = p.getPcid();
			_PInfoJedis.getEnBy(pcid, true);
		}
	}

	// ===初始化玩家数据
	public static void getNPlayersByNew(int ucid, int svid,
			final String channel, NPlayers sups) {
		if (ucid == 0 || sups == null || sups.uplayers == null)
			return;
		Player p = JsonImportInit.importJson(ucid, svid, channel);
		NPlayer sup = PlayerEntity.toSUPlayer(p);
		sups.uplayers.add(sup);
	}

	public static void getNPlayersByUser(User u, int svid,
			final String channel, NPlayers sups) {
		if (sups == null || u == null)
			return;
		int ucid = u.getUcid();
		String pcids = u.getPcids();
		if (pcids == null)
			pcids = "";
		pcids = pcids.trim();

		boolean ischange = "".equals(pcids);
		StringBuffer buffer = new StringBuffer(pcids);

		List<Player> plist = PlayerEntity.getListByUcidSvid(pcids, ucid, svid);
		if (plist != null && plist.size() > 0) {
			sups.uplayers = PlayerEntity.toSUPlayerList(plist);
		} else {
			if (ischange) {
				getNPlayersByNew(ucid, svid, channel, sups);
			}
		}

		if (!ischange)
			return;

		if (sups == null || sups.uplayers == null || sups.uplayers.isEmpty())
			return;

		for (NPlayer item : sups.uplayers) {
			buffer.append(item.pid).append(",");
		}
		pcids = buffer.toString();
		u.setPcids(pcids);
		UserEntity.updateUser(u);
	}

	// ============= 转变药水

	public static String getSpellNameByGid(final int gid) {
		String r = "";
		switch (gid) {
		case 3000:
			r = "空袭技能";
			break;
		case 3001:
			r = "治疗技能";
			break;
		case 3002:
			r = "狂暴技能";
			break;
		default:
			break;
		}
		return r;
	}

	private static List<NSpell> toListNSpell(String strInfo) {
		List<NSpell> r2 = null;
		if (strInfo == null || "".equals(strInfo.trim()))
			return r2;
		try {
			List list = JSON.parseList(strInfo);
			r2 = new ArrayList<NSpell>();
			for (Object obj : list) {
				Map m = (Map) obj;
				int num = MapEx.getInt(m, "num");
				int gid = MapEx.getInt(m, "gid");
				if (gid <= 0 || num <= 0)
					continue;
				NSpell item = new NSpell();
				item.gid = gid;
				item.num = num;
				r2.add(item);
			}
		} catch (Exception e) {
			log.info(UtileTools.ex2s(e));
		}
		return r2;
	}

	private static NSpell getNSpellBy(List<NSpell> list, int gid) {
		NSpell r2 = null;
		boolean isNull = ListEx.isEmpty(list);
		if (isNull)
			return r2;
		for (NSpell item : list) {
			if (item.gid == gid) {
				r2 = item;
				break;
			}
		}
		return r2;
	}

	private static String toStrSpells(String strSpells, int gid, int num) {
		if (strSpells == null)
			strSpells = "";
		String r2 = strSpells;
		if (num == 0)
			return r2;
		List<NSpell> origin = toListNSpell(r2);

		boolean isNullOrigin = ListEx.isEmpty(origin);
		if (num < 0) {
			if (isNullOrigin)
				return r2;
			NSpell addItem = getNSpellBy(origin, gid);
			if (addItem == null)
				return r2;
			addItem.num += num;
			if (addItem.num < 0)
				addItem.num = 0;
		} else {
			if (isNullOrigin)
				origin = new ArrayList<NSpell>();
			NSpell addItem = getNSpellBy(origin, gid);
			if (addItem == null) {
				addItem = new NSpell();
				addItem.gid = gid;
				addItem.num = num;
				origin.add(addItem);
			} else {
				addItem.num += num;
			}
		}
		r2 = toStrSpells(origin);
		return r2;
	}

	private static String toStrSpells(List<NSpell> list) {
		String r2 = "";
		boolean isNull = ListEx.isEmpty(list);
		if (isNull)
			return r2;
		StringBuffer sb = new StringBuffer("[");
		for (NSpell item : list) {
			sb.append("{");
			sb.append("\"gid\"").append(":").append(item.gid).append(",");
			sb.append("\"num\"").append(":").append(item.num);
			sb.append("}").append(",");
		}
		r2 = sb.toString();
		int len = r2.length();
		if (len > 2) {
			r2 = r2.substring(0, len - 1);
		}
		r2 += "]";
		return r2;
	}

	private static NSpells toNSpells(List<NSpell> list, NSpells nspell) {
		if (list == null)
			return nspell;
		if (nspell != null) {
			nspell.lists = list;
		}
		return nspell;
	}

	public static void getNSPells(Player p, NSpells nspell) {
		if (p == null)
			return;
		String strSpell = p.getSpells();
		if (null == strSpell || "".equals(strSpell.trim()))
			return;
		getNSPells(strSpell, nspell);
	}

	public static void getNSPells(String strSpell, NSpells nspell) {
		List<NSpell> list_ = toListNSpell(strSpell);
		toNSpells(list_, nspell);
	}

	// ===============npcs

	public static String toStrNNPCs(List<NNPCBeFighted> list) {
		String r2 = "";
		boolean isNull = ListEx.isEmpty(list);
		if (isNull)
			return r2;
		StringBuffer sb = new StringBuffer("[");
		for (NNPCBeFighted item : list) {
			sb.append("{");
			sb.append("\"gold\"").append(":").append(item.gold).append(",");
			sb.append("\"ncid\"").append(":").append(item.ncid).append(",");
			sb.append("\"oil\"").append(":").append(item.oil).append(",");
			sb.append("\"star\"").append(":").append(item.star);
			sb.append("}").append(",");
		}
		r2 = sb.toString();
		int len = r2.length();
		if (len > 2) {
			r2 = r2.substring(0, len - 1);
		}
		r2 += "]";
		return r2;
	}

	public static void getNNPCs(Player p, NNPCBeFighteds npcs) {
		if (p == null)
			return;
		boolean isGuid = p.getGuid_step() < 100;
		if (isGuid)
			return;

		String strNPCS = p.getNpcs();
		if (null == strNPCS || "".equals(strNPCS.trim()))
			return;
		getNNPCs(strNPCS, npcs);
	}

	public static void getNNPCs(String strNPCS, NNPCBeFighteds nnpcs) {
		List<NNPCBeFighted> list_ = toListNNPC(strNPCS);
		toNNPCs(list_, nnpcs);
	}

	public static void toNNPCs(List<NNPCBeFighted> list, NNPCBeFighteds nnpcs) {
		if (list == null)
			return;
		if (nnpcs != null) {
			nnpcs.lists = list;
		}
	}

	public static List<NNPCBeFighted> toListNNPC(String strInfo) {
		List<NNPCBeFighted> r2 = null;
		if (strInfo == null || "".equals(strInfo.trim()))
			return r2;
		try {
			List list = JSON.parseList(strInfo);
			r2 = new ArrayList<NNPCBeFighted>();
			for (Object obj : list) {
				Map m = (Map) obj;
				int gold = MapEx.getInt(m, "gold");
				int ncid = MapEx.getInt(m, "ncid");
				int oil = MapEx.getInt(m, "oil");
				int star = MapEx.getInt(m, "star");
				if (ncid <= 0)
					continue;
				NNPCBeFighted item = new NNPCBeFighted();
				item.gold = gold;
				item.ncid = ncid;
				item.oil = oil;
				item.star = star;
				r2.add(item);
			}
		} catch (Exception e) {
			log.info(UtileTools.ex2s(e));
		}
		return r2;
	}

	/** 购买资源 **/
	static public boolean buyRes(Player p, String resType, int buyVal, int gems) {
		int gemCan = FormulaGame.resToGems(buyVal);
		boolean isYuenan = LogicalEvent.isActiveBuyRes(p);
		if (isYuenan) {
			gemCan = (int) (Math.ceil(gemCan * 0.7));
		}

		if (gemCan - gems > 3) {
			return false;
		}

		addPlayerRes(p, resType, buyVal);
		int hasGold = 0;
		int hasOil = 0;
		switch (resType) {
		case ConstantType.Type_Res_Gold:
			hasGold = buyVal;
			break;
		case ConstantType.Type_Res_Oil:
			hasOil = buyVal;
			break;
		default:
			break;
		}
		LogicAchievement.changeAchievements(p, hasGold, hasOil, 0, 0, 0, 0);
		return true;
	}

	/** 分享 得将 **/
	static public void shareSuccess(Player p) {
		if (p == null)
			return;
		int share = p.getShare();
		share++;
		p.setShare(share);
		if (share % 10 == 0) {
			int gems = p.getCrystal();
			gems += 5;
			p.setCrystal(gems);
		}
		PlayerEntity.updatePlayer(p);
	}

	/** 越南服务器 -输入活动兑换码兑换奖励 **/
	static public void exchangeActive(Player p, int gems) {
		if (p == null)
			return;
		addPlayerRes(p, ConstantType.Type_Res_Crystal, gems);

		String strChn = p.getChannel();
		boolean isYueNan = ChannelCfg.Famv.equals(strChn)
				|| ChannelCfg.Famvios.equals(strChn);

		if (isYueNan) {
			String title = "Nhận thưởng Giftcode";
			String cont = "Chúc mừng bạn đã nhận được một món quà trị giá "
					+ gems + " đá quý. Chúc bạn chơi game vui vẻ! Trân trọng. ";
			sendMailToPlayer(p, title, cont);
		}
		int toId = p.getPcid();
		LogicalSession.refreshPInfo(toId);
	}

	// 下发用户的资源信息
	@SuppressWarnings("unchecked")
	private static void issuedPInfo(GameTcpRepsonse ctx, final String methodName) {

		boolean isIssued = BackToClientLogicHandle.isCanIssued(methodName);
		if (!isIssued)
			return;

		if (ctx == null)
			return;
		try {
			int pcid = ctx.getPcid();
			if (pcid == 0)
				return;
			boolean isCan = ctx.session.getLePlayer().getCanIssuedPinfo();
			if (!isCan)
				return;
			NPInfo pinfo = new NPInfo();
			ReturnStatus rst = new ReturnStatus();
			GameTcpHandle.service.onGetPInfo(ctx, pinfo, rst);
			Map result = new NewMap();
			result.put(0, 1961543560);
			result.put(1, rst.toMap());
			result.put(106671390, pinfo.toMap());
			ctx.send(result);
		} catch (Exception ex) {
			log.info(UtileTools.ex2s(ex));
		}
	}

	// 下发用户的资源信息
	@SuppressWarnings("unchecked")
	private static void issuedPHeros(GameTcpRepsonse ctx,
			final String methodName) {

		boolean isIssued = BackToClientLogicHandle.isCanIssued(methodName);
		if (!isIssued)
			return;

		if (ctx == null)
			return;
		try {
			int pcid = ctx.getPcid();
			if (pcid == 0)
				return;
			boolean isCan = ctx.session.getLePlayer().getCanIssuedHeros();
			if (!isCan)
				return;

			NHeros heors = new NHeros();

			ReturnStatus rst = new ReturnStatus();
			GameTcpHandle.service.onRefreshHeros(ctx, heors, rst);
			Map result = new NewMap();
			result.put(0, -67879522);
			result.put(1, rst.toMap());
			result.put(99165395, heors.toMap());
			ctx.send(result);
		} catch (Exception ex) {
			log.info(UtileTools.ex2s(ex));
		}
	}

	public static void issuedData(GameTcpRepsonse ctx, final String methodName) {
		issuedPHeros(ctx, methodName);
		issuedPInfo(ctx, methodName);
	}

}
