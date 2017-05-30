package com.sea.channel.famv;

import gen_b2g.serv.bean.ConstantType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.handler.codec.http.HttpRequest;

import com.bowlong.lang.StrEx;
import com.bowlong.util.DateEx;
import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;
import com.sea.channel.ChannelCfg;
import com.sea.channel.downjoy.MD5;
import com.sea.db.bean.Payment;
import com.sea.db.entity.PaymentEntity;
import com.sea.handler.gate.GateConfig;
import com.sea.handler.gate.tcp.NotifyGateGame;
import com.sea.handler.response.ResponseWeb;
import com.sea.json.originData.PayOriginJson;
import com.sea.logic.logicEntity.LERequest;
import com.sea.logic.logicOperate.LogicOrder;
import com.sea.tools.UtileTools;

@SuppressWarnings("rawtypes")
public class PayFamv {

	static Log log = LogFactory.getLog(PayFamv.class);

	static final long Diff_Time = DateEx.TIME_MINUTE * 30;

	public static boolean checkPay(String strQuery, String strSign) {
		String appKey = ConstantType.Type_Sign_Key;
		if (strSign != null) {
			strSign = strSign.replace("a", "+");
			strSign = strSign.replace("r", "-");
		}
		long tl = LERequest.getTimeByGameSign(strSign, appKey);
		if (tl <= 0l)
			return false;
		long now_time = System.currentTimeMillis();
		long min = now_time - Diff_Time;
		long max = now_time + Diff_Time;
		if (tl > max || tl < min) {
			log.info("越南充值失败,时间不正确=" + tl);
			return false;
		}
		try {
			String strSvcid = ResponseWeb.getParamsVal(strQuery, "svcid");
			int svcid = Integer.parseInt(strSvcid);

			String strPcid = ResponseWeb.getParamsVal(strQuery, "pcid");
			int pcid = Integer.parseInt(strPcid);

			String channel = ResponseWeb.getParamsVal(strQuery, "chn");
			String strIden = ResponseWeb.getParamsVal(strQuery, "iden");
			String strGems = ResponseWeb.getParamsVal(strQuery, "gems");

			String tranId = tl + "";

			if (strSign == null || "".equals(strSign.trim())) {
				log.info("strSign === null");
				return false;
			}
			if (channel == null)
				channel = "famv_null";

			tranId = channel + tranId;

			Payment pay = LogicOrder.getEnityByChnNo(tranId, svcid);
			int status = PaymentEntity.Pay_Status_SendToGame;
			boolean isHasPay = pay != null;
			if (isHasPay) {
				status = pay.getStatus();
				if (status == PaymentEntity.Pay_Status_Create) {
					status = PaymentEntity.Pay_Status_SendToGame;
				}
			}
			if (status != PaymentEntity.Pay_Status_SendToGame) {
				log.info("该订单号:" + tranId + ",已经完成===");
				return false;
			}

			Map map = PayOriginJson.getMapByGems(channel, strGems);
			if (map == null) {
				return false;
			}
			strIden = MapEx.getString(map, PayOriginJson.PJ_UNI);
			if (isHasPay) {
				int gems = MapEx.getInt(map, PayOriginJson.PJ_GEM);
				double money = MapEx.getInt(map, PayOriginJson.PJ_MONEY);
				pay.setProductuuid(strIden);
				pay.setMoney(money);
				pay.setGem(gems);
				pay.setStatus(status);
				LogicOrder.updatePayment(pay);
			} else {
				LogicOrder.createNewOrderByChnNo(svcid, pcid, status, channel,
						strIden, tranId, strQuery, strGems);
			}

			NotifyGateGame.getInstance().sendToGame(
					GateConfig.TGG_Payment_Famv, svcid, tranId);
			return true;
		} catch (Exception e) {
			log.error(UtileTools.ex2s(e));
			return false;
		}
	}

	/*** appvnios Apple payment 付 款 方 式 ****/
	static final private String APPLE_ITUNES = "APPLE_ITUNES";
	static final private String BANK = "BANK";
	static final private String CARD = "CARD";
	static final String client_secret_ios = "d57615b29e84066a13abf8c1fc184ee60543c9432";
	static final String client_secret_and = "2109f1791e716e7924455a9d750c0a5a05448685f";

