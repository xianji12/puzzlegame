package com.itjie.testdislocateImage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MyJFrameAction extends JFrame implements ActionListener {
    JButton jButton1 = new JButton("���Ұ�");
    JButton jButton2 = new JButton("�ٵ��Ұ�");

    public MyJFrameAction() {
        this.setSize(603, 680);
        this.setTitle("�¼���ʾ");
        this.setAlwaysOnTop(true);//�ö�����
        this.setLocationRelativeTo(null);//�򿪽���Ϊ����
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//��Xʱ�رճ���
        this.setLayout(null);//���Բ��֣��Լ��������������ڷ��ļ�

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
        //�Ե�ǰ�İ�ť�����ж�
        // ��ȡ��ǰ���������Ǹ���ť����
        if (e.getSource() == jButton1) {
            jButton1.setSize(200, 200);
        } else if (e.getSource() == jButton2) {
            Random r = new Random();
            jButton2.setLocation(r.nextInt(500),r.nextInt(500));
        }
    }
}
