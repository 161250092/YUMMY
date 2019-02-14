package cn.tycoding.service.memberService;


import cn.tycoding.entity.PageBean;
import cn.tycoding.entity.merchant.MerchantInfo;

import java.util.List;

public interface MerchantVisitService {

    public List getAllMerchants();

    public List searchMerchants(String restaurantName,String restaurantType);

    public List getMerchantAllDishesInForce(String idCode);

//    public PageBean getMerchantsByPage(int pageCode, int pageSize);
//
//    public PageBean getDishesByPage(String idCode,int pageCode,int pageSize);
//
//    public MerchantInfo visitMerchant(String idCode);

}
