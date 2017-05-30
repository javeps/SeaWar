package com.sea.channel.ty;

import gen_b2g.serv.bean.ConstantType;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.handler.codec.http.HttpRequest;

import com.bowlong.util.DateEx;
import com.bowlong.util.MapEx;
import com.sea.channel.ChannelCfg;
import com.sea.channel.ChnPayVerify;
import com.sea.db.bean.Payment;
import com.sea.db.entity.PaymentEntity;
import com.sea.handler.gate.GateConfig;
import com.sea.handler.response.ResponseWeb;
import com.sea.json.originData.PayOriginJson;
import com.sea.logic.logicEntity.LERequest;
import com.sea.logic.logicOperate.LogicOrder;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class PayTYKJ {

	static Log log = LogFactory.getLog(PayTYKJ.class);

	static final String key_order_no = "order_no";
	static final String key_pay_code = "pay_code";
	static final String key_price = "price";
	static final String key_create_time = "create_time";
	static final String key_status = "status";
	static final String key_phone = "phone";
	static final String key_Pcid = "pcid";

	static final String res_code = "{\"res_code\":0}";
	static final int code_success = 0;
	static final int svcid = 3000;

	static final long Diff_Time = DateEx.TIME_MINUTE * 20;

	static public String checkPay(HttpRequest request) {
		Map<String, String> bodyMap = ResponseWeb
				.getMapQueryByPostBody(request);
		int pcid = MapEx.getInt(bodyMap, key_Pcid);
		if (pcid != 0) {
			clientNotity(bodyMap);
		} else {
			tyNotity(bodyMap);
		}
		return res_code;
	}

	// 客户端通知数据
	static void clientNotity(Map<String, String> bodyMap) {
		String strUri = ResponseWeb.getStrQueryByMap(bodyMap);
		boolean isMustInfo = true;
		try {
			String strSign = MapEx.getString(bodyMap, "sign");
			String appKey = ConstantType.Type_Sign_Key;
			if (strSign != null) {
				strSign = strSign.replace("a", "+");
				strSign = strSign.replace("r", "-");
			}
			long tl = LERequest.getTimeByGameSign(strSign, appKey);
			long now_time = System.currentTimeMillis();
			long min = now_time - Diff_Time;
			long max = now_time + Diff_Time;
			if (tl < min || tl > max) {
				log.info("天翼客户端充值回掉 时间sign不对 ：" + strUri);
				return;
			}

			String strIden = MapEx.getString(bodyMap, "iden");
			Map map = PayOriginJson.getMapBy(ChannelCfg.Ty, strIden);
			if (map == null) {
				log.info("天翼客户端充值回掉 ident为空   ： " + strUri);
				return;
			}
			double money = MapEx.getDouble(map, PayOriginJson.PJ_MONEY);

			String chnBillNo = MapEx.getString(bodyMap, key_order_no);
			String billNo = chnBillNo;
			Payment pay = LogicOrder.getEnityByChnNo(billNo, svcid);
			if (pay == null) {
				int pcid = MapEx.getInt(bodyMap, key_Pcid);
				int status = PaymentEntity.Pay_Status_SendToGame;
				LogicOrder.createNewOrderByChnNo(svcid, pcid, status,
						ChannelCfg.Ty, strIden, chnBillNo, strUri, "");
			} else {
				strUri = pay.getQuery();
			}
			boolean isOkey = ChnPayVerify.issuePayToGame(
					GateConfig.TGG_Payment_Tykj, svcid, billNo, money,
					chnBillNo, strUri);
			isMustInfo = !isOkey;
		} catch (Exception e) {
			log.equals(UtileTools.ex2s(e));
		} finally {
			if (isMustInfo) {
				log.info("天翼客户端充值回掉：" + strUri);
			}
		}
	}

	static public void tyNotity(Map<String, String> bodyMap) {
		String strUri = ResponseWeb.getStrQueryByMap(bodyMap);
		boolean isMustInfo = true;
		try {
			if (bodyMap.containsKey(key_status)) {
				int status = MapEx.getInt(bodyMap, key_status);
				if (status == 0) {
					String strIden = MapEx.getString(bodyMap, key_pay_code);
					Map map = PayOriginJson.getMapBy(ChannelCfg.Ty, strIden);
					if (map == null) {
						log.info("天翼服务器端充值回掉 ident为空   ： " + strUri);
						return;
					}

					double money = MapEx.getDouble(map, PayOriginJson.PJ_MONEY);
					double price = MapEx.getDouble(bodyMap, key_price);
					if (money != price) {
						log.info("天翼服务器充值回掉 金额不对  ： " + strUri);
						return;
					}

					String chnBillNo = MapEx.getString(bodyMap, key_order_no);
					String billNo = chnBillNo;
					Payment pay = LogicOrder.getEnityByChnNo(billNo, svcid);

					if (pay == null) {
						status = PaymentEntity.Pay_Status_Create;
						LogicOrder.createNewOrderByChnNo(svcid, 0, status,
								ChannelCfg.Ty, strIden, billNo, strUri, "");
						isMustInfo = false;
						return;
					}

					money = pay.getMoney();
					if (price != money) {
						int gem = MapEx.getInt(map, PayOriginJson.PJ_GEM);
						pay.setMoney(money);
						pay.setGem(gem);
						pay.setQuery(strUri);
						LogicOrder.updatePayment(pay);
						isMustInfo = false;
					}

					// boolean isOkey = ChnPayVerify.issuePayToGame(
					// GateConfig.TGG_Payment_Tykj, svcid, billNo, money,
					// chnBillNo, strUri);
					//
					// isMustInfo = !isOkey;
				}
			}
		} catch (Exception e) {
			log.equals(UtileTools.ex2s(e));
		} finally {
			if (isMustInfo) {
				log.info("天翼服务器端充值回掉：" + strUri);
			}
		}
	}
}
