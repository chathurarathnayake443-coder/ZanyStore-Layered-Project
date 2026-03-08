package lk.ijse.zanystore.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
import lk.ijse.zanystore.bo.BOFactory;
import lk.ijse.zanystore.bo.custom.ServiceProviderBO;
import lk.ijse.zanystore.bo.custom.impl.ServiceProviderBOImpl;
import lk.ijse.zanystore.dao.custom.ServiceproviderDAO;
import lk.ijse.zanystore.dao.custom.impl.ServiceproviderDAOImpl;
import lk.ijse.zanystore.db.DBConnection;
import lk.ijse.zanystore.dto.EmployeeDTO;
import lk.ijse.zanystore.dto.ServiceproviderDTO;
import lk.ijse.zanystore.model.ServiceproviderModel;

public class ServiceproviderController implements Initializable {
    
    @FXML
    private TextField idField;
    
    @FXML
    private TextField nameField;
    
    @FXML
    private TextField addressField;
    
    @FXML
    private TextField contactField;
    
    @FXML
    private TextField typeField;
    
    @FXML
    private TableView tableService;
    
    @FXML
    private TableColumn colId;
    
    @FXML
    private TableColumn colName;
    
    @FXML
    private TableColumn colAddress;
    
    @FXML
    private TableColumn colContact;
    
    @FXML
    private TableColumn colType;
    
    private final String SER_ID_REGEX = "^[0-9]+$";
    private final String SER_NAME_REGEX = "^[A-Za-z\\s]{3,}$";
    private final String SER_ADDRESS_REGEX = "^[A-Za-z0-9\\s,]{3,}$";
    private final String SER_CONTACT_REGEX = "^[0-9]{10}$";
    private final String SER_TYPE_REGEX = "^[A-Za-z\\s]{3,}$";

    ServiceProviderBO serviceProviderBO = (ServiceProviderBO) BOFactory.getInstance().getBOFactory(BOFactory.BOTypes.SERVICEPROVIDER);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Service Provider View Loaded");
        
        colId.setCellValueFactory(new PropertyValueFactory<>("serprovider_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("serprovider_name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("serprovider_address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("serprovider_contact_no"));
        colType.setCellValueFactory(new PropertyValueFactory<>("serprovider_type"));
        
        tableService.setOnMouseClicked(event -> {
        Object object = tableService.getSelectionModel().getSelectedItem();
        ServiceproviderDTO selected = (ServiceproviderDTO)object;
        if (selected != null) {
            idField.setText(String.valueOf(selected.getSerprovider_id()));
            nameField.setText(selected.getSerprovider_name());
            addressField.setText(selected.getSerprovider_address());
            contactField.setText(selected.getSerprovider_contact_no());
            typeField.setText(selected.getSerprovider_type());
        }
    });
        
        loadTable();
        showNextId();
    }   
    
    @FXML
    private void clickSave(){
        try{
            String serName = nameField.getText().trim();
            String serAddress = addressField.getText().trim();
            String serContact = contactField.getText().trim();
            String serType = typeField.getText().trim();
            
            if(!serName.matches(SER_NAME_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Service Provider Name").show();
                return;
            }
            
            if(!serAddress.matches(SER_ADDRESS_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Address").show();
                return;
            }
            
            if(!serContact.matches(SER_CONTACT_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Contact Number").show();
                return;
            }
            
            if(!serType.matches(SER_TYPE_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Material Type").show();
                return;
            }
            
            boolean result = serviceProviderBO.saveServiceProvider(new ServiceproviderDTO(serName,serAddress,serContact,serType));
            
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Service Provider Saved Successfully !").show();
                loadTable();
                cleanFields();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Failed to Save !").show();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void clickUpdate(){
        try{
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String address = addressField.getText().trim();
            String contact = contactField.getText().trim();
            String type = typeField.getText().trim();
            
            if(!id.matches(SER_ID_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Service Provider ID").show();
                return;
            }
            
            if(!name.matches(SER_NAME_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Service Provider Name").show();
                return;
            }
            
            if(!address.matches(SER_ADDRESS_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Address").show();
                return;
            }
            
            if(!contact.matches(SER_CONTACT_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Contact Number").show();
                return;
            }
            
            if(!type.matches(SER_TYPE_REGEX)){
                new Alert(Alert.AlertType.ERROR,"Invalid Material Type").show();
                return;
            }

            boolean result = serviceProviderBO.updateServiceProvider(new ServiceproviderDTO(Integer.parseInt(id),name,address,contact,type));
            
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Updated Successfully !").show();
                loadTable();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Failed to Update !").show();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void clickDelete(){
        try{
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText("Are you sure you want to Delete Service Provider?");

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");

        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == yesButton) {
            String id = idField.getText().trim();
            
            boolean deleteResult = serviceProviderBO.deleteServiceProvider(id);
            
            if(deleteResult){
                new Alert(Alert.AlertType.INFORMATION,"Deleted Successfully !").show();
                loadTable();
                cleanFields();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Failed to Delete !").show();
            }
        } 
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void clickReset(){
        cleanFields();
    }
    
    @FXML
    private void cleanFields(){
        idField.setText("");
        nameField.setText("");
        addressField.setText("");
        contactField.setText("");
        typeField.setText("");
    }
    
    @FXML
    private void loadTable(){
        try{
            List<ServiceproviderDTO> serviceList = serviceProviderBO.getAllServiceProvider();
            
            ObservableList<ServiceproviderDTO> obList = FXCollections.observableArrayList();
            
            for(ServiceproviderDTO provider : serviceList){
                obList.add(provider);
            }
            
            tableService.setItems(obList);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
private void showNextId(){
    try{
        String id = serviceProviderBO.generateNextProviderID();
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
