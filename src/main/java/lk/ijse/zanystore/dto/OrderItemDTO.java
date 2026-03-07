/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.zanystore.dto;

/**
 *
 * @author chathura
 */
public class OrderItemDTO {
    private int orderId;
    private String item_name;
    private int item_id;
    private String color;
    private int qty;
    private double price;

    public OrderItemDTO(int item_id, String color, int qty) {
        this.item_id = item_id;
        this.color = color;
        this.qty = qty;
    }

    public OrderItemDTO(int orderId, int item_id, int qty, String color) {
        this.orderId = orderId;
        this.item_id = item_id;
        this.color = color;
        this.qty = qty;
    }

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public OrderItemDTO(String item_name, int item_id, String color, int qty, double price) {
        this.item_name = item_name;
        this.item_id = item_id;
        this.color = color;
        this.qty = qty;
        this.price = price;
    }
    
    

    public OrderItemDTO(String item_name, String color, int qty) {
        this.item_name = item_name;
        this.color = color;
        this.qty = qty;
    }

    public String getItem_name() {
        return item_name;
    }

    public OrderItemDTO(String item_name, int item_id, String color, int qty) {
        this.item_name = item_name;
        this.item_id = item_id;
        this.color = color;
        this.qty = qty;
    }
    
    

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
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
        return "OrderItemDTO{" + "itemName=" + item_name + ", item_id=" + item_id + ", color=" + color + ", qty=" + qty + '}';
    }
  
}
