package com.ilp.dao;

import com.ilp.config.DBConfig;
import com.ilp.config.DBConnection;
import com.ilp.entity.Account;
import com.ilp.entity.Transaction;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao {

    private TransactionDao() {
    }

    public final static TransactionDao getInst() {
        return TransactionDaoInit.Inst;
    }

    public void saveTransaction(Account account, double amount) {
        try {

            Date date = Date.valueOf(LocalDate.now());
            Statement stmt = DBConnection.getInstance().getConn().createStatement();
            final String sql = "INSERT INTO " + DBConfig.TRANSACTION_TABLE +
                    "(ws_cust_id,ws_accnt_type,ws_amt,ws_trxn_date,ws_src_typ,ws_tgt_typ) VALUES ('" + account.getCustomer_id() + "'," +
                    "'" + account.getAccount_type() + "','" + amount + "','" + date + "'," +
                    "'" + account.getAccount_type() + "','" + account.getAccount_type() + "')";
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Transaction> getTransaction(long customer_id) {
        List<Transaction> transaction_list = new ArrayList<>();
        try {

            Statement stmt = DBConnection.getInstance().getConn().createStatement();
            String sql = "SELECT t.* FROM " + DBConfig.TRANSACTION_TABLE + " t WHERE t.ws_cust_id='" + customer_id + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                Transaction transaction = new Transaction();
                transaction.setTransaction_id(rs.getInt("ws_trxn_id"));
                transaction.setCustomer_id(rs.getString("ws_cust_id"));
                transaction.setAccount_type(rs.getString("ws_accnt_type"));
                transaction.setAmount(rs.getDouble("ws_amt"));
                transaction.setTransaction_date(rs.getDate("ws_trxn_date"));
                transaction.setSource_account_type(rs.getString("ws_src_typ"));
                transaction.setTarget_account_type(rs.getString("ws_tgt_typ"));

                transaction_list.add(transaction);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transaction_list;
    }

    private static final class TransactionDaoInit {
        private static final TransactionDao Inst = new TransactionDao();
    }
}
