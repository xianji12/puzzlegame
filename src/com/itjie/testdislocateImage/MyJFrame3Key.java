package com.itjie.testdislocateImage;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyJFrame3Key extends JFrame implements KeyListener {
    public MyJFrame3Key() {
        this.setSize(603, 680);
        this.setTitle("�¼���ʾ");
        this.setAlwaysOnTop(true);//�ö�����
        this.setLocationRelativeTo(null);//�򿪽���Ϊ����
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//��Xʱ�رճ���
        this.setLayout(null);//���Բ��֣��Լ��������������ڷ��ļ�

        //������������Ӽ��̼���
        //������this��������󣬵�ǰ�Ľ�����󣬱�ʾ��Ҫ������������Ӽ���
        //addKeyListener����ʾҪ����������Ӽ��̼���
        //����this�����¼�������֮�󣬻�ִ�б����еĶ�Ӧ�Ĵ��루����������
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
        System.out.println("���²���");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("�ɿ�����");
        //��ȡ������ÿһ�������ı��
        int keyCode = e.getKeyCode();
            System.out.println("������ţ�" + keyCode);
    }
}
