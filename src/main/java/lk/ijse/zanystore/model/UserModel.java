/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.zanystore.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lk.ijse.zanystore.dto.UserDTO;
import lk.ijse.zanystore.util.CrudUtil;

/**
 *
 * @author chathura
 */
public class UserModel {

    public boolean clickSaveUser(UserDTO userDTO) throws SQLException{
        boolean result = CrudUtil.execute("INSERT INTO user (user_name, user_address, user_salary, user_contact_no, user_password) VALUES (?,?,?,?,?)",
                userDTO.getUser_name(),userDTO.getUser_address(),userDTO.getUser_salary(),userDTO.getUser_contact_no(),userDTO.getUser_password());

        return result;
    }

    public boolean checkExistingUser(String userName) throws SQLException{
        boolean result = CrudUtil.execute("SELECT COUNT(*) AS count FROM user WHERE user_name = ?", userName);

        return result;
    }

    public boolean clickDeleteUser(String userName) throws SQLException{
        boolean result = CrudUtil.execute("DELETE FROM user WHERE user_name = ?", userName);

        return result;
    }

    public boolean clickUpdateUser(UserDTO userDTO) throws SQLException{
        boolean result = CrudUtil.execute("UPDATE user SET user_address = ?, user_salary = ?, user_contact_no = ?, user_password = ? WHERE user_name = ?",
                userDTO.getUser_address(),userDTO.getUser_salary(),userDTO.getUser_contact_no(),userDTO.getUser_password(),userDTO.getUser_name());

        return result;
    }

    public String checkUserForUpdate(String name) throws SQLException{
        ResultSet result = CrudUtil.execute("SELECT user_password FROM user WHERE user_name = ?", name);

        if(result.next()){
            String password = result.getString("user_password");
            return password;
        }

        return null;
    }

    public List<UserDTO> loadUserTable() throws SQLException{
        ResultSet results = CrudUtil.execute("SELECT * FROM user");

        List<UserDTO> userList = new ArrayList<>();

        while(results.next()){
            String userName = results.getString("user_name");
                String userPassword = results.getString("user_password");
                String userAddress = results.getString("user_address");
                double userSalary = results.getDouble("user_salary");
                String userContact = results.getString("user_contact_no");

                UserDTO userDTO = new UserDTO(userName,userAddress,userSalary,userContact,userPassword);
                userList.add(userDTO);
        }

        return userList;
    }
}
