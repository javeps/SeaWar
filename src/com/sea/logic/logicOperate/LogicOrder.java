package com.sea.logic.logicOperate;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.util.DateEx;
import com.sea.db.bean.Payment;
import com.sea.db.bean.Player;
import com.sea.db.entity.PaymentEntity;
import com.sea.db.entity.Payment_totalEntity;
import com.sea.db.entity.PlayerEntity;
import com.sea.json.originData.CfgJson;
import com.sea.tools.UtileTools;

public class LogicOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(LogicOrder.class);

	static public String createNewOrder(int svcid, int pcid, String channel,
			String idenStr, String phoneInfo) {
		String ocid = "";
		Payment pay = null;
		try {
			pay = PaymentEntity.createNewInsert(svcid, pcid, channel, idenStr,
					phoneInfo);
		} catch (Exception e) {
			pay = null;
			log.error(UtileTools.ex2s(e));
		}

		if (pay != null) {
			ocid = pay.getUnid();
		}
		return ocid;
	}

	public static String createNewOrder(Player p, String strUni,
			String phoneInfo) {
		if (p == null) {
			log.info("=====创建订单 用户p为空=====");
			return "";
		}
		int svcid = p.getSvcid();
		int pcid = p.getPcid();
		String channel = p.getChannel();
		if ("9".equals(strUni) || "30000833350409".equals(strUni)) {
			long code_time = p.getMonthcode();
			if (code_time > 0) {
				Date now_date = new Date();
				long now_time = now_date.getTime();
				if (now_time < code_time) {
					log.info("=====购买月卡失败，该用户有月卡,未结束=====");
					return "";
				}

				String fmt = DateEx.fmt_yyyy_MM_dd;
				String str_now = DateEx.format(now_date, fmt);
				Date code_date = new Date(code_time);
				String str_code = DateEx.format(code_date, fmt);
				if (str_code.equals(str_now)) {
					log.info("=====购买月卡失败，该用户有月卡,即将结束=====");
					return "";
				}
			}
		}

		return createNewOrder(svcid, pcid, channel, strUni, phoneInfo);
	}

	public static String createNewOrderByChnNo(int svcid, int pcid, int status,
			String channel, String idenStr, String chnBillNo, String querey,
			String phoneInfo) {
		String ocid = "";
		if (status < PaymentEntity.Pay_Status_Create)
			status = PaymentEntity.Pay_Status_Create;
		if (status > PaymentEntity.Pay_Status_FailBuy)
			status = PaymentEntity.Pay_Status_FailBuy;
		Payment pay = PaymentEntity.createNewByChnBillNo(svcid, pcid, channel,
				idenStr, chnBillNo, phoneInfo);
		if (pay != null) {
			ocid = pay.getUnid();
			pay.setQuery(querey);
			pay.setStatus(status);
		}
		try {
			PaymentEntity.insertPayment(pay);
		} catch (Exception e) {
			log.error(UtileTools.ex2s(e));
			ocid = "";
		}
		return ocid;
	}

	// 游戏订单
	public static Payment getEnityByBillNo(String strBillNo, int svcid) {
		Payment pay = null;
		pay = PaymentEntity.getEnityByUnid(strBillNo, svcid);
		return pay;
	}

	// 渠道订单
	public static Payment getEnityByChnNo(String strChnNo, int svcid) {
		Payment pay = null;
		pay = PaymentEntity.getEnityByUnid(strChnNo, svcid);
		return pay;
	}

	public static void updatePayment(Payment pay) {
		PaymentEntity.updatePayment(pay);
	}

	// 处理充值
	public static void handleOrder(String billNo, int svcid) {
		Payment pay = getEnityByBillNo(billNo, svcid);
		if (pay == null)
			return;
		int status = pay.getStatus();
		if (status != PaymentEntity.Pay_Status_SendToGame)
			return;
		int pcid = pay.getPcid();
		Player p = PlayerEntity.getPlayer(pcid);
		if (p == null)
			return;

		int money = (int) pay.getMoney();
		String uuid = pay.getProductuuid();
		boolean isMothCode = money >= 20;

		boolean isVerifyMC = CfgJson.isVerifyMonCode();
		if (!isVerifyMC) {
			isMothCode = true;
		}

		isMothCode = isMothCode && uuid != null;
		if (isMothCode) {
			uuid = uuid.trim();
			isMothCode = isMothCode
					&& ("9".equals(uuid) || "30000833350409".equals(uuid));
		}
		if (isMothCode) {
			LogicPlayer.handlePayMoneyBuyMonCode(p, money);
		} else {
			int buyCryVal = pay.getGem();
			LogicPlayer.handlePayMoney(p, money, buyCryVal);
		}

		// ========== 处理 中秋节活动
		LogicPlayer.handlePayActivity(p, money);
		LogicPlayer.handlePayHuodong(p, money);

		Date vDate = new Date();
		long now_time = vDate.getTime();
		pay.setFinishtime(now_time);
		pay.setStatus(PaymentEntity.Pay_Status_Success);
		PaymentEntity.updatePayment(pay);

		String channel = p.getChannel();
		int month = DateEx.month(vDate);
		Payment_totalEntity.recordPTotal(svcid, channel, money, month);
	}
}