	static String getVniosIden(String v, boolean isApp) {
		double d = 0;
		try {
			d = Double.parseDouble(v);
		} catch (Exception e) {
		}

		if (isApp) {
			if (d == 0.99)
				return "com.publish.apt.cuopbien.item1";
			else if (d == 4.99)
				return "com.publish.apt.cuopbien.item2";
			else if (d == 9.99)
				return "com.publish.apt.cuopbien.item3";
		} else {
			if (d == 10000)
				return "1";
			else if (d == 20000)
				return "2";
			else if (d == 50000)
				return "3";
			else if (d == 100000)
				return "4";
			else if (d == 200000)
				return "5";
			else if (d == 300000)
				return "6";
			else if (d == 500000)
				return "7";
		}
		return "";
	}

	static public boolean notifyAppvnios(HttpRequest request) {
		Map<String, String> bodyMap = ResponseWeb
				.getMapQueryByPostBody(request);
		String payType = MapEx.getString(bodyMap, "transaction_type");
		String amount = MapEx.getString(bodyMap, "amount");
		boolean isOkey = false;
		String chnStr = ChannelCfg.Appvnios_app;
		String strIden = "";
		switch (payType) {
		case APPLE_ITUNES:
			isOkey = appNotify(bodyMap);
			strIden = getVniosIden(amount, true);
			break;
		case BANK:
			chnStr = ChannelCfg.Appvnios_bank;
			isOkey = bankNotify(bodyMap);
			strIden = getVniosIden(amount, false);
			break;
		case CARD:
			chnStr = ChannelCfg.Appvnios_card;
			isOkey = cardNotify(bodyMap);
			strIden = getVniosIden(amount, false);
			break;
		default:
			break;
		}

		isOkey = payType.equals(APPLE_ITUNES) || payType.equals(BANK)
				|| payType.equals(CARD);

		int orderStatus = MapEx.getInt(bodyMap, "status");
		isOkey = isOkey && orderStatus == 1;

		String strUri = ResponseWeb.getStrQueryByMap(bodyMap);

		if (isOkey && !"".equals(strIden)) {
			int svcid = 4000;
			String billNo = MapEx.getString(bodyMap, "transaction_id");
			Payment pay = LogicOrder.getEnityByChnNo(billNo, svcid);
			if (pay == null) {
				int status = PaymentEntity.Pay_Status_Create;
				LogicOrder.createNewOrderByChnNo(svcid, 0, status, chnStr,
						strIden, billNo, strUri, "");
			} else {
				pay.setQuery(strUri);
				LogicOrder.updatePayment(pay);

				int status = pay.getStatus();
				if (status == PaymentEntity.Pay_Status_SendToGame) {
					NotifyGateGame.getInstance().sendToGame(
							GateConfig.TGG_Payment_Famv, svcid, billNo);
				}
			}
		} else {
			log.error(strUri);
		}

		return isOkey;
	}

	static private boolean verifyAppvnios(Map<String, String> kvmap,
			List<String> keys, String signature) {
		int orderStatus = MapEx.getInt(kvmap, "status");
		if (orderStatus != 1)
			return false;

		if (ListEx.isEmpty(keys))
			return false;
		StringBuffer build = new StringBuffer();
		for (String key : keys) {
			String v = kvmap.get(key);
			// build.append(key).append("=").append(v);
			build.append(v);
		}
		String str = build.toString();
		String sign = MD5.MD5Encode(str);
		return sign.equalsIgnoreCase(signature);
	}

	static private boolean appNotify(Map<String, String> bodyMap) {
		// md5(amount + country_code + currency + productid + sandbox + state +
		// status + target + transaction_id + transaction_type + client_secret)
		// （加密后的字符串用于保密目的）client_secret:创建应用时提供的Client Secret价值
		String hash = MapEx.getString(bodyMap, "hash");
		bodyMap.put("client_secret", client_secret_ios);
		List<String> keys = new ArrayList<String>();
		keys.add("amount");// Float （支付数量）
		keys.add("country_code");// String（国家吗）
		keys.add("currency");// String （价格单位）
		keys.add("productid");
		keys.add("sandbox");// 交易,0用户实施交易
		keys.add("state");
		keys.add("status");// 交易状态: 1 – 成功
		keys.add("target");// 任意选择的变量
		keys.add("transaction_id");// 系统上的交易吗，开发商使用以确认交易
		keys.add("transaction_type");
		keys.add("client_secret");
		return verifyAppvnios(bodyMap, keys, hash);
	}

