package lk.ijse.zanystore.entity;

public class OrderPayment {
    private int order_payment_id;
    private int cloth_order_id;
    private int payment_id;
    private int customer_id;
    private String date;

    public OrderPayment() {}

    public OrderPayment(int cloth_order_id, int payment_id, int customer_id, String date){
        this.cloth_order_id = cloth_order_id;
        this.payment_id = payment_id;
        this.customer_id = customer_id;
        this.date = date;
    }

    public OrderPayment(int order_payment_id, int cloth_order_id, int payment_id, int customer_id, String date) {
        this.order_payment_id = order_payment_id;
        this.cloth_order_id = cloth_order_id;
        this.payment_id = payment_id;
        this.customer_id = customer_id;
        this.date = date;
    }

    public int getOrder_payment_id() {
        return order_payment_id;
    }
    public void setOrder_payment_id(int order_payment_id) {
        this.order_payment_id = order_payment_id;
    }
    public int getCloth_order_id() {
        return cloth_order_id;
    }
    public void setCloth_order_id(int cloth_order_id) {
        this.cloth_order_id = cloth_order_id;
    }
    public int getPayment_id() {
        return payment_id;
    }
    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }
    public int getCustomer_id() {
        return customer_id;
    }
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
