package com.sea.channel.apple;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.util.DateEx;
import com.bowlong.util.MapEx;
import com.sea.db.bean.Payment;
import com.sea.db.entity.PaymentEntity;
import com.sea.handler.gate.GateConfig;
import com.sea.handler.gate.tcp.NotifyGateGame;
import com.sea.handler.request.HttpReqWeb;
import com.sea.handler.response.ResponseWeb;
import com.sea.json.originData.IAPVerify;
import com.sea.logic.logicOperate.LogicOrder;
import com.sea.tools.UtileTools;

public class PayApple {

	static Log log = LogFactory.getLog(PayApple.class);

	static final String hostBuy = "https://buy.itunes.apple.com/verifyReceipt";
	static final String hostSandBox = "https://sandbox.itunes.apple.com/verifyReceipt";
	static final long Diff_Time = DateEx.TIME_MINUTE * 60;

	private static boolean checkPayBuyBox(String strQuery, String base64ViCode) {
		return checkPay(hostBuy, strQuery, base64ViCode);
	}

	private static boolean checkPaySandBox(String strQuery, String base64ViCode) {
		return checkPay(hostSandBox, strQuery, base64ViCode);
	}

	public static boolean checkPay(String strQuery, String base64ViCode) {
		boolean isHasLimit = IAPVerify.isLimitVerify(base64ViCode);
		if (isHasLimit)
			return false;
		boolean rbuy = checkPayBuyBox(strQuery, base64ViCode);
		if (!rbuy) {
			log.info("========= ios 验证正式模式为 false 此时验证 shandBox===========");
			rbuy = checkPaySandBox(strQuery, base64ViCode);
		}
		return rbuy;
	}

	@SuppressWarnings("rawtypes")
	private static boolean checkPay(String host, String strQuery,
			String base64ViCode) {

		String keySvcid = "svcid";
		String keyPcid = "pcid";
		String keyChn = "chn";
		String keyTranId = "tranid";
		String keyIden = "iden";

		boolean r = false;
		try {
			String strSvcid = ResponseWeb.getParamsVal(strQuery, keySvcid);
			int svcid = Integer.parseInt(strSvcid);

			String strPcid = ResponseWeb.getParamsVal(strQuery, keyPcid);
			int pcid = Integer.parseInt(strPcid);

			String channel = ResponseWeb.getParamsVal(strQuery, keyChn);
			String tranId = ResponseWeb.getParamsVal(strQuery, keyTranId);
			String strIden = ResponseWeb.getParamsVal(strQuery, keyIden);

			if (base64ViCode == null || "".equals(base64ViCode.trim())) {
				log.info("base64 === null");
				return r;
			}
			if (tranId == null || "".equals(tranId.trim())) {
				log.info("订单 tranId === null");
				return r;
			}
			if (channel == null)
				channel = "app_null";

			tranId = channel + tranId;

			Payment pay = LogicOrder.getEnityByChnNo(tranId, svcid);
			int status = PaymentEntity.Pay_Status_SendToGame;
			boolean isHasPay = pay != null;
			if (isHasPay) {
				status = pay.getStatus();
			}
			if (status != PaymentEntity.Pay_Status_SendToGame) {
				log.info("该订单号" + tranId + "已经完成===");
				return r;
			}
			Map<String, String> parames = new HashMap<String, String>();
			parames.put("receipt-data", base64ViCode);
			String strVerifyVal = HttpReqWeb.sendPostJson(host, parames);

			System.out.println(strVerifyVal);

			Map veriMap = UtileTools.strToMap(strVerifyVal);
			boolean isStatus = veriMap.containsKey("status");
			if (!isStatus) {
				log.info("验证状态不正确验证返回值:" + strVerifyVal);
				return r;
			}

			int statusVerify = MapEx.getInt(veriMap, "status");
			if (statusVerify != 0) {
				log.info("验证状态不正确验证status:" + statusVerify);
				return r;
			}

			Map productInfo = MapEx.getMap(veriMap, "receipt");

			long original_purchase_date_ms = MapEx.getLong(productInfo,
					"original_purchase_date_ms");
			long now_time = System.currentTimeMillis();
			long min = now_time - Diff_Time;
			long max = now_time + Diff_Time;
			if (original_purchase_date_ms < min
					|| original_purchase_date_ms > max) {
				log.info("验证订单号purchase时间不正确original_purchase_date_ms:"
						+ original_purchase_date_ms);
				return r;
			}

			String original_transaction_id = MapEx.getString(productInfo,
					"original_transaction_id");
			int index_train_id = tranId.indexOf(original_transaction_id);
			if (index_train_id == -1) {
				log.info("验证订单号订单号不正确original_transaction_id:"
						+ original_transaction_id);
				return r;
			}

			String vpro_id = "com.coolape.islandwar";
			String product_id = MapEx.getString(productInfo, "product_id");
			int index = product_id.indexOf(vpro_id);
			if (index == -1) {
				log.info("验证订单号不正确product_id:" + product_id);
				return r;
			}

			if (!isHasPay) {
				LogicOrder.createNewOrderByChnNo(svcid, pcid, status, channel,
						strIden, tranId, strQuery, "");
			}
			NotifyGateGame.getInstance().sendToGame(GateConfig.TGG_Payment_Ios,
					svcid, tranId);
			r = true;
		} catch (Exception e) {
			log.error(UtileTools.ex2s(e));
		}
		return r;
	}
}
