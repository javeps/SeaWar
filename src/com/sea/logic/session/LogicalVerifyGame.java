package com.sea.logic.session;

import gen_b2g.serv.bean.ConstantType;
import gen_b2g.serv.bean.ReturnStatus;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.netty.bio2.TcpChannel;
import com.bowlong.util.DateEx;
import com.sea.cache.process.ProBuild;
import com.sea.cache.process.ProPlayer;
import com.sea.db.bean.Player;
import com.sea.db.bean.Player_buildings;
import com.sea.handler.game.tcpGame.GameTcpAbsRepsonse;
import com.sea.localEntry.DBBuilding;
import com.sea.logic.BackToClientCofig;
import com.sea.logic.logicEntity.PlayerStatus;
import com.sea.logic.logicOperate.LogicBuild;
import com.sea.logic.logicOperate.LogicPlayer;
import com.sea.tools.FormulaGame;

/**
 * 验证操作
 * 
 * @author Administrator
 * 
 */
public class LogicalVerifyGame extends LogicalSession implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(LogicalVerifyGame.class);

	/** 验证玩家 资源 **/
	static public boolean isEnoughRes(Player p, String resType, int resVal,
			int gems) {
		if (p == null)
			return false;
		if (gems > 0) {
			return p.getCrystal() > gems;
		}
		return LogicPlayer.isEnoughRes(p, resType, resVal);
	}

	/** 验证玩家 资源 (足够就减少) **/
	static public boolean isEnoughResAndReduce(Player p, String resType,
			int resVal, int gems) {
		if (p == null)
			return false;
		boolean isEnough = false;
		int val = 0;
		boolean isChange = resVal != 0 || gems != 0;
		if (gems > 0) {
			val = p.getCrystal();
			val -= gems;
			isEnough = val >= 0;
			if (isEnough) {
				p.setCrystal(val);
				switch (resType) {
				case ConstantType.Type_Res_Gold:
					p.setStored_gold(0);
					break;
				case ConstantType.Type_Res_Oil:
					p.setStored_oil(0);
					break;
				default:
					break;
				}
			}
		} else {
			switch (resType) {
			case ConstantType.Type_Res_Gold:
				val = p.getStored_gold();
				val -= resVal;
				isEnough = val >= 0;
				if (isEnough) {
					p.setStored_gold(val);
				}
				break;
			case ConstantType.Type_Res_Oil:
				val = p.getStored_oil();
				val -= resVal;
				isEnough = val >= 0;
				if (isEnough) {
					p.setStored_oil(val);
				}
				break;
			default:
				break;
			}
		}
		if (isChange) {
			ProPlayer.upPlayer(p);
		}
		return isEnough;
	}

	/** 取得本地建筑对象 **/
	static public DBBuilding getDBBuild(int gid, int lvl) {
		return DBBuilding.getDBByGidLvl(gid, lvl);
	}

	/** 验证能否完成升级 **/
	static public boolean isCanFinishBuild(Player pl, int bcid) {
		if (pl == null)
			return false;
		Player_buildings pb = LogicBuild.getBuildInMap(pl, bcid);
		if (pb == null)
			return false;
		int htLvl = pl.getCurtownlvl();
		int gid = pb.getGid();
		int lvl = pb.getLvl();
		int type = 4;
		long cooldownMs = 0;
		return isEnoughBuild(htLvl, gid, lvl, type, cooldownMs);
	}

	/** 建筑等级type[1新建，2升级，3维修,4完成升级] **/
	static public boolean isEnoughBuild(int htLvl, int gid, int lvl, int type,
			long cooldownMs) {
		if (type == 2 || type == 4) {
			boolean isVTime = type == 2;
			return DBBuilding
					.isCanUpGrade(gid, lvl, htLvl, cooldownMs, isVTime);
		}
		DBBuilding db = getDBBuild(gid, lvl);
		return db != null;
	}

	/** 建筑花费是否足够type[1新建，2升级，3维修] **/
	static public boolean isEnoughBuildCost(int htLvl, int gid, int lvl,
			long cooldownMs, String resType, int costVal, int gems, int type) {
		boolean bl = isEnoughBuild(htLvl, gid, lvl, type, cooldownMs);
		if (!bl)
			return false;

		DBBuilding db = getDBBuild(gid, lvl);
		if (db == null)
			return false;
		
		if (!db.BuildRscType.equals(resType))
			return false;
		int cost = db.BuildCost;
		if (type == 3)
			cost /= 100;

		int diff = cost - costVal;
		if (diff > 0) {
			int costGems = FormulaGame.resToGems(diff);
			int min = costGems - 5;
			if (gems < min)
				return false;
		}
		return true;
	}

	static public ReturnStatus getResultStatus(ReturnStatus ret, final int succ) {
		ret.succ = succ;
		ret.msg = BackToClientCofig.getMsgStr(succ);
		return ret;
	}

	/** 验证session **/
	static public void verifySession(TcpChannel chn, ReturnStatus ret) {
		GameTcpAbsRepsonse absChn = (GameTcpAbsRepsonse) chn;
		if (absChn.session != null || absChn.sessionId == 0)
			return;
		getResultStatus(ret, BackToClientCofig.LOGIN_OTHER_MORE);
	}

	/** 验证玩家 **/
	static public Player verifyPlayer(TcpChannel chn, ReturnStatus ret,
			boolean isLogin) {
		GameTcpAbsRepsonse absChn = (GameTcpAbsRepsonse) chn;
		Player p = null;
		try {
			if (isLogin) {
				Player preP = absChn.getPlayer();
				p = ProPlayer.getPlayerByPcidUcid(absChn.pcid, absChn.ucid);

				if (preP != null) {
					boolean isSame = (preP.getUcid() == absChn.ucid && preP
							.getPcid() == absChn.pcid);
					if (!isSame) {
						LogicPlayer.downLine(preP);
					}
				}
				absChn.session = null;
			} else {
				verifySession(chn, ret);
				if (ret.succ != BackToClientCofig.SUCCESS)
					return null;

				p = absChn.getPlayer();
			}
		} catch (Exception e) {
		}

		if (p == null) {
			getResultStatus(ret, BackToClientCofig.NONE_PLAYER);
		} else {
			if (p.getType() != 2) {
				int status = p.getState();
				if (status == PlayerStatus.Seal) {
					getResultStatus(ret, BackToClientCofig.Frozen_PLAYER);
				}
			}
		}
		return p;
	}

	/** 验证玩家 资源 (足够就减少)兵返回不足的状态 **/
	static public void verifyRes(TcpChannel chn, ReturnStatus ret,
			String resType, int resVal, int gems) {

		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS)
			return;

		verifyRes(p, ret, resType, resVal, gems);
	}

	/** 验证玩家 资源 (足够就减少)兵返回不足的状态 **/
	static public void verifyRes(Player p, ReturnStatus ret, String resType,
			int resVal, int gems) {
		if (p == null)
			getResultStatus(ret, BackToClientCofig.NONE_PLAYER);
		if (ret.succ != BackToClientCofig.SUCCESS)
			return;
		boolean isEnough = isEnoughRes(p, resType, resVal, gems);
		if (!isEnough) {
			if (gems > 0) {
				getResultStatus(ret, BackToClientCofig.NOT_ENOUGH_CRYATAL);
			} else {
				getResultStatus(ret, BackToClientCofig.NOT_ENOUGH_RES);
			}
		}
	}

	/** 验证玩家 资源 (足够就减少)兵返回不足的状态 **/
	static public void verifyResAndReduce(TcpChannel chn, ReturnStatus ret,
			String resType, int resVal, int gems) {

		Player p = verifyPlayer(chn, ret, false);
		if (ret.succ != BackToClientCofig.SUCCESS)
			return;

		verifyResAndReduce(p, ret, resType, resVal, gems);
	}

	/** 验证玩家 资源 (足够就减少)兵返回不足的状态 **/
	static public void verifyResAndReduce(Player p, ReturnStatus ret,
			String resType, int resVal, int gems) {
		if (p == null)
			getResultStatus(ret, BackToClientCofig.NONE_PLAYER);
		if (ret.succ != BackToClientCofig.SUCCESS)
			return;
		boolean isEnough = isEnoughResAndReduce(p, resType, resVal, gems);
		if (!isEnough) {
			if (gems > 0) {
				getResultStatus(ret, BackToClientCofig.NOT_ENOUGH_CRYATAL);
			} else {
				getResultStatus(ret, BackToClientCofig.NOT_ENOUGH_RES);
			}
		}
	}

	/** 验证玩家请求的有效性 **/
	static public Player verifyForSign(TcpChannel chn, ReturnStatus ret,
			String sign, String method) {
		Player p = verifyPlayer(chn, ret, false);
		if (p == null)
			getResultStatus(ret, BackToClientCofig.NONE_PLAYER);

		if (ret.succ != BackToClientCofig.SUCCESS)
			return p;

		GameTcpAbsRepsonse absChn = (GameTcpAbsRepsonse) chn;

		boolean isValid = absChn.session.getLeRequest().addVal(sign, method);
		if (!isValid)
			getResultStatus(ret, BackToClientCofig.REQUEST_INVALID);
		return p;
	}

	/** 验证玩家足够工人 **/
	static public void verifyWorker(Player p, ReturnStatus ret) {
		if (p == null)
			getResultStatus(ret, BackToClientCofig.NONE_PLAYER);
		if (ret.succ != BackToClientCofig.SUCCESS)
			return;
		boolean isEnough = p.getCurbonum() < p.getMaxbonum();
		if (!isEnough) {
			getResultStatus(ret, BackToClientCofig.NOT_ENOUGH_WORKER);
		}
	}

	/** 验证建筑花费type[1新建，2升级，3维修] **/
	static public void verifyBuildCostGidLvl(Player p, ReturnStatus ret,
			int gid, int lvl, long cooldownMs, String resType, int costVal,
			int gems, int type) {
		if (p == null)
			getResultStatus(ret, BackToClientCofig.NONE_PLAYER);
		if (ret.succ != BackToClientCofig.SUCCESS)
			return;

		if (cooldownMs > 0) {
			verifyWorker(p, ret);
		}
		if (ret.succ != BackToClientCofig.SUCCESS)
			return;

		int htLvl = p.getCurtownlvl();
		boolean isEnough = isEnoughBuildCost(htLvl, gid, lvl, cooldownMs,
				resType, costVal, gems, type);
		if (isEnough) {
			verifyRes(p, ret, resType, costVal, gems);
		} else {
			getResultStatus(ret, BackToClientCofig.NOT_ENOUGH_RES);
		}
	}

	/** 验证建筑花费type[1新建，2升级，3维修] **/
	static public void verifyBuildCostBcid(Player p, ReturnStatus ret,
			int bcid, long cooldownMs, String resType, int costVal, int gems,
			int type) {
		if (p == null)
			getResultStatus(ret, BackToClientCofig.NONE_PLAYER);
		if (ret.succ != BackToClientCofig.SUCCESS)
			return;
		int pcid = p.getPcid();
		Player_buildings pb = ProBuild.getBuildByPcidBcid(pcid, bcid);
		if (pb == null) {
			getResultStatus(ret, BackToClientCofig.NONE_BUILD);
			return;
		}
		int gid = pb.getGid();
		int lvl = pb.getLvl();

		verifyBuildCostGidLvl(p, ret, gid, lvl, cooldownMs, resType, costVal,
				gems, type);
	}

	/** 验证完成通过时间的花费 **/
	static public boolean isEnoughTimeGems(long endtime, long nowtime, int gems) {
		boolean isEnough = false;
		int seconds = (int) ((endtime - nowtime) / DateEx.TIME_SECOND);
		int gemsCan = FormulaGame.timeToGems(seconds);
		if (gems < gemsCan) {
			int diff = gemsCan - gems;
			isEnough = diff <= 15;
		}
		return isEnough;
	}

}
