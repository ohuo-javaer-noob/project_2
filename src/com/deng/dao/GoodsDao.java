package com.deng.dao;

import com.deng.db.DbClose;
import com.deng.db.DbConn;
import com.deng.entity.Goods;
import com.deng.tools.ScannerChoice;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @Description:
 * @Author asus
 * @Date 2019/11/17 13:49
 */
public class GoodsDao {

    Connection coon = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    /**
     * @Description: addGoods 商品添加
     * @pram [goods]
     * @return: boolean
     * @auther: Deng
     * @date: 2019/11/17 16:19
     */
    public boolean addGoods(Goods goods){
        boolean bool = false;
        coon = DbConn.getConn();
        String sql = "INSERT INTO GOODS(GNAME,GPRICE,GNUM) VALUES(?,?,?)";

        try {
            pstmt = coon.prepareStatement(sql);
            pstmt.setDouble(2,goods.getGprice());
            pstmt.setString(1,goods.getGname());
            pstmt.setInt(3,goods.getGnum());
            int rs = pstmt.executeUpdate();
            if (rs > 0){
                bool = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbClose.addClose(pstmt, coon);
        }

        return bool;
    }

    public boolean updateGoods(int key, Goods goods){
        boolean bool = false;
        coon = DbConn.getConn();
        switch (key){
            case 1:     //1.更改商品名称
                String sqlName = "UPDATE GOODS SET GNAME=? WHERE GID=?";

                try {
                    pstmt = coon.prepareStatement(sqlName);
                    pstmt.setString(1,goods.getGname());
                    pstmt.setInt(2,goods.getGid());
                    int rs = pstmt.executeUpdate();
                    if (rs > 0){
                        bool =true;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    DbClose.addClose(pstmt,coon);
                }

                break;
            case 2:     //2.更改商品价格
                String sqlPrice = "UPDATE GOODS SET GPRICE=? WHERE GID=?";
                try {
                    pstmt = coon.prepareStatement(sqlPrice);
                    pstmt.setDouble(1,goods.getGprice());
                    pstmt.setInt(2,goods.getGid());
                    int rs = pstmt.executeUpdate();
                    if (rs > 0){
                        bool =true;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    DbClose.addClose(pstmt,coon);
                }
                break;
            case 3:     //3.更改商品数量
                String sqlNum = "UPDATE GOODS SET GNUM=? WHERE GID=?";
                try {
                    pstmt = coon.prepareStatement(sqlNum);
                    pstmt.setInt(1,goods.getGnum());
                    pstmt.setInt(2,goods.getGid());
                    int rs = pstmt.executeUpdate();
                    if (rs > 0){
                        bool =true;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    DbClose.addClose(pstmt,coon);
                }
                break;
            default:
                break;
        }
        return bool;
    }

    /**
     * @Description: deleteGoods 删除商品
     * @pram [gid]
     * @return: boolean
     * @auther: Deng
     * @date: 2019/11/17 17:33
     */
    public boolean deleteGoods(int gid){
        boolean bool = false;
        coon = DbConn.getConn();
        String sql = "DELETE FROM GOODS WHERE GID=?";

        try {
            pstmt = coon.prepareStatement(sql);
            pstmt.setInt(1,gid);
            int rs = pstmt.executeUpdate();
            if (rs > 0){
                bool =true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbClose.addClose(pstmt,coon);
        }
        return bool;
    }

    public ArrayList<Goods> queryGoods(int key){
        ArrayList<Goods> goodsList = new ArrayList<Goods>();
        coon = DbConn.getConn();
        switch (key){
            case 1:     //key =1 商品数量 升序查询
                String sqlGnum = "SELECT * FROM GOODS ORDER BY GNUM ASC";
                try {
                    pstmt = coon.prepareStatement(sqlGnum);
                    rs = pstmt.executeQuery();
                    while (rs.next()){
                        int gid = rs.getInt("gid");
                        String gname = rs.getNString(2);
                        double gprice = rs.getDouble(3);
                        int gnum = rs.getInt(4);
                        Goods goods = new Goods(gid, gname, gprice, gnum);
                        goodsList.add(goods);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    DbClose.queryClose(pstmt,rs,coon);
                }
                break;
            case 2:     //key =2 商品价格 升序查询
                String sqlPrice = "SELECT * FROM GOODS PRDER BY GPRICE ASC";

                try {
                    pstmt = coon.prepareStatement(sqlPrice);
                    rs = pstmt.executeQuery();
                    while (rs.next()){
                        int gid = rs.getInt("gid");
                        String gname = rs.getNString(2);
                        double gprice = rs.getDouble(3);
                        int gnum = rs.getInt(4);
                        Goods goods = new Goods(gid, gname, gprice, gnum);
                        goodsList.add(goods);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    DbClose.queryClose(pstmt,rs,coon);
                }
                break;
            case 3:     //key =3 关键字查询
                String nameGet = ScannerChoice.ScannerInfoString();
                String sqlGname = "SELECT *FROM GOODS WHERE GNAME LIKE '%' ||?||'%' ";
                try {
                    pstmt = coon.prepareStatement(sqlGname);
                    rs = pstmt.executeQuery();
                    while (rs.next()){
                        int gid = rs.getInt("gid");
                        String gname = rs.getString(2);
                        double gprice = rs.getDouble(3);
                        int gnum = rs.getInt(4);

                        Goods goods = new Goods(gid,gname,gprice,gnum);
                        goodsList.add(goods);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }  finally {
                    DbClose.queryClose(pstmt,rs,coon);
                }
                break;
                default:
                    break;
        }
        return goodsList;
    }

    public ArrayList<Goods> displayGoods(){
        ArrayList<Goods> goodsList = new ArrayList<Goods>();
        coon = DbConn.getConn();
        String sql = "SELECT * FROM GOODS";

        try {
            pstmt = coon.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                int gid = rs.getInt(1);
                String gname = rs.getString(2);
                double gprice = rs.getDouble("gprice"); 		//双引号+主键名,也可用数字表示.
                int gnum = rs.getInt(4);

                Goods goods = new Goods(gid,gname,gprice,gnum);	//创建Goods对象，并赋值.
                goodsList.add(goods);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsList;
    }

}
