package com.ilp.controller.cashier;

import com.ilp.controller.CashierStarter;
import com.ilp.dao.AccountDao;
import com.ilp.dao.TransactionDao;
import com.ilp.entity.Account;
import com.ilp.util.MenuUtil;

import java.util.Scanner;

public class DepositController {

    public static void deposit(Scanner sc) {
        System.out.print("Enter Account Number: \t");
        long accout_no = sc.nextLong();
        Account account = AccountDao.getInst().getAccount(accout_no);
        if (account.getAccount_id() == null) {
            System.out.println("Account Details not found!\n\n");
            retry(sc);
        } else {
            System.out.print("Enter Amount Deposit: \t");
            double amount = sc.nextDouble();
            double balance = account.getBalance() + amount;
            AccountDao.getInst().updateAccountAmount(account.getCustomer_id(), balance);
            TransactionDao.getInst().saveTransaction(account, amount);
            System.out.println("Deposit was Successful.");
            retry(sc);
        }
    }

    private static void retry(Scanner sc) {
        System.out.println();
        System.out.println(
                "1. Another Deposit\n" +
                        "2. Goto Menu\n");
        int repl = sc.nextInt();

        switch (repl) {
            case 1:
                deposit(sc);
                break;
            case 2:
                MenuUtil.showCashierMenu();
                CashierStarter.run(sc);
                break;
        }
    }

}
