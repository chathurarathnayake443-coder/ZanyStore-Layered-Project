package lk.ijse.zanystore.dao.custom;

import lk.ijse.zanystore.dto.QuotationItemDTO;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface QuotationDAO {

    public int getId() throws SQLException;

    public boolean save(QuotationItemDTO quotationItemDTO) throws SQLException;
}
