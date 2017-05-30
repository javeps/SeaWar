package com.sea.tools;

import java.io.Serializable;

public class FormulaTools implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 差值等差
	 * 
	 * @param diff
	 *            等差数的等差值
	 * @param a
	 *            第一个数
	 * @param b
	 *            等差数的基数值
	 * @param n
	 *            当前数n
	 * @return 第n个数值
	 */
	public static double getValByDValueSameDiffer(double diff, double a, int b,
			int n) {
		if (n <= 1)
			return a;
		double dv = b + (n - 2) * diff;
		double v = a + dv;
		double r = getValByDValueSameDiffer(diff, v, b, --n);
		return r;
	}

	public static int getValByDValueSameDifferToInt(double diff, double a,
			int b, int n) {
		double r = getValByDValueSameDiffer(diff, a, b, n);
		if (r > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		return (int) r;
	}

	public static long getValByDValueSameDifferToLong(double diff, double a,
			int b, int n) {
		double r = getValByDValueSameDiffer(diff, a, b, n);
		if (r > Long.MAX_VALUE)
			return Long.MAX_VALUE;
		return (long) r;
	}

	/**
	 * 差值等比
	 * 
	 * @param rate
	 *            等比数的等比值(幂底数值)
	 * @param a
	 *            第一个数
	 * @param b
	 *            等比数的基数值
	 * @param n
	 *            当前数n
	 * @return 第n个数值
	 */
	public static double getValByDValueSameRate(double rate, double a, int b,
			int n) {
		if (n <= 1)
			return a;

		double dv = b * Math.pow(rate, (n - 2));
		double d = a + dv;
		double r = getValByDValueSameRate(rate, d, b, --n);
		return r;
	}

	public static int getValByDValueSameRateToInt(double rate, double a, int b,
			int n) {
		double r = getValByDValueSameRate(rate, a, b, n);
		if (r > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		return (int) r;
	}

	public static long getValByDValueSameRateToLong(double rate, double a,
			int b, int n) {
		double r = getValByDValueSameRate(rate, a, b, n);
		if (r > Long.MAX_VALUE)
			return Long.MAX_VALUE;
		return (long) r;
	}
}
