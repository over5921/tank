package com.xbin.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName TankFrame
 * @Descrption
 * @Author xiebin
 * @Date 2020/9/6 22:58
 * @Version 1.0
 **/
public class TankFrame extends Frame {
    Tank mainTank=new Tank(50,50,Dir.DOWN,Group.GOOD,this);

    public int initTankCount =Integer.parseInt(PropertiesMan.get("initBabTankCount"));

    List<Tank> tanks=new ArrayList<>();

    List<Bullet> bullets=new ArrayList<Bullet>();

    List<Explode> explodes=new ArrayList<>();

    Explode explode=new Explode(100,50,this);

    public final static int WIDTH=800,HEIGHT=600;

    public TankFrame() {
        setSize(WIDTH, HEIGHT);
        //窗口大小是否可以变化
        setResizable(false);
        //设置标题
        setTitle("tank war ");

        setVisible(true);

        addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        for (int i = 0; i < initTankCount; i++) {
            tanks.add(new Tank(50+i*50,300,Dir.DOWN,Group.BAD,this));
        }

    }

    Image offSceenImage=null;
    @Override
    public void update(Graphics g) {
       if(offSceenImage==null){
           offSceenImage=this.createImage(WIDTH,HEIGHT);
       }
        Graphics graphics = offSceenImage.getGraphics();
        Color color = graphics.getColor();
        graphics.setColor(Color.black);
        graphics.fillRect(0,0,WIDTH,HEIGHT);
        graphics.setColor(color);
        paint(graphics);
        g.drawImage(offSceenImage,0,0,null);
    }

    @Override
    public void paint(Graphics g) {


        Color color = g.getColor();
        g.setColor(Color.red);
        g.drawString("子弹的个数："+bullets.size(),10,60);
        g.drawString("坦克的个数："+tanks.size(),10,80);
        g.setColor(color);

        mainTank.paint(g);

        for(int i=0;i<bullets.size();i++){
            bullets.get(i).paint(g);
        }
        for (int i = 0; i <tanks.size() ; i++) {
            tanks.get(i).paint(g);
        }

        for (int i = 0; i <bullets.size() ; i++) {
            for (int j = 0; j <tanks.size() ; j++) {
                bullets.get(i).collWith(tanks.get(j));

            }
        }
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);

        }

    }

    class MyKeyListener extends KeyAdapter {
        boolean bu=false;
        boolean bd=false;
        boolean bl=false;
        boolean br=false;

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_UP:
                    bu=true;
                break;
                case KeyEvent.VK_DOWN:
                    bd=true;
                    break;
                case KeyEvent.VK_LEFT:
                    bl=true;
                    break;
                case KeyEvent.VK_RIGHT:
                    br=true;
                    break;
                default:
                    break;
            }
            setMainDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_UP:
                    bu=false;
                    break;
                case KeyEvent.VK_DOWN:
                    bd=false;
                    break;
                case KeyEvent.VK_LEFT:
                    bl=false;
                    break;
                case KeyEvent.VK_RIGHT:
                    br=false;
                    break;
                case KeyEvent.VK_CONTROL:
                    mainTank.fire();
                    break;
                default:
                    break;
            }
            setMainDir();
        }

        private void setMainDir() {
            if(!bu&&!bd&&!bl&&!br){
                mainTank.setMoving(false);
            }else{
                mainTank.setMoving(true);
                if(bu) mainTank.setDir(Dir.UP);
                if(bd) mainTank.setDir(Dir.DOWN);
                if(bl) mainTank.setDir(Dir.LEFT);
                if(br) mainTank.setDir(Dir.RIGHT);
            }

        }
    }
}
