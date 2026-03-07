package lk.ijse.zanystore.entity;

public class Quotation {
    private int quotation_id;
    private String customer_name;
    private double total_amount;

    public Quotation() {}

    public Quotation(String customer_name, double total_amount){
        this.customer_name = customer_name;
        this.total_amount = total_amount;
    }

    public Quotation(int quotation_id, String customer_name, double total_amount) {
        this.quotation_id = quotation_id;
        this.customer_name = customer_name;
        this.total_amount = total_amount;
    }

    public int getQuotation_id() {
        return quotation_id;
    }
    public void setQuotation_id(int quotation_id) {
        this.quotation_id = quotation_id;
    }
    public String getCustomer_name() {
        return customer_name;
    }
    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }
    public double getTotal_amount() {
        return total_amount;
    }
    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }
}
