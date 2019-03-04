package cn.yummy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
public class IndexController {

    /**
     * 这些页面对未登录用户可见
     *
     *
     */
    @GetMapping(value = {"/", "/index"})
    public String index() {
        return "home/index";
    }


    @GetMapping(value = {"/member"})
    public String member() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        attributes.getRequest().getSession().removeAttribute("account");
        attributes.getRequest().getSession().removeAttribute("idCode");
        return "memberPages/memberLogin";
    }

    @GetMapping(value = {"/memberRegister"})
    public String memberRegister() {
        return "memberPages/memberRegister";
    }




    @GetMapping(value = {"/merchant"})
    public String merchant() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        attributes.getRequest().getSession().removeAttribute("account");
        attributes.getRequest().getSession().removeAttribute("idCode");
        return "merchantPages/merchantLogin";
    }


    @GetMapping(value = {"/merchantRegister"})
    public String merchantRegister() {
        return "merchantPages/merchantRegister";
    }





    @GetMapping(value = {"/manager"})
    public String managerLogin() {
        return "managerPages/managerLogin";
    }


//    @GetMapping(value = {"/test"})
//    public String test() {
//        return "home/test";
//    }
//



}
