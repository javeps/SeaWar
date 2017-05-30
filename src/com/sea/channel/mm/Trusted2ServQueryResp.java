/**
 * @author kimi
 * @dateTime 2013-4-28 下午4:09:13
 */
package com.sea.channel.mm;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 响应MMIAP支付服务器bean
 * 
 * @author kimi
 * @dateTime 2013-4-28 下午4:09:13
 */
@XmlRootElement(name = "Trusted2ServQueryResp")
public class Trusted2ServQueryResp implements Serializable {
	private static final long serialVersionUID = 1L;

	private String msgType;// 消息类型

	private String version;// 版本号

	private String tradeID;// 外部交易ID

	private int returnCode;// 返回值。

	private String orderID;// 订单编号

	private String payCode;// 计费点代码，ReturnCode=0时需要

	private String startDate;// 应用ID

	private int totalPrice;// 总价

	private String channelID;// 渠道ID

	private String exData;// 自定义参数

	private String srcIP;// 查询来源的IP地址

	public String getMsgType() {
		return msgType;
	}

	@XmlElement(name = "MsgType")
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getVersion() {
		return version;
	}

	@XmlElement(name = "Version")
	public void setVersion(String version) {
		this.version = version;
	}

	public String getOrderID() {
		return orderID;
	}

	@XmlElement(name = "OrderID")
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getTradeID() {
		return tradeID;
	}

	@XmlElement(name = "TradeID")
	public void setTradeID(String tradeID) {
		this.tradeID = tradeID;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	@XmlElement(name = "TotalPrice")
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getReturnCode() {
		return returnCode;
	}

	@XmlElement(name = "ReturnCode")
	public void setReturnCode(Integer returnCode) {
		this.returnCode = returnCode;
	}

	public String getPayCode() {
		return payCode;
	}

	@XmlElement(name = "PayCode")
	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}

	public String getStartDate() {
		return startDate;
	}

	@XmlElement(name = "StartDate")
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getChannelID() {
		return channelID;
	}

	@XmlElement(name = "ChannelID")
	public void setChannelID(String channelID) {
		this.channelID = channelID;
	}

	public String getExData() {
		return exData;
	}

	@XmlElement(name = "ExData")
	public void setExData(String exData) {
		this.exData = exData;
	}

	public String getSrcIP() {
		return srcIP;
	}

	@XmlElement(name = "SrcIP")
	public void setSrcIP(String srcIP) {
		this.srcIP = srcIP;
	}
}
