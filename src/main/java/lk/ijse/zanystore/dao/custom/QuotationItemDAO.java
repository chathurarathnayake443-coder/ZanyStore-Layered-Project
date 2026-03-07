package lk.ijse.zanystore.dao.custom;

import lk.ijse.zanystore.dto.QuotationDTO;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.SQLException;

public interface QuotationItemDAO {

    public boolean save(QuotationDTO quotationDTO) throws SQLException;
}
