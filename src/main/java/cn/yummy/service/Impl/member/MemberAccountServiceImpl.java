package cn.yummy.service.Impl.member;

import cn.yummy.dao.memberDao.MemberAccountDataService;
import cn.yummy.entity.Result;
import cn.yummy.entity.member.Member;
import cn.yummy.service.memberService.MemberAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberAccountServiceImpl implements MemberAccountService{

    @Autowired
    private MemberAccountDataService memberAccountDataService;

    @Override
    public Result login(String account, String password) {
        String actualPassword = memberAccountDataService.getPassword(account);
        if(actualPassword.equals(""))
            return new Result(false,"用户 不存在");
        else if(!actualPassword.equals(password))
            return new Result(false,"用户名 或 密码错误");
        else {
            return new Result(true, "登陆成功");

        }
    }

    @Override
    public Result register(Member member) {
        boolean  result = memberAccountDataService.createMemberAccount(member);
        if(result)
            return new Result(true,"注册成功");
        else
            return new Result(false,"注册失败");
    }



    @Override
    public Result cancelAccount(String account) {
        return null;
    }


}
