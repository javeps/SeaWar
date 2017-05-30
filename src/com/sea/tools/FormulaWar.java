package com.sea.tools;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FormulaWar implements Serializable {

	private static final long serialVersionUID = 1L;

	static public final int KOffer = 60;
	static public final int KDefense = 40;

	/*** 计算战斗结束攻击方胜利得分，防御方失败丢分;攻击方失败丢分,防御方胜利得分 **/
	static public List<Integer> reckonWarGetCups(int offerCups, int defCups,
			int star) {
		List<Integer> result = new ArrayList<Integer>();
		boolean isWin = star > 0;
		int Sa = isWin ? 1 : 0;
		// 计算攻击方
		double rateOffer = 1 / (1 + Math.pow(10, (defCups - offerCups) / 400.0));
		double rateDefense = 1 / (1 + Math.pow(10,
				(offerCups - defCups) / 400.0));
		int cupsOffer = 0;
		int cupsDefense = 0;
		if (isWin) {
			cupsOffer = (int) ((KOffer * (Sa - rateOffer)) * (star / 3.0));
			cupsDefense = (int) (KDefense * (0 - rateDefense));
			cupsOffer = cupsOffer <= 0 ? -cupsOffer : cupsOffer;
			cupsDefense = cupsDefense <= 0 ? cupsDefense : -cupsDefense; 
		} else {
			cupsOffer = (int) (KOffer * (Sa - rateOffer));
			cupsDefense = (int) (KDefense * (1 - rateDefense));
			cupsOffer = cupsOffer <= 0 ? cupsOffer : -cupsOffer;
			cupsDefense = cupsDefense <= 0 ? -cupsDefense : cupsDefense;
		}
		result.add(cupsOffer);
		result.add(cupsDefense);
		return result;
	}

	/**
	 * 杯数计算： Ra：攻方当前杯数 Rb: 守方当前杯数 Ea：攻方的胜负值数学期望，Ea=1/(1+10^[(Rb-Ra)/400])
	 * Eb:守方的胜负值数学期望，Eb=1/(1+10^[(Ra-Rb)/400]) 有Ea+Eb=1 K:攻方为60，守方为40 Sa:实际胜负值
	 * 胜利 =1，失败 = 0； Ra’：攻方战斗后杯数 Ra’ = Ra + 60*(Sa-Ea) Rb’：守方战斗后杯数 Rb’ = Rb+
	 * 40*(Sa-Eb)
	 */
	public static int getCups(int Ra, int Rb, int star, boolean isOffense) {
		double Ea = 1 / (1 + Math.pow(10, (Rb - Ra) / 400.0f));
		double Eb = 1 / (1 + Math.pow(10, (Ra - Rb) / 400.0f));
		float K = 0;
		if (isOffense) {
			K = 60;
		} else {
			K = 40;
		}
		int Sa = 1;
		if (star > 0) {
			Sa = 1;
		} else {
			Sa = 0;
		}
		double ret = 0;
		if (isOffense) {
			if (star > 0) {
				ret = Ra + (K * (Sa - Ea)) * (star / 3.0);
			} else {
				ret = Ra + (K * (Sa - Ea));
			}
		} else {
			ret = Rb + K * (Sa - Eb);
		}
		return (int) ret;
	}
}
