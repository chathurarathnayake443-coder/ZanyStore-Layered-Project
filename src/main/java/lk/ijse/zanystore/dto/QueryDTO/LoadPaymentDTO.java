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
}
