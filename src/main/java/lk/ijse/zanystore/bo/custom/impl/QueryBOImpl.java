package lk.ijse.zanystore.bo.custom.impl;

import lk.ijse.zanystore.bo.custom.QueryBO;
import lk.ijse.zanystore.dao.DAOFactory;
import lk.ijse.zanystore.dao.custom.QueryDAO;
import lk.ijse.zanystore.dao.custom.ReturnDAO;
import lk.ijse.zanystore.dao.custom.impl.QueryDAOImpl;
import lk.ijse.zanystore.dto.QueryDTO.LoadItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryBOImpl implements QueryBO {

    QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);

    public List<LoadItemDTO> loadItemTable() throws SQLException {
        List<LoadItemDTO> list = queryDAO.loadItemTable();
        return list;
    }
}
