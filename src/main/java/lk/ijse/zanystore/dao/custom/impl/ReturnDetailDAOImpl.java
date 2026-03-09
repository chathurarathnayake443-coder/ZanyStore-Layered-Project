package lk.ijse.zanystore.dao.custom.impl;

import lk.ijse.zanystore.dao.custom.ReturnDetailDAO;
import lk.ijse.zanystore.dto.ReturnDTO;
import lk.ijse.zanystore.entity.ReturnDetail;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class ReturnDetailDAOImpl implements ReturnDetailDAO {

    public boolean save(ReturnDetail entity) throws SQLException {
        boolean result = CrudUtil.execute("INSERT INTO return_order_details (cloth_order_id, return_order_id, order_return_date) VALUES (?,?,?)",entity.getCloth_order_id(),entity.getReturn_order_id(),entity.getOrder_return_date());
        return result;
    }

    @Override
    public boolean update(ReturnDetail entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        boolean result = CrudUtil.execute("DELETE FROM return_order_details WHERE return_order_id = ?",id);
        return result;
    }

    @Override
    public List<ReturnDetail> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public String showNextId() throws SQLException {
        return "";
    }

    @Override
    public ReturnDetail find(int empId) throws SQLException {
        return null;
    }

    @Override
    public String getId() throws SQLException {
        return "";
    }
}
