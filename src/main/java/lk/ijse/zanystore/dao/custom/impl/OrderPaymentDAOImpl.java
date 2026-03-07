package lk.ijse.zanystore.dao.custom.impl;

import lk.ijse.zanystore.dao.custom.OrderPaymentDAO;
import lk.ijse.zanystore.dto.PaymentDTO;
import lk.ijse.zanystore.entity.OrderPayment;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class OrderPaymentDAOImpl implements OrderPaymentDAO {

    public boolean save(OrderPayment entity) throws SQLException {
        boolean result = CrudUtil.execute("INSERT INTO order_payment_details (cloth_order_id,payment_id,customer_id,date) VALUES (?,?,?,?)", entity.getCloth_order_id(),entity.getPayment_id(),entity.getCustomer_id(),entity.getDate());
        return result;
    }

    @Override
    public boolean update(OrderPayment entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public List<OrderPayment> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public String showNextId() throws SQLException {
        return "";
    }

    @Override
    public OrderPayment find(int empId) throws SQLException {
        return null;
    }

    @Override
    public String getId() throws SQLException {
        return "";
    }
}
