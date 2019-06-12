package cn.yummy.dao.statistics;

import cn.yummy.dao.memberDao.MemberOrderDataServiceImpl;
import cn.yummy.dao.mysql.MySQLConnector;
import cn.yummy.entity.member.ConsumptionCharacteristics;
import cn.yummy.entity.member.DishForMember;
import cn.yummy.entity.member.OrderCharacteristics;
import cn.yummy.entity.merchant.Dish;
import cn.yummy.entity.order.MemberSearchEntity;
import cn.yummy.entity.order.Order;
import cn.yummy.entity.primitiveType.Location;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ConsumerStatisticsDataServiceImpl implements ConsumerStatisticsDataService{

    private static MemberOrderDataServiceImpl memberOrderDataService = new MemberOrderDataServiceImpl();



    private Connection conn;

    @Override
    public OrderCharacteristics getOrderCharacteristics(LocalDate startTime, LocalDate endTime,String account) {

        List orders = memberOrderDataService.searchMemberOrders(new MemberSearchEntity(account,startTime,endTime,0,Integer.MAX_VALUE));

      //  OrderCharacteristics orderCharacteristics = new OrderCharacteristics();
        HashMap<LocalDate,Integer> ordersCount = new HashMap<>();
        HashMap<LocalDate,Double> consumptionCount = new HashMap<>();

        int days = Period.between(endTime,startTime).getDays();

        for(int i=0;i<days;i++){
            LocalDate date = startTime.plusDays(i);
            ordersCount.put(date,getOrderCount(orders,date));
            consumptionCount.put(date,getOneDayConsumption(orders,date));
        }

        return new OrderCharacteristics(orders.size(),ordersCount,consumptionCount);
    }


    private int getOrderCount(List<Order> orders,LocalDate localDate){
        int count = 0;
        for(Order order:orders){
            if(order.getDeliveryTime().toLocalDate().toString().equals(localDate.toString())){
                count++;
            }
        }
        return count;
    }

    private double getOneDayConsumption(List<Order> orders,LocalDate localDate){
        double result = 0;
        for(Order order:orders){
            if(order.getDeliveryTime().toLocalDate().toString().equals(localDate.toString())){
                result +=order.getTotalPrice();
            }
        }
        return result;
    }

    private static int[] distanceInterval = {0,1,2,3,4,5,100};
    @Override
    public ConsumptionCharacteristics getConsumptionCharacteristics(LocalDate startTime, LocalDate endTime,String account) {
        List<Order> orders = memberOrderDataService.searchMemberOrders(new MemberSearchEntity(account,startTime,endTime,0,Integer.MAX_VALUE));
        //餐厅偏好
        HashMap<String, Integer > merchantsFavor = new HashMap<>();
        //消费区间
        HashMap<Integer,Integer > consumptionIntervals = new HashMap<>();
        //点餐地点
        HashMap<Integer,Integer>  consumptionDistance = new HashMap<>();
        //点餐时间
        HashMap<LocalTime,Integer> consumptionTimeIntervals = new HashMap<>();

        //菜品偏好
        HashMap<String,Integer> dishesFavor = new HashMap<>();


        for(Order order:orders){
            //餐厅偏好
            String idCode = order.getIdCode();
            if(merchantsFavor.containsKey(idCode)){
                merchantsFavor.put(idCode,merchantsFavor.get(idCode)+1);
            }
            else
                merchantsFavor.put(idCode,1);

            //消费区间
            double totalPrice = order.getTotalPrice();
            int adjustedTotalPrice  = (int)(totalPrice/10)*10;
            if(consumptionIntervals.containsKey(adjustedTotalPrice)){
                consumptionIntervals.put(adjustedTotalPrice,consumptionIntervals.get(adjustedTotalPrice)+1);
            }
            else
                consumptionIntervals.put(adjustedTotalPrice,1);

            //点餐时间
            LocalDateTime dateTime = order.getDeliveryTime();
            LocalTime localTime = dateTime.toLocalTime().withMinute(0);
            if(consumptionTimeIntervals.containsKey(localTime)){
                consumptionTimeIntervals.put(localTime,consumptionTimeIntervals.get(localTime)+1);
            }
            else
                consumptionTimeIntervals.put(localTime,1);

            //菜品偏好
            List<DishForMember> dishes = order.getDishes();
            for(DishForMember dish:dishes){
                String name  = dish.getName();
                if(dishesFavor.containsKey(name)){
                    dishesFavor.put(name,dishesFavor.get(name)+1);
                }
                else
                    dishesFavor.put(name,1);
            }

            //点餐地点
            Location memberLocation = order.getUserLocation();
            Location merchantLocation = Util.getLocation(order.getIdCode());
            double distance = Util.getDistance(memberLocation,merchantLocation);

            for(int i=0;i<distanceInterval.length;i++){
                consumptionDistance.put(distanceInterval[i],0);
            }
            for(int i=0;i<distanceInterval.length-1;i++){
                if(distanceInterval[i]<distance&&distanceInterval[i+1]>distance){
                    consumptionDistance.put(distanceInterval[i],consumptionDistance.get(distanceInterval[i]));
                }
            }



        }

        return new ConsumptionCharacteristics(merchantsFavor,dishesFavor,consumptionIntervals,consumptionDistance,consumptionTimeIntervals);

    }



}
