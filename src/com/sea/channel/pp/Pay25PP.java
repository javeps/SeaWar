package com.sea.channel.pp;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Decoder;

import com.bowlong.util.MapEx;
import com.sea.channel.ChnPayVerify;
import com.sea.db.bean.Payment;
import com.sea.db.entity.PaymentEntity;
import com.sea.handler.gate.GateConfig;
import com.sea.handler.gate.tcp.NotifyGateGame;
import com.sea.handler.response.ResponseWeb;
import com.sea.logic.logicOperate.LogicOrder;
import com.sea.tools.UtileTools;

//@SuppressWarnings("restriction")
@SuppressWarnings("all")
public class Pay25PP {

	static Log log = LogFactory.getLog(Pay25PP.class);

	// coolape 公钥
	static final String Str_Pub_Key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvB3y2EpnPTmkiS10jLSL"
			+ "\r\n"
			+ "VPqyfmsByhuSfEcN1iJOp39eRtqWHhoeT+hL5N3yeG5SKEvDYkj/SQCxBZ1IDMUF"
			+ "\r\n"
			+ "Sne6q74cVDljBfnqSHhGnHxjwEYZenSBeXl243QdF/1JoXf6OXekTboL4buviF5d"
			+ "\r\n"
			+ "QzKvDZ2oNCQnxvUDMeYuX5kw39ht/jSQh/1Qg7z4jNalDrxt1jpWFPA5r3jfyr9H"
			+ "\r\n"
			+ "/d4NeGmClXwYiB1H/nLPh6eFgZkX4qf9ZVQACdpeN4gaNWZaQmkXYomJjBWAI7MX"
			+ "\r\n"
			+ "PrlUbbTWTz4y+x0VNef4h0xGjSinGulzV8Dga7HMAIwSKnn8frnVpu+9xm6E7reE"
			+ "\r\n" + "hwIDAQAB" + "\r\n";

	static boolean isInit = false;

	static RSAEncrypt _rsae = RSAEncrypt.getInstance();

	private static void init() throws Exception {
		if (isInit)
			return;
		_rsae.loadPublicKey(Str_Pub_Key);
		isInit = true;
	}

	// 充值服务地址
	private static int svcid = 1000;

	private static String getJsonBySign(String sign) throws Exception {

		init();
		BASE64Decoder base64Decoder = new BASE64Decoder();
		byte[] dcDataStr = base64Decoder.decodeBuffer(sign);
		byte[] plainData = _rsae.decrypt(_rsae.getPublicKey(), dcDataStr);
		return new String(plainData, "UTF-8");
	}

	@SuppressWarnings("rawtypes")
	public static boolean checkPay(String quereStr, String sign) {
		try {
			String keyStatus = "status";// 状态 0 正常状态 ,1 已兑换过并成功返回
			String keyPPOrder = "order_id";// pp 订单号
			String keyBillNo = "billno";// 自身订单号
			String keyPPDou = "amount";// PP 币数量

			String jsonSign = getJsonBySign(sign);
			Map map = UtileTools.strToMap(jsonSign);
			String signBillno = MapEx.getString(map, keyBillNo);
			if (signBillno == null || "".equals(signBillno.trim())) {
				log.info("====sign 中的厂商订单号为空 ====");
				return false;
			}

			String billno = ResponseWeb.getParamsVal(quereStr, keyBillNo);
			if (!signBillno.equals(billno)) {
				log.info("====sign 中的厂商订单号:" + signBillno + "  与请求上的订单号"
						+ billno + " 不同 ====");
				return false;
			}

			int signStatus = MapEx.getInt(map, keyStatus);
			if (signStatus == 1)
				return true;

			String statusBack = ResponseWeb.getParamsVal(quereStr, "status");
			int statusInt = Integer.parseInt(statusBack);
			if (statusInt != signStatus) {
				log.info("====sign 中的状态:" + signStatus + "  与请求上的状态"
						+ statusInt + " 不同 ====");
				return false;
			}

			double signPPBi = MapEx.getDouble(map, keyPPDou);
			String ppBiStr = ResponseWeb.getParamsVal(quereStr, keyPPDou);
			double ppBiDou = Double.parseDouble(ppBiStr);

			if (ppBiDou != signPPBi) {
				log.info("====sign 中的PP豆:" + signPPBi + "  与请求上的PP豆" + ppBiDou
						+ " 不同 ====");
				return false;
			}
			String signPPNo = MapEx.getString(map, keyPPOrder);

			boolean issue = ChnPayVerify.issuePayToGame(
					GateConfig.TGG_Payment_25PP, svcid, signBillno, signPPBi,
					signPPNo, quereStr);

			return issue;
		} catch (Exception e) {
			log.error(UtileTools.ex2s(e));
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	static boolean checkPay2(String quereStr, String sign) {
		try {
			String keyStatus = "status";// 状态 0 正常状态 ,1 已兑换过并成功返回
			String keyPPOrder = "order_id";// pp 订单号
			String keyBillNo = "billno";// 自身订单号
			String keyPPDou = "amount";// PP 币数量

			String jsonSign = getJsonBySign(sign);
			Map map = UtileTools.strToMap(jsonSign);
			String signBillno = MapEx.getString(map, keyBillNo);
			if (signBillno == null || "".equals(signBillno.trim())) {
				log.info("====sign 中的厂商订单号为空 ====");
				return false;
			}

			String billno = ResponseWeb.getParamsVal(quereStr, keyBillNo);
			if (!signBillno.equals(billno)) {
				log.info("====sign 中的厂商订单号:" + signBillno + "  与请求上的订单号"
						+ billno + " 不同 ====");
				return false;
			}

			int signStatus = MapEx.getInt(map, keyStatus);
			if (signStatus == 1)
				return true;

			String statusBack = ResponseWeb.getParamsVal(quereStr, "status");
			int statusInt = Integer.parseInt(statusBack);
			if (statusInt != signStatus) {
				log.info("====sign 中的状态:" + signStatus + "  与请求上的状态"
						+ statusInt + " 不同 ====");
				return false;
			}

			double signPPBi = MapEx.getDouble(map, keyPPDou);
			String ppBiStr = ResponseWeb.getParamsVal(quereStr, keyPPDou);
			double ppBiDou = Double.parseDouble(ppBiStr);

			if (ppBiDou != signPPBi) {
				log.info("====sign 中的PP豆:" + signPPBi + "  与请求上的PP豆" + ppBiDou
						+ " 不同 ====");
				return false;
			}

			Payment billEntity = LogicOrder.getEnityByBillNo(billno, svcid);
			if (billEntity == null) {
				log.info("==== 厂商 pay ment 为空=== 厂商订单号 =" + billno + ",pp豆:"
						+ signPPBi);
				return false;
			}

			int status = billEntity.getStatus();

			if (status == PaymentEntity.Pay_Status_Success)
				return true;

			if (status == PaymentEntity.Pay_Status_FailBuy)
				return false;

			billEntity.setMoney(signPPBi);

			String signPPNo = MapEx.getString(map, keyPPOrder);
			billEntity.setChid(signPPNo);
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
			NotifyGateGame.getInstance().sendToGame(
					GateConfig.TGG_Payment_25PP, svcid, signBillno);

			return true;
		} catch (Exception e) {
			log.error(UtileTools.ex2s(e));
		}
		return false;
	}
}
