package lk.ijse.zanystore.bo.custom.impl;

import lk.ijse.zanystore.bo.custom.QueryBO;
import lk.ijse.zanystore.dao.DAOFactory;
import lk.ijse.zanystore.dao.custom.QueryDAO;
import lk.ijse.zanystore.dao.custom.ReturnDAO;
import lk.ijse.zanystore.dao.custom.impl.QueryDAOImpl;
import lk.ijse.zanystore.dto.QueryDTO.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryBOImpl implements QueryBO {

    QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);

    public List<LoadItemDTO> loadItemTable() throws SQLException {
        List<LoadItemDTO> list = queryDAO.loadItemTable();
        return list;
    }

    public LoadLastOrderDTO loadLastOrderTable() throws SQLException {
        LoadLastOrderDTO dto = queryDAO.loadLastOrderTable();
        return dto;
    }

    public LoadLastPaymentDTO loadLastPaymentTable() throws SQLException {
        LoadLastPaymentDTO dto = queryDAO.loadLastPaymentTable();
        return dto;
    }

    public List<LoadLowStockDTO> loadLowStockTable() throws SQLException {
        List<LoadLowStockDTO> list = queryDAO.loadLowStockTable();
        return list;
    }

    public List<LoadPaymentDTO> loadPaymentTable() throws SQLException {
        List<LoadPaymentDTO> list = queryDAO.loadPaymentTable();
        return list;
    }

    public List<String> loadItemColorsTable(String itemName) throws SQLException {
        List<String> list = queryDAO.loadItemColorsTable(itemName);
        return list;
    }
}
