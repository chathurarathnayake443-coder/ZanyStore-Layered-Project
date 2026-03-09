package lk.ijse.zanystore.dao.custom;

import lk.ijse.zanystore.dao.SuperDAO;
import lk.ijse.zanystore.dto.QueryDTO.*;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface QueryDAO extends SuperDAO {

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
