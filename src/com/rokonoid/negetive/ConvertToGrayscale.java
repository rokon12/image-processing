package com.rokonoid.negetive;

import com.rokonoid.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: Bazlur Rahman Rokon
 * Date: 12/25/12
 * Time: 11:58 PM
 */
public class ConvertToGrayscale {
    public static BufferedImage avg(BufferedImage image) {
        BufferedImage avgGray = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        int[] avgLUT = new int[766];
        for (int i = 0; i < avgLUT.length; i++) {
            avgLUT[i] = (int) (i / 3);
        }
        int red, green, blue, alpha;
        int newPixel;

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                alpha = new Color(image.getRGB(i, j)).getAlpha();
                red = new Color(image.getRGB(i, j)).getRed();
                green = new Color(image.getRGB(i, j)).getGreen();
                blue = new Color(image.getRGB(i, j)).getBlue();
                newPixel = red + green + blue;
                newPixel = avgLUT[newPixel];
                newPixel = Utils.createRGB(alpha, red, green, blue);
                avgGray.setRGB(i, j, newPixel);

            }
        }


        return avgGray;
    }

    private static BufferedImage luminosity(BufferedImage original) {

        int alpha, red, green, blue;
        int newPixel;

        BufferedImage lum = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());

        for (int i = 0; i < original.getWidth(); i++) {
            for (int j = 0; j < original.getHeight(); j++) {

                // Get pixels by R, G, B
                alpha = new Color(original.getRGB(i, j)).getAlpha();
                red = new Color(original.getRGB(i, j)).getRed();
                green = new Color(original.getRGB(i, j)).getGreen();
                blue = new Color(original.getRGB(i, j)).getBlue();

                red = (int) (0.21 * red + 0.71 * green + 0.07 * blue);
                // Return back to original format
                newPixel = Utils.createRGB(alpha, red, red, red);

                // Write pixels into image
                lum.setRGB(i, j, newPixel);

            }
        }

        return lum;

    }

    // The desaturation method
    private static BufferedImage desaturation(BufferedImage original) {

        int alpha, red, green, blue;
        int newPixel;

        int[] pixel = new int[3];

        BufferedImage des = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
        int[] desLUT = new int[511];
        for (int i = 0; i < desLUT.length; i++) desLUT[i] = (int) (i / 2);

        for (int i = 0; i < original.getWidth(); i++) {
            for (int j = 0; j < original.getHeight(); j++) {

                // Get pixels by R, G, B
                alpha = new Color(original.getRGB(i, j)).getAlpha();
                red = new Color(original.getRGB(i, j)).getRed();
                green = new Color(original.getRGB(i, j)).getGreen();
                blue = new Color(original.getRGB(i, j)).getBlue();

                pixel[0] = red;
                pixel[1] = green;
                pixel[2] = blue;

                int newval = (int) (findMax(pixel) + findMin(pixel));
                newval = desLUT[newval];

                // Return back to original format
                newPixel = Utils.createRGB(alpha, newval, newval, newval);

                // Write pixels into image
                des.setRGB(i, j, newPixel);

            }
        }

        return des;

    }

    private static int findMin(int[] pixel) {

        int min = pixel[0];

        for (int i = 0; i < pixel.length; i++) {
            if (pixel[i] < min)
                min = pixel[i];
        }

        return min;

    }

    private static int findMax(int[] pixel) {

        int max = pixel[0];

        for (int i = 0; i < pixel.length; i++) {
            if (pixel[i] > max)
                max = pixel[i];
        }

        return max;
    }


}
