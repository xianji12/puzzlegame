package com.itjie.ui;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.Random;

public class LoginJFrame extends JFrame implements MouseListener {
    JButton loginButton = new JButton();
    JButton registerButton = new JButton();
    JTextField userField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JTextField capTchaField = new JTextField();
    JLabel rightCode = new JLabel();
    private Mysql lb = null;

    public LoginJFrame() {
        //��ʼ������
        InitJFrame();
        //�����������������������
        InitView();
        this.setVisible(true);
        setDB();
    }


    public static String getCapTcha(int length) {
        Random random = new Random();
        String characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder capTcha = new StringBuilder();//����+��ƴ��Ч�ʸ�
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            capTcha.append(characters.charAt(index));
        }
        return capTcha.toString();
    }

    private void setDB() {
        lb = new Mysql();
        lb.setDburl("jdbc:mysql://localhost:3306/user_password");
        lb.setDbdriver("com.mysql.cj.jdbc.Driver");
        lb.setUsername("root");
        lb.setPassword("root");
    }

    private void InitView() {
        //����û�������
        JLabel userLabel = new JLabel(new ImageIcon("image\\login\\�û���.png"));
        userLabel.setBounds(133, 140, 47, 16);
        this.getContentPane().add(userLabel);
        //����û��������
        userField.setBounds(195, 135, 200, 30);
        this.getContentPane().add(userField);

        //�����������
        JLabel passwordLabel = new JLabel(new ImageIcon("image\\login\\����.png"));
        passwordLabel.setBounds(133, 201, 32, 16);
        this.getContentPane().add(passwordLabel);
        //������������
        passwordField.setBounds(195, 195, 200, 30);
        this.getContentPane().add(passwordField);

        //�����֤������
        JLabel capTchaLabel = new JLabel(new ImageIcon("image\\login\\��֤��.png"));
        capTchaLabel.setBounds(133, 255, 50, 30);
        this.getContentPane().add(capTchaLabel);
        //�����֤�������
        capTchaField.setBounds(195, 255, 100, 30);
        this.getContentPane().add(capTchaField);
        //�����֤�뱳��
        JLabel bg2 = new JLabel(new ImageIcon("image\\animal\\animal1\\14.jpg"));
        bg2.setBounds(300, 255, 50, 30);
        //��ȡ��֤��
        String capTcha = getCapTcha(4);
        rightCode.setText(capTcha);
        rightCode.addMouseListener(this);//��ӵ���¼�
        rightCode.setBounds(310, 255, 50, 30);
        this.getContentPane().add(rightCode);
        this.getContentPane().add(bg2);

        //��ӵ�¼��ť
        loginButton.setBounds(123, 310, 128, 47);
        loginButton.setIcon(new ImageIcon("image\\login\\��¼��ť.png"));
        //ȥ����ť��Ĭ�ϱ߿�
        loginButton.setBorderPainted(false);
        //ȥ����ť��Ĭ�ϱ���
        loginButton.setContentAreaFilled(false);
        loginButton.addMouseListener(this);
        this.getContentPane().add(loginButton);

        //���ע�ᰴť

        registerButton.setBounds(256, 310, 128, 47);
        registerButton.setIcon(new ImageIcon("image\\login\\ע�ᰴť.png"));
        //ȥ����ť��Ĭ�ϱ߿�
        registerButton.setBorderPainted(false);
        //ȥ����ť��Ĭ�ϱ���
        registerButton.setContentAreaFilled(false);
        registerButton.addMouseListener(this);
        this.getContentPane().add(registerButton);

        //����������밴ť

        //��ӱ���ͼƬ
        JLabel background = new JLabel(new ImageIcon("image\\login\\background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);
    }

    private void InitJFrame() {
        this.setSize(488, 430);
        this.setTitle("ƴͼ������ v2.0��¼");
        ImageIcon icon = new ImageIcon("App.������Ƭ\\20196131733891546.jpeg");
        this.setIconImage(icon.getImage());
        this.setAlwaysOnTop(true);//�ö�����
        this.setLocationRelativeTo(null);//�򿪽���Ϊ����
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);//��Xʱ�رճ���
    }

    public void showJDialog(String content) {
        //����һ����������
        JDialog jDialog = new JDialog();
        //���������ô�С
        jDialog.setSize(200, 150);
        jDialog.setTitle("��ܰ��ʾ");

        ImageIcon icon = new ImageIcon("App.������Ƭ\\20196131733891546.jpeg");
        jDialog.setIconImage(icon.getImage());
        //�õ����ö�
        jDialog.setAlwaysOnTop(true);
        //�õ�������
        jDialog.setLocationRelativeTo(null);
        //�������ر��޷�����֮��Ľ���
        jDialog.setModal(true);

        JLabel warnning = new JLabel(content);
        warnning.setBounds(0, 0, 200, 105);
        warnning.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel bg3 = new JLabel(new ImageIcon("App.������Ƭ\\20196131733891546.jpeg"));
        bg3.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warnning);
        jDialog.getContentPane().add(bg3);
        jDialog.setLayout(null);
        jDialog.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == loginButton) {
            String name = userField.getText();
            String passWord = passwordField.getText();
            String code = capTchaField.getText();
            if (code.length() == 0) {
                showJDialog("��֤�벻��Ϊ��");
            } else if (name.length() == 0 || passWord.length() == 0) {
                showJDialog("�û��������벻��Ϊ��");
            } else if (!code.equalsIgnoreCase(rightCode.getText())) {
                showJDialog("��֤���������");
            } else {
                try {
                    Connection connection = DriverManager.getConnection(lb.getDburl(), lb.getUsername(), lb.getPassword());
                    String sql = "SELECT * FROM user WHERE name = ? AND password = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, name);
                    statement.setString(2, passWord);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        showJDialog("��¼�ɹ�");
                        new GameJFrame();
                        this.dispose();
                    } else {
                        showJDialog("�û������������");
                    }
                    resultSet.close();
                    statement.close();
                    connection.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } else if (e.getSource() == registerButton) {
            new RegisterJFrame();
            this.dispose();
        } //д�������֤������ˢ����֤��Ĺ���
        else if (e.getSource() == rightCode) {
            String code = getCapTcha(4);
            rightCode.setText(code);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == loginButton) {
            loginButton.setIcon(new ImageIcon("image\\login\\��¼����.png"));
        } else if (e.getSource() == registerButton) {
            registerButton.setIcon(new ImageIcon("image\\login\\ע�ᰴ��.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == loginButton) {
            loginButton.setIcon(new ImageIcon("image\\login\\��¼��ť.png"));
        } else if (e.getSource() == registerButton) {
            registerButton.setIcon(new ImageIcon("image\\login\\ע�ᰴť.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
