package com.rokonoid.algorimthms;

import com.rokonoid.utils.ColorValue;
import com.rokonoid.utils.RGB;

import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: Bazlur Rahman Rokon
 * Date: 12/26/12
 * Time: 7:41 PM
 */
public class EdgeDetector {
    private static boolean diagonal = false;
    private static int T = 200;

    public static int[][] normalX() {
        int[][] xMask = {
                {-1, -2, -1},
                {0, 0, 0},
                {1, 2, 1}};
        return xMask;
    }

    public static int[][] diagonalX() {
        int[][] xMask = {
                {0, -1, -2},
                {1, 0, -1},
                {2, 1, 0}};
        return xMask;
    }

    public static int[][] normalY() {
        int[][] yMask = {
                {-1, 0, 1},
                {-2, 0, 2},
                {-1, 0, 1}
        };
        return yMask;
    }

    public static int[][] diagonalY() {
        int[][] yMask = {
                {-2, -1, 0},
                {-1, 0, 1},
                {0, 1, 2}
        };
        return yMask;
    }

    public static void setDiagonal(boolean diagonal) {
        EdgeDetector.diagonal = diagonal;
    }

    public static BufferedImage detect(BufferedImage input) {
        ColorValue colorValue = new ColorValue(input);
        RGB[][] pixels = colorValue.getRgb();
        RGB[][] writePixels = new RGB[input.getWidth()][input.getHeight()];

        int[][] xMask, yMask;

        if (diagonal) {
            xMask = diagonalX();
            yMask = diagonalY();
        } else {
            xMask = normalX();
            yMask = normalY();
        }

        for (int i = 0; i < input.getWidth(); i++) {
            writePixels[i][0] = new RGB(255, 255, 255);
        }

        for (int i = 0; i < input.getHeight(); i++) {
            writePixels[0][i] = new RGB(255, 255, 255);
        }

        int gX = 0, gY = 0;/*
                                     * getThreshold(pixels, input.getWidth(),
									 * input.getHeight());
									 */

        for (int i = 1; i < input.getWidth() - 1; i++) {
            for (int j = 1; j < input.getHeight() - 1; j++) {
                gX = xMask[0][0] * pixels[i - 1][j - 1].grayScale
                        + xMask[0][1] * pixels[i - 1][j].grayScale
                        + xMask[0][2] * pixels[i - 1][j + 1].grayScale;
                gX += xMask[1][0] * pixels[i][j - 1].grayScale + xMask[1][1]
                        * pixels[i][j].grayScale + xMask[1][2]
                        * pixels[i][j + 1].grayScale;
                gX += xMask[2][0] * pixels[i + 1][j - 1].grayScale
                        + xMask[2][1] * pixels[i + 1][j].grayScale
                        + xMask[2][2] * pixels[i + 1][j + 1].grayScale;

                gY = yMask[0][0] * pixels[i - 1][j - 1].grayScale
                        + yMask[0][1] * pixels[i - 1][j].grayScale
                        + yMask[0][2] * pixels[i - 1][j + 1].grayScale;
                gY += yMask[1][0] * pixels[i][j - 1].grayScale + yMask[1][1]
                        * pixels[i][j].grayScale + yMask[1][2]
                        * pixels[i][j + 1].grayScale;
                gY += yMask[2][0] * pixels[i + 1][j - 1].grayScale
                        + yMask[2][1] * pixels[i + 1][j].grayScale
                        + yMask[2][2] * pixels[i + 1][j + 1].grayScale;

                if (Math.sqrt(gX * gX + gY * gY) >= T) {
                    // System.out.println("Point detected at (" + i + "," + j +
                    // ")");
                    writePixels[i][j] = new RGB(0, 0, 0);
                } else {
                    writePixels[i][j] = new RGB(255, 255, 255);
                }
            }
        }

        int l = input.getHeight() - 1;
        for (int i = 0; i < input.getWidth(); i++) {
            writePixels[i][l] = new RGB(255, 255, 255);
        }

        l = input.getWidth() - 1;
        for (int i = 0; i < input.getHeight(); i++) {
            writePixels[l][i] = new RGB(255, 255, 255);
        }

        BufferedImage output =
                new BufferedImage(input.getWidth(), input.getHeight(),
                        input.getType());

        for (int row = 0; row < writePixels.length; row++) {
            for (int column = 0; column < writePixels[row].length; column++) {
                output.setRGB(column, row, writePixels[row][column].rgb);
            }
        }

        return output;
    }
}
