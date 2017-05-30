package com.sea.json.originData;

import com.sea.tools.UtileTools;

public class NoticeJson {
	public static final String Path_Head = "json/gate/notice/";
	static final String Path_All = Path_Head + "all.json";

	public static String getContNotice(String strChn, boolean isGetChn) {
		String all = UtileTools.readStr(Path_All);
		String chn = getContNoticeInChn(strChn, isGetChn);
		String r = all + chn;
		return r;
	}

	private static String getContNoticeInChn(String strChn, boolean isGetChn) {
		String r = "";
		if (!isGetChn)
			return r;

		String path = Path_Head + strChn + ".json";
		r = UtileTools.readStr(path);
		return r;
	}
}
