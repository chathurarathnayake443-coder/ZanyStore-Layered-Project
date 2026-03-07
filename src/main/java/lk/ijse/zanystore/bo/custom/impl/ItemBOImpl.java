package lk.ijse.zanystore.bo.custom.impl;

import lk.ijse.zanystore.dao.custom.ItemColorStockDAO;
import lk.ijse.zanystore.dao.custom.ItemDAO;
import lk.ijse.zanystore.dao.custom.impl.ItemColorStockDAOImpl;
import lk.ijse.zanystore.dao.custom.impl.ItemDAOImpl;
import lk.ijse.zanystore.dto.ItemColorStockDTO;
import lk.ijse.zanystore.dto.ItemDTO;
import lk.ijse.zanystore.entity.Item;
import lk.ijse.zanystore.entity.ItemColorStock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl {

    ItemDAO itemDAO = new ItemDAOImpl();
    ItemColorStockDAO itemColorStockDAO = new ItemColorStockDAOImpl();

    public boolean saveItem(ItemDTO itemDTO) throws SQLException {
        return itemDAO.save(new Item(itemDTO.getItem_name(),itemDTO.getItem_type(),itemDTO.getItem_unit_price()));
    }

    public ItemDTO findItem(String name) throws SQLException {
        Item result = itemDAO.find(name);
        return new ItemDTO(result.getItem_id());
    }

    public boolean saveItemColors(ItemColorStockDTO itemDTO) throws SQLException {
        return itemColorStockDAO.save(new ItemColorStock(itemDTO.getItemId(),itemDTO.getColor(),itemDTO.getQty()));
    }

    public boolean updateItemColors(ItemColorStockDTO itemDTO) throws SQLException {
        return itemColorStockDAO.update(new ItemColorStock(itemDTO.getItemId(),itemDTO.getColor(),itemDTO.getQty()));
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
