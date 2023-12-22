package com.itjie.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    //����һ����ά���飨4��4�У�
    //Ŀ�ģ�������������
    //����ͼƬ��ʱ�򣬻���ݶ�ά�����е����ݽ��м���
    int[][] data = new int[4][4];
    //��¼�հ׷����ڶ�ά�����е�λ��
    int x = 0;
    int y = 0;
    String path = "image\\animal\\animal1\\";
    //����һ����ȷ�����飬�洢��ȷ������
    int[][] win = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0},
    };
    int step = 0;
    JMenuItem girlitem = new JMenuItem("��Ů");
    JMenuItem animalItem = new JMenuItem("����");
    JMenuItem sportItem = new JMenuItem("�˶�");
    JMenuItem replayItem = new JMenuItem("������Ϸ");
    JMenuItem reLoginItem = new JMenuItem("���µ�¼");
    JMenuItem closeItem = new JMenuItem("�ر���Ϸ");
    JMenuItem accountItem = new JMenuItem("���ں�");
    Random r = new Random();


    public GameJFrame() {
        //��ʼ������
        initJFrame();

        //��ʼ���˵�
        initJMenuBar();

        //��ʼ������(����)
        initData();

        //��ʼ��ͼƬ(���ݴ���֮��Ľ��ȥ����ͼƬ)
        initImage();

        this.setVisible(true);
    }

    private void initData() {
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random r = new Random();
        for (int i = 0; i < array.length; i++) {
            int index = r.nextInt(array.length);
            int temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
        //����ά�����������
        //�ⷨһ
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                x = i / 4;
                y = i % 4;
            }
            data[i / 4][i % 4] = array[i];

            //��i=0��i/4=0��i%4=0

        }
    }
    //���ͼƬ��ʱ����Ҫ���ն�ά�����й�����������ͼƬ

    private void initImage() {
        //���ԭ���Ѿ����ֵ�����ͼƬ
        this.getContentPane().removeAll();
        if (victory()) {
            JLabel winJLabel = new JLabel(new ImageIcon("C:\\Users\\86157\\IdeaProjects\\untitled3\\image\\win.png"));
            winJLabel.setBounds(203, 283, 197, 73);
            this.getContentPane().add(winJLabel);
        }

        JLabel stepCount = new JLabel("����" + step);
        stepCount.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepCount);
        ;
        //·���ֳ�����
        //����·����һ���Ǵ��̷���ʼ�ġ�c:\D��\
        //���·�������Ǵ��̷���ʼ�ģ������Ŀ���ԡ� aaa\\bbb����aaa�ļ���������ȥ��bbb�ļ���
        //��ѭ��---����ѭ���ظ�ִ��4��
        for (int i = 0; i < 4; i++) {
            //��ѭ��---��ʾ��һ�����4��ͼƬ
            //*��ִ����ѭ����ִ����ѭ��
            for (int j = 0; j < 4; j++) {
                int num = data[i][j];
                //����һ��JLabel�Ķ��󣨹���������
                JLabel jLabel = new JLabel(new ImageIcon(path + num + ".jpg"));
                //�ѹ���������ӵ�������
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                //��ͼƬ��ӱ߿�
                // jLabel.setBorder(BorderFactory.createLineBorder(Color.black));
                jLabel.setBorder(new BevelBorder(1));
                //��ȡ����������add��������
                this.getContentPane().add(jLabel);
            }

        }
        //��ӱ���ͼƬ
        ImageIcon bg1 = new ImageIcon("image\\background.png");
        JLabel background = new JLabel(bg1);
        background.setBounds(40, 40, 508, 560);
        //�ѱ���ͼƬ��ӵ�������
        this.getContentPane().add(background);

        //ˢ�½���
        this.getContentPane().repaint();

    }

    private void initJMenuBar() {
        //���������˵�����
        JMenuBar jMenuBar = new JMenuBar();

        //�����˵����������ѡ��Ķ��󣨹��� �������ǣ�
        JMenu functionJMenu = new JMenu("����");
        JMenu aboutJMenu = new JMenu("��������");
        JMenu changeJMenu = new JMenu("����ͼƬ");

        replayItem.addActionListener(this);
        girlitem.addActionListener(this);
        animalItem.addActionListener(this);
        sportItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        changeJMenu.addActionListener(this);

        //��ÿ����Ŀ������ӵ������� �������ǣ�ѡ����
        changeJMenu.add(girlitem);
        changeJMenu.add(animalItem);
        changeJMenu.add(sportItem);
        functionJMenu.add(changeJMenu);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);
        aboutJMenu.add(accountItem);

        //������ѡ����� �������ǣ���ӵ��˵�������
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        //�ٰѲ˵�������ӵ�����JFrame��
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        this.setSize(603, 680);
        this.setTitle("ƴͼ������ v2.0");
        ImageIcon icon = new ImageIcon("App.������Ƭ\\20196131733891546.jpeg");
        this.setIconImage(icon.getImage());
        this.setAlwaysOnTop(true);//�ö�����
        this.setLocationRelativeTo(null);//�򿪽���Ϊ����
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);//��Xʱ�رճ���
        this.setLayout(null);//���Բ��֣��Լ��������������ڷ��ļ�
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 65) {
            this.getContentPane().removeAll();
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(all);

            ImageIcon bg = new ImageIcon("image\\background.png");
            JLabel background = new JLabel(bg);
            background.setBounds(40, 40, 508, 560);
            //�ѱ���ͼƬ��ӵ�������
            this.getContentPane().add(background);
            this.getContentPane().repaint();

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (victory()) {
            return;
        }
        //���������Ҽ��̲��������ж�
        //��37�ϣ�38�ң�39�£�40
        e.getKeyCode();
        if (e.getKeyCode() == 37) {
            if (y == 3) {
                return;
            }
            ;
            //�߼���
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;
            step++;
            initImage();
            ;
        } else if (e.getKeyCode() == 38) {
            if (x == 3) {
                return;
            }
            //�߼���
            //�ѿհ׷�����������������ƶ�
            //x,y����հ׷���    ��x+1��y�����·�����
            //�ѿհ׷����·������ָ�ֵ���հ׷���
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;
            step++;
            initImage();
        } else if (e.getKeyCode() == 39) {
            if (y == 0) {
                return;
            }
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;
            step++;
            initImage();
        } else if (e.getKeyCode() == 40) {
            if (x == 0) {
                return;
            }
            //�߼���
            //�ѿհ׷�����������������ƶ�
            //x,y����հ׷���    ��x-1��y�����Ϸ�����
            //�ѿհ׷����Ϸ������ָ�ֵ���հ׷���
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;
            step++;
            initImage();
        } else if (e.getKeyCode() == 65) {
            initImage();
        } else if (e.getKeyCode() == 87) {
            data = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0},
            };
            x = 3;
            y = 3;
            initImage();
        }
    }

    public boolean victory() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != win[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == replayItem) {
            step = 0;
            initData();
            initImage();
        } else if (e.getSource() == reLoginItem) {
            this.setVisible(false);
            new LoginJFrame();
        } else if (e.getSource() == closeItem) {
            //this.dispose();
            System.exit(0);
        } else if (e.getSource() == accountItem) {
            //����һ���������
            JDialog jDialog = new JDialog();
            ImageIcon icon = new ImageIcon("App.������Ƭ\\20196131733891546.jpeg");
            jDialog.setIconImage(icon.getImage());
            JLabel jLabel = new JLabel(new ImageIcon("΢�Ŷ�ά��\\˧.png"));
            jLabel.setBounds(0, 0, 258, 258);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(344, 344);
            //�õ����ö�
            jDialog.setAlwaysOnTop(true);
            //�õ������
            jDialog.setLocationRelativeTo(null);
            //���򲻹ر����޷���������Ľ���
            jDialog.setModal(true);
            //�õ�����ʾ����
            jDialog.setVisible(true);
        } else if (e.getSource() == girlitem) {
            step = 0;
            String girl = "girl" + (r.nextInt(13) + 1) + "\\";
            path = "image\\girl\\" + girl;
            initData();
            initImage();
        } else if (e.getSource() == animalItem) {
            step = 0;
            String animal = "animal" + (r.nextInt(8) + 1) + "\\";
            path = "image\\animal\\" + animal;
            initData();
            initImage();
        } else if (e.getSource() == sportItem) {
            step = 0;
            String sport = "sport" + (r.nextInt(10) + 1) + "\\";
            path = "image\\sport\\" + sport;
            initData();
            initImage();
        }
    }
}
