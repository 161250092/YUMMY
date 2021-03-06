package cn.yummy.controller.memeberController;

import cn.yummy.entity.merchant.MerchantInfo;
import cn.yummy.service.memberService.MerchantVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberSearchController {

    @Autowired
    private MerchantVisitService merchantVisitService;




    @RequestMapping("/getAllMerchants")
    public List getMerchants(@RequestParam("account")String account){
//可访问商家
        return merchantVisitService.getAllAccessibleMerchants();

    }


    @RequestMapping("/searchMerchants")
    public List searchMerchants(
            @RequestParam("restaurantName") String restaurantName,@RequestParam("restaurantType") String restaurantType){
        return merchantVisitService.searchMerchants(restaurantName,restaurantType);

    }


    @RequestMapping("/getMerchantAllDishes")
    public List getMerchantAllDishes(
            @RequestParam("idCode") String idCode) {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        attributes.getRequest().getSession().setAttribute("idCode",idCode);



        return merchantVisitService.getMerchantAllDishesInForce(idCode);
    }

    @RequestMapping("/getMerchantInfo")
    public MerchantInfo getMerchantInfo(
            @RequestParam("idCode") String idCode) {
        return merchantVisitService.getMerchantInfo(idCode);
    }


}
