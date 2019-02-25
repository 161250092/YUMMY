package cn.yummy.service.memberService;

import cn.yummy.entity.MemberSearchEntity;
import cn.yummy.entity.Result;
import cn.yummy.entity.order.Order;

import java.util.List;

public interface OrderService {

    public Result submitOrder(Order order);

    public List getMemberOrders(String account);

    public List searchMemberOrders(MemberSearchEntity memberSearchEntity);

//  确认接收 ，前提是已经付款
    public Result confirmOrder(long orderId);

    public Result abolishOrder(long orderId);

    public Result cancelOrder(long orderId);

    public void turnOrderStateIsPayed(Order order);
}
