package com.deng.page;



import com.deng.dao.GoodsDao;
import com.deng.dao.GsalesDao;
import com.deng.dao.SalesManDao;
import com.deng.entity.Goods;
import com.deng.entity.Gsales;
import com.deng.entity.SalesMan;
import com.deng.tools.Arith;
import com.deng.tools.QueryPrint;
import com.deng.tools.ScannerChoice;
import com.sun.org.apache.bcel.internal.generic.BranchHandle;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.xml.internal.ws.api.databinding.ClientCallBridge;
import jdk.nashorn.internal.objects.NativeError;
import org.junit.Test;

import javax.print.attribute.AttributeSetUtilities;
import javax.swing.text.html.CSS;
import javax.xml.transform.Source;
import java.text.BreakIterator;
import java.util.ArrayList;

/**
 * @Description:
 * @Author asus
 * @Date 2019/11/3 16:26
 */

public class MainPage extends ScannerChoice {
    public static void main(String[] args) {
       /* MainPage ma = new MainPage();
        ma.mainPage();*/
       mainPage();
    }

    public static void mainPage(){
        System.out.println("***************************\n");
        System.out.println("\t 1.商品维护\n");
        System.out.println("\t 2.前台收银\n");
        System.out.println("\t 3.商品管理\n");
        System.out.println("***************************");
        System.out.println("\n请输入选项,或者按0退出.");
        do{
           String choice = ScannerChoice.ScannerInfoString();
            if(choice.matches("[0-3]")){
              int info = Integer.parseInt(choice);
              switch (info){
                  //退出系统
                  case 0:
                      System.out.println("----------------");
                      System.out.println("您已退出系统");
                      System.exit(1);
                      break;
                      //商品维护
                  case 1:
                      MaintenancePage();
                      break;
                      //前台收银
                  case 2:
                      checkstandLogPage();
                      break;
                      //商品管理
                  case 3:
                      commodityManagementPage();
                      break;
                  default: break;

              }
            }else {
                System.out.println("输入有误！");
                System.out.println("请重新输入或按0退出");
            }
        }while(true);

    }

    /**
     * @Description: MaintenancePage 商品维护界面
     * @pram []
     * @return: void
     * @auther: Deng
     * @date: 2019/11/3 17:31
     */
    public static void MaintenancePage(){
        System.out.println("1.添加商品");
        System.out.println("2.更改商品");
        System.out.println("3.删除商品");
        System.out.println("4.显示所有商品");
        System.out.println("5.查询商品");
        System.out.println("*********************");
        System.out.print("请选择，输入数字或按0返回上一级菜单：");

        do{
            String choice = ScannerChoice.ScannerInfoString();

            if(choice.matches("[0-5]")){
                int info = Integer.parseInt(choice);
                switch (info){
                    case 0:
                        mainPage();
                        break;
                        //添加
                    case 1:
                        GoodsPage.addGoodsPage();
                        break;
                        //更改
                    case 2:
                        GoodsPage.updateGoodsPage();
                        break;
                        //删除
                    case 3:
                        GoodsPage.deleteGoodsPage();
                        break;
                        //显示所有
                    case 4:
                        GoodsPage.displayGoodsPage();
                        break;
                        //查询
                    case 5:
                        GoodsPage.queryGoodsPage();
                        break;
                }
            }
            else {
                System.out.println("输入有误！");
                System.out.println("重新输入或按0返回上一级菜单");
            }
        }while (true);

    }


    /**
     * @Description: checkstandLogPage 前台收银界面
     * @pram []
     * @return: void
     * @auther: Deng
     * @date: 2019/11/3 17:32
     */
    public static void checkstandLogPage(){
        System.out.println("\n*******欢迎使用商超购物管理系统*******\n");
        System.out.println("\t 1.登录系统\n");
        System.out.println("\t 2.退出\n");
        System.out.println("-----------------------------");
        System.out.println("请输入选项,或者按 0 返回上一级菜单.");

        do {
            String info = ScannerChoice.ScannerInfoString();
            String regex = "[0-2]";
            if (info.matches(regex)){
                int choice = Integer.parseInt(info);
                switch (choice){
                    case 0:
                        MainPage.mainPage();
                        break;
                    case 1:
                        //售货员登陆
                        //三次登陆机会
                        int loginTime = 3;
                        while (loginTime != 0){
                            loginTime--;
                            System.out.println("---用户名---");
                            String sName = ScannerChoice.ScannerInfoString();
                            System.out.println("---密码---");
                            String sPasswd = ScannerChoice.ScannerInfoString();
                            ArrayList<SalesMan> salesManList = new ArrayList<SalesMan>();
                            salesManList = new SalesManDao().checkstandLog(sName);
                            if (salesManList == null||salesManList.size()<=0){
                                System.out.println("用户名不正确");
                                System.out.println("\n剩余登陆次数" + loginTime);
                            } else {
                                SalesMan salesMan = salesManList.get(0);
                                if (sPasswd.equals(salesMan.getsPassword())){
                                    System.out.println("登陆成功");
                                    //
                                }else {
                                    System.out.println("密码输入错误");
                                    System.out.println("\n剩余登陆次数" + loginTime);
                                }
                            }
                        }
                        System.out.println("---------");
                        System.err.println("已强制退出系统");
                        System.exit(1);
                        break;
                    case 2:
                        //退出
                        System.out.println("----------------");
                        System.out.println("您已退出系统");
                        System.exit(2);
                        break;
                }
            }
        }while (true);
    }


