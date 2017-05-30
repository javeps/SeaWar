/**
 * @author kimi
 * @dateTime 2013-4-28 下午4:09:13
 */
package com.sea.channel.he;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sea.cache.jedis.DCfgJedis;

@XmlRootElement(name = "request")
public class MoblieHePost implements Serializable {

	private static final long serialVersionUID = 1L;

	static final String headBNo = DCfgJedis.getPayUUIDHead();

	private String userId;// 用户伪码
	private String contentId;// 计费代码
	private String consumeCode;// 道具计费代码
	private String cpId;// 道具计费代码
	private Integer hRet;// 返回值0表示成功
	private Integer status;// 返回值
	private String versionId;// 版本号
	private String cpparam;// CP透传参数
	private String packageID;// 套餐包ID(非局数据ID)

	public String getUserId() {
		return userId;
	}

	@XmlElement(name = "userId")
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContentId() {
		return contentId;
	}

	@XmlElement(name = "contentId")
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getConsumeCode() {
		return consumeCode;
	}

	@XmlElement(name = "consumeCode")
	public void setConsumeCode(String consumeCode) {
		this.consumeCode = consumeCode;
	}

	public String getCpId() {
		return cpId;
	}

	@XmlElement(name = "cpId")
	public void setCpId(String cpId) {
		this.cpId = cpId;
	}

	public Integer gethRet() {
		return hRet;
	}

	@XmlElement(name = "hRet")
	public void sethRet(Integer hRet) {
		this.hRet = hRet;
	}

	public Integer getStatus() {
		return status;
	}

	@XmlElement(name = "status")
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getVersionId() {
		return versionId;
	}

	@XmlElement(name = "versionId")
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	public String getCpparam() {
		return cpparam;
	}

	@XmlElement(name = "cpparam")
	public void setCpparam(String cpparam) {
		this.cpparam = cpparam;
		if (this.cpparam != null && !"".equals(this.cpparam)) {
			int indexHead = this.cpparam.indexOf(headBNo);
			if (indexHead > 0) {
				this.cpparam = this.cpparam.substring(indexHead);
			}
		}
	}

	public String getPackageID() {
		return packageID;
	}

	@XmlElement(name = "packageID")
	public void setPackageID(String packageID) {
		this.packageID = packageID;
	}
}
