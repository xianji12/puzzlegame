package com.itjie.testdislocateImage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MyJFrameAction extends JFrame implements ActionListener {
    JButton jButton1 = new JButton("点我啊");
    JButton jButton2 = new JButton("再点我啊");

    public MyJFrameAction() {
        this.setSize(603, 680);
        this.setTitle("事件演示");
        this.setAlwaysOnTop(true);//置顶界面
        this.setLocationRelativeTo(null);//打开界面为居中
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//打X时关闭程序
        this.setLayout(null);//绝对布局，自己可以随心所欲摆放文件

        jButton1.setBounds(0, 0, 100, 50);
        jButton1.addActionListener(this);


        jButton2.setBounds(100, 0, 100, 50);
        jButton2.addActionListener(this);

        this.getContentPane().add(jButton1);
        this.getContentPane().add(jButton2);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MyJFrameAction();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //对当前的按钮进行判断
        // 获取当前被操作的那个按钮对象
        if (e.getSource() == jButton1) {
            jButton1.setSize(200, 200);
        } else if (e.getSource() == jButton2) {
            Random r = new Random();
            jButton2.setLocation(r.nextInt(500),r.nextInt(500));
        }
    }
}
