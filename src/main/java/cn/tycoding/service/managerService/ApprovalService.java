package cn.tycoding.service.managerService;

import cn.tycoding.entity.Result;

public interface ApprovalService {

    public Result passTheApplication(long applicationId);

    public Result refuseTheApplication(long applicationId);


}