	static private boolean bankNotify(Map<String, String> bodyMap) {
		// md5(amount + country_code + currency + sandbox + state + status +
		// target + transaction_id + transaction_type + client_secret)
		// （加密后的字符串用于保密目的）client_secret: 创建应用时提供的Client Secret价值
		String hash = MapEx.getString(bodyMap, "hash");
		bodyMap.put("client_secret", client_secret_and);
		List<String> keys = new ArrayList<String>();
		keys.add("amount");// Float （支付数量）
		keys.add("country_code");// String（国家吗）
		keys.add("currency");// String （价格单位）
		keys.add("sandbox");// 交易,0用户实施交易
		keys.add("state");
		keys.add("status");// 交易状态: 1 – 成功
		keys.add("target");// 任意选择的变量
		keys.add("transaction_id");// 系统上的交易吗，开发商使用以确认交易
		keys.add("transaction_type");
		keys.add("client_secret");
		return verifyAppvnios(bodyMap, keys, hash);
	}

	static private boolean cardNotify(Map<String, String> bodyMap) {
		// md5(amount + card_code + card_serial + card_vendor + country_code +
		// currency + sandbox + state + status + target + transaction_id +
		// transaction_type + client_secret)
		// （加密后的字符串用于保密目的）client_secret:创建应用时提供的Client Secret价值
		String hash = MapEx.getString(bodyMap, "hash");
		bodyMap.put("client_secret", client_secret_and);
		List<String> keys = new ArrayList<String>();
		keys.add("amount");// Float （支付数量）
		keys.add("card_code");// 卡号
		keys.add("card_serial");// 卡密
		keys.add("card_vendor");// 充值卡提供商
		keys.add("country_code");// String（国家吗）
		keys.add("currency");// String （价格单位）
		keys.add("sandbox");// 交易,0用户实施交易
		keys.add("state");
		keys.add("status");// 交易状态: 1 – 成功
		keys.add("target");// 任意选择的变量
		keys.add("transaction_id");// 系统上的交易吗，开发商使用以确认交易
		keys.add("transaction_type");
		keys.add("client_secret");
		return verifyAppvnios(bodyMap, keys, hash);
	}

