package cn.yummy.service.Impl.member;

import cn.yummy.dao.memberDao.MemberStatisticsDataService;
import cn.yummy.entity.member.MemberStatisticsInformation;
import cn.yummy.service.memberService.MemberStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class MemberStatisticsServiceImpl implements MemberStatisticsService {
    @Autowired
    private MemberStatisticsDataService memberStatisticsDataService;


    @Override
    public double getMemberConsumption(String account) {
        return memberStatisticsDataService.getMemberConsumption(account);
    }

    @Override
    public int getAbolishedOrdersNum(String account) {
        return memberStatisticsDataService.getAbolishedOrdersNum(account);
    }

    @Override
    public int getAcceptedOrdersNum(String account) {
        return memberStatisticsDataService.getAcceptedOrdersNum(account);
    }

    @Override
    public HashMap<String, Double> getConsumptionInformation(String account) {
        return memberStatisticsDataService.getConsumptionInformation(account);
    }

    @Override
    public MemberStatisticsInformation getMemberStatisticsInformation(String account) {
        MemberStatisticsInformation memberStatisticsInformation = new MemberStatisticsInformation();
        memberStatisticsInformation.setAbolishedOrderNum(this.getAbolishedOrdersNum(account));
        memberStatisticsInformation.setAcceptedOrdersNum(this.getAcceptedOrdersNum(account));
        memberStatisticsInformation.setConsumptionInformation(this.getConsumptionInformation(account));
        memberStatisticsInformation.setTotalConsumption(this.getMemberConsumption(account));

        return memberStatisticsInformation;
    }
}
