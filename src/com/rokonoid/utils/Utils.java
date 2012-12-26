package com.rokonoid.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Bazlur Rahman Rokon
 * Date: 12/25/12
 * Time: 11:45 PM
 */
public class Utils {
    public static BufferedImage getImage(String source) {
        File input = new File(source);
        BufferedImage image = null;
        try {
            image = ImageIO.read(input);
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int createRGB(int alpha, int r, int g, int b) {
        int rgb = (alpha << 24) + (r << 16) + (g << 8) + b;
        return rgb;
    }

    public static void saveImage(BufferedImage image, String destination) {
        File file = new File(destination + "/converted.png");
        if (file.exists()) {
            file = new File(destination + "/converted " + Math.random() + ".png");
        }

        try {
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Convert R, G, B, Alpha to standard 8 bit
    public static int colorToRGB(int alpha, int red, int green, int blue) {

        int newPixel = 0;
        newPixel += alpha;
        newPixel = newPixel << 8;
        newPixel += red;
        newPixel = newPixel << 8;
        newPixel += green;
        newPixel = newPixel << 8;
        newPixel += blue;

        return newPixel;

    }
}
