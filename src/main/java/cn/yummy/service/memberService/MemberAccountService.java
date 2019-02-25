package cn.yummy.service.memberService;


import cn.yummy.entity.Result;
import cn.yummy.entity.member.Member;

public interface MemberAccountService {

    public Result login(String account, String password);

    //注册
    public Result register(Member member);


    //注销账号
    public Result cancelAccount(String account);



}
