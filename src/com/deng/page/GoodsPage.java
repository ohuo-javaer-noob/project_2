package com.deng.page;

import com.deng.dao.GoodsDao;
import com.deng.entity.Goods;
import com.deng.tools.QueryPrint;
import com.deng.tools.ScannerChoice;

import java.util.ArrayList;

import static com.deng.tools.ScannerChoice.ScannerInfo;
import static com.deng.tools.ScannerChoice.ScannerInfoString;
import static com.deng.tools.ScannerChoice.changedInfoNext;

/**
 * @Description:
 * @Author asus
 * @Date 2019/11/3 17:56
 */
public class GoodsPage {

    /**
     * @Description: 商品添加界面
     * @pram []
     * @return: void
     * @auther: Deng
     * @date: 2019/11/17 18:18
     */
    public static void addGoodsPage(){
        System.out.println("\t正在执行添加商品操作\n");
        System.out.println("\n请输入添加商品--名称");
        String goodName = ScannerChoice.ScannerInfoString();
        System.out.println("\n请输入添加商品--价格");
        double goodPrice = ScannerChoice.ScannerInfo();
        System.out.println("\n请输入添加商品--数量");
        int goodNum = ScannerChoice.ScannerNum();

        Goods goods = new Goods(goodName,goodPrice,goodNum);
        boolean bool = new GoodsDao().addGoods(goods);
        if (bool){
            System.out.println("\n您已成功添加商品到数据库");
        }else {
            System.out.println("\n商品添加失败");
        }

    }

    /**
     * @Description: 修改商品界面
     * @pram []
     * @return: void
     * @auther: Deng
     * @date: 2019/11/17 18:20
     */
    public static void updateGoodsPage(){
        System.out.println("\t正在执行修改商品操作\n");
        System.out.println("\t请输入想要更改商品的名字");
        int gid = QueryPrint.query("updateGoodsPage");

        System.out.println("\n--------请选择您要更改的内容\n");
        System.out.println("\t1.更改商品-名称");
        System.out.println("\t2.更改商品-价格");
        System.out.println("\t3.更改商品-数量");
        System.out.println("\n请输入选项,或者按0返回上一级菜单.");

        do {
            String choice = ScannerChoice.ScannerInfoString();
            String regex = "[0-3]";

            if (choice.matches(regex)){
                int info = Integer.parseInt(choice);
                switch (info){
                    case 0:
                        MainPage.mainPage();
                        break;
                    case 1:
                        System.out.println("输入更改商品的名称：");
                        String gName = ScannerChoice.ScannerInfoString();
                        Goods goodName = new Goods(gid, gName);
                        boolean boolName = new GoodsDao().updateGoods(gid, goodName);
                        if (boolName){
                            System.out.println("商品名称更新成功");
                        } else System.out.println("商品名称更新失败");
                        changedInfoNext("updateGoodsPage");
                        break;
                    case 2:
                        System.out.println("输入更改商品的价格");
                        double gPrice = ScannerChoice.ScannerInfo();
                        Goods goodPrice = new Goods(gid, gPrice);
                        boolean boolUpdate = new GoodsDao().updateGoods(gid, goodPrice);
                        if (boolUpdate){
                            System.out.println("商品价格更新成功");
                        } else {
                            System.out.println("商品价格更新失败");
                        }
                        changedInfoNext("updateGoodsPage");
                        break;
                    case 3:
                        System.out.println("输入更改商品的数量");
                        int gnum = ScannerChoice.ScannerNum();
                        Goods goodNum = new Goods(gid, gnum);
                        boolean boolNum = new GoodsDao().updateGoods(gid,goodNum);
                        if (boolNum){
                            System.out.println("商品数量更新成功");
                        } else {
                            System.out.println("商品数量更新失败");
                        }
                        changedInfoNext("updateGoodsPage");
                        break;
                        default:
                            System.out.println("请输入正确的选择");
                            break;
                }
            }
            System.err.println("输入有误");
            System.out.println("请重新选择,或者按0返回上一级菜单.");
        } while (true);
    }

    /**
     * @Description: 删除商品界面
     * @pram []
     * @return: void
     * @auther: Deng
     * @date: 2019/11/30 17:38
     */
    public static void deleteGoodsPage(){
        System.out.println("\t正在执行 删除商品 操作\n");
        System.out.println("请输入想要删除的商品名字");

        int gid = QueryPrint.query("deleteGoodsPage"); //return the goods gid
        do {
            System.out.println("\n确认删除该商品(Y/N)");
            String choice = ScannerChoice.ScannerInfoString();
            if ("Y".equals(choice)||"y".equals(choice)){
                boolean bool = new GoodsDao().deleteGoods(gid);
                if (bool){
                    System.out.println("删除成功");
                } else {
                    System.err.println("删除失败");
                    changedInfoNext("deleteGoodsPage");
                }
            } else if("N".equals(choice)||"n".equals(choice)){
                MainPage.mainPage();
            }
            System.out.println("\t输入有误，请重新输入");
        } while (true);
    }

