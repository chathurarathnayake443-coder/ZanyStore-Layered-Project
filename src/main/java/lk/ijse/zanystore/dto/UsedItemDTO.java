/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.zanystore.dto;

/**
 *
 * @author chathura
 */
public class UsedItemDTO {
    private String customer_name;
    private String item_name;
    private double amount;
    private String date;
    private String color;
    private int qty;

    public UsedItemDTO(String customer_name, String item_name, double amount, String date, String color, int qty) {
        this.customer_name = customer_name;
        this.item_name = item_name;
        this.amount = amount;
        this.date = date;
        this.color = color;
        this.qty = qty;
    }

    public UsedItemDTO(String item_name, String color, int qty) {
        this.item_name = item_name;
        this.color = color;
        this.qty = qty;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "UsedItemDTO{" + "customer_name=" + customer_name + ", item_name=" + item_name + ", amount=" + amount + ", date=" + date + ", color=" + color + ", qty=" + qty + '}';
    }
    
    
}
