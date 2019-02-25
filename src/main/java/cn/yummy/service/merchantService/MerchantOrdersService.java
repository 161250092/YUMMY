package cn.yummy.service.merchantService;

import cn.yummy.entity.SearchEntity;
import cn.yummy.entity.merchant.Dish;
import cn.yummy.entity.member.MemberLevel;
import cn.yummy.entity.order.Order;


import java.util.Date;
import java.util.List;

public interface MerchantOrdersService {

    public List<Order> getMerchantAllOrders(String idCode);

    public List<Order> checkMerchantOrders(SearchEntity searchEntity);



}
