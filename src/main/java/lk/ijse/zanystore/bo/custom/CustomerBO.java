package lk.ijse.zanystore.bo.custom;

import lk.ijse.zanystore.dto.CustomerDTO;
import lk.ijse.zanystore.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerBO {

    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException;

    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException;

    public boolean deleteCustomer(String id) throws SQLException;

    public List<CustomerDTO> loadCustomerTable() throws SQLException;

    public String showNextId() throws SQLException;
}
