package cn.yummy.service.merchantService;

import cn.yummy.entity.order.MerchantSearchEntity;
import cn.yummy.entity.order.Order;


import java.util.List;

public interface MerchantOrdersService {

    public List<Order> getMerchantAllOrders(String idCode);

    public List<Order> checkMerchantOrders(MerchantSearchEntity searchEntity);



}
