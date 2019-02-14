package cn.tycoding.service.memberService;


import cn.tycoding.entity.Result;
import cn.tycoding.entity.member.Member;

public interface MemberAccountService {

    public Result login(String account, String password);

    //注册
    public Result register(Member member);


    //注销账号
    public Result cancelAccount(String account);



}
