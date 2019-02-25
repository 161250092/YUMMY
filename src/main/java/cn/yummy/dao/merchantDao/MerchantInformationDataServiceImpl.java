package cn.yummy.dao.merchantDao;

import cn.yummy.dao.mysql.MySQLConnector;
import cn.yummy.entity.merchant.Discount;
import cn.yummy.entity.primitiveType.Location;
import cn.yummy.entity.merchant.MerchantInfo;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
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
        for(MerchantInfo merchantInfo:merchants){
            merchantInfo.setLocation(this.getMerchantLocation(merchantInfo.getIdCode()));
        }

        return merchants;
    }

    @Override
    public List<MerchantInfo> searchMerchants(String restaurantName, String restaurantType) {
        conn = new MySQLConnector().getConnection("Yummy");
        PreparedStatement stmt;
        String sql;

        List<MerchantInfo>  merchants = new ArrayList<>();
        try{
            sql = "select * from merchantInfo where restaurantName=? or restaurantType=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,restaurantName);
            stmt.setString(2,restaurantType);
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
        for(MerchantInfo merchantInfo:merchants){
            String idCode = merchantInfo.getIdCode();
            merchantInfo.setLocation(this.getMerchantLocation(idCode));
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

        merchantInfo.setLocation(this.getMerchantLocation(idCode));
        merchantInfo.setDiscounts(this.getMerchantDiscounts(idCode));
        merchantInfo.infoToString();
        return merchantInfo;
    }


    private Location getMerchantLocation(String idCode){

        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");
        Location location = new Location();
        try{
            sql = "select * from location where account=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,idCode);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                location.setLocationId(rs.getLong("locationId"));
                location.setLat(rs.getDouble("lat"));
                location.setLng(rs.getDouble("lng"));
                location.setAddress(rs.getString("address"));
                location.setAccount(rs.getString("account"));
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return location;

    }

    @Override
    public List<Discount> getMerchantDiscounts(String idCode){
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");
        List<Discount> discounts = new ArrayList<>();
        try{
            sql = "select * from discount where idCode=?";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1,idCode);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Discount discount = new Discount();
                discount.setDiscountId(rs.getLong("discountId"));
                discount.setIdCode(rs.getString("idCode"));
                discount.setTotalPrice(rs.getDouble("totalPrice"));
                discount.setReducePrice(rs.getDouble("reducePrice"));
                discounts.add(discount);
            }
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }


        return discounts;
    }


    @Override
    public boolean updateMerchantInfo(MerchantInfo merchantInfo) {
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "insert into application(idCode,bankAccount,restaurantName,restaurantType,phone,minDeliveryCost,deliveryCost,isRead,isApproved,address,lat,lng)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

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
            stmt.setString(10,merchantInfo.getLocation().getAddress());
            stmt.setDouble(11,merchantInfo.getLocation().getLat());
            stmt.setDouble(12,merchantInfo.getLocation().getLng());

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
