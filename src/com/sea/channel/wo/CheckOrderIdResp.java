/**
 * @author kimi
 * @dateTime 2013-4-28 下午4:09:13
 */
package com.sea.channel.wo;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sea.cache.jedis.DCfgJedis;

@XmlRootElement(name = "paymessages")
public class CheckOrderIdResp implements Serializable {

	private static final long serialVersionUID = 1L;

	static final String headBNo = DCfgJedis.getPayUUIDHead();

	private String checkOrderIdRsp;// 0-验证成功 1-验证失败，必填
	private String appname;// 应用名称，必填,长度<= 64
	private String feename;// 计费点名称,必填
	private String payfee;// 计费点金额(分),必填
	private String appdeveloper;// 应用开发商名称,必填
	private String gameaccount;// 游戏帐号，联网支付必填
	private String macaddress;// mac地址去掉冒号,联网支付必填
	private String appid;// 必填
	private String ipaddress;// ip地址，去掉点号，不为到3位数,联网必填
	private String serviceid;// 沃商计费点，必填
	private String channelid;// 渠道ID,必填
	private String cpid;// 沃商店CPID,必填
	private String ordertime;// 时间戳，必填
	private String imei;// 设备标识，必填
	private String appversion;// 版本号，必填

	public String getCheckOrderIdRsp() {
		return checkOrderIdRsp;
	}

	@XmlElement(name = "checkOrderIdRsp")
	public void setCheckOrderIdRsp(String checkOrderIdRsp) {
		this.checkOrderIdRsp = checkOrderIdRsp;
	}

	public String getAppname() {
		return appname;
	}

	@XmlElement(name = "appname")
	public void setAppname(String appname) {
		this.appname = appname;
	}

	public String getFeename() {
		return feename;
	}

	@XmlElement(name = "feename")
	public void setFeename(String feename) {
		this.feename = feename;
	}

	public String getPayfee() {
		return payfee;
	}

	@XmlElement(name = "payfee")
	public void setPayfee(String payfee) {
		this.payfee = payfee;
	}

	public String getAppdeveloper() {
		return appdeveloper;
	}

	@XmlElement(name = "appdeveloper")
	public void setAppdeveloper(String appdeveloper) {
		this.appdeveloper = appdeveloper;
	}

	public String getGameaccount() {
		return gameaccount;
	}

	@XmlElement(name = "gameaccount")
	public void setGameaccount(String gameaccount) {
		this.gameaccount = gameaccount;
	}

	public String getMacaddress() {
		return macaddress;
	}

	@XmlElement(name = "macaddress")
	public void setMacaddress(String macaddress) {
		this.macaddress = macaddress;
	}

	public String getAppid() {
		return appid;
	}

	@XmlElement(name = "appid")
	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	@XmlElement(name = "ipaddress")
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getServiceid() {
		return serviceid;
	}

	@XmlElement(name = "serviceid")
	public void setServiceid(String serviceid) {
		this.serviceid = serviceid;
	}

	public String getChannelid() {
		return channelid;
	}

	@XmlElement(name = "channelid")
	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}

	public String getCpid() {
		return cpid;
	}

	@XmlElement(name = "cpid")
	public void setCpid(String cpid) {
		this.cpid = cpid;
	}

	public String getOrdertime() {
		return ordertime;
	}

	@XmlElement(name = "ordertime")
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}

	public String getImei() {
		return imei;
	}

	@XmlElement(name = "imei")
	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getAppversion() {
		return appversion;
	}

	@XmlElement(name = "appversion")
	public void setAppversion(String appversion) {
		this.appversion = appversion;
	}

	public CheckOrderIdResp() {
		super();
	}

	public CheckOrderIdResp(String checkOrderIdRsp, String appname,
			String feename, String payfee, String appdeveloper,
			String gameaccount, String macaddress, String appid,
			String ipaddress, String serviceid, String channelid, String cpid,
			String ordertime, String imei, String appversion) {
		super();
		this.checkOrderIdRsp = checkOrderIdRsp;
		this.appname = appname;
		this.feename = feename;
		this.payfee = payfee;
		this.appdeveloper = appdeveloper;
		this.gameaccount = gameaccount;
		this.macaddress = macaddress;
		this.appid = appid;
		this.ipaddress = ipaddress;
		this.serviceid = serviceid;
		this.channelid = channelid;
		this.cpid = cpid;
		this.ordertime = ordertime;
		this.imei = imei;
		this.appversion = appversion;
	}

	public CheckOrderIdResp(String checkOrderIdRsp,String gameaccount, String macaddress,
			String ipaddress, String serviceid, String ordertime, String imei) {
		super();

		UnicomStore e = UnicomStore.getEntityByBM(serviceid);

		this.checkOrderIdRsp = checkOrderIdRsp;
		this.appname = "海岛大作战の海岛奇兵";
		this.feename = e != null ? e.getName() : "";
		this.payfee = e != null ? e.getMoney() + "" : "0";
		this.appdeveloper = "成都腾犸乐动科技有限公司";
		this.gameaccount = gameaccount;
		this.macaddress = macaddress;
		this.appid = PayUnicom.app_id;
		this.ipaddress = ipaddress;
		this.serviceid = serviceid;
		this.channelid = PayUnicom.fid;
		this.cpid = PayUnicom.cpid;
		this.ordertime = ordertime;
		this.imei = imei;
		this.appversion = "1.23";
	}
}

