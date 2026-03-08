package lk.ijse.zanystore.dto.QueryDTO;

public class LoadItemDTO {

    //For ItemController -> loadItemTable()
    private int item_id;
    private String item_name;
    private String item_type;
    private double item_unit_price;
    private String color;
    private int qty;

    public LoadItemDTO(int item_id, String item_name, String item_type, double item_unit_price, String color, int qty){
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_type = item_type;
        this.item_unit_price = item_unit_price;
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
