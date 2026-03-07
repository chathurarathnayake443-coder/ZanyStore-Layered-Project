package lk.ijse.zanystore.controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.zanystore.App;
import lk.ijse.zanystore.bo.custom.CustomerBO;
import lk.ijse.zanystore.bo.custom.impl.CustomerBOImpl;
import lk.ijse.zanystore.dao.custom.CustomerDAO;
import lk.ijse.zanystore.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.zanystore.dto.CustomerDTO;

public class CustomerController implements Initializable {
    
    @FXML
    private TextField idField;
    
    @FXML
    private TextField nameField;
    
    @FXML
    private TextField addressField;
    
    @FXML
    private TextField contactField;
    
    @FXML
    private TableView tableCustomer;
    
    @FXML
    private TableColumn colId;
    
    @FXML
    private TableColumn colName;
    
    @FXML
    private TableColumn colAddress;
    
    @FXML
    private TableColumn colContact;
    
    private final String CUS_ID_REGEX = "^[0-9]+$";
    private final String CUS_NAME_REGEX = "^[A-Za-z0-9\\s]{3,}$";
    private final String CUS_ADDRESS_REGEX = "^[A-Za-z0-9\\s,]{3,}$";
    private final String CUS_CONTACT_REGEX = "^[0-9]{10}$";

    CustomerBO customerBO = new CustomerBOImpl();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Customer View Loaded");
        
        colId.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("customer_address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("customer_contact_no"));
        
        tableCustomer.setOnMouseClicked(event -> {
        Object object = tableCustomer.getSelectionModel().getSelectedItem();
        CustomerDTO selected = (CustomerDTO)object;
        if (selected != null) {
            idField.setText(String.valueOf(selected.getCustomer_id()));
            nameField.setText(selected.getCustomer_name());
            addressField.setText(selected.getCustomer_address());
            contactField.setText(selected.getCustomer_contact_no());
        }
    });
        
        loadCustomerTable();
        showNextId();
    } 
    
    @FXML
    private void clickSaveCustomer(){
        try{
            String name = nameField.getText().trim();
            String address = addressField.getText().trim();
            String contact = contactField.getText().trim();
            
            if(!name.matches(CUS_NAME_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Customer Name").show();
                return;
            }
            
            if(!address.matches(CUS_ADDRESS_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Customer Address").show();
                return;
            }
            
            if(!contact.matches(CUS_CONTACT_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Customer Contact").show();
                return;
            }

            boolean result = customerBO.saveCustomer(new CustomerDTO(name,address,contact));
            
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Customer Added Successfully !").show();
                loadCustomerTable();
                cleanFields();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Failed to Add Customer").show();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void clickUpdateCustomer(){
        try{
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String address = addressField.getText().trim();
            String contact = contactField.getText().trim();
            
            if(!name.matches(CUS_NAME_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Customer Name").show();
                return;
            }
            
            if(!address.matches(CUS_ADDRESS_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Customer Address").show();
                return;
            }
            
            if(!contact.matches(CUS_CONTACT_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Customer Contact").show();
                return;
            }

            boolean result = customerBO.updateCustomer(new CustomerDTO(Integer.parseInt(id),name,address,contact));
            
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Customer Updated Successfully !").show();
                loadCustomerTable();
                cleanFields();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Failed to Update Customer").show();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void clickDeleteCustomer(){
        try{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText("Are you sure you want to Delete Customer?");

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");

        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == yesButton) {
            String id = idField.getText();

            boolean deleteResult = customerBO.deleteCustomer(id);
            
            if(deleteResult){
                new Alert(Alert.AlertType.INFORMATION,"Customer Deleted Successfully !").show();
                loadCustomerTable();
                cleanFields();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Failed to Delete Customer !").show();
            }
        } 
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void loadCustomerTable(){
        try{
            
            List<CustomerDTO> customerList = customerBO.loadCustomerTable();
            
            ObservableList<CustomerDTO> obList = FXCollections.observableArrayList();
            
            for(CustomerDTO customerDTO : customerList){
                obList.add(customerDTO);
            }
            
            tableCustomer.setItems(obList);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private void cleanFields(){
        idField.setText("");
        nameField.setText("");
        addressField.setText("");
        contactField.setText("");
    }
    
    @FXML
    private void clickReset(){
        cleanFields();
    }
    
    @FXML
private void showNextId(){
    try{
        String id = customerBO.showNextId();
        idField.setText(id);
    }
    catch(Exception e){
        e.printStackTrace();
    }
}

@FXML
private void backButton(){
    try{
        App.setRoot("layout");
    }
    catch(Exception e){
        e.printStackTrace();
    }
}
    
}
