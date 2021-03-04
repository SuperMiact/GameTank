package com.gametank;

import java.awt.*;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    int x = 200, y = 200;
    Dir dir = Dir.DOWN;
    private static final int SPEED = 10;

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

        g.fillRect(x,y,50,50);

        switch (dir){
            case LEFT:
                x-=SPEED;
                break;
            case RIGHT:
                x+=SPEED;
                break;
            case UP:
                y-=SPEED;
                break;
            case DOWN:
                y+=SPEED;
                break;
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
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir(){
            if(bL) dir = Dir.LEFT;
            if(bR) dir = Dir.RIGHT;
            if(bU) dir = Dir.UP;
            if(bD) dir = Dir.DOWN;
        }

    }

}
