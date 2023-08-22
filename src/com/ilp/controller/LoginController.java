package com.ilp.controller;

import com.ilp.dao.UserDao;
import com.ilp.entity.User;
import com.ilp.util.MenuUtil;

import java.util.Scanner;

public class LoginController {

    public static void loginPerform(Scanner sc) {
        System.out.print("Enter Username:\t");
        String username = sc.next();
        System.out.print("Enter Password:\t");
        String password = sc.next();

        User user = UserDao.getInst().getUser(username, password);
        if (user.getUserId() > 0) {
            start(sc);
        } else {
            System.out.println("Invalid Username Or Password!");
            System.out.println("\n");
            retry(sc);
        }
    }

    public static void start(Scanner sc) {
        MenuUtil.mainMenu();

        int n = sc.nextInt();
        switch (n) {
            case 1:
                CustomerStarter.run(sc);
                break;
            case 2:
                CashierStarter.run(sc);
                break;
            case 3:
                MenuUtil.WelcomeMenu();
                loginPerform(sc);
                break;
            case 0:
                System.exit(0);
                break;
        }
    }

    private static final void retry(Scanner sc) {

        System.out.println("---------- RETRY ---------");
        System.out.println("1. Login Again \n" +
                "2. Exist Application\n");

        int n = sc.nextInt();
        switch (n) {
            case 1:
                loginPerform(sc);
                break;
            case 2:
                System.exit(0);
                break;
        }
    }

}
