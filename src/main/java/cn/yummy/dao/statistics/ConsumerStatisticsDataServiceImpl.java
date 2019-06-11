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
            Location merchantLocation = getLocation(order.getIdCode());
            double distance = getDistance(memberLocation,merchantLocation);

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


    private Location getLocation(long locationId){
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");
        Location location = new Location();
        try{
            sql = "select * from location where locationId=?";
            stmt = conn.prepareStatement(sql);

            stmt.setLong(1,locationId);
            ResultSet rs = stmt.executeQuery();


            while(rs.next()){
              //  location.setAccount();
                location.setAddress(rs.getString("account"));
                location.setLat(rs.getDouble("lat"));
                location.setLng(rs.getDouble("lng"));

            }
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return location;
    }


    private Location getLocation(String account){
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");
        Location location = new Location();
        try{
            sql = "select * from location where account=?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1,account);
            ResultSet rs = stmt.executeQuery();


            while(rs.next()){
                //  location.setAccount();
                location.setAddress(rs.getString("account"));
                location.setLat(rs.getDouble("lat"));
                location.setLng(rs.getDouble("lng"));

            }
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return location;
    }


    private double getDistance(Location source,Location destination){
        double Lat1 = rad(source.getLat()); // 纬度

        double Lat2 = rad(destination.getLat());

        double a = Lat1 - Lat2;//两点纬度之差

        double b = rad(source.getLng()) - rad(destination.getLng()); //经度之差

        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(Lat1) * Math.cos(Lat2) * Math.pow(Math.sin(b / 2), 2)));//计算两点距离的公式

        s = s * 6378137.0;//弧长乘地球半径（半径为米）

        s = Math.round(s * 10000d) / 10000d;//精确距离的数值
        s = s/1000;//将单位转换为km，如果想得到以米为单位的数据 就不用除以1000

        return s;
    }

    private double rad(double d){
        return d*Math.PI/180;
    }

}
