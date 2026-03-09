package lk.ijse.zanystore.dao.custom;

import lk.ijse.zanystore.dao.CrudDAO;
import lk.ijse.zanystore.dto.ItemColorStockDTO;
import lk.ijse.zanystore.entity.ItemColorStock;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemColorStockDAO extends CrudDAO<ItemColorStock> {

    public List<String> getColors(int itemId) throws SQLException;

    public int getTotalQty(int ItemId) throws SQLException;

    public int getColorQty(int itemId, String color) throws SQLException;

    public List<String> getColorsById(int id) throws SQLException;

    public boolean decreaseQty(ItemColorStock entity) throws SQLException;

    public boolean exists(int itemId, String color) throws SQLException;
}
