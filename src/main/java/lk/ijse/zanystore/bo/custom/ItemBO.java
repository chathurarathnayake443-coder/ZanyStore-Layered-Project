package lk.ijse.zanystore.bo.custom;

import lk.ijse.zanystore.db.DBConnection;
import lk.ijse.zanystore.dto.QueryDTO.LoadItemDTO;
import lk.ijse.zanystore.entity.Item;
import lk.ijse.zanystore.entity.ItemColorStock;
import lk.ijse.zanystore.bo.custom.SuperBO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ItemBO extends SuperBO {

    public boolean saveItem(String color, int qtyToAdd, String name, String type, double price);

    public int createId(String name, String type, double price) throws SQLException;

    public boolean deleteItem(int itemId) throws SQLException;

    public List<String> loadItemNames() throws SQLException;

    public List<String> populateColors(int itemId) throws SQLException;

    public int getItemIdByName(String name) throws SQLException;

    public int getTotalQtyForItem(int itemId) throws SQLException;

    public int getColorQty(int itemId, String color) throws SQLException;

    public String showNextId() throws SQLException;

    public List<LoadItemDTO> loadItemTable() throws SQLException;
}
