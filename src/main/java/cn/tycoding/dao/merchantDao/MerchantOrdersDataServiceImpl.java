package cn.tycoding.dao.merchantDao;

import cn.tycoding.dao.mysql.MySQLConnector;
import cn.tycoding.entity.SearchEntity;
import cn.tycoding.entity.member.DishForMember;
import cn.tycoding.entity.merchant.Location;
import cn.tycoding.entity.order.Order;
import cn.tycoding.entity.order.OrderState;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MerchantOrdersDataServiceImpl implements MerchantOrdersDataService {

    private Connection conn;

    @Override
    public List<Order> getMerchantAllOrders(String idCode) {
        List<Order> orders  = this.getMerchantAllOrdersWithoutDishes(idCode);

        for(Order order:orders){
            this.getDishesInOrder(order);
        }

        return orders;
    }

    @Override
    public List<Order> checkMerchantOrders(SearchEntity searchEntity) {
        List<Order> ordersWithoutDishes = new ArrayList<>();
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "select order_tb.orderId,order_tb.account,order_tb.idCode,order_tb.expectedArriveTime,order_tb.orderAcceptedTime,\n" +
                    "order_tb.totalPrice,order_tb.isPayed,order_tb.isReceived,order_tb.isAbolished,location.lat,location.lng,location.locationId from order_tb,location,member where order_tb.userLocation = location.locationId  and order_tb.account =member.account " +
                    "and order_tb.idCode =? and order_tb.orderAcceptedTime between ? and ? and order_tb.totalPrice between ? and ? and member.memberLevel between ? and ?";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,searchEntity.getIdCode());
            stmt.setDate(2,java.sql.Date.valueOf(searchEntity.getStartTime()));
            stmt.setDate(3,java.sql.Date.valueOf(searchEntity.getEndTime()));
            stmt.setDouble(4,searchEntity.getLowPrice());
            stmt.setDouble(5,searchEntity.getHighPrice());
            stmt.setInt(6,searchEntity.getLowLevel());
            stmt.setInt(7,searchEntity.getHighLevel());

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

                ordersWithoutDishes.add(order);

            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }




        return null;
    }


    /**
     *  获取商家订单信息(无订单信息)
     * @param idCode
     * @return
     */
    public  List<Order> getMerchantAllOrdersWithoutDishes(String idCode){

        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        List<Order> orders = new ArrayList<>();
        try{
            sql ="select order_tb.orderId,order_tb.account,order_tb.idCode,order_tb.expectedArriveTime,order_tb.orderAcceptedTime,\n" +
                    "order_tb.totalPrice,order_tb.isPayed,order_tb.isReceived,order_tb.isAbolished,location.lat,location.lng,location.locationId from order_tb,location where order_tb.userLocation = location.locationId and order_tb.idCode =? ";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1,idCode);
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


        for(Order order:orders){
            this.getDishesInOrder(order);
        }

        return orders;
    }

    public boolean getDishesInOrder(Order order){
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "select dishInOrder.selectQuantity,dishInOrder.dishId,dish.idCode,dish.startTime,dish.endTime,dish.dishType,dish.dishName,dish.price,quantity,dish.description from dishInOrder,dish where dishInOrder.dishId =dish.dishId and dishInOrder.orderId=?";

            stmt = conn.prepareStatement(sql);
            stmt.setLong(1,order.getOrderId());
            ResultSet rs = stmt.executeQuery();

            List<DishForMember> dishForMembers = new ArrayList<>();
            while(rs.next()){
                DishForMember dishForMember = new DishForMember();
                dishForMember.setSelectQuantity(rs.getInt("selectQuantity"));
                dishForMember.setDishId(rs.getLong("dishId"));
                dishForMember.setIdCode(rs.getString("idCode"));
                dishForMember.setStartTime(rs.getDate("startTime").toLocalDate());
                dishForMember.setEndTime(rs.getDate("endTime").toLocalDate());
                dishForMember.setType(rs.getString("dishType"));
                dishForMember.setName(rs.getString("dishName"));
                dishForMember.setPrice(rs.getDouble("price"));
                dishForMember.setQuantity(rs.getInt("quantity"));
                dishForMember.setDescription(rs.getString("description"));
                dishForMembers.add(dishForMember);
            }
            order.setDishes(dishForMembers);
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }



}
