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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.zanystore.App;
import lk.ijse.zanystore.bo.BOFactory;
import lk.ijse.zanystore.bo.custom.PlaceOrderBO;
import lk.ijse.zanystore.dto.QueryDTO.LoadItemDetailDTO;
import lk.ijse.zanystore.dto.QueryDTO.LoadOtherDetailsDTO;

/**
 * FXML Controller class
 *
 * @author chathura
 */
public class UsedItemsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private ComboBox<String> idBox;
    
    @FXML
    private TextField nameField;
    
    @FXML
    private TextField dateField;
    
    @FXML
    private TextField priceField;
    
    @FXML
    private TableView tblUsedItems;
    
    @FXML
    private TableColumn colId;
    
    @FXML
    private TableColumn colName;
    
    @FXML
    private TableColumn colColor;
    
    @FXML
    private TableColumn colQty;

    PlaceOrderBO placeOrderBO = (PlaceOrderBO) BOFactory.getInstance().getBOFactory(BOFactory.BOTypes.PLACE_ORDER);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Used Items Page Loaded");
        
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colName.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        
        loadId();
        
        idBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
        loadOtherDetails(Integer.parseInt(newVal));
        loadTable(Integer.parseInt(newVal));
    }
        });
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

@FXML
private void loadId(){
    try{
        List<String> list = placeOrderBO.getAllId();
        
        ObservableList obList = FXCollections.observableArrayList();
        
        for(String id : list){
            obList.add(id);
        }
        
        idBox.setItems(obList);
        
    }
    catch(Exception e){
        e.printStackTrace();
    }
}

@FXML
private void loadOtherDetails(int id){
    try{
        LoadOtherDetailsDTO dto = placeOrderBO.loadOtherDetailsTable(id);

            nameField.setText(dto.getCustomer_name());
            priceField.setText(String.valueOf(dto.getPayment_amount()));
            dateField.setText(dto.getCloth_order_end_date());

    }
    catch(Exception e){
        e.printStackTrace();
    }
}

@FXML
private void loadTable(int id){
    try{
        List <LoadItemDetailDTO> list = placeOrderBO.loadItemDetailTable(id);
        
        ObservableList<LoadItemDetailDTO> itemList = FXCollections.observableArrayList();
        
        for(LoadItemDetailDTO dto : list){
            itemList.add(dto);
        }
        
        tblUsedItems.setItems(itemList);
    }
    catch(Exception e){
        e.printStackTrace();
    }
}
}
