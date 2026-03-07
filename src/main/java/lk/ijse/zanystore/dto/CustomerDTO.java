/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.zanystore.dto;

/**
 *
 * @author chathura
 */
public class CustomerDTO {
    private int customer_id;
    private String customer_name;
    private String customer_address;
    private String customer_contact_no;

    public CustomerDTO() {
    }

    public CustomerDTO(int customer_id, String customer_name, String customer_address, String customer_contact_no) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.customer_address = customer_address;
        this.customer_contact_no = customer_contact_no;
    }

    public CustomerDTO(String customer_name, String customer_address, String customer_contact_no) {
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

    @Override
    public String toString() {
        return "CustomerDTO{" + "customer_id=" + customer_id + ", customer_name=" + customer_name + ", customer_address=" + customer_address + ", customer_contact_no=" + customer_contact_no + '}';
    }
    
}
