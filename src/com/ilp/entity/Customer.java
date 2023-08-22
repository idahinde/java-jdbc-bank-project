package com.ilp.entity;

public class Customer {

    private Long ssnId;
    private String firstName;
    private String lastName;
    private int age;
    private String address;

    public Customer() {
    }

    public Customer(Long ssnId, String firstName, String lastName, int age, String address) {
        this.ssnId = ssnId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
    }

    public Long getSsnId() {
        return ssnId;
    }

    public void setSsnId(Long ssnId) {
        this.ssnId = ssnId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Customer{");
        sb.append("ssnId=").append(ssnId);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", age=").append(age);
        sb.append(", address='").append(address).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
