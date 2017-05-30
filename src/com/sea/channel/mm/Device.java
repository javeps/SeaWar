package com.sea.channel.mm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement 
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Device {
	private Integer deviceType;// 设备类型

	private String deviceID;// 设备ID

	public String getDeviceID() {
		return deviceID;
	}

	@XmlElement(name = "DeviceID", required = false)
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public Integer getDeviceType() {
		return deviceType;
	}

	@XmlElement(name = "DeviceType", required = false)
	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}
}
