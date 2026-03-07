package lk.ijse.zanystore.dao.custom.impl;

import lk.ijse.zanystore.dao.custom.ServiceproviderDAO;
import lk.ijse.zanystore.dto.ServiceproviderDTO;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceproviderDAOImpl implements ServiceproviderDAO {

    public boolean save(ServiceproviderDTO serviceProviderDTO) throws SQLException {
        boolean result = CrudUtil.execute("INSERT INTO service_providers (serprovider_name, serprovider_address, serprovider_contact_no, serprovider_type) VALUES (?,?,?,?)", serviceProviderDTO.getSerprovider_name(),serviceProviderDTO.getSerprovider_address(),serviceProviderDTO.getSerprovider_contact_no(),serviceProviderDTO.getSerprovider_type());
        return result;
    }

    public boolean update(ServiceproviderDTO serviceProviderDTO) throws SQLException{
        boolean result = CrudUtil.execute("UPDATE service_providers SET serprovider_name = ?, serprovider_address = ?, serprovider_contact_no = ?, serprovider_type = ? WHERE serprovider_id = ?", serviceProviderDTO.getSerprovider_name(),serviceProviderDTO.getSerprovider_address(),serviceProviderDTO.getSerprovider_contact_no(),serviceProviderDTO.getSerprovider_type(), serviceProviderDTO.getSerprovider_id());
        return result;
    }

    public boolean delete(int id) throws SQLException{
        boolean result = CrudUtil.execute("DELETE FROM service_providers WHERE serprovider_id = ?", id);
        return result;
    }

    public List<ServiceproviderDTO> getAll() throws SQLException{
        ResultSet results = CrudUtil.execute("SELECT * FROM service_providers");

        List<ServiceproviderDTO> serviceList = new ArrayList<>();

        while(results.next()){
            int id = results.getInt("serprovider_id");
            String name = results.getString("serprovider_name");
            String address = results.getString("serprovider_address");
            String contact = results.getString("serprovider_contact_no");
            String type = results.getString("serprovider_type");

            ServiceproviderDTO serviceProvider = new ServiceproviderDTO(id,name,address,contact,type);
            serviceList.add(serviceProvider);
        }
        return serviceList;
    }

    public String showNextId() throws SQLException {
        ResultSet result = CrudUtil.execute("SELECT serprovider_id FROM service_providers ORDER BY serprovider_id DESC LIMIT 1");

        if(result.next()){
            int id = result.getInt("serprovider_id");
            String serId = String.valueOf(id + 1);
            return serId;
        }
        return null;
    }
}
