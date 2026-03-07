package lk.ijse.zanystore.dao.custom;

import lk.ijse.zanystore.dto.ReturnDTO;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.SQLException;

public interface ReturnDetailDAO {

    public boolean save(ReturnDTO returnDTO) throws SQLException;

    public boolean delete(String id) throws SQLException;
}
