package com.sea.channel.mm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sea.channel.ChnPayVerify;
import com.sea.handler.gate.GateConfig;
import com.sea.handler.request.HttpReqWeb;
import com.sea.tools.UtileTools;

public class PayMoblieMM {

	static Log log = LogFactory.getLog(PayMoblieMM.class);

	static String searchURL = "http://ospd.mmarket.com:8089/trust";
	static int svcid = 3000;
	static String errorOrder = "00000000000000000000";
	static int orderTypeTest = 0;// 0：测试订单

	public static String checkPay(String xml) {
		SyncAppOrderResp result = new SyncAppOrderResp();
		result.setMsgType("SyncAppOrderResp");
		result.setVersion("1.0.0");
		result.sethRet(0);

		boolean isMustInfo = true;
		Trusted2ServQueryReq quest = null;
		try {
			SyncAppOrderReq r = JaxbReadXml.readXmlString(
					SyncAppOrderReq.class, xml);
			String chnBillNo = r.getOrderID();
			String billNo = r.getExData();
			if (chnBillNo != null && !"".equals(chnBillNo.trim())
					&& !errorOrder.equals(chnBillNo.trim()) && billNo != null
					&& !"".equals(billNo.trim())) {
				chnBillNo = chnBillNo.trim();
				billNo = billNo.trim();
				int chnOrderType = r.getOrderType();
				int amount = r.getTotalPrice();// 价格单位:分

				quest = new Trusted2ServQueryReq(r.getVersion(), r.getAppID(),
						chnBillNo, r.getTradeID(), chnOrderType);
				byte[] bes = JaxbReadXml.getBytes(Trusted2ServQueryReq.class,
						quest);
				String qback = HttpReqWeb.sendPostXml(searchURL, "UTF-8", bes);
				Trusted2ServQueryResp entity = JaxbReadXml.readXmlString(
						Trusted2ServQueryResp.class, qback);

				if (entity != null) {
					int code = entity.getReturnCode();
					if (chnOrderType == 0 || code == 0) {
						double money = (double) amount / 100;
						boolean isOkey = ChnPayVerify.issuePayToGame(
								GateConfig.TGG_Payment_MM, svcid, billNo,
								money, chnBillNo, xml);
						isMustInfo = !isOkey;
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
			return JaxbReadXml.getString(SyncAppOrderResp.class, result);
		} catch (Exception e) {
			log.error(UtileTools.ex2s(e));
		}
		return "";
	}

	static public void mmReadXML() throws Exception {
		String path = "sql/MM.xml";
		path = "json/origin.xml";
		String xml = UtileTools.readStr(path);
		// xml =
		// "<?xml version='1.0' encoding='utf-8'?><SyncAppOrderReq xmlns='http://www.monternet.com/dsmp/schemas/'><TransactionID>CSSP16122856</TransactionID>		<MsgType>SyncAppOrderReq</MsgType>		<Version>1.0.0</Version>		<Send_Address>			<DeviceType>200</DeviceType>			<DeviceID>CSSP</DeviceID>		</Send_Address>		<Dest_Address>			<DeviceType>400</DeviceType>			<DeviceID>SPSYN</DeviceID>		</Dest_Address>		<OrderID>11130619144434998192</OrderID>		<CheckID>0</CheckID>		<ActionTime>20130619144435</ActionTime>		<ActionID>1</ActionID>		<MSISDN></MSISDN>		<FeeMSISDN>ECAD2EVFADF3AE2A</FeeMSISDN>		<AppID>300001489326</AppID>		<PayCode>30000148932601</PayCode>		<TradeID>L0IF7AF2J4L5IF1B</TradeID>		<Price>100</Price>		<TotalPrice>100</TotalPrice>		<SubsNumb>1</SubsNumb>		<SubsSeq>1</SubsSeq>		<ChannelID>2000000032</ChannelID>		<ExData></ExData>		<OrderType>1</OrderType>		<MD5Sign>ABCDEFGHIKDFIEJFLAKDJFSIDF</MD5Sign>	</SyncAppOrderReq>";
		String out = PayMoblieMM.checkPay(xml);
		System.out.println(out);
	}
}
