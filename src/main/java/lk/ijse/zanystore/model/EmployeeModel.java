/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.zanystore.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lk.ijse.zanystore.dto.EmployeeDTO;
import lk.ijse.zanystore.util.CrudUtil;

/**
 *
 * @author chathura
 */
public class EmployeeModel {

    public EmployeeDTO searchEmployee(int empId) throws SQLException{
        ResultSet results = CrudUtil.execute("SELECT * FROM employee WHERE employee_id = ?", empId);

        if(results.next()){
            int id = results.getInt("employee_id");
            String name = results.getString("employee_name");
            int age = results.getInt("employee_age");
            String address = results.getString("employee_address");
            double salary = results.getDouble("employee_salary");
            String contact = results.getString("employee_contact_number");
            String notes = results.getString("employee_special_notes");

            EmployeeDTO employeeDTO = new EmployeeDTO(id,name,age,address,salary,contact,notes);
            return employeeDTO;
        }
        return null;
    }

    public boolean clickSaveEmployee(EmployeeDTO employeeDTO) throws SQLException{
        boolean result = CrudUtil.execute("INSERT INTO employee (employee_name,employee_age,employee_address,employee_salary,employee_contact_number,employee_special_notes) VALUES (?,?,?,?,?,?)",
                employeeDTO.getEmployee_name(),employeeDTO.getEmployee_age(),employeeDTO.getEmployee_address(),employeeDTO.getEmployee_salary(),employeeDTO.getEmployee_contact_number(),employeeDTO.getEmployee_special_notes());

        return result;
    }

    public boolean clickUpdateEmployee(EmployeeDTO employeeDTO) throws SQLException{
        boolean result = CrudUtil.execute("UPDATE employee SET employee_name = ?, employee_age = ?, employee_address = ?, employee_salary = ?, employee_contact_number = ?, employee_special_notes = ? WHERE employee_id = ?",
                employeeDTO.getEmployee_name(),employeeDTO.getEmployee_age(),employeeDTO.getEmployee_address(),employeeDTO.getEmployee_salary(),employeeDTO.getEmployee_contact_number(),employeeDTO.getEmployee_special_notes(),employeeDTO.getEmployee_id());

        return result;
    }

    public boolean clickDeleteEmployee(int empId) throws SQLException{
        boolean result = CrudUtil.execute("DELETE FROM employee WHERE employee_id = ?", empId);

        return result;
    }

    public List<EmployeeDTO> loadEmployeeTable() throws SQLException{
        ResultSet results = CrudUtil.execute("SELECT * FROM employee");

        List<EmployeeDTO> employeeList = new ArrayList<>();

        while(results.next()){
            int id = results.getInt("employee_id");
            String name = results.getString("employee_name");
            int age = results.getInt("employee_age");
            String address = results.getString("employee_address");
            double salary = results.getDouble("employee_salary");
            String contact = results.getString("employee_contact_number");
            String notes = results.getString("employee_special_notes");

            EmployeeDTO employeeDTO = new EmployeeDTO(id,name,age,address,salary,contact,notes);
            employeeList.add(employeeDTO);
        }
        return employeeList;
    }
}
