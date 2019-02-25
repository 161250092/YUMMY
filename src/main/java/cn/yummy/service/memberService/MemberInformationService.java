package cn.yummy.service.memberService;

import cn.yummy.entity.primitiveType.Result;
import cn.yummy.entity.member.Member;
import cn.yummy.entity.primitiveType.Location;


public interface MemberInformationService {

    public Member getMemberInformation(String account);

    public Result updateMemberInformation(Member member);

    public Result addNewLocation(Location location);

    public Result deleteLocation(long locationId);

    public Result deleteAccount(String account);

}
