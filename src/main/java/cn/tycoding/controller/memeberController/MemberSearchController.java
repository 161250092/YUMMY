package cn.tycoding.controller.memeberController;

import cn.tycoding.entity.PageBean;
import cn.tycoding.entity.member.DishForMember;
import cn.tycoding.entity.merchant.MerchantInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberSearchController {


    @RequestMapping("/getAllMerchants")
    public PageBean getMerchants(@RequestParam("account")String account){
        List  rows = new ArrayList();
        for(int i=0;i<10;i++){
            rows.add(new MerchantInfo());
        }
        PageBean pageBean =new PageBean(10,rows);
        return pageBean;

    }


    @RequestMapping("/searchMerchants")
    public PageBean searchMerchants(
            @RequestParam("restaurantName") String restaurantName,@RequestParam("restaurantType") String restaurantType){
        System.out.println(restaurantName);
        System.out.println(restaurantType);

        List  rows = new ArrayList();
        for(int i=0;i<5;i++){
            rows.add(new MerchantInfo());
        }
        PageBean pageBean =new PageBean(5,rows);
        return pageBean;
    }


    @RequestMapping("/getMerchantAllDishes")
    public PageBean getMerchantAllDishes(
            @RequestParam("idCode") String idCode) {

        List  rows = new ArrayList();
        for(int i=0;i<10;i++){
            rows.add(new DishForMember(i,idCode));
        }
        PageBean pageBean =new PageBean(10,rows);
        return pageBean;
    }

}
