package com.ilp.controller.cashier;

import com.ilp.controller.CashierStarter;
import com.ilp.dao.AccountDao;
import com.ilp.dao.TransactionDao;
import com.ilp.entity.Account;
import com.ilp.entity.Transaction;
import com.ilp.util.MenuUtil;

import java.util.List;
import java.util.Scanner;

public class ViewAccountStatementController {

    public static void viewStatement(Scanner sc) {
        System.out.print("Enter Account Number: \t");
        long accout_no = sc.nextLong();
        Account account = AccountDao.getInst().getAccount(accout_no);
        if (account.getAccount_id() == null) {
            System.out.println("Account Details not found!");
            retry(sc);
        } else {
            List<Transaction> transactionList = TransactionDao.getInst().getTransaction(account.getCustomer_id());
            String format = "| %-5d | %-12s | %-12s | %-12s | %-14s | %-13s | %-14s |%n";
            System.out.format("+-------+--------------+--------------+--------------+----------------+---------------+----------------+%n");
            System.out.format("| Account Statement: %-10s                                                                        |%n", account.getAccount_id());
            System.out.format("+-------+--------------+--------------+--------------+----------------+---------------+----------------+%n");
            System.out.format("| S/N   | CUSTOMER ID  | ACCOUNT TYPE | AMOUNT       | TRXN DATE      | SRC ACCT TYPE | TGT ACCT TYPE  |%n");
            System.out.format("+-------+--------------+--------------+--------------+----------------+---------------+----------------+%n");

            int s = 1;
            for (Transaction trxn : transactionList) {
                System.out.format(format, s, trxn.getCustomer_id(), trxn.getAccount_type(), trxn.getAmount().toString(),
                        trxn.getTransaction_date().toString(), trxn.getSource_account_type(), trxn.getTarget_account_type());
                s++;
            }
            System.out.format("+-------+--------------+--------------+--------------+----------------+---------------+----------------+%n");
            retry(sc);
        }
    }

    private static void retry(Scanner sc) {
        System.out.println();
        System.out.println("1. Retry\n" +
                "2. Goto Menu\n");
        int repl = sc.nextInt();

        switch (repl) {
            case 1:
                viewStatement(sc);
                break;
            case 2:
                MenuUtil.showCashierMenu();
                CashierStarter.run(sc);
                break;
        }
    }

}
