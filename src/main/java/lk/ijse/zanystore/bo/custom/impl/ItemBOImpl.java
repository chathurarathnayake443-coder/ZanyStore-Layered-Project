package lk.ijse.zanystore.bo.custom.impl;

import lk.ijse.zanystore.bo.custom.ItemBO;
import lk.ijse.zanystore.dao.DAOFactory;
import lk.ijse.zanystore.dao.custom.ItemColorStockDAO;
import lk.ijse.zanystore.dao.custom.ItemDAO;
import lk.ijse.zanystore.dao.custom.QuotationDAO;
import lk.ijse.zanystore.dao.custom.impl.ItemColorStockDAOImpl;
import lk.ijse.zanystore.dao.custom.impl.ItemDAOImpl;
import lk.ijse.zanystore.db.DBConnection;
import lk.ijse.zanystore.dto.ItemColorStockDTO;
import lk.ijse.zanystore.dto.ItemDTO;
import lk.ijse.zanystore.entity.Item;
import lk.ijse.zanystore.entity.ItemColorStock;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);
    ItemColorStockDAO itemColorStockDAO = (ItemColorStockDAO)DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM_COLOR_STOCK);

    public boolean saveItem(String color, int qtyToAdd, String name, String type, double price) {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            int itemId = createId(name, type, price);

            boolean b1 = itemColorStockDAO.update(new ItemColorStock(itemId, color, qtyToAdd));

            if (!b1) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            boolean b2 = itemColorStockDAO.save(new ItemColorStock(itemId, color, qtyToAdd));

            if (!b2) {
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

    public int createId(String name, String type, double price) throws SQLException {
        Item item = itemDAO.find(name);
        if(!(item == null)){
            return item.getItem_id();
        }
        boolean saved = itemDAO.save(new Item(name,type,price));
        if (!saved) {
            throw new SQLException("Item insert failed");
        }
        Item newItem = itemDAO.find(name);
        return newItem.getItem_id();
    }

    public boolean deleteItemColors(int itemId) throws SQLException {
        return itemColorStockDAO.delete(itemId);
    }

    public boolean deleteItem(int itemId) throws SQLException {
        return itemDAO.delete(itemId);
    }

    public List<String> loadItemNames() throws SQLException {
        List<String> itemNames = itemDAO.getNames();
        return itemNames;
    }

    public List<String> populateColors(int itemId) throws SQLException {
        List<String> colors = itemColorStockDAO.getColors(itemId);
        return colors;
    }

    public int getItemIdByName(String name) throws SQLException {
        int id = itemDAO.getIds(name);
        return id;
    }

    public int getTotalQtyForItem(int itemId) throws SQLException {
        int qty = itemColorStockDAO.getTotalQty(itemId);
        return qty;
    }

    public int getColorQty(int itemId, String color) throws SQLException {
        int qty = itemColorStockDAO.getColorQty(itemId, color);
        return qty;
    }

    public String showNextId() throws SQLException {
        String id = itemDAO.showNextId();
        return id;
    }

}
