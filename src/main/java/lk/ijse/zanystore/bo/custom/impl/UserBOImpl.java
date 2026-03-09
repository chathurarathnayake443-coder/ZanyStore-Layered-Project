package lk.ijse.zanystore.bo.custom.impl;

import lk.ijse.zanystore.bo.custom.UserBO;
import lk.ijse.zanystore.dao.DAOFactory;
import lk.ijse.zanystore.dao.custom.QuotationDAO;
import lk.ijse.zanystore.dao.custom.UserDAO;
import lk.ijse.zanystore.dao.custom.impl.UserDAOImpl;
import lk.ijse.zanystore.dto.UserDTO;
import lk.ijse.zanystore.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);

    public boolean existUser(String name) throws SQLException {
        boolean result = userDAO.exist(name);
        return result;
    }

    public boolean saveUser(UserDTO userDTO) throws SQLException {
        boolean result = userDAO.save(new User(userDTO.getUser_name(), userDTO.getUser_password(),userDTO.getUser_address(),userDTO.getUser_salary(),userDTO.getUser_contact_no()));
        return result;
    }

    public boolean deleteUser(String name) throws SQLException {
        boolean result = userDAO.delete(name);
        return result;
    }

    public UserDTO findUser(String name) throws SQLException {
        User user = userDAO.find(name);
        if (user == null) return null;
        return new UserDTO(user.getUser_name(),user.getUser_password(),user.getUser_salary(),user.getUser_address(),user.getUser_contact_no());
    }

    public boolean updateUser(UserDTO userDTO) throws SQLException {
        boolean result = userDAO.update(new User(userDTO.getUser_name(), userDTO.getUser_password(),userDTO.getUser_address(),userDTO.getUser_salary(),userDTO.getUser_contact_no()));
        return result;
    }

    public List<UserDTO> getAllUser() throws SQLException {
        List<User> userList = userDAO.getAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userList) {
            userDTOList.add(new UserDTO(user.getUser_name(),user.getUser_address(),user.getUser_salary(),user.getUser_contact_no(),user.getUser_password()));
        }
        return userDTOList;
    }
}
