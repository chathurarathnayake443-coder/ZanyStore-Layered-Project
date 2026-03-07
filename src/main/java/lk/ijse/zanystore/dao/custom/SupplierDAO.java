package lk.ijse.zanystore.dao.custom;

import lk.ijse.zanystore.dto.SupplierDTO;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierDAO {

    public boolean save(SupplierDTO supplierDTO) throws SQLException;

    public boolean update(SupplierDTO supplierDTO) throws SQLException;

    public boolean delete(int id) throws SQLException;

    public List<SupplierDTO> getAll() throws SQLException;

    public String showNextId() throws SQLException;
}
