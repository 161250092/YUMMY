package cn.yummy.controller.merchantController;


import cn.yummy.entity.Result;
import cn.yummy.entity.merchant.MerchantRegisterInf;
import cn.yummy.service.merchantService.MerchantAccountService;
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
        Result rs = this.merchantAccountService.register(merchantRegisterInf);
        if(rs.isSuccess()) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            attributes.getRequest().getSession().setAttribute("account", rs.getMessage());
            return rs;
        }
        else
            return rs;
    }





}
