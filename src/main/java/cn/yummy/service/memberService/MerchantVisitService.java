package cn.yummy.service.memberService;


import cn.yummy.entity.merchant.MerchantInfo;

import java.util.List;

public interface MerchantVisitService {

    public List getAllMerchants();

    public List searchMerchants(String restaurantName,String restaurantType);

    public List getMerchantAllDishesInForce(String idCode);

    public MerchantInfo getMerchantInfo(String idCode);

}
