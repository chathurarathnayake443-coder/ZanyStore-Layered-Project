package lk.ijse.zanystore.dao.custom;

import lk.ijse.zanystore.dao.CrudDAO;
import lk.ijse.zanystore.dto.EmployeeDTO;
import lk.ijse.zanystore.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO extends CrudDAO<Employee> {

    public Employee find(int empId) throws SQLException;

    public boolean save(Employee employeeDTO) throws SQLException;

    public boolean update(Employee employeeDTO) throws SQLException;

    public boolean delete(int empId) throws SQLException;

    public List<Employee> getAll() throws SQLException;

    public String showNextId() throws SQLException;
}
