/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.zanystore.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lk.ijse.zanystore.dto.ServiceproviderDTO;
import lk.ijse.zanystore.util.CrudUtil;

/**
 *
 * @author chathura
 */
public class ServiceproviderModel {
    public boolean clickSave(ServiceproviderDTO serviceProviderDTO) throws SQLException{
            boolean result = CrudUtil.execute("INSERT INTO service_providers (serprovider_name, serprovider_address, serprovider_contact_no, serprovider_type) VALUES (?,?,?,?)", serviceProviderDTO.getSerprovider_name(),serviceProviderDTO.getSerprovider_address(),serviceProviderDTO.getSerprovider_contact_no(),serviceProviderDTO.getSerprovider_type());
            return result;
    }

    public boolean clickUpdate(ServiceproviderDTO serviceProviderDTO, int id) throws SQLException{
            boolean result = CrudUtil.execute("UPDATE service_providers SET serprovider_name = ?, serprovider_address = ?, serprovider_contact_no = ?, serprovider_type = ? WHERE serprovider_id = ?", serviceProviderDTO.getSerprovider_name(),serviceProviderDTO.getSerprovider_address(),serviceProviderDTO.getSerprovider_contact_no(),serviceProviderDTO.getSerprovider_type(), id);
            return result;
}

    public boolean clickDelete(int id) throws SQLException{
            boolean result = CrudUtil.execute("DELETE FROM service_providers WHERE serprovider_id = ?", id);
            return result;
    }

    public List<ServiceproviderDTO> loadTable() throws SQLException{
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
}


