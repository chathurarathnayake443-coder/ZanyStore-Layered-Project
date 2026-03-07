package lk.ijse.zanystore.dao.custom.impl;

import lk.ijse.zanystore.dao.custom.CustomerDAO;
import lk.ijse.zanystore.dto.CustomerDTO;
import lk.ijse.zanystore.entity.Customer;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    public boolean save(Customer entity) throws SQLException {
        boolean result = CrudUtil.execute("INSERT INTO customer (customer_name,customer_address,customer_contact_no) VALUES (?,?,?)", entity.getCustomer_name(),entity.getCustomer_address(),entity.getCustomer_contact_no());
        return result;
    }

    public boolean update(Customer entity) throws SQLException{
        boolean result = CrudUtil.execute("UPDATE customer SET customer_name = ?, customer_address = ?, customer_contact_no = ? WHERE customer_id = ?", entity.getCustomer_name(), entity.getCustomer_address(), entity.getCustomer_contact_no(),entity.getCustomer_id());
        return result;
    }

    public boolean delete(int id) throws SQLException{
        boolean result = CrudUtil.execute("DELETE FROM customer WHERE customer_id = ?", id);
        return result;
    }

    public List<Customer> getAll() throws SQLException{
        ResultSet results = CrudUtil.execute("SELECT * FROM customer");

        List<Customer> customerList = new ArrayList<>();

        while(results.next()){
            int id = results.getInt("customer_id");
            String name = results.getString("customer_name");
            String address = results.getString("customer_address");
            String contact = results.getString("customer_contact_no");

            customerList.add(new Customer(id,name,address,contact));
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
