/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.zanystore.dto;

/**
 *
 * @author chathura
 */
public class LowStockDTO {
    private int item_id;
    private String item_name;
    private String color;
    private int qty;

    public LowStockDTO(int item_id, String item_name, String color, int qty) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.color = color;
        this.qty = qty;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
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
        return "LowStockDTO{" + "item_id=" + item_id + ", item_name=" + item_name + ", color=" + color + ", qty=" + qty + '}';
    }
    
    
}