    /**
     * @Description: 查询商品界面
     * @pram []
     * @return: void
     * @auther: Deng
     * @date: 2019/11/30 17:55
     */
    public static void queryGoodsPage(){
        System.out.println("\t\t  正在执行查询商品操作\n");
        System.out.println("\t\t1.按照商品 数量升序 查询");
        System.out.println("\t\t2.按照商品 价格升序 查询");
        System.out.println("\t\t3.输入商品  关键字  查询");
        System.out.println("\n请输入选项,或者按0返回上一级菜单.");

        do {
            String info = ScannerInfoString();
            String regex = "[0-3]";
            if (info.matches(regex)){
                int choice = Integer.parseInt(info);
                switch (choice){
                    case 0:
                        MainPage.mainPage();
                        break;
                    case 1:

                    case 2:

                    case 3:
                        if (choice == 3) {
                            System.out.println("\t\t正在执行商品  关键字  查询操作\n");
                            System.out.println("\n请输入商品关键字");
                        }
                        ArrayList<Goods> goodsList = new GoodsDao().queryGoods(choice);
                        if (goodsList == null || goodsList.size() <= 0){
                            System.err.println("\n\t查询的商品不存在");
                            queryGoodsPage();
                        } else {
                            if (choice == 1) //打印目录，不要放在功能函数中，会影响其他方法调用
                            {
                                System.out.println("\t\t\t\t\t商品按照 数量升序 列表\n\n");
                            }else if (choice == 2)
                            {
                                System.out.println("\t\t\t\t\t商品按照 价格升序 列表\n\n");
                            }else
                            {
                                System.out.println("\t\t\t\t\t您所查找的商品如下\n\n");
                            }
                            System.out.println("\t商品编号\t\t商品名称\t\t商品价格\t\t商品数量\t\t备注\n");
                            for (int i= 0; i < goodsList.size(); i++){
                                Goods goods = goodsList.get(i);
                                System.out.print("\t"+goods.getGid()+"\t\t"+goods.getGname()+"\t\t"+goods.getGprice()+"\t\t"+goods.getGnum());
                                int gnum = goods.getGnum();
                                if (gnum ==0)
                                {
                                    System.out.println("\t\t该商品已售空！");
                                }else if (gnum<10)
                                {
                                    System.out.println("\t\t该商品已不足10件");
                                }else
                                {
                                    System.out.println("\t\t-");
                                }
                                System.out.println("\t");
                            }
                            System.out.println("---------------------");
                            }
                        }
                        break;
                }
            System.out.println("请正确输入");
            } while (true);
        //用户选择操作完查询后的下一步
        System.out.println("\n\n输入 0 返回上一级菜单");
        boolean boolNext = true;
        do
        {
            String choice = ScannerInfoString();
            if ("0".equals(choice))
            {
                boolNext = false;
                queryGoodsPage();
            }
            System.err.println("!输入有误!");
            System.out.println("请输入 0 返回上一级菜单");
        } while (boolNext);
    }

    /**
     * 5.展示所有商品界面
     */
    public static void displayGoodsPage()
    {
        System.out.println("\t\t\t\t\t所有商品列表\n\n");
        ArrayList<Goods> goodsList = new GoodsDao().displayGoods();

        if (goodsList.size() <= 0)
        {
            System.err.println("！库存为空！");
            MainPage.MaintenancePage();
        }else
        {
            System.out.println("\t商品编号\t\t商品名称\t\t商品价格\t\t商品数量\t\t备注\n");
            for (int i = 0,length = goodsList.size(); i < length; i++) //避免重复计算变量，浪费资源！
            {
                Goods goods = goodsList.get(i);
                System.out.print("\t"+goods.getGid()+"\t\t"+goods.getGname()+"\t\t"+goods.getGprice()+" $\t\t"+goods.getGnum());

                int gNum = goods.getGnum();
                if (gNum==0)
                {
                    System.out.println("\t\t该商品已售空！");
                }else if (gNum<10)
                {
                    System.out.println("\t\t该商品已不足10件");
                }else
                {
                    System.out.println("\t\t-");
                }
                System.out.println("\t");
            }
            //下一步
            System.out.println("---------------------");
            do
            {
                System.out.println("输入 0 返回上一级菜单");
                String choice = ScannerInfoString();
                if ("0".equals(choice))
                {
                    MainPage.MaintenancePage();
                }
                System.out.println("输入有误！");
            } while (true);
        }
    }
}


