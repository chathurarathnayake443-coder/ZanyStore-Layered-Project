package lk.ijse.zanystore.dao.custom;

import lk.ijse.zanystore.dto.CustomerDTO;
import lk.ijse.zanystore.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO {
    public boolean save(Customer entity) throws SQLException;

    public boolean update(Customer entity) throws SQLException;

    public boolean delete(int id) throws SQLException;

    public List<Customer> getAll() throws SQLException;

    public String showNextId() throws SQLException;
}
