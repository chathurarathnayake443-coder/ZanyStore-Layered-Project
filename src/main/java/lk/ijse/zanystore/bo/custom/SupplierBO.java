package lk.ijse.zanystore.bo.custom;

import lk.ijse.zanystore.dto.SupplierDTO;
import lk.ijse.zanystore.entity.Supplier;
import lk.ijse.zanystore.bo.custom.SuperBO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierBO extends SuperBO {

    public boolean addSupplier(SupplierDTO supplier) throws SQLException;

    public boolean updateSupplier(SupplierDTO supplier) throws SQLException;

    public boolean deleteSupplier(String id) throws SQLException;

    public List<SupplierDTO> getAllSupplier() throws SQLException;

    public String generateNextSupplierId() throws SQLException;
}
