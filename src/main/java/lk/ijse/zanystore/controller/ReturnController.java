package lk.ijse.zanystore.controller;

import java.net.URL;
import java.sql.*;
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
import lk.ijse.zanystore.bo.BOFactory;
import lk.ijse.zanystore.bo.custom.QueryBO;
import lk.ijse.zanystore.bo.custom.ReturnBO;
import lk.ijse.zanystore.bo.custom.impl.ReturnBOImpl;
import lk.ijse.zanystore.dao.custom.impl.ReturnDAOImpl;
import lk.ijse.zanystore.dao.custom.impl.ReturnDetailDAOImpl;
import lk.ijse.zanystore.db.DBConnection;
import lk.ijse.zanystore.dto.QueryDTO.LoadReturnDTO;
import lk.ijse.zanystore.dto.ReturnDTO;
import lk.ijse.zanystore.dto.SupplierDTO;

public class ReturnController implements Initializable {
    
    @FXML
    private TextField returnIdField;
    
    @FXML
    private TextField orderIdField;
    
    @FXML
    private TextField detailField;
    
    @FXML
    private TextField dateField;
    
    @FXML
    private TextField nameField;
    
    @FXML
    private TextField addressField;
    
    @FXML
    private TableView tableReturn;
    
    @FXML
    private TableColumn colReturnId;
    
    @FXML
    private TableColumn colOrderId;

    ReturnBO returnBO =  (ReturnBO) BOFactory.getInstance().getBOFactory(BOFactory.BOTypes.RETURN);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Return View Loaded");
        
        colReturnId.setCellValueFactory(new PropertyValueFactory<>("return_order_id"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("cloth_order_id"));
        
        tableReturn.setOnMouseClicked(event -> {
        Object object = tableReturn.getSelectionModel().getSelectedItem();
        LoadReturnDTO selected = (LoadReturnDTO)object;
        if (selected != null) {
            returnIdField.setText(String.valueOf(selected.getReturn_order_id()));
            orderIdField.setText(String.valueOf(selected.getCloth_order_id()));
            detailField.setText(selected.getReturn_order_details());
            nameField.setText(selected.getCustomer_name());
            addressField.setText(selected.getCustomer_address());
            dateField.setText(selected.getOrder_return_date());
        }
    });
        
        loadReturnTable();
        showNextId();
    }  
    
    @FXML
    private void clickAddReturn() {
        try {
            String orderId = orderIdField.getText().trim();
            String detail = detailField.getText();
            String date = dateField.getText();

            boolean result = returnBO.saveReturn(detail, orderId, date);

            if (!result) {
                new Alert(Alert.AlertType.ERROR, "Return Creation Unsuccessful !").show();
            }
            new Alert(Alert.AlertType.INFORMATION, "Return Created Successfully").show();
            loadReturnTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void clickDelete() {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setTitle("Delete Confirmation");
            alert.setHeaderText("Are you sure you want to Delete Return?");

            ButtonType yesButton = new ButtonType("Yes");
            ButtonType noButton = new ButtonType("No");

            alert.getButtonTypes().setAll(yesButton, noButton);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == yesButton) {
                String returnId = returnIdField.getText();

                boolean deleteResult = returnBO.deleteReturn(Integer.parseInt(returnId));

                if(!deleteResult){
                    new Alert(Alert.AlertType.ERROR, "Return Delete Unsuccessful !").show();
                }
                loadReturnTable();
                new Alert(Alert.AlertType.INFORMATION, "Return Deleted Successfully").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went Wrong !").show();
            e.printStackTrace();
        }
    }
    
    @FXML
    private void clickReset(){
        cleanFields();
    }
    
    @FXML
    private void cleanFields(){
        returnIdField.setText("");
        orderIdField.setText("");
        detailField.setText("");
        nameField.setText("");
        addressField.setText("");
        dateField.setText("");
    }
    
    @FXML
    private void loadReturnTable(){
        try{
            List<LoadReturnDTO> returnList = returnBO.loadReturnTable();
            ObservableList<LoadReturnDTO> obList = FXCollections.observableArrayList();
            
            for(LoadReturnDTO returns : returnList){
                obList.add(returns);
            }
            
            tableReturn.setItems(obList);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
private void showNextId(){
    try{
        String id = returnBO.generateNextReturnId();
        returnIdField.setText(id);
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
