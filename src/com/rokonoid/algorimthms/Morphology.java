package com.rokonoid.algorimthms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import javax.imageio.ImageIO;

public class Morphology {
	static boolean[][] mask = { { false, true, false }, { true, true, true },
			{ false, true, false } };

	public static boolean[][]
		toBinaryImage(BufferedImage input, int threhshold) {
		boolean[][] binary = new boolean[input.getHeight()][input.getWidth()];

		for (int y = 0; y < binary.length; y++) {
			for (int x = 0; x < binary[y].length; x++) {
				int rgb = input.getRGB(x, y);
				Color color = new Color(rgb);
				int avg =
					(color.getRed() + color.getGreen() + color.getBlue()) / 3;

				binary[y][x] = avg >= threhshold;
			}
		}

		return binary;
	}

	public static void write(boolean[][] binary) throws FileNotFoundException {

		for (int y = 0; y < binary.length; y++) {
			for (int x = 0; x < binary[y].length; x++) {
				System.out.print(binary[y][x] ? "*" : " ");
			}
			System.out.println();
		}
	}

	public static boolean[][] ditectBorder(boolean[][] input)
		throws FileNotFoundException {
		boolean[][] dilated = dilate(input);
		boolean[][] border = minus(dilated, input);

		return border;
	}

	// {{0,1,0},{1,1,1},{0,1,0}};
	public static boolean[][] dilate(boolean[][] input)
		throws FileNotFoundException {

		boolean[][] dilate = new boolean[input.length][input[0].length];

		for (int i = 0; i < dilate.length; i++) {
			for (int j = 0; j < dilate[j].length; j++) {
				dilate[i][j] = input[i][j];
			}
		}

		for (int y = 0; y < input.length - 2; y++) {
			for (int x = 0; x < input[y].length - 2; x++) {
				if (hit(input, x, y)) {
					fill(dilate, x, y);
				}
			}
		}

		return dilate;
	}

	public static void fill(boolean[][] input, int x, int y) {
		for (int dy = 0; dy < 3; dy++) {
			for (int dx = 0; dx < 3; dx++) {
				input[y + dy][x + dx] = true; // mask[dy][dx];
			}
		}
	}

	public static boolean hit(boolean[][] input, int x, int y) {
		for (int dy = 0; dy < 3; dy++) {
			for (int dx = 0; dx < 3; dx++) {
				if (input[y + dy][x + dx] && mask[dy][dx]) {
					return true;
				}
			}
		}

		return false;
	}

	public static boolean[][] minus(boolean[][] left, boolean[][] right) {
		boolean[][] minus = new boolean[left.length][left[0].length];

		for (int i = 0; i < minus.length; i++) {
			for (int j = 0; j < minus[i].length; j++) {
				if (left[i][j] && !right[i][j]) {
					minus[i][j] = true;
				}
			}
		}

		return minus;
	}

	public static void main(String[] args) throws IOException {

		BufferedImage input = ImageIO.read(new File("/home/bazlur/Code/Java/image-processing/resources/input.png"));

		boolean[][] binary = toBinaryImage(input, 10);
		boolean[][] dilate = dilate(binary);
		boolean[][] minus = minus(dilate, binary);

		System.setOut(new PrintStream(new File("out.txt")));
		write(binary);

		System.setOut(new PrintStream(new File("out_d.txt")));
		write(dilate);

		System.setOut(new PrintStream(new File("out_b.txt")));
		write(minus);
	}
}
