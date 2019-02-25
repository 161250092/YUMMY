package cn.yummy.service.managerService;

import cn.yummy.entity.Result;

import java.util.List;

public interface ApprovalService {

    public List getAllApplication();

    public Result passTheApplication(long applicationId);

    public Result refuseTheApplication(long applicationId);


}
