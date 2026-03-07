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
public class QuotationItemDTO {
//    private String customer_name;
//    private double fullTotal;
//    private List<QuotationDTO> itemList;
//
//    public QuotationItemDTO(String customer_name, List<QuotationDTO> itemList) {
//        this.customer_name = customer_name;
//        this.itemList = itemList;
//    }
//
//    public QuotationItemDTO(String customer_name, double fullTotal, List<QuotationDTO> itemList) {
//        this.customer_name = customer_name;
//        this.fullTotal = fullTotal;
//        this.itemList = itemList;
//    }
//
//    public double getFullTotal() {
//        return fullTotal;
//    }
//
//    public void setFullTotal(double fullTotal) {
//        this.fullTotal = fullTotal;
//    }
//
//    public String getCustomer_name() {
//        return customer_name;
//    }
//
//    public void setCustomer_name(String customer_name) {
//        this.customer_name = customer_name;
//    }
//
//    public List<QuotationDTO> getItemList() {
//        return itemList;
//    }
//
//    public void setItemList(List<QuotationDTO> itemList) {
//        this.itemList = itemList;
//    }
//
//    @Override
//    public String toString() {
//        return "QuotationItemDTO{" + "customer_name=" + customer_name + ", itemList=" + itemList + '}';
//    }
//

    private String customer_name;
    private double fullTotal;

    QuotationItemDTO(){}

    public QuotationItemDTO(String customer_name, double fullTotal) {
        this.customer_name = customer_name;
        this.fullTotal = fullTotal;
    }

    public String getCustomer_name() {
        return customer_name;
    }
    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }
    public double getFullTotal() {
        return fullTotal;
    }
    public void setFullTotal(double fullTotal) {
        this.fullTotal = fullTotal;
    }

}
