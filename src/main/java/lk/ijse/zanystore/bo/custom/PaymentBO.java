package lk.ijse.zanystore.bo.custom;

import javafx.scene.control.Alert;
import lk.ijse.zanystore.db.DBConnection;
import lk.ijse.zanystore.entity.OrderPayment;
import lk.ijse.zanystore.entity.Payment;
import lk.ijse.zanystore.bo.custom.SuperBO;
import java.sql.Connection;
import java.sql.SQLException;

public interface PaymentBO extends SuperBO {

    public String generateNextPaymentId() throws SQLException;

    public boolean savePayment(String amount, String method, String oId, String pId, String cId, String date);

    public String getTotalAmount() throws SQLException;
}
