package lk.ijse.zanystore.dao.custom.impl;

import lk.ijse.zanystore.dao.custom.QuotationDAO;
import lk.ijse.zanystore.dto.QuotationItemDTO;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuotationDAOImpl implements QuotationDAO {

    public int getId() throws SQLException {
        ResultSet result = CrudUtil.execute("SELECT quotation_id FROM quotation ORDER BY quotation.quotation_id DESC LIMIT 1");

        if (result.next()) {
            int id = result.getInt("quotation_id");
            return id;
        }
        return 0;
    }

    public boolean save(QuotationItemDTO quotationItemDTO) throws SQLException {
        boolean result = CrudUtil.execute("INSERT INTO quotation (customer_name, total_amount) VALUES (?,?)", quotationItemDTO.getCustomer_name(),quotationItemDTO.getFullTotal());
        return result;
    }
}
