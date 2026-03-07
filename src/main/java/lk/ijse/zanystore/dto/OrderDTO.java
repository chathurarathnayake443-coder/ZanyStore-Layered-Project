/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.zanystore.dto;

import java.util.List;

/**
 *
 * @author chathura
 */
public class OrderDTO {
    private int cloth_order_id;
    private int customer_id;
    private String serprovider_name;
    private String cloth_order_description;
    private String cloth_order_start_date;
    private String cloth_order_end_date;
    private String given_date;
    private String recieved_date;
    private List<OrderItemDTO> itemList;

    public OrderDTO() {
    }

    public OrderDTO(int cloth_order_id, int customer_id, String cloth_order_description, String cloth_order_start_date, String cloth_order_end_date) {
        this.cloth_order_id = cloth_order_id;
        this.customer_id = customer_id;
        this.cloth_order_description = cloth_order_description;
        this.cloth_order_start_date = cloth_order_start_date;
        this.cloth_order_end_date = cloth_order_end_date;
    }

    public OrderDTO(int customer_id, String cloth_order_description, String cloth_order_start_date, String cloth_order_end_date) {
        this.customer_id = customer_id;
        this.cloth_order_description = cloth_order_description;
        this.cloth_order_start_date = cloth_order_start_date;
        this.cloth_order_end_date = cloth_order_end_date;
    }

    public OrderDTO(int customer_id, String cloth_order_description, String cloth_order_start_date, String cloth_order_end_date, String given_date, String recieved_date, List<OrderItemDTO> itemList) {
        this.customer_id = customer_id;
        this.cloth_order_description = cloth_order_description;
        this.cloth_order_start_date = cloth_order_start_date;
        this.cloth_order_end_date = cloth_order_end_date;
        this.given_date = given_date;
        this.recieved_date = recieved_date;
        this.itemList = itemList;
    }
    
    

    public String getGiven_date() {
        return given_date;
    }

    public void setGiven_date(String given_date) {
        this.given_date = given_date;
    }

    public String getRecieved_date() {
        return recieved_date;
    }

    public void setRecieved_date(String recieved_date) {
        this.recieved_date = recieved_date;
    }

    public List<OrderItemDTO> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItemDTO> itemList) {
        this.itemList = itemList;
    }
    
    

    public OrderDTO(int cloth_order_id, int customer_id, String cloth_order_description, String cloth_order_start_date, String cloth_order_end_date, List<OrderItemDTO> itemList) {
        this.cloth_order_id = cloth_order_id;
        this.customer_id = customer_id;
        this.cloth_order_description = cloth_order_description;
        this.cloth_order_start_date = cloth_order_start_date;
        this.cloth_order_end_date = cloth_order_end_date;
        this.itemList = itemList;
    }

    public OrderDTO(int cloth_order_id, int customer_id, String serprovider_name, String cloth_order_description, String cloth_order_start_date, String cloth_order_end_date) {
        this.cloth_order_id = cloth_order_id;
        this.customer_id = customer_id;
        this.serprovider_name = serprovider_name;
        this.cloth_order_description = cloth_order_description;
        this.cloth_order_start_date = cloth_order_start_date;
        this.cloth_order_end_date = cloth_order_end_date;
    }

    public int getCloth_order_id() {
        return cloth_order_id;
    }

    public void setCloth_order_id(int cloth_order_id) {
        this.cloth_order_id = cloth_order_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getSerprovider_name() {
        return serprovider_name;
    }

    public void setSerprovider_name(String serprovider_name) {
        this.serprovider_name = serprovider_name;
    }

    public String getCloth_order_description() {
        return cloth_order_description;
    }

    public void setCloth_order_description(String cloth_order_description) {
        this.cloth_order_description = cloth_order_description;
    }

    public String getCloth_order_start_date() {
        return cloth_order_start_date;
    }

    public void setCloth_order_start_date(String cloth_order_start_date) {
        this.cloth_order_start_date = cloth_order_start_date;
    }

    public String getCloth_order_end_date() {
        return cloth_order_end_date;
    }

    public void setCloth_order_end_date(String cloth_order_end_date) {
        this.cloth_order_end_date = cloth_order_end_date;
    }

    @Override
    public String toString() {
        return "OrderDTO{" + "cloth_order_id=" + cloth_order_id + ", customer_id=" + customer_id + ", serprovider_name=" + serprovider_name + ", cloth_order_description=" + cloth_order_description + ", cloth_order_start_date=" + cloth_order_start_date + ", cloth_order_end_date=" + cloth_order_end_date + '}';
    }
    
}
