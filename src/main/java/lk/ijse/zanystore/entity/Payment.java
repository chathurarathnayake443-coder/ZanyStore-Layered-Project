package lk.ijse.zanystore.entity;

public class Payment {
    private int payment_id;
    private double payment_amount;
    private String payment_method;

    public Payment() {}

    public Payment(double payment_amount, String payment_method){
        this.payment_amount = payment_amount;
        this.payment_method = payment_method;
    }

    public Payment(int payment_id, double payment_amount, String payment_method) {
        this.payment_id = payment_id;
        this.payment_amount = payment_amount;
        this.payment_method = payment_method;
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
    public String getPayment_method() {
        return payment_method;
    }
    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }
}
