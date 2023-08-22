package com.ilp.dao;

import com.ilp.config.DBConfig;
import com.ilp.config.DBConnection;
import com.ilp.entity.User;

import java.sql.ResultSet;
import java.sql.Statement;

public class UserDao {

    private UserDao() {
    }

    public final static UserDao getInst() {
        return LoginDaoInit.Inst;
    }

    public boolean save(User user) {
        try {

            Statement stmt = DBConnection.getInstance().getConn().createStatement();
            StringBuffer sb = new StringBuffer("INSERT INTO ").append(DBConfig.LOGIN_TABLE).append(" (ws_username, ws_password) VALUES(");
            sb.append("'").append(user.getUsername()).append("',");
            sb.append("'").append(user.getPassword()).append("')");

            stmt.executeUpdate(sb.toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getUser(String username, String password) {
        try {

            Statement stmt = DBConnection.getInstance().getConn().createStatement();
            final String sql = "SELECT u.* FROM " + DBConfig.LOGIN_TABLE + " u WHERE u.ws_username='" + username + "' AND u.ws_password='" + password + "' ";
            ResultSet rs = stmt.executeQuery(sql);
            User user = new User();
            while (rs.next()) {
                user.setUserId(rs.getInt("ws_account_id"));
                user.setUsername(rs.getString("ws_username"));
                user.setPassword(rs.getString("ws_password"));
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final class LoginDaoInit {
        private static final UserDao Inst = new UserDao();
    }

}
