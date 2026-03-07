/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.zanystore.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lk.ijse.zanystore.dto.SupplierDTO;
import lk.ijse.zanystore.util.CrudUtil;

/**
 *
 * @author chathura
 */
public class SupplierModel {

    public boolean clickAddSupplier(SupplierDTO supplierDTO) throws SQLException{
        boolean result = CrudUtil.execute("INSERT INTO supplier (supplier_name, supplier_item, supplier_address, supplier_contact_no) VALUES (?,?,?,?)",
                supplierDTO.getSupplier_name(),supplierDTO.getSupplier_item(),supplierDTO.getSupplier_address(),supplierDTO.getSupplier_contact_no());

        return result;
    }

    public boolean clickUpdateSupplier(SupplierDTO supplierDTO) throws SQLException{
        boolean result = CrudUtil.execute("UPDATE supplier SET supplier_name = ?, supplier_item = ?, supplier_address = ?, supplier_contact_no = ? WHERE supplier_id = ?",
                supplierDTO.getSupplier_name(),supplierDTO.getSupplier_item(),supplierDTO.getSupplier_address(),supplierDTO.getSupplier_contact_no(),supplierDTO.getSupplier_id()
                );

        return result;
    }

    public boolean clickDeleteSupplier(int id) throws SQLException{
        boolean result = CrudUtil.execute("DELETE FROM supplier WHERE supplier_id = ?", id);

        return result;
    }

    public List<SupplierDTO> loadSupplierTable() throws SQLException{

        ResultSet results = CrudUtil.execute("SELECT * FROM supplier");

        List<SupplierDTO> supplierList = new ArrayList<>();

        while(results.next()){
            int id = results.getInt("supplier_id");
                String name = results.getString("supplier_name");
                String item = results.getString("supplier_item");
                String address = results.getString("supplier_address");
                String contact = results.getString("supplier_contact_no");

                SupplierDTO supplierDTO = new SupplierDTO(id,name,address,item,contact);
                supplierList.add(supplierDTO);
        }
        return supplierList;
    }
}
