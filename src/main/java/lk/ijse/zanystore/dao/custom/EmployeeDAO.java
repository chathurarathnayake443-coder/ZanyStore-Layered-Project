package lk.ijse.zanystore.dao.custom;

import lk.ijse.zanystore.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {

    public EmployeeDTO find(int empId) throws SQLException;

    public boolean save(EmployeeDTO employeeDTO) throws SQLException;

    public boolean update(EmployeeDTO employeeDTO) throws SQLException;

    public boolean delete(int empId) throws SQLException;

    public List<EmployeeDTO> getAll() throws SQLException;

    public String showNextId() throws SQLException;
}
