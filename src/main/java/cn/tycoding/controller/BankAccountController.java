package cn.tycoding.controller;

import cn.tycoding.entity.Result;
import cn.tycoding.entity.order.Order;
import cn.tycoding.service.BankAccountService;
import cn.tycoding.service.memberService.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bankAccount")
public class BankAccountController {


//    @RequestMapping("/in")
//    public Result in() {
//
//        return new Result(true,"转入成功");
//    }

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/out")
    public Result out(@RequestParam("account")String account,
                      @RequestParam("password")String password,
                      @RequestParam("idCode")String idCode,
                      @RequestParam("orderId")long orderId) {


        Result rs =  bankAccountService.out(account,password,orderId,idCode);
        orderService.payForOrder(orderId);
        System.out.println(rs.getMessage());
        return rs;
    }



}
