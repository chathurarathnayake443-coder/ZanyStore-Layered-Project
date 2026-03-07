package lk.ijse.zanystore.entity;

public class OrderSerprovider {
    private int ser_assign_id;
    private int cloth_order_id;
    private String given_date;
    private String received_date;

    public OrderSerprovider() {
    }

    public OrderSerprovider(int cloth_order_id, String given_date, String received_date) {
        this.cloth_order_id = cloth_order_id;
        this.given_date = given_date;
        this.received_date = received_date;
    }

    public OrderSerprovider(int ser_assign_id, int cloth_order_id, String given_date, String received_date) {
        this.ser_assign_id = ser_assign_id;
        this.cloth_order_id = cloth_order_id;
        this.given_date = given_date;
        this.received_date = received_date;
    }

    public int getSer_assign_id() {
        return ser_assign_id;
    }
    public void setSer_assign_id(int ser_assign_id) {
        this.ser_assign_id = ser_assign_id;
    }
    public int getCloth_order_id() {
        return cloth_order_id;
    }
    public void setCloth_order_id(int cloth_order_id) {
        this.cloth_order_id = cloth_order_id;
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
