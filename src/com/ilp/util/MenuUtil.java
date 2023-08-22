package com.ilp.util;

public class MenuUtil {

    public static void WelcomeMenu() {
        System.out.println("------------ WELCOME ABC BANK --------- \n");
        System.out.println("------------ Login to Start Your Session ----------------");
    }

    public static void mainMenu() {
        System.out.println("------------ WELCOME ABC BANK MAIN MENU --------- \n");
        System.out.println("1. Customer Executive \n" +
                "2. Cashier Menu \n" +
                "3. Logout \n" +
                "0. Exist Application");
    }

    public static void showCustomerMenu() {
        System.out.println("------------ WELCOME CUSTOMER EXECUTIVE OF ABC BANK --------- \n");
        System.out.println("1. Get All Customers \n" +
                "2. Get a Customer \n" +
                "3. Add Customer \n" +
                "4. Update Customer Details \n" +
                "5. Delete Customer \n" +
                "6. Get All Accounts \n" +
                "7. Get Account \n" +
                "8. Add Account \n" +
                "9. Delete Account \n" +
                "0. Back \n");
    }

    public static void showCashierMenu() {
        System.out.println("--------------- WELCOME CASHIER OF ABC BANK ---------------\n");
        System.out.println("1. Get Account \n"
                + "2. Withdraw Money From Account \n"
                + "3. Deposit Money Into Account \n"
                + "4. Transfer Money From Account \n"
                + "5. View Account Statements \n"
                + "0. Back ");
    }

}
