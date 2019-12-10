package com.deng.dao;

import com.deng.db.DbClose;
import com.deng.db.DbConn;
import com.deng.entity.SalesMan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @Description:
 * @Author asus
 * @Date 2019/12/1 14:21
 */
public class SalesManDao {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    /**
     * @Description: 根据登录名查询数据库中密码
     * @pram [sName]
     * @return: java.util.ArrayList<com.deng.entity.SalesMan>
     * @auther: Deng
     * @date: 2019/12/4 20:55
     */
    public ArrayList<SalesMan> checkstandLog(String sName){
        ArrayList<SalesMan> salesManInfo = new ArrayList<SalesMan>();
        conn = DbConn.getConn();
        String sql = "SELECT SID,SPASSWORD FROM SALESMAN WHERE SNAME=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,sName);
            rs = pstmt.executeQuery();
            while (rs.next()){
                String sPassword = rs.getString("spassword");
                int sId = rs.getInt("sId");
                SalesMan salesMan = new SalesMan(sId, sPassword);
                salesManInfo.add(salesMan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.queryClose(pstmt,rs,conn);
        }
        return salesManInfo;
    }

    public boolean addSalesMan(SalesMan sName)
    {
        boolean bool = false;
        conn = DbConn.getConn();
        String sql = "INSERT INTO SALESMAN(SNAME,SPASSWORD) VALUES(?,?)";

        try
        {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,sName.getsName());
            pstmt.setString(2,sName.getsPassword());

            int rs = pstmt.executeUpdate();
            if (rs > 0)
            {
                bool = true;
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }finally
        {
            DbClose.addClose(pstmt,conn);
        }
        return bool;
    }


    public  boolean updateSalesMan(int key,SalesMan sName)
    {

        boolean bool = false;
        conn = DbConn.getConn();
        switch (key)
        {
            case 1:		//	3.1 �����ۻ�Ա����
                String sqlName = "UPDATE SALESMAN SET SNAME=? WHERE SID=?";

                try
                {
                    pstmt = conn.prepareStatement(sqlName);
                    pstmt.setString(1, sName.getsName());
                    pstmt.setInt(2,sName.getsId());

                    int rs = pstmt.executeUpdate();
                    if (rs > 0)
                    {
                        bool = true;
                    }
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }finally{
                    DbClose.addClose(pstmt,conn);
                }
                break;
            case 2:		//	3.2 �����ۻ�Ա����
                String sqlPrice = "UPDATE SALESMAN SET SPASSWORD=? WHERE SID=?";

                try
                {
                    pstmt = conn.prepareStatement(sqlPrice);
                    pstmt.setString(1,sName.getsPassword());
                    pstmt.setInt(2, sName.getsId());

                    int rs = pstmt.executeUpdate();
                    if (rs > 0)
                    {
                        bool = true;
                    }
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }finally{
                    DbClose.addClose(pstmt,conn);
                }
                break;
            default:
                break;
        }
        return bool;
    }

    /**
     * 4.ɾ���ۻ�Ա
     * @param sName �û���
     * @return boolean
     */
    public boolean deleteSalesMan(String sName)
    {
        boolean bool = false;
        conn = DbConn.getConn();
        String sql = "DELETE FROM SALESMAN WHERE SNAME=?";
        try
        {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,sName);
            int rs = pstmt.executeUpdate();
            if (rs > 0)
            {
                bool = true;
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }finally{
            DbClose.addClose(pstmt,conn);
        }
        return bool;
    }

    /**
     * 5.ģ����ѯ�ۻ�Ա
     * @param sName �û���
     * @return ArrayList<SalesMan>
     */
    public ArrayList<SalesMan> querySalesMan(String sName)
    {
        ArrayList<SalesMan> SalesManList = new ArrayList<SalesMan>();
        conn = DbConn.getConn();

        sName = "%"+sName+"%";	//���û�����ȡ���ַ������� % ���ţ����ﵽģ����ѯ��Ŀ��.�ַ��� �����ӻ��и�����ķ�ʽ�����Ż����룡
        String sql = "SELECT * FROM SALESMAN WHERE SNAME LIKE ?";  //��Ȼ����ֱ�Ӹ� % .ֻ���������ַ����ķ�ʽ
        try
        {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, sName);
            rs = pstmt.executeQuery();
            while (rs.next())
            {
                int sid = rs.getInt("sid");
                String sname = rs.getString(2);
                String sPassWord = rs.getString(3);

                SalesMan salesMan = new SalesMan(sid,sname,sPassWord);
                SalesManList.add(salesMan);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }finally
        {
            DbClose.queryClose(pstmt, rs, conn);
        }
        return SalesManList;
    }

    /**
     * 6.��ʾ�����ۻ�Ա
     * @return ArrayList<SalesMan>
     */
    public  ArrayList<SalesMan> displaySalesMan()
    {
        ArrayList<SalesMan> salesManList = new ArrayList<SalesMan>();
        conn = DbConn.getConn();
        String sql = "SELECT * FROM SALESMAN";

        try
        {
            pstmt = conn.prepareStatement(sql);
            rs =  pstmt.executeQuery();
            while (rs.next())
            {
                int sId = rs.getInt(1);
                String sName = rs.getString(2);
                String sSpassWord = rs.getString(3);

                SalesMan salesMan = new SalesMan(sId,sName,sSpassWord);
                salesManList.add(salesMan);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }finally
        {
            DbClose.queryClose(pstmt, rs, conn);
        }
        return salesManList;
    }
    
}
