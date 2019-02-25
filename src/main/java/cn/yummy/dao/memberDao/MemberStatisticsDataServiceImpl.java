package cn.yummy.dao.memberDao;

import cn.yummy.dao.mysql.MySQLConnector;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MemberStatisticsDataServiceImpl implements MemberStatisticsDataService {

    private Connection conn;
    @Override
    public double getMemberConsumption(String account) {

        double total = 0;
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "select sum(totalPrice)  as total from order_tb where account=?  and isReceived=? group by account";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1,account);
            stmt.setBoolean(2,true);


            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                total = rs.getDouble("total");
            }
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return total;
    }

    @Override
    public int getAbolishedOrdersNum(String account) {

        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");
        int num = 0;
        try{
            sql = "select count(*) as num from order_tb where account=? and isAbolished=?";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1,account);
            stmt.setBoolean(2,true);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                num = rs.getInt("num");
            }
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return num;
    }

    @Override
    public int getAcceptedOrdersNum(String account) {

        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");
        int num = 0;
        try{
            sql = "select count(*) as num from order_tb where account=? and isReceived=?";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1,account);
            stmt.setBoolean(2,true);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                num = rs.getInt("num");
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return num;
    }

    @Override
    public HashMap<String, Double> getConsumptionInformation(String account) {

        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");
        HashMap<String,Double> consumptionInformation = new HashMap<>();
        try{
            sql = "select DISTINCT merchantInfo.restaurantName,sum(order_tb.totalPrice) as totalConsumption from order_tb,merchantInfo where order_tb.idCode = merchantInfo.idCode and   order_tb.isReceived=? and order_tb.account=? group by merchantInfo.restaurantName;";

            stmt = conn.prepareStatement(sql);
            stmt.setBoolean(1,true);
            stmt.setString(2,account);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
               consumptionInformation.put(rs.getString("restaurantName"),rs.getDouble("totalConsumption"));
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return consumptionInformation;
    }


}
