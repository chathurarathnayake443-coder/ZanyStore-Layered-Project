package lk.ijse.zanystore.dao.custom;

import lk.ijse.zanystore.dao.SuperDAO;
import lk.ijse.zanystore.dto.QueryDTO.LoadItemDTO;
import lk.ijse.zanystore.dto.QueryDTO.LoadLastOrderDTO;
import lk.ijse.zanystore.dto.QueryDTO.LoadLastPaymentDTO;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface QueryDAO extends SuperDAO {

    public List<LoadItemDTO> loadItemTable() throws SQLException;

    public LoadLastOrderDTO loadLastOrderTable() throws SQLException;

    public LoadLastPaymentDTO loadLastPaymentTable() throws SQLException;
}
