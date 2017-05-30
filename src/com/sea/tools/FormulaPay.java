package com.sea.tools;

/**
 * 充值公式
 * 
 * @author Administrator
 * 
 */
public class FormulaPay {

	public final static int Basics_Rate = 15;
	// 阶位rank,steps
	public final static int Steps_6 = 6;
	public final static int Steps_30 = 30;
	public final static int Steps_68 = 68;
	public final static int Steps_128 = 128;
	public final static int Steps_328 = 328;
	public final static int Steps_648 = 648;

	public static double getGemByRate(double money) {
		int m = (int) money;
		double v = 0;
		if (m < Steps_6) {
			v = getGemNormer(m);
		} else if (m < Steps_30) {
			v = getGemSmall(m);
		} else if (m < Steps_68) {
			v = getGemLittle(m);
		} else if (m < Steps_128) {
			v = getGemPetty(m);
		} else if (m < Steps_328) {
			v = getGemMiddle(m);
		} else {
			v = getGemBig(m);
		}

		return v;
	}

	public static double getGemNormer(int m) {
		return m * Basics_Rate;
	}

	private static double getGemSmallGive(int m) {
		double d = (m - Steps_6) * 1.5;
		int v = (int) d;
		return v;
	}

	private static double getGemSmall(int m) {
		// 6~29
		return 100 + (m - Steps_6) * Basics_Rate + getGemSmallGive(m);
	}

	private static double getGemLittleGive(int m) {
		// 30~67
		return (m - Steps_30) * 3;
	}

	private static double getGemLittle(int m) {
		// 30~67
		return 500 + (m - Steps_30) * Basics_Rate + getGemLittleGive(m);
	}

	private static double getGemPettyGive(int m) {
		// 68~127
		double d = (m - Steps_68) * 6.5;
		int v = (int) d;
		return v;
	}

	private static double getGemPetty(int m) {
		// 68~127
		return 1200 + (m - Steps_68) * Basics_Rate + getGemPettyGive(m);
	}

	private static double getGemMiddleGive(int m) {
		// 128~327
		return (m - Steps_128) * 5;
	}

	private static double getGemMiddle(int m) {
		// 128~327
		return 2500 + (m - Steps_128) * Basics_Rate + getGemMiddleGive(m);
	}

	private static double getGemBigGive(int m) {
		// 328~
		double d = (m - Steps_328) * 8.5;
		int v = (int) d;
		return v;
	}

	private static double getGemBig(int m) {
		// 328~
		return 6500 + (m - Steps_328) * Basics_Rate + getGemBigGive(m);
	}
}
