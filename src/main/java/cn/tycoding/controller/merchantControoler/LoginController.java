package cn.tycoding.controller.merchantControoler;

import cn.tycoding.entity.Result;
import cn.tycoding.entity.merchant.MerchantRegisterInf;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
public class LoginController {


    @RequestMapping("/merchantLogin")
    public Result merchantLogin(@RequestParam("account") String account, @RequestParam("password") String password) {
        System.out.println(account);
        return new Result(true, "登录成功");
    }



    @RequestMapping("/merchantRegister")
    public Result merchantRegister(@RequestBody MerchantRegisterInf merchantRegisterInf){
        System.out.println(merchantRegisterInf.getPassword());
        return new Result(true, "0000001");
    }


    @RequestMapping("/merchantLogout")
    public Result merchantLogout(){
        return null;
    }



}
