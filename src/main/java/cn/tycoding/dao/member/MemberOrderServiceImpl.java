package cn.tycoding.dao.member;

import cn.tycoding.entity.order.Order;

import java.util.List;

public class MemberOrderServiceImpl implements  MemberOrderService{
    @Override
    public boolean submitOrder(Order order) {


        return false;
    }


    public boolean submitDishesInorder(Order order){


        return true;
    }

    @Override
    public List getMemberOrders(String account) {
        return null;
    }

    @Override
    public boolean confirmOrder(long orderId) {
        return false;
    }

    @Override
    public boolean abolishOrder(long orderId) {
        return false;
    }

    @Override
    public boolean cancelOrder(long orderId) {
        return false;
    }
}
