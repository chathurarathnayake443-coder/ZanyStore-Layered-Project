package lk.ijse.zanystore.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.zanystore.bo.custom.PaymentBO;
import lk.ijse.zanystore.dao.custom.impl.OrderPaymentDAOImpl;
import lk.ijse.zanystore.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.zanystore.db.DBConnection;
import lk.ijse.zanystore.dto.PaymentDTO;
import lk.ijse.zanystore.entity.OrderPayment;
import lk.ijse.zanystore.entity.Payment;

import java.sql.Connection;
import java.sql.SQLException;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAOImpl paymentDAO = new PaymentDAOImpl();
    OrderPaymentDAOImpl orderPaymentDAO = new OrderPaymentDAOImpl();

    public String generateNextPaymentId() throws SQLException {
        String id = paymentDAO.showNextId();
        return id;
    }

    public boolean savePayment(String amount, String method, String oId, String pId, String cId, String date){
        Connection connection = null;
        try{
            connection= DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean b1 = paymentDAO.save(new Payment(Double.parseDouble(amount),method));

            if (!b1) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            boolean b2 = orderPaymentDAO.save(new OrderPayment(Integer.parseInt(oId),Integer.parseInt(pId),Integer.parseInt(cId),date));

            if (!b2) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch (Exception e) {
            try { if (connection != null) connection.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            new Alert(Alert.AlertType.ERROR, "Something went Wrong !").show();
            e.printStackTrace();
        } finally {
            try { if (connection != null) connection.setAutoCommit(true); } catch (SQLException ex) { ex.printStackTrace(); }
        }
        return true;
    }

    public String getTotalAmount() throws SQLException {
        String total = paymentDAO.findTotal();
        return total;
    }
}
