package cn.yummy.service.memberService;


import cn.yummy.entity.primitiveType.Result;
import cn.yummy.entity.member.Member;

public interface MemberAccountService {

    public Result login(String account, String password);

    //发送验证码
    public void sendVerificationCode(String mailAddress, String verificationCode);

    //注册
    public Result register(Member member);





}
