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
public class CustomerviewModel {

    public List<CustomerDTO> loadCustomerViewTable() throws SQLException{

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
