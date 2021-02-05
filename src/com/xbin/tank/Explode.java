package com.xbin.tank;

import java.awt.*;

/**
 * @ClassName Explode
 * @Descrption
 * @Author xiebin
 * @Date 2020/9/12 10:30
 * @Version 1.0
 **/
public class Explode {

    private int x,y;

    public static final int WEITH=ResourceMan.expolers[0].getWidth();

    public static final int HEIGHT=ResourceMan.expolers[0].getHeight();

    TankFrame tankFrame;
    int step;

    public Explode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
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

    public void paint(Graphics g) {

        g.drawImage(ResourceMan.expolers[step++],this.x,this.y,null);


        if(step>=ResourceMan.expolers.length){
            tankFrame.explodes.remove(this);
        }
    }
}
