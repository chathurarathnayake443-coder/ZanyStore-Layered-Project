package lk.ijse.zanystore.dao.custom;

import lk.ijse.zanystore.dto.ReturnDTO;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ReturnDAO {

    public boolean save(ReturnDTO returnDTO) throws SQLException;

    public String showNextId() throws SQLException;

    public String getId() throws SQLException;

    public boolean delete(String id) throws SQLException;
}
