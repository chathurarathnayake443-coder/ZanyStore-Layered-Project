package lk.ijse.zanystore.dao.custom;

import lk.ijse.zanystore.dto.OrderSerproviderDTO;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.SQLException;

public interface OrderSerproviderDAO {

    public boolean save(OrderSerproviderDTO orderSerproviderDTO) throws SQLException;
}
