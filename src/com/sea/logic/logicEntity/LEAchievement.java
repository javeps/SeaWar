package com.sea.logic.logicEntity;

import gen_b2g.serv.bean.NAchievement;

import java.io.Serializable;
import java.util.List;

import com.bowlong.util.ListEx;

//用于数据下发
public class LEAchievement implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<NAchievement> _list;
	private boolean _isIssued = false;

	public LEAchievement() {
	}

	public LEAchievement(List<NAchievement> list_) {
		this._list = list_;
	}

	public static LEAchievement newDefault() {
		LEAchievement r2 = new LEAchievement();
		return r2;
	}

	public List<NAchievement> getList() {
		return this._list;
	}

	public void setList(List<NAchievement> list_) {
		this._list = list_;
	}

	public void setIssued(boolean b_) {
		_isIssued = b_;
	}

	public boolean getIssued() {
		return this._isIssued;
	}

	public NAchievement getNMarkByGid(int gid) {
		NAchievement r2 = null;
		boolean isNull = ListEx.isEmpty(this._list);
		if (isNull)
			return r2;
		for (NAchievement item : this._list) {
			if (item.gid == gid) {
				r2 = item;
				break;
			}
		}
		return r2;
	}

	public NAchievement getNMarkByLid(int loid) {
		NAchievement r2 = null;
		boolean isNull = ListEx.isEmpty(this._list);
		if (isNull)
			return r2;
		for (NAchievement item : this._list) {
			if (item.localId == loid) {
				r2 = item;
				break;
			}
		}
		return r2;
	}

	public void reset() {
		if (this._list != null) {
			this._list.clear();
			this._list = null;
		}
		setIssued(true);
	}
	// ======================常量
	/*
	 * public final static int GID_Mark_1 = 1; public final static int
	 * GID_Mark_2 = 2; public final static int GID_Mark_3 = 3; public final
	 * static int GID_Mark_4 = 4; public final static int GID_Mark_5 = 5; public
	 * final static int GID_Mark_6 = 6; public final static int GID_Mark_7 = 7;
	 * public final static int GID_Mark_8 = 8; public final static int
	 * GID_Mark_9 = 9; public final static int GID_Mark_10 = 10; public final
	 * static int GID_Mark_11 = 11; public final static int GID_Mark_12 = 12;
	 * public final static int GID_Mark_13 = 13; public final static int
	 * GID_Mark_14 = 14; public static boolean isHasGid(final int gid){ boolean
	 * r2 = false; switch (gid) { case GID_Mark_1: case GID_Mark_2: case
	 * GID_Mark_3: case GID_Mark_4: case GID_Mark_5: case GID_Mark_6: case
	 * GID_Mark_7: case GID_Mark_8: case GID_Mark_9: case GID_Mark_10: case
	 * GID_Mark_11: case GID_Mark_12: case GID_Mark_13: case GID_Mark_14: r2 =
	 * true; break;
	 * 
	 * default: break; } return r2; } private static HashMap<Integer, String>
	 * _nameMap = null; public static String getMarkName(int gid){ String r2
	 * =""; if(_nameMap == null){ _nameMap = new HashMap<Integer, String>();
	 * _nameMap.put(GID_Mark_1, "守财奴"); _nameMap.put(GID_Mark_2, "电脑终结者");
	 * _nameMap.put(GID_Mark_3, "你的基地真壮观"); _nameMap.put(GID_Mark_4, "金币抢劫者");
	 * _nameMap.put(GID_Mark_5, "你的木头太多了"); _nameMap.put(GID_Mark_6, "垄断石油");
	 * _nameMap.put(GID_Mark_7, "军事威慑"); _nameMap.put(GID_Mark_8, "战士的荣耀");
	 * _nameMap.put(GID_Mark_9, "嘿，你的基地不见了"); _nameMap.put(GID_Mark_10, "战无不胜");
	 * _nameMap.put(GID_Mark_11, "固若金汤"); _nameMap.put(GID_Mark_12, "大炮？还是炮灰？");
	 * _nameMap.put(GID_Mark_13, "每日奖励"); _nameMap.put(GID_Mark_14, "首充奖励"); }
	 * boolean ishas = isHasGid(gid); if(ishas){ r2 = _nameMap.get(gid); }
	 * return r2; }
	 */
}
