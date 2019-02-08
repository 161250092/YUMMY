package cn.tycoding.dao.merchantDao;

import cn.tycoding.dao.mysql.MySQLConnector;
import cn.tycoding.entity.merchant.MerchantInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MerchantInformationDataServiceImpl implements MerchantInformationDataService {
    private Connection conn;

    @Override
    public List<MerchantInfo> getAllMerchant() {
        conn = new MySQLConnector().getConnection("Yummy");
        PreparedStatement stmt;
        String sql;

        List<MerchantInfo>  merchants = new ArrayList<>();
        try{
            sql = "select * from merchantInfo";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                MerchantInfo merchantInfo  = new MerchantInfo();
                merchantInfo.setIdCode(rs.getString("idCode"));
                merchantInfo.setBankAccount(rs.getString("bankAccount"));
                merchantInfo.setRestaurantName(rs.getString("restaurantName"));
                merchantInfo.setRestaurantType(rs.getString("restaurantType"));
                merchantInfo.setPhone(rs.getString("phone"));
                merchantInfo.setMinDeliveryCost(rs.getDouble("minDeliveryCost"));
                merchantInfo.setDeliveryCost(rs.getDouble("deliveryCost"));
                merchants.add(merchantInfo);
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return merchants;
    }

    @Override
    public MerchantInfo getMerchantInfo(String idCode) {

        conn = new MySQLConnector().getConnection("Yummy");
        PreparedStatement stmt;
        String sql;

        MerchantInfo merchantInfo = new MerchantInfo();
        try{
            sql = "select * from merchantInfo where idCode=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,idCode);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                merchantInfo.setIdCode(idCode);
                merchantInfo.setBankAccount(rs.getString("bankAccount"));
                merchantInfo.setRestaurantName(rs.getString("restaurantName"));
                merchantInfo.setRestaurantType(rs.getString("restaurantType"));
                merchantInfo.setPhone(rs.getString("phone"));
                merchantInfo.setMinDeliveryCost(rs.getDouble("minDeliveryCost"));
                merchantInfo.setDeliveryCost(rs.getDouble("deliveryCost"));
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return merchantInfo;
    }

    @Override
    public boolean updateMerchantInfo(MerchantInfo merchantInfo) {
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "insert into application(idCode,bankAccount,restaurantName,restaurantType,phone,minDeliveryCost,deliveryCost,isRead,isApproved)VALUES(?,?,?,?,?,?,?,?,?)";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,merchantInfo.getIdCode());
            stmt.setString(2,merchantInfo.getBankAccount());
            stmt.setString(3,merchantInfo.getRestaurantName());
            stmt.setString(4,merchantInfo.getRestaurantType());
            stmt.setString(5,merchantInfo.getPhone());
            stmt.setDouble(6,merchantInfo.getMinDeliveryCost());
            stmt.setDouble(7,merchantInfo.getDeliveryCost());
            stmt.setBoolean(8,false);
            stmt.setBoolean(9,false);
            stmt.executeUpdate();

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean addNewDiscount(String idCode,double totalPrice, double reducePrice) {

        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "insert into discount(idCode,totalPrice,reducePrice)VALUES(?,?,?)";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,idCode);
            stmt.setDouble(2,totalPrice);
            stmt.setDouble(3,reducePrice);
            stmt.executeUpdate();

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }



        return true;
    }

    @Override
    public boolean deleteDiscount(long discountId) {

        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "delete from discount where discountId=?";

            stmt = conn.prepareStatement(sql);
            stmt.setLong(1,discountId);

            stmt.executeUpdate();

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }



        return true;
    }
}
