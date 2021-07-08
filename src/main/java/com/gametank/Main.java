package com.gametank;

import com.gametank.model.Dir;
import com.gametank.model.Group;
import com.gametank.model.Tank;
import com.gametank.util.PropertyMgr;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();

        int initTankCount = PropertyMgr.getInt("initTankCount");

        //初始化敌方
        for (int i = 0; i < initTankCount; i++) {
            tankFrame.tanks.add(new Tank(50+i*80,200, Dir.DOWN, Group.BAD,tankFrame));
        }

        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }

    }
}
