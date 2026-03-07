package lk.ijse.zanystore.dao.custom;

import lk.ijse.zanystore.dao.CrudDAO;
import lk.ijse.zanystore.dto.QuotationDTO;
import lk.ijse.zanystore.entity.Quotation;
import lk.ijse.zanystore.entity.QuotationItem;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.SQLException;

public interface QuotationItemDAO extends CrudDAO<QuotationItem> {

    public boolean save(QuotationItem entity) throws SQLException;
}
