package lk.ijse.zanystore.dao;

import lk.ijse.zanystore.dao.SuperDAO;
import lk.ijse.zanystore.dto.EmployeeDTO;
import lk.ijse.zanystore.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO {

    public boolean save(T entity) throws SQLException;

    public boolean update(T entity) throws SQLException;

    public boolean delete(int id) throws SQLException;

    public List<T> getAll() throws SQLException;

    public String showNextId() throws SQLException;

    public T find(int empId) throws SQLException;

    public String getId() throws SQLException;
}
