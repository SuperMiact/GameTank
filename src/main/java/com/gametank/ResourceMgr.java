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
            tankL= ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
            tankU= ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
            tankR= ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
            tankD= ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankD.gif"));

            bulletL= ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/missileL.gif"));
            bulletU= ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/missileD.gif"));
            bulletR= ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/missileR.gif"));
            bulletD= ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/missileU.gif"));

            for (int i = 0; i < 11; i++)
                explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/"+i+".gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
