package com.xbin.tank;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * @ClassName Tank
 * @Descrption 坦克类
 * @Author xiebin
 * @Date 2020/9/9 21:51
 * @Version 1.0
 **/
public class Tank {
    private int x, y;

    public static final int WEITH=ResourceMan.goodTankD.getWidth();

    public static final int HEIGTH=ResourceMan.goodTankD.getHeight();

    private TankFrame tankFrame;

    private Group group;

    boolean live=true;


    private  Dir dir;

    Rectangle rectangle=new Rectangle();

    private Random random=new Random();


    private boolean moving=true;

    private static final int SPEED=1;

    public Tank(int x, int y, Dir dir,Group group,TankFrame tankFrame) {
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
            tankFrame.tanks.remove(this);
        }
        switch (dir){
            case UP:
                g.drawImage(ResourceMan.goodTankU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMan.goodTankD,x,y,null);
                break;
            case LEFT:
                g.drawImage(ResourceMan.goodTankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMan.goodTankR,x,y,null);
                break;
        }

        move();

    }

    private void move() {
        if(!moving) return;
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

        if(random.nextInt(10)>8&&this.group!=Group.GOOD){
            this.fire();
        }
        randonMove();

        bound();

        rectangle.x=this.x;
        rectangle.y=this.y;
    }

    private void bound() {
        if(x<0) x=0;
        if(y<30) y=30;
        if(x>TankFrame.WIDTH-Tank.WEITH) x=TankFrame.WIDTH-Tank.WEITH;
        if(y>TankFrame.HEIGHT-Tank.HEIGTH) y=TankFrame.HEIGHT-Tank.HEIGTH;
    }

    public void randonMove(){
        if(this.group==Group.BAD&&random.nextInt(100)>95){
            this.dir= Dir.values()[random.nextInt(4)];

        }

    }

    public void fire() {
        int bx=this.x+Tank.WEITH/2-Bullet.WEITH/2;
        int by=this.y+Tank.HEIGTH/2-Bullet.HEIGTH/2;
        tankFrame.bullets.add(new Bullet(bx,by,this.dir,this.group,this.tankFrame));
    }
    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }
    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
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

    public void die() {
        this.live=false;

    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println(LocalDateTime.now().getSecond());
        }

    }
}
