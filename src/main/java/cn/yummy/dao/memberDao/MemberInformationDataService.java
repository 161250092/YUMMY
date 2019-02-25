package cn.yummy.dao.memberDao;


import cn.yummy.entity.member.Member;
import cn.yummy.entity.primitiveType.Location;

import java.util.List;

public interface MemberInformationDataService {

    public List<Location> getMemberLocation(String account);

    public boolean addNewLocation(Location location);

    public boolean deleteLocation(long locationId);



    public Member getMemberInformation(String account);

//  修改昵称和电话，属于用户调用
    public boolean updateMemberInformation(Member member);

//  修改等级，属于系统调用
    public void updateMemberLevel(String account,int level);

    public boolean deleteAccount(String account);



}
