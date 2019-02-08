package cn.tycoding.dao.bankAccountDao;

import cn.tycoding.dao.mysql.MySQLConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BankAccountDataServiceImpl implements  BankAccountDataService {
    private Connection conn;
    @Override
    public void transferAccountIn(String account,double amount) {
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Bank");

        try{
            sql = "update bankAccount set balance=balance+? where account=?";

            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1,amount);
            stmt.setString(2,account);
            stmt.executeUpdate();
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }



    }


    @Override
    public boolean transferAccountOut(String account, String password, double amount) {
        if(!this.isBalanceEnough(account,amount)) {
            return false;
        }

        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Bank");

        try{
            sql = "update bankAccount set balance=balance-? where account=? and password=?";

            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1,amount);
            stmt.setString(2,account);
            stmt.setString(3,password);
            stmt.executeUpdate();

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }



        return true;
    }

    public boolean isBalanceEnough(String account,double amount){
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Bank");

        double balance = 0;
        try{
            sql = "select balance from bankAccount where account=?";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,account);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                balance = rs.getDouble("balance");
            }
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }


        return balance>amount;
    }

}
