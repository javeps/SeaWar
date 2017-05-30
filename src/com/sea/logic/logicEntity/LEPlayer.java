package com.sea.logic.logicEntity;

import java.io.Serializable;

//用于数据下发
public class LEPlayer implements Serializable {

	private static final long serialVersionUID = 1L;

	public LEPlayer() {
	}

	public static LEPlayer newDefault() {
		return new LEPlayer();
	}

	private boolean _canIssuedPinfo = false;

	private boolean _canIssuedHeros = false;

	public void setCanIssuedPinfo(boolean issue) {
		this._canIssuedPinfo = issue;
	}

	public boolean getCanIssuedPinfo() {
		return this._canIssuedPinfo;
	}

	public void setCanIssuedHeros(boolean issue) {
		this._canIssuedHeros = issue;
	}

	public boolean getCanIssuedHeros() {
		return this._canIssuedHeros;
	}
}