package cn.tycoding.util;


import cn.tycoding.dao.merchantDao.MerchantInformationDataService;
import cn.tycoding.dao.merchantDao.MerchantInformationDataServiceImpl;
import cn.tycoding.entity.member.DishForMember;
import cn.tycoding.entity.member.MemberLevel;
import cn.tycoding.entity.merchant.Discount;
import cn.tycoding.entity.order.Order;

import java.util.ArrayList;
import java.util.List;

public class ComputePrice {

    private MerchantInformationDataService merchantInformationDataService = new MerchantInformationDataServiceImpl();


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

        return price-reducePrice;
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


//会员减价 尚未实现
    private double memberLevelDiscount(MemberLevel memberLevel){
        int level = memberLevel.getLevel();

        return 1;
    }


}
