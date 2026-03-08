package lk.ijse.zanystore.dao.custom;

import lk.ijse.zanystore.dao.CrudDAO;
import lk.ijse.zanystore.dto.ReturnDTO;
import lk.ijse.zanystore.entity.Return;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ReturnDAO extends CrudDAO<Return> {

    public boolean save(Return entity) throws SQLException;

    public String showNextId() throws SQLException;

    public String getId() throws SQLException;

    public boolean delete(String id) throws SQLException;

    public int getTotalCount() throws SQLException;
}
