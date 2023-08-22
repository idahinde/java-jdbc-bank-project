package com.ilp.dao;

import com.ilp.config.DBConfig;
import com.ilp.config.DBConnection;
import com.ilp.entity.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {

    private CustomerDao() {
    }

    public final static CustomerDao getInst() {
        return CustomerDaoInit.Inst;
    }

    public boolean save(Customer customer) {
        try {

            String sql = "INSERT INTO " + DBConfig.CUSTOMER_TABLE +
                    "(ws_cst_ssnid, ws_cst_first_name, ws_cst_last_name, ws_cst_age, ws_cst_address)" +
                    " VALUES (?,?,?,?,?) ";
            PreparedStatement prmt = DBConnection.getInstance().getConn().prepareStatement(sql);

            prmt.setLong(1, customer.getSsnId());
            prmt.setString(2, customer.getFirstName());
            prmt.setString(3, customer.getLastName());
            prmt.setInt(4, customer.getAge());
            prmt.setString(5, customer.getAddress());

            prmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Customer getCustomer(Long customer_id) {
        try {

            String sql = "select c.* from " + DBConfig.CUSTOMER_TABLE + " c where c.ws_cst_ssnid='" + customer_id + "' ";
            Statement stmt = DBConnection.getInstance().getConn().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            Customer customer = new Customer();
            while (rs.next()) {
                customer.setSsnId(rs.getLong("ws_cst_ssnid"));
                customer.setFirstName(rs.getString("ws_cst_first_name"));
                customer.setLastName(rs.getString("ws_cst_last_name"));
                customer.setAge(rs.getInt("ws_cst_age"));
                customer.setAddress(rs.getString("ws_cst_address"));
            }
            return customer;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean updateCustomer(Customer customer) {

        try {

            String sql = "UPDATE " + DBConfig.CUSTOMER_TABLE + " SET ws_cst_first_name=?, ws_cst_last_name=?, " +
                    "ws_cst_age=?, ws_cst_address=? WHERE ws_cst_ssnid=? ";

            PreparedStatement prsmt = DBConnection.getInstance().getConn().prepareStatement(sql);
            prsmt.setString(1, customer.getFirstName());
            prsmt.setString(2, customer.getLastName());
            prsmt.setInt(3, customer.getAge());
            prsmt.setString(4, customer.getAddress());
            prsmt.setLong(5, customer.getSsnId());

            prsmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public List<Customer> getCustomerList() {
        List<Customer> customerList = new ArrayList<>();
        try {

            String sql = "select c.* from " + DBConfig.CUSTOMER_TABLE + " c ";
            Statement stmt = DBConnection.getInstance().getConn().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setSsnId(rs.getLong("ws_cst_ssnid"));
                customer.setFirstName(rs.getString("ws_cst_first_name"));
                customer.setLastName(rs.getString("ws_cst_last_name"));
                customer.setAge(rs.getInt("ws_cst_age"));
                customer.setAddress(rs.getString("ws_cst_address"));
                customerList.add(customer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerList;
    }

    public boolean deleteCustomer(long customer_id) {

        try {
            Statement stmt = DBConnection.getInstance().getConn().createStatement();
            stmt.executeUpdate("delete from " + DBConfig.CUSTOMER_TABLE + " where ws_cst_ssnid='" + customer_id + "' ");
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private static final class CustomerDaoInit {
        private static final CustomerDao Inst = new CustomerDao();
    }

}
