package cn.tycoding.service.Impl.merchant;

import cn.tycoding.dao.merchantDao.MerchantStatisticsDataService;
import cn.tycoding.entity.merchant.MerchantStatistics;
import cn.tycoding.service.merchantService.MerchantStatisticsService;
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
