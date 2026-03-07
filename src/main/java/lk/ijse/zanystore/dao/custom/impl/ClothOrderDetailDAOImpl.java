package lk.ijse.zanystore.dao.custom.impl;

import lk.ijse.zanystore.dao.custom.ClothOrderDetailDAO;
import lk.ijse.zanystore.dto.OrderItemDTO;
import lk.ijse.zanystore.entity.OrderItem;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class ClothOrderDetailDAOImpl implements ClothOrderDetailDAO {
    public boolean save(OrderItem entity) throws SQLException {
        boolean result = CrudUtil.execute("INSERT INTO order_item_details (cloth_order_id, item_id, qty, price, color)VALUES (?,?,?,?,?)", entity.getCloth_order_id() , entity.getItem_id(), entity.getQty(), entity.getPrice(), entity.getColor());
        return result;
    }

    @Override
    public boolean update(OrderItem entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public List<OrderItem> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public String showNextId() throws SQLException {
        return "";
    }

    @Override
    public OrderItem find(int empId) throws SQLException {
        return null;
    }

    @Override
    public String getId() throws SQLException {
        return "";
    }
}
