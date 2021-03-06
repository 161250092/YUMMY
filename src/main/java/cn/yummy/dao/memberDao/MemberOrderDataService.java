package cn.yummy.dao.memberDao;

import cn.yummy.entity.order.MemberSearchEntity;
import cn.yummy.entity.order.Order;

import java.util.List;

public interface MemberOrderDataService {
    public boolean submitOrder(Order order);

    public List getMemberOrders(String account);

    public List searchMemberOrders(MemberSearchEntity memberSearchEntity);

    public Order getOrder(long orderId);

    public double getOrderPrice(long orderId);


    public boolean confirmOrder(long orderId);

    public boolean abolishOrder(long orderId);

    public boolean cancelOrder(long orderId);



    public void turnOrderStateIsPayed(Order order);

    public boolean isPayed(long orderId);

}
