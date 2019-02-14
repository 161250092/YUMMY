package cn.tycoding.dao.merchantDao;

import cn.tycoding.entity.PageBean;
import cn.tycoding.entity.SearchEntity;
import cn.tycoding.entity.order.Order;

import java.util.List;

public interface MerchantOrdersDataService {



    public List<Order> getMerchantAllOrders(String idCode);


    public List<Order> checkMerchantOrders(SearchEntity searchEntity);



}
