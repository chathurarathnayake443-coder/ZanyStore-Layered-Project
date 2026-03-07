package lk.ijse.zanystore.dao.custom.impl;

import lk.ijse.zanystore.dao.custom.UserDAO;
import lk.ijse.zanystore.dto.UserDTO;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    public boolean exist(String name) throws SQLException {
        ResultSet results = CrudUtil.execute("SELECT COUNT(*) AS count FROM user WHERE user_name = ?",name);

        if(results.next()){
            int count = results.getInt("count");
            return count > 0;
        }
        return false;
    }

    public boolean save(UserDTO userDTO) throws SQLException {
        boolean result = CrudUtil.execute("INSERT INTO user (user_name, user_address, user_salary, user_contact_no, user_password) VALUES (?,?,?,?,?)",userDTO.getUser_name(),userDTO.getUser_address(),userDTO.getUser_salary(),userDTO.getUser_contact_no(),userDTO.getUser_password());
        return result;
    }

    public boolean delete(String name) throws SQLException {
        boolean result = CrudUtil.execute("DELETE FROM user WHERE user_name = ?",name);
        return result;
    }

    public UserDTO find(String name) throws SQLException {
        ResultSet result =  CrudUtil.execute("SELECT user_name, user_address, user_salary, user_contact_no, user_password FROM user WHERE user_name = ?",name);

        if(result.next()){
            String user_address = result.getString("user_address");
            String user_salary = result.getString("user_salary");
            String user_contact_no = result.getString("user_contact_no");
            String user_password = result.getString("user_password");
            return new UserDTO(user_address,Double.parseDouble(user_salary),user_contact_no,user_password);
        }
        return null;
    }

    public List<UserDTO> getAll() throws SQLException {
        ResultSet results = CrudUtil.execute("SELECT * FROM user");

        List<UserDTO> list = new ArrayList<>();
        while (results.next()){
            String userName = results.getString("user_name");
            String userPassword = results.getString("user_password");
            String userAddress = results.getString("user_address");
            double userSalary = results.getDouble("user_salary");
            String userContact = results.getString("user_contact_no");

            list.add(new UserDTO(userName,userAddress,userSalary,userContact,userPassword));
        }
        return list;
    }

    public boolean update(UserDTO userDTO) throws SQLException {
        boolean result = CrudUtil.execute("UPDATE user SET user_address = ?, user_salary = ?, user_contact_no = ?, user_password = ? WHERE user_name = ?",userDTO.getUser_address(),userDTO.getUser_salary(),userDTO.getUser_contact_no(),userDTO.getUser_password(),userDTO.getUser_name());
        return result;
    }
}
