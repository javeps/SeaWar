package com.sea.logic.logicEntity;

import gen_b2g.serv.bean.ConstantType;

import java.io.Serializable;

//用于数据下发
public class LEChat implements Serializable {

	private static final long serialVersionUID = 1L;

	public boolean isIssuedPub;
	public boolean isIssuedPri;
	public boolean isIssuedClan;
	public long lastPub;
	public long lastPri;
	public long latClan;
	public int maxLenPub;
	public int maxLenPri;
	public int maxLenClan;

	public LEChat() {
	}

	public LEChat(long maxPub, long maxPri, long maxClan, int lenPub,
			int lenPri, int lenClan) {
		setIssuedAll(true);
		this.latClan = maxClan;
		this.maxLenClan = lenClan;
		this.lastPri = maxPri;
		this.maxLenPri = lenPri;
		this.lastPub = maxPub;
		this.maxLenPub = lenPub;
	}

	public static LEChat newDefault() {
		return new LEChat(0, 0, 0, 50, 30, 50);
	}

	public void setIssuedAll(boolean b_) {
		this.isIssuedPri = b_;
		this.isIssuedClan = b_;
		this.isIssuedPub = b_;
	}

	public void setMaxId(int type, long val) {
		switch (type) {
		case ConstantType.Type_Chat_Clan:
			latClan = val;
			break;
		case ConstantType.Type_Chat_Pri:
			lastPri = val;
			break;
		default:
			lastPub = val;
			break;
		}
	}

	public long getLast(int type) {
		long r2 = 0;
		switch (type) {
		case ConstantType.Type_Chat_Clan:
			r2 = latClan;
			break;
		case ConstantType.Type_Chat_Pri:
			r2 = lastPri;
			break;
		default:
			r2 = lastPub;
			break;
		}
		return r2;
	}

	public int getMaxLength(int type) {
		int r2 = 0;
		switch (type) {
		case ConstantType.Type_Chat_Clan:
			r2 = maxLenClan;
			break;
		case ConstantType.Type_Chat_Pri:
			r2 = maxLenPri;
			break;
		default:
			r2 = maxLenPub;
			break;
		}
		return r2;
	}

	public void setIssuedTrue(int type) {
		switch (type) {
		case ConstantType.Type_Chat_Clan:
			this.isIssuedClan = true;
			break;
		case ConstantType.Type_Chat_Pri:
			this.isIssuedPri = true;
			break;
		default:
			this.isIssuedPub = true;
			break;
		}
	}

	public void setIssuedFalse(int type) {
		switch (type) {
		case ConstantType.Type_Chat_Clan:
			this.isIssuedClan = false;
			break;
		case ConstantType.Type_Chat_Pri:
			this.isIssuedPri = false;
			break;
		default:
			this.isIssuedPub = false;
			break;
		}
	}

	public boolean getIssued() {
		return this.isIssuedClan || this.isIssuedPri || this.isIssuedPub;
	}

	public void reset() {
		setIssuedAll(true);
		this.latClan = 0;
		this.maxLenClan = 50;
		this.lastPri = 0;
		this.maxLenPri = 30;
		this.lastPub = 0;
		this.maxLenPub = 50;
	}
}
