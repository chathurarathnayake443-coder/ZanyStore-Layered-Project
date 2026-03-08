package lk.ijse.zanystore.bo.custom.impl;

import lk.ijse.zanystore.bo.custom.SupplierBO;
import lk.ijse.zanystore.dao.custom.SupplierDAO;
import lk.ijse.zanystore.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.zanystore.dto.SupplierDTO;
import lk.ijse.zanystore.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO = new SupplierDAOImpl();

    public boolean addSupplier(SupplierDTO supplier) throws SQLException {
        boolean result = supplierDAO.save(new Supplier(supplier.getSupplier_name(),supplier.getSupplier_item(),supplier.getSupplier_address(),supplier.getSupplier_contact_no()));
        return result;
    }

    public boolean updateSupplier(SupplierDTO supplier) throws SQLException {
        boolean result = supplierDAO.update(new Supplier(supplier.getSupplier_id(),supplier.getSupplier_name(),supplier.getSupplier_item(),supplier.getSupplier_address(),supplier.getSupplier_contact_no()));
        return result;
    }

    public boolean deleteSupplier(String id) throws SQLException {
        boolean result = supplierDAO.delete(Integer.parseInt(id));
        return result;
    }

    public List<SupplierDTO> getAllSupplier() throws SQLException {
        List<SupplierDTO> supplierDTOList = new ArrayList<>();
        List<Supplier> supplierList = supplierDAO.getAll();
        for (Supplier supplier : supplierList) {
            supplierDTOList.add(new SupplierDTO(supplier.getSupplier_id(),supplier.getSupplier_name(),supplier.getSupplier_item(),supplier.getSupplier_address(),supplier.getSupplier_contact_no()));
        }
        return supplierDTOList;
    }

    public String generateNextSupplierId() throws SQLException {
        String id = supplierDAO.showNextId();
        return id;
    }
}
