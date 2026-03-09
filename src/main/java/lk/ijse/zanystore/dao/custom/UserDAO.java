package lk.ijse.zanystore.dao.custom;

import lk.ijse.zanystore.dao.CrudDAO;
import lk.ijse.zanystore.dto.UserDTO;
import lk.ijse.zanystore.entity.User;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserDAO extends CrudDAO<User> {

    public boolean exist(String name) throws SQLException;

    public boolean delete(String name) throws SQLException;

    public User find(String name) throws SQLException;
}
