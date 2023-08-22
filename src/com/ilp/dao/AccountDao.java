package com.ilp.dao;

import com.ilp.config.DBConfig;
import com.ilp.config.DBConnection;
import com.ilp.entity.Account;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {

    private AccountDao() {
    }

    public final static AccountDao getInst() {
        return AccountDaoInit.Inst;
    }

    public boolean save(Account account) {
        try {

            Date date = Date.valueOf(LocalDate.now());
            String sql = "INSERT INTO " + DBConfig.ACCOUNT_TABLE +
                    "(ws_acct_id, ws_cust_id, ws_acct_type, ws_acct_balance, ws_acct_crdate, ws_acct_last_trxn_date, ws_acct_duration) " +
                    "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement prsmt = DBConnection.getInstance().getConn().prepareStatement(sql);
            prsmt.setLong(1, account.getAccount_id());
            prsmt.setLong(2, account.getCustomer_id());
            prsmt.setString(3, account.getAccount_type());
            prsmt.setString(4, "0.00");
            prsmt.setDate(5, date);
            prsmt.setDate(6, date);
            prsmt.setInt(7, 1);

            prsmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Account getAccount(long account_id) {

        try {

            Statement stmt = DBConnection.getInstance().getConn().createStatement();
            final String sql = "SELECT a.* FROM " + DBConfig.ACCOUNT_TABLE + " a WHERE a.ws_acct_id='" + account_id + "' ";
            ResultSet rs = stmt.executeQuery(sql);
            Account account = new Account();

            while (rs.next()) {
                account.setAccount_id(rs.getLong("ws_acct_id"));
                account.setCustomer_id(rs.getLong("ws_cust_id"));
                account.setAccount_type(rs.getString("ws_acct_type"));
                account.setBalance(rs.getDouble("ws_acct_balance"));
                account.setCreated_date(rs.getDate("ws_acct_crdate"));
                account.setLast_transaction_date(rs.getDate("ws_acct_last_trxn_date"));
                account.setDuration(rs.getInt("ws_acct_duration"));
            }
            return account;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Account> getAccounts() {
        List<Account> accountList = new ArrayList<>();
        try {

            Statement stmt = DBConnection.getInstance().getConn().createStatement();
            final String sql = "SELECT a.* FROM " + DBConfig.ACCOUNT_TABLE + " a ";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Account account = new Account();
                account.setAccount_id(rs.getLong("ws_acct_id"));
                account.setCustomer_id(rs.getLong("ws_cust_id"));
                account.setAccount_type(rs.getString("ws_acct_type"));
                account.setBalance(rs.getDouble("ws_acct_balance"));
                account.setCreated_date(rs.getDate("ws_acct_crdate"));
                account.setLast_transaction_date(rs.getDate("ws_acct_last_trxn_date"));
                account.setDuration(rs.getInt("ws_acct_duration"));
                accountList.add(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountList;
    }

    public void updateAccountAmount(long customer_id, double amount) {
        try {

            Date date = Date.valueOf(LocalDate.now());
            Statement stmt = DBConnection.getInstance().getConn().createStatement();
            String sql = "UPDATE " + DBConfig.ACCOUNT_TABLE + " a SET " +
                    "a.ws_acct_balance='" + amount + "', " +
                    "a.ws_acct_last_trxn_date='" + date + "'" +
                    " WHERE a.ws_cust_id='" + customer_id + "' ";

            stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean deleteAccount(long account_id) {
        try {
            Statement stmt = DBConnection.getInstance().getConn().createStatement();
            stmt.executeUpdate("delete from " + DBConfig.ACCOUNT_TABLE + " where ws_acct_id='" + account_id + "' ");
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private static final class AccountDaoInit {
        private static final AccountDao Inst = new AccountDao();
    }

}
