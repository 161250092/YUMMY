package cn.yummy.service.Impl.merchant;

import cn.yummy.dao.merchantDao.MerchantOrdersDataService;
import cn.yummy.entity.SearchEntity;
import cn.yummy.entity.order.Order;
import cn.yummy.service.merchantService.MerchantOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MerchantOrdersServiceImpl implements MerchantOrdersService {


    @Autowired
    private MerchantOrdersDataService merchantOrdersDataService;

    @Override
    public List<Order> getMerchantAllOrders(String idCode) {
        return merchantOrdersDataService.getMerchantAllOrders(idCode);
    }

    @Override
    public List<Order> checkMerchantOrders(SearchEntity searchEntity) {
        return merchantOrdersDataService.checkMerchantOrders(searchEntity);
    }
}
