package com.gametank;

import java.awt.*;

public class Trank {
    private int x, y;
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 10;

    public Trank(int x,int y,Dir dir){
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public Dir getDir(){
        return dir;
    }

    public void setDir(Dir dir){
        this.dir = dir;
    }

    public void paint(Graphics g){
        //TODO Auto-generated method stub
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
}
