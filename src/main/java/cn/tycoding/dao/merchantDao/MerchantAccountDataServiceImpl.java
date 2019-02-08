package cn.tycoding.dao.merchantDao;

import cn.tycoding.dao.mysql.MySQLConnector;
import cn.tycoding.entity.merchant.MerchantRegisterInf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MerchantAccountDataServiceImpl implements MerchantAccountDataService {

    private Connection conn;


    @Override
    public int getMerchantNum() {

        int merchantNum = 0;
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");
        try{
            sql = "select count(*) as num from merchant";

            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                merchantNum = rs.getInt("num");
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return merchantNum;
    }

    @Override
    public boolean createMerchantAccount(String idCode, MerchantRegisterInf merchantRegisterInf) {
        createAccount(idCode,merchantRegisterInf.getPassword());
        createMerchantInfo(idCode,merchantRegisterInf);
        return true;
    }

    @Override
    public String getPassword(String idCode) {
        String password = "";
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");
        try{
            sql = "select password from merchant where idCode=?";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,idCode);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                password = rs.getString("password");
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return password;
    }


    public void createAccount(String idCode, String password){
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");
        try{
            sql = "insert into merchant(idCode,password)VALUES(?,?) ";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1,idCode);
            stmt.setString(2,password);
            stmt.executeUpdate();

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public void createMerchantInfo(String idCode, MerchantRegisterInf merchantRegisterInf){


        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");
        try{
            sql = "insert into merchantInfo(idCode,bankAccount,restaurantName,phone,restaurantType,minDeliveryCost,deliveryCost)" +
                    "VALUES(?,?,?,?,'空',0,0) ";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,idCode);
            stmt.setString(2,merchantRegisterInf.getBankAccount());
            stmt.setString(3,merchantRegisterInf.getRestaurantName());
            stmt.setString(4,merchantRegisterInf.getPhone());

            stmt.executeUpdate();
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }



}
