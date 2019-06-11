package cn.yummy.service.Impl.merchant;

import cn.yummy.entity.merchant.ConsumersCharacteristics;
import cn.yummy.entity.merchant.Dish;
import cn.yummy.entity.merchant.MarketStatistics;
import cn.yummy.entity.merchant.SalesStatistics;
import cn.yummy.service.merchantService.ComprehensiveStatistics;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

@Service
public class ComprehensiveStatisticsImpl implements ComprehensiveStatistics {
    @Override
    public ConsumersCharacteristics getConsumersCharacteristics(LocalDate startTime, LocalDate endTime, String interval) {
        //地域分布
        HashMap<Integer,Integer> locationInterval = new HashMap<>();
        locationInterval.put(1,56);
        locationInterval.put(2,49);
        locationInterval.put(3,29);
        locationInterval.put(5,16);
        locationInterval.put(7,3);
        locationInterval.put(10,0);

        //消费者总消费金额分布
        HashMap<Integer,Integer> consumptionAMountInterval = new HashMap<>();
        consumptionAMountInterval.put(10,0);
        consumptionAMountInterval.put(20,42);
        consumptionAMountInterval.put(30,47);
        consumptionAMountInterval.put(40,30);
        consumptionAMountInterval.put(50,12);
        consumptionAMountInterval.put(70,5);
        consumptionAMountInterval.put(100,1);
        consumptionAMountInterval.put(200,0);

        //消费次数(留存率)
        HashMap<String,Integer> consumptionTimes = new HashMap<>();
        consumptionTimes.put("1",17);
        consumptionTimes.put("2",50);
        consumptionTimes.put("3",57);
        consumptionTimes.put("4",43);
        consumptionTimes.put("5",50);
        consumptionTimes.put("5+",66);

        //新客率
        HashMap<LocalDate,Integer> newConsumersProportion = new HashMap<>();
        for(int i=1;i<30;i++){
            newConsumersProportion.put(LocalDate.of(2019,6,i),(int)(Math.random()*15));
        }

        return new ConsumersCharacteristics(locationInterval,consumptionAMountInterval,consumptionTimes,newConsumersProportion);
    }

    @Override
    public SalesStatistics getSalesStatistics(LocalDate startTime, LocalDate endTime, String interval) {
        double income = 5432.35;
        //订单数
        int orderNums = 265;
        //平均订单价格
        double averagePriceOfOrders = 20.50;
        //菜品销售数
        HashMap<String,Integer> dishesProportion = new HashMap<>();
        for(int i=1;i<12;i++){
            int temp = (int)(Math.random()*100);
            dishesProportion.put("菜品"+i,temp);
        }

        //订单价格分布
        HashMap<String,Integer> orderPricesInterval = new HashMap<>();
        for(int i=1;i<10;i++){
            int temp = (int)(Math.random()*100);
            orderPricesInterval.put(i*10+"",temp);
        }
        orderPricesInterval.put("100+",43);

        //订单时间分布
        HashMap<LocalTime,Integer> orderTimeInterval = new HashMap<>();
        for(int i=0;i<13;i++){
            int temp = (int)(Math.random()*100);
            orderTimeInterval.put(LocalTime.of(9+i,0),temp);
        }

        //销售额涨跌
        HashMap<LocalDate,Double>  salesAmountCondition = new HashMap<>();
        for(int i=1;i<30;i++){
            int temp = (int)(Math.random()*100);
            salesAmountCondition.put(LocalDate.of(2016,6,i),500.0+temp);
        }



        return new SalesStatistics(income,orderNums,averagePriceOfOrders,dishesProportion,orderPricesInterval,orderTimeInterval,salesAmountCondition);
    }

    @Override
    public MarketStatistics getMarkerStatistics(LocalDate startTime, LocalDate endTime, String interval) {
        return new MarketStatistics(0.98,0.004);
    }
}
