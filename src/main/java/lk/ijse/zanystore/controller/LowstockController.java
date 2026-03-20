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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.zanystore.App;
import lk.ijse.zanystore.bo.BOFactory;
import lk.ijse.zanystore.bo.custom.LayoutBO;
import lk.ijse.zanystore.dto.QueryDTO.LoadLowStockDTO;

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

    LayoutBO layoutBO = (LayoutBO) BOFactory.getInstance().getBOFactory(BOFactory.BOTypes.LAYOUT);

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
        List<LoadLowStockDTO> list = layoutBO.loadLowStockTable();
        ObservableList<LoadLowStockDTO> obList = FXCollections.observableArrayList();
        
        for(LoadLowStockDTO item : list){
            obList.add(item);
        }
        
        tblLowStock.setItems(obList);
    }
    catch(Exception e){
        e.printStackTrace();
    }
}
    
}
