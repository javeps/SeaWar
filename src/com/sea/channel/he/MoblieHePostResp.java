/**
 * @author kimi
 * @dateTime 2013-4-28 下午4:09:13
 */
package com.sea.channel.he;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
public class MoblieHePostResp implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer hRet;// 返回值0表示成功
	private String message;// 用户伪码

	public Integer gethRet() {
		return hRet;
	}

	@XmlElement(name = "hRet")
	public void sethRet(Integer hRet) {
		this.hRet = hRet;
	}

	public String getMessage() {
		return message;
	}

	@XmlElement(name = "message")
	public void setMessage(String message) {
		this.message = message;
	}
}
