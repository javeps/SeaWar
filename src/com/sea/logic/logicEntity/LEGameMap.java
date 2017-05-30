package com.sea.logic.logicEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sea.db.bean.Player_build_obst;
import com.sea.db.bean.Player_buildings;

/*** 游戏地图对象 ***/
public class LEGameMap implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(LEGameMap.class);

	static public final int Type_Map_Build = 1;
	static public final int Type_Map_Obst = 2;

	// key : post,val : type + cid
	public Map<Integer, String> mapPost = new ConcurrentHashMap<Integer, String>();
	// key : type + cid,val :post
	public Map<String, Integer> mapBPost = new ConcurrentHashMap<String, Integer>();

	private boolean isInitBuild = false;
	private boolean isInitObst = false;
	
	public LEGameMap() {
	}

	public static LEGameMap newDefault() {
		return new LEGameMap();
	}

	public boolean isHasPost(int post) {
		return mapPost.containsKey(post);
	}

	public void initBuilds(List<Player_buildings> origin) {
		if (isInitBuild)
			return;
		isInitBuild = true;
		
		if (origin == null)
			return;
		for (Player_buildings item : origin) {
			addBuild(item);
		}
	}

	public void addBuild(Player_buildings b) {
		if (b == null)
			return;
		int post = b.getPosition_index();
		String type = "bcid:" + b.getBcid();
		if (mapPost.containsKey(post)) {
			log.info("玩家pcid = " + b.getPcid() + ", " + type + " 与 "
					+ mapPost.get(post) + " ,位置相同了。");
		} else {
			mapPost.put(post, type);
			mapBPost.put(type, post);
		}
	}

	public void initObsts(List<Player_build_obst> origin) {
		
		if (isInitObst)
			return;
		isInitObst = true;
		
		if (origin == null)
			return;
		for (Player_build_obst item : origin) {
			addObst(item);
		}
	}

	public void addObst(Player_build_obst obst) {
		if (obst == null)
			return;
		int post = obst.getPosition_index();
		String type = "ocid:" + obst.getBcid();
		if (mapPost.containsKey(post)) {
			log.info("玩家pcid = " + obst.getPcid() + ", " + type + " 与 "
					+ mapPost.get(post) + " ,位置相同了。");
		} else {
			mapPost.put(post, type);
			mapBPost.put(type, post);
		}
	}

	public void resetPost(int post, int cid, int type) {
		String typeStr = "";
		if (type == Type_Map_Build)
			typeStr = "bcid:" + cid;
		else
			typeStr = "ocid:" + cid;

		boolean isHas = mapBPost.containsKey(typeStr);
		if (isHas) {
			int prepost = mapBPost.get(typeStr);
			mapPost.remove(prepost);
		}

		mapPost.put(post, typeStr);
		mapBPost.put(typeStr, post);
	}

	public void clear() {
		mapPost.clear();
		mapBPost.clear();
		isInitObst = false;
		isInitBuild = false;
	}
}
