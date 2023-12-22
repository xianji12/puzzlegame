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
        //添加用户名文字
        JLabel userLabel = new JLabel(new ImageIcon("image\\register\\注册用户名.png"));
        userLabel.setBounds(110, 140, 79, 16);
        this.getContentPane().add(userLabel);

        //添加用户名输入框

        userField.setBounds(220, 135, 200, 30);
        this.getContentPane().add(userField);

        //添加密码文字
        JLabel passwordLabel = new JLabel(new ImageIcon("image\\register\\注册密码.png"));
        passwordLabel.setBounds(110, 201, 64, 16);
        this.getContentPane().add(passwordLabel);

        //添加密码输入框
        passwordField.setBounds(220, 195, 200, 30);
        this.getContentPane().add(passwordField);

        //添加确认密码文字
        JLabel passwordLabel2 = new JLabel(new ImageIcon("image\\register\\再次输入密码.png"));
        passwordLabel2.setBounds(110, 262, 96, 17);
        this.getContentPane().add(passwordLabel2);

        //添加确认密码输入框

        passwordField2.setBounds(220, 255, 200, 30);
        this.getContentPane().add(passwordField2);
        //添加注册按钮

        registerButton.setBounds(128, 310, 128, 47);
        registerButton.setIcon(new ImageIcon("image\\register\\注册按钮.png"));
        //去除按钮的默认边框
        registerButton.setBorderPainted(false);
        //去除按钮的默认背景
        registerButton.setContentAreaFilled(false);
        registerButton.addMouseListener(this);
        this.getContentPane().add(registerButton);
        //添加重置按钮

        resetButton.setBounds(256, 310, 128, 47);
        resetButton.setIcon(new ImageIcon("image\\register\\重置按钮.png"));
        resetButton.setBorderPainted(false);
        resetButton.setContentAreaFilled(false);
        resetButton.addMouseListener(this);
        this.getContentPane().add(resetButton);
        //添加背景图片
        JLabel background = new JLabel(new ImageIcon("image\\register\\background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);
    }

    private void InitJFrame() {
        this.setSize(488, 430);
        this.setTitle("拼图单机版 v2.0注册");
        ImageIcon icon = new ImageIcon("App.界面照片\\20196131733891546.jpeg");
        this.setIconImage(icon.getImage());
        this.setAlwaysOnTop(true);//置顶界面
        this.setLocationRelativeTo(null);//打开界面为居中
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);//打X时关闭程序
    }

    private void setDB() {
        db = new Mysql();
        db.setDburl("jdbc:mysql://localhost:3306/user_password");
        db.setDbdriver("com.mysql.cj.jdbc.Driver");
        db.setUsername("root");
        db.setPassword("root");
    }

    public void showJDialog(String content) {
        //创建一个弹窗对象
        JDialog jDialog = new JDialog();
        //给弹窗设置大小
        jDialog.setSize(200, 150);
        jDialog.setTitle("温馨提示");

        ImageIcon icon = new ImageIcon("App.界面照片\\20196131733891546.jpeg");
        jDialog.setIconImage(icon.getImage());
        //让弹窗置顶
        jDialog.setAlwaysOnTop(true);
        //让弹窗居中
        jDialog.setLocationRelativeTo(null);
        //弹窗不关闭无法操作之后的界面
        jDialog.setModal(true);

        JLabel warnning = new JLabel(content);
        warnning.setBounds(0, 0, 200, 105);
        warnning.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel bg3 = new JLabel(new ImageIcon("App.界面照片\\20196131733891546.jpeg"));
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
                showJDialog("用户名或密码不能为空");
            } else if (passWord2.length() == 0) {
                showJDialog("再次输入密码为空");
            } else if (!passWord.equalsIgnoreCase(passWord2)) {
                showJDialog("密码不一致");
            } else {
                try {
                    Connection connection = DriverManager.getConnection(db.getDburl(), db.getUsername(), db.getPassword());
                    String sql = "INSERT INTO user (name,password) VALUES (?,?)";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, name);
                    statement.setString(2, passWord);
                    //判断用户名是否重复
                    String sql2 = "SELECT * FROM user WHERE name = ?";
                    PreparedStatement statement2 = connection.prepareStatement(sql2);
                    statement2.setString(1, name);
                    ResultSet resultSet = statement2.executeQuery();
                    int i = statement.executeUpdate();
                    if (resultSet.next()) {
                        showJDialog("用户名已存在");
                        return;
                    } else if (i == 1) {
                        showJDialog("注册成功");
                        this.setVisible(false);
                        new LoginJFrame();
                    } else {
                        showJDialog("注册失败");
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
            registerButton.setIcon(new ImageIcon("image\\register\\注册按下.png"));
        } else if (e.getSource() == resetButton) {
            resetButton.setIcon(new ImageIcon("image\\register\\重置按下.png"));
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == registerButton) {
            registerButton.setIcon(new ImageIcon("image\\register\\注册按钮.png"));
        } else if (e.getSource() == resetButton) {
            resetButton.setIcon(new ImageIcon("image\\register\\重置按钮.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    //写出注册界面的所有组件


}