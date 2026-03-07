package lk.ijse.zanystore.dao.custom.impl;

import lk.ijse.zanystore.dao.custom.ClothOrderDetailDAO;
import lk.ijse.zanystore.dto.OrderItemDTO;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.SQLException;

public class ClothOrderDetailDAOImpl implements ClothOrderDetailDAO {
    public boolean save(OrderItemDTO orderItem) throws SQLException {
        boolean result = CrudUtil.execute("INSERT INTO order_item_details (cloth_order_id, item_id, qty, price, color)VALUES (?,?,?,?,?)", orderItem.getOrderId() , orderItem.getItem_id(), orderItem.getQty(), orderItem.getPrice(), orderItem.getColor());
        return result;
    }
}
