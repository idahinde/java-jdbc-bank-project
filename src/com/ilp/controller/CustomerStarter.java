package com.ilp.controller;

import com.ilp.controller.customer.AccountController;
import com.ilp.controller.customer.CustomerController;
import com.ilp.util.MenuUtil;

import java.util.Scanner;

public class CustomerStarter {

    public static final void run(Scanner sc) {
        MenuUtil.showCustomerMenu();
        int n = sc.nextInt();
        switch (n) {
            case 0:
                LoginController.start(sc);
                break;
            case 1:
                CustomerController.printAllCustomer(sc);
                break;
            case 2:
                CustomerController.getCustomerDetail(sc);
                break;
            case 3:
                CustomerController.addCustomerDetails(sc);
                break;
            case 4:
                CustomerController.updateCustomerDetails(sc);
                break;
            case 5:
                CustomerController.deleteCustomerAccount(sc);
                break;
            case 6:
                AccountController.getAllAccounts(sc);
                break;
            case 7:
                AccountController.getAccount(sc);
                break;
            case 8:
                AccountController.addAccount(sc);
                break;
            case 9:
                AccountController.deleteAccount(sc);
                break;
            default:
                startFresh(sc);
                break;
        }
    }

    public static void startFresh(Scanner sc) {
        System.out.println("1. Back \n" +
                "2. Main Menu \n" +
                "3. Exist Application\n");
        int n = sc.nextInt();
        switch (n) {
            case 1:
                run(sc);
                break;
            case 2:
                LoginController.start(sc);
                break;
            case 3:
                System.exit(0);
                break;

        }
    }

}
