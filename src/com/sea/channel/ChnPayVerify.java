package com.sea.channel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sea.db.bean.Payment;
import com.sea.db.entity.PaymentEntity;
import com.sea.handler.gate.tcp.NotifyGateGame;
import com.sea.logic.logicOperate.LogicOrder;
import com.sea.tools.FormulaPay;

public class ChnPayVerify {
	static Log log = LogFactory.getLog(ChnPayVerify.class);

	public static boolean issuePayToGame(final int type, final int svcid,
			String billno, double money, String chnBillNo, String quereStr) {

		Payment billEntity = LogicOrder.getEnityByBillNo(billno, svcid);
		if (billEntity == null) {
			log.info("==== 厂商 pay ment 为空=== 厂商订单号 =" + billno + ",渠道商订单号:"
					+ chnBillNo);
			return false;
		}

		int status = billEntity.getStatus();

		if (status == PaymentEntity.Pay_Status_Success)
			return true;

		if (status == PaymentEntity.Pay_Status_FailBuy)
			return false;

		if (money > 0)
			billEntity.setMoney(money);

		if (chnBillNo != null)
			billEntity.setChid(chnBillNo);

		billEntity.setQuery(quereStr);
		switch (status) {
		case PaymentEntity.Pay_Status_Create:
		case PaymentEntity.Pay_Status_ChannelBack:
			status = PaymentEntity.Pay_Status_SendToGame;
			billEntity.setStatus(status);
			LogicOrder.updatePayment(billEntity);
			break;
		default:
			break;
		}
		NotifyGateGame.getInstance().sendToGame(type, svcid, billno);
		return true;
	}

	public static boolean issuePayToGameByFormula(final int type,
			final int svcid, String billno, double money, String chnBillNo,
			String quereStr) {

		Payment billEntity = LogicOrder.getEnityByBillNo(billno, svcid);
		if (billEntity == null) {
			log.info("==== 厂商 pay ment 为空=== 厂商订单号 =" + billno + ",渠道商订单号:"
					+ chnBillNo);
			return false;
		}

		int status = billEntity.getStatus();

		if (status == PaymentEntity.Pay_Status_Success)
			return true;

		if (status == PaymentEntity.Pay_Status_FailBuy)
			return false;

		if (money <= 0)
			return false;

		int gem = (int) FormulaPay.getGemByRate(money);

		if (gem <= 0)
			return false;

		billEntity.setGem(gem);

		billEntity.setMoney(money);

		if (chnBillNo != null)
			billEntity.setChid(chnBillNo);

		billEntity.setQuery(quereStr);
		switch (status) {
		case PaymentEntity.Pay_Status_Create:
		case PaymentEntity.Pay_Status_ChannelBack:
			status = PaymentEntity.Pay_Status_SendToGame;
			billEntity.setStatus(status);
			LogicOrder.updatePayment(billEntity);
			break;
		default:
			break;
		}
		NotifyGateGame.getInstance().sendToGame(type, svcid, billno);
		return true;
	}
}
