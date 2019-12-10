package com.deng.entity;

/**
 * @Description:商品实体类
 * @pram
 * @return:
 * @auther: Deng
 * @date: 2019/11/3 17:40
 */
public final class Goods {
    //数据库Goods表主键
    private int gid;
    private String gname;
    private double gprice;
    private int gnum;

    /**
     * 添加商品信息
     * @param gname,gprice,gnum
     */
    public Goods(String gname, double gprice, int gnum){
        this.gname = gname;
        this.gprice = gprice;
        this.gnum = gnum;
    }

    /**
     * 显示商品信息
     * @param gname,gprice,gnum
     */
    public Goods(int gid, String gname, double gprice, int gnum){
        this.gname = gname;
        this.gprice = gprice;
        this.gnum = gnum;
        this.gid = gid;
    }

    /**
     * 根据编号更改商品信息
     * @param gid,gnum
     */
    public Goods(int gid,int gnum)
    {
        this.gid	= gid;
        this.gnum 	= gnum;
    }
    /**
     * 根据编号更改商品信息
     * @param gid,gprice
     */
    public  Goods(int gid,double gprice)
    {
        this.gid	= gid;
        this.gprice = gprice;
    }

    /**
     * 根据编号更改商品信息
     * @param gid,gname
     */
    public  Goods(int gid,String gname)
    {
        this.gid	= gid;
        this.gname  = gname;
    }



    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public double getGprice() {
        return gprice;
    }

    public void setGprice(double gprice) {
        this.gprice = gprice;
    }

    public int getGnum() {
        return gnum;
    }

    public void setGnum(int gnum) {
        this.gnum = gnum;
    }
}
