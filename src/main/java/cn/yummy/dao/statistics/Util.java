package cn.yummy.dao.statistics;

import cn.yummy.dao.mysql.MySQLConnector;
import cn.yummy.entity.primitiveType.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;

public class Util {

    private static Connection conn = new MySQLConnector().getConnection("Yummy");

    public static Location getLocation(long locationId){
        PreparedStatement stmt;
        String sql;
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


    public   static Location getLocation(String account){
        PreparedStatement stmt;
        String sql;
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


    public static double getDistance(Location source,Location destination){
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

    public static double rad(double d){
        return d*Math.PI/180;
    }


    public static double getConsumersTotalConsumptionInStore(String account,String idCode){
        PreparedStatement stmt;
        String sql;
        double result = 0;

        try{
            sql = "select sum(totalPrice) as t ,account from order_tb where isPayed=true and idCode=? and account=?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1,idCode);
            stmt.setString(2,account);
            ResultSet rs = stmt.executeQuery();

            Location location = new Location();
            while(rs.next()){
                result = rs.getDouble("t");
            }
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;

    }

    public static int getConsumptionTimes(String account,String idCode){
        PreparedStatement stmt;
        String sql;
        int result = 0;

        try{
            sql = "select count(*) as ct from order_tb  where isPayed=true and idCode=?  and account=?;";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1,idCode);
            stmt.setString(2,account);
            ResultSet rs = stmt.executeQuery();

            Location location = new Location();
            while(rs.next()){
                result = rs.getInt("ct");
            }
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static double getSalesVolume(String idCode,LocalDate startDate,LocalDate endTime){
        PreparedStatement stmt;
        String sql;
        double  result = 0;

        try{
            sql = "select sum(totalPrice) as volume from order_tb where idCode=? and isPayed=true and orderAcceptedTime between ? and ?;";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1,idCode);
            stmt.setObject(2,startDate);
            stmt.setObject(3,endTime);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                result = rs.getInt("volume");
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static double getSalesVolume(LocalDate date){
        PreparedStatement stmt;
        String sql;
        double  result = 0;
        LocalDateTime endTime = LocalDateTime.of(date, LocalTime.of(23,59,59));

        try{
            sql = "select sum(totalPrice) as volume from order_tb where isPayed=true and orderAcceptedTime between ? and ?;";
            stmt = conn.prepareStatement(sql);

            stmt.setObject(1,date);
            stmt.setObject(2,endTime);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                result = rs.getInt("volume");
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return (int)(result*100)/100;
    }



    public static double getSalesVolume(String idCode,LocalDate date){
        PreparedStatement stmt;
        String sql;
        double  result = 0;

        LocalDateTime endTime = LocalDateTime.of(date, LocalTime.of(23,59,59));

        try{
            sql = "select sum(totalPrice) as volume from order_tb where idCode=? and isPayed=true and orderAcceptedTime BETWEEN ? and ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1,idCode);
            stmt.setObject(2,date);
            stmt.setObject(3,endTime);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                result = rs.getInt("volume");
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


    public static double getPlatformVolume(LocalDate date){
        PreparedStatement stmt;
        String sql;
        double  result = 0;

        LocalDateTime endTime = LocalDateTime.of(date, LocalTime.of(23,59,59));

        try{
            sql = "select sum(bonus) as volume from paymentRecord where recordType='in' and recordTime between ? and ? ";
            stmt = conn.prepareStatement(sql);
            stmt.setObject(1,date);
            stmt.setObject(2,endTime);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                result += rs.getInt("volume");
            }

            sql = "select sum(bonus) as volume from paymentRecord where recordType='out' and recordTime between ? and ? ";
            stmt = conn.prepareStatement(sql);
            stmt.setObject(1,date);
            stmt.setObject(2,endTime);

            rs = stmt.executeQuery();
            while(rs.next()){
                result -= rs.getInt("volume");
            }
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;


    }


    public static int getOrdersNum(String idCode,LocalDate startDate,LocalDate endTime){
        PreparedStatement stmt;
        String sql;
        int  result = 0;

        try{
            sql = "select count(*) as orderNum from order_tb where idCode=? and isPayed=true and orderAcceptedTime between ? and ?;";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1,idCode);
            stmt.setObject(2,startDate);
            stmt.setObject(3,endTime);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                result = rs.getInt("orderNum");
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


    public static void dishFavor(HashMap<String,Integer> dishesFavorInterval){

        PreparedStatement stmt;
        String sql;


        try{
            sql = "select count(a.dishId) as c,b.* from dishInOrder   as a, dish as b where a.dishId=b.dishId group by a.dishId;";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                dishesFavorInterval.put(rs.getString("dishName"),rs.getInt("c"));
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void merchantFavor(HashMap<String,Integer> merchantsFavorInterval){
        PreparedStatement stmt;
        String sql;


        try{
            sql = "select  count(b.idCode) as c,b.idCode from dishInOrder as a, dish as b where a.dishId=b.dishId group by b.idCode;";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                merchantsFavorInterval.put(rs.getString("idCode"),rs.getInt("c"));
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public static int getNumInConsumptionInterval(int start,int end){
        PreparedStatement stmt;
        String sql;
        int result = 0;

        try{
            sql = "select count(*) as c from order_tb where  totalPrice between ? and ?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,start);
            stmt.setInt(2,end);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                result  = rs.getInt("c");
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    public static void getNumInConsumptionTimesInterval( HashMap<String,Integer> consumptionTimesInterval){
        PreparedStatement stmt;
        String sql;


        try{
            sql = "select  count(*) as c,account  from order_tb group by account;";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
               consumptionTimesInterval.put(rs.getString("account"),rs.getInt("c"));
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }



    }





}
