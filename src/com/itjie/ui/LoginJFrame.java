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
        //初始化界面
        InitJFrame();
        //在这个界面上添加组件和内容
        InitView();
        this.setVisible(true);
        setDB();
    }


    public static String getCapTcha(int length) {
        Random random = new Random();
        String characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder capTcha = new StringBuilder();//比用+号拼接效率高
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
        //添加用户名文字
        JLabel userLabel = new JLabel(new ImageIcon("image\\login\\用户名.png"));
        userLabel.setBounds(133, 140, 47, 16);
        this.getContentPane().add(userLabel);
        //添加用户名输入框
        userField.setBounds(195, 135, 200, 30);
        this.getContentPane().add(userField);

        //添加密码文字
        JLabel passwordLabel = new JLabel(new ImageIcon("image\\login\\密码.png"));
        passwordLabel.setBounds(133, 201, 32, 16);
        this.getContentPane().add(passwordLabel);
        //添加密码输入框
        passwordField.setBounds(195, 195, 200, 30);
        this.getContentPane().add(passwordField);

        //添加验证码文字
        JLabel capTchaLabel = new JLabel(new ImageIcon("image\\login\\验证码.png"));
        capTchaLabel.setBounds(133, 255, 50, 30);
        this.getContentPane().add(capTchaLabel);
        //添加验证码输入框
        capTchaField.setBounds(195, 255, 100, 30);
        this.getContentPane().add(capTchaField);
        //添加验证码背景
        JLabel bg2 = new JLabel(new ImageIcon("image\\animal\\animal1\\14.jpg"));
        bg2.setBounds(300, 255, 50, 30);
        //获取验证码
        String capTcha = getCapTcha(4);
        rightCode.setText(capTcha);
        rightCode.addMouseListener(this);//添加点击事件
        rightCode.setBounds(310, 255, 50, 30);
        this.getContentPane().add(rightCode);
        this.getContentPane().add(bg2);

        //添加登录按钮
        loginButton.setBounds(123, 310, 128, 47);
        loginButton.setIcon(new ImageIcon("image\\login\\登录按钮.png"));
        //去除按钮的默认边框
        loginButton.setBorderPainted(false);
        //去除按钮的默认背景
        loginButton.setContentAreaFilled(false);
        loginButton.addMouseListener(this);
        this.getContentPane().add(loginButton);

        //添加注册按钮

        registerButton.setBounds(256, 310, 128, 47);
        registerButton.setIcon(new ImageIcon("image\\login\\注册按钮.png"));
        //去除按钮的默认边框
        registerButton.setBorderPainted(false);
        //去除按钮的默认背景
        registerButton.setContentAreaFilled(false);
        registerButton.addMouseListener(this);
        this.getContentPane().add(registerButton);

        //添加忘记密码按钮

        //添加背景图片
        JLabel background = new JLabel(new ImageIcon("image\\login\\background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);
    }

    private void InitJFrame() {
        this.setSize(488, 430);
        this.setTitle("拼图单机版 v2.0登录");
        ImageIcon icon = new ImageIcon("App.界面照片\\20196131733891546.jpeg");
        this.setIconImage(icon.getImage());
        this.setAlwaysOnTop(true);//置顶界面
        this.setLocationRelativeTo(null);//打开界面为居中
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);//打X时关闭程序
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
        if (e.getSource() == loginButton) {
            String name = userField.getText();
            String passWord = passwordField.getText();
            String code = capTchaField.getText();
            if (code.length() == 0) {
                showJDialog("验证码不能为空");
            } else if (name.length() == 0 || passWord.length() == 0) {
                showJDialog("用户名或密码不能为空");
            } else if (!code.equalsIgnoreCase(rightCode.getText())) {
                showJDialog("验证码输入错误");
            } else {
                try {
                    Connection connection = DriverManager.getConnection(lb.getDburl(), lb.getUsername(), lb.getPassword());
                    String sql = "SELECT * FROM user WHERE name = ? AND password = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, name);
                    statement.setString(2, passWord);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        showJDialog("登录成功");
                        new GameJFrame();
                        this.dispose();
                    } else {
                        showJDialog("用户名或密码错误");
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
        } //写出点击验证码文字刷新验证码的功能
        else if (e.getSource() == rightCode) {
            String code = getCapTcha(4);
            rightCode.setText(code);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == loginButton) {
            loginButton.setIcon(new ImageIcon("image\\login\\登录按下.png"));
        } else if (e.getSource() == registerButton) {
            registerButton.setIcon(new ImageIcon("image\\login\\注册按下.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == loginButton) {
            loginButton.setIcon(new ImageIcon("image\\login\\登录按钮.png"));
        } else if (e.getSource() == registerButton) {
            registerButton.setIcon(new ImageIcon("image\\login\\注册按钮.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
