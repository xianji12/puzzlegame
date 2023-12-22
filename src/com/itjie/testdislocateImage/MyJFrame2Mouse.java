package com.itjie.testdislocateImage;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyJFrame2Mouse extends JFrame implements MouseListener {
    JButton jButton1 = new JButton("���Ұ�");

    public MyJFrame2Mouse() {
        this.setSize(603, 680);
        this.setTitle("�¼���ʾ");
        this.setAlwaysOnTop(true);//�ö�����
        this.setLocationRelativeTo(null);//�򿪽���Ϊ����
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//��Xʱ�رճ���
        this.setLayout(null);//���Բ��֣��Լ��������������ڷ��ļ�

        //����ť����λ�úͿ��
        jButton1.setBounds(0, 0, 100, 50);

        //����ť������¼�
        jButton1.addMouseListener(this);

        this.getContentPane().add(jButton1);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MyJFrame2Mouse();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("����");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("���²���");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("�ɿ�");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("����");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("����");
    }
}
