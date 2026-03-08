package lk.ijse.zanystore.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.zanystore.bo.custom.PaymentBO;
import lk.ijse.zanystore.dao.DAOFactory;
import lk.ijse.zanystore.dao.custom.OrderPaymentDAO;
import lk.ijse.zanystore.dao.custom.PaymentDAO;
import lk.ijse.zanystore.dao.custom.QuotationDAO;
import lk.ijse.zanystore.dao.custom.impl.OrderPaymentDAOImpl;
import lk.ijse.zanystore.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.zanystore.db.DBConnection;
import lk.ijse.zanystore.dto.PaymentDTO;
import lk.ijse.zanystore.entity.OrderPayment;
import lk.ijse.zanystore.entity.Payment;

import java.sql.Connection;
import java.sql.SQLException;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);
    OrderPaymentDAO orderPaymentDAO = (OrderPaymentDAO)DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CLOTH_ORDER_PAYMENT);

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

            String paymentId = paymentDAO.showNextId();
            int id = Integer.parseInt(paymentId) - 1;

            boolean b2 = orderPaymentDAO.save(new OrderPayment(Integer.parseInt(oId),id,Integer.parseInt(cId),date));

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
