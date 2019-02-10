package cn.tycoding.service.Impl.merchant;

import cn.tycoding.dao.merchantDao.MerchantOrdersDataService;
import cn.tycoding.entity.SearchEntity;
import cn.tycoding.entity.order.Order;
import cn.tycoding.service.merchantService.MerchantOrdersService;
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
