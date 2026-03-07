package lk.ijse.zanystore.dao.custom.impl;

import lk.ijse.zanystore.dto.ReturnDTO;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReturnDAOImpl {

    public boolean save(ReturnDTO returnDTO) throws SQLException {
        boolean result = CrudUtil.execute("INSERT INTO return_order (return_order_details) VALUES (?)", returnDTO.getReturn_order_details());
        return result;
    }

    public String showNextId() throws SQLException {
        ResultSet result = CrudUtil.execute("SELECT return_order_id FROM return_order ORDER BY return_order_id DESC LIMIT 1");
        if (result.next()) {
            int id = result.getInt("return_order_id");
            return String.valueOf(id + 1);
        }
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

    public boolean delete(String id) throws SQLException {
        boolean result = CrudUtil.execute("DELETE FROM return_order WHERE return_order_id = ?",id);
        return result;
    }
}
