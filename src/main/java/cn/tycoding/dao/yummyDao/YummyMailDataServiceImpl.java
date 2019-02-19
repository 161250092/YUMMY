package cn.tycoding.dao.yummyDao;

import cn.tycoding.dao.mysql.MySQLConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class YummyMailDataServiceImpl implements  YummyMailDataService{

    private Connection conn;
    @Override
    public String getYummyMailPassword() {

        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");
        String pw = "";
        try{
            sql = "select password from yummy_mail where account=?";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1,"1151138974");

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                pw = rs.getString("password");
            }
            stmt.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return pw;
    }
}
