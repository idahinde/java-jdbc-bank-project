package com.ilp.controller.cashier;

import com.ilp.controller.CashierStarter;
import com.ilp.dao.AccountDao;
import com.ilp.dao.TransactionDao;
import com.ilp.entity.Account;
import com.ilp.util.MenuUtil;

import java.util.Scanner;

public class TransferController {

    public static void transferFound(Scanner sc) {
        System.out.print("Enter Source Account Number: \t");
        long src_account_no = sc.nextLong();
        Account src_account = AccountDao.getInst().getAccount(src_account_no);
        if (src_account.getAccount_id() == null) {
            System.out.println("Source Account Details not found!\n");
            retry(sc);
        } else {
            System.out.print("Enter Target Account Number: \t");
            long target_account_no = sc.nextLong();
            Account target_accountSrc = AccountDao.getInst().getAccount(target_account_no);
            if (target_accountSrc.getAccount_id() == null) {
                System.out.println("Source Account Details not found!");
                retry(sc);
            } else {
                System.out.print("Enter Amount to Transfer: \t");
                double amount = sc.nextDouble();
                if (amount > src_account.getBalance()) {
                    System.out.print("Insufficient Found: \t");
                    retry(sc);
                } else {
                    double src_balance = src_account.getBalance() - amount;
                    double target_balance = target_accountSrc.getBalance() + amount;
                    AccountDao.getInst().updateAccountAmount(src_account.getCustomer_id(), src_balance);
                    AccountDao.getInst().updateAccountAmount(target_accountSrc.getCustomer_id(), target_balance);

                    TransactionDao.getInst().saveTransaction(src_account, amount);
                    TransactionDao.getInst().saveTransaction(target_accountSrc, amount);
                    System.out.println("Transfer was Successful.");
                    retry(sc);
                }
            }
        }
    }

    private static void retry(Scanner sc) {
        System.out.println();
        System.out.println(
                "1. Another Transfer\n" +
                        "2. Goto Menu\n");
        int repl = sc.nextInt();

        switch (repl) {
            case 1:
                transferFound(sc);
                break;
            case 2:
                MenuUtil.showCashierMenu();
                CashierStarter.run(sc);
                break;
        }
    }
}
