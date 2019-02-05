package cn.tycoding.service.memberService;


import cn.tycoding.entity.Result;
import cn.tycoding.entity.member.Member;

public interface MemberAccountManagerService {

    public Result login(String account, String password);

    //注册
    public Result register(Member member, String verificationCode);

    //获取验证码
    public Result getVerificationCode(String mailAddress);

    //注销账号
    public Result cancelAccount(String account);



}
