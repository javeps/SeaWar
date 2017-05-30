/**
 * @author kimi
 * @dateTime 2013-4-28 下午4:07:25
 */
package com.sea.channel.mm;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Trusted2ServQueryReq")
public class Trusted2ServQueryReq implements Serializable {

	private static final long serialVersionUID = 1L;

	private String msgType;// 消息类型

	private String version;// 版本号

	private String appID;// 应用ID

	private String orderID;// 订单编号

	private String tradeID;// 外部交易ID

	private int orderType;// 订单类型（默认为0）

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

	public String getAppID() {
		return appID;
	}

	@XmlElement(name = "AppID")
	public void setAppID(String appID) {
		this.appID = appID;
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

	public Integer getOrderType() {
		return orderType;
	}

	@XmlElement(name = "OrderType")
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	// 这个方法很重要，解决一个反编译问题
	public Trusted2ServQueryReq() {
		super();
	}

	public Trusted2ServQueryReq(String version, String appID, String orderID,
			String tradeID, int orderType) {
		this.msgType = "Trusted2ServQueryReq";
		this.version = version;
		this.appID = appID;
		this.orderID = orderID;
		this.tradeID = tradeID;
		this.orderType = orderType;
	}
}
