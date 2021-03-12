package com.gametank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {
    public static BufferedImage tankL,tankU,tankR,tankD;
    public static BufferedImage bulletL,bulletU,bulletR,bulletD;
    public static BufferedImage[] explodes = new BufferedImage[11];

    static {
        try {
            tankU= ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
            tankL= ImageUtil.rotateImage(tankU,-90);
            tankR= ImageUtil.rotateImage(tankU,90);
            tankD= ImageUtil.rotateImage(tankU,180);

            bulletU= ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletL= ImageUtil.rotateImage(bulletU,-90);
            bulletR= ImageUtil.rotateImage(bulletU,90);
            bulletD= ImageUtil.rotateImage(bulletU,180);

            for (int i = 0; i < 11; i++) {
                explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/"+i+".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
