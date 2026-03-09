package lk.ijse.zanystore.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.zanystore.bo.custom.CreateQuotationBO;
import lk.ijse.zanystore.dao.DAOFactory;
import lk.ijse.zanystore.dao.custom.ItemDAO;
import lk.ijse.zanystore.dao.custom.QuotationDAO;
import lk.ijse.zanystore.dao.custom.QuotationItemDAO;
import lk.ijse.zanystore.dao.custom.impl.ItemDAOImpl;
import lk.ijse.zanystore.dao.custom.impl.QuotationDAOImpl;
import lk.ijse.zanystore.dao.custom.impl.QuotationItemDAOImpl;
import lk.ijse.zanystore.db.DBConnection;
import lk.ijse.zanystore.dto.QuotationDTO;
import lk.ijse.zanystore.dto.QuotationItemDTO;
import lk.ijse.zanystore.entity.Quotation;
import lk.ijse.zanystore.entity.QuotationItem;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreateQuotationBOImpl implements CreateQuotationBO {

    QuotationDAO quotationDAO = (QuotationDAO)DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUOTATION);
    QuotationItemDAO quotationItemDAO = (QuotationItemDAO)DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUOTATION_ITEM);
    ItemDAO itemDAO = (ItemDAO)DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);

    public List<String> loadItemNames() throws SQLException {
        List<String> itemNames = itemDAO.getNames();
        return itemNames;
    }

    public double getPriceForItem(String itemName) throws SQLException {
        double price = itemDAO.getPrice(itemName);
        return price;
    }

    public boolean placeQuotation(String customerName, double fullTotal, List<QuotationDTO>quotationItemList) throws SQLException {
        Connection connection = null;
        try{
            connection= DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean r1 = quotationDAO.save(new Quotation(customerName, fullTotal));

            if (!r1) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            String newId = quotationDAO.getId();

            for (QuotationDTO quotationDTO : quotationItemList) {
                boolean r2 = quotationItemDAO.save(new QuotationItem(Integer.parseInt(newId), quotationDTO.getItemName(),quotationDTO.getColor(), (int) quotationDTO.getQty(),quotationDTO.getUnitPrice()));

                if (!r2) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch (Exception e) {
            try { if (connection != null) connection.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
        } finally {
            try { if (connection != null) connection.setAutoCommit(true); } catch (SQLException ex) { ex.printStackTrace(); }
        }
        return true;
    }


}
