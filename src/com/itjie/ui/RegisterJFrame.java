package com.itjie.ui;

import javax.swing.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;


public class RegisterJFrame extends JFrame implements MouseListener {
    JTextField userField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JPasswordField passwordField2 = new JPasswordField();
    JButton registerButton = new JButton();
    JButton resetButton = new JButton();
    private Mysql db = null;

    {
        userField.setText("");
        passwordField.setText("");
        passwordField2.setText("");
    }

    public RegisterJFrame() {
        InitJFrame();
        InitView();
        setDB();
        this.setVisible(true);
    }

    private void InitView() {
        //����û�������
        JLabel userLabel = new JLabel(new ImageIcon("image\\register\\ע���û���.png"));
        userLabel.setBounds(110, 140, 79, 16);
        this.getContentPane().add(userLabel);

        //����û��������

        userField.setBounds(220, 135, 200, 30);
        this.getContentPane().add(userField);

        //�����������
        JLabel passwordLabel = new JLabel(new ImageIcon("image\\register\\ע������.png"));
        passwordLabel.setBounds(110, 201, 64, 16);
        this.getContentPane().add(passwordLabel);

        //������������
        passwordField.setBounds(220, 195, 200, 30);
        this.getContentPane().add(passwordField);

        //���ȷ����������
        JLabel passwordLabel2 = new JLabel(new ImageIcon("image\\register\\�ٴ���������.png"));
        passwordLabel2.setBounds(110, 262, 96, 17);
        this.getContentPane().add(passwordLabel2);

        //���ȷ�����������

        passwordField2.setBounds(220, 255, 200, 30);
        this.getContentPane().add(passwordField2);
        //���ע�ᰴť

        registerButton.setBounds(128, 310, 128, 47);
        registerButton.setIcon(new ImageIcon("image\\register\\ע�ᰴť.png"));
        //ȥ����ť��Ĭ�ϱ߿�
        registerButton.setBorderPainted(false);
        //ȥ����ť��Ĭ�ϱ���
        registerButton.setContentAreaFilled(false);
        registerButton.addMouseListener(this);
        this.getContentPane().add(registerButton);
        //������ð�ť

        resetButton.setBounds(256, 310, 128, 47);
        resetButton.setIcon(new ImageIcon("image\\register\\���ð�ť.png"));
        resetButton.setBorderPainted(false);
        resetButton.setContentAreaFilled(false);
        resetButton.addMouseListener(this);
        this.getContentPane().add(resetButton);
        //��ӱ���ͼƬ
        JLabel background = new JLabel(new ImageIcon("image\\register\\background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);
    }

    private void InitJFrame() {
        this.setSize(488, 430);
        this.setTitle("ƴͼ������ v2.0ע��");
        ImageIcon icon = new ImageIcon("App.������Ƭ\\20196131733891546.jpeg");
        this.setIconImage(icon.getImage());
        this.setAlwaysOnTop(true);//�ö�����
        this.setLocationRelativeTo(null);//�򿪽���Ϊ����
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);//��Xʱ�رճ���
    }

    private void setDB() {
        db = new Mysql();
        db.setDburl("jdbc:mysql://localhost:3306/user_password");
        db.setDbdriver("com.mysql.cj.jdbc.Driver");
        db.setUsername("root");
        db.setPassword("root");
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
        if (e.getSource() == registerButton) {
            String name = userField.getText();
            String passWord = passwordField.getText();
            String passWord2 = passwordField2.getText();
            if (name.length() == 0 || passWord.length() == 0) {
                showJDialog("�û��������벻��Ϊ��");
            } else if (passWord2.length() == 0) {
                showJDialog("�ٴ���������Ϊ��");
            } else if (!passWord.equalsIgnoreCase(passWord2)) {
                showJDialog("���벻һ��");
            } else {
                try {
                    Connection connection = DriverManager.getConnection(db.getDburl(), db.getUsername(), db.getPassword());
                    String sql = "INSERT INTO user (name,password) VALUES (?,?)";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, name);
                    statement.setString(2, passWord);
                    //�ж��û����Ƿ��ظ�
                    String sql2 = "SELECT * FROM user WHERE name = ?";
                    PreparedStatement statement2 = connection.prepareStatement(sql2);
                    statement2.setString(1, name);
                    ResultSet resultSet = statement2.executeQuery();
                    int i = statement.executeUpdate();
                    if (resultSet.next()) {
                        showJDialog("�û����Ѵ���");
                        return;
                    } else if (i == 1) {
                        showJDialog("ע��ɹ�");
                        this.setVisible(false);
                        new LoginJFrame();
                    } else {
                        showJDialog("ע��ʧ��");
                    }
                    statement.close();
                    connection.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == registerButton) {
            registerButton.setIcon(new ImageIcon("image\\register\\ע�ᰴ��.png"));
        } else if (e.getSource() == resetButton) {
            resetButton.setIcon(new ImageIcon("image\\register\\���ð���.png"));
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == registerButton) {
            registerButton.setIcon(new ImageIcon("image\\register\\ע�ᰴť.png"));
        } else if (e.getSource() == resetButton) {
            resetButton.setIcon(new ImageIcon("image\\register\\���ð�ť.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    //д��ע�������������


}