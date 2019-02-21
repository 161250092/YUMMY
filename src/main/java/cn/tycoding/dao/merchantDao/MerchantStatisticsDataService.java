package cn.tycoding.dao.merchantDao;

public interface MerchantStatisticsDataService {
    public double getMerchantMonthlyIncome(String account);

    public int getReceivedOrdersNum(String account);

    public int getAbolishedOrdersNum(String account);

}
