package com.sea.channel.wo;

import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.util.MapEx;
import com.sea.channel.ChnPayVerify;
import com.sea.channel.mm.JaxbReadXml;
import com.sea.channel.mumayi.MD5Util;
import com.sea.content.Svc;
import com.sea.db.bean.Payment;
import com.sea.db.entity.PaymentEntity;
import com.sea.handler.gate.GateConfig;
import com.sea.logic.logicOperate.LogicOrder;
import com.sea.tools.UtileTools;

public class PayUnicom {

	static Log log = LogFactory.getLog(PayUnicom.class);

	static int svcid = 3000;

	static public final String cpid = "90199874965";// 开发商ID
	static public final String app_id = "9019987496520140730231936904400";// 应用ID
	static public final String fid = "00012243";// 渠道ID
	static public final String app_key = "2905d3832a9b9eb1601c";

	static String code_pc_success_4 = "00000";// 支付成功

	public static String checkPay(String xml) {
		boolean isMustInfo = true;
		try {
			CallBackReq post = JaxbReadXml
					.readXmlString(CallBackReq.class, xml);
			String billNo = post.getBillNo();
			String md5 = post.getSignMsg();
			String md5v = MD5(post.getValForMD5());
			if (md5v.equalsIgnoreCase(md5) && post.gethRet() == 0
					&& code_pc_success_4.equals(post.getStatus())) {
				int m = post.getPayfee();// 单位分
				int money = m / 100;
				boolean isSure = ChnPayVerify.issuePayToGame(
						GateConfig.TGG_Payment_Wo, svcid, billNo, money, null,
						xml);
				isMustInfo = !isSure;
			}
		} catch (Exception e) {
			log.error(UtileTools.ex2s(e));
		}

		if (isMustInfo) {
			log.info(xml);
		}

		return CallBackResp.back;
	}

	@SuppressWarnings("rawtypes")
	public static String verify(String xml) {
		boolean isMustInfo = true;
		CheckOrderIdResp result = null;
		try {
			CheckOrderIdReq post = JaxbReadXml.readXmlString(
					CheckOrderIdReq.class, xml);
			String billNo = post.getBillNo();
			String md5 = post.getSignMsg();
			String md5v = MD5(post.getValForMD5());
			if (md5v.equalsIgnoreCase(md5)) {
				Payment pay = LogicOrder.getEnityByBillNo(billNo, svcid);
				if (pay != null) {
					int status = pay.getStatus();
					if (status == PaymentEntity.Pay_Status_Create
							|| status == PaymentEntity.Pay_Status_SendToGame) {
						SimpleDateFormat dfmt = new SimpleDateFormat(
								Svc.fmtYyyyMmDdHHmmss);
						String ordertime = dfmt.format(pay.getCreatetime());

						String serviceid = "";
						UnicomStore e = UnicomStore.getEntityByYwdm(pay
								.getProductuuid());
						if (e != null)
							serviceid = e.getBm();

						String info = pay.getPhoneinfo();
						Map map = UtileTools.strToMap(info);
						String mac = MapEx.getString(map, "mac");
						String ip = MapEx.getString(map, "ip");
						String imei = MapEx.getString(map, "imei");

						result = new CheckOrderIdResp("0", pay.getPcid() + "",
								mac, ip, serviceid, ordertime, imei);

						isMustInfo = false;
					}
				}
			}
		} catch (Exception e) {
			log.error(UtileTools.ex2s(e));
		}

		if (isMustInfo) {
			log.info(xml);
		}

		try {
			if (result == null)
				result = new CheckOrderIdResp("1", "", "", "", "", "", "");
			return JaxbReadXml.getString(CheckOrderIdResp.class, result);
		} catch (Exception e) {
			log.error(UtileTools.ex2s(e));
		}
		return "";
	}

	static public String MD5(String val) {
		return MD5Util.toMD5(val);
	}

	static public void woReadXML() throws Exception {
		String path = "sql/MM.xml";
		path = "json/origin.xml";
		String xml = UtileTools.readStr(path);
		// xml =
		// "<?xml version='1.0' encoding='utf-8'?><SyncAppOrderReq xmlns='http://www.monternet.com/dsmp/schemas/'><TransactionID>CSSP16122856</TransactionID>		<MsgType>SyncAppOrderReq</MsgType>		<Version>1.0.0</Version>		<Send_Address>			<DeviceType>200</DeviceType>			<DeviceID>CSSP</DeviceID>		</Send_Address>		<Dest_Address>			<DeviceType>400</DeviceType>			<DeviceID>SPSYN</DeviceID>		</Dest_Address>		<OrderID>11130619144434998192</OrderID>		<CheckID>0</CheckID>		<ActionTime>20130619144435</ActionTime>		<ActionID>1</ActionID>		<MSISDN></MSISDN>		<FeeMSISDN>ECAD2EVFADF3AE2A</FeeMSISDN>		<AppID>300001489326</AppID>		<PayCode>30000148932601</PayCode>		<TradeID>L0IF7AF2J4L5IF1B</TradeID>		<Price>100</Price>		<TotalPrice>100</TotalPrice>		<SubsNumb>1</SubsNumb>		<SubsSeq>1</SubsSeq>		<ChannelID>2000000032</ChannelID>		<ExData></ExData>		<OrderType>1</OrderType>		<MD5Sign>ABCDEFGHIKDFIEJFLAKDJFSIDF</MD5Sign>	</SyncAppOrderReq>";
		String out = PayUnicom.checkPay(xml);
		System.out.println(out);
	}
}
