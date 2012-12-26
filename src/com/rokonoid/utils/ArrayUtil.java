package com.rokonoid.utils;

public class ArrayUtil {
	public static void magnify(int[] array, double magnigier) {
		for (int i = 0; i < array.length; i++) {
			array[i] *= magnigier;
		}
	}

	public static int firstNonZeroIndex(int[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] != 0) {
				return i;
			}
		}

		return -1;
	}

	public static int lastNonZeroIndex(int[] array) {
		for (int i = array.length - 1; i >= 0; i--) {
			if (array[i] != 0) {
				return i;
			}
		}

		return -1;
	}
}
