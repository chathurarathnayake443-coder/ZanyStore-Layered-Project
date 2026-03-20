package lk.ijse.zanystore.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.zanystore.bo.custom.ReturnBO;
import lk.ijse.zanystore.dao.DAOFactory;
import lk.ijse.zanystore.dao.custom.QueryDAO;
import lk.ijse.zanystore.dao.custom.QuotationDAO;
import lk.ijse.zanystore.dao.custom.ReturnDAO;
import lk.ijse.zanystore.dao.custom.ReturnDetailDAO;
import lk.ijse.zanystore.dao.custom.impl.ReturnDAOImpl;
import lk.ijse.zanystore.dao.custom.impl.ReturnDetailDAOImpl;
import lk.ijse.zanystore.db.DBConnection;
import lk.ijse.zanystore.dto.QueryDTO.LoadReturnDTO;
import lk.ijse.zanystore.dto.ReturnDTO;
import lk.ijse.zanystore.entity.Return;
import lk.ijse.zanystore.entity.ReturnDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReturnBOImpl implements ReturnBO {

    ReturnDAO returnDAO = (ReturnDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RETURN);
    ReturnDetailDAO returnDetailDAO = (ReturnDetailDAO)DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RETURN_DETAIL);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);

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

            boolean r2 = returnDetailDAO.save(new ReturnDetail(Integer.parseInt(id),Integer.parseInt(orderId),date));

            if (!r2) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch (Exception e) {
            try { if (connection != null) connection.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
            return false;
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
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch (Exception e) {
            try { if (connection != null) connection.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
            return false;
        } finally {
            try { if (connection != null) connection.setAutoCommit(true); } catch (SQLException ex) { ex.printStackTrace(); }
        }
        return true;
    }

    public String generateNextReturnId() throws SQLException {
        String id = returnDAO.showNextId();
        return id;
    }

    public List<LoadReturnDTO> loadReturnTable() throws SQLException {
        List<LoadReturnDTO> list = queryDAO.loadReturnTable();
        return list;
    }
}
