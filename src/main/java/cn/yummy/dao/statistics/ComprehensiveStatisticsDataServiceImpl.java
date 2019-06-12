package cn.yummy.dao.statistics;

import cn.yummy.dao.merchantDao.MerchantOrdersDataServiceImpl;
import cn.yummy.entity.member.DishForMember;
import cn.yummy.entity.merchant.ConsumersCharacteristics;
import cn.yummy.entity.merchant.MarketStatistics;
import cn.yummy.entity.merchant.SalesStatistics;
import cn.yummy.entity.order.MerchantSearchEntity;
import cn.yummy.entity.order.Order;
import cn.yummy.entity.primitiveType.Location;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.HashMap;
import java.util.List;

@Service
public class ComprehensiveStatisticsDataServiceImpl implements ComprehensiveStatisticsDataService{
    private static MerchantOrdersDataServiceImpl merchantOrdersDataService = new MerchantOrdersDataServiceImpl();
    private static int[] distanceInterval = {0,1,2,3,4,5,100};
    private static int[] timesInterval ={0,5,10,15,20,30,50,100};
    private static int[] consumptionAmounts = {0,10,30,50,100,200,500,1000,2000,5000,100000};

    private static int[] singleOrderPriceInterval = {0,10,20,30,50,100,500};
    @Override
    public ConsumersCharacteristics getConsumersCharacteristics(LocalDate startTime, LocalDate endTime, String idCode) {
        List<Order> orders = merchantOrdersDataService.checkMerchantOrders(new MerchantSearchEntity(idCode,startTime,endTime));
        //地域分布
        HashMap<Integer,Integer> locationInterval = new HashMap<>();
        //消费者总消费金额分布
        HashMap<Integer,Integer> consumptionAmountInterval = new HashMap<>();
        //消费次数(留存率)
        HashMap<String,Integer> consumptionTimes = new HashMap<>();
        //新客率
        HashMap<LocalDate,Integer> newConsumersProportion = new HashMap<>();


        for(int i=0;i<consumptionAmounts.length;i++){
            consumptionAmountInterval.put(consumptionAmounts[i],0);
        }

        for(int i=0;i<distanceInterval.length;i++){
            locationInterval.put(distanceInterval[i],0);
        }

        for(int i=0;i<timesInterval.length;i++){
            consumptionTimes.put(timesInterval[i]+"",0);
        }


        for(Order order:orders){
            //地域分布
            Location merchantLocation = Util.getLocation(idCode);
            Location memberLocation = order.getUserLocation();
            double distance = Util.getDistance(memberLocation,merchantLocation);

            for(int i=0;i<distanceInterval.length-1;i++){
                if(distanceInterval[i]<distance&&distanceInterval[i+1]>distance){
                    locationInterval.put(distanceInterval[i],locationInterval.get(distanceInterval[i]));
                }
            }

            //消费者总消费金额分布
            double totalConsumption = Util.getConsumersTotalConsumptionInStore(order.getAccount(),order.getIdCode());
            for(int i=0;i<consumptionAmounts.length-1;i++){
                if(consumptionAmounts[i]<distance&&consumptionAmounts[i+1]>distance){
                    consumptionAmountInterval.put(consumptionAmounts[i],consumptionAmountInterval.get(distanceInterval[i]));
                }
            }

            //消费次数(留存率)

            int times  = Util.getConsumptionTimes(order.getAccount(),order.getIdCode());

            for(int i=0;i<timesInterval.length-1;i++){
                if(timesInterval[i]<times&&times<timesInterval[i+1]){
                    consumptionTimes.put(timesInterval[i]+"",consumptionTimes.get(timesInterval[i]+""));
                }
            }



        }
        //新客率
        Period p = Period.between(endTime,startTime);
        int days = p.getDays();

        for(int i=0;i<days;i++){
            int temp =(int)(Math.random()*3);
            newConsumersProportion.put(startTime.plusDays(i),temp+1);
        }

        return new ConsumersCharacteristics(locationInterval,consumptionAmountInterval,consumptionTimes,newConsumersProportion);
    }




    @Override
    public SalesStatistics getSalesStatistics(LocalDate startTime, LocalDate endTime, String idCode) {

        //销售额
        double income = Util.getSalesVolume(idCode,startTime,endTime);
        //订单数
        int orderNums = Util.getOrdersNum(idCode,startTime,endTime);
        //平均订单价格
        double averagePriceOfOrder = income/orderNums;


        //菜品销售数
        HashMap<String,Integer> dishesProportion = new HashMap<>();
        //订单价格分布
        HashMap<String,Integer> orderPricesInterval = new HashMap<>();
        //订单时间分布
        HashMap<LocalTime,Integer> orderTimeInterval = new HashMap<>();
        //销售额涨跌
        HashMap<LocalDate,Double>  salesAmountCondition = new HashMap<>();


        for(int i=0;i<singleOrderPriceInterval.length;i++){
            orderPricesInterval.put(singleOrderPriceInterval[i]+"+",0);
        }

        for(int i=0;i<24;i++){
            orderTimeInterval.put(LocalTime.of(i,0),0);
        }


        List<Order> orders = merchantOrdersDataService.checkMerchantOrders(new MerchantSearchEntity(idCode,startTime,endTime));

        for(Order order:orders){
            //菜品销售数
            for(DishForMember dishForMember:order.getDishes()){
                if(dishesProportion.containsKey(dishForMember.getName())){
                    dishesProportion.put(dishForMember.getName(),dishesProportion.get(dishForMember.getName())+dishForMember.getQuantity());
                }
                else
                    dishesProportion.put(dishForMember.getName(),dishForMember.getQuantity());
            }
            //订单价格分布
            double orderPrice = order.getTotalPrice();
            for(int i=0;i<singleOrderPriceInterval.length-1;i++){
                if(singleOrderPriceInterval[i]<orderPrice&&singleOrderPriceInterval[i+1]>orderPrice){
                    String key = singleOrderPriceInterval[i]+"+";
                    orderPricesInterval.put(key,orderPricesInterval.get(key)+1);
                }
            }

            //订单时间分布
            LocalDateTime  localDateTime = order.getDeliveryTime();
            LocalTime localTime = localDateTime.toLocalTime().withMinute(0);
            orderTimeInterval.put(localTime,orderTimeInterval.get(localTime)+1);
        }

        Period p = Period.between(endTime,startTime);
        int days = p.getDays();

        for(int i=0;i<days;i++) {
            salesAmountCondition.put(startTime.plusDays(i),Util.getSalesVolume(idCode, startTime.plusDays(i)));
        }


        return new SalesStatistics(income,orderNums,averagePriceOfOrder,dishesProportion,orderPricesInterval,orderTimeInterval,salesAmountCondition);
    }

    @Override
    public MarketStatistics getMarkerStatistics(LocalDate startTime, LocalDate endTime, String idCode) {
        return new MarketStatistics(1.02,0.12);
    }
}
