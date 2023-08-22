package com.ilp.config;

import com.ilp.dao.UserDao;
import com.ilp.entity.User;

import java.sql.*;

public class DBConnection {

    private Connection conn = null;

    private DBConnection() {
        this.openConn();
    }

    public static DBConnection getInstance() {
        return DBConnectionInit.Instance;
    }

    private void openConn() {
        try {
            conn = DriverManager.getConnection(DBConfig.CONNECTION_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initializeTables() throws SQLException {
        Statement stmt = conn.createStatement();
        if (!isTableExist(DBConfig.ACCOUNT_TABLE))
            stmt.executeUpdate(DBConfig.CREATE_ACCOUNT_TABLE);

        if (!isTableExist(DBConfig.LOGIN_TABLE)) {
            stmt.executeUpdate(DBConfig.CREATE_LOGIN_TABLE);
            UserDao.getInst().save(new User("sanchi680", "1234sanchi"));
        }

        if (!isTableExist(DBConfig.CUSTOMER_TABLE))
            stmt.executeUpdate(DBConfig.CREATE_CUSTOMER_TABLE);

        if (!isTableExist(DBConfig.TRANSACTION_TABLE))
            stmt.executeUpdate(DBConfig.CREATE_TRANSACTION_TABLE);
    }

    public Connection getConn() {
        return conn;
    }

    private boolean isTableExist(String sTablename) throws SQLException {
        DatabaseMetaData dbmd = conn.getMetaData();
        ResultSet rs = dbmd.getTables(null, null, sTablename.toUpperCase(), null);
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }

    private static final class DBConnectionInit {
        private static final DBConnection Instance = new DBConnection();
    }

}
