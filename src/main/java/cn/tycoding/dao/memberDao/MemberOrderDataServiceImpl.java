package cn.tycoding.dao.memberDao;

import cn.tycoding.dao.merchantDao.MerchantOrdersDataServiceImpl;
import cn.tycoding.dao.mysql.MySQLConnector;
import cn.tycoding.entity.member.DishForMember;
import cn.tycoding.entity.merchant.Location;
import cn.tycoding.entity.order.Order;
import cn.tycoding.entity.order.OrderState;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MemberOrderDataServiceImpl implements MemberOrderDataService {
    private Connection conn;

    private MerchantOrdersDataServiceImpl merchantOrdersService = new MerchantOrdersDataServiceImpl();
    @Override
    public boolean submitOrder(Order order) {

        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        long orderId = 0;
        try{
            sql = "insert into order_tb(account,idCode,userLocation," +
                    "orderAcceptedTime,expectedArriveTime,totalPrice,isPayed,isReceived,isAbolished)values(?,?,?,?,?,?,?,?,?)";

            stmt = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1,order.getAccount());
            stmt.setString(2,order.getIdCode());
            stmt.setLong(3,order.getUserLocation().getLocationId());
            stmt.setObject(4,order.getOrderAcceptedTime());
            stmt.setObject(5,order.getExpectedArriveTime());
            stmt.setDouble(6,order.getTotalPrice());
            stmt.setBoolean(7,order.getOrderState().isPayed());
            stmt.setBoolean(8,order.getOrderState().isReceived());
            stmt.setBoolean(9,order.getOrderState().isAbolished());

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                orderId = rs.getInt(1);
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        order.setOrderId(orderId);
        this.submitDishesInorder(order);

        return true;
    }


    private void submitDishesInorder(Order order){
        long orderId = order.getOrderId();
        for(DishForMember dishForMember:order.getDishes())
            this.insertDishForMemberInorder(dishForMember,orderId);

    }

    private void insertDishForMemberInorder(DishForMember dishForMember,long orderId){


        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "insert into dishInOrder(orderId,dishId,selectQuantity)VALUES(?,?,?)";
            stmt = conn.prepareStatement(sql);

            stmt.setLong(1,orderId);
            stmt.setLong(2,dishForMember.getDishId());
            stmt.setInt(3,dishForMember.getSelectQuantity());
            stmt.executeUpdate();
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public List getMemberOrders(String account) {

        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");
        List<Order> orders = new ArrayList<>();
        try{
            sql ="select order_tb.orderId,order_tb.account,order_tb.idCode,order_tb.expectedArriveTime,order_tb.orderAcceptedTime,\n" +
                    "order_tb.totalPrice,order_tb.isPayed,order_tb.isReceived,order_tb.isAbolished,location.lat,location.lng,location.locationId from order_tb,location where order_tb.userLocation = location.locationId and order_tb.account =?";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,account);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Order order = new Order();
                order.setOrderId(rs.getLong("orderId"));
                order.setAccount(rs.getString("account"));
                order.setIdCode(rs.getString("idCode"));

                Location location = new Location();
                location.setLocationId(rs.getLong("locationId"));
                location.setLat(rs.getDouble("lat"));
                location.setLng(rs.getDouble("lng"));
                location.setAccount(rs.getString("account"));
                location.setAddress(rs.getString("address"));
                order.setUserLocation(location);

                order.setExpectedArriveTime(rs.getTimestamp("expectedArriveTime").toLocalDateTime());
                order.setOrderAcceptedTime(rs.getTimestamp("orderAcceptedTime").toLocalDateTime());

                order.setTotalPrice(rs.getDouble("totalPrice"));

                OrderState orderState = new OrderState();
                orderState.setAbolished(rs.getBoolean("isAbolished"));
                orderState.setPayed(rs.getBoolean("isPayed"));
                orderState.setReceived(rs.getBoolean("isReceived"));
                order.setOrderState(orderState);

                orders.add(order);

            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        //填充DISH IN ORDERS
        for(Order order:orders){
            merchantOrdersService.getDishesInOrder(order);
        }


        return orders;
    }

    @Override
    public boolean confirmOrder(long orderId) {

        this.changeOrderState(orderId,"isReceived");
        return true;
    }

    @Override
    public boolean abolishOrder(long orderId) {
        this.changeOrderState(orderId,"isAbolished");
        return true;
    }


    @Override
    public boolean payForOrder(long orderId) {
        this.changeOrderState(orderId,"isPayed");
        return true;
    }

    private void changeOrderState(long orderId,String stateItem){
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "update order_tb set "+stateItem+"=? where orderId=?";
            stmt = conn.prepareStatement(sql);
            stmt.setBoolean(1,true);
            stmt.setLong(2,orderId);

            stmt.executeUpdate();
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }



    }


    @Override
    public boolean cancelOrder(long orderId) {
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "delete from order_tb where orderId=?";
            stmt = conn.prepareStatement(sql);

            stmt.setLong(1,orderId);
            stmt.executeUpdate();
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }


        return true;
    }


}
