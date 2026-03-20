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
import lk.ijse.zanystore.bo.BOFactory;
import lk.ijse.zanystore.bo.custom.PlaceOrderBO;
import lk.ijse.zanystore.dto.QueryDTO.LoadOrderViewDTO;

public class OrderviewController implements Initializable {

    @FXML
    private TableView tableOrder;
    
    @FXML
    private TableColumn colOrderId;
    
    @FXML
    private TableColumn colCusId;
    
    @FXML
    private TableColumn colSerName;
    
    @FXML
    private TableColumn colDetail;
    
    @FXML
    private TableColumn colStart;
    
    @FXML
    private TableColumn colFinish;
    
    @FXML
    private Button closeBtn;

    PlaceOrderBO placeOrderBO = (PlaceOrderBO) BOFactory.getInstance().getBOFactory(BOFactory.BOTypes.PLACE_ORDER);

     @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Order Table View Loaded");
        
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("cloth_order_id"));
        colCusId.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        colDetail.setCellValueFactory(new PropertyValueFactory<>("cloth_order_description"));
        colStart.setCellValueFactory(new PropertyValueFactory<>("cloth_order_start_date"));
        colFinish.setCellValueFactory(new PropertyValueFactory<>("cloth_order_end_date"));
        
        loadTable();
    }

    @FXML
    private void onCloseClicked() {
        // Close the window that contains this button
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void loadTable(){
        try{
            List<LoadOrderViewDTO> orderList = placeOrderBO.loadOrderViewTable();
            
            ObservableList<LoadOrderViewDTO> obList = FXCollections.observableArrayList();
            
            for(LoadOrderViewDTO orderDTO : orderList){
                obList.add(orderDTO);
            }
            
            tableOrder.setItems(obList);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
}

