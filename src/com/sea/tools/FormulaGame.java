package com.sea.tools;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FormulaGame implements Serializable {

	private static final long serialVersionUID = 1L;

	public static List<Integer> getDesc(List<Integer> origin) {
		if (origin == null || origin.isEmpty())
			return origin;
		Collections.sort(origin, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1 > o2)
					return -1;
				if (o1 < o2)
					return 1;
				return 0;
			}
		});
		return origin;
	}

	public static int getClanRenown(List<Integer> origin) {
		int r = 0;
		if (origin == null || origin.isEmpty())
			return r;
		getDesc(origin);
		int len = origin.size();
		double sum = 0;
		for (int i = 0; i < len; i++) {
			int v = origin.get(i);
			// 前5名
			if (i < 3)
				sum += v * 0.8;
			else if (i < 8)
				sum += v * 0.7;
			else if (i < 15)
				sum += v * 0.6;
			else
				sum += v * 0.2;
		}
		r = (int) sum;
		return r;
	}

	public static int getClanRenown(Integer... origin) {
		int r = 0;
		if (origin == null || origin.length <= 0)
			return r;
		List<Integer> list = new ArrayList<Integer>();

		for (Integer item : origin) {
			list.add(item);
		}
		return getClanRenown(list);
	}

	public static int getClanRenown(int... origin) {
		int r = 0;
		if (origin == null || origin.length <= 0)
			return r;
		List<Integer> list = new ArrayList<Integer>();

		for (Integer item : origin) {
			list.add(item);
		}
		return getClanRenown(list);
	}

	public static int getRewardGemByTopClan(int clanTop, int topIndex) {
		int r = 0;
		switch (clanTop) {
		case 1:
			r = getRGemClanFirst(topIndex);
			break;
		case 2:
			r = getRGemClanSecond(topIndex);
			break;
		case 3:
			r = getRGemClanThree(topIndex);
			break;
		default:
			break;
		}
		return r;
	}

	private static int getRGemClanFirst(int topIndex) {
		if (topIndex <= 0)
			return 0;
		if (topIndex < 4)
			return 500;
		else if (topIndex < 11)
			return 260;
		else
			return 150;
	}

	private static int getRGemClanSecond(int topIndex) {
		if (topIndex <= 0)
			return 0;
		if (topIndex < 4)
			return 300;
		else if (topIndex < 11)
			return 150;
		else
			return 100;
	}

	private static int getRGemClanThree(int topIndex) {
		if (topIndex <= 0)
			return 0;
		if (topIndex < 6)
			return 200;
		else if (topIndex < 16)
			return 100;
		else
			return 50;
	}

	public static int getRewardGemByTopPlayerWeek(int topIndex) {
		int r = 0;
		if (topIndex <= 0)
			return r;
		r = (11 - topIndex) * 200;
		return r;
	}

	public static int getRewardGemByTopPlayerWeekNew(int topIndex) {
		int r = 0;
		if (topIndex <= 0)
			return r;
		r = (11 - topIndex) * 50;
		return r;
	}

	public static int getRewardGemByTopPlayerAll(int topIndex) {
		int r = 0;
		if (topIndex <= 0)
			return r;
		r = (6 - topIndex) * 100;
		return r;
	}

	/****** 资源换宝石 *****/
	public static int resToGems(int resources) {
		if (resources <= 0)
			return 0;

		int[] ranges = { 100, 1000, 10000, 100000, 1000000, 10000000 };
		int[] gems = { 1, 5, 25, 125, 600, 3000 };
		int lenRanges = ranges.length;
		int lenGems = gems.length;
		int storagemax = 8001000;
		int result;
		if (resources <= ranges[0]) {
			return gems[0];
		}

		int i = 0;
		if (ranges[0] < resources && resources <= ranges[1]) {
			i = 1;
		} else if (ranges[1] < resources && resources <= ranges[2]) {
			i = 2;
		} else if (ranges[2] < resources && resources <= ranges[3]) {
			i = 3;
		} else if (ranges[3] < resources && resources <= ranges[4]) {
			i = 4;
		}

		if (i > 0) {
			result = (int) Math
					.ceil((double) (resources - ranges[i - 1])
							/ ((double) (ranges[i] - ranges[i - 1]) / (double) (gems[i] - gems[i - 1]))
							+ gems[i - 1]);
			return result;
		}

		if (ranges[4] < resources && resources <= storagemax) {
			result = (int) Math
					.ceil((double) (resources - ranges[lenRanges - 2])
							/ ((double) (ranges[lenRanges - 1] - ranges[lenRanges - 2]) / (double) (gems[lenGems - 1] - gems[lenGems - 2]))
							+ gems[lenGems - 2]);
			return result;
		}

		if (storagemax < resources && resources <= ranges[5]) {
			i = 5;
			result = (int) Math
					.ceil((double) (resources - ranges[i - 1])
							/ ((double) (ranges[i] - ranges[i - 1]) / (double) (gems[i] - gems[i - 1]))
							+ gems[i - 1]);
			return result;
		}

		if (resources > ranges[5]) {
			return resToGems(resources % storagemax) + resources / storagemax
					* resToGems(storagemax);
		}
		return 0;
	}

	/****** 时间换宝石 *****/
	public static int timeToGems(int seconds) {
		if (seconds <= 0)
			return 0;
		int[] ranges = { 60, 3600, 86400, 604800 };// 单位秒
		int[] gems = { 1, 20, 260, 1000 };// 宝石档位
		int result = 0;
		if (seconds <= ranges[0]) {
			result = gems[0];
			return result;
		}
		int i = 0;
		if (ranges[0] < seconds && seconds <= ranges[1]) {
			i = 1;
		} else if (ranges[1] < seconds && seconds <= ranges[2]) {
			i = 2;
		} else {
			i = 3;
		}
		if (i > 0) {
			result = (int) Math
					.ceil((double) (seconds - ranges[i - 1])
							/ ((double) (ranges[i] - ranges[i - 1]) / (double) (gems[i] - gems[i - 1]))
							+ gems[i - 1]);
			return result;
		}

		return result;
	}
}
