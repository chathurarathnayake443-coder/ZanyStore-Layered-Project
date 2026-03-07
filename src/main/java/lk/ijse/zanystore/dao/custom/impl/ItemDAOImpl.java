package lk.ijse.zanystore.dao.custom.impl;

import lk.ijse.zanystore.dao.custom.ItemDAO;
import lk.ijse.zanystore.dto.ItemDTO;
import lk.ijse.zanystore.entity.Item;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    public Item find(String name) throws SQLException {
        ResultSet result = CrudUtil.execute("SELECT item_id FROM item WHERE item_name = ? LIMIT 1",name);
        if (result.next()) {
            return  new Item(result.getInt("item_id"));
        }
        return null;
    }

    public boolean save(Item entity) throws SQLException {
        boolean result = CrudUtil.execute("INSERT INTO item (item_name, item_type, item_unit_price) VALUES (?, ?, ?)",entity.getItem_name(),entity.getItem_type(),entity.getItem_unit_price());
        return result;
    }

    @Override
    public boolean update(Item entity) throws SQLException {
        return false;
    }

    public boolean delete(int id) throws SQLException {
        boolean result = CrudUtil.execute("DELETE FROM item WHERE item_id = ?",id);
        return result;
    }

    @Override
    public List<Item> getAll() throws SQLException {
        return List.of();
    }

    public String showNextId() throws SQLException {
        ResultSet result = CrudUtil.execute("SELECT item_id FROM item ORDER BY item_id DESC LIMIT 1");
        if (result.next()) {
            int item_id = result.getInt("item_id");
            String id = String.valueOf(item_id+1);
            return id;
        }
        return null;
    }

    @Override
    public Item find(int empId) throws SQLException {
        return null;
    }

    @Override
    public String getId() throws SQLException {
        return "";
    }

    public List<String> getNames() throws SQLException {
        ResultSet results = CrudUtil.execute("SELECT item_name FROM item ORDER BY item_name");
        List<String> list = new ArrayList<>();
        while (results.next()) {
            String name = results.getString("item_name");
            list.add(name);
        }
        return list;
    }

    public int getIds(String itemName) throws SQLException {
        ResultSet results = CrudUtil.execute("SELECT item_id FROM item WHERE item_name = ? LIMIT 1",itemName);
        if (results.next()) {
            int item_id = results.getInt("item_id");
            return item_id;
        }
        return 0;
    }

    public Double getPrice(String itemName) throws SQLException {
        ResultSet results = CrudUtil.execute("SELECT item_unit_price from item WHERE item_name = ?",itemName);
        if (results.next()) {
            double price = results.getDouble("item_unit_price");
            return price;
        }
        return null;
    }

    public String getNameById(int id) throws SQLException {
        ResultSet result = CrudUtil.execute("SELECT item_name FROM item WHERE item_id = ?",id);
        if (result.next()) {
            String name = result.getString("item_name");
            return name;
        }
        return null;
    }
}
