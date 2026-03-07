package lk.ijse.zanystore.dao.custom;

import lk.ijse.zanystore.dao.CrudDAO;
import lk.ijse.zanystore.dto.OrderDTO;
import lk.ijse.zanystore.entity.ClothOrder;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ClothOrderDAO extends CrudDAO<ClothOrder> {

    public String showNextId() throws SQLException;

    public boolean save(ClothOrder entity) throws SQLException;

    public String getId() throws SQLException;

    public List<String> getAllId() throws SQLException;
}
