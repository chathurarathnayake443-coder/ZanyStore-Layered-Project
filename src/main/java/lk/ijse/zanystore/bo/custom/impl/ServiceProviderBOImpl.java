package lk.ijse.zanystore.bo.custom.impl;

import lk.ijse.zanystore.bo.custom.ServiceProviderBO;
import lk.ijse.zanystore.dao.DAOFactory;
import lk.ijse.zanystore.dao.custom.QuotationDAO;
import lk.ijse.zanystore.dao.custom.ServiceproviderDAO;
import lk.ijse.zanystore.dao.custom.impl.ServiceproviderDAOImpl;
import lk.ijse.zanystore.dto.ServiceproviderDTO;
import lk.ijse.zanystore.entity.ServiceProvider;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceProviderBOImpl implements ServiceProviderBO {

    ServiceproviderDAO serviceproviderDAO = (ServiceproviderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SERVICEPROVIDER);

    public boolean saveServiceProvider(ServiceproviderDTO dto) throws SQLException {
        boolean result = serviceproviderDAO.save(new ServiceProvider(dto.getSerprovider_name(),dto.getSerprovider_address(),dto.getSerprovider_contact_no(),dto.getSerprovider_type()));
        return result;
    }

    public boolean updateServiceProvider(ServiceproviderDTO dto) throws SQLException {
        boolean result = serviceproviderDAO.update(new ServiceProvider(dto.getSerprovider_id(),dto.getSerprovider_name(),dto.getSerprovider_address(),dto.getSerprovider_contact_no(),dto.getSerprovider_type()));
        return result;
    }

    public boolean deleteServiceProvider(String id) throws SQLException {
        boolean result = serviceproviderDAO.delete(Integer.parseInt(id));
        return result;
    }

    public List<ServiceproviderDTO> getAllServiceProvider() throws SQLException {
        List<ServiceproviderDTO> serviceproviderDTOList = new ArrayList<>();
        List<ServiceProvider> list = serviceproviderDAO.getAll();
        for(ServiceProvider serviceProvider : list){
            serviceproviderDTOList.add(new ServiceproviderDTO(serviceProvider.getSerprovider_id(),serviceProvider.getSerprovider_name(),serviceProvider.getSerprovider_address(),serviceProvider.getSerprovider_contact_no(),serviceProvider.getSerprovider_type()));
        }
        return serviceproviderDTOList;
    }

    public String generateNextProviderID() throws SQLException {
        String id = serviceproviderDAO.showNextId();
        return id;
    }
}
