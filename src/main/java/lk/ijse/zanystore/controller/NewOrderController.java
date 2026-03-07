/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package lk.ijse.zanystore.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.zanystore.App;
import lk.ijse.zanystore.dao.custom.ItemColorStockDAO;
import lk.ijse.zanystore.dao.custom.ItemDAO;
import lk.ijse.zanystore.dao.custom.impl.*;
import lk.ijse.zanystore.db.DBConnection;
import lk.ijse.zanystore.dto.*;
//import lk.ijse.zanystore.model.OrderModel;

/**
 * FXML Controller class
 *
 * @author chathura
 */
public class NewOrderController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private ComboBox<String> addingColorBox;

    @FXML
    private TextField addingIdField;

    @FXML
    private TextField addingQtyField;

    @FXML
    private TableColumn colColor;

    @FXML
    private TableColumn colEdit;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colItemColor;

    @FXML
    private TableColumn colItemName;

    @FXML
    private TableColumn colItemQty;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colQty;

    @FXML
    private TableColumn colUnitPrice;

    @FXML
    private TextField customerIdField;

    @FXML
    private DatePicker endDate;

    @FXML
    private DatePicker givenDate;

    @FXML
    private TextArea orderDetailsField;

    @FXML
    private TextField orderIdField;

    @FXML
    private DatePicker recievedDate;

    @FXML
    private DatePicker startDate;

    @FXML
    private TableView tableItem;

    @FXML
    private TableView tableOrder;

    ItemDAO itemDAO = new ItemDAOImpl();
    ItemColorStockDAO itemColorStockDAO = new ItemColorStockDAOImpl();
    ClothOrderDAOImpl clothOrderDAO = new ClothOrderDAOImpl();
    ClothOrderDetailDAOImpl clothOrderDetailDAO = new ClothOrderDetailDAOImpl();
    OrderSerproviderDAOImpl orderSerproviderDAO = new OrderSerproviderDAOImpl();
    
    private final ObservableList<OrderItemDTO> orderItemObList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        colId.setCellValueFactory(new PropertyValueFactory<>("item_id"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        colItemColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("item_unit_price"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        
        colName.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        colEdit.setCellFactory(cell -> new TableCell<OrderItemDTO, Void>(){
            Button btn = new Button("Remove");
            
            {
                
                btn.setStyle(
            "-fx-background-color: #4b9e34;" +
            "-fx-text-fill: white;" +
            "-fx-padding: 6 12 6 12;" +
            "-fx-background-radius: 5;"
        );
                
                btn.setOnAction(event -> {
                    OrderItemDTO item = getTableView().getItems().get(getIndex());
                    orderItemObList.remove(item);
                    loadOrderItemTbl();
                });
                
            }
            
            @Override
            protected void updateItem(Void item, boolean empty){
                super.updateItem(item, empty);
                if (empty) {
        setGraphic(null);
    } else {
        HBox box = new HBox(btn);
        box.setStyle("-fx-padding: 5;");
        box.setAlignment(Pos.CENTER);
        setGraphic(box);
    }
            }
        });
        
        tableItem.setOnMouseClicked(event -> {
        Object object = tableItem.getSelectionModel().getSelectedItem();
        ItemDTO selected = (ItemDTO)object;
        if (selected != null) {
            addingIdField.setText(String.valueOf(selected.getItem_id()));
            loadComboItemColor(selected.getItem_id());
        }
    });
        
        loadItemTable();
        showNextId();
    }
    
    @FXML
    private void loadItemTable(){
        try{
            Connection conn = DBConnection.getInstance().getConnection();
            
            PreparedStatement pstm = conn.prepareStatement("SELECT ics.item_id, i.item_name, i.item_unit_price, ics.color, ics.qty FROM item_color_stock ics JOIN item i ON ics.item_id = i.item_id");
            
            ResultSet results = pstm.executeQuery();
            
            List<ItemDTO> itemList = new ArrayList<>();
            
            while(results.next()){
                int id = results.getInt("item_id");
                String name = results.getString("item_name");
                double unitPrice = results.getDouble("item_unit_price");
                String color = results.getString("color");
                int qty = results.getInt("qty");
                
                ItemDTO itemDTO = new ItemDTO(id,name, color, unitPrice, qty);
                itemList.add(itemDTO);
            }
            
            ObservableList<ItemDTO> obList = FXCollections.observableArrayList();
            
            for(ItemDTO itemDTO : itemList){
                obList.add(itemDTO);
            }
            
            tableItem.setItems(obList);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
     private void loadComboItemColor(int itemId) {
    try {
        List<String> colorList = itemColorStockDAO.getColorsById(itemId);
        ObservableList<String> list = FXCollections.observableArrayList();

        for(String color : colorList){
            list.add(color);
        }
        addingColorBox.setItems(list);

    } catch (Exception e) {
        e.printStackTrace();
    }
}

     @FXML
    private void AddToOrder(ActionEvent event) {

        try{
            String selectedColor = addingColorBox.getSelectionModel().getSelectedItem();
        
            int itemId = Integer.parseInt(addingIdField.getText());
            String color = selectedColor;
            String qty = addingQtyField.getText();

            String name = itemDAO.getNameById(itemId);
            OrderItemDTO orderItemDTO = new OrderItemDTO(name, itemId, color, Integer.parseInt(qty));
            orderItemObList.add(orderItemDTO);
            addingIdField.setText("");
            addingQtyField.setText("");
            addingColorBox.getSelectionModel().clearSelection();
         loadOrderItemTbl();
        }
        catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        
    }
    
     private void loadOrderItemTbl() {
    
        tableOrder.setItems(orderItemObList);
        
    }
     
     @FXML
    private void saveOrder(ActionEvent event) {
         Connection connection = null;
        try{
            connection= DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            String customerId = customerIdField.getText();
            String orderDetails = orderDetailsField.getText();
            String sDate = String.valueOf(startDate.getValue());
            String eDate = String.valueOf(endDate.getValue());
            String gDate = String.valueOf(givenDate.getValue());
            String rDate = String.valueOf(recievedDate.getValue());


            boolean b1 = clothOrderDAO.save(new OrderDTO(Integer.parseInt(customerId), orderDetails, sDate, eDate));

            if (!b1) {
                connection.rollback();
                connection.setAutoCommit(true);
                //return false;
            }

            String orderId = clothOrderDAO.getId();

          for (OrderItemDTO orderItemDTO : orderItemObList) {
              boolean b2 = clothOrderDetailDAO.save(new OrderItemDTO(Integer.parseInt(orderId),orderItemDTO.getItem_id(),orderItemDTO.getQty(),orderItemDTO.getColor()));

              if (!b2) {
                  connection.rollback();
                  connection.setAutoCommit(true);
                  //return false;
              }

              boolean b3 = itemColorStockDAO.decreaseQty(new ItemColorStockDTO(orderItemDTO.getItem_id(),orderItemDTO.getColor(),orderItemDTO.getQty()));

              if (!b3) {
                  connection.rollback();
                  connection.setAutoCommit(true);
                  //return false;
              }
          }

          boolean b4 = orderSerproviderDAO.save(new OrderSerproviderDTO(Integer.parseInt(orderId),gDate,rDate));

            if (!b4) {
                connection.rollback();
                connection.setAutoCommit(true);
                //return false;
            }

                connection.commit();
                connection.setAutoCommit(true);
              new Alert(Alert.AlertType.INFORMATION,"Order Placed Successfully !").show();
              loadItemTable();
              showNextId();
              cleanFields();
              tableOrder.getItems().clear();
        }
        catch (Exception e) {
            try { if (connection != null) connection.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            new Alert(Alert.AlertType.ERROR, "Something went Wrong !").show();
            e.printStackTrace();
        } finally {
            try { if (connection != null) connection.setAutoCommit(true); } catch (SQLException ex) { ex.printStackTrace(); }
        }
        
    }
    
    @FXML
private void showNextId(){
    try{
        String id = clothOrderDAO.showNextId();
        orderIdField.setText(id);
    }
    catch(Exception e){
        e.printStackTrace();
    }
}

@FXML
    private void onOpenCustomersClicked(javafx.event.ActionEvent event) {
        try {
            // Load the FXML for the popup window (create a separate order_table.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/zanystore/customerview.fxml"));
            Parent root = loader.load();

            // Optionally get popup controller to pass data/init
            CustomerviewController popupController = loader.getController();
            // e.g. popupController.initData(someListOrId);

            // Create new stage (popup window)
            Stage stage = new Stage();
            stage.setTitle("All Customers");
            stage.setScene(new Scene(root));

            // Make the popup modal and set owner so it blocks the main window (optional)
            // Get owner window from the current button — centers and ties window focus
            Window owner = ((Node) event.getSource()).getScene().getWindow();
            stage.initOwner(owner);
            stage.initModality(Modality.APPLICATION_MODAL); // use APPLICATION_MODAL or WINDOW_MODAL
            stage.setResizable(false);

            // showAndWait() will block until popup closes (modal). Use show() if you want non-modal.
            stage.showAndWait();

        } catch (IOException ex) {
            ex.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to open Customers popup.").show();
        }
    }
    
    @FXML
    private void onOpenOrdersClicked(javafx.event.ActionEvent event) {
        try {
            // Load the FXML for the popup window (create a separate order_table.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/zanystore/orderview.fxml"));
            Parent root = loader.load();

            // Optionally get popup controller to pass data/init
            OrderviewController popupController = loader.getController();
            // e.g. popupController.initData(someListOrId);

            // Create new stage (popup window)
            Stage stage = new Stage();
            stage.setTitle("All Orders");
            stage.setScene(new Scene(root));

            // Make the popup modal and set owner so it blocks the main window (optional)
            // Get owner window from the current button — centers and ties window focus
            Window owner = ((Node) event.getSource()).getScene().getWindow();
            stage.initOwner(owner);
            stage.initModality(Modality.APPLICATION_MODAL); // use APPLICATION_MODAL or WINDOW_MODAL
            stage.setResizable(false);

            // showAndWait() will block until popup closes (modal). Use show() if you want non-modal.
            stage.showAndWait();

        } catch (IOException ex) {
            ex.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to open Orders popup.").show();
        }
    }
    
    @FXML
private void clickPayment() throws IOException {
    Parent paymentFXML = App.loadFXML("payment");
    LayoutController.getInstance()
            .getMainContent()
            .getChildren()
            .setAll(paymentFXML);
}

@FXML
private void cleanFields(){
    try{
        customerIdField.setText("");
        orderDetailsField.setText("");
        startDate.setValue(null);
        endDate.setValue(null);
        givenDate.setValue(null);
        recievedDate.setValue(null);
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
private void backButton(){
    try{
        App.setRoot("layout");
    }
    catch(Exception e){
        e.printStackTrace();
    }
}

@FXML
private void clickUsedItems() throws IOException {
    Parent paymentFXML = App.loadFXML("usedItems");
    LayoutController.getInstance()
            .getMainContent()
            .getChildren()
            .setAll(paymentFXML);
}

}
