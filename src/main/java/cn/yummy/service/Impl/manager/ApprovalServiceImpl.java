package cn.yummy.service.Impl.manager;

import cn.yummy.dao.managerDao.ManagerApplicationDataService;
import cn.yummy.entity.primitiveType.Result;
import cn.yummy.service.managerService.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ApprovalServiceImpl implements ApprovalService{
    @Autowired
    ManagerApplicationDataService managerApplicationDataService;

    @Override
    public List getAllApplication() {
        return managerApplicationDataService.getAllNotReadApplications();
    }

    @Override
    public Result passTheApplication(long applicationId) {
        if(managerApplicationDataService.passApplication(applicationId))
            return new Result(true,"审核成功");
        else
            return new Result(false,"失败");
    }

    @Override
    public Result refuseTheApplication(long applicationId) {
        if(managerApplicationDataService.refuseApplication(applicationId))
            return new Result(true,"拒绝成功");
        else
            return new Result(false,"失败");
    }


}
