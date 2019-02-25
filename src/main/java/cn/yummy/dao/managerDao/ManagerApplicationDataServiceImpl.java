package cn.yummy.dao.managerDao;

import cn.yummy.dao.merchantDao.MerchantInformationDataServiceImpl;
import cn.yummy.dao.mysql.MySQLConnector;
import cn.yummy.entity.manager.ApplicationFromMerchant;
import cn.yummy.entity.primitiveType.Location;
import cn.yummy.entity.merchant.MerchantInfo;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Service
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

                Location location = new Location();
                location.setAccount(merchantInfo.getIdCode());
                location.setAddress(rs.getString("address"));
                location.setLat(rs.getDouble("lat"));
                location.setLng(rs.getDouble("lng"));

                merchantInfo.setLocation(location);


                merchantInfo.infoToString();


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

        for(ApplicationFromMerchant applicationFromMerchant:applicationFromMerchants){
            String idCode = applicationFromMerchant.getNewMerchantInfo().getIdCode();
            MerchantInfo oldMerchant = new MerchantInformationDataServiceImpl().getMerchantInfo(idCode);
            applicationFromMerchant.setOldMerchantInfo(oldMerchant);
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

        MerchantInfo merchantInfo = this.getNewMerchantInfo(applicationId);
        updateMerchantInfo(merchantInfo);


        return true;
    }

    private MerchantInfo getNewMerchantInfo(long applicationId){

        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");
        MerchantInfo merchantInfo = new MerchantInfo();
        Location location = new Location();
        try{
            sql = "select idCode,bankAccount,restaurantName,restaurantType,phone,address,lat,lng,minDeliveryCost,deliveryCost from application where applicationId=?";
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1,applicationId);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                merchantInfo.setIdCode(rs.getString("idCode"));
                merchantInfo.setBankAccount(rs.getString("bankAccount"));
                merchantInfo.setRestaurantName(rs.getString("restaurantName"));
                merchantInfo.setRestaurantType(rs.getString("restaurantType"));
                merchantInfo.setPhone(rs.getString("phone"));
                merchantInfo.setMinDeliveryCost(rs.getDouble("minDeliveryCost"));
                merchantInfo.setDeliveryCost(rs.getDouble("deliveryCost"));

                location.setAccount(merchantInfo.getIdCode());
                location.setAddress(rs.getString("address"));
                location.setLat(rs.getDouble("lat"));
                location.setLng(rs.getDouble("lng"));

                merchantInfo.setLocation(location);
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return merchantInfo;
    }

    private void updateMerchantInfo(MerchantInfo merchantInfo){

            long locationId = this.getLocationId(merchantInfo.getIdCode());
//            更新地址
            updateLocation(locationId,merchantInfo.getLocation());

        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "update MerchantInfo set bankAccount=?,restaurantName=?,restaurantType=?,phone=?,minDeliveryCost=?,deliveryCost=? where idCode=?";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,merchantInfo.getBankAccount());
            stmt.setString(2,merchantInfo.getRestaurantName());
            stmt.setString(3,merchantInfo.getRestaurantType());
            stmt.setString(4,merchantInfo.getPhone());
            stmt.setDouble(5,merchantInfo.getMinDeliveryCost());
            stmt.setDouble(6,merchantInfo.getDeliveryCost());
            stmt.setString(7,merchantInfo.getIdCode());
            stmt.executeUpdate();
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private void updateLocation(long locationId,Location location){
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "update location set lat=?,lng=?,address=? where locationId=?";

            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1,location.getLat());
            stmt.setDouble(2,location.getLng());
            stmt.setString(3,location.getAddress());
            stmt.setLong(4,locationId);
            stmt.executeUpdate();
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private long getLocationId(String account){
        long locationId = 0;
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "select locationId from location where account=?";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1,account);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                locationId = rs.getLong("locationId");
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
            return locationId;
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
