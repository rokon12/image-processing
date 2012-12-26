package com.rokonoid.algorimthms;

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
 * Time: 7:43 PM
 */
public class EdgeDetectorTest {
    private File file;
    private String destination;

    @Before
    public void setUp() throws Exception {
        file = new File("/home/bazlur/Code/Java/image-processing/resources/edgeDetection.jpg");
        destination = "/home/bazlur/Desktop/image_processing";
    }

    @Test
    public void testDetect() throws Exception {
        BufferedImage image = ImageIO.read(file);
        BufferedImage imageConverted = EdgeDetector.detect(image);
        Utils.saveImage(imageConverted, destination);
    }
}
