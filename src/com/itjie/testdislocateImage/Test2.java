package com.itjie.testdislocateImage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test2 {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(603, 680);
        jFrame.setTitle("�¼���ʾ");
        jFrame.setAlwaysOnTop(true);//�ö�����
        jFrame.setLocationRelativeTo(null);//�򿪽���Ϊ����
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//��Xʱ�رճ���
        jFrame.setLayout(null);//���Բ��֣��Լ��������������ڷ��ļ�

        JButton jButton = new JButton("��¼");
        jButton.setBounds(0, 0, 100, 50);
        //����ť��Ӽ����¼�
        //jButton:������󣬱�ʾ��Ҫ���ĸ��������¼�
        //addActionListener:��ʾ��Ҫ���������ĸ��¼�������������������������������ո�
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("����лл");;
            }
        });
        jFrame.getContentPane().add(jButton);
        jFrame.setVisible(true);
    }
}
