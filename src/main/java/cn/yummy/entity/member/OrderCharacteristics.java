package cn.yummy.entity.member;

import cn.yummy.entity.order.Order;

import java.time.LocalDate;
import java.util.HashMap;

public class OrderCharacteristics {
    //订单统计
    private int acceptedOrdersNum;

    //订单数增长统计
    private HashMap<LocalDate,Integer> ordersCount;

    //消费数增长统计
    private HashMap<LocalDate,Double> consumptionCount;

    public OrderCharacteristics() {
    }

    public OrderCharacteristics(int acceptedOrdersNum, HashMap<LocalDate, Integer> ordersCount, HashMap<LocalDate, Double> consumptionCount) {
        this.acceptedOrdersNum = acceptedOrdersNum;
        this.ordersCount = ordersCount;
        this.consumptionCount = consumptionCount;
    }

    public int getAcceptedOrdersNum() {
        return acceptedOrdersNum;
    }

    public void setAcceptedOrdersNum(int acceptedOrdersNum) {
        this.acceptedOrdersNum = acceptedOrdersNum;
    }

    public HashMap<LocalDate, Integer> getOrdersCount() {
        return ordersCount;
    }

    public void setOrdersCount(HashMap<LocalDate, Integer> ordersCount) {
        this.ordersCount = ordersCount;
    }

    public HashMap<LocalDate, Double> getConsumptionCount() {
        return consumptionCount;
    }

    public void setConsumptionCount(HashMap<LocalDate, Double> consumptionCount) {
        this.consumptionCount = consumptionCount;
    }

    //    public static void main(String[] args) {
//
//        new OrderCharacteristics().test();
//
//    }
//    public void test(){
//        LocalDate date = LocalDate.of(2019,6,12);
//        System.out.println(date.toString());
//        LocalDate  d = date.minusDays(1);
//        System.out.println(d.toString());
//
//
//    }

}
