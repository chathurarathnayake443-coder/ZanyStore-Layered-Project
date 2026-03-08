package lk.ijse.zanystore.bo.custom;

import lk.ijse.zanystore.dto.QueryDTO.LoadItemDTO;
import lk.ijse.zanystore.bo.custom.SuperBO;
import lk.ijse.zanystore.dto.QueryDTO.LoadLastOrderDTO;
import lk.ijse.zanystore.dto.QueryDTO.LoadLastPaymentDTO;
import lk.ijse.zanystore.dto.QueryDTO.LoadLowStockDTO;

import java.sql.SQLException;
import java.util.List;

public interface QueryBO extends SuperBO {

    public List<LoadItemDTO> loadItemTable() throws SQLException;

    public LoadLastOrderDTO loadLastOrderTable() throws SQLException;

    public LoadLastPaymentDTO loadLastPaymentTable() throws SQLException;

    public List<LoadLowStockDTO> loadLowStockTable() throws SQLException;
}
