package com.sea.channel.gp;

import gen_b2g.serv.bean.ConstantType;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.util.DateEx;
import com.bowlong.util.MapEx;
import com.sea.db.bean.Payment;
import com.sea.db.entity.PaymentEntity;
import com.sea.handler.gate.GateConfig;
import com.sea.handler.gate.tcp.NotifyGateGame;
import com.sea.handler.response.ResponseWeb;
import com.sea.logic.logicEntity.LERequest;
import com.sea.logic.logicOperate.LogicOrder;
import com.sea.tools.UtileTools;

public class PayGooglePay {

	static Log log = LogFactory.getLog(PayGooglePay.class);

	static final long Diff_Time = DateEx.TIME_MINUTE * 60;

	// NOTE TO PROGRAMMERS
	// the ownedItemData contains the following information
	// it must be converted to JSON object
	// JSONOBJECT jsonObject = new JSONOBJECT(ownedItemData);
	// "orderId":"12999763169054705758.1371079406387615"
	// "packageName":"com.example.app",
	// "productId":"exampleSku",
	// "purchaseTime":1345678900000, (msec since 1970/01/01)
	// "purchaseState":0, // 0-purchased, 1 canceled, 2 refunded
	// "developerPayload":"example developer payload"
	// "purchaseToken" : "122333444455555",

	@SuppressWarnings("rawtypes")
	public static boolean checkPay(String strQuery, String strSign) {
		String appKey = ConstantType.Type_Sign_Key;
		if (strSign != null) {
			strSign = strSign.replace("a", "+");
			strSign = strSign.replace("r", "-");
		}
		long tl = LERequest.getTimeByGameSign(strSign, appKey);
		if (tl <= 0l)
			return false;
		try {
			String strSvcid = ResponseWeb.getParamsVal(strQuery, "svcid");
			int svcid = Integer.parseInt(strSvcid);

			String strPcid = ResponseWeb.getParamsVal(strQuery, "pcid");
			int pcid = Integer.parseInt(strPcid);

			String channel = ResponseWeb.getParamsVal(strQuery, "chn");
			String strIden = ResponseWeb.getParamsVal(strQuery, "iden");
			String strJson = ResponseWeb.getParamsVal(strQuery, "json");

			Map map = UtileTools.strToMap(strJson);

			String productId = MapEx.getString(map, "productId");
			productId = productId.trim();

			if (!productId.equals(strIden.trim())) {
				log.info("gplay充值失败,产品信息不正确=" + strJson);
				return false;
			}

			long now_time = System.currentTimeMillis();
			long min = now_time - Diff_Time;
			long max = now_time + Diff_Time;
			long payTime = MapEx.getLong(map, "purchaseTime");
			if (payTime > max || payTime < min) {
				log.info("gplay充值失败,时间不正确=" + strJson);
				return false;
			}
			String tranId = tl + "";

			if (strSign == null || "".equals(strSign.trim())) {
				log.info("strSign === null");
				return false;
			}
			if (channel == null)
				channel = "gplay_null";

			tranId = channel + tranId;

			Payment pay = LogicOrder.getEnityByChnNo(tranId, svcid);
			int status = PaymentEntity.Pay_Status_SendToGame;
			boolean isHasPay = pay != null;
			if (isHasPay) {
				status = pay.getStatus();
			}
			if (status != PaymentEntity.Pay_Status_SendToGame) {
				log.info("该订单号:" + tranId + ",已经完成===");
				return false;
			}

			if (!isHasPay) {
				LogicOrder.createNewOrderByChnNo(svcid, pcid, status, channel,
						strIden, tranId, strQuery, "");
			}
			NotifyGateGame.getInstance().sendToGame(GateConfig.TGG_Payment_GP,
					svcid, tranId);
			return true;
		} catch (Exception e) {
			log.error(UtileTools.ex2s(e));
			return false;
		}
	}
}
