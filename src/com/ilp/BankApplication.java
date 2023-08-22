package com.ilp;

import com.ilp.config.DBConfig;
import com.ilp.config.DBConnection;
import com.ilp.controller.LoginController;
import com.ilp.util.MenuUtil;

import java.io.File;
import java.sql.SQLException;
import java.util.Scanner;

public class BankApplication {

    public static void main(String[] args) throws SQLException {
        initialize();
        MenuUtil.WelcomeMenu();
        Scanner sc = new Scanner(System.in);
        while (true) {
            LoginController.loginPerform(sc);
        }
    }

    private static void initialize() throws SQLException {
        File file = new File(DBConfig.DERBY_PATH);
        if (!file.exists()) {
            file.mkdir();
        }
        DBConnection.getInstance().initializeTables();
    }

}
