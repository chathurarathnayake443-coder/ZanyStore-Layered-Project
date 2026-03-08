package lk.ijse.zanystore.dao.custom.impl;

import lk.ijse.zanystore.dao.custom.PaymentDAO;
import lk.ijse.zanystore.dto.PaymentDTO;
import lk.ijse.zanystore.entity.Payment;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {

    public boolean save(Payment entity) throws SQLException {
        boolean result = CrudUtil.execute("INSERT INTO payment (payment_amount, payment_method) VALUES (?,?)", entity.getPayment_amount(),entity.getPayment_method());
        return result;
    }

    @Override
    public boolean update(Payment entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public List<Payment> getAll() throws SQLException {
        return List.of();
    }

    public String showNextId() throws SQLException {
        ResultSet result = CrudUtil.execute("SELECT payment_id FROM payment ORDER BY payment_id DESC LIMIT 1");
        if (result.next()) {
            int  id = result.getInt("payment_id");
            return String.valueOf(id + 1);
        }
        return null;
    }

    @Override
    public Payment find(int empId) throws SQLException {
        return null;
    }

    @Override
    public String getId() throws SQLException {
        return "";
    }

    public String findTotal() throws SQLException {
        ResultSet results = CrudUtil.execute("SELECT SUM(payment_amount) AS sum FROM payment");
        if (results.next()) {
            double amount = results.getDouble("sum");
            return String.valueOf(amount);
        }
        return null;
    }

    public double getSum() throws SQLException {
        ResultSet results = CrudUtil.execute("SELECT SUM(payment_amount) AS sum FROM payment");
        if (results.next()) {
            double amount = results.getDouble("sum");
            return amount;
        }
        return 0;
    }
}
