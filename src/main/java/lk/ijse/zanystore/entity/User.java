package lk.ijse.zanystore.entity;

public class User {
    private String user_name;
    private String user_password;
    private String user_address;
    private double user_salary;
    private String user_contact_no;

    public User(){}

    public User(String user_password, String user_address, double user_salary, String user_contact_no) {
        this.user_password = user_password;
        this.user_address = user_address;
        this.user_salary = user_salary;
        this.user_contact_no = user_contact_no;
    }

    public User(String user_name, String user_password, String user_address, double user_salary, String user_contact_no) {
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_address = user_address;
        this.user_salary = user_salary;
        this.user_contact_no = user_contact_no;
    }
    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public String getUser_password() {
        return user_password;
    }
    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
    public String getUser_address() {
        return user_address;
    }
    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }
    public double getUser_salary() {
        return user_salary;
    }
    public void setUser_salary(double user_salary) {
        this.user_salary = user_salary;
    }
    public String getUser_contact_no() {
        return user_contact_no;
    }
    public void setUser_contact_no(String user_contact_no) {
        this.user_contact_no = user_contact_no;
    }
}
