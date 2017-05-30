/**
 * @author kimi
 * @dateTime 2013-4-28 下午4:09:13
 */
package com.sea.channel.wo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sea.cache.jedis.DCfgJedis;

@XmlRootElement(name = "checkOrderIdReq")
public class CheckOrderIdReq implements Serializable {

	private static final long serialVersionUID = 1L;

	static final String headBNo = DCfgJedis.getPayUUIDHead();

	private String orderid;// 商户订单号(游戏订单号)
	private String billNo;// 商户订单号(游戏订单号)

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
		} else {
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

	public String getValForMD5() {
		return "orderid=" + this.orderid + "&Key=" + PayUnicom.app_key;
	}
}
