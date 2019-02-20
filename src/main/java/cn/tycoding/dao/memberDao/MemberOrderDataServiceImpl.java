package cn.tycoding.dao.memberDao;

import cn.tycoding.dao.merchantDao.MerchantOrdersDataServiceImpl;
import cn.tycoding.dao.mysql.MySQLConnector;
import cn.tycoding.entity.MemberSearchEntity;
import cn.tycoding.entity.member.DishForMember;
import cn.tycoding.entity.merchant.Dish;
import cn.tycoding.entity.merchant.Location;
import cn.tycoding.entity.order.Order;
import cn.tycoding.entity.order.OrderState;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
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
                    "totalPrice,isPayed,isReceived,isAbolished)values(?,?,?,?,?,?,?)";

            stmt = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1,order.getAccount());
            stmt.setString(2,order.getIdCode());
            stmt.setLong(3,order.getUserLocation().getLocationId());
            stmt.setDouble(4,order.getTotalPrice());
            stmt.setBoolean(5,order.getOrderState().isPayed());
            stmt.setBoolean(6,order.getOrderState().isReceived());
            stmt.setBoolean(7,order.getOrderState().isAbolished());

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
//插入数据和减库存
    private void submitDishesInorder(Order order){
        long orderId = order.getOrderId();
        for(DishForMember dishForMember:order.getDishes()) {
            this.insertDishForMemberInorder(dishForMember, orderId);
            this.reduceStock(dishForMember);
        }

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
//    减库存
    private void reduceStock(DishForMember dishForMember){
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "update dish set quantity=quantity-? where dishId=?";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1,dishForMember.getSelectQuantity());
            stmt.setLong(2,dishForMember.getDishId());

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
            sql ="select order_tb.orderId,order_tb.account,order_tb.idCode,order_tb.submitTime,order_tb.expectedArriveTime,order_tb.orderAcceptedTime,\n" +
                    "order_tb.totalPrice,order_tb.isPayed,order_tb.isReceived,order_tb.isAbolished,location.address,location.lat,location.lng,location.locationId from order_tb,location where order_tb.userLocation = location.locationId and order_tb.account =? order by order_tb.submitTime desc";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,account);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Order order = this.getOrderWithoutDish(rs);
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
//    这个方法可以分离出来
    private Order getOrderWithoutDish(ResultSet rs) throws SQLException {
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

        order.setTotalPrice(rs.getDouble("totalPrice"));

        OrderState orderState = new OrderState();
        orderState.setAbolished(rs.getBoolean("isAbolished"));
        orderState.setPayed(rs.getBoolean("isPayed"));
        orderState.setReceived(rs.getBoolean("isReceived"));

        if(orderState.isPayed()) {
            order.setSubmitTime(rs.getTimestamp("submitTime").toLocalDateTime());
            order.setExpectedArriveTime(rs.getTimestamp("expectedArriveTime").toLocalDateTime());
        }

        if(orderState.isReceived()){
            order.setOrderAcceptedTime(rs.getTimestamp("orderAcceptedTime").toLocalDateTime());
        }

        order.setOrderState(orderState);
        return order;
    }

    @Override
    public List searchMemberOrders(MemberSearchEntity memberSearchEntity) {

        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");
        List<Order> orders = new ArrayList<>();
        try{
            if(memberSearchEntity.getRestaurantName().equals("")) {
                sql = "select order_tb.orderId,order_tb.account,order_tb.idCode,order_tb.submitTime,order_tb.expectedArriveTime,order_tb.orderAcceptedTime,\n" +
                        "order_tb.totalPrice,order_tb.isPayed,order_tb.isReceived,order_tb.isAbolished,location.address,location.lat,location.lng,location.locationId from order_tb,location where order_tb.userLocation = location.locationId and order_tb.account =?" +
                        "and order_tb.orderAcceptedTime between ? and ? and order_tb.totalPrice between ? and ?";

                stmt = conn.prepareStatement(sql);

                stmt.setString(1,memberSearchEntity.getAccount());
                stmt.setObject(2,memberSearchEntity.getStartTime());
                stmt.setObject(3,memberSearchEntity.getEndTime());
                stmt.setDouble(4,memberSearchEntity.getLowPrice());
                stmt.setDouble(5,memberSearchEntity.getHighPrice());

            }
            else {
                sql = "select order_tb.orderId,order_tb.account,order_tb.idCode,order_tb.submitTime,order_tb.expectedArriveTime,order_tb.orderAcceptedTime,\n" +
                        "order_tb.totalPrice,order_tb.isPayed,order_tb.isReceived,order_tb.isAbolished,location.address,location.lat,location.lng,location.locationId,merchantInfo.restaurantName from order_tb,location,merchantInfo where order_tb.userLocation = location.locationId and order_tb.idCode=merchantInfo.idCode and order_tb.account =?" +
                        "and order_tb.orderAcceptedTime between ? and ? and order_tb.totalPrice between ? and ? and merchantInfo.restaurantName=?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1,memberSearchEntity.getAccount());
                stmt.setObject(2,memberSearchEntity.getStartTime());
                stmt.setObject(3,memberSearchEntity.getEndTime());
                stmt.setDouble(4,memberSearchEntity.getLowPrice());
                stmt.setDouble(5,memberSearchEntity.getHighPrice());
                stmt.setString(6,memberSearchEntity.getRestaurantName());
            }


            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Order order = this.getOrderWithoutDish(rs);
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

        System.out.println(orders.size());
        return orders;
    }

    @Override
    public Order getOrder(long orderId) {
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");
        Order order = new Order();
        try{
            sql ="select order_tb.orderId,order_tb.account,order_tb.idCode,order_tb.submitTime,order_tb.expectedArriveTime,order_tb.orderAcceptedTime,\n" +
                    "order_tb.totalPrice,order_tb.isPayed,order_tb.isReceived,order_tb.isAbolished,location.address,location.lat,location.lng,location.locationId from order_tb,location where order_tb.userLocation = location.locationId and order_tb.orderId =?";

            stmt = conn.prepareStatement(sql);

            stmt.setLong(1,orderId);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                order = this.getOrderWithoutDish(rs);
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        //填充DISH IN ORDER
        merchantOrdersService.getDishesInOrder(order);
        return order;
    }

    @Override
    public double getOrderPrice(long orderId) {
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        double totalPrice = 0;
        try{
            sql = "select totalPrice from order_tb where orderId=?";

            stmt = conn.prepareStatement(sql);
            stmt.setLong(1,orderId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                totalPrice = rs.getDouble("totalPrice");
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return totalPrice;

    }

    @Override
    public boolean confirmOrder(long orderId) {
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "update order_tb set  isReceived=?,orderAcceptedTime=? where orderId=?";
            stmt = conn.prepareStatement(sql);
            stmt.setBoolean(1,true);
            stmt.setObject(2, LocalDateTime.now());
            stmt.setLong(3,orderId);

            stmt.executeUpdate();
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean abolishOrder(long orderId) {
        this.changeOrderState(orderId,"isAbolished");
        return true;
    }


    @Override
    public void turnOrderStateIsPayed(Order order) {
        this.changeOrderState(order.getOrderId(),"isPayed");

        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "update order_tb set submitTime=?,expectedArriveTime=? where orderId=?";

            stmt = conn.prepareStatement(sql);
            stmt.setObject(1,order.getSubmitTime());
            stmt.setObject(2,order.getExpectedArriveTime());
            stmt.setLong(3,order.getOrderId());
            stmt.executeUpdate();
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public boolean isPayed(long orderId) {
        PreparedStatement stmt;
        String sql;
        boolean isPayed = false;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "select isPayed from order_tb where orderId=? ";
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1,orderId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                isPayed = rs.getBoolean("isPayed");
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return isPayed;
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

//取消订单
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

        this.restoreStock(orderId);
        this.cancelDishesInOrder(orderId);
        return true;
    }

//恢复库存
    private void restoreStock(long orderId){
            Order order = new Order();
            order.setOrderId(orderId);
            merchantOrdersService.getDishesInOrder(order);
            for(DishForMember dishForMember:order.getDishes()){
                this.rollBack(dishForMember);
            }

    }

    private void rollBack(DishForMember dishForMember){
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "update dish set quantity=quantity+? where dishId=?";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,dishForMember.getSelectQuantity());
            stmt.setLong(2,dishForMember.getDishId());
            stmt.executeUpdate();
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

//删除记录
    private void cancelDishesInOrder(long orderId){
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "delete from  dishInOrder where orderId=?";
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1,orderId);

            stmt.executeUpdate();
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }



}
