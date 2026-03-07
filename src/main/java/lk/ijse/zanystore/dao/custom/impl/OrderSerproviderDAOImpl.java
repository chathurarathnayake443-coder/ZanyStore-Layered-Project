package lk.ijse.zanystore.dao.custom.impl;

import lk.ijse.zanystore.dao.custom.OrderSerproviderDAO;
import lk.ijse.zanystore.dto.OrderSerproviderDTO;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.SQLException;

public class OrderSerproviderDAOImpl implements OrderSerproviderDAO {

    public boolean save(OrderSerproviderDTO orderSerproviderDTO) throws SQLException {
        boolean result = CrudUtil.execute("INSERT INTO order_serprovider_details (cloth_order_id, given_date, received_date) VALUES (?, ?, ?)",orderSerproviderDTO.getOrder_id(),orderSerproviderDTO.getGiven_date(),orderSerproviderDTO.getReceived_date());
        return result;
    }
}
