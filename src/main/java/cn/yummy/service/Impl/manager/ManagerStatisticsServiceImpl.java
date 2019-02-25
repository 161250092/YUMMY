package cn.yummy.service.Impl.manager;


import cn.yummy.dao.managerDao.ManagerStatisticsDataService;
import cn.yummy.entity.manager.StatisticsInformation;
import cn.yummy.service.managerService.ManagerStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerStatisticsServiceImpl implements ManagerStatisticsService {

    @Autowired
    private ManagerStatisticsDataService  managerStatisticsDataService;

    @Override
    public StatisticsInformation getStatisticsInformation() {

        StatisticsInformation statisticsInformation = new StatisticsInformation();
        statisticsInformation.setBalance(managerStatisticsDataService.getBalance());
        statisticsInformation.setMonthlyActualIncome(managerStatisticsDataService.getMonthlyActualIncome());

        statisticsInformation.setMonthlyIncome(managerStatisticsDataService.getMonthlyIncome());
        statisticsInformation.setMonthlyExpense(managerStatisticsDataService.getMonthlyExpense());

        statisticsInformation.setEachLevelMemberNum(managerStatisticsDataService.getEachLevelMemberNum());

        statisticsInformation.setMemberNum(managerStatisticsDataService.getMemberNum());
        statisticsInformation.setRestaurantNum(managerStatisticsDataService.getRestaurantNum());

        return statisticsInformation;
    }
}
