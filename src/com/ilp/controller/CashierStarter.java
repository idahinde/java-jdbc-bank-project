package com.ilp.controller;

import com.ilp.controller.cashier.*;
import com.ilp.util.MenuUtil;

import java.util.Scanner;

public class CashierStarter {

    public final static void run(Scanner sc) {
        MenuUtil.showCashierMenu();
        int n = sc.nextInt();
        switch (n) {
            case 1:
                AccountController.getAccount(sc);
                break;
            case 2:
                WithdrawalController.withdrawCash(sc);
                break;
            case 3:
                DepositController.deposit(sc);
                break;
            case 4:
                TransferController.transferFound(sc);
                break;
            case 5:
                ViewAccountStatementController.viewStatement(sc);
                break;
            case 0:
                LoginController.start(sc);
                break;
        }
    }

}
