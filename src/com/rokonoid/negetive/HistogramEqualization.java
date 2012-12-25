package com.rokonoid.negetive;

import com.rokonoid.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Bazlur Rahman Rokon
 * Date: 12/26/12
 * Time: 12:46 AM
 * <p/>
 * <p/>
 * Histogram equalization can be done in three steps:
 * <p/>
 * 1. compute histogram
 * 2. calculate the normalized sum of the histogram
 * 3. Transform input image to output image
 */
public class HistogramEqualization {
    public static ArrayList<int[]> imageHistogram(BufferedImage input) {
        int[] rh = new int[256];
        int[] gh = new int[256];
        int[] bh = new int[256];

        for (int i = 0; i < rh.length; i++) {
            rh[i] = 0;
        }
        for (int i = 0; i < gh.length; i++) {
            gh[i] = 0;
        }
        for (int i = 0; i < bh.length; i++) {
            bh[i] = 0;
        }

        for (int i = 0; i < input.getWidth(); i++) {
            for (int j = 0; j < input.getHeight(); j++) {

                int red = new Color(input.getRGB(i, j)).getRed();
                int green = new Color(input.getRGB(i, j)).getGreen();
                int blue = new Color(input.getRGB(i, j)).getBlue();

                // Increase the values of colors
                rh[red]++;
                gh[green]++;
                bh[blue]++;
            }
        }

        ArrayList<int[]> hist = new ArrayList<int[]>();
        hist.add(rh);
        hist.add(gh);
        hist.add(bh);

        return hist;
    }

    private static ArrayList<int[]> histogramEqualizationLUT(BufferedImage image) {
        // Get an image histogram - calculated values by R, G, B channels
        ArrayList<int[]> imageHist = imageHistogram(image);

        // Create the lookup table
        ArrayList<int[]> imageLUT = new ArrayList<int[]>();

        // Fill the lookup table
        int[] rhistogram = new int[256];
        int[] ghistogram = new int[256];
        int[] bhistogram = new int[256];

        for (int i = 0; i < rhistogram.length; i++) rhistogram[i] = 0;
        for (int i = 0; i < ghistogram.length; i++) ghistogram[i] = 0;
        for (int i = 0; i < bhistogram.length; i++) bhistogram[i] = 0;

        long sumr = 0;
        long sumg = 0;
        long sumb = 0;

        // Calculate the scale factor
        float scale_factor = (float) (255.0 / (image.getWidth() * image.getHeight()));

        for (int i = 0; i < rhistogram.length; i++) {
            sumr += imageHist.get(0)[i];
            int valr = (int) (sumr * scale_factor);
            if (valr > 255) {
                rhistogram[i] = 255;
            } else rhistogram[i] = valr;

            sumg += imageHist.get(1)[i];
            int valg = (int) (sumg * scale_factor);
            if (valg > 255) {
                ghistogram[i] = 255;
            } else ghistogram[i] = valg;

            sumb += imageHist.get(2)[i];
            int valb = (int) (sumb * scale_factor);
            if (valb > 255) {
                bhistogram[i] = 255;
            } else bhistogram[i] = valb;
        }

        imageLUT.add(rhistogram);
        imageLUT.add(ghistogram);
        imageLUT.add(bhistogram);

        return imageLUT;

    }

    private static BufferedImage histogramEqualization(BufferedImage original) {

        int red;
        int green;
        int blue;
        int alpha;
        int newPixel = 0;

        // Get the Lookup table for histogram equalization
        ArrayList<int[]> histLUT = histogramEqualizationLUT(original);

        BufferedImage histogramEQ = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());

        for(int i=0; i<original.getWidth(); i++) {
            for(int j=0; j<original.getHeight(); j++) {

                // Get pixels by R, G, B
                alpha = new Color(original.getRGB (i, j)).getAlpha();
                red = new Color(original.getRGB (i, j)).getRed();
                green = new Color(original.getRGB (i, j)).getGreen();
                blue = new Color(original.getRGB (i, j)).getBlue();

                // Set new pixel values using the histogram lookup table
                red = histLUT.get(0)[red];
                green = histLUT.get(1)[green];
                blue = histLUT.get(2)[blue];

                // Return back to original format
                newPixel = Utils.colorToRGB(alpha, red, green, blue);

                // Write pixels into image
                histogramEQ.setRGB(i, j, newPixel);

            }
        }

        return histogramEQ;
    }

    public static void main(String[] args) {
        BufferedImage image =Utils. getImage("/home/rokon/Desktop/14715_4593770536066_1506144814_n.jpg");
        BufferedImage newImage = histogramEqualization(image);
        Utils.saveImage(newImage,"/home/rokon/Desktop");
    }

}
