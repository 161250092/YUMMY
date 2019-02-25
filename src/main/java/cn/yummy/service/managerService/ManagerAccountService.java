package cn.yummy.service.managerService;

import cn.yummy.entity.primitiveType.Result;

public interface ManagerAccountService {

    public Result login(String account,String password);
}
