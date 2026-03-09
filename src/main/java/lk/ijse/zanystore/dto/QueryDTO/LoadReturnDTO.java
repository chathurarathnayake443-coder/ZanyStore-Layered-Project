package lk.ijse.zanystore.dto.QueryDTO;

public class LoadReturnDTO {
    private int return_order_id;
    private int cloth_order_id;
    private String return_order_details;
    private String customer_name;
    private String customer_address;
    private String order_return_date;

    public LoadReturnDTO() {}

    public LoadReturnDTO(int return_order_id, int cloth_order_id, String return_order_details, String customer_name, String customer_address, String order_return_date) {
        this.return_order_id = return_order_id;
        this.cloth_order_id = cloth_order_id;
        this.return_order_details = return_order_details;
        this.customer_name = customer_name;
        this.customer_address = customer_address;
        this.order_return_date = order_return_date;
    }

    public int getReturn_order_id() {
        return return_order_id;
    }
    public void setReturn_order_id(int return_order_id) {
        this.return_order_id = return_order_id;
    }
    public int getCloth_order_id() {
        return cloth_order_id;
    }
    public void setCloth_order_id(int cloth_order_id) {
        this.cloth_order_id = cloth_order_id;
    }
    public String getReturn_order_details() {
        return return_order_details;
    }
    public void setReturn_order_details(String return_order_details) {
        this.return_order_details = return_order_details;
    }
    public String getCustomer_name() {
        return customer_name;
    }
    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }
    public String getCustomer_address() {
        return customer_address;
    }
    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }
    public String getOrder_return_date() {
        return order_return_date;
    }
    public void setOrder_return_date(String order_return_date) {
        this.order_return_date = order_return_date;
    }
}
