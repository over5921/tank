package com.xbin.tank;

/**
 * @ClassName Demo
 * @Descrption
 * @Author xiebin
 * @Date 2020/9/6 22:30
 * @Version 1.0
 **/
public class GameStart {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();

        while (true) {
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
