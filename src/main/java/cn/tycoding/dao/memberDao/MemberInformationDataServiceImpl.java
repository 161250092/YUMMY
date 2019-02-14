package cn.tycoding.dao.memberDao;

import cn.tycoding.dao.mysql.MySQLConnector;
import cn.tycoding.entity.member.Member;
import cn.tycoding.entity.member.MemberLevel;
import cn.tycoding.entity.merchant.Location;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Service
public class MemberInformationDataServiceImpl implements MemberInformationDataService {

    private Connection conn;

    @Override
    public List<Location> getMemberLocation(String account) {

        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");
        List<Location> locations = new ArrayList<>();

        try{
            sql = "select * from Location where account=?";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1,account);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Location location = new Location();
                location.setLocationId(rs.getLong("locationId"));
                location.setAddress(rs.getString("address"));
                location.setAccount(account);
                location.setLng(rs.getDouble("lng"));
                location.setLat(rs.getDouble("lat"));
                locations.add(location);
            }
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return locations;
    }

    @Override
    public boolean addNewLocation(Location location) {

        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "insert into location(account,lat,lng,address)VALUES(?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,location.getAccount());
            stmt.setDouble(2,location.getLat());
            stmt.setDouble(3,location.getLng());
            stmt.setString(4,location.getAddress());
            stmt.executeUpdate();
            stmt.close();
            conn.close();

            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteLocation(long locationId) {
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "delete from location where locationId=?";

            stmt = conn.prepareStatement(sql);
            stmt.setLong(1,locationId);
            stmt.executeUpdate();

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }


        return true;
    }

    @Override
    public Member getMemberInformation(String account) {
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");
        Member member = new Member();
        try{
            sql = "select * from member where account=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,account);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                member.setUserId(rs.getLong("userId"));
                member.setAccount(account);
                member.setPassword(rs.getString("password"));
                member.setMail(rs.getString("mail"));
                member.setNickName(rs.getString("nickName"));
                member.setPhone(rs.getString("phone"));
                member.setMemberLevel(new MemberLevel(rs.getInt("memberLevel")));
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        member.setLocations(this.getMemberLocation(account));
        return member;
    }

    @Override
    public boolean updateMemberInformation(Member member) {
        long userId = this.getUserId(member.getAccount());

        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");
        try{
            sql = "update member set nickName=?,phone=? where userId=?";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1,member.getNickName());
            stmt.setString(2,member.getPhone());
            stmt.setLong(3,userId);
            stmt.executeUpdate();
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }


    private  long getUserId(String account){
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");
        long userId = 0;
        try{
            sql = "select userId from member where account=?";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1,account);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                userId = rs.getLong("userId");
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return userId;
    }

    @Override
    public boolean deleteAccount(String account) {

        Member member = this.getMemberInformation(account);
        this.removeAccountIntoDeletedMember(member);

        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "delete from member where userId=?";

            stmt = conn.prepareStatement(sql);
            stmt.setLong(1,member.getUserId());
            stmt.executeUpdate();
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }



        return true;
    }


    private void removeAccountIntoDeletedMember(Member member){

        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "insert into deletedMember(userId,account,password,nickName,phone,mail,memberLevel)values(?,?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1,member.getUserId());
            stmt.setString(2,member.getAccount());
            stmt.setString(3,member.getPassword());
            stmt.setString(4,member.getNickName());
            stmt.setString(5,member.getPhone());
            stmt.setString(6,member.getMail());
            stmt.setInt(7,member.getMemberLevel().getLevel());
            stmt.executeUpdate();
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
