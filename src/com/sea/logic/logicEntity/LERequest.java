package com.sea.logic.logicEntity;

import gen_b2g.serv.bean.ConstantType;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.util.DateEx;
import com.sea.content.Svc;
import com.sea.tools.EnAndDecryption;
import com.sea.tools.UtileTools;

//用于数据下发
public class LERequest implements Serializable {

	static Log log = LogFactory.getLog(LERequest.class);

	// 区域时间（+-之间）
	static long AREA_TIME = DateEx.TIME_SECOND * 600;

	private static final long serialVersionUID = 1L;

	public static LERequest newDefault() {
		return new LERequest();
	}

	private HashMap<String, Long> reqMap = new HashMap<String, Long>();

	public void clear() {
		reqMap.clear();
	}

	public boolean isContain(String key) {
		boolean r2 = false;
		r2 = reqMap.containsKey(key);
		return r2;
	}

	public boolean addVal(String sign, String method) {
		boolean r2 = false;
		if (sign == null || "".equals(sign.trim()))
			return r2;
		String key = ConstantType.Type_Sign_Key;
		long val = getTimeByGameSign(sign, key);
		StringBuffer sb_ = new StringBuffer("");
		sb_.append("方法名字:").append(method).append(",请求无效.sign=").append(sign)
				.append(",");
		long now_time = System.currentTimeMillis();
		long min_time = now_time - AREA_TIME;
		long max_time = now_time + AREA_TIME;
		if (val < min_time || val > max_time) {
			SimpleDateFormat fdf_ = new SimpleDateFormat(
					Svc.fmt_yyyy_MM_dd_HH_mm_ss_sss);
			String str_n = fdf_.format(new Date(val));
			String str_s = fdf_.format(new Date(now_time));
			String str_mi = fdf_.format(new Date(min_time));
			String str_md = fdf_.format(new Date(max_time));
			sb_.append("传上来时间=").append(str_n).append(",now=").append(str_s)
					.append(",min=").append(str_mi).append(",max=")
					.append(str_md);
			log.error(sb_.toString());
			return r2;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(val).append("_").append(method);
		String mapKey_ = sb.toString();
		r2 = (!isContain(mapKey_));
		if (r2) {
			reqMap.put(mapKey_, val);
		} else {
			sb_.append("该请求已经发送了的！");
			log.error(sb_.toString());
		}
		return r2;
	}

	public void removeLimitTime() {
		synchronized (reqMap) {
			long now_time = System.currentTimeMillis();
			long max_time = now_time - AREA_TIME;
			List<String> keys_ = new ArrayList<String>();
			for (Entry<String, Long> item : reqMap.entrySet()) {
				String key_ = item.getKey();
				long val_ = item.getValue();
				if (max_time > val_) {
					keys_.add(key_);
				}
			}
			for (String strItem : keys_) {
				reqMap.remove(strItem);
			}
		}
	}
	
	public static long getTimeByGameSign(String strSign,String appKey){
		long r = 0;
		if(strSign == null || appKey == null)
			return r;
		strSign = strSign.trim();
		appKey = appKey.trim();
		if("".equals(strSign))
			return r;
		String strVal = EnAndDecryption.decoder(strSign, appKey);
		try {
			r = Long.parseLong(strVal);
		} catch (Exception e) {
			log.error(UtileTools.ex2s(e));
		}
		return r;
	}
}
