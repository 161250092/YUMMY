package cn.tycoding.service.merchantService;

import cn.tycoding.entity.merchant.Dish;
import cn.tycoding.entity.member.MemberLevel;


import java.util.Date;
import java.util.List;

public interface MerchantStatisticsService {

    public List<Dish>  getMerchantAllOrders(String idCode);

    public List<Dish>  getMerchantPayedOrders(String idCode);

    public List<Dish>  getMerchantAbolishedOrders(String idCode);


    public List<Dish>  getOrdersByTime(String idCode, Date startTime, Date endTime);

    public List<Dish>  getOrdersByMoney(String idCode, double money);

    public List<Dish> getOrdersByMemberLevel(String idCode, MemberLevel memberLevel);

    public List<Integer> getRecentIncome(String idCode, Date startTime, Date endTime);


}
