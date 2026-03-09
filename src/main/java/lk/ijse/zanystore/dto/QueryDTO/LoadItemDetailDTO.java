package lk.ijse.zanystore.dto.QueryDTO;

public class LoadItemDetailDTO {
    private String color;
    private int qty;
    private String item_name;

    public LoadItemDetailDTO(String color, int qty, String item_name) {
        this.color = color;
        this.qty = qty;
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
    public String getItem_name() {
        return item_name;
    }
    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

}