	public static boolean checkPayAppvnios(String strQuery, String strSign) {
		String appKey = ConstantType.Type_Sign_Key;
		if (strSign != null) {
			strSign = strSign.replace("a", "+");
			strSign = strSign.replace("r", "-");
		}
		long tl = LERequest.getTimeByGameSign(strSign, appKey);
		if (tl <= 0l)
			return false;
		long now_time = System.currentTimeMillis();
		long min = now_time - Diff_Time;
		long max = now_time + Diff_Time;
		if (tl > max || tl < min) {
			log.info("越南充值失败,时间不正确=" + tl);
			return false;
		}
		try {
			String strSvcid = ResponseWeb.getParamsVal(strQuery, "svcid");
			int svcid = Integer.parseInt(strSvcid);

			String strPcid = ResponseWeb.getParamsVal(strQuery, "pcid");
			int pcid = Integer.parseInt(strPcid);

			// String channel = ResponseWeb.getParamsVal(strQuery, "chn");
			// String strIden = ResponseWeb.getParamsVal(strQuery, "iden");
			// String strGems = ResponseWeb.getParamsVal(strQuery, "gems");

			String tranId = ResponseWeb.getParamsVal(strQuery, "chnBillNo");

			if (strSign == null || "".equals(strSign.trim())) {
				log.info("strSign === null");
				return false;
			}

			Payment pay = LogicOrder.getEnityByChnNo(tranId, svcid);
			int status = PaymentEntity.Pay_Status_SendToGame;
			boolean isHasPay = pay != null;
			if (isHasPay) {
				status = pay.getStatus();
				if (status == PaymentEntity.Pay_Status_Create) {
					status = PaymentEntity.Pay_Status_SendToGame;
				}
				// channel = pay.getChannel();
			} else {
				String strTran = ResponseWeb.getParamsVal(strQuery,
						"transactionResult");
				Map map = UtileTools.strToMap(strTran);

				boolean isCanLog = true;
				if (map != null) {
					String payStatus = MapEx.getString(map, "status");
					payStatus = payStatus.trim();
					if ("1".equals(payStatus)
							|| "true".equalsIgnoreCase(payStatus)) {
						boolean isTransaction = map.containsKey("transaction");
						String strIden = "";
						String chnStr = "";
						if (isTransaction) {
							Map data = MapEx.getMap(map, "transaction");
							tranId = MapEx.getString(data, "transaction_id");
							Map inapp = MapEx.getMap(data, "inapp ");
							strIden = getVniosIden(
									MapEx.getString(inapp, "amount"), true);
							chnStr = ChannelCfg.Appvnios_app;
						} else {
							Map data = MapEx.getMap(map, "data");
							tranId = MapEx.getString(data, "transaction_id");
							strIden = getVniosIden(
									MapEx.getString(data, "amount"), false);

							String typeStr = MapEx.getString(data, "type");
							if (CARD.equals(typeStr)) {
								chnStr = ChannelCfg.Appvnios_card;
							} else {
								chnStr = ChannelCfg.Appvnios_bank;
							}
						}

						isCanLog = StrEx.isEmpty(strIden);
						if (!isCanLog) {
							String dbOdId = LogicOrder.createNewOrderByChnNo(
									svcid, pcid, status, chnStr, strIden,
									tranId, "", "");

							if (dbOdId != null && !"".equalsIgnoreCase(dbOdId)) {
								pay = LogicOrder.getEnityByChnNo(tranId, svcid);
							}
						}
					}
				}

				if (isCanLog) {
					log.info("vnios充值，问题===" + strQuery);
					return false;
				}
			}

			if (status != PaymentEntity.Pay_Status_SendToGame) {
				log.info("该订单号:" + tranId + ",已经完成===");
				return false;
			}

			if (pay == null)
				return false;

			pay.setPcid(pcid);
			pay.setStatus(status);
			LogicOrder.updatePayment(pay);

			NotifyGateGame.getInstance().sendToGame(
					GateConfig.TGG_Payment_Famv, svcid, tranId);
			return true;
		} catch (Exception e) {
			log.error(UtileTools.ex2s(e));
			return false;
		}
	}

	static void verVnios() {
		// hash=441fb931fb122239955f4f71aa78aca7&status=1&transaction_type=CARD&state=TestPaymentState&card_serial=12042302047&country_code=VN&transaction_id=C27544DF40D26CA7&currency=VND&amount=10000&client_secret=d57615b29e84066a13abf8c1fc184ee60543c9432&card_code=0128241833932&card_vendor=VIETTEL&sandbox=0&target=username:coolape|userid:7429943
		// hash=f50a75ecd4a50894849e318c349cadab&status=1&transaction_type=CARD&state=game_state&card_serial=36041800168624&country_code=VN&transaction_id=C115456F65290B88&currency=VND&amount=10000&client_secret=2109f1791e716e7924455a9d750c0a5a05448685f&card_code=58899176979003&card_vendor=VINAPHONE&sandbox=0&target=username:Stormone|userid:1514609
		Map<String, String> bodyMap = new HashMap<String, String>();
		bodyMap.put("hash", "f50a75ecd4a50894849e318c349cadab");
		bodyMap.put("status", "1");
		bodyMap.put("transaction_type", "CARD");
		bodyMap.put("state", "game_state");
		bodyMap.put("card_serial", "36041800168624");
		bodyMap.put("country_code", "VN");
		bodyMap.put("transaction_id", "C115456F65290B88");
		bodyMap.put("currency", "VND");
		bodyMap.put("amount", "10000");
		bodyMap.put("client_secret",
				"2109f1791e716e7924455a9d750c0a5a05448685f");
		bodyMap.put("card_code", "58899176979003");
		bodyMap.put("card_vendor", "VINAPHONE");
		bodyMap.put("sandbox", "0");
		bodyMap.put("target", "username:Stormone|userid:1514609");
		boolean isOkey = cardNotify(bodyMap);
		System.out.println(isOkey);
	}

	public static void main(String[] args) {
		verVnios();
	}
}
