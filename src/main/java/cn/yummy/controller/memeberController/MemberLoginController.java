package cn.yummy.controller.memeberController;

import cn.yummy.entity.primitiveType.Result;
import cn.yummy.entity.member.Member;
import cn.yummy.mapper.SystemMailMapper;
import cn.yummy.util.mail.VerificationCode;
import cn.yummy.service.memberService.MemberAccountService;
import cn.yummy.util.GenerateVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.security.GeneralSecurityException;

@RestController
@RequestMapping("/member")
public class MemberLoginController {

    @Autowired
    private MemberAccountService memberAccountService;


    @RequestMapping("/login")
    public Result memberLogin(@RequestParam("account") String account, @RequestParam("password") String password) {
        Result rs = memberAccountService.login(account,password);

        if(rs.isSuccess()) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            attributes.getRequest().getSession().setAttribute("account", account); //将登陆用户信息存入到session域对象中
            return new Result(true, "用户" + account + "登录成功");
        }
        else
            return rs;
    }



    @RequestMapping("/sendVerificationCode")
    public Result sendVerificationCode(@RequestParam("mail") String mail) {
        String verificationCode = GenerateVerification.generateVerification(6);

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        attributes.getRequest().getSession().setAttribute("verificationCode", verificationCode);

        memberAccountService.sendVerificationCode(mail,verificationCode);
        return new Result(true, "已发送");
    }

    @RequestMapping("/validate")
    public Result validate(@RequestParam("verificationCode") String verificationCode) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String verificationCodeInStore =(String) attributes.getRequest().getSession().getAttribute("verificationCode");

        if(verificationCodeInStore.equals(verificationCode))
            return new Result(true, "验证码一致");
        else
            return new Result(false,"验证码不一致");
    }


    @RequestMapping("/register")
    public Result memberRegister(@RequestBody Member member) {
         Result rs = memberAccountService.register(member);
         if(rs.isSuccess()){
             ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
             attributes.getRequest().getSession().setAttribute("account",member.getAccount());
         }
        return rs;
    }

    @RequestMapping("/logout")
    public Result memberLogout(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        attributes.getRequest().getSession().removeAttribute("account");
        return null;
    }


}
