package lk.ijse.zanystore.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.zanystore.bo.custom.ReturnBO;
import lk.ijse.zanystore.dao.DAOFactory;
import lk.ijse.zanystore.dao.custom.QuotationDAO;
import lk.ijse.zanystore.dao.custom.ReturnDAO;
import lk.ijse.zanystore.dao.custom.ReturnDetailDAO;
import lk.ijse.zanystore.dao.custom.impl.ReturnDAOImpl;
import lk.ijse.zanystore.dao.custom.impl.ReturnDetailDAOImpl;
import lk.ijse.zanystore.db.DBConnection;
import lk.ijse.zanystore.dto.ReturnDTO;
import lk.ijse.zanystore.entity.Return;
import lk.ijse.zanystore.entity.ReturnDetail;

import java.sql.Connection;
import java.sql.SQLException;

public class ReturnBOImpl implements ReturnBO {

    ReturnDAO returnDAO = (ReturnDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RETURN);
    ReturnDetailDAO returnDetailDAO = (ReturnDetailDAO)DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RETURN_DETAIL);

    public boolean saveReturn(String detail, String orderId, String date) {
        Connection connection = null;
        try{
            connection= DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean r1 = returnDAO.save(new Return(detail));

            if (!r1) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            String id = returnDAO.getId();

            boolean r2 = returnDetailDAO.save(new ReturnDetail(Integer.parseInt(orderId),Integer.parseInt(id),date));

            if (!r2) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        }
        catch (Exception e) {
            try { if (connection != null) connection.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
        } finally {
            try { if (connection != null) connection.setAutoCommit(true); } catch (SQLException ex) { ex.printStackTrace(); }
        }
        return true;
    }

    public boolean deleteReturn(int returnId) {
        Connection connection = null;
        try{
            connection= DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean b1 = returnDetailDAO.delete(returnId);

            if (!b1) {
                connection.rollback();
                connection.setAutoCommit(true);
                //return false;
            }

            boolean b2 = returnDAO.delete(returnId);

            if (!b2) {
                connection.rollback();
                connection.setAutoCommit(true);
                //return false;
            }
        }
        catch (Exception e) {
            try { if (connection != null) connection.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
        } finally {
            try { if (connection != null) connection.setAutoCommit(true); } catch (SQLException ex) { ex.printStackTrace(); }
        }
        return true;
    }

    public String generateNextReturnId() throws SQLException {
        String id = returnDAO.showNextId();
        return id;
    }
}
