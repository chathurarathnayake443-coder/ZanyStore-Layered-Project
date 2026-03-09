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

    public List<String> getAllId() throws SQLException;

    public int getOrderCount() throws SQLException;
}
