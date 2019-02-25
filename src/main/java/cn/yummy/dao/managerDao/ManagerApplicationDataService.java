package cn.yummy.dao.managerDao;

import cn.yummy.entity.manager.ApplicationFromMerchant;

import java.util.List;

public interface ManagerApplicationDataService {
    public List<ApplicationFromMerchant> getAllNotReadApplications();


    public boolean passApplication(long applicationId);

    public boolean refuseApplication(long applicationId);


}
