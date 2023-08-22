package com.ilp.controller.cashier;

import com.ilp.controller.CashierStarter;
import com.ilp.dao.AccountDao;
import com.ilp.entity.Account;
import com.ilp.util.MenuUtil;

import java.util.Scanner;

public class AccountController {

    public static void getAccount(Scanner sc) {
        System.out.print("Enter Account Number: \t");
        long accout_no = sc.nextLong();
        Account account = AccountDao.getInst().getAccount(accout_no);
        if (account.getAccount_id() == null) {
            System.out.println("Account Details not found!");
        } else {
            String format = "| %-17s | %-17s |%n";
            System.out.format("+-------------------+-------------------+%n");
            System.out.format("| Column            | Value             |%n");
            System.out.format("+-------------------+-------------------+%n");
            System.out.format(format, "Customer ID", account.getCustomer_id());
            System.out.format(format, "Account ID", account.getAccount_id());
            System.out.format(format, "Account Type", account.getAccount_type());
            System.out.format(format, "Balance", account.getBalance().toString());
            System.out.format(format, "Created Date", account.getCreated_date());
            System.out.format(format, "Last Trxn Date", account.getLast_transaction_date());
            System.out.format(format, "Duration", account.getDuration().toString());
            System.out.format("+-------------------+-------------------+%n");
        }
        retry(sc);
    }

    private static void retry(Scanner sc) {
        System.out.println();
        System.out.println("1. Retry\n" +
                "2. Goto Menu\n");
        int repl = sc.nextInt();

        switch (repl) {
            case 1:
                getAccount(sc);
                break;
            case 2:
                MenuUtil.showCashierMenu();
                CashierStarter.run(sc);
                break;
        }
    }

}
