package com.gametank;

import java.awt.*;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    Trank myTank = new Trank(200,200,Dir.DOWN);
    Bullet b = new Bullet(300,300,Dir.DOWN);

    public TankFrame(){
        setSize(800,600);
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

    @Override
    public void paint(Graphics g) {
        myTank.paint(g);
        b.paint(g);
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
