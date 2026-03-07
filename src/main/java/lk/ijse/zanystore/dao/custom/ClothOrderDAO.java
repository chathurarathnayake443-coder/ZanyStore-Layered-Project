package lk.ijse.zanystore.dao.custom;

import lk.ijse.zanystore.dto.OrderDTO;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ClothOrderDAO {

    public String showNextId() throws SQLException;

    public boolean save(OrderDTO orderDTO) throws SQLException;

    public String getId() throws SQLException;

    public List<String> getAllId() throws SQLException;
}
