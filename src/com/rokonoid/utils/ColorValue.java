package com.rokonoid.utils;

import java.awt.image.BufferedImage;

public class ColorValue {
	private RGB[][] rgb;
	private int[][] r;
	private int[][] g;
	private int[][] b;

	private int grayScale[][];

	public ColorValue(BufferedImage image) {
		rgb = ImageUtil.getRGB(image);
		r = new int[image.getHeight()][image.getWidth()];
		g = new int[image.getHeight()][image.getWidth()];
		b = new int[image.getHeight()][image.getWidth()];

		grayScale = new int[image.getHeight()][image.getWidth()];

		for (int row = 0; row < rgb.length; row++) {
			for (int column = 0; column < rgb[row].length; column++) {
				r[row][column] = rgb[row][column].r;
				g[row][column] = rgb[row][column].g;
				b[row][column] = rgb[row][column].b;

				grayScale[row][column] = 
					(r[row][column] + g[row][column] + b[row][column]) / 3;
			}
		}
	}

	public RGB[][] getRgb() {
		return rgb;
	}

	public int[][] getR() {
		return r;
	}

	public int[][] getG() {
		return g;
	}

	public int[][] getB() {
		return b;
	}
	
	public int minGrayScale(){
		int min = 256;
		for (int[] row : grayScale) {
			for (int g : row) {
				if (g < min) {
					min = g;
				}
			}
		}
		
		return min;
	}
	
	public int maxGrayScale(){
		int max = -1;
		for (int[] row : grayScale) {
			for (int g : row) {
				if (g < max) {
					max = g;
				}
			}
		}
		
		return max;
	}

	public int[][] getGrayScale() {
		return grayScale;
	}
}
