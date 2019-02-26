package cn.yummy.util;


import cn.yummy.dao.memberDao.MemberInformationDataService;
import cn.yummy.dao.memberDao.MemberInformationDataServiceImpl;
import cn.yummy.dao.merchantDao.MerchantInformationDataService;
import cn.yummy.dao.merchantDao.MerchantInformationDataServiceImpl;
import cn.yummy.entity.member.DishForMember;
import cn.yummy.entity.member.Member;
import cn.yummy.entity.member.MemberLevel;
import cn.yummy.entity.merchant.Discount;
import cn.yummy.entity.order.Order;
import cn.yummy.service.Impl.member.MemberInformationServiceImpl;
import cn.yummy.service.memberService.MemberInformationService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ComputePrice {

    private MerchantInformationDataService merchantInformationDataService = new MerchantInformationDataServiceImpl();

    private MemberInformationDataService memberInformationDataService = new MemberInformationDataServiceImpl();

    public boolean isReachMinDelivery(double price,Order order){
        return price>merchantInformationDataService.getMerchantInfo(order.getIdCode()).getMinDeliveryCost();
    }


    public double computePriceWithoutDiscount(Order order){
        double totalPrice = 0;
        for(DishForMember dishForMember:order.getDishes())
            totalPrice += dishForMember.getPrice()*dishForMember.getSelectQuantity();

        return totalPrice+merchantInformationDataService.getMerchantInfo(order.getIdCode()).getDeliveryCost();
    }


    public double computeTotalPrice(Order order){
        double price = this.computePriceWithoutDiscount(order);

        List<Discount> discounts = merchantInformationDataService.getMerchantDiscounts(order.getIdCode());
        double reducePrice = this.maxDiscount(discounts,price);

        Member member = memberInformationDataService.getMemberInformation(order.getAccount());
        double memberDiscount = this.memberLevelDiscount(member.getMemberLevel());

//        System.out.println("会员折扣:"+memberDiscount);

        return (price-reducePrice)*memberDiscount;
    }

    private double maxDiscount(List<Discount> discounts,double price){

        ArrayList<Double>  satisfiedDiscount = new ArrayList<>();
        for(Discount discount:discounts){
            if(discount.getTotalPrice()<price)
                satisfiedDiscount.add(discount.getReducePrice());
        }
        double maxDiscount = 0;

        for (Double aSatisfiedDiscount : satisfiedDiscount) {
            if (aSatisfiedDiscount > maxDiscount)
                maxDiscount = aSatisfiedDiscount;
        }

        return maxDiscount;
    }


    public double returnMoney(Order order){
        LocalDateTime submitTime = order.getSubmitTime();
        LocalDateTime expectTime = order.getExpectedArriveTime();

        Duration expectedDuration = Duration.between(submitTime,expectTime);
        long expectedWaitingTime = expectedDuration.toHours()*60+expectedDuration.toMinutes();

        Duration actualDuration = Duration.between(submitTime,LocalDateTime.now());
        long actualWaitingTime =actualDuration.toHours()*60+actualDuration.toMinutes();


        double returnPercent = this.returnPercent(expectedWaitingTime,actualWaitingTime);
        System.out.println("退回比率 "+returnPercent);

        return  order.getTotalPrice()*returnPercent;
    }

    private double[] timePercent ={0.1,0.3,0.5,0.7,0.9,1.1,1.3,1.5};

    private double[] returnPercent ={0.9,0.7,0.5,0.3,0.1,0.2,0.3,0.7};

    private double returnPercent(long expectTime,long actualTime){
            double percent =actualTime/expectTime;
            int index = 0;
            for(int i=0;i<timePercent.length-1;i++){
                if(percent>=timePercent[i]&&percent<timePercent[i+1]) {
                    index = i;
                    break;
                }
            }
            if(percent<timePercent[0])
                index = 0;
            if(percent>timePercent[timePercent.length-1])
                index = timePercent.length-1;

            return returnPercent[index];

    }


//会员减价 尚未实现
    private double memberLevelDiscount(MemberLevel memberLevel){
        int level = memberLevel.getLevel();
        return 1-(level-1)*0.01;
    }




}
