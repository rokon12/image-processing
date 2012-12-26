package com.rokonoid.algorimthms;

import com.rokonoid.utils.Utils;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Bazlur Rahman Rokon
 * Date: 12/26/12
 * Time: 7:54 PM
 */
public class CornerDetectorTest {


    private File file;
    private String destination;

    @Before
    public void setUp() throws Exception {
        file = new File("/home/bazlur/Code/Java/image-processing/resources/corner.png");
        destination = "/home/bazlur/Desktop/image_processing";
    }

    @Test
    public void detect() throws IOException {
        BufferedImage input = ImageIO.read(file);
        int width = input.getWidth();
        int height = input.getHeight();

        int[] in = new int[width * height];
        input.getRGB(0, 0, width, height, in, 0, width);

        Harris harris = new Harris();
        harris.init(in, width, height, .16);

        int[] out = harris.process();

        BufferedImage outImage =
                new BufferedImage(width, height, input.getType());
        outImage.setRGB(0, 0, width, height, out, 0, width);

        Utils.saveImage(outImage, destination);
    }
}
