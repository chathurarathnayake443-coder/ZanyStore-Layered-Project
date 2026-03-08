package lk.ijse.zanystore.bo.custom;

import lk.ijse.zanystore.dto.ServiceproviderDTO;
import lk.ijse.zanystore.entity.ServiceProvider;
import lk.ijse.zanystore.bo.custom.SuperBO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ServiceProviderBO extends SuperBO {

    public boolean saveServiceProvider(ServiceproviderDTO dto) throws SQLException;

    public boolean updateServiceProvider(ServiceproviderDTO dto) throws SQLException;

    public boolean deleteServiceProvider(String id) throws SQLException;

    public List<ServiceproviderDTO> getAllServiceProvider() throws SQLException;

    public String generateNextProviderID() throws SQLException;
}
