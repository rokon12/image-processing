package com.rokonoid.negetive;

import com.rokonoid.utils.Utils;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Bazlur Rahman Rokon
 * Date: 12/26/12
 * Time: 9:58 AM
 */
public class HistogramEqualizationTest {
    private File file;
    private String destination;

    @Before
    public void setUp() throws Exception {
        file = new File("/home/bazlur/Desktop/rokon.jpg");
        destination = "/home/bazlur/Desktop/image_processing";
    }

    @Test
    public void testHistogramEqualization() throws Exception {
        BufferedImage image = ImageIO.read(file);
        BufferedImage imageConverted = HistogramEqualization.histogramEqualization(image);
        Utils.saveImage(imageConverted, destination);
    }
}
