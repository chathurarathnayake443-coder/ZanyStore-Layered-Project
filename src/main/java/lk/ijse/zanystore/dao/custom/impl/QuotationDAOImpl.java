package lk.ijse.zanystore.dao.custom.impl;

import lk.ijse.zanystore.dao.custom.QuotationDAO;
import lk.ijse.zanystore.dto.QuotationItemDTO;
import lk.ijse.zanystore.entity.Quotation;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class QuotationDAOImpl implements QuotationDAO {

    public String getId() throws SQLException {
        ResultSet result = CrudUtil.execute("SELECT quotation_id FROM quotation ORDER BY quotation.quotation_id DESC LIMIT 1");

        if (result.next()) {
            int id = result.getInt("quotation_id");
            return String.valueOf(id);
        }
        return null;
    }

    public boolean save(Quotation entity) throws SQLException {
        boolean result = CrudUtil.execute("INSERT INTO quotation (customer_name, total_amount) VALUES (?,?)", entity.getCustomer_name(),entity.getTotal_amount());
        return result;
    }

    @Override
    public boolean update(Quotation entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public List<Quotation> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public String showNextId() throws SQLException {
        return "";
    }

    @Override
    public Quotation find(int empId) throws SQLException {
        return null;
    }
}
