package lk.ijse.zanystore.dto.QueryDTO;

public class LoadPaymentDTO {
    private int payment_id;
    private int cloth_order_id;
    private double payment_amount;
    private String customer_name;
    private String date;

    public LoadPaymentDTO() {
    }

    public LoadPaymentDTO(int payment_id, int cloth_order_id, double payment_amount, String customer_name, String date) {
        this.payment_id = payment_id;
        this.cloth_order_id = cloth_order_id;
        this.payment_amount = payment_amount;
        this.customer_name = customer_name;
        this.date = date;
    }

    public int getPayment_id() {
        return payment_id;
    }
    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }
    public int getCloth_order_id() {
        return cloth_order_id;
    }
    public void setCloth_order_id(int cloth_order_id) {
        this.cloth_order_id = cloth_order_id;
    }
    public double getPayment_amount() {
        return payment_amount;
    }
    public void setPayment_amount(double payment_amount) {
        this.payment_amount = payment_amount;
    }
    public String getCustomer_name() {
        return customer_name;
    }
    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
