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
 * Time: 9:29 AM
 */
public class ConvertToGrayscaleTest {

    private File file;
    private String destination;

    @Before
    public void before() {
        file = new File("/home/bazlur/Desktop/rokon.jpg");
        destination = "/home/bazlur/Desktop/image_processing";
    }

    @Test
    public void testAvg() throws Exception {
        BufferedImage image = ImageIO.read(file);
        BufferedImage imageConverted = ConvertToGrayscale.avg(image);

        Utils.saveImage(imageConverted, destination);
    }

    @Test
    public void testLuminosity() throws Exception {
        BufferedImage image = ImageIO.read(file);
        BufferedImage imageConverted = ConvertToGrayscale.luminosity(image);

        Utils.saveImage(imageConverted, destination);
    }

    @Test
    public void testDesaturation() throws Exception {
        BufferedImage image = ImageIO.read(file);
        BufferedImage imageConverted = ConvertToGrayscale.desaturation(image);

        Utils.saveImage(imageConverted, destination);
    }
}
