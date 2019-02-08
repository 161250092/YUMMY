package cn.tycoding.dao.managerDao;

import cn.tycoding.dao.mysql.MySQLConnector;
import cn.tycoding.entity.manager.ApplicationFromMerchant;
import cn.tycoding.entity.merchant.MerchantInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ManagerApplicationDataServiceImpl implements ManagerApplicationDataService{
    private Connection conn;

    //all the application have no oldInformation
    @Override
    public List<ApplicationFromMerchant> getAllNotReadApplications() {

        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        List<ApplicationFromMerchant> applicationFromMerchants = new ArrayList<>();
        try{
            sql = "select * from application where isRead=?";

            stmt = conn.prepareStatement(sql);
            stmt.setBoolean(1,false);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                //申请的信息
                MerchantInfo merchantInfo = new MerchantInfo();
                merchantInfo.setIdCode(rs.getString("idCode"));
                merchantInfo.setBankAccount(rs.getString("bankAccount"));
                merchantInfo.setRestaurantName(rs.getString("restaurantName"));
                merchantInfo.setRestaurantType(rs.getString("restaurantType"));
                merchantInfo.setPhone(rs.getString("phone"));
                merchantInfo.setMinDeliveryCost(rs.getDouble("minDeliveryCost"));
                merchantInfo.setDeliveryCost(rs.getDouble("deliveryCost"));


                ApplicationFromMerchant applicationFromMerchant = new ApplicationFromMerchant();

                applicationFromMerchant.setApplicationId(rs.getLong("applicationId"));
                applicationFromMerchant.setNewMerchantInfo(merchantInfo);
                applicationFromMerchant.setRead(rs.getBoolean("isRead"));
                applicationFromMerchant.setApproved(rs.getBoolean("isApproved"));

                applicationFromMerchants.add(applicationFromMerchant);

            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return applicationFromMerchants;
    }

    @Override
    public boolean passApplication(long applicationId) {
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "update application set isRead=?,isApproved=? where applicationId=?";

            stmt = conn.prepareStatement(sql);
            stmt.setBoolean(1,true);
            stmt.setBoolean(2,true);
            stmt.setLong(3,applicationId);
            stmt.executeUpdate();

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean refuseApplication(long applicationId) {

        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "update application set isRead=?,isApproved=? where applicationId=?";

            stmt = conn.prepareStatement(sql);
            stmt.setBoolean(1,true);
            stmt.setBoolean(2,false);
            stmt.setLong(3,applicationId);
            stmt.executeUpdate();

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }



        return true;
    }
}
