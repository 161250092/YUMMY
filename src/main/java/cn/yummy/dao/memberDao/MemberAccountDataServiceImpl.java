package cn.yummy.dao.memberDao;

import cn.yummy.dao.mysql.MySQLConnector;
import cn.yummy.entity.member.Member;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Service
public class MemberAccountDataServiceImpl implements MemberAccountDataService {


    private Connection conn;

    @Override
    public String getPassword(String account) {

        String password = "";
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");
        try{
            sql = "select password from member where account=?";

            stmt = conn.prepareStatement(sql);

            stmt.setString(1,account);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                password = rs.getString("password");
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return password;
    }

    @Override
    public boolean isAccountExist(String account) {
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        String deleteAccountPassword = "";
        try{
            sql = "select password from deletedMember where account=?";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1,account);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                deleteAccountPassword = rs.getString("password");
            }

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        //  getPassword(account)如果是空说明账号不存在，deleteAccountPassword.equals("")为空说明没被注销
        return !this.getPassword(account).equals("")&&!deleteAccountPassword.equals("");

    }

    @Override
    public boolean createMemberAccount(Member member) {
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");

        try{
            sql = "insert into member(account,password,nickName,phone,mail,memberLevel)VALUES(?,?,?,?,?,?) ";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,member.getAccount());
            stmt.setString(2,member.getPassword());
            stmt.setString(3,member.getNickName());
            stmt.setString(4,member.getPhone());
            stmt.setString(5,member.getMail());
            stmt.setInt(6,1);
            stmt.executeUpdate();

            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }
}
