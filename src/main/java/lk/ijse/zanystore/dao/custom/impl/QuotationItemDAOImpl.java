package lk.ijse.zanystore.dao.custom.impl;

import lk.ijse.zanystore.dao.custom.QuotationItemDAO;
import lk.ijse.zanystore.dto.QuotationDTO;
import lk.ijse.zanystore.dto.QuotationItemDTO;
import lk.ijse.zanystore.entity.QuotationItem;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class QuotationItemDAOImpl implements QuotationItemDAO {

    public boolean save(QuotationItem entity) throws SQLException {
        boolean result = CrudUtil.execute("INSERT INTO quotation_item (quotation_id,item_name, color,quantity,unit_price) VALUES (?,?,?,?,?)", entity.getQuotation_id(), entity.getItem_name(),entity.getColor(),entity.getQuantity(),entity.getUnit_price());
        return result;
    }

    @Override
    public boolean update(QuotationItem entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public List<QuotationItem> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public String showNextId() throws SQLException {
        return "";
    }

    @Override
    public QuotationItem find(int empId) throws SQLException {
        return null;
    }

    @Override
    public String getId() throws SQLException {
        return "";
    }
}
