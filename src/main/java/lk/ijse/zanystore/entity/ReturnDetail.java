package lk.ijse.zanystore.entity;

public class ReturnDetail {
    private int return_id;
    private int return_order_id;
    private int cloth_order_id;
    private String order_return_date;

    public ReturnDetail() {}

    public ReturnDetail(int return_order_id, int cloth_order_id, String order_return_date) {
        this.return_order_id = return_order_id;
        this.cloth_order_id = cloth_order_id;
        this.order_return_date = order_return_date;
    }

    public ReturnDetail(int return_id, int return_order_id, int cloth_order_id,String order_return_date) {
        this.return_id = return_id;
        this.return_order_id = return_order_id;
        this.cloth_order_id = cloth_order_id;
        this.order_return_date = order_return_date;
    }

    public int getReturn_id() {
        return return_id;
    }
    public void setReturn_id(int return_id) {
        this.return_id = return_id;
    }
    public int getReturn_order_id() {
        return return_order_id;
    }
    public void setReturn_order_id(int return_order_id) {
        this.return_order_id = return_order_id;
    }
    public int getCloth_order_id() {
        return cloth_order_id;
    }
    public void setCloth_order_id(int cloth_order_id) {
        this.cloth_order_id = cloth_order_id;
    }
    public String getOrder_return_date() {
        return order_return_date;
    }
    public void setOrder_return_date(String order_return_date) {
        this.order_return_date = order_return_date;
    }
}
