/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.zanystore.dto;

/**
 *
 * @author chathura
 */
public class ItemDTO {
    private int item_id;
    private int color_stock_id;
    private String item_name;
    private String item_type;
    private String color;
    private Double item_unit_price;
    private int qty;

    public ItemDTO() {
    }

    public ItemDTO(int item_id, String item_name, String item_type, String color, Double item_unit_price, int qty) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_type = item_type;
        this.color = color;
        this.item_unit_price = item_unit_price;
        this.qty = qty;
    }

    public ItemDTO(String color) {
        this.color = color;
    }

    public ItemDTO(int item_id, int color_stock_id, String item_name, String item_type, String color, Double item_unit_price, int qty) {
        this.item_id = item_id;
        this.color_stock_id = color_stock_id;
        this.item_name = item_name;
        this.item_type = item_type;
        this.color = color;
        this.item_unit_price = item_unit_price;
        this.qty = qty;
    }

    public ItemDTO(int item_id, String item_name, String color, Double item_unit_price, int qty) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.color = color;
        this.item_unit_price = item_unit_price;
        this.qty = qty;
    }

    public ItemDTO(String name, String type, double price) {
        this.item_name = name;
        this.item_type = type;
        this.item_unit_price = price;
    }

    public ItemDTO(int itemId) {
        this.item_id = itemId;
    }


    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getColor_stock_id() {
        return color_stock_id;
    }

    public void setColor_stock_id(int color_stock_id) {
        this.color_stock_id = color_stock_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getItem_unit_price() {
        return item_unit_price;
    }

    public void setItem_unit_price(Double item_unit_price) {
        this.item_unit_price = item_unit_price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "ItemDTO{" + "item_id=" + item_id + ", color_stock_id=" + color_stock_id + ", item_name=" + item_name + ", item_type=" + item_type + ", color=" + color + ", item_unit_price=" + item_unit_price + ", qty=" + qty + '}';
    }

//    private int item_id;
//    private String item_name;
//    private String item_type;
//    private Double item_unit_price;
//
//    public ItemDTO() {}
//
//    public ItemDTO(int item_id, String item_name, String item_type, Double item_unit_price) {
//        this.item_id = item_id;
//        this.item_name = item_name;
//        this.item_type = item_type;
//        this.item_unit_price = item_unit_price;
//    }
//    public int getItem_id() {
//        return item_id;
//    }
//    public void setItem_id(int item_id) {
//        this.item_id = item_id;
//    }
//    public String getItem_name() {
//        return item_name;
//    }
//    public void setItem_name(String item_name) {
//        this.item_name = item_name;
//    }
//    public String getItem_type() {
//        return item_type;
//    }
//    public void setItem_type(String item_type) {
//        this.item_type = item_type;
//    }
//    public Double getItem_unit_price() {
//        return item_unit_price;
//    }
//    public void setItem_unit_price(Double item_unit_price) {
//        this.item_unit_price = item_unit_price;
//    }
//    @Override
//    public String toString() {
//        return "";
//    }
//
}
