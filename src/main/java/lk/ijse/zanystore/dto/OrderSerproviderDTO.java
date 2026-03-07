package lk.ijse.zanystore.dto;

public class OrderSerproviderDTO {
    private int assign_id;
    private int order_id;
    private String given_date;
    private String received_date;

    public OrderSerproviderDTO() {}

    public OrderSerproviderDTO(int order_id, String given_date, String received_date){
        this.order_id = order_id;
        this.given_date = given_date;
        this.received_date = received_date;
    }

    public OrderSerproviderDTO(int assign_id, int order_id, String given_date, String received_date) {
        this.assign_id = assign_id;
        this.order_id = order_id;
        this.given_date = given_date;
        this.received_date = received_date;
    }

    public int getAssign_id() {
        return assign_id;
    }
    public void setAssign_id(int assign_id) {
        this.assign_id = assign_id;
    }
    public int getOrder_id() {
        return order_id;
    }
    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
    public String getGiven_date() {
        return given_date;
    }
    public void setGiven_date(String given_date) {
        this.given_date = given_date;
    }
    public String getReceived_date() {
        return received_date;
    }
    public void setReceived_date(String received_date) {
        this.received_date = received_date;
    }

}
