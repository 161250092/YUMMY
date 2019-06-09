package cn.yummy.entity.merchant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;


public class SalesStatistics {
    //销售额
    private double income;
    //订单数
    private int orderNums;
    //平均订单价格
    private double averagePriceOfOrders;
    //菜品销售数
    private HashMap<Dish,Integer> dishesProportion;
    //订单价格分布
    private HashMap<Integer,Integer> OrderPricesInterval;
    //订单时间分布
    private HashMap<LocalDateTime,Integer> OrderTimeInterval;
    //销售额涨跌
    private HashMap<LocalDate,Double>  salesAmountCondition;


}
