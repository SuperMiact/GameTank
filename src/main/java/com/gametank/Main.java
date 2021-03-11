package com.gametank;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();
        tankFrame.myTank= new Tank(200,500,Dir.DOWN,Group.GOOD,tankFrame);
        //初始化敌方
        for (int i = 0; i < 5; i++) {
            tankFrame.tanks.add(new Tank(50+i*80,200,Dir.DOWN,Group.BAD,tankFrame));
//            tankFrame.tanks.get(tankFrame.tanks.size()-1).setMoving(true);
        }
    
        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }

    }
}
