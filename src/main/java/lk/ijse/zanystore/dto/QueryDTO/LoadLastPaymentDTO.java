package lk.ijse.zanystore.dto.QueryDTO;

public class LoadLastPaymentDTO {
    private int payment_id;
    private double payment_amount;
    private String customer_name;
    private String date;

    public LoadLastPaymentDTO(int payment_id, double payment_amount, String customer_name, String date) {
        this.payment_id = payment_id;
        this.payment_amount = payment_amount;
        this.customer_name = customer_name;
        this.date = date;
    }
    public LoadLastPaymentDTO() {

    }
    public int getPayment_id() {
        return payment_id;
    }
    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
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
