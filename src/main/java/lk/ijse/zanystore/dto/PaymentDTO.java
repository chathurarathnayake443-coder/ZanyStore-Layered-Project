/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.zanystore.dto;

/**
 *
 * @author chathura
 */
public class PaymentDTO {
    private int paymentId;
    private int orderId;
    private String name;
    private double amount;
    private String method;
    private String date;
    private int customerId;

    public PaymentDTO(double amount, String method) {
        this.amount = amount;
        this.method = method;
    }

    public PaymentDTO(int orderId, int paymentId, int customerId, String date) {
        this.orderId = orderId;
        this.paymentId = paymentId;
        this.customerId = customerId;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PaymentDTO(int paymentId, int orderId, String name, double amount, String date) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.name = name;
        this.amount = amount;
        this.date = date;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" + "paymentId=" + paymentId + ", orderId=" + orderId + ", customerId=" + customerId + ", amount=" + amount + ", method=" + method + ", date=" + date + '}';
    }

    
    
}
