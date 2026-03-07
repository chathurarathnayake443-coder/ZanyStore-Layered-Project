package lk.ijse.zanystore.dao.custom.impl;

import lk.ijse.zanystore.dao.custom.OrderSerproviderDAO;
import lk.ijse.zanystore.dto.OrderSerproviderDTO;
import lk.ijse.zanystore.entity.OrderSerprovider;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class OrderSerproviderDAOImpl implements OrderSerproviderDAO {

    public boolean save(OrderSerprovider entity) throws SQLException {
        boolean result = CrudUtil.execute("INSERT INTO order_serprovider_details (cloth_order_id, given_date, received_date) VALUES (?, ?, ?)",entity.getCloth_order_id(),entity.getGiven_date(),entity.getReceived_date());
        return result;
    }

    @Override
    public boolean update(OrderSerprovider entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public List<OrderSerprovider> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public String showNextId() throws SQLException {
        return "";
    }

    @Override
    public OrderSerprovider find(int empId) throws SQLException {
        return null;
    }

    @Override
    public String getId() throws SQLException {
        return "";
    }
}
