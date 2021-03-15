package com.gametank;

import java.awt.*;
import java.util.Random;

public class Tank {
    private Random random = new Random();
    private int x, y;
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 5;

    Rectangle rect = new Rectangle();

    public static int WIDTH = ResourceMgr.goodTankD.getWidth();
    public static int HEIGHT = ResourceMgr.goodTankD.getHeight();

    private boolean moving = true;
    private TankFrame tf = null;
    private boolean living = true;
    private Group group = Group.BAD;

    public Tank(int x, int y, Dir dir,Group group, TankFrame tf){
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir(){
        return dir;
    }

    public void setDir(Dir dir){
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g){
        if (!living){
            tf.tanks.remove(this);
        }
        switch (dir){
            case LEFT:
                g.drawImage(this.group == Group.GOOD?ResourceMgr.goodTankL:ResourceMgr.badTankL,x,y,null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD?ResourceMgr.goodTankU:ResourceMgr.badTankU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD?ResourceMgr.goodTankR:ResourceMgr.badTankR,x,y,null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD?ResourceMgr.goodTankD:ResourceMgr.badTankD,x,y,null);
                break;
            default:
                break;
        }
        move();
    }

    private void move(){
        if (!moving) {
            return;
        }
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
            default:
                break;
        }
        if (this.group == Group.BAD && random.nextInt(100) > 95) {
            this.fire();
        }
        if (this.group == Group.BAD && random.nextInt(100) > 95){
            randomDir();
        }

        boundsCheck();

        rect.x = this.x;
        rect.y = this.y;
    }

    private void boundsCheck() {
        if (this.x < 0){
            x = 0;
        }
        if (this.y < 30){
            y = 30;
        }
        if (this.x > TankFrame.GAME_WIDTH-Tank.WIDTH){
            x = TankFrame.GAME_WIDTH-Tank.WIDTH;
        }
        if (this.y > TankFrame.GAME_HEIGHT-Tank.HEIGHT){
            y = TankFrame.GAME_HEIGHT-Tank.HEIGHT;
        }
    }

    private void randomDir() {
        //[LEFT,UP,RIGHT,DOWN] 随机取一个下标，随机换一个方向
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        int bx = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int by = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;

        tf.bullets.add(new Bullet(bx,by,this.dir,this.group,this.tf));
    }

    public void die() {
        this.living=false;
    }
}
