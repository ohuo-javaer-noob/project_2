package com.deng.dao;

import com.deng.db.DbClose;
import com.deng.db.DbConn;
import com.deng.entity.Gsales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @Description:
 * @Author asus
 * @Date 2019/12/8 12:11
 */
public class GsalesDao {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public ArrayList<Gsales> dailyGsales(){
        ArrayList<Gsales> GsalesList = new ArrayList<>();
        conn = DbConn.getConn();
        String sql = "select gname,gprice,allSum from goods, (selecet gid as salesid,sum(snum) as allSum from gsales where trunc(sdate) group by gid) where gid = salesid";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                String gName = rs.getNString(1);
                double gPrice = rs.getDouble(2);
                int gNum = rs.getInt(3);
                int allSum = rs.getInt("allSum");
                Gsales gsales = new Gsales(gName, gPrice, gNum, allSum);
                GsalesList.add(gsales);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.queryClose(pstmt,rs,conn);
        }
        return GsalesList;
    }

    /**
     * @Description: 购物结算，向sales表中插入商品数据
     * @pram
     * @return:
     * @auther: Deng
     * @date: 2019/12/8 12:29
     */
    public boolean shoppingSettlement(Gsales gsales){
        boolean bool = false;
        conn = DbConn.getConn();
        String sql = "INSERT INTO GSALES(GID,SID,SNUM) VALUES(?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,gsales.getgId());
            pstmt.setInt(2,gsales.getsId());
            pstmt.setInt(3,gsales.getsNum());
            int rs = pstmt.executeUpdate();
            if (rs>0){
                bool = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.addClose(pstmt,conn);
        }
        return bool;
    }
}
