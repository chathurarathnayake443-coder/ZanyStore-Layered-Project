package lk.ijse.zanystore.dao.custom.impl;

import lk.ijse.zanystore.dao.custom.ReturnDAO;
import lk.ijse.zanystore.dto.ReturnDTO;
import lk.ijse.zanystore.entity.Return;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReturnDAOImpl implements ReturnDAO {

    public boolean save(Return entity) throws SQLException {
        boolean result = CrudUtil.execute("INSERT INTO return_order (return_order_details) VALUES (?)", entity.getReturn_order_details());
        return result;
    }

    @Override
    public boolean update(Return entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        boolean result = CrudUtil.execute("DELETE FROM return_order WHERE return_order_id = ?",id);
        return result;
    }

    @Override
    public List<Return> getAll() throws SQLException {
        return List.of();
    }

    public String showNextId() throws SQLException {
        ResultSet result = CrudUtil.execute("SELECT return_order_id FROM return_order ORDER BY return_order_id DESC LIMIT 1");
        if (result.next()) {
            int id = result.getInt("return_order_id");
            return String.valueOf(id + 1);
        }
        return null;
    }

    @Override
    public Return find(int empId) throws SQLException {
        return null;
    }

    public String getId() throws SQLException {
        ResultSet result = CrudUtil.execute("SELECT return_order_id FROM return_order ORDER BY return_order_id DESC LIMIT 1");
        if (result.next()) {
            int id = result.getInt("return_order_id");
            return String.valueOf(id);
        }
        return null;
    }

    public int getTotalCount() throws SQLException {
        ResultSet results = CrudUtil.execute("SELECT COUNT(return_order_id) AS total_returns FROM return_order");
        if (results.next()) {
            return results.getInt("total_returns");
        }
        return 0;
    }
}
