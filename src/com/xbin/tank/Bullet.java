package com.xbin.tank;

import java.awt.*;

/**
 * @ClassName Bullet
 * @Descrption 子弹类
 * @Author xiebin
 * @Date 2020/9/9 22:36
 * @Version 1.0
 **/
public class Bullet {

    private int x,y;

    public static final int WEITH=ResourceMan.bulletD.getWidth();

    public static final int HEIGTH=ResourceMan.bulletD.getHeight();

    private static final int SPEED=10;

    private Dir dir;

    private boolean live=true;

    private Group group;

    Rectangle rectangle=new Rectangle();

    TankFrame tankFrame;

    public Bullet(int x, int y, Dir dir,Group group,TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame=tankFrame;
        this.group=group;

        rectangle.x=x;
        rectangle.y=y;
        rectangle.width=WEITH;
        rectangle.height=HEIGTH;
    }


    public void paint(Graphics g) {
        if(!live){
            tankFrame.bullets.remove(this);
        }
        switch (dir){
            case UP:
                g.drawImage(ResourceMan.bulletU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMan.bulletD,x,y,null);
                break;
            case LEFT:
                g.drawImage(ResourceMan.bulletL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMan.bulletR,x,y,null);
                break;
        }
        move();

    }

    private void move() {
        switch (dir){
            case UP:y-=SPEED;
                break;
            case DOWN:y+=SPEED;
                break;
            case LEFT:x-=SPEED;
                break;
            case RIGHT:x+=SPEED;
                break;
        }
        if(x<0||y<0||x>TankFrame.WIDTH||y>TankFrame.HEIGHT){
            live=false;
        }
        rectangle.x=this.x;
        rectangle.y=this.y;
    }

    /**
     * 判断子弹是否与坦克碰撞
     * @param tank
     */
    public void collWith(Tank tank) {
        if(this.group==tank.getGroup()){
            return;
        }
        Rectangle rectangle1=new Rectangle(this.x,this.y,WEITH,HEIGTH);
        Rectangle rectangle2=new Rectangle(tank.getX(),tank.getY(),Tank.WEITH,Tank.HEIGTH);
        if(rectangle1.intersects(rectangle2)){
            this.die();
            tank.die();
            int ex=tank.getX()+Tank.WEITH/2-Explode.WEITH/2;
            int ey=tank.getY()+Tank.HEIGTH/2-Explode.HEIGHT/2;
            tankFrame.explodes.add(new Explode(ex,ey,tankFrame));
        }

    }

    private void die() {
        this.live=false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
