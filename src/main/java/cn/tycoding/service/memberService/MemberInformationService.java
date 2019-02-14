package cn.tycoding.service.memberService;

import cn.tycoding.entity.Result;
import cn.tycoding.entity.member.Member;
import cn.tycoding.entity.merchant.Location;


public interface MemberInformationService {

    public Member getMemberInformation(String account);

    public Result updateMemberInformation(Member member);

    public Result addNewLocation(Location location);

    public Result deleteLocation(long locationId);

    public Result deleteAccount(String account);

}
