package com.gametank;

import java.awt.*;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

    Tank myTank = new Tank(200,500,Dir.DOWN,Group.GOOD,this);
    List<Bullet> bullets = new ArrayList<Bullet>();
    List<Tank> tanks = new ArrayList<Tank>();
    static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;

    public TankFrame(){
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setResizable(false);
        setTitle("TankWar");
        setVisible(true);

        this.addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if (offScreenImage==null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.RED);
        g.drawString("子弹的数量："+bullets.size(),10,60);
        g.drawString("敌人的数量："+tanks.size(),10,80);
        g.setColor(color);

        myTank.paint(g);
        for (int i = 0 ;i<bullets.size();i++){
            bullets.get(i).paint(g);
        }
        for (int i = 0 ;i<tanks.size();i++){
            tanks.get(i).paint(g);
        }

        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collideWith(tanks.get(j));
            }

        }

    }

    class MyKeyListener extends KeyAdapter {

        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        boolean bD = false;

        @Override
        //这个方法是系统调用我们，所以系统会通知我们哪个键被按下了
        //既然是系统调用我们自己的方法，那系统会给我们传一个KeyEvent这个事件
        public void keyPressed(KeyEvent e) {
            //哪个键被按下了，把它的代码取出来
            int key = e.getKeyCode();
            switch (key){
                //向左 向右 向上 向下
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            //改变坦克方向
            setMainTankDir();
        }

        @Override
        //按键弹起
        public void keyReleased(KeyEvent e) {
            //哪个键被按下了，把它的代码取出来
            int key = e.getKeyCode();
            switch (key){
                //向左 向右 向上 向下
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_SPACE:
                    myTank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir(){
            if (!bL && !bR && !bU && !bD) myTank.setMoving(false);
            else {
                myTank.setMoving(true);

                if(bL) myTank.setDir(Dir.LEFT);
                if(bR) myTank.setDir(Dir.RIGHT);
                if(bU) myTank.setDir(Dir.UP);
                if(bD) myTank.setDir(Dir.DOWN);
            }
        }
    }
}
