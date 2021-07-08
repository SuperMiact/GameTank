package com.gametank.util;

import java.awt.*;
import java.awt.image.BufferedImage;


public class ImageUtil {
    /**
     * @param bufferedImage 传进来的Image
     * @param degree    旋转的角度
     * @return
     */
    public static BufferedImage rotateImage(final BufferedImage bufferedImage,
                  final int degree){
        //把向上的图片load到内存，然后向右旋转90度，就是向右
        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();
        int type = bufferedImage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(w,h,type)).createGraphics()).setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(degree),w / 2,h / 2);
        graphics2d.drawImage(bufferedImage,0,0,null);
        graphics2d.dispose();
        return img;
    }
}
