package lk.ijse.zanystore.entity;

public class Item {
    private int item_id;
    private String item_name;
    private String item_type;
    private double item_unit_price;

    public Item() {
    }

    public Item(int item_id){
        this.item_id=item_id;
    }

    public Item(String item_name, String item_type, double item_unit_price) {
        this.item_name = item_name;
        this.item_type = item_type;
        this.item_unit_price = item_unit_price;
    }

    public Item(int item_id, String item_name, String item_type, double item_unit_price) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_type = item_type;
        this.item_unit_price = item_unit_price;
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
    public String getItem_type() {
        return item_type;
    }
    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }
    public double getItem_unit_price() {
        return item_unit_price;
    }
    public void setItem_unit_price(double item_unit_price) {
        this.item_unit_price = item_unit_price;
    }
}
