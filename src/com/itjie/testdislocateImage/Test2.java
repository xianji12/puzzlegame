package com.itjie.testdislocateImage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test2 {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(603, 680);
        jFrame.setTitle("事件演示");
        jFrame.setAlwaysOnTop(true);//置顶界面
        jFrame.setLocationRelativeTo(null);//打开界面为居中
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//打X时关闭程序
        jFrame.setLayout(null);//绝对布局，自己可以随心所欲摆放文件

        JButton jButton = new JButton("登录");
        jButton.setBounds(0, 0, 100, 50);
        //给按钮添加监听事件
        //jButton:组件对象，表示你要给哪个组件添加事件
        //addActionListener:表示我要给组件添加哪个事件监听（动作监听包含鼠标左键点击，空格）
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("别按我谢谢");;
            }
        });
        jFrame.getContentPane().add(jButton);
        jFrame.setVisible(true);
    }
}
