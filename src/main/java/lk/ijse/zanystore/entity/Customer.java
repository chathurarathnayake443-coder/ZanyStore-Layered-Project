package lk.ijse.zanystore.entity;

public class Customer {
    private int customer_id;
    private String customer_name;
    private String customer_address;
    private String customer_contact_no;

    public Customer() {
    }

    public Customer(String customer_name, String customer_address, String customer_contact_no){
        this.customer_name = customer_name;
        this.customer_address = customer_address;
        this.customer_contact_no = customer_contact_no;
    }

    public Customer(int customer_id, String customer_name, String customer_address, String customer_contact_no) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.customer_address = customer_address;
        this.customer_contact_no = customer_contact_no;
    }

    public int getCustomer_id() {
        return customer_id;
    }
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
    public String getCustomer_name() {
        return customer_name;
    }
    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }
    public String getCustomer_address() {
        return customer_address;
    }
    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }
    public String getCustomer_contact_no() {
        return customer_contact_no;
    }
    public void setCustomer_contact_no(String customer_contact_no) {
        this.customer_contact_no = customer_contact_no;
    }

}
