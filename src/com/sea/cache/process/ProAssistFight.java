package com.sea.cache.process;

import gen_b2g.serv.bean.ConstantType;
import gen_b2g.serv.bean.NEnergy;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.util.DateEx;
import com.bowlong.util.NewMap;
import com.sea.cache.jedis.game.AssistUserPlayerJedis;
import com.sea.db.bean.Player;
import com.sea.logic.logicOperate.LogicPlayer;

public class ProAssistFight implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(ProAssistFight.class);

	static final Map<Integer, Integer> mapOffenDefense = new ConcurrentHashMap<Integer, Integer>();
	static final Map<Integer, Long> mapDefense = new ConcurrentHashMap<Integer, Long>();

	static long addFtime = 3 * DateEx.TIME_MINUTE + 50 * DateEx.TIME_SECOND;

	public static void setBePlayer(int attPcid, int beAttPcid) {
		if (attPcid == 0)
			return;

		mapOffenDefense.remove(attPcid);
		if (beAttPcid != 0) {
			mapDefense.remove(beAttPcid);

			mapOffenDefense.put(attPcid, beAttPcid);
			long vtSeconds = System.currentTimeMillis() + addFtime;
			mapDefense.put(beAttPcid, vtSeconds);
		}
	}

	public static Player getBePlayer(int attPcid) {
		Player r = null;
		if (mapOffenDefense.containsKey(attPcid)) {
			int pcid = mapOffenDefense.get(attPcid);
			r = AssistUserPlayerJedis.getPlayerByPcid(pcid);
		} else {
			r = new Player();
			r.type = ConstantType.Type_User_NPC;
			r.pcid = 0;
			r.pname = "";
		}
		return r;
	}

	public static Player getBePlayer(Player att) {
		if (att == null)
			return null;
		return getBePlayer(att.getPcid());
	}

	public static long getBePlayerFightEndTime(int beAttPcid) {
		if (mapDefense.containsKey(beAttPcid)) {
			long v = mapDefense.get(beAttPcid);
			long diff = v - System.currentTimeMillis();
			if (diff > 0l) {
				return (long) Math.ceil((double) diff / DateEx.TIME_SECOND);
			} else {
				mapDefense.remove(beAttPcid);
			}
		}
		return 0l;
	}

	public static void removeKey(int attPcid) {
		if (mapOffenDefense.containsKey(attPcid)) {
			int bePcid = mapOffenDefense.get(attPcid);
			mapDefense.remove(bePcid);
		}
		mapOffenDefense.remove(attPcid);
	}

	public static void removeKey(Player att) {
		if (att == null)
			return;
		int attPcid = att.getPcid();
		removeKey(attPcid);
	}

	public static void setFightTempVal(Player att, Player beAtt) {
		if (att == null || beAtt == null)
			return;
		int attpcid = att.getPcid();
		int bepcid = beAtt.getPcid();
		setBePlayer(attpcid, bepcid);
	}

	// ==== npc 取得的energy 能源片
	static final NewMap<Integer, NEnergy> mapNPCEnergy = new NewMap<Integer, NEnergy>();

	public static void delNPCEnergyVal() {
		mapNPCEnergy.clear();
	}

	public static void setNPCEnergy(Player p, NEnergy energy) {
		if (p == null)
			return;
		if (energy == null)
			return;
		int pcid = p.getPcid();
		mapNPCEnergy.put(pcid, energy);
	}

	public static void addEnergyByNPC(Player p) {
		if (p == null)
			return;
		int pcid = p.getPcid();
		if (mapNPCEnergy.containsKey(pcid)) {
			NEnergy e = mapNPCEnergy.get(pcid);
			if (e.num > 0) {
				LogicPlayer.changeEnergyNum(p, e.egid, e.num);
			}
		}
		mapNPCEnergy.remove(pcid);
	}
}
