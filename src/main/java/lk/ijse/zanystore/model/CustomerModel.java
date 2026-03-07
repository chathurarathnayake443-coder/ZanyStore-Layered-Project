/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.zanystore.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lk.ijse.zanystore.dto.CustomerDTO;
import lk.ijse.zanystore.util.CrudUtil;

/**
 *
 * @author chathura
 */
public class CustomerModel {

    public boolean clickSaveCustomer(CustomerDTO customerDTO) throws SQLException{
        boolean result = CrudUtil.execute("INSERT INTO customer (customer_name,customer_address,customer_contact_no) VALUES (?,?,?)", customerDTO.getCustomer_name(),customerDTO.getCustomer_address(),customerDTO.getCustomer_contact_no());
        return result;
    }

    public boolean clickUpdateCustomer(CustomerDTO customerDTO) throws SQLException{
        boolean result = CrudUtil.execute("UPDATE customer SET customer_name = ?, customer_address = ?, customer_contact_no = ? WHERE customer_id = ?", customerDTO.getCustomer_name(), customerDTO.getCustomer_address(), customerDTO.getCustomer_contact_no(),customerDTO.getCustomer_id());
        return result;
    }

    public boolean clickDeleteCustomer(int id) throws SQLException{
        boolean result = CrudUtil.execute("DELETE FROM customer WHERE customer_id = ?", id);
        return result;
    }

    public List<CustomerDTO> loadCustomerTable() throws SQLException{
        ResultSet results = CrudUtil.execute("SELECT * FROM customer");

        List<CustomerDTO> customerList = new ArrayList<>();

        while(results.next()){
           int id = results.getInt("customer_id");
           String name = results.getString("customer_name");
           String address = results.getString("customer_address");
           String contact = results.getString("customer_contact_no");

           CustomerDTO customerDTO = new CustomerDTO(id,name,address,contact);
           customerList.add(customerDTO);
        }
        return customerList;
    }
}
