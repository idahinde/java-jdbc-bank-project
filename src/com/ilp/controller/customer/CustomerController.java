package com.ilp.controller.customer;

import com.ilp.controller.CustomerStarter;
import com.ilp.dao.CustomerDao;
import com.ilp.entity.Customer;

import java.util.Scanner;

public class CustomerController {

    public static void printAllCustomer(Scanner sc) {

        String format = "| %-17s | %-45s | %-10s | %-55s |%n";
        System.out.println("|-------------------+-----------------------------------------------+------------+---------------------------------------------------------|");
        System.out.format("|  %-135s |%n", " Customer Account List");
        System.out.println("|-------------------+-----------------------------------------------+------------+---------------------------------------------------------|");
        System.out.format(format, "Customer ID", "Customer Full Name", "Age", "Customer Address");
        System.out.println("|-------------------+-----------------------------------------------+------------+---------------------------------------------------------|");
        for (Customer c : CustomerDao.getInst().getCustomerList()) {
            System.out.format(format, c.getSsnId(), c.getFirstName() + " " + c.getLastName(), c.getAge(), c.getAddress());
        }
        System.out.println("|-------------------+-----------------------------------------------+------------+---------------------------------------------------------|");
        CustomerStarter.startFresh(sc);
    }

    public static void getCustomerDetail(Scanner sc) {
        System.out.print("Enter Customer SSNID: \t");
        long customer_id = sc.nextLong();
        Customer customer = CustomerDao.getInst().getCustomer(customer_id);
        if (customer.getSsnId() > 0) {
            String format = "| %-17s | %-55s |%n";
            System.out.format("+-------------------+-------------------+%n");
            System.out.format("| Column            | Value             |%n");
            System.out.format("+-------------------+-------------------+%n");
            System.out.format(format, "Customer SSNID", customer.getSsnId());
            System.out.format(format, "Customer Name", customer.getFirstName() + " " + customer.getLastName());
            System.out.format(format, "Age", customer.getAge());
            System.out.format(format, "Address", customer.getAddress());
            System.out.format("+-------------------+-------------------+%n");
        } else {
            System.out.println("Invalid SSNID, Customer Details not found");
        }
        CustomerStarter.startFresh(sc);
    }

    public static void addCustomerDetails(Scanner sc) {
        System.out.print("Enter Customer SSNID:  \t");
        long ssnid = sc.nextLong();
        System.out.print("Enter Customer First Name:\t");
        String fname = sc.next();
        System.out.print("Enter Customer Last Name:\t");
        String lname = sc.next();
        System.out.print("Enter Customer Age:\t");
        int age = sc.nextInt();
        System.out.print("Enter Customer Address:");
        String addr = sc.next();

        Customer customer = new Customer(ssnid, fname, lname, age, addr);
        boolean re = CustomerDao.getInst().save(customer);
        if (re)
            System.out.println("Customer Account Was Successfully Created.");
        else
            System.out.println("Unable to Create Customer Account.");
        CustomerStarter.startFresh(sc);
    }

    public static void updateCustomerDetails(Scanner sc) {
        System.out.print("Enter Customer SSNID:\t");
        long ssnid = sc.nextLong();
        Customer customer = CustomerDao.getInst().getCustomer(ssnid);
        if (customer.getSsnId() != null) {

            System.out.print("Enter Customer First Name:\t");
            String fname = sc.next();
            System.out.print("Enter Customer Last Name:\t");
            String lname = sc.next();
            System.out.print("Enter Customer Age:\t");
            int age = sc.nextInt();
            System.out.print("Enter Customer Address:");
            String addr = sc.next();

            customer = new Customer(ssnid, fname, lname, age, addr);
            boolean re = CustomerDao.getInst().updateCustomer(customer);
            if (re) {
                System.out.println("Customer Account Was Successfully Updated.");
            } else {
                System.out.println("Unable to Update Customer Account.");
            }
        } else {
            System.out.println("Customer Account Not Found.");
        }
        CustomerStarter.startFresh(sc);
    }

    public static void deleteCustomerAccount(Scanner sc) {
        System.out.print("Enter Customer SSNID:  \t");
        long ssnid = sc.nextLong();
        Customer customer = CustomerDao.getInst().getCustomer(ssnid);
        if (customer.getSsnId() > 0) {
            boolean re = CustomerDao.getInst().deleteCustomer(ssnid);
            if (re)
                System.out.println("Customer Account Was Successfully Updated.");
            else
                System.out.println("Unable to Update Customer Account.");
        } else {
            System.out.println("Invalid SSNID, Customer Details not found");
        }
        CustomerStarter.startFresh(sc);
    }

}
