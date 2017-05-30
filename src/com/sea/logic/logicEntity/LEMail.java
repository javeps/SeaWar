package com.sea.logic.logicEntity;

import java.io.Serializable;

import com.sea.db.bean.Attack_mail;
import com.sea.db.bean.Player_mail;
import com.sea.logic.logicOperate.LogicMail;

//用于数据下发
public class LEMail implements Serializable {

	private static final long serialVersionUID = 1L;

	public boolean isIssuedAtt;
	public boolean isIssuedPlayer;
	public int maxLenAtt;
	public int maxLenPlayer;

	public Attack_mail maxMailAtt;
	public Player_mail maxMailPlayer;

	public LEMail() {
	}

	public LEMail(int lenAtt, int lenPlayer) {
		reset(lenAtt, lenPlayer);
	}

	public static LEMail newDefault() {
		return new LEMail(30, 30);
	}

	public void setIssuedAll(boolean b_) {
		this.isIssuedPlayer = b_;
		this.isIssuedAtt = b_;
	}

	public int getMaxLength(int type) {
		int r2 = 0;
		switch (type) {
		case LogicMail.Type_Mail_Player:
			r2 = maxLenPlayer;
			break;
		default:
			r2 = maxLenAtt;
			break;
		}
		return r2;
	}

	public void setIssuedTrue(int type) {
		switch (type) {
		case LogicMail.Type_Mail_Player:
			this.isIssuedPlayer = true;
			break;
		default:
			this.isIssuedAtt = true;
			break;
		}
	}

	public void setIssuedFalse(int type) {
		switch (type) {
		case LogicMail.Type_Mail_Player:
			this.isIssuedPlayer = false;
			break;
		default:
			this.isIssuedAtt = false;
			break;
		}
	}

	public boolean getIssuedPlayer() {
		return this.isIssuedPlayer;
	}

	public boolean getIssuedAtt() {
		return this.isIssuedAtt;
	}

	public Attack_mail getMaxMailAtt() {
		return this.maxMailAtt;
	}

	public void setMaxMailAtt(Attack_mail val) {
		this.maxMailAtt = val;
	}

	public Player_mail getMaxMailPlayer() {
		return this.maxMailPlayer;
	}

	public void setMaxMailPlayer(Player_mail val) {
		this.maxMailPlayer = val;
	}

	public void reset(int lenAtt, int lenPlayer) {
		setIssuedAll(true);
		this.maxLenPlayer = lenPlayer;
		this.maxLenAtt = lenAtt;
		this.maxMailAtt = null;
		this.maxMailPlayer = null;
	}
}
