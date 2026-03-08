package lk.ijse.zanystore.bo.custom.impl;

import lk.ijse.zanystore.bo.custom.CustomerBO;
import lk.ijse.zanystore.dao.DAOFactory;
import lk.ijse.zanystore.dao.custom.CustomerDAO;
import lk.ijse.zanystore.dao.custom.QuotationDAO;
import lk.ijse.zanystore.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.zanystore.dto.CustomerDTO;
import lk.ijse.zanystore.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException {
        return customerDAO.save(new Customer(customerDTO.getCustomer_name(),customerDTO.getCustomer_address(),customerDTO.getCustomer_contact_no()));
    }

    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException {
        return customerDAO.update(new Customer(customerDTO.getCustomer_id(), customerDTO.getCustomer_name(),customerDTO.getCustomer_address(),customerDTO.getCustomer_contact_no()));
    }

    public boolean deleteCustomer(String id) throws SQLException {
        return customerDAO.delete(Integer.parseInt(id));
    }

    public List<CustomerDTO> loadCustomerTable() throws SQLException {
        List<Customer> customerList = customerDAO.getAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer customer : customerList) {
            customerDTOList.add(new CustomerDTO(customer.getCustomer_id(),customer.getCustomer_name(),customer.getCustomer_address(),customer.getCustomer_contact_no()));
        }
        return customerDTOList;
    }

    public String showNextId() throws SQLException {
        String id = customerDAO.showNextId();
        return id;
    }
}
