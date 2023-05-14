/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import contants.CustomerType;

/**
 *
 * @author HP
 */
public class Customer {
    private int customerId;
    private String name;
    private String email;
    private String contact;
    private String address;
    private CustomerType customerType;

    public Customer() {
    }

    public Customer(int customerId, String name, String email, String contact, String address, CustomerType customerType) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.address = address;
        this.customerType = customerType;
    }

    // Getters and setters for all fields

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }
}

