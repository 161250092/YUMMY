package cn.yummy.entity.yummy;

import java.io.Serializable;

public class SystemMail implements Serializable {
    private long mailId;
    private String account;
    private String password;

    public SystemMail() {
    }

    public long getMailId() {
        return mailId;
    }

    public void setMailId(long mailId) {
        this.mailId = mailId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
