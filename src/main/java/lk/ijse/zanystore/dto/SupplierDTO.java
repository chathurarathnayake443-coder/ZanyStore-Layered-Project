/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.zanystore.dto;

/**
 *
 * @author chathura
 */
public class SupplierDTO {
    private int supplier_id;
    private String supplier_name;
    private String supplier_address;
    private String supplier_item;
    private String Supplier_contact_no;

    public SupplierDTO() {
    }

    public SupplierDTO(int supplier_id, String supplier_name, String supplier_address, String supplier_item, String Supplier_contact_no) {
        this.supplier_id = supplier_id;
        this.supplier_name = supplier_name;
        this.supplier_address = supplier_address;
        this.supplier_item = supplier_item;
        this.Supplier_contact_no = Supplier_contact_no;
    }

    public SupplierDTO(String supplier_name, String supplier_address, String supplier_item, String Supplier_contact_no) {
        this.supplier_name = supplier_name;
        this.supplier_address = supplier_address;
        this.supplier_item = supplier_item;
        this.Supplier_contact_no = Supplier_contact_no;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getSupplier_address() {
        return supplier_address;
    }

    public void setSupplier_address(String supplier_address) {
        this.supplier_address = supplier_address;
    }

    public String getSupplier_item() {
        return supplier_item;
    }

    public void setSupplier_item(String supplier_item) {
        this.supplier_item = supplier_item;
    }

    public String getSupplier_contact_no() {
        return Supplier_contact_no;
    }

    public void setSupplier_contact_no(String Supplier_contact_no) {
        this.Supplier_contact_no = Supplier_contact_no;
    }

    @Override
    public String toString() {
        return "SupplierDTO{" + "supplier_id=" + supplier_id + ", supplier_name=" + supplier_name + ", supplier_address=" + supplier_address + ", supplier_item=" + supplier_item + ", Supplier_contact_no=" + Supplier_contact_no + '}';
    }
   
}
