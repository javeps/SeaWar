package com.sea.logic.logicOperate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sea.cache.jedis.game.PlayerJedis;
import com.sea.channel.ChannelCfg;
import com.sea.db.bean.Player;

/***
 * 分区 港澳台，谷歌
 * 
 * @author Administrator
 * 
 */
public class LogicSubarea implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(LogicSubarea.class);

	// 修改用户数据(所有player的服务器标识svcid)
	static public void resetAllPlayerSvcid(int newsvicd) {
		List<Player> origin = PlayerJedis.getListAll();
		if (origin == null || origin.isEmpty())
			return;
		for (Player item : origin) {
			item.setSvcid(newsvicd);
		}
		PlayerJedis.resetAllPlayersByGroup(origin);
	}

	// 定时删除数据(删除内地)
	static public boolean isDelPlayerChina() {
		List<Player> origin = PlayerJedis.getListAll();
		if (origin == null || origin.isEmpty())
			return false;

		List<Player> delList = new ArrayList<Player>();
		int count = 500;

		A: for (Player item : origin) {
			final String chn = item.getChannel();
			B: switch (chn) {
			case ChannelCfg.GOOGLEPLAY:
			case ChannelCfg.IOSGAT:
				continue A;
			default:
				if (count < 0)
					break A;
				count--;
				delList.add(item);
				break B;
			}
		}

		boolean isSuccess = delList.isEmpty();
		PlayerJedis.delPlayersByDelList(delList);
		return isSuccess;
	}

	// 定时删除数据(删除国外渠道)
	static public boolean isDelPlayerForeign() {
		List<Player> origin = PlayerJedis.getListAll();
		if (origin == null || origin.isEmpty())
			return false;

		List<Player> delList = new ArrayList<Player>();
		int count = 500;

		A: for (Player item : origin) {
			final String chn = item.getChannel();
			B: switch (chn) {
			case ChannelCfg.GOOGLEPLAY:
			case ChannelCfg.IOSGAT:
				if (count < 0)
					break A;
				count--;
				delList.add(item);
				break B;
			default:
				continue A;
			}
		}

		boolean isSuccess = delList.isEmpty();
		PlayerJedis.delPlayersByDelList(delList);
		return isSuccess;
	}
}
