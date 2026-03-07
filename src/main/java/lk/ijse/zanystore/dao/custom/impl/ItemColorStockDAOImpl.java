package lk.ijse.zanystore.dao.custom.impl;

import lk.ijse.zanystore.dao.custom.ItemColorStockDAO;
import lk.ijse.zanystore.dto.ItemColorStockDTO;
import lk.ijse.zanystore.entity.ItemColorStock;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemColorStockDAOImpl implements ItemColorStockDAO {

    public boolean update(ItemColorStock entity) throws SQLException {
        boolean result = CrudUtil.execute("UPDATE item_color_stock SET qty = qty + ? WHERE item_id = ? AND color = ?", entity.getQty(),entity.getItem_id(),entity.getColor());
        return result;
    }

    public boolean save(ItemColorStock entity) throws SQLException {
        boolean result = CrudUtil.execute("INSERT INTO item_color_stock (item_id, color, qty) VALUES (?, ?, ?)",entity.getItem_id(),entity.getColor(),entity.getQty());
        return result;
    }

    public boolean delete(int id) throws SQLException {
        boolean result = CrudUtil.execute("DELETE FROM item_color_stock WHERE item_id = ?",id);
        return result;
    }

    @Override
    public List<ItemColorStock> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public String showNextId() throws SQLException {
        return "";
    }

    @Override
    public ItemColorStock find(int empId) throws SQLException {
        return null;
    }

    @Override
    public String getId() throws SQLException {
        return "";
    }

    public List<String> getColors(int itemId) throws SQLException {
        ResultSet results = CrudUtil.execute("SELECT color FROM item_color_stock WHERE item_id = ? ORDER BY color",itemId);
        List<String> colorList = new ArrayList<>();
        while (results.next()) {
            colorList.add(results.getString("color"));
        }
        return colorList;
    }

    public int getTotalQty(int ItemId) throws SQLException {
        ResultSet results = CrudUtil.execute("SELECT COALESCE(SUM(qty),0) AS total_qty FROM item_color_stock WHERE item_id = ?",ItemId);
        if (results.next()) {
            return results.getInt("total_qty");
        }
        return 0;
    }

    public int getColorQty(int itemId, String color) throws SQLException {
        ResultSet results = CrudUtil.execute("SELECT qty FROM item_color_stock WHERE item_id = ? AND color = ?",itemId,color);
        if (results.next()) {
            return results.getInt("qty");
        }
        return 0;
    }

    public List<String> getColorsById(int id) throws SQLException {
        ResultSet result = CrudUtil.execute("SELECT color FROM item_color_stock WHERE item_id = ?",id);
        List<String> colorList = new ArrayList<>();
        while (result.next()) {
            String color = result.getString("color");
            colorList.add(color);
        }
        return colorList;
    }

    public boolean decreaseQty(ItemColorStock entity) throws SQLException {
        boolean result = CrudUtil.execute("UPDATE item_color_stock SET qty = qty - ? WHERE item_id = ? AND color = ? AND qty >= ?", entity.getQty(), entity.getItem_id(), entity.getColor(), entity.getQty());
        return result;
    }
}
