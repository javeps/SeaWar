package com.sea.logic.logicEntity;

import java.io.Serializable;

import com.sea.db.bean.Attack_mail;

//用于数据下发
public class LEAttack implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int Init_AttMail_ClientId = 1;// 初始化数据的客服端ID

	private boolean isCanAdd = true;
	private int idMaxClientAtt = 0;

	//当前战报
	private Attack_mail _curAttMail;
	public Attack_mail getCurAttMail(){
		return _curAttMail;
	}
	public void setCurAttMail(Attack_mail val_){
		_curAttMail = val_;
	}
	public LEAttack() {
	}

	public LEAttack(int idMaxAtt) {
		setIdMaxClientAtt(idMaxAtt);
	}

	public static LEAttack newDefault() {
		return new LEAttack(0);
	}

	public void setIsCanAdd(boolean val) {
		this.isCanAdd = val;
	}

	public boolean getIsCanAdd() {
		return this.isCanAdd;
	}

	public void setIdMaxClientAtt(final int val) {
		boolean isCan = (val > this.idMaxClientAtt);
		if (isCan) {
			this.idMaxClientAtt = val;
		}
		setIsCanAdd(isCan);
	}

	public int GetIdMaxClientAtt() {
		return this.idMaxClientAtt;
	}
	
	public void reset(int idMax){
		this.idMaxClientAtt = idMax;
		this.isCanAdd = false;
	}
}
