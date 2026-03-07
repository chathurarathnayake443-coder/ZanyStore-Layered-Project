package lk.ijse.zanystore.entity;

public class QuotationItem {
    private int quotation_item_id;
    private int quotation_id;
    private String item_name;
    private String color;
    private int quantity;
    private double unit_price;

    public QuotationItem() {
    }

    public QuotationItem(int quotation_id, String item_name, String color, int quantity, double unit_price) {
        this.quotation_id = quotation_id;
        this.item_name = item_name;
        this.color = color;
        this.quantity = quantity;
        this.unit_price = unit_price;
    }

    public QuotationItem(int quotation_item_id, int quotation_id, String item_name, String color, int quantity, double unit_price) {
        this.quotation_item_id = quotation_item_id;
        this.quotation_id = quotation_id;
        this.item_name = item_name;
        this.color = color;
        this.quantity = quantity;
        this.unit_price = unit_price;
    }

    public int getQuotation_item_id() {
        return quotation_item_id;
    }
    public void setQuotation_item_id(int quotation_item_id) {
        this.quotation_item_id = quotation_item_id;
    }
    public int getQuotation_id() {
        return quotation_id;
    }
    public void setQuotation_id(int quotation_id) {
        this.quotation_id = quotation_id;
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
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getUnit_price() {
        return unit_price;
    }
    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }
}
