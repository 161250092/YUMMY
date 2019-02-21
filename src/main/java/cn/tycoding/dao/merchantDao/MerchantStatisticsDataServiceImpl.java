package cn.tycoding.dao.merchantDao;

import cn.tycoding.dao.mysql.MySQLConnector;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Service
public class MerchantStatisticsDataServiceImpl implements  MerchantStatisticsDataService{

    private Connection conn;
    @Override
    public double getMerchantMonthlyIncome(String account) {
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        double monthlyIncome  = 0;
        try{
            sql = "select  sum(totalPrice) as monthlyIncome from order_tb where idCode=? and isReceived=? and orderAcceptedTime BETWEEN ? and ?";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,account);
            stmt.setBoolean(2,true);
            LocalDate today = LocalDate.now();
            LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
            LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
            stmt.setObject(3,firstDayOfThisMonth);
            stmt.setObject(4,lastDayOfThisMonth);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                monthlyIncome = rs.getDouble("monthlyIncome");
            }
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return (double)Math.round(monthlyIncome*100)/100;
    }

    @Override
    public int getReceivedOrdersNum(String account) {
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");
        int num = 0;
        try{
            sql = "select count(*) as num from order_tb where idCode=? and isReceived=? and orderAcceptedTime BETWEEN ? and ?";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1,account);
            stmt.setBoolean(2,true);
            LocalDate today = LocalDate.now();
            LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
            LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
            stmt.setObject(3,firstDayOfThisMonth);
            stmt.setObject(4,lastDayOfThisMonth);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                num = rs.getInt("num");
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return  num;
    }

    @Override
    public int getAbolishedOrdersNum(String account) {
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");
        int num = 0;
        try{
            sql = "select count(*) as num from order_tb where idCode=? and isAbolished=? and orderAcceptedTime BETWEEN ? and ?";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1,account);
            stmt.setBoolean(2,true);
            LocalDate today = LocalDate.now();
            LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
            LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
            stmt.setObject(3,firstDayOfThisMonth);
            stmt.setObject(4,lastDayOfThisMonth);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                num = rs.getInt("num");
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return  num;
    }
}
