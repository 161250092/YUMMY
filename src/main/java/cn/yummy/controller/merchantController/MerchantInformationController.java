package cn.yummy.controller.merchantController;

import cn.yummy.entity.primitiveType.Result;
import cn.yummy.entity.merchant.MerchantInfo;
import cn.yummy.service.merchantService.MerchantInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
@RequestMapping("/merchantInformation")
public class MerchantInformationController {

    @Autowired
    private MerchantInformationService merchantInformationService;
    @RequestMapping("/getMerchantInformation")
    public MerchantInfo getMerchantInfo(@RequestParam("idCode") String idCode){
//        System.out.println("get merchant information");
//        return new MerchantInfo();


        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        idCode = (String) attributes.getRequest().getSession().getAttribute("account");
        return merchantInformationService.getMerchantInfo(idCode);

    }


    @RequestMapping("/updateMerchantInformation")
    public Result updateMerchantInfo(@RequestBody MerchantInfo merchantInfo){
//
//        System.out.println(merchantInfo.getBankAccount());
//        return new Result(true,"提交更新申请成功");
        System.out.println(merchantInfo.getLocation().getAddress());

        if(merchantInformationService.updateMerchantInfo(merchantInfo))
            return new Result(true,"提交更新申请成功");
        else
            return new Result(false,"提交更新申请失败");

    }

    @RequestMapping("/addNewDiscount")
    public Result addNewDiscount(@RequestParam("totalPrice")double totalPrice,
                                 @RequestParam("reducePrice")double reducePrice){

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String idCode =(String)attributes.getRequest().getSession().getAttribute("account");
//        System.out.println(totalPrice+" "+reducePrice);
//        return new Result(true,"添加新优惠成功");
        if(merchantInformationService.addNewDiscount(idCode,totalPrice,reducePrice))
            return new Result(true,"添加新优惠成功");
        else
            return new Result(false,"添加新优惠失败");
    }


    @RequestMapping("/deleteDiscount")
    public Result deleteDiscount(@RequestParam("discountId")long discountId){
//
        if(merchantInformationService.deleteDiscount(discountId))
            return new Result(true,"删除成功");
        else
            return new Result(true,"删除失败");
    }





}
