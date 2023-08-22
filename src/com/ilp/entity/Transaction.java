package com.ilp.entity;

import java.sql.Date;

public class Transaction {

    private Integer transaction_id;
    private String customer_id;
    private String account_type;
    private Double amount;
    private Date transaction_date;
    private String source_account_type;
    private String target_account_type;

    public Integer getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(Integer transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String account_id) {
        this.customer_id = account_id;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(Date transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getSource_account_type() {
        return source_account_type;
    }

    public void setSource_account_type(String source_account_type) {
        this.source_account_type = source_account_type;
    }

    public String getTarget_account_type() {
        return target_account_type;
    }

    public void setTarget_account_type(String target_account_type) {
        this.target_account_type = target_account_type;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Transaction [customer_id=");
        builder.append(customer_id);
        builder.append(", account_type=");
        builder.append(account_type);
        builder.append(", amount=");
        builder.append(amount);
        builder.append(", transaction_date=");
        builder.append(transaction_date);
        builder.append(", source_account_type=");
        builder.append(source_account_type);
        builder.append(", target_account_type=");
        builder.append(target_account_type);
        builder.append("]");
        return builder.toString();
    }

}
