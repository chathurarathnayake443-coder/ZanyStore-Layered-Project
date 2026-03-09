package lk.ijse.zanystore.dto.QueryDTO;

public class LoadOrderViewDTO {
    private int cloth_order_id;
    private String cloth_order_description;
    private String cloth_order_start_date;
    private String cloth_order_end_date;
    private int customer_id;

    public LoadOrderViewDTO() {}

    public LoadOrderViewDTO(int cloth_order_id, String cloth_order_description, String cloth_order_start_date, String cloth_order_end_date, int customer_id) {
        this.cloth_order_id = cloth_order_id;
        this.cloth_order_description = cloth_order_description;
        this.cloth_order_start_date = cloth_order_start_date;
        this.cloth_order_end_date = cloth_order_end_date;
        this.customer_id = customer_id;
    }
    public int getCloth_order_id() {
        return cloth_order_id;
    }
    public void setCloth_order_id(int cloth_order_id) {
        this.cloth_order_id = cloth_order_id;
    }
    public String getCloth_order_description() {
        return cloth_order_description;
    }
    public void setCloth_order_description(String cloth_order_description) {
        this.cloth_order_description = cloth_order_description;
    }
    public String getCloth_order_start_date() {
        return cloth_order_start_date;
    }
    public void setCloth_order_start_date(String cloth_order_start_date) {
        this.cloth_order_start_date = cloth_order_start_date;
    }
    public String getCloth_order_end_date() {
        return cloth_order_end_date;
    }
    public void setCloth_order_end_date(String cloth_order_end_date) {
        this.cloth_order_end_date = cloth_order_end_date;
    }
    public int getCustomer_id() {
        return customer_id;
    }
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

}
