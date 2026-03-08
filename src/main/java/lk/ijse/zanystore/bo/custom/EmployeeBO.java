package lk.ijse.zanystore.bo.custom;

import lk.ijse.zanystore.dto.EmployeeDTO;
import lk.ijse.zanystore.entity.Employee;
import lk.ijse.zanystore.bo.custom.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeBO extends SuperBO {

    public EmployeeDTO searchEmployee(int id) throws SQLException;

    public boolean deleteEmployee(int id) throws SQLException;

    public boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException;

    public boolean saveEmployee(EmployeeDTO employeeDTO) throws SQLException;

    public List<EmployeeDTO> loadEmployeeTable() throws SQLException;

    public String showNextId() throws SQLException;
}
