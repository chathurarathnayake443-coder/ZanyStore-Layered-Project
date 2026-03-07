package lk.ijse.zanystore.entity;

public class ItemColorStock {
    private int color_stock_id;
    private int item_id;
    private String color;
    private int qty;

    public ItemColorStock() {
    }

    public ItemColorStock(int item_id, String color, int qty) {
        this.item_id = item_id;
        this.color = color;
        this.qty = qty;
    }

    public ItemColorStock(int color_stock_id, int item_id, String color, int qty) {
        this.color_stock_id = color_stock_id;
        this.item_id = item_id;
        this.color = color;
        this.qty = qty;
    }

    public int getColor_stock_id() {
        return color_stock_id;
    }
    public void setColor_stock_id(int color_stock_id) {
        this.color_stock_id = color_stock_id;
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
}
