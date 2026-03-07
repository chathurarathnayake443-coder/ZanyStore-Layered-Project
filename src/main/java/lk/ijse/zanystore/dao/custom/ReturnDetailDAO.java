package lk.ijse.zanystore.dao.custom;

import lk.ijse.zanystore.dao.CrudDAO;
import lk.ijse.zanystore.dto.ReturnDTO;
import lk.ijse.zanystore.entity.ReturnDetail;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.SQLException;

public interface ReturnDetailDAO extends CrudDAO<ReturnDetail> {

    public boolean save(ReturnDetail entity) throws SQLException;

    public boolean delete(String id) throws SQLException;
}
