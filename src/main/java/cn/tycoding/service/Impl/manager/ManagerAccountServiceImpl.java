package cn.tycoding.service.Impl.manager;

import cn.tycoding.dao.managerDao.ManagerAccountDataService;
import cn.tycoding.entity.Result;
import cn.tycoding.service.managerService.ManagerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerAccountServiceImpl implements ManagerAccountService {
    @Autowired
    private ManagerAccountDataService managerAccountDataService;

    @Override
    public Result login(String account, String password) {

        String _password = managerAccountDataService.getPassword(account);
        if(_password.equals(password))
            return new Result(true,"登录成功");
        else
            return new Result(false,"登录失败");

    }
}
