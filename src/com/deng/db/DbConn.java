package com.deng.db;

import org.junit.Test;

import java.awt.*;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;


/**
 * @Description:
 * @Author asus
 * @Date 2019/11/17 13:52
 */
public class DbConn {

    @Test
    public static Connection  getConn() {
        Connection conn = null;

        String user = "root";
        String passswd = "123456";
        String url = "jdbc:mysql://localhost:3306/SupermarketSystem";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection(url,user,passswd);
            if (!conn.isClosed()){
                System.out.println("数据库连接成功");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return conn;
    }
}

