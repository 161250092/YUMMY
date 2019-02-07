package cn.tycoding.dao.merchant;

import cn.tycoding.entity.SearchEntity;
import cn.tycoding.entity.order.Order;

import java.util.List;

public interface MerchantOrdersService {

    public List<Order> getMerchantAllOrders(String idCode);

    public List<Order> checkMerchantOrders(SearchEntity searchEntity);



}
