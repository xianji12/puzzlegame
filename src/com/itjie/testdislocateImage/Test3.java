package com.itjie.testdislocateImage;

import javax.swing.*;
import java.sql.*;

public class Test3 {

        public static void main(String[] args) throws Exception {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection com = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_password", "root", "root");
            // DriverManager 注册驱动
            // Connection 数据库连接对象  url（指定连接的路径 语法：“jdbc:mysql://ip地址:端口号/数据库名称”）
            Statement stat = com.createStatement();
            //执行 sql 语句的对象
            String sql = "SELECT * FROM user";
            ResultSet rs = stat.executeQuery(sql);
            // 执行 增删改查 （DML）语句用 int executeUpdate(Sting sql);
            // 执行 DQL 语句 ResultSet executeQuery(String sql);
            // 对象释放 void close();
            while (rs.next()){
                System.out.println(rs.getString("name") + "\t" + rs.getString("password"));
            }
            com.close();
            stat.close();
            com.close();
        }
}
