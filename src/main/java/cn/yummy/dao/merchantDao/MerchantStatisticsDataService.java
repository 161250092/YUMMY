package cn.yummy.dao.merchantDao;

public interface MerchantStatisticsDataService {
    public double getMerchantMonthlyIncome(String account);

    public int getReceivedOrdersNum(String account);

    public int getAbolishedOrdersNum(String account);

}
