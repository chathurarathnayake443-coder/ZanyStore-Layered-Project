package lk.ijse.zanystore.dao.custom.impl;

import lk.ijse.zanystore.dao.custom.ClothOrderDAO;
import lk.ijse.zanystore.dto.OrderDTO;
import lk.ijse.zanystore.entity.ClothOrder;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClothOrderDAOImpl implements ClothOrderDAO {

    public String showNextId() throws SQLException {
        ResultSet results = CrudUtil.execute("SELECT cloth_order_id FROM cloth_order ORDER BY cloth_order_id DESC LIMIT 1");
        if (results.next()) {
            int  id = results.getInt("cloth_order_id");
            return String.valueOf(id + 1);
        }
        return null;
    }

    @Override
    public ClothOrder find(int empId) throws SQLException {
        return null;
    }

    public boolean save(ClothOrder entity) throws SQLException {
        boolean result = CrudUtil.execute("INSERT INTO cloth_order (customer_id, cloth_order_description, cloth_order_start_date, cloth_order_end_date) VALUES (?,?,?,?)", entity.getCustomer_id(), entity.getCloth_order_description(), entity.getCloth_order_start_date(),entity.getCloth_order_end_date());
        return result;
    }

    @Override
    public boolean update(ClothOrder entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public List<ClothOrder> getAll() throws SQLException {
        return List.of();
    }

    public String getId() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT cloth_order_id FROM cloth_order ORDER BY cloth_order_id DESC LIMIT 1");
        if (rs.next()) {
            return String.valueOf(rs.getInt("cloth_order_id"));
        }
        return null;
    }

    public List<String> getAllId() throws SQLException {
        ResultSet results = CrudUtil.execute("SELECT cloth_order_id FROM cloth_order ORDER BY cloth_order_id DESC");
        List<String> list = new ArrayList<>();
        while (results.next()) {
            list.add(String.valueOf(results.getInt("cloth_order_id")));
        }
        return list;
    }

    public int getOrderCount() throws SQLException {
        ResultSet result = CrudUtil.execute("SELECT COUNT(cloth_order_id) AS total_orders FROM cloth_order");
        if (result.next()) {
            return result.getInt("total_orders");
        }
        return 0;
    }
}
