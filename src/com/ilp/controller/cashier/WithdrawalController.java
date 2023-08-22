package com.ilp.controller.cashier;

import com.ilp.controller.CashierStarter;
import com.ilp.dao.AccountDao;
import com.ilp.dao.TransactionDao;
import com.ilp.entity.Account;
import com.ilp.util.MenuUtil;

import java.util.Scanner;

public class WithdrawalController {

    public static void withdrawCash(Scanner sc) {
        System.out.print("Enter Account Number: \t");
        long accout_no = sc.nextLong();
        Account account = AccountDao.getInst().getAccount(accout_no);
        if (account.getAccount_id() == null) {
            System.out.println("Account Details not found!\n\n");
            retry(sc);
        } else {
            System.out.print("Enter Amount to Withdraw: \t");
            double amount = sc.nextDouble();
            if (amount > account.getBalance()) {
                System.out.print("Insufficient Found: \t");
                retry(sc);
            } else {
                double balance = account.getBalance() - amount;
                AccountDao.getInst().updateAccountAmount(account.getCustomer_id(), balance);
                TransactionDao.getInst().saveTransaction(account, amount);
                System.out.println("Withdrawal was Successful.");
                retry(sc);
            }
        }
    }

    private static void retry(Scanner sc) {
        System.out.println();
        System.out.println(
                "1. Another Withdrawal\n" +
                        "2. Goto Menu\n");
        int repl = sc.nextInt();

        switch (repl) {
            case 1:
                withdrawCash(sc);
                break;
            case 2:
                MenuUtil.showCashierMenu();
                CashierStarter.run(sc);
                break;
        }
    }

}
