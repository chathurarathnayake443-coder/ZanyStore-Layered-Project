package lk.ijse.zanystore.entity;

public class OrderItem {
    private int order_item_id;
    private int cloth_order_id;
    private int item_id;
    private int qty;
    private double price;
    private String color;

    public OrderItem() {}

    public OrderItem(int cloth_order_id, int item_id, int qty, double price, String color){
        this.cloth_order_id = cloth_order_id;
        this.item_id = item_id;
        this.qty = qty;
        this.price = price;
        this.color = color;
    }

    public OrderItem(int order_item_id, int cloth_order_id, int item_id, int qty, double price, String color) {
        this.order_item_id = order_item_id;
        this.cloth_order_id = cloth_order_id;
        this.item_id = item_id;
        this.qty = qty;
        this.price = price;
        this.color = color;
    }

    public int getOrder_item_id() {
        return order_item_id;
    }
    public void setOrder_item_id(int order_item_id) {
        this.order_item_id = order_item_id;
    }
    public int getCloth_order_id() {
        return cloth_order_id;
    }
    public void setCloth_order_id(int cloth_order_id) {
        this.cloth_order_id = cloth_order_id;
    }
    public int getItem_id() {
        return item_id;
    }
    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }
    public int getQty() {
        return qty;
    }
    public void setQty(int qty) {
        this.qty = qty;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
}

