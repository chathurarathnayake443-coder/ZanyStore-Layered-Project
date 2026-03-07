/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package lk.ijse.zanystore.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.zanystore.dao.custom.CustomerDAO;
import lk.ijse.zanystore.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.zanystore.dto.CustomerDTO;

/**
 * FXML Controller class
 *
 * @author chathura
 */
public class CustomerviewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableColumn colId;
    
    @FXML
    private TableColumn colName;
    
    @FXML
    private TableColumn colAddress;
    
    @FXML
    private TableColumn colContact;
    
    @FXML
    private TableView tableCustomer;
    
    @FXML
    private Button closeBtn;
    
    CustomerDAO customerDAO = new CustomerDAOImpl();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Customer View Loaded");
        // TODO
        
        colId.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("customer_address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("customer_contact_no"));
        
        loadTable();
    } 
    
     @FXML
    private void onCloseClicked() {
        // Close the window that contains this button
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }
    
    private void loadTable(){
        try{
            List<CustomerDTO> customerList = customerDAO.getAll();
            
            ObservableList<CustomerDTO> obList = FXCollections.observableArrayList();
            
            for(CustomerDTO customer : customerList){
                obList.add(customer);
            }
            
            tableCustomer.setItems(obList);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
