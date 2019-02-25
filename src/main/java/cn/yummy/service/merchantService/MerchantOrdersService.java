package cn.yummy.service.merchantService;

import cn.yummy.entity.order.SearchEntity;
import cn.yummy.entity.order.Order;


import java.util.List;

public interface MerchantOrdersService {

    public List<Order> getMerchantAllOrders(String idCode);

    public List<Order> checkMerchantOrders(SearchEntity searchEntity);



}
