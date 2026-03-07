package lk.ijse.zanystore.dao.custom.impl;

import lk.ijse.zanystore.dao.custom.SupplierDAO;
import lk.ijse.zanystore.dto.SupplierDTO;
import lk.ijse.zanystore.entity.Supplier;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {

    public boolean save(Supplier entity) throws SQLException {
        boolean result = CrudUtil.execute("INSERT INTO supplier (supplier_name, supplier_item, supplier_address, supplier_contact_no) VALUES (?,?,?,?)",
                entity.getSupplier_name(),entity.getSupplier_item(),entity.getSupplier_address(),entity.getSupplier_contact_no());

        return result;
    }

    public boolean update(Supplier entity) throws SQLException{
        boolean result = CrudUtil.execute("UPDATE supplier SET supplier_name = ?, supplier_item = ?, supplier_address = ?, supplier_contact_no = ? WHERE supplier_id = ?",
                entity.getSupplier_name(),entity.getSupplier_item(),entity.getSupplier_address(),entity.getSupplier_contact_no(),entity.getSupplier_id()
        );

        return result;
    }

    public boolean delete(int id) throws SQLException{
        boolean result = CrudUtil.execute("DELETE FROM supplier WHERE supplier_id = ?", id);

        return result;
    }

    public List<Supplier> getAll() throws SQLException{

        ResultSet results = CrudUtil.execute("SELECT * FROM supplier");

        List<Supplier> supplierList = new ArrayList<>();

        while(results.next()){
            int id = results.getInt("supplier_id");
            String name = results.getString("supplier_name");
            String item = results.getString("supplier_item");
            String address = results.getString("supplier_address");
            String contact = results.getString("supplier_contact_no");

            supplierList.add(new Supplier(id,name,address,item,contact));
        }
        return supplierList;
    }

    public String showNextId() throws SQLException {
        ResultSet result = CrudUtil.execute("SELECT supplier_id FROM supplier ORDER BY supplier_id DESC LIMIT 1");

        if(result.next()){
            int supplier_id = result.getInt("supplier_id");
            String id = String.valueOf(supplier_id+1);
            return id;
        }
        return null;
    }

    @Override
    public Supplier find(int empId) throws SQLException {
        return null;
    }

    @Override
    public String getId() throws SQLException {
        return "";
    }
}
