package com.deng.tools;

import com.deng.entity.Goods;
import com.deng.page.GoodsPage;
import com.deng.page.MainPage;
import com.deng.page.SalesManPage;

import java.util.Scanner;

/**
 * @Description:
 * @Author asus
 * @Date 2019/11/3 16:43
 */
public class ScannerChoice {
    /**
     * @Description: 从键盘获得输入
     * @pram
     * @return:
     * @auther: Deng
     * @date: 2019/11/3 16:45
     */
    public static String ScannerInfoString(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入：");
        return scanner.next();
    }

    /**
     * @Description: ScannerInfo 价格输入 小数点后两位
     * @pram []
     * @return: double
     * @auther: Deng
     * @date: 2019/11/3 18:15
     */
    public static double ScannerInfo(){
        double num = 0.00;
        do{
            Scanner sc = new Scanner(System.in);
            System.out.print("保留小数点后两位，请输入：");
            String info = sc.next();
            String regex = "(([1-9][0-9]*)\\.([0-9]{2}))|[0]\\.([0-9]{2})";
            if(info.matches(regex)) {
                num = Double.parseDouble(info);
            }else {
                System.out.println("输入有误");
                continue;
            }
            break;
        }while (true);
        return num;
    }

    /**
     * @Description: ScannerNum 商品数量输入
     * @pram []
     * @return: int
     * @auther: Deng
     * @date: 2019/11/3 18:16
     */
    public static int ScannerNum(){
        int num;
        String regex = "([1-9])|([1-9][0-9]+)";
        do{
            Scanner sc = new Scanner(System.in);
            String snum = sc.next();
            if(snum.matches(regex)){
                num = Integer.parseInt(snum);
            }else{
                System.out.println("输入有误");
                continue;
            }
            break;
        }while (true);
        return num;
    }

    /**
     * @Description: 下一步操作
     * @pram
     * @return:
     * @auther: Deng
     * @date: 2019/11/30 13:12
     */
    public static void changedInfoNext(String oper){
        do{
            System.out.println("是否继续进行 - 当前操作(Y/N)");
            String choice = ScannerChoice.ScannerInfoString();
            if("y".equals(choice) || "Y".equals(choice)){
                if ("updateGoodsPage".equals(oper)){
                    GoodsPage.updateGoodsPage();
                }else if ("deleteGoodsPage".equals(oper)){
                    GoodsPage.deleteGoodsPage();
                }else if ("addGoodsPage".equals(oper)){
                    GoodsPage.addGoodsPage();
                }
            }else if("N".equals(choice)||"n".equals(choice)){
                MainPage.mainPage();
            }
            System.out.println("\n输入有误，重新输入");
        }while (true);
    }

    /**
     * 获取用户-更改-完售货员-下一步
     * 获取用户-添加-完售货员-下一步
     * 获取用户-查询-完售货员-下一步
     * 获取用户-删除-完售货员-下一步
     * @param 调用者
     */
    public static void choiceSalesManNext(String oper)
    {
        do
        {
            System.out.println("是否继续进行-当前操作:(Y/N)");
            String choice = ScannerChoice.ScannerInfoString();

            if ( "y".equals(choice) || "Y".equals(choice) )
            {
                //下面的嵌套if-else 是让用户选择继续操作当前步骤所跳转到指定页面。（因为不同函数调用，跳转的指定函数不同）
                if ("updateSalesMan".equals(oper))
                {
                    SalesManPage.updateSalesManPage();
                }else if ("deleteSalesMan".equals(oper))
                {
                    SalesManPage.deleteSalesManPage();
                }else if ("addSalesMan".equals(oper))
                {
                    SalesManPage.addSalesManPage();
                }else if ("querySalesMan".equals(oper))
                {
                    SalesManPage.querySalesManPage();
                }
                //上面的嵌套结束
            }else if ("N".equals(choice) || "n".equals(choice))
            {
                MainPage.salesManManagementPage();
            }
            System.err.println("\t输入有误！");
        } while (true);
    }
}
