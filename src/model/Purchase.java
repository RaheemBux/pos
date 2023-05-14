/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import contants.PaymentType;
import contants.Unit;
import java.sql.Date;

/**
 *
 * @author HP
 */
public class Purchase {

    private int purchaseId;
    private Date purchaseDate;
    private int quantity;
    private Unit unit;
    private int price;
    private String purchaseNumber;
    private int amountPaid;
    private int amountRemaining;
    private int totalAmount;
    private PaymentType paymentType;
    private Customer customer;
    private Product product;
    
    public Purchase(){}

    public Purchase(int purchaseId, Date purchaseDate, int quantity, Unit unit, int price, String purchaseNumber, int amountPaid, int amountRemaining, int totalAmount, PaymentType paymentType, Customer customer, Product product) {
        this.purchaseId = purchaseId;
        this.purchaseDate = purchaseDate;
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
        this.purchaseNumber = purchaseNumber;
        this.amountPaid = amountPaid;
        this.amountRemaining = amountRemaining;
        this.totalAmount = totalAmount;
        this.paymentType = paymentType;
        this.customer = customer;
        this.product = product;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(String purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }

    public int getAmountRemaining() {
        return amountRemaining;
    }

    public void setAmountRemaining(int amountRemaining) {
        this.amountRemaining = amountRemaining;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
