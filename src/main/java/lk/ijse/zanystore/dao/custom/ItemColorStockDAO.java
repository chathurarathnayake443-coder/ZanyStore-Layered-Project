package lk.ijse.zanystore.dao.custom;

import lk.ijse.zanystore.dto.ItemColorStockDTO;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemColorStockDAO {
    public boolean update(ItemColorStockDTO itemColorStockDTO) throws SQLException;

    public boolean save(ItemColorStockDTO itemColorStockDTO) throws SQLException;

    public boolean delete(int id) throws SQLException;

    public List<String> getColors(int itemId) throws SQLException;

    public int getTotalQty(int ItemId) throws SQLException;

    public int getColorQty(int itemId, String color) throws SQLException;

    public List<String> getColorsById(int id) throws SQLException;

    public boolean decreaseQty(ItemColorStockDTO itemColorStockDTO) throws SQLException;
}
