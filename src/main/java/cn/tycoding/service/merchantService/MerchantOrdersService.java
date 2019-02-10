package cn.tycoding.service.merchantService;

import cn.tycoding.entity.SearchEntity;
import cn.tycoding.entity.merchant.Dish;
import cn.tycoding.entity.member.MemberLevel;
import cn.tycoding.entity.order.Order;


import java.util.Date;
import java.util.List;

public interface MerchantOrdersService {

    public List<Order> getMerchantAllOrders(String idCode);

    public List<Order> checkMerchantOrders(SearchEntity searchEntity);



}
