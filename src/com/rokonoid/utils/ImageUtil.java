package com.rokonoid.utils;

import java.awt.image.BufferedImage;


public class ImageUtil {
	public static RGB[][] getRGB(BufferedImage image) {
		RGB[][] rgbs = new RGB[image.getHeight()][image.getWidth()];

		for (int row = 0; row < rgbs.length; row++) {
			for (int column = 0; column < rgbs[row].length; column++) {
				rgbs[row][column] = new RGB(image.getRGB(column, row));
			}
		}

		return rgbs;
	}

//	public static BufferedImage equalizeToRGB(BufferedImage unequalized) {
//		ColorValue colorValue = new ColorValue(unequalized);
//
//		Histogram redHistogram = new Histogram(colorValue.getR(), true);
//		Histogram greenHistogram = new Histogram(colorValue.getG(), true);
//		Histogram blueHistogram = new Histogram(colorValue.getB(), true);
//
//		int imageArea = unequalized.getWidth() * unequalized.getHeight();
//		int[] redEqualized = redHistogram.equalized(imageArea);
//		int[] greenEqualized = greenHistogram.equalized(imageArea);
//		int[] blueEqualized = blueHistogram.equalized(imageArea);
//
//		RGB[][] unequaizedRGB = colorValue.getRgb();
//		BufferedImage equalized =
//			new BufferedImage(unequalized.getWidth(), unequalized.getHeight(),
//				unequalized.getType());
//		for (int row = 0; row < unequaizedRGB.length; row++) {
//			for (int column = 0; column < unequaizedRGB[row].length; column++) {
//				int r = redEqualized[unequaizedRGB[row][column].r];
//				int g = greenEqualized[unequaizedRGB[row][column].g];
//				int b = blueEqualized[unequaizedRGB[row][column].b];
//				equalized.setRGB(column, row, RGB.toRGB(r, g, b));
//			}
//		}
//
//		return equalized;
//	}

//	public static BufferedImage equalizeToGrayScale(BufferedImage unequalized) {
//		ColorValue colorValue = new ColorValue(unequalized);
//
//		Histogram grayHistogram =
//			new Histogram(colorValue.getGrayScale(), true);
//		int[] grayEqualized =
//			grayHistogram.equalized(unequalized.getWidth(),
//				unequalized.getHeight());
//
//		BufferedImage equalized =
//			new BufferedImage(unequalized.getWidth(), unequalized.getHeight(),
//				unequalized.getType());
//
//		int[][] gray = colorValue.getGrayScale();
//		for (int row = 0; row < gray.length; row++) {
//			for (int column = 0; column < gray[row].length; column++) {
//				int v = grayEqualized[gray[row][column]];
//
//				equalized.setRGB(column, row, RGB.toRGB(v, v, v));
//			}
//		}
//
//		return equalized;
//	}

	public BufferedImage toNegative(BufferedImage image) {

		// BufferedImage negative = new BufferedImage(image.getWidth(),
		// image.getHeight(), BufferedImage.)

		return null;
	}
}
