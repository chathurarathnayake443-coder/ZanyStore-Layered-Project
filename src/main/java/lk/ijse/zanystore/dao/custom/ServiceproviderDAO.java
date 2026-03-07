package lk.ijse.zanystore.dao.custom;

import lk.ijse.zanystore.dto.ServiceproviderDTO;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ServiceproviderDAO {

    public boolean save(ServiceproviderDTO serviceProviderDTO) throws SQLException;

    public boolean update(ServiceproviderDTO serviceProviderDTO) throws SQLException;

    public boolean delete(int id) throws SQLException;

    public List<ServiceproviderDTO> getAll() throws SQLException;

    public String showNextId() throws SQLException;
}
