package lk.ijse.zanystore.dao.custom.impl;

import lk.ijse.zanystore.dao.custom.OrderPaymentDAO;
import lk.ijse.zanystore.dto.PaymentDTO;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.SQLException;

public class OrderPaymentDAOImpl implements OrderPaymentDAO {

    public boolean save(PaymentDTO paymentDTO) throws SQLException {
        boolean result = CrudUtil.execute("INSERT INTO order_payment_details (cloth_order_id,payment_id,customer_id,date) VALUES (?,?,?,?)", paymentDTO.getOrderId(),paymentDTO.getPaymentId(),paymentDTO.getCustomerId(),paymentDTO.getDate());
        return result;
    }
}
