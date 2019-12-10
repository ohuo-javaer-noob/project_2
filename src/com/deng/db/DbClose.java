package com.deng.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description:
 * @Author asus
 * @Date 2019/11/17 13:51
 */
public class DbClose {

    /**
     * @Description: addClose 添加功能
     * @pram [pstmt, conn]
     * @return: void
     * @auther: Deng
     * @date: 2019/11/17 13:57
     */
    public static void addClose(PreparedStatement pstmt, Connection conn){

        if(pstmt != null){
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void queryClose(PreparedStatement pstmt, ResultSet rs, Connection conn){
        if (pstmt != null){
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
