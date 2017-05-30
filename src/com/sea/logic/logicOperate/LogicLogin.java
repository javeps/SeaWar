package com.sea.logic.logicOperate;

import gen_b2g.serv.bean.NStrVal;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sea.cache.jedis.admin.AdminRecord;
import com.sea.cache.jedis.game._GameKeys;
import com.sea.cache.jedis.origin.JedisPipeline;
import com.sea.channel.ChannelCfg;
import com.sea.db.entity.UserEntity;
import com.sea.tools.UtileTools;

public class LogicLogin implements Serializable {

	static Log log = LogFactory.getLog(LogicLogin.class);

	private static final long serialVersionUID = 1L;

	static final String[] strs = new String[] { "a", "b", "c", "d", "e", "f",
			"g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
			"t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9" };

	private static String getNewLgid(String lgId, String chnstr) {
		if (lgId == null || "".equals(lgId.trim()))
			return "";
		StringBuffer buff = new StringBuffer();
		if (chnstr == null || "".equals(chnstr.trim()))
			chnstr = ChannelCfg.Cm;
		buff.append(chnstr).append("_").append(lgId);
		String v = buff.toString();
		buff.setLength(0);
		return v;
	}

	private static boolean isHasLgidCm(String lgId, String chnstr) {
		if (lgId == null || "".equals(lgId.trim()))
			return true;
		lgId = getNewLgid(lgId, chnstr);
		String parttern = _GameKeys.fmt(_GameKeys.Pattern_Key_User, lgId);
		List<String> list = JedisPipeline.getListKeysByPattern(parttern);
		boolean isHas = list != null && !list.isEmpty();
		return isHas;
	}

	public static boolean regestUserByCm(String lgId, String lgPwd,
			String chnstr) {
		boolean isCan = !isHasLgidCm(lgId, chnstr);
		if (isCan) {
			lgId = getNewLgid(lgId, chnstr);
			UserEntity.createNewUserInsert(lgId, "", lgPwd, "", "", "1.05");
			AdminRecord.addDayRegistNum(chnstr);
			AdminRecord.addRegistNum(chnstr);
		}
		return isCan;
	}

	private static String random(int len) {
		if (len <= 0)
			return "";
		int max = strs.length;
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < len; i++) {
			int index = UtileTools.randInt(0, max);
			buff.append(strs[index]);
		}
		String v = buff.toString();
		buff.setLength(0);
		return v;
	}

	public static void regestUserFastByCm(String chnstr, NStrVal outLgId,
			NStrVal outLgPwd) {
		String lgid = random(10);
		while (isHasLgidCm(lgid, chnstr)) {
			lgid = random(10);
		}
		String lgPwd = random(7);
		regestUserByCm(lgid, lgPwd, chnstr);
		outLgId.val = lgid;
		outLgPwd.val = lgPwd;
	}
}
