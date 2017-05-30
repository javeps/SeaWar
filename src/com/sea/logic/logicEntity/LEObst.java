package com.sea.logic.logicEntity;

import java.io.Serializable;

//用于数据下发
public class LEObst implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int Init_Obst_ClientId = 1001;// 初始化数据的客服端ID

	private boolean isCanAdd = true;
	private int idMaxClient = 0;

	public LEObst() {
	}

	public LEObst(int idMaxAtt) {
		setIdMaxClient(idMaxAtt);
	}

	public static LEObst newDefault() {
		return new LEObst(0);
	}

	public void setIsCanAdd(boolean val) {
		this.isCanAdd = val;
	}

	public boolean getIsCanAdd() {
		return this.isCanAdd;
	}

	public void setIdMaxClient(final int val) {
		boolean isCan = (val > this.idMaxClient);
		if (isCan) {
			this.idMaxClient = val;
		}
		setIsCanAdd(isCan);
	}

	public int GetIdMaxClient() {
		return this.idMaxClient;
	}
	
	public void reset(int idMax){
		this.idMaxClient = idMax;
		this.isCanAdd = false;
	}
}
