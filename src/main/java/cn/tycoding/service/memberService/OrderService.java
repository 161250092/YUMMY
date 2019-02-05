package cn.tycoding.service.memberService;

import cn.tycoding.entity.Result;
import cn.tycoding.entity.order.Order;

import java.util.List;

public interface OrderService {

    public Result submitOrder(Order order);

    public Result cancelOrder(long orderId);

    public List<Order> getOrderRecords(String account);

    public Result confirmOrderArrival(Order order);

}
