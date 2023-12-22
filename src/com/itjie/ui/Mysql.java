package com.itjie.ui;

import java.sql.*;

public class Mysql {
    private String dburl = null;
    // 定义数据库连接
    private Connection conn = null;
    // 定义数据库状态
    private PreparedStatement stmt = null;
    // 定义数据库返回结果集
    private ResultSet rs = null;
    // 定义数据库用户名
    private String username = "root";
    // 定义数据库连接密码
    private String password = "root";
    // 定义数据库驱动方式
    private String dbdriver = null;

    // 返回当前实例数据库连接url
    public String getDburl() {
        return dburl;
    }

    // 设置数据库连接url的方法
    public void setDburl(String dburl) {
        this.dburl = dburl;
    }

    // 返回当前实例结果集的方法
    public ResultSet getRs() {
        return rs;
    }

    // 设置当前实例结果集的方法
    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    // 返回当前实例化数据库用户名
    public String getUsername() {
        return username;
    }

    // 设置数据库用户名的方法
    public void setUsername(String username) {
        this.username = username;
    }

    // 返回当前实例数据库连接密码
    public String getPassword() {
        return password;
    }

    // 设置数据库连接的方法
    public void setPassword(String password) {
        this.password = password;
    }

    // 返回当前实例数据库驱动方式的方法
    public String getDbdriver() {
        return dbdriver;
    }

    // 设置数据库驱动方式的方法
    public void setDbdriver(String dbdriver) {
        this.dbdriver = dbdriver;
    }

}
