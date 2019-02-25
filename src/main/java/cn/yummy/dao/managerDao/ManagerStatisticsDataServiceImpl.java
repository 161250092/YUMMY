package cn.yummy.dao.managerDao;

import cn.yummy.dao.mysql.MySQLConnector;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerStatisticsDataServiceImpl implements  ManagerStatisticsDataService{

    private Connection conn;

    @Override
    public int getRestaurantNum() {

        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        int num = 0;
        try{
            sql = "select count(*) as num from merchant";

            stmt = conn.prepareStatement(sql);

            ResultSet rs  = stmt.executeQuery();
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
    public int getMemberNum() {
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");
        int num = 0;
        try{
            sql = "select count(*) as num from member";

            stmt = conn.prepareStatement(sql);
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
    public ArrayList<Integer> getEachLevelMemberNum() {

        ArrayList<Integer>  eachLevelMemberNum = new ArrayList<>();
        for(int i=1;i<=8;i++){
            eachLevelMemberNum.add(this.getMemberNumInDifferentLevel(i));
        }
        return eachLevelMemberNum;
    }

    @Override
    public double getMonthlyActualIncome() {
        double actualIncome = this.getMonthlyIncome()-this.getMonthlyExpense();
        actualIncome = (double) Math.round(actualIncome * 100) / 100;
        return actualIncome;
    }


    private int getMemberNumInDifferentLevel(int level){
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");
        int num = 0;
        try{
            sql = "select count(*) as num from member where memberLevel=?";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,level);
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
    public double getMonthlyIncome() {

        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");
        double monthlyIncome = 0;
        try{
            sql = "select sum(bonus) as monthlyIncome from paymentRecord where recordType=? and recordTime between ? and ?";

            stmt = conn.prepareStatement(sql);
            LocalDate today = LocalDate.now();
            LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
            LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());

            stmt.setString(1,"in");
            stmt.setObject(2,firstDayOfThisMonth);
            stmt.setObject(3,lastDayOfThisMonth);
            ResultSet rs =  stmt.executeQuery();
            while(rs.next()){
                monthlyIncome = rs.getDouble("monthlyIncome");
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return monthlyIncome;
    }

    @Override
    public double getMonthlyExpense() {

        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");
        double monthlyExpense= 0;
        try{
            sql = "select sum(bonus) as monthlyExpense from paymentRecord where recordType=? and recordTime between ? and ?";

            stmt = conn.prepareStatement(sql);
            LocalDate today = LocalDate.now();
            LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
            LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());

            stmt.setString(1,"out");
            stmt.setObject(2,firstDayOfThisMonth);
            stmt.setObject(3,lastDayOfThisMonth);
            ResultSet rs =  stmt.executeQuery();
            while(rs.next()){
                monthlyExpense = rs.getDouble("monthlyExpense");
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return monthlyExpense;
    }

    @Override
    public double getBalance() {


        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Bank");
        double balance = 0;
        try{
            sql = "select balance from bankAccount where account=?";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1,"yummy");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                balance = rs.getDouble("balance");
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return balance;
    }
}
