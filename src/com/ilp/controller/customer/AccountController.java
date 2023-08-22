package com.ilp.controller.customer;

import com.ilp.controller.CustomerStarter;
import com.ilp.dao.AccountDao;
import com.ilp.entity.Account;

import java.util.List;
import java.util.Scanner;

public class AccountController {

    public static void getAllAccounts(Scanner sc) {

        List<Account> accounts = AccountDao.getInst().getAccounts();
        String format = "| %-15s | %-15s | %-13s | %-13s | %-23s | %-23s |%n";
        System.out.println("|-----------------+-----------------+---------------+---------------+-------------------------+-------------------------+");
        System.out.format("| %-117s |%n"," Account Details List ");
        System.out.println("|-----------------+-----------------+---------------+---------------+-------------------------+-------------------------+");
        System.out.format(format, "Account Number", "Customer SSNID", "Account Type", "Balance", "Created Date", "Last TRXN Date");
        System.out.println("|-----------------+-----------------+---------------+---------------+-------------------------+-------------------------+");
        for (Account a : accounts) {
            System.out.format(format, a.getAccount_id().toString(), a.getCustomer_id().toString(),
                    a.getBalance().toString(), a.getAccount_type(), a.getCreated_date().toString(),
                    a.getLast_transaction_date().toString());
        }
        System.out.println("|-----------------+-----------------+---------------+---------------+-------------------------+-------------------------+");
        CustomerStarter.startFresh(sc);
    }

    public static void getAccount(Scanner sc) {
        System.out.print("Enter Account Number: \t");
        long accout_no = sc.nextLong();
        Account account = AccountDao.getInst().getAccount(accout_no);
        if (account.getAccount_id() == null) {
            System.out.println("Account Details not found!");
            CustomerStarter.startFresh(sc);
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
            CustomerStarter.startFresh(sc);
        }
    }

    public static void addAccount(Scanner sc) {
        System.out.print("Enter Customer SSDIN: \t");
        long ssnid = sc.nextLong();
        System.out.print("Enter Account Number: \t");
        long account_no = sc.nextLong();
        System.out.print("Enter Account Type(S or C): \t");
        String account_type = sc.next();

        Account account = new Account(ssnid, account_no, account_type);
        AccountDao.getInst().save(account);
        System.out.println("Account Was Successfully Created.");
        CustomerStarter.startFresh(sc);
    }

    public static void deleteAccount(Scanner sc) {
        System.out.print("Enter Customer SSNID:  \t");
        long account_id = sc.nextLong();
        Account account = AccountDao.getInst().getAccount(account_id);
        if (account.getAccount_id() > 0) {
            boolean re = AccountDao.getInst().deleteAccount(account_id);
            if (re)
                System.out.println("Account Was Successfully Updated.");
            else
                System.out.println("Unable to Update Account.");
        } else {
            System.out.println("Invalid Account Number, Account not found");
        }
        CustomerStarter.startFresh(sc);
    }

}
