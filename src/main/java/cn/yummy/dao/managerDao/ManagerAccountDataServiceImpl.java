package cn.yummy.dao.managerDao;

import cn.yummy.dao.mysql.MySQLConnector;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
@Service
public class ManagerAccountDataServiceImpl implements  ManagerAccountDataService {
    private Connection conn;
    @Override
    public String getPassword(String account) {
        String password = "";
        PreparedStatement stmt;
        String sql;
        conn = new MySQLConnector().getConnection("Yummy");
        try{
            sql = "select password from manager where account=?";

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
}
