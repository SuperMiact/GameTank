package com.gametank.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 处理图片类
 */
public class ResourceMgr {
    public static BufferedImage goodTankL,goodTankU,goodTankR,goodTankD;
    public static BufferedImage badTankL,badTankU,badTankR,badTankD;
    public static BufferedImage bulletL,bulletU,bulletR,bulletD;
    public static BufferedImage[] explodes = new BufferedImage[11];

    static {
        try {
            goodTankU= ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTankU.gif"));
            goodTankL= ImageUtil.rotateImage(goodTankU,-90);
            goodTankR= ImageUtil.rotateImage(goodTankU,90);
            goodTankD= ImageUtil.rotateImage(goodTankU,180);

            badTankU= ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTankU.gif"));
            badTankL= ImageUtil.rotateImage(badTankU,-90);
            badTankR= ImageUtil.rotateImage(badTankU,90);
            badTankD= ImageUtil.rotateImage(badTankU,180);

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
