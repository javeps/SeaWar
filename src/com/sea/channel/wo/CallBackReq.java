/**
 * @author kimi
 * @dateTime 2013-4-28 下午4:09:13
 */
package com.sea.channel.wo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sea.cache.jedis.DCfgJedis;

@XmlRootElement(name = "callbackReq")
public class CallBackReq implements Serializable {

	private static final long serialVersionUID = 1L;

	static final String headBNo = DCfgJedis.getPayUUIDHead();

	private String orderid;// 商户订单号(游戏订单号)
	private String billNo;// 商户订单号(游戏订单号)
	private String ordertime;// 交易时间
	private String cpid;// 开发商ID
	private String appid;// 应用ID
	private String fid;// 渠道ID
	private String consumeCode;// 计费点ID
	private int payfee;// 计费点金额(分)
	private int payType;// 支付方式
	private int hRet;// 支付结果
	private String status;// 状态码
	private String signMsg;// MD5

	public String getOrderid() {
		return orderid;
	}

	@XmlElement(name = "orderid")
	public void setOrderid(String orderid) {
		this.orderid = orderid;
		if (this.orderid != null && !"".equals(this.orderid)) {
			int indexHead = this.orderid.indexOf(headBNo);
			if (indexHead > 0) {
				setBillNo(this.orderid.substring(indexHead));
			}
		}else {
			setBillNo("");
		}
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getSignMsg() {
		return signMsg;
	}

	@XmlElement(name = "signMsg")
	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}

	public String getOrdertime() {
		return ordertime;
	}

	@XmlElement(name = "ordertime")
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}

	public String getCpid() {
		return cpid;
	}

	@XmlElement(name = "cpid")
	public void setCpid(String cpid) {
		this.cpid = cpid;
	}

	public String getAppid() {
		return appid;
	}

	@XmlElement(name = "appid")
	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getFid() {
		return fid;
	}

	@XmlElement(name = "fid")
	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getConsumeCode() {
		return consumeCode;
	}

	@XmlElement(name = "consumeCode")
	public void setConsumeCode(String consumeCode) {
		this.consumeCode = consumeCode;
	}

	public int getPayfee() {
		return payfee;
	}

	@XmlElement(name = "payfee")
	public void setPayfee(int payfee) {
		this.payfee = payfee;
	}

	public int getPayType() {
		return payType;
	}

	@XmlElement(name = "payType")
	public void setPayType(int payType) {
		this.payType = payType;
	}

	public int gethRet() {
		return hRet;
	}

	@XmlElement(name = "hRet")
	public void sethRet(int hRet) {
		this.hRet = hRet;
	}

	public String getStatus() {
		return status;
	}

	@XmlElement(name = "status")
	public void setStatus(String status) {
		this.status = status;
	}

	public String getValForMD5() {
		return "orderid=" + this.orderid + "&ordertime=" + this.ordertime
				+ "&cpid=" + this.cpid + "&appid=" + this.appid + "&fid="
				+ this.fid + "&consumeCode=" + this.consumeCode + "&payfee="
				+ this.payfee + "&payType=" + this.payType + "&hRet="
				+ this.hRet + "&status=" + this.status + "&Key="
				+ PayUnicom.app_key;
	}
}
