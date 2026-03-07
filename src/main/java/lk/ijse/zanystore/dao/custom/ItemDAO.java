package lk.ijse.zanystore.dao.custom;

import lk.ijse.zanystore.dto.ItemDTO;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemDAO {

    public ItemDTO find(String name) throws SQLException;

    public boolean save(ItemDTO itemDTO) throws SQLException;

    public boolean delete(int id) throws SQLException;

    public String showNextId() throws SQLException;

    public List<String> getNames() throws SQLException;

    public int getIds(String itemName) throws SQLException;

    public Double getPrice(String itemName) throws SQLException;

    public String getNameById(int id) throws SQLException;
}
