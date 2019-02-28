package cn.yummy.controller;



import cn.yummy.entity.primitiveType.Result;
import cn.yummy.entity.yummy.PayForm;
import cn.yummy.entity.yummy.SystemMail;
import cn.yummy.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/bankAccount")
public class BankAccountController {


    @Autowired
    private BankAccountService bankAccountService;



    @RequestMapping("/payOut")
    public Result out(@RequestBody PayForm payForm) {
//        System.out.println(1+" "+payForm.getDateTime());
//        if(!payForm.isDeliveryRightNow()) {
//            payForm.formatTransfer();
//            System.out.println(2 + " " + payForm.getDeliveryTime());
//        }
//        return new Result(true,"payed");
        return bankAccountService.payForOrder(payForm);
    }



}
