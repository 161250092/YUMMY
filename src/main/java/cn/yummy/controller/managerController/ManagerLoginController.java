package cn.yummy.controller.managerController;

import cn.yummy.entity.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
@RequestMapping("/manager")
public class ManagerLoginController {

    @RequestMapping("/login")
    public Result adminLogin(@RequestParam("account") String account, @RequestParam("password") String password) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        attributes.getRequest().getSession().setAttribute("account", account); //将登陆用户信息存入到session域对象中
        return new Result(true, "经理："+account+"登录成功");
    }



}
