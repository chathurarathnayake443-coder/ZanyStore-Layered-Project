package lk.ijse.zanystore.entity;

public class Employee {
    private int employee_id;
    private String employee_name;
    private int  employee_age;
    private String employee_address;
    private double employee_salary;
    private String employee_contact_number;
    private String employee_special_notes;

    public Employee(){}

    public Employee(String employee_name, int employee_age, String employee_address, double employee_salary, String employee_contact_number, String employee_special_notes){
        this.employee_name = employee_name;
        this.employee_age = employee_age;
        this.employee_address = employee_address;
        this.employee_salary = employee_salary;
        this.employee_contact_number = employee_contact_number;
        this.employee_special_notes = employee_special_notes;
    }

    public Employee(int employee_id, String employee_name, int employee_age, String employee_address, double employee_salary, String employee_contact_number, String employee_special_notes) {
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.employee_age = employee_age;
        this.employee_address = employee_address;
        this.employee_salary = employee_salary;
        this.employee_contact_number = employee_contact_number;
        this.employee_special_notes = employee_special_notes;
    }

    public int getEmployee_id() {
        return employee_id;
    }
    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }
    public String getEmployee_name() {
        return employee_name;
    }
    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }
    public int getEmployee_age() {
        return employee_age;
    }
    public void setEmployee_age(int employee_age) {
        this.employee_age = employee_age;
    }
    public String getEmployee_address() {
        return employee_address;
    }
    public void setEmployee_address(String employee_address) {
        this.employee_address = employee_address;
    }
    public double getEmployee_salary() {
        return employee_salary;
    }
    public void setEmployee_salary(double employee_salary) {
        this.employee_salary = employee_salary;
    }
    public String getEmployee_contact_number() {
        return employee_contact_number;
    }
    public void setEmployee_contact_number(String employee_contact_number) {
        this.employee_contact_number = employee_contact_number;
    }
    public String getEmployee_special_notes() {
        return employee_special_notes;
    }
    public void setEmployee_special_notes(String employee_special_notes) {
        this.employee_special_notes = employee_special_notes;
    }

}
