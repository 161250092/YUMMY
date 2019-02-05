package cn.tycoding.service.memberService;

import cn.tycoding.entity.order.Order;

import java.util.Date;
import java.util.List;

public interface MemberStatisticsService {
    public List<Order> getOrdersByTime(String account,Date startTime,Date endTime);

    public List<Order> getOrdersByMoney(String account,double floor,double upper);

    public List<Order> getOrdersByMerchant(String account,String idCode);

}
