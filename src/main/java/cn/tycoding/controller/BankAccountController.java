package cn.tycoding.controller;

import cn.tycoding.entity.Result;
import cn.tycoding.entity.order.Order;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bankAccount")
public class BankAccountController {


    @RequestMapping("/in")
    public Result in() {

        return new Result(true,"转入成功");
    }


    @RequestMapping("/out")
    public Result out(@RequestParam("account")String account,
                      @RequestParam("password")String password,
                      @RequestParam("orderId")String orderId) {

        System.out.println(account);
        System.out.println(password);
        System.out.println(orderId);

        return new Result(true,"转出成功,预计20分钟送达");
    }



}
