package cn.yummy.controller.managerController;

import cn.yummy.entity.primitiveType.Result;
import cn.yummy.service.managerService.ManagerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
@RequestMapping("/manager")
public class ManagerLoginController {
    @Autowired
    ManagerAccountService  managerAccountService;

    @RequestMapping("/login")
    public Result adminLogin(@RequestParam("account") String account, @RequestParam("password") String password) {

        if(managerAccountService.login(account,password).isSuccess()){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        attributes.getRequest().getSession().setAttribute("account", account); //将登陆用户信息存入到session域对象中
        return new Result(true, "经理："+account+"登录成功");
        }
        else
            return new Result(false,"登录失败");
    }



}
