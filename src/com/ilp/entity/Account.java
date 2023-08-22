package com.ilp.entity;

import java.sql.Date;

public class Account {

    private Long customer_id;
    private Long account_id;
    private String account_type;
    private Double balance;
    private Date created_date;
    private Date last_transaction_date;
    private Integer duration;

    public Account() {
    }

    public Account(Long customer_id, Long account_id, String account_type) {
        this.customer_id = customer_id;
        this.account_id = account_id;
        this.account_type = account_type;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getLast_transaction_date() {
        return last_transaction_date;
    }

    public void setLast_transaction_date(Date last_transaction_date) {
        this.last_transaction_date = last_transaction_date;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Account [customer_id=");
        builder.append(customer_id);
        builder.append(", account_id=");
        builder.append(account_id);
        builder.append(", account_type=");
        builder.append(account_type);
        builder.append(", balance=");
        builder.append(balance);
        builder.append(", created_date=");
        builder.append(created_date);
        builder.append(", last_transaction_date=");
        builder.append(last_transaction_date);
        builder.append(", duration=");
        builder.append(duration);
        builder.append("]");
        return builder.toString();
    }

}
