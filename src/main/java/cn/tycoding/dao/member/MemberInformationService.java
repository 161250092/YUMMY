package cn.tycoding.dao.member;


import cn.tycoding.entity.member.Member;
import cn.tycoding.entity.merchant.Location;

import java.util.List;

public interface MemberInformationService {

    public List<Location> getMemberLocation(String account);

    public boolean addNewLocation(Location location);

    public boolean deleteLocation(long locationId);



    public Member getMemberInformation(String account);

    public boolean updateMemberInformation(Member member);

    public boolean deleteAccount(String account);



}
