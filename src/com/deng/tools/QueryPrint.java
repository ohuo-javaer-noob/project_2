package com.deng.tools;

import com.deng.dao.GoodsDao;
import com.deng.db.DbClose;
import com.deng.db.DbConn;
import com.deng.entity.Goods;
import com.deng.entity.SalesMan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @Description:
 * @Author asus
 * @Date 2019/11/30 11:55
 */
public class QueryPrint {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    /**
     * @Description: 模糊查询
     * @pram [oper]
     * @return: int 返回查询到信息的gid 返回-1 查询异常
     * @auther: Deng
     * @date: 2019/11/30 12:00
     */
    public static int query(String oper){
        int gid = -1;
        String shopping = ScannerChoice.ScannerInfoString();
        ArrayList<Goods> goodsLists = new QueryPrint().queryGoodsKey(-1,shopping);
        if (goodsLists == null || goodsLists.size() <= 0){
            System.err.println("\t 查无此商品");
            ScannerChoice.changedInfoNext(oper);
        }else {
            Goods goods = goodsLists.get(0);
            System.out.println("\t\t\t\t\t商品列表\n\n");
            System.out.println("\t商品编号\t\t商品名称\t\t商品价格\t\t商品数量\t\t备注\n");
            System.out.print("\t"+goods.getGid()+"\t\t"+goods.getGname()+"\t\t"+goods.getGprice()+"\t\t"+goods.getGnum());
            if (goods.getGnum() == 0){
                System.out.println("\t\t该商品数量已售空");
            }else if (goods.getGnum() < 10){
                System.out.println("\t\t该商品数量已不足10件");
            }else {
                System.out.println("\t\t-");
            }
            gid = goods.getGid();
        }
        return gid;
    }
    /**
     * 模糊查询函数小工具
     * @return int 当商品件数有且只有一件时返回商品gid号，商品已售空时返回 -1. >1件时返回-2 . 查无此商品时返回-3
     *
     */
    public static int querySettlement()
    {
        int gid = -1;
        ArrayList<Goods> goodsSettlement = new GoodsDao().queryGoods(3);//調用 关键字查询函数
        if (goodsSettlement == null || goodsSettlement.size() <= 0)
        {
            System.err.println("\t！！查无此商品 ！！\n");
            gid = -3;
        }else	//查到有此商品，实现进行 更改商品 信息操作！
        {
            System.out.println("\t\t\t\t\t商品列表\n\n");
            System.out.println("\t商品编号\t\t商品名称\t\t商品价格\t\t商品数量\t\t备注\n");
            for (int i = 0; i < goodsSettlement.size(); i++)
            {
                Goods goods = goodsSettlement.get(i);
                if (goods.getGnum() > 0)
                {
                    System.out.print("\t"+goods.getGid()+"\t\t"+goods.getGname()+"\t\t"+goods.getGprice()+"\t\t"+goods.getGnum());

                    if (goods.getGnum()==0)
                    {
                        System.out.println("\t\t该商品已售空");
                    }else if (goods.getGnum()<10)
                    {
                        System.out.println("\t\t该商品已不足10件");
                    }else
                    {
                        System.out.println("\t\t-");
                    }
                    if (goodsSettlement.size()==1)
                    {
                        gid = goods.getGid(); //将商品编号返回给调用者
                    }else
                    {
                        gid = -2;
                    }
                }
            }
        }
        return gid;
    }

    /**
     * @Description: 查询商品
     * @pram
     * @return:
     * @auther: Deng
     * @date: 2019/11/30 12:02
     */
    public ArrayList<Goods> queryGoodsKey(int gid, String gName){
        ArrayList<Goods> goodsList = new ArrayList<Goods>();
        conn = DbConn.getConn();
        String sql = "SELECT * FROM GOODS WHERE GID=? OR GNAME=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,gid);
            pstmt.setString(2, gName);
            rs = pstmt.executeQuery();
            while (rs.next()){
                int gId = rs.getInt("gid");
                String gname = rs.getString(2);
                double gprice = rs.getDouble(3);
                int gnum = rs.getInt(4);
                Goods goods = new Goods(gId,gname,gprice,gnum);
                goodsList.add(goods);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.queryClose(pstmt, rs, conn);
        }
        return goodsList;
    }
    
    /**
     * @Description: 查询售货员信息
     * @pram 
     * @return: 
     * @auther: Deng
     * @date: 2019/11/30 12:12
     */
    public ArrayList<SalesMan> querySaleMan(String sName){
        ArrayList<SalesMan> saleManList = new ArrayList<SalesMan>();
        conn = DbConn.getConn();
        String sql = "SELECT * FROM SALESMAN WHERE SNAME=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,sName);
            rs = pstmt.executeQuery();
            while (rs.next())
            {
                int sid = rs.getInt("sid");
                String sName1 = rs.getString(2);
                String sPassWord = rs.getString(3);

                SalesMan salesMan = new SalesMan(sid,sName1,sPassWord);
                saleManList.add(salesMan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.queryClose(pstmt, rs, conn);
        }
        return saleManList;
    }
}
