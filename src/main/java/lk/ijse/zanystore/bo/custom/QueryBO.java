package lk.ijse.zanystore.bo.custom;

import lk.ijse.zanystore.dto.QueryDTO.*;
import lk.ijse.zanystore.bo.custom.SuperBO;

import java.sql.SQLException;
import java.util.List;

public interface QueryBO extends SuperBO {

    public List<LoadItemDTO> loadItemTable() throws SQLException;

    public LoadLastOrderDTO loadLastOrderTable() throws SQLException;

    public LoadLastPaymentDTO loadLastPaymentTable() throws SQLException;

    public List<LoadLowStockDTO> loadLowStockTable() throws SQLException;

    public List<LoadPaymentDTO> loadPaymentTable() throws SQLException;

    public List<String> loadItemColorsTable(String itemName) throws SQLException;

    public List<LoadReturnDTO> loadReturnTable() throws SQLException;

    public LoadOtherDetailsDTO loadOtherDetailsTable(int id) throws SQLException;

    public List<LoadItemDetailDTO> loadItemDetailTable(int id) throws SQLException;
}
