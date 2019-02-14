package cn.tycoding.service.managerService;

import cn.tycoding.entity.Result;

import java.util.List;

public interface ApprovalService {

    public List getAllApplication();

    public Result passTheApplication(long applicationId);

    public Result refuseTheApplication(long applicationId);


}
