package lk.ijse.zanystore.dao.custom;

import lk.ijse.zanystore.dao.CrudDAO;
import lk.ijse.zanystore.dto.ReturnDTO;
import lk.ijse.zanystore.entity.Return;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ReturnDAO extends CrudDAO<Return> {

    public int getTotalCount() throws SQLException;
}
