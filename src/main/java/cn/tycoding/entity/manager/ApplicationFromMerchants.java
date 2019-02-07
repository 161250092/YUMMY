package cn.tycoding.entity.manager;

import cn.tycoding.entity.merchant.Discount;
import cn.tycoding.entity.merchant.Location;
import cn.tycoding.entity.merchant.MerchantInfo;

import java.util.List;

public class ApplicationFromMerchants {
    private long applicationId;

    private MerchantInfo oldMerchantInfo;

    private MerchantInfo newMerchantInfo;

    private boolean isRead;

    private boolean isApproved;



    public ApplicationFromMerchants() {
        this.applicationId = 1;
        this.oldMerchantInfo = new MerchantInfo();
        this.newMerchantInfo = new MerchantInfo();
        isRead = false;
        isApproved = false;
    }




    public MerchantInfo getOldMerchantInfo() {
        return oldMerchantInfo;
    }

    public void setOldMerchantInfo(MerchantInfo oldMerchantInfo) {
        this.oldMerchantInfo = oldMerchantInfo;
    }

    public MerchantInfo getNewMerchantInfo() {
        return newMerchantInfo;
    }

    public void setNewMerchantInfo(MerchantInfo newMerchantInfo) {
        this.newMerchantInfo = newMerchantInfo;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
    }

    public long getApplicationId() {
        return applicationId;
    }
}
