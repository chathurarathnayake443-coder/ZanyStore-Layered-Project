package lk.ijse.zanystore.dao.custom.impl;

import lk.ijse.zanystore.dao.custom.EmployeeDAO;
import lk.ijse.zanystore.dto.EmployeeDTO;
import lk.ijse.zanystore.entity.Employee;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    public Employee find(int empId) throws SQLException {
        ResultSet results = CrudUtil.execute("SELECT * FROM employee WHERE employee_id = ?", empId);

        if(results.next()){
            int id = results.getInt("employee_id");
            String name = results.getString("employee_name");
            int age = results.getInt("employee_age");
            String address = results.getString("employee_address");
            double salary = results.getDouble("employee_salary");
            String contact = results.getString("employee_contact_number");
            String notes = results.getString("employee_special_notes");

            return new Employee(id,name,age,address,salary,contact,notes);
        }
        return null;
    }

    @Override
    public String getId() throws SQLException {
        return "";
    }

    public boolean save(Employee entity) throws SQLException{
        boolean result = CrudUtil.execute("INSERT INTO employee (employee_name,employee_age,employee_address,employee_salary,employee_contact_number,employee_special_notes) VALUES (?,?,?,?,?,?)",
                entity.getEmployee_name(),entity.getEmployee_age(),entity.getEmployee_address(),entity.getEmployee_salary(),entity.getEmployee_contact_number(),entity.getEmployee_special_notes());

        return result;
    }

    public boolean update(Employee entity) throws SQLException{
        boolean result = CrudUtil.execute("UPDATE employee SET employee_name = ?, employee_age = ?, employee_address = ?, employee_salary = ?, employee_contact_number = ?, employee_special_notes = ? WHERE employee_id = ?",
                entity.getEmployee_name(),entity.getEmployee_age(),entity.getEmployee_address(),entity.getEmployee_salary(),entity.getEmployee_contact_number(),entity.getEmployee_special_notes(),entity.getEmployee_id());

        return result;
    }

    public boolean delete(int empId) throws SQLException{
        boolean result = CrudUtil.execute("DELETE FROM employee WHERE employee_id = ?", empId);
        return result;
    }

    public List<Employee> getAll() throws SQLException{
        ResultSet results = CrudUtil.execute("SELECT * FROM employee");

        List<Employee> employeeList = new ArrayList<>();

        while(results.next()){
            int id = results.getInt("employee_id");
            String name = results.getString("employee_name");
            int age = results.getInt("employee_age");
            String address = results.getString("employee_address");
            double salary = results.getDouble("employee_salary");
            String contact = results.getString("employee_contact_number");
            String notes = results.getString("employee_special_notes");

            employeeList.add(new Employee(id,name,age,address,salary,contact,notes));
        }
        return employeeList;
    }

    public String showNextId() throws SQLException {
        ResultSet results = CrudUtil.execute("SELECT employee_id FROM employee ORDER BY employee_id DESC LIMIT 1");

        if(results.next()){
            int id = results.getInt("employee_id");
            String empId = String.valueOf(id + 1);
            return empId;
        }
        return null;
    }
}
