package cn.yummy.service.Impl.merchant;

import cn.yummy.dao.merchantDao.MerchantStatisticsDataService;
import cn.yummy.entity.merchant.MerchantStatistics;
import cn.yummy.service.merchantService.MerchantStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantStatisticsServiceImpl implements MerchantStatisticsService {
    @Autowired
    private MerchantStatisticsDataService merchantStatisticsDataService;

    @Override
    public MerchantStatistics getMerchantStatistics(String account) {
        MerchantStatistics merchantStatistics = new MerchantStatistics();
        merchantStatistics.setMonthlyIncome(merchantStatisticsDataService.getMerchantMonthlyIncome(account));
        merchantStatistics.setReceivedOrdersNum(merchantStatisticsDataService.getReceivedOrdersNum(account));
        merchantStatistics.setAbolishedOrdersNum(merchantStatisticsDataService.getAbolishedOrdersNum(account));
        return merchantStatistics;
    }

}
