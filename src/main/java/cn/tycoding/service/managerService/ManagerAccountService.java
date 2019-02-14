package cn.tycoding.service.managerService;

import cn.tycoding.entity.Result;

public interface ManagerAccountService {

    public Result login(String account,String password);
}
