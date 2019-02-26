package cn.yummy.controller.memeberController;

import cn.yummy.entity.merchant.MerchantInfo;
import cn.yummy.service.memberService.MerchantVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberSearchController {

    @Autowired
    private MerchantVisitService merchantVisitService;




    @RequestMapping("/getAllMerchants")
    public List getMerchants(@RequestParam("account")String account){

        return merchantVisitService.getAllMerchants();

    }


    @RequestMapping("/searchMerchants")
    public List searchMerchants(
            @RequestParam("restaurantName") String restaurantName,@RequestParam("restaurantType") String restaurantType){
        return merchantVisitService.searchMerchants(restaurantName,restaurantType);

    }


    @RequestMapping("/getMerchantAllDishes")
    public List getMerchantAllDishes(
            @RequestParam("idCode") String idCode) {

        return merchantVisitService.getMerchantAllDishesInForce(idCode);
    }

    @RequestMapping("/getMerchantInfo")
    public MerchantInfo getMerchantInfo(
            @RequestParam("idCode") String idCode) {
        return merchantVisitService.getMerchantInfo(idCode);
    }

}