/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.zanystore.dto;

/**
 *
 * @author chathura
 */
public class UserDTO {
    private String user_name;
    private String user_address;
    private Double user_salary;
    private String user_contact_no;
    private String user_password;
    
    UserDTO(){}

    public UserDTO(String user_name, String user_address, Double user_salary, String user_contact_no, String user_password) {
        this.user_name = user_name;
        this.user_address = user_address;
        this.user_salary = user_salary;
        this.user_contact_no = user_contact_no;
        this.user_password = user_password;
    }

    public UserDTO(String user_address, Double user_salary, String user_contact_no, String user_password) {
        this.user_address = user_address;
        this.user_salary = user_salary;
        this.user_contact_no = user_contact_no;
        this.user_password = user_password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public Double getUser_salary() {
        return user_salary;
    }

    public void setUser_salary(Double user_salary) {
        this.user_salary = user_salary;
    }

    public String getUser_contact_no() {
        return user_contact_no;
    }

    public void setUser_contact_no(String user_contact_no) {
        this.user_contact_no = user_contact_no;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "user_name=" + user_name + ", user_address=" + user_address + ", user_salary=" + user_salary + ", user_contact_no=" + user_contact_no + ", user_password=" + user_password + '}';
    }
    
    
}
