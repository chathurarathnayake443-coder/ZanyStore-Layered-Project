package lk.ijse.zanystore.bo.custom;

import lk.ijse.zanystore.dto.UserDTO;
import lk.ijse.zanystore.entity.User;
import lk.ijse.zanystore.bo.custom.SuperBO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserBO extends SuperBO {

    public boolean existUser(String name) throws SQLException;

    public boolean saveUser(UserDTO userDTO) throws SQLException;

    public boolean deleteUser(String name) throws SQLException;

    public UserDTO findUser(String name) throws SQLException;

    public boolean updateUser(UserDTO userDTO) throws SQLException;

    public List<UserDTO> getAllUser() throws SQLException;
}
