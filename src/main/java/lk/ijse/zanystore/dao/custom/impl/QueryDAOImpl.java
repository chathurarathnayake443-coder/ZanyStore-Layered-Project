package lk.ijse.zanystore.dao.custom.impl;

import lk.ijse.zanystore.dao.custom.QueryDAO;
import lk.ijse.zanystore.dto.QueryDTO.LoadItemDTO;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {

    //for ItemController
    public List<LoadItemDTO> loadItemTable() throws SQLException {
        ResultSet results = CrudUtil.execute("SELECT ics.item_id, i.item_name, i.item_type, i.item_unit_price, ics.color, ics.qty " +
                "FROM item_color_stock ics JOIN item i ON ics.item_id = i.item_id");

        List<LoadItemDTO> list = new ArrayList<>();
        while (results.next()) {
            int itemId = results.getInt("item_id");
            String itemName = results.getString("item_name");
            String itemType = results.getString("item_type");
            int itemQty = results.getInt("qty");
            double price = results.getDouble("item_unit_price");
            String color = results.getString("color");

            list.add(new LoadItemDTO(itemId,itemName,itemType,price,color,itemQty));
        }
        return list;
    }
}
