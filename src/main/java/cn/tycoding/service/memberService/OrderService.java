package cn.tycoding.service.memberService;

import cn.tycoding.entity.Result;
import cn.tycoding.entity.order.Order;

import java.util.List;

public interface OrderService {

    public Result submitOrder(Order order);

    public List getMemberOrders(String account);

    public Result confirmOrder(long orderId);

    public Result abolishOrder(long orderId);

    public Result cancelOrder(long orderId);

    public void payForOrder(long orderId);
}
