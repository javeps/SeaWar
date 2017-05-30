package com.sea.json.originData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sea.tools.UtileTools;

public class IAPVerify implements Serializable {
	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(IAPVerify.class);

	private static final String Path = "json/gate/appVerify";

	public static List<String> getListVerify() {
		List<String> v = null;
		String str = UtileTools.readStr2(Path);
		str = str.trim();
		if ("".equals(str))
			return v;
		v = new ArrayList<String>();
		String[] arr = str.split(",");
		for (String oneStr : arr) {
			if (oneStr == null)
				continue;
			if ("".equals(oneStr))
				continue;
			v.add(oneStr);
		}
		return v;
	}

	public static boolean isLimitVerify(String base64Code) {
		boolean isLimit = false;
		if (base64Code == null)
			return true;
		base64Code = base64Code.trim();
		if ("".equals(base64Code))
			return true;

		List<String> list = getListVerify();
		if (list == null || list.isEmpty())
			return isLimit;

		for (String str : list) {
			isLimit = base64Code.indexOf(str) != -1;
			if (isLimit)
				break;
		}
		return isLimit;
	}
}
