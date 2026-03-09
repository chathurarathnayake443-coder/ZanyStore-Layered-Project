package lk.ijse.zanystore.dto.QueryDTO;

public class LoadOtherDetailsDTO {
    private String customer_name;
    private double payment_amount;
    private String cloth_order_end_date;

    public LoadOtherDetailsDTO() {}

    public LoadOtherDetailsDTO(String customer_name, double payment_amount, String cloth_order_end_date) {
        this.customer_name = customer_name;
        this.payment_amount = payment_amount;
        this.cloth_order_end_date = cloth_order_end_date;
    }
    public String getCustomer_name() {
        return customer_name;
    }
    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }
    public double getPayment_amount() {
        return payment_amount;
    }
    public void setPayment_amount(double payment_amount) {
        this.payment_amount = payment_amount;
    }
    public String getCloth_order_end_date() {
        return cloth_order_end_date;
    }
    public void setCloth_order_end_date(String cloth_order_end_date) {
        this.cloth_order_end_date = cloth_order_end_date;
    }
}