    /**
     * @Description: commodityManagementPage  商品管理界面
     * @pram []
     * @return: void
     * @auther: Deng
     * @date: 2019/11/3 17:32
     */
    public static void commodityManagementPage(){
        System.out.println("***************************\n");
        System.out.println("\t 1.售货员管理\n");
        System.out.println("\t 2.列出当日卖出列表\n");
        System.out.println("***************************");

        System.out.println("\n请输入选项,或者按 0 返回上一级菜单.");

        do {
            String choice = ScannerInfoString();
            String regex = "[0-2]";
            if (choice.matches(regex)){
                int info = Integer.parseInt(choice);
                switch (info){
                    case 0:
                        mainPage();
                        break;

                    case 1:
                        salesManManagementPage();
                        break;
                    case 2:
                        GsalesPage.dailySaleGoodsPage();
                        break;
                        default:
                            break;
                }
            }
            System.out.println("输入有误");
            System.out.println("重新输入或按0返回上一级");
        }while (true);
    }

    public static void salesManManagementPage() {
        System.out.println("***************************\n");
        System.out.println("\t 1.添加售货员\n");
        System.out.println("\t 2.更改售货员\n");
        System.out.println("\t 3.删除售货员\n");
        System.out.println("\t 4.查询售货员\n");
        System.out.println("\t 5.显示所有售货员\n");
        System.out.println("***************************");

        System.out.println("\n请输入选项,或者按 0 返回上一级菜单.");
        do {
            String choice = ScannerInfoString();
            String regex = "[0-5]";
            if (choice.matches(regex)){
                int info = Integer.parseInt(choice);
                switch (info){
                    case 0:
                        commodityManagementPage();
                        break;
                    case 1:
                        SalesManPage.addSalesManPage();
                        break;
                    case 2:
                        SalesManPage.updateSalesManPage();
                        break;
                    case 3:
                        SalesManPage.deleteSalesManPage();
                        break;
                    case 4:
                        SalesManPage.querySalesManPage();
                        break;
                    case 5:
                        SalesManPage.displaySalesManPage();
                        break;
                    default:
                        break;
                }
            }
            System.err.println("\t!输入有误!");
            System.out.println("重新输入或按 0 返回上一级菜单.");
        }while(true);
    }

    public static void shoppingSettlementPage(int salesManSid){
        System.out.println("按 S 开始购物结算 按 0 返回账号登陆界面");
        String choNext = ScannerChoice.ScannerInfoString();
        if ("s".equals(choNext) || "S".equals(choNext)){
            System.out.println("\n--输入商品关键字---");
            int gid = QueryPrint.querySettlement();
            switch (gid){
                case -3:
                    break;
                case -1:
                    System.out.println("商品已售空");
                    break;
                default:
                    System.out.println("按商品编号选择商品");
                    int shoppingGid = ScannerNum();
                    ArrayList<Goods> goodList = new QueryPrint().queryGoodsKey(shoppingGid, null);
                    if (goodList == null || goodList.size() <= 0){
                        System.err.println("\t无此商品");
                    } else {
                        Goods goods = goodList.get(0);
                        int gNum = goods.getGnum();
                        double gPrice = goods.getGprice();
                        System.out.println("输入购买数量");
                        do {
                            int choiceGoodsNum = ScannerNum();
                            if (gNum < choiceGoodsNum){
                                System.out.println("商品数量不足");
                                System.out.println("请重新输入购买数量");
                            } else {
                                double allPrice = Arith.mul(choiceGoodsNum, gPrice);
                                System.out.println("\t\t\t购物车结算\n");
                                System.out.println("\t\t\t商品名称\t商品单价\t购买数量\t总价\n");
                                System.out.println("\t\t" + goods.getGname() + "\t\t" + goods.getGprice() + "\t\t" + choiceGoodsNum + "\t\t" + allPrice);
                                do {
                                    System.out.println("确认购买(Y/N)");
                                    String choShopping  = ScannerChoice.ScannerInfoString();
                                    if ("Y".equals(choShopping) || "y".equals(choShopping)){
                                        System.out.println("\n总价：" + allPrice + "$");
                                        System.out.println("实际缴费金额");
                                        do {
                                            double amount = ScannerInfo();
                                            double balance = Arith.sub(amount, allPrice);
                                            if (balance < 0){
                                                System.err.println("缴纳金额不足");
                                                System.out.println("请重新输入金额");
                                            } else {
                                                /*  1.更改goods表数量
                                                    2.增加sales表数量
                                                    原商品数量gNum。 结算员Id  salesManSid */
                                                //对sales表操作
                                                Gsales gSales = new Gsales(goods.getGid(),salesManSid,choiceGoodsNum);
                                                boolean insert = new GsalesDao().shoppingSettlement(gSales);

                                                //对goods表操作
                                                int goodsNewNum = gNum - choiceGoodsNum;
                                                Goods newGoods = new Goods(goods.getGid(),goodsNewNum);
                                                boolean update = new GoodsDao().updateGoods(3,newGoods);

                                                if (update && insert){
                                                    System.out.println("找零：" + balance);
                                                    System.out.println("欢迎下次光临");
                                                } else {
                                                    System.err.println("支付失败");
                                                }
                                                shoppingSettlementPage(salesManSid);
                                            }
                                        }while (true);
                                    } else if ("N".equals(choShopping) ||"n".equals(choShopping)){
                                        shoppingSettlementPage(salesManSid);
                                    }
                                }while (true);
                            }
                        }while (true);
                    }
                    break;
            }
        } else if ("0".equals(choNext)){
            checkstandLogPage();
        } else {
            System.out.println("输入错误，请重新输入");
            System.out.println("------------");
            shoppingSettlementPage(salesManSid);
        }
    }
}

