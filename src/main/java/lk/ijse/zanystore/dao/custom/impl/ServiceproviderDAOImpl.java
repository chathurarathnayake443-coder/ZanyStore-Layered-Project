package lk.ijse.zanystore.dao.custom.impl;

import lk.ijse.zanystore.dao.custom.ServiceproviderDAO;
import lk.ijse.zanystore.dto.ServiceproviderDTO;
import lk.ijse.zanystore.entity.ServiceProvider;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceproviderDAOImpl implements ServiceproviderDAO {

    public boolean save(ServiceProvider entity) throws SQLException {
        boolean result = CrudUtil.execute("INSERT INTO service_providers (serprovider_name, serprovider_address, serprovider_contact_no, serprovider_type) VALUES (?,?,?,?)", entity.getSerprovider_name(),entity.getSerprovider_address(),entity.getSerprovider_contact_no(),entity.getSerprovider_type());
        return result;
    }

    public boolean update(ServiceProvider entity) throws SQLException{
        boolean result = CrudUtil.execute("UPDATE service_providers SET serprovider_name = ?, serprovider_address = ?, serprovider_contact_no = ?, serprovider_type = ? WHERE serprovider_id = ?", entity.getSerprovider_name(),entity.getSerprovider_address(),entity.getSerprovider_contact_no(),entity.getSerprovider_type(), entity.getSerprovider_id());
        return result;
    }

    public boolean delete(int id) throws SQLException{
        boolean result = CrudUtil.execute("DELETE FROM service_providers WHERE serprovider_id = ?", id);
        return result;
    }

    public List<ServiceProvider> getAll() throws SQLException{
        ResultSet results = CrudUtil.execute("SELECT * FROM service_providers");

        List<ServiceProvider> serviceList = new ArrayList<>();

        while(results.next()){
            int id = results.getInt("serprovider_id");
            String name = results.getString("serprovider_name");
            String address = results.getString("serprovider_address");
            String contact = results.getString("serprovider_contact_no");
            String type = results.getString("serprovider_type");

            serviceList.add(new ServiceProvider(id,name,address,contact,type));
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

    @Override
    public ServiceProvider find(int empId) throws SQLException {
        return null;
    }

    @Override
    public String getId() throws SQLException {
        return "";
    }
}