class UnicomStore {
	private String bm;// 编码
	private String ywdm;// 业务代码
	private String name;
	private int money;// 金额(分)
	private int gems;// 砖石

	public String getBm() {
		return bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	public String getYwdm() {
		return ywdm;
	}

	public void setYwdm(String ywdm) {
		this.ywdm = ywdm;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getGems() {
		return gems;
	}

	public void setGems(int gems) {
		this.gems = gems;
	}

	public UnicomStore(String bm, String ywdm, String name, int money, int gems) {
		super();
		this.bm = bm;
		this.ywdm = ywdm;
		this.name = name;
		this.money = money;
		this.gems = gems;
	}

	static final public Map<String, UnicomStore> map = new ConcurrentHashMap<String, UnicomStore>();

	static final public Map<String, String> mapBM = new ConcurrentHashMap<String, String>();

	static boolean isInit = false;

	static void initMap() {
		if (isInit)
			return;
		isInit = true;
		UnicomStore e1 = new UnicomStore("9019987496520140730231936904400001",
				"140804048238", "15颗宝石", 100, 15);
		UnicomStore e2 = new UnicomStore("9019987496520140730231936904400002",
				"140804048239", "30颗宝石", 200, 30);
		UnicomStore e3 = new UnicomStore("9019987496520140730231936904400003",
				"140804048240", "45颗宝石", 300, 45);
		UnicomStore e4 = new UnicomStore("9019987496520140730231936904400004",
				"140804048241", "60颗宝石", 400, 60);
		UnicomStore e5 = new UnicomStore("9019987496520140730231936904400005",
				"140804048242", "75颗宝石", 500, 75);
		UnicomStore e6 = new UnicomStore("9019987496520140730231936904400006",
				"140804048243", "90颗宝石", 600, 90);
		UnicomStore e7 = new UnicomStore("9019987496520140730231936904400007",
				"140804048244", "120颗宝石", 800, 120);
		UnicomStore e8 = new UnicomStore("9019987496520140730231936904400008",
				"140804048245", "150颗宝石", 1000, 150);
		UnicomStore e9 = new UnicomStore("9019987496520140730231936904400009",
				"140804048246", "300颗宝石", 1500, 300);

		map.put(e1.getYwdm(), e1);
		map.put(e2.getYwdm(), e2);
		map.put(e3.getYwdm(), e3);
		map.put(e4.getYwdm(), e4);
		map.put(e5.getYwdm(), e5);
		map.put(e6.getYwdm(), e6);
		map.put(e7.getYwdm(), e7);
		map.put(e8.getYwdm(), e8);
		map.put(e9.getYwdm(), e9);

		mapBM.put(e1.getBm(), e1.getYwdm());
		mapBM.put(e2.getBm(), e2.getYwdm());
		mapBM.put(e3.getBm(), e3.getYwdm());
		mapBM.put(e4.getBm(), e4.getYwdm());
		mapBM.put(e5.getBm(), e5.getYwdm());
		mapBM.put(e6.getBm(), e6.getYwdm());
		mapBM.put(e7.getBm(), e7.getYwdm());
		mapBM.put(e8.getBm(), e8.getYwdm());
		mapBM.put(e9.getBm(), e9.getYwdm());
	}

	static public UnicomStore getEntityByYwdm(String ywdm) {
		initMap();
		if (ywdm == null || "".equals(ywdm.trim()))
			return null;
		return map.get(ywdm);
	}

	static public UnicomStore getEntityByBM(String bm) {
		initMap();
		if (bm == null || "".equals(bm.trim()))
			return null;
		String ywdm = mapBM.get(bm);
		return getEntityByYwdm(ywdm);
	}
}
