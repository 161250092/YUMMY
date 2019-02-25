package cn.yummy.entity.member;


import cn.yummy.entity.primitiveType.Location;

import java.util.List;

public class Member {
    private long  userId;

    private String account;
    private String password;

    private String nickName;
    private String phone;

    private List<Location> locations;

    private String mail;

    private MemberLevel memberLevel;



    public Member() {
    }



    public long getUserId() {
        return userId;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getNickName() {
        return nickName;
    }

    public String getPhone() {
        return phone;
    }

    public String getMail() {
        return mail;
    }



    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public void setMail(String mail) {
        this.mail = mail;
    }

    public MemberLevel getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(MemberLevel memberLevel) {
        this.memberLevel = memberLevel;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}
