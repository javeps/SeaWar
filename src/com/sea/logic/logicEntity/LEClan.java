package com.sea.logic.logicEntity;

import java.io.Serializable;

//用于数据下发
public class LEClan implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean _isIssuedClan;
	private boolean _isIssuedClanMember;
	private boolean _isIssuedClanRequest;

	public void setIsIssuedClan(boolean v) {
		this._isIssuedClan = v;
	}

	public void setIsIssuedClanMember(boolean v) {
		this._isIssuedClanMember = v;
	}

	public void setIsIssuedClanRequest(boolean v) {
		this._isIssuedClanRequest = v;
	}

	public boolean getIsIssuedClan() {
		return this._isIssuedClan;
	}

	public boolean getIsIssuedClanMember() {
		return this._isIssuedClanMember;
	}

	public boolean getIsIssuedClanRequest() {
		return this._isIssuedClanRequest;
	}

	public static LEClan newDefault() {
		LEClan v = new LEClan();
		return v;
	}

	public void reset(boolean isBol) {
		this._isIssuedClan = isBol;
		this._isIssuedClanMember = isBol;
		this._isIssuedClanRequest = isBol;
	}
}
