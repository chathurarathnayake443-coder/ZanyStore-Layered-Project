package lk.ijse.zanystore.dao.custom;

import lk.ijse.zanystore.dao.CrudDAO;
import lk.ijse.zanystore.dto.SupplierDTO;
import lk.ijse.zanystore.entity.Supplier;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierDAO extends CrudDAO<Supplier> {

    public boolean save(Supplier entity) throws SQLException;

    public boolean update(Supplier entity) throws SQLException;

    public boolean delete(int id) throws SQLException;

    public List<Supplier> getAll() throws SQLException;

    public String showNextId() throws SQLException;
}
