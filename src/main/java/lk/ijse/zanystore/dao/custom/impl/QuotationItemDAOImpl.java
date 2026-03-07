package lk.ijse.zanystore.dao.custom.impl;

import lk.ijse.zanystore.dao.custom.QuotationItemDAO;
import lk.ijse.zanystore.dto.QuotationDTO;
import lk.ijse.zanystore.dto.QuotationItemDTO;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.SQLException;

public class QuotationItemDAOImpl implements QuotationItemDAO {

    public boolean save(QuotationDTO quotationDTO) throws SQLException {
        boolean result = CrudUtil.execute("INSERT INTO quotation_item (quotation_id,item_name, color,quantity,unit_price) VALUES (?,?,?,?,?)", quotationDTO.getqId(), quotationDTO.getItemName(),quotationDTO.getColor(),quotationDTO.getQty(),quotationDTO.getUnitPrice());
        return result;
    }
}
