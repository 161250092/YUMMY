package cn.yummy.dao.memberDao;

import cn.yummy.entity.member.Member;

public interface MemberAccountDataService {

    public String getPassword(String account);

    public boolean isAccountExist(String account);

    public boolean createMemberAccount(Member member);


}
