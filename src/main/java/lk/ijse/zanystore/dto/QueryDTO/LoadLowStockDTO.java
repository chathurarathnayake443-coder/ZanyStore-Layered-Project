package lk.ijse.zanystore.dto.QueryDTO;

public class LoadLowStockDTO {
    private int item_id;
    private String item_name;
    private String color;
    private int qty;

    public LoadLowStockDTO(int item_id, String item_name, String color, int qty) {
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
}
