package lk.ijse.zanystore.dao.custom;

import lk.ijse.zanystore.dao.CrudDAO;
import lk.ijse.zanystore.entity.Payment;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface PaymentDAO extends CrudDAO<Payment> {
    public double getSum() throws SQLException;

    public String findTotal() throws SQLException;
}
