package lk.ijse.zanystore.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
import lk.ijse.zanystore.dao.custom.SupplierDAO;
import lk.ijse.zanystore.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.zanystore.db.DBConnection;
import lk.ijse.zanystore.dto.CustomerDTO;
import lk.ijse.zanystore.dto.SupplierDTO;
//import lk.ijse.zanystore.model.SupplierModel;

public class SupplierController implements Initializable {
    
    @FXML
    private TextField idField;
    
    @FXML
    private TextField nameField;
    
    @FXML
    private TextField itemField;
    
    @FXML
    private TextField addressField;
    
    @FXML
    private TextField contactField;
    
    @FXML
    private TableView tableSupplier;
    
    @FXML
    private TableColumn colId;
    
    @FXML
    private TableColumn colName;
    
    @FXML
    private TableColumn colItem;
    
    @FXML
    private TableColumn colAddress;
    
    @FXML
    private TableColumn colContact;
    
    private final String SUP_ID_REGEX = "^[0-9]+$";
    private final String SUP_NAME_REGEX = "^[A-Za-z]{3,}$";
    private final String SUP_ITEM_REGEX = "^[A-Za-z]{1,}$";
    private final String SUP_ADDRESS_REGEX = "^[A-Za-z0-9]{3,}$";
    private final String SUP_CONTACT_REGEX = "^[0-9]{10}$";
    
    SupplierDAO supplierDAO = new SupplierDAOImpl();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Supplier Page Loaded");
        
        colId.setCellValueFactory(new PropertyValueFactory<>("supplier_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("supplier_name"));
        colItem.setCellValueFactory(new PropertyValueFactory<>("supplier_item"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("supplier_address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("supplier_contact_no"));
        
        tableSupplier.setOnMouseClicked(event -> {
        Object object = tableSupplier.getSelectionModel().getSelectedItem();
        SupplierDTO selected = (SupplierDTO)object;
        if (selected != null) {
            idField.setText(String.valueOf(selected.getSupplier_id()));
            nameField.setText(selected.getSupplier_name());
            itemField.setText(selected.getSupplier_item());
            addressField.setText(selected.getSupplier_address());
            contactField.setText(selected.getSupplier_contact_no());
        }
    });
        
        loadSupplierTable();
        showNextId();
    } 
    
    @FXML
    private void clickAddSupplier(){
        try{
            String name = nameField.getText().trim();
            String item = itemField.getText().trim();
            String address = addressField.getText().trim();
            String contact = contactField.getText().trim();
            
            if(!(name.matches(SUP_NAME_REGEX))){
                new Alert(Alert.AlertType.ERROR,"Invalid Supplier Name !").show();
            }
            
            if(!(item.matches(SUP_ITEM_REGEX))){
                new Alert(Alert.AlertType.ERROR,"Invalid Item Name !").show();
            }
            
            if(!(address.matches(SUP_ADDRESS_REGEX))){
                new Alert(Alert.AlertType.ERROR,"Invalid Supplier Address !").show();
            }
            
            if(!(contact.matches(SUP_CONTACT_REGEX))){
                new Alert(Alert.AlertType.ERROR,"Invalid Supplier Contact !").show();
            }

            boolean result = supplierDAO.save(new SupplierDTO(name,item,address,contact));
            
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Supplier Added Successfully !").show();
                loadSupplierTable();
                cleanFields();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Failed to Add Supplier").show();
            }
        }  
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void clickUpdateSupplier(){
        try{
            String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String item = itemField.getText().trim();
        String address = addressField.getText().trim();
        String contact = contactField.getText().trim();
        
        if(!(id.matches(SUP_ID_REGEX))){
            new Alert(Alert.AlertType.ERROR,"Invalid Supplier ID !").show();
        }
        
        if(!(name.matches(SUP_NAME_REGEX))){
                new Alert(Alert.AlertType.ERROR,"Invalid Supplier Name !").show();
            }
            
        if(!(item.matches(SUP_ITEM_REGEX))){
                new Alert(Alert.AlertType.ERROR,"Invalid Item Name !").show();
            }
            
        if(!(address.matches(SUP_ADDRESS_REGEX))){
                new Alert(Alert.AlertType.ERROR,"Invalid Supplier Address !").show();
            }
            
        if(!(contact.matches(SUP_CONTACT_REGEX))){
                new Alert(Alert.AlertType.ERROR,"Invalid Supplier Contact !").show();
            }

        boolean result = supplierDAO.update(new SupplierDTO(Integer.parseInt(id),name,item,address,contact));
        
        if(result){
            new Alert(Alert.AlertType.INFORMATION,"Supplier Updated Successfully !").show();
            loadSupplierTable();
        }
        else{
            new Alert(Alert.AlertType.ERROR,"Failed to Update Supplier").show();
        }
        }
        catch(Exception e){
            e.printStackTrace();
        }
            
    }
    
    @FXML
    private void clickDeleteSupplier(){
        try{
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText("Are you sure you want to Delete Supplier?");

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");

        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == yesButton) {
                String id = idField.getText().trim();
            
        if(!(id.matches(SUP_ID_REGEX))){
            new Alert(Alert.AlertType.ERROR,"Invalid Supplier ID !").show();
        }
        
        boolean deleteResult = supplierDAO.delete(Integer.parseInt(id));
        
        if(deleteResult){
            new Alert(Alert.AlertType.INFORMATION,"Supplier Deleted Successfully !").show();
            loadSupplierTable();
            cleanFields();
        }
        else{
            new Alert(Alert.AlertType.ERROR,"Failed to Delete Supplier !").show();
        }
        }        
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void loadSupplierTable(){
        try{
            List<SupplierDTO> supplierList = supplierDAO.getAll();

            ObservableList<SupplierDTO> obList = FXCollections.observableArrayList();
            
            for(SupplierDTO supplierDTO : supplierList){
                obList.add(supplierDTO);
            }
            
            tableSupplier.setItems(obList);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void cleanFields(){
        idField.setText("");
        nameField.setText("");
        itemField.setText("");
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
        String id = supplierDAO.showNextId();
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
