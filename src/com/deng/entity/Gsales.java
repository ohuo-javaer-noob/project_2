package com.deng.entity;

/**
 * @Description:商品结算实体类
 * @Author asus
 * @Date 2019/11/3 17:40
 */
public class Gsales {
    private int gId;
    private int sId;
    private int sNum;

    private String gName;
    private double gPrice;
    private int gNum;
    private int allSnum;  //单种商品销量总和

    /**
 * @Description: Gsales 购物结算
 * @pram [gId, sId, sNum]
 * @return:
 * @auther: Deng
 * @date:
 */
    public Gsales(int gId, int sId, int sNum) {
        this.gId = gId;
        this.sId = sId;
        this.sNum = sNum;
    }

    /**
 * @Description: Gsales展现商品列表
 * @pram [gName, gPrice, gNum, allSnum]
 * @return:
 * @auther: Deng
 * @date:
 */
    public Gsales(String gName, double gPrice, int gNum, int allSnum) {
        this.gName = gName;
        this.gPrice = gPrice;
        this.gNum = gNum;
        this.allSnum = allSnum;
    }

    public int getgId() {
        return gId;
    }

    public void setgId(int gId) {
        this.gId = gId;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public int getsNum() {
        return sNum;
    }

    public void setsNum(int sNum) {
        this.sNum = sNum;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public double getgPrice() {
        return gPrice;
    }

    public void setgPrice(double gPrice) {
        this.gPrice = gPrice;
    }

    public int getgNum() {
        return gNum;
    }

    public void setgNum(int gNum) {
        this.gNum = gNum;
    }

    public int getAllSnum() {
        return allSnum;
    }

    public void setAllSnum(int allSnum) {
        this.allSnum = allSnum;
    }
}
