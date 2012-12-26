package com.rokonoid.algorimthms;

import com.rokonoid.utils.ColorValue;
import com.rokonoid.utils.RGB;

import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: Bazlur Rahman Rokon
 * Date: 12/26/12
 * Time: 5:10 PM
 */
public class PointDetector {

    public static BufferedImage detect(BufferedImage input) {
        ColorValue imageInfo = new ColorValue(input);
        RGB[][] pixels = imageInfo.getRgb();
        RGB[][] writePixels = new RGB[input.getWidth()][input.getHeight()];

        int[][] mask = {{-1, -1, -1}, {-1, 8, -1}, {-1, -1, -1}};

        int T = 50, R = 0;
        for (int i = 0; i < input.getWidth(); i++) {
            writePixels[i][0] = new RGB(255, 255, 255);
        }

        for (int i = 0; i < input.getHeight(); i++) {
            writePixels[0][i] = new RGB(255, 255, 255);
        }

        for (int i = 1; i < input.getWidth() - 1; i++) {
            for (int j = 1; j < input.getHeight() - 1; j++) {
                R =
                        mask[0][0] * pixels[i - 1][j - 1].grayScale + mask[0][1]
                                * pixels[i - 1][j].grayScale + mask[0][2]
                                * pixels[i - 1][j + 1].grayScale;
                R +=
                        mask[1][0] * pixels[i][j - 1].grayScale + mask[1][1]
                                * pixels[i][j].grayScale + mask[1][2]
                                * pixels[i][j + 1].grayScale;
                R +=
                        mask[2][0] * pixels[i + 1][j - 1].grayScale + mask[2][1]
                                * pixels[i + 1][j].grayScale + mask[2][2]
                                * pixels[i + 1][j + 1].grayScale;
                if (Math.abs(R) >= T) {
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

        BufferedImage writeImage =
                new BufferedImage(input.getWidth(), input.getHeight(),
                        input.getType());
        for (int row = 0; row < writePixels.length; row++) {
            for (int column = 0; column < writePixels[row].length; column++) {
                writeImage.setRGB(column, row, writePixels[row][column].rgb);
            }
        }

        return writeImage;
    }
}
