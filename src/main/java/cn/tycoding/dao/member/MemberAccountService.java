package cn.tycoding.dao.member;

import cn.tycoding.entity.member.Member;

public interface MemberAccountService {

    public String getPassword(String account);

    public boolean isAccountExist(String account);

    public boolean createMemberAccount(Member member);


}
