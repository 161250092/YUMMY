package cn.tycoding.dao.memberDao;

import java.util.List;

public interface MemberStatisticsDataService {

    public double getMemberConsumption(String account);

    public int getAbolishedOrders(String account);

    public int getAcceptedOrders(String account);

    public List<String> getMerchantConsumed(String account);

    public List<Double>  getMemberConsumptionInEachMerchant(String account);

}
