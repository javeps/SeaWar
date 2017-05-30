package com.sea.logic.logicEntity;

import java.io.Serializable;

//用于 计算用户的英雄的最大属性值
public class HeroMax implements Serializable {

	private static final long serialVersionUID = 1L;

	private int _hgid;
	private int _maxHP;
	private int _maxDam;
	private int _maxSpeed;

	public int getHgid() {
		return this._hgid;
	}

	public int getMaxHP() {
		return this._maxHP;
	}

	public int getMaxDam() {
		return this._maxDam;
	}

	public int getMaxSpeed() {
		return this._maxSpeed;
	}

	public void setHgid(int gid) {
		this._hgid = gid;
	}

	public void setMaxHP(int hp) {
		this._maxHP = hp;
	}

	public void setMaxDam(int dam) {
		this._maxDam = dam;
	}

	public void setMaxSpeed(int sp) {
		this._maxSpeed = sp;
	}
}
