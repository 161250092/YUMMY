package cn.yummy.controller;



import cn.yummy.entity.primitiveType.Result;
import cn.yummy.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bankAccount")
public class BankAccountController {


    @Autowired
    private BankAccountService bankAccountService;


    @RequestMapping("/out")
    public Result out(@RequestParam("account")String account,
                      @RequestParam("password")String password,
                      @RequestParam("idCode")String idCode,
                      @RequestParam("orderId")long orderId) {

//        Result rs =  bankAccountService.out(account,password,orderId,idCode);
        return bankAccountService.out(account,password,orderId,idCode);
    }



}
