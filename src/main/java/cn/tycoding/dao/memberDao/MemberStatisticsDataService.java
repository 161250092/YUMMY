package cn.tycoding.dao.memberDao;

import java.util.HashMap;
import java.util.List;

public interface MemberStatisticsDataService {

    public double getMemberConsumption(String account);

    public int getAbolishedOrdersNum(String account);

    public int getAcceptedOrdersNum(String account);

    public HashMap<String,Double> getConsumptionInformation(String account);

}
