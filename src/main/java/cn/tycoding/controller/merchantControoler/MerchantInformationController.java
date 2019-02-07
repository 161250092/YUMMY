package cn.tycoding.controller.merchantControoler;

import cn.tycoding.entity.Result;
import cn.tycoding.entity.merchant.Discount;
import cn.tycoding.entity.merchant.MerchantInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merchantInformation")
public class MerchantInformationController {

    @RequestMapping("/getMerchantInformation")
    public MerchantInfo getMerchantInfo(@RequestParam("idCode") String idCode){
        System.out.println("get merchant information");
        return new MerchantInfo();
    }


    @RequestMapping("/updateMerchantInformation")
    public Result updateMerchantInfo(@RequestBody MerchantInfo merchantInfo){

        System.out.println(merchantInfo.getBankAccount());
        return new Result(true,"提交更新申请成功");

    }

    @RequestMapping("/addNewDiscount")
    public Result addNewDiscount(@RequestParam("totalPrice")double totalPrice,
                                 @RequestParam("reducePrice")double reducePrice){
        System.out.println(totalPrice+" "+reducePrice);
        return new Result(true,"添加新优惠成功");
    }


    @RequestMapping("/deleteDiscount")
    public Result deleteDiscount(@RequestParam("discountId")long discountId){
        System.out.println(discountId);
        return new Result(true,"删除成功");
    }





}
