/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.zanystore.dto;

/**
 *
 * @author chathura
 */
public class ReturnDTO {
    private int return_order_id;
    private int cloth_order_id;
    private String return_order_details;
    private String customer_name;
    private String customer_address;
    private String return_date;

    public ReturnDTO() {
    }

    public ReturnDTO(String return_order_details){
        this.return_order_details = return_order_details;
    }

    public ReturnDTO(int cloth_order_id,int return_order_id,String return_date){
        this.cloth_order_id = cloth_order_id;
        this.return_order_id = return_order_id;
        this.return_date = return_date;
    }

    public ReturnDTO(int return_order_id, int cloth_order_id, String return_order_details, String customer_name, String customer_address, String return_date) {
        this.return_order_id = return_order_id;
        this.cloth_order_id = cloth_order_id;
        this.return_order_details = return_order_details;
        this.customer_name = customer_name;
        this.customer_address = customer_address;
        this.return_date = return_date;
    }

    public ReturnDTO(int return_order_id, int cloth_order_id) {
        this.return_order_id = return_order_id;
        this.cloth_order_id = cloth_order_id;
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

    public String getReturn_order_details() {
        return return_order_details;
    }

    public void setReturn_order_details(String return_order_details) {
        this.return_order_details = return_order_details;
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

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    @Override
    public String toString() {
        return "ReturnDTO{" + "return_order_id=" + return_order_id + ", cloth_order_id=" + cloth_order_id + ", return_order_details=" + return_order_details + ", customer_name=" + customer_name + ", customer_address=" + customer_address + ", return_date=" + return_date + '}';
    }

    
    
}
