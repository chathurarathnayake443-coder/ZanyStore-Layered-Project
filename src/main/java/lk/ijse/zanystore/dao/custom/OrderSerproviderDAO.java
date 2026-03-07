package lk.ijse.zanystore.dao.custom;

import lk.ijse.zanystore.dao.CrudDAO;
import lk.ijse.zanystore.dto.OrderSerproviderDTO;
import lk.ijse.zanystore.entity.OrderSerprovider;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.SQLException;

public interface OrderSerproviderDAO extends CrudDAO<OrderSerprovider> {

    public boolean save(OrderSerprovider entity) throws SQLException;
}
