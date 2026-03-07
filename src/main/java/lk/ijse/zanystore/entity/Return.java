package lk.ijse.zanystore.entity;

public class Return {
    private int return_order_id;
    private String return_order_details;

    public Return() {
    }

    public Return(String return_order_details){
        this.return_order_details = return_order_details;
    }

    public Return(int return_order_id, String return_order_details) {
        this.return_order_id = return_order_id;
        this.return_order_details = return_order_details;
    }

    public int getReturn_order_id() {
        return return_order_id;
    }
    public void setReturn_order_id(int return_order_id) {
        this.return_order_id = return_order_id;
    }
    public String getReturn_order_details() {
        return return_order_details;
    }
    public void setReturn_order_details(String return_order_details) {
        this.return_order_details = return_order_details;
    }
}
