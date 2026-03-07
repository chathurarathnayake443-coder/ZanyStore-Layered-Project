package lk.ijse.zanystore.dao.custom.impl;

import lk.ijse.zanystore.dto.ReturnDTO;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.SQLException;

public class ReturnDetailDAOImpl {

    public boolean save(ReturnDTO returnDTO) throws SQLException {
        boolean result = CrudUtil.execute("INSERT INTO return_order_details (cloth_order_id, return_order_id, order_return_date) VALUES (?,?,?)",returnDTO.getCloth_order_id(),returnDTO.getReturn_order_id(),returnDTO.getReturn_date());
        return result;
    }

    public boolean delete(String id) throws SQLException {
        boolean result = CrudUtil.execute("DELETE FROM return_order_details WHERE return_order_id = ?",id);
        return result;
    }
}
