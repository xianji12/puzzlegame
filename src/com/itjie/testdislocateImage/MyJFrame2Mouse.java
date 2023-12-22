package com.itjie.testdislocateImage;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyJFrame2Mouse extends JFrame implements MouseListener {
    JButton jButton1 = new JButton("点我啊");

    public MyJFrame2Mouse() {
        this.setSize(603, 680);
        this.setTitle("事件演示");
        this.setAlwaysOnTop(true);//置顶界面
        this.setLocationRelativeTo(null);//打开界面为居中
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//打X时关闭程序
        this.setLayout(null);//绝对布局，自己可以随心所欲摆放文件

        //给按钮设置位置和宽高
        jButton1.setBounds(0, 0, 100, 50);

        //给按钮绑定鼠标事件
        jButton1.addMouseListener(this);

        this.getContentPane().add(jButton1);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MyJFrame2Mouse();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("单击");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("按下不松");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("松开");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("划入");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("划出");
    }
}
