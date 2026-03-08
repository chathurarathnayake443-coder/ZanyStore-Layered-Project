package lk.ijse.zanystore.bo.custom.impl;

import lk.ijse.zanystore.bo.custom.EmployeeBO;
import lk.ijse.zanystore.dao.DAOFactory;
import lk.ijse.zanystore.dao.custom.EmployeeDAO;
import lk.ijse.zanystore.dao.custom.QuotationDAO;
import lk.ijse.zanystore.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.zanystore.dto.EmployeeDTO;
import lk.ijse.zanystore.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    public EmployeeDTO searchEmployee(int id) throws SQLException {
        Employee entity = employeeDAO.find(id);
        return new EmployeeDTO(entity.getEmployee_id(), entity.getEmployee_name(),entity.getEmployee_age(),entity.getEmployee_address(),entity.getEmployee_salary(),entity.getEmployee_contact_number(),entity.getEmployee_special_notes());
    }

    public boolean deleteEmployee(int id) throws SQLException {
        return employeeDAO.delete(id);
    }

    public boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException {
        return employeeDAO.update(new Employee(employeeDTO.getEmployee_id(), employeeDTO.getEmployee_name(),employeeDTO.getEmployee_age(),employeeDTO.getEmployee_address(),employeeDTO.getEmployee_salary(),employeeDTO.getEmployee_contact_number(),employeeDTO.getEmployee_special_notes()));
    }

    public boolean saveEmployee(EmployeeDTO employeeDTO) throws SQLException {
        return employeeDAO.save(new Employee(employeeDTO.getEmployee_name(),employeeDTO.getEmployee_age(),employeeDTO.getEmployee_address(),employeeDTO.getEmployee_salary(),employeeDTO.getEmployee_contact_number(),employeeDTO.getEmployee_special_notes()));
    }

    public List<EmployeeDTO> loadEmployeeTable() throws SQLException {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        List<Employee> list = employeeDAO.getAll();
        for (Employee employee : list) {
            employeeDTOList.add(new EmployeeDTO(employee.getEmployee_id(),employee.getEmployee_name(),employee.getEmployee_age(),employee.getEmployee_address(),employee.getEmployee_salary(),employee.getEmployee_contact_number(),employee.getEmployee_special_notes()));
        }
        return employeeDTOList;
    }

    public String showNextId() throws SQLException {
        String id = employeeDAO.showNextId();
        return id;
    }
}
