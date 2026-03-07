package lk.ijse.zanystore.dao.custom.impl;

import lk.ijse.zanystore.dao.custom.CustomerDAO;
import lk.ijse.zanystore.dto.CustomerDTO;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    public boolean save(CustomerDTO customerDTO) throws SQLException {
        boolean result = CrudUtil.execute("INSERT INTO customer (customer_name,customer_address,customer_contact_no) VALUES (?,?,?)", customerDTO.getCustomer_name(),customerDTO.getCustomer_address(),customerDTO.getCustomer_contact_no());
        return result;
    }

    public boolean update(CustomerDTO customerDTO) throws SQLException{
        boolean result = CrudUtil.execute("UPDATE customer SET customer_name = ?, customer_address = ?, customer_contact_no = ? WHERE customer_id = ?", customerDTO.getCustomer_name(), customerDTO.getCustomer_address(), customerDTO.getCustomer_contact_no(),customerDTO.getCustomer_id());
        return result;
    }

    public boolean delete(int id) throws SQLException{
        boolean result = CrudUtil.execute("DELETE FROM customer WHERE customer_id = ?", id);
        return result;
    }

    public List<CustomerDTO> getAll() throws SQLException{
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

    public String showNextId() throws SQLException {
        ResultSet results = CrudUtil.execute("SELECT customer_id FROM customer ORDER BY customer_id DESC LIMIT 1");

        if(results.next()){
            int id = results.getInt("customer_id");
            String cusId = String.valueOf(id + 1);
            return cusId;
        }
        return null;
    }
}
