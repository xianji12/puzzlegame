package com.itjie.testdislocateImage;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyJFrame3Key extends JFrame implements KeyListener {
    public MyJFrame3Key() {
        this.setSize(603, 680);
        this.setTitle("事件演示");
        this.setAlwaysOnTop(true);//置顶界面
        this.setLocationRelativeTo(null);//打开界面为居中
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//打X时关闭程序
        this.setLayout(null);//绝对布局，自己可以随心所欲摆放文件

        //给整个窗体添加键盘监听
        //调用者this：本类对象，当前的界面对象，表示我要给整个界面添加监听
        //addKeyListener：表示要给本界面添加键盘监听
        //参数this：当事件被触发之后，会执行本类中的对应的代码（下面三个）
        this.addKeyListener(this);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MyJFrame3Key();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("按下不松");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("松开按键");
        //获取键盘上每一个按键的编号
        int keyCode = e.getKeyCode();
            System.out.println("按键编号：" + keyCode);
    }
}
