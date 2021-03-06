package cn.yummy.service.Impl.member;

import cn.yummy.dao.memberDao.MemberInformationDataService;
import cn.yummy.entity.primitiveType.Result;
import cn.yummy.entity.member.Member;
import cn.yummy.entity.primitiveType.Location;
import cn.yummy.service.memberService.MemberInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberInformationServiceImpl implements MemberInformationService{

    @Autowired
    private MemberInformationDataService memberInformationDataService;

    @Override
    public Member getMemberInformation(String account) {
        return memberInformationDataService.getMemberInformation(account);
    }

    @Override
    public Result updateMemberInformation(Member member) {
        if(memberInformationDataService.updateMemberInformation(member))
            return new Result(true,"修改成功");
        else
            return new Result(false,"修改失败");
    }

    @Override
    public Result addNewLocation(Location location) {
        if(memberInformationDataService.addNewLocation(location))
            return new Result(true,"添加位置成功");
        else
            return new Result(false,"添加位置失败");
    }

    @Override
    public Result deleteLocation(long locationId) {

        if(memberInformationDataService.deleteLocation(locationId))
            return new Result(true,"删除成功");
        else
            return new Result(false,"删除失败");
    }

    @Override
    public Result deleteAccount(String account) {
        if(memberInformationDataService.deleteAccount(account))
            return new Result(true,"");
        else
            return new Result(false,"");
    }
}
