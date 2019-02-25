package cn.yummy.entity.manager;

import cn.yummy.entity.merchant.MerchantInfo;

public class ApplicationFromMerchant {
    private long applicationId;

    private MerchantInfo oldMerchantInfo;

    private MerchantInfo newMerchantInfo;

    private boolean isRead;

    private boolean isApproved;



    public ApplicationFromMerchant() {
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
