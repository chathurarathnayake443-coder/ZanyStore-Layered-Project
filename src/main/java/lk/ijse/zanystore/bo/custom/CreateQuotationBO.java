package lk.ijse.zanystore.bo.custom;

import lk.ijse.zanystore.db.DBConnection;
import lk.ijse.zanystore.dto.QuotationDTO;
import lk.ijse.zanystore.entity.Quotation;
import lk.ijse.zanystore.entity.QuotationItem;
import lk.ijse.zanystore.bo.custom.SuperBO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CreateQuotationBO extends SuperBO {
    public List<String> loadItemNames() throws SQLException;

    public double getPriceForItem(String itemName) throws SQLException;

    public boolean placeQuotation(String customerName, double fullTotal, List<QuotationDTO>quotationItemList) throws SQLException;
}
