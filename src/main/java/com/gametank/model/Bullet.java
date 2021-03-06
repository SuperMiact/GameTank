package com.gametank.model;

import com.gametank.*;
import com.gametank.util.Explode;
import com.gametank.util.PropertyMgr;
import com.gametank.util.ResourceMgr;

import java.awt.*;

public class Bullet {
    private static final int SPEED = PropertyMgr.getInt("bulletSpeed");
    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();

    Rectangle rect = new Rectangle();

    private int x ,y ;
    private Dir dir;

    private boolean living = true;
    TankFrame tf = null;
    private Group group = null;

    public Bullet(int x, int y, Dir dir,Group group,TankFrame tf) {
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g){
        if (!living){
            tf.getBullets().remove(this);
        }

        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletD,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            default:
                break;
        }

        move();
    }

    private void move(){
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

        //更新rect,这样矩形就跟着子弹在不断移动，后面就不用在new了
        rect.x = this.x;
        rect.y = this.y;

        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            living = false;
        }
    }

    /**
     * 碰撞逻辑
     */
    public void collideWith(Bullet bullet,Tank tank) {
        System.out.println("bullet:"+ bullet.getGroup());
        System.out.println("tank:"+ tank.getGroup());
        if (bullet.getGroup()==tank.getGroup()) {
            return;
        }

        if (rect.intersects(tank.rect)){
            tank.die();
            this.die();
            int ex = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
            int ey = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
            tf.getExplodes().add(new Explode(ex,ey,tf));
        }
    }

    private void die() {
        this.living =false;
    }
}
