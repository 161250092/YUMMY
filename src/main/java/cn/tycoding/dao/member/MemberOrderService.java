package cn.tycoding.dao.member;

import cn.tycoding.entity.order.Order;

import java.util.List;

public interface MemberOrderService {
    public boolean submitOrder(Order order);


    public List getMemberOrders(String account);

    public boolean confirmOrder(long orderId);

    public boolean abolishOrder(long orderId);

    public boolean cancelOrder(long orderId);


}
