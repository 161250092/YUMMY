package cn.yummy.entity.merchant;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;


public class SalesStatistics {
    //销售额
    private double income;
    //订单数
    private int orderNums;
    //平均订单价格
    private double averagePriceOfOrders;
    //菜品销售数
    private HashMap<String,Integer> dishesProportion;
    //订单价格分布
    private HashMap<String,Integer> orderPricesInterval;
    //订单时间分布
    private HashMap<LocalTime,Integer> orderTimeInterval;
    //销售额涨跌
    private HashMap<LocalDate,Double>  salesAmountCondition;


    public SalesStatistics() {
    }

    public SalesStatistics(double income, int orderNums, double averagePriceOfOrders, HashMap<String, Integer> dishesProportion, HashMap<String, Integer> orderPricesInterval, HashMap<LocalTime, Integer> orderTimeInterval, HashMap<LocalDate, Double> salesAmountCondition) {
        this.income = income;
        this.orderNums = orderNums;
        this.averagePriceOfOrders = averagePriceOfOrders;
        this.dishesProportion = dishesProportion;
        this.orderPricesInterval = orderPricesInterval;
        this.orderTimeInterval = orderTimeInterval;
        this.salesAmountCondition = salesAmountCondition;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public int getOrderNums() {
        return orderNums;
    }

    public void setOrderNums(int orderNums) {
        this.orderNums = orderNums;
    }

    public double getAveragePriceOfOrders() {
        return averagePriceOfOrders;
    }

    public void setAveragePriceOfOrders(double averagePriceOfOrders) {
        this.averagePriceOfOrders = averagePriceOfOrders;
    }

    public HashMap<String, Integer> getDishesProportion() {
        return dishesProportion;
    }

    public void setDishesProportion(HashMap<String, Integer> dishesProportion) {
        this.dishesProportion = dishesProportion;
    }

    public HashMap<LocalTime, Integer> getOrderTimeInterval() {
        return orderTimeInterval;
    }

    public void setOrderTimeInterval(HashMap<LocalTime, Integer> orderTimeInterval) {
        this.orderTimeInterval = orderTimeInterval;
    }

    public HashMap<LocalDate, Double> getSalesAmountCondition() {
        return salesAmountCondition;
    }

    public void setSalesAmountCondition(HashMap<LocalDate, Double> salesAmountCondition) {
        this.salesAmountCondition = salesAmountCondition;
    }

    public HashMap<String, Integer> getOrderPricesInterval() {
        return orderPricesInterval;
    }

    public void setOrderPricesInterval(HashMap<String, Integer> orderPricesInterval) {
        this.orderPricesInterval = orderPricesInterval;
    }


}
