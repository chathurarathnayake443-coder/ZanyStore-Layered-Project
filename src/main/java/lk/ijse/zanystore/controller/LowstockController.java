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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.zanystore.App;
import lk.ijse.zanystore.db.DBConnection;
import lk.ijse.zanystore.dto.LowStockDTO;

/**
 * FXML Controller class
 *
 * @author chathura
 */
public class LowstockController implements Initializable {
    
    @FXML
    private TableView tblLowStock;
    
    @FXML
    private TableColumn colId;
    
    @FXML
    private TableColumn colName;
    
    @FXML
    private TableColumn colColor;
    
    @FXML
    private TableColumn colQty;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Low Stock View Loaded");
        
        colId.setCellValueFactory(new PropertyValueFactory<>("item_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        
        loadTable();
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
private void loadTable(){
    try{
        Connection conn = DBConnection.getInstance().getConnection();
        
        String sql = "SELECT\n" +
"    i.item_id,\n" +
"    i.item_name,\n" +
"    ics.color,\n" +
"    ics.qty AS remaining_qty\n" +
"FROM item_color_stock ics\n" +
"JOIN item i\n" +
"    ON i.item_id = ics.item_id\n" +
"WHERE ics.qty <= 200\n" +
"ORDER BY ics.qty ASC;";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        ResultSet results = pstm.executeQuery();
        
        List<LowStockDTO> stockList = new ArrayList<>();
        
        while(results.next()){
            int id = results.getInt("item_id");
            String name = results.getString("item_name");
            String color = results.getString("color");
            int qty = results.getInt("remaining_qty");
            
            LowStockDTO stock = new LowStockDTO(id, name, color, qty);
            stockList.add(stock);
        }
        
        ObservableList<LowStockDTO> obList = FXCollections.observableArrayList();
        
        for(LowStockDTO item : stockList){
            obList.add(item);
        }
        
        tblLowStock.setItems(obList);
    }
    catch(Exception e){
        e.printStackTrace();
    }
}
    
}
