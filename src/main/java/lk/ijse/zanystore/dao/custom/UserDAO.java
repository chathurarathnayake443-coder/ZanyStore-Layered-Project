package lk.ijse.zanystore.dao.custom;

import lk.ijse.zanystore.dto.UserDTO;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserDAO {

    public boolean exist(String name) throws SQLException;

    public boolean save(UserDTO userDTO) throws SQLException;

    public boolean delete(String name) throws SQLException;

    public UserDTO find(String name) throws SQLException;

    public List<UserDTO> getAll() throws SQLException;

    public boolean update(UserDTO userDTO) throws SQLException;
}
