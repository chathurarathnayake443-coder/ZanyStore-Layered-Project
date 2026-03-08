/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package lk.ijse.zanystore.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.zanystore.App;
import lk.ijse.zanystore.bo.BOFactory;
import lk.ijse.zanystore.bo.custom.PlaceOrderBO;
import lk.ijse.zanystore.dao.custom.ClothOrderDAO;
import lk.ijse.zanystore.dao.custom.impl.ClothOrderDAOImpl;
import lk.ijse.zanystore.db.DBConnection;
import lk.ijse.zanystore.dto.UsedItemDTO;

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
    private ComboBox<Integer> idBox;
    
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
        loadOtherDetails(newVal);
        loadTable(newVal);
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
        Connection conn = DBConnection.getInstance().getConnection();
        
        String sql = "SELECT customer.customer_name, payment.payment_amount, cloth_order.cloth_order_end_date FROM cloth_order JOIN customer ON customer.customer_id = cloth_order.customer_id LEFT JOIN order_payment_details ON cloth_order.cloth_order_id = order_payment_details.cloth_order_id LEFT JOIN payment ON order_payment_details.payment_id = payment.payment_id WHERE cloth_order.cloth_order_id = ?";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setInt(1, id);
        
        ResultSet results = pstm.executeQuery();
        
       if(results.next()){ 
            nameField.setText(results.getString("customer_name"));
            priceField.setText(String.valueOf(results.getDouble("payment_amount")));
            dateField.setText(results.getString("cloth_order_end_date"));
        }
    }
    catch(Exception e){
        e.printStackTrace();
    }
}

@FXML
private void loadTable(int id){
    try{
        Connection conn = DBConnection.getInstance().getConnection();
        
        String sql = "SELECT oid.color, oid.qty, i.item_name FROM order_item_details oid JOIN item i ON i.item_id = oid.item_id JOIN item_color_stock ics ON ics.item_id = oid.item_id AND ics.color = oid.color WHERE oid.cloth_order_id = ?";
         
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setInt(1, id);
        
        ResultSet results = pstm.executeQuery();
        
        ObservableList<UsedItemDTO> itemList = FXCollections.observableArrayList();
        
        while(results.next()){
            String itemName = results.getString("item_name");
            String color = results.getString("color");
            int qty = results.getInt("qty");
            
            itemList.add(new UsedItemDTO(itemName, color, qty));
        }
        
        tblUsedItems.setItems(itemList);
    }
    catch(Exception e){
        e.printStackTrace();
    }
}
}
