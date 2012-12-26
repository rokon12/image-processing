package com.rokonoid.utils;

import java.util.Arrays;

public class ArithmaticUtil {
	public static int[] getCumulativeForNonZero(int[] normal) {
		int firstNonZero = ArrayUtil.firstNonZeroIndex(normal);
		if (firstNonZero < 0) {
			return null;
		}
		
		int[] cumulative = Arrays.copyOf(normal, normal.length);
		int last = normal[firstNonZero];

		for (int i = firstNonZero + 1; i < cumulative.length; i++) {
			if (cumulative[i] != 0) {
				cumulative[i] += last;
				last = cumulative[i];
			}
		}

		return cumulative;
	}
}
