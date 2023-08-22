package com.ilp.config;

import java.io.File;

public class DBConfig {

    public static final String DERBY_PATH = System.getProperty("user.home") + File.separator + "cashier-derby";
    public static final String CONNECTION_URL = "jdbc:derby:" + DERBY_PATH + File.separator + "dbbankdata;create=true";

    public static final String LOGIN_TABLE = "tbllogin";
    public static final String ACCOUNT_TABLE = "tblaccount";
    public static final String CUSTOMER_TABLE = "tblcustomer";
    public static final String TRANSACTION_TABLE = "tbltransaction";

    public static final String CREATE_ACCOUNT_TABLE = "CREATE TABLE " + ACCOUNT_TABLE
            + " (ws_acct_id VARCHAR(11) NOT NULL, ws_cust_id VARCHAR(11) NOT NULL,"
            + "ws_acct_type VARCHAR(3) NOT NULL, ws_acct_balance VARCHAR(17) NOT NULL, ws_acct_crdate DATE NOT NULL,"
            + "ws_acct_last_trxn_date DATE NOT NULL, ws_acct_duration INT NOT NULL, PRIMARY KEY(ws_acct_id))";

    public static final String CREATE_TRANSACTION_TABLE = "CREATE TABLE " + TRANSACTION_TABLE
            + " (ws_trxn_id INT GENERATED ALWAYS AS IDENTITY,"
            + "ws_cust_id VARCHAR(11) NOT NULL, ws_accnt_type VARCHAR(3) NOT NULL, ws_amt VARCHAR(17) NOT NULL,"
            + "ws_trxn_date DATE NOT NULL, ws_src_typ VARCHAR(3) NOT NULL, ws_tgt_typ VARCHAR(3) NOT NULL)";

    public static final String CREATE_LOGIN_TABLE = "CREATE TABLE " + LOGIN_TABLE
            + " (ws_account_id INT GENERATED ALWAYS AS IDENTITY, ws_username VARCHAR(115) NOT NULL," +
            " ws_password VARCHAR(115) NOT NULL)";

    public static final String CREATE_CUSTOMER_TABLE = "CREATE TABLE " + CUSTOMER_TABLE
            + " (ws_cst_ssnid VARCHAR(11) NOT NULL, ws_cst_first_name VARCHAR(55) NOT NULL, ws_cst_last_name VARCHAR(55) NOT NULL," +
            " ws_cst_age INT NOT NULL, ws_cst_address VARCHAR(155) NOT NULL, PRIMARY KEY (ws_cst_ssnid) )";

}
