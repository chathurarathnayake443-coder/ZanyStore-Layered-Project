/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.zanystore.dto;

/**
 *
 * @author chathura
 */
public class ServiceproviderDTO {
    private int serprovider_id;
    private String serprovider_name;
    private String serprovider_address;
    private String serprovider_contact_no;
    private String serprovider_type;

    public ServiceproviderDTO() {
    }

    public ServiceproviderDTO(int serprovider_id, String serprovider_name, String serprovider_address, String serprovider_contact_no, String serprovider_type) {
        this.serprovider_id = serprovider_id;
        this.serprovider_name = serprovider_name;
        this.serprovider_address = serprovider_address;
        this.serprovider_contact_no = serprovider_contact_no;
        this.serprovider_type = serprovider_type;
    }

    public ServiceproviderDTO(String serprovider_name, String serprovider_address, String serprovider_contact_no, String serprovider_type) {
        this.serprovider_name = serprovider_name;
        this.serprovider_address = serprovider_address;
        this.serprovider_contact_no = serprovider_contact_no;
        this.serprovider_type = serprovider_type;
    }
    
    

    public int getSerprovider_id() {
        return serprovider_id;
    }

    public void setSerprovider_id(int serprovider_id) {
        this.serprovider_id = serprovider_id;
    }

    public String getSerprovider_name() {
        return serprovider_name;
    }

    public void setSerprovider_name(String serprovider_name) {
        this.serprovider_name = serprovider_name;
    }

    public String getSerprovider_address() {
        return serprovider_address;
    }

    public void setSerprovider_address(String serprovider_address) {
        this.serprovider_address = serprovider_address;
    }

    public String getSerprovider_contact_no() {
        return serprovider_contact_no;
    }

    public void setSerprovider_contact_no(String serprovider_contact_no) {
        this.serprovider_contact_no = serprovider_contact_no;
    }

    public String getSerprovider_type() {
        return serprovider_type;
    }

    public void setSerprovider_type(String serprovider_type) {
        this.serprovider_type = serprovider_type;
    }

    @Override
    public String toString() {
        return "ServiceproviderDTO{" + "serprovider_id=" + serprovider_id + ", serprovider_name=" + serprovider_name + ", serprovider_address=" + serprovider_address + ", serprovider_contact_no=" + serprovider_contact_no + ", serprovider_type=" + serprovider_type + '}';
    }
    
    
}
