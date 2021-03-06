package com.gametank;

import org.junit.Test;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class ImageTest {

    @Test
    public void t1(){
        try {
            BufferedImage image = ImageIO.read(new File("src/main/resources/images/bulletU.png"));
            //判断image不为空
            assertNotNull(image);

            BufferedImage image2 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            //this.getClass()
            assertNotNull(image2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
