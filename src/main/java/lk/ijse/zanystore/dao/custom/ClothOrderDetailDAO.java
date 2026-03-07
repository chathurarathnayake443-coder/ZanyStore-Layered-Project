package lk.ijse.zanystore.dao.custom;

import lk.ijse.zanystore.dto.OrderItemDTO;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.SQLException;

public interface ClothOrderDetailDAO {

    public boolean save(OrderItemDTO orderItem) throws SQLException;
}
