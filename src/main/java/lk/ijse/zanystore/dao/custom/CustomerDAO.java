package lk.ijse.zanystore.dao.custom;

import lk.ijse.zanystore.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO {
    public boolean save(CustomerDTO customerDTO) throws SQLException;

    public boolean update(CustomerDTO customerDTO) throws SQLException;

    public boolean delete(int id) throws SQLException;

    public List<CustomerDTO> getAll() throws SQLException;

    public String showNextId() throws SQLException;
}
