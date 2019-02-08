package cn.tycoding.entity.member;


import cn.tycoding.entity.merchant.Location;

import java.util.ArrayList;
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


    public Member(String account){
        this.account = account;
        this.nickName ="DongDaYo";
        this.phone = "15834812411";
        this.mail = "11231231@qq.com";
        memberLevel = new MemberLevel();
        memberLevel.setLevel(1);
        locations = new ArrayList<Location>();
        Location location1 = new Location(1);
        Location location2 = new Location(1);
        Location location3 = new Location(1);
        locations.add(location1);
        locations.add(location2);
        locations.add(location3);
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