package cn.tycoding.dao.managerDao;

import cn.tycoding.entity.manager.ApplicationFromMerchant;

import java.util.List;

public interface ManagerApplicationDataService {
    public List<ApplicationFromMerchant> getAllNotReadApplications();


    public boolean passApplication(long applicationId);

    public boolean refuseApplication(long applicationId);


}
