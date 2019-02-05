package cn.tycoding.service.memberService;

import cn.tycoding.entity.Result;

import java.util.List;

public interface MemberInformationService {

    public List<String> getLocations(String account);

    public Result modifyPhone(String account, String oldPhone, String newPhone);

    public Result modifyName(String account, String oldName, String newName);

    public Result addLocation(String account,String newLocation);

    public Result deleteLocation(String account,String location);

    public Result changeLocation(String account,String oldLocation,String newLocation);

}
