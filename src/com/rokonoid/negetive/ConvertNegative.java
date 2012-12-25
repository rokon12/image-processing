package com.rokonoid.negetive;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Bazlur Rahman Rokon
 * Date: 12/25/12
 * Time: 9:57 PM
 */
public class ConvertNegative {
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

    public static void saveImage(BufferedImage image, String destination) {
        File file = new File(destination + "/converted");
        try {
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage getNegetiveImage(BufferedImage image) {
        int w1 = image.getWidth();
        int h1 = image.getHeight();
        BufferedImage gray = new BufferedImage(w1, h1, 1);
        int value, alpha, r, g, b;
        for (int i = 0; i < w1; i++) {
            for (int j = 0; j < h1; j++) {
                value = image.getRGB(i, j);
                alpha = getAplha(value);
                r = 255 - getRed(value);
                g = 255 - getGreen(value);
                b = 255 - getBlue(value);
                value = createRGB(alpha, r, g, b);
                gray.setRGB(i, j, value);

                System.out.println("r=" + r + ", g=" + g + ", b=" + b +" value-"+ value + " value(getRed()) "+getRed(value));
            }
        }
        return gray;
    }

    public static int createRGB(int alpha, int r, int g, int b) {
        int rgb = (alpha << 24) + (r << 16) + (g << 8) + b;
        return rgb;
    }

    private static int getAplha(int rgb) {
        return (rgb >> 24) & 255;
    }

    public static int getRed(int rgb) {
        return (rgb >> 16) & 255;
    }

    public static int getGreen(int rgb) {
        return (rgb >> 8) & 255;
    }

    public static int getBlue(int rgb) {
        return rgb & 255;
    }

    public static void main(String[] args) {
        BufferedImage image = getImage("/home/rokon/Desktop/14715_4593770536066_1506144814_n.jpg");
        saveImage(getNegetiveImage(image), "/home/rokon/Desktop");
        System.out.println(getRed(255));
    }
}
