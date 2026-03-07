package lk.ijse.zanystore.dao.custom;

import lk.ijse.zanystore.dao.CrudDAO;
import lk.ijse.zanystore.dto.ServiceproviderDTO;
import lk.ijse.zanystore.entity.ServiceProvider;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ServiceproviderDAO extends CrudDAO<ServiceProvider> {

    public boolean save(ServiceProvider entity) throws SQLException;

    public boolean update(ServiceProvider entity) throws SQLException;

    public boolean delete(int id) throws SQLException;

    public List<ServiceProvider> getAll() throws SQLException;

    public String showNextId() throws SQLException;
}
