package lk.ijse.zanystore.dao.custom.impl;

import lk.ijse.zanystore.dto.PaymentDTO;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDAOImpl {

    public boolean save(PaymentDTO paymentDTO) throws SQLException {
        boolean result = CrudUtil.execute("INSERT INTO payment (payment_amount, payment_method) VALUES (?,?)", paymentDTO.getAmount(),paymentDTO.getMethod());
        return result;
    }

    public String showNextId() throws SQLException {
        ResultSet result = CrudUtil.execute("SELECT payment_id FROM payment ORDER BY payment_id DESC LIMIT 1");
        if (result.next()) {
            int  id = result.getInt("payment_id");
            return String.valueOf(id + 1);
        }
        return null;
    }

    public String findTotal() throws SQLException {
        ResultSet results = CrudUtil.execute("SELECT SUM(payment_amount) AS sum FROM payment");
        if (results.next()) {
            double amount = results.getDouble("sum");
            return String.valueOf(amount);
        }
        return null;
    }
}
