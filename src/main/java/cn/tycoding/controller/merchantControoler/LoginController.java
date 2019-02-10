package cn.tycoding.controller.merchantControoler;


import cn.tycoding.entity.Result;
import cn.tycoding.entity.merchant.MerchantRegisterInf;
import cn.tycoding.service.merchantService.MerchantAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@RestController
public class LoginController {

    @Autowired
    private MerchantAccountService merchantAccountService;

    @RequestMapping("/merchantLogin")
    public Result merchantLogin(@RequestParam("account") String account, @RequestParam("password") String password) {
            Result result = this.merchantAccountService.login(account,password);
            if(result.isSuccess()){
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                attributes.getRequest().getSession().setAttribute("account", account); //将登陆用户信息存入到session域对象中
            }
            return result;
    }



    @RequestMapping("/merchantRegister")
    public Result merchantRegister(@RequestBody MerchantRegisterInf merchantRegisterInf){

//        System.out.println(merchantRegisterInf.getPassword());
        return this.merchantAccountService.register(merchantRegisterInf);
    }


    @RequestMapping("/merchantLogout")
    public Result merchantLogout(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        attributes.getRequest().getSession().removeAttribute("account");
        return null;
    }



}
