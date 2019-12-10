package com.deng.entity;

/**
 * @Description: 售货员实体类
 * @Author asus
 * @Date 2019/11/3 17:40
 */
public class SalesMan {
    private int sId;
    private String sName;
    private String sPassword;

    /**
 * @Description: SalesMan 验证用户登陆
 * @pram [sId, sPassword]
 * @return:
 * @auther: Deng
 * @date:
 */
    public SalesMan(int sId, String sPassword) {
        this.sId = sId;
        this.sPassword = sPassword;
    }

    /**
 * @Description: SalesMan 查询用户 更改密码
 * @pram [sId, sName, sPassword]
 * @return:
 * @auther: Deng
 * @date:
 */
    public SalesMan(int sId, String sName, String sPassword) {
        this.sId = sId;
        this.sName = sName;
        this.sPassword = sPassword;
    }

    /**
 * @Description: SalesMan 添加用户
 * @pram [sName, sPassword]
 * @return:
 * @auther: Deng
 * @date:
 */
    public SalesMan(String sName, String sPassword) {
        this.sName = sName;
        this.sPassword = sPassword;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsPassword() {
        return sPassword;
    }

    public void setsPassword(String sPassword) {
        this.sPassword = sPassword;
    }
}
