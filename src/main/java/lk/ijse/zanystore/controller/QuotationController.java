package lk.ijse.zanystore.controller;

import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import lk.ijse.zanystore.App;
import lk.ijse.zanystore.bo.custom.CreateQuotationBO;
import lk.ijse.zanystore.bo.custom.ItemBO;
import lk.ijse.zanystore.bo.custom.impl.CreateQuotationBOImpl;
import lk.ijse.zanystore.bo.custom.impl.ItemBOImpl;
import lk.ijse.zanystore.dao.custom.ItemDAO;
import lk.ijse.zanystore.dao.custom.QuotationDAO;
import lk.ijse.zanystore.dao.custom.QuotationItemDAO;
import lk.ijse.zanystore.dao.custom.impl.ItemDAOImpl;
import lk.ijse.zanystore.dao.custom.impl.QuotationDAOImpl;
import lk.ijse.zanystore.dao.custom.impl.QuotationItemDAOImpl;
import lk.ijse.zanystore.db.DBConnection;
import lk.ijse.zanystore.dto.OrderDTO;
import lk.ijse.zanystore.dto.OrderItemDTO;
import lk.ijse.zanystore.dto.QuotationDTO;
import lk.ijse.zanystore.dto.QuotationItemDTO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class QuotationController implements Initializable {
    
    @FXML
    private ComboBox itemNameBox;
    
    
    @FXML
    private ComboBox colorBox;
    
    @FXML
    private TextField qtyField;
    
    @FXML
    private TextField unitPriceField;
    
    @FXML
    private Label subtotalLabel;
    
    @FXML
    private Button addBtn;
    
    @FXML
    private Button resetBtn;
    
    @FXML
    private Button printBtn;
    
    @FXML
    private TableView tblQuotation;
    
    @FXML
    private TableColumn colName;
    
    @FXML
    private TableColumn colColor;
    
    @FXML
    private TableColumn colQty;
    
    @FXML
    private TableColumn colPrice;
    
    @FXML
    private TableColumn colTotal;
    
    @FXML
    private TableColumn colEdit; 
    
    @FXML
    private TextField customerField;
    
    private ObservableList<QuotationDTO> quotationItemObList = FXCollections.observableArrayList();
    
    QuotationDAO quotationDAO = new QuotationDAOImpl();
    QuotationItemDAO quotationItemDAO = new QuotationItemDAOImpl();
    ItemDAO itemDAO = new ItemDAOImpl();
    CreateQuotationBOImpl createQuotationBO = new CreateQuotationBOImpl();
    ItemBOImpl itemBO = new ItemBOImpl();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Quotation Page Loaded");
        
        loadItemNames();
        
        itemNameBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null) return;
            try {
                String name = String.valueOf(itemNameBox.getValue());
                loadItemColors(name);
                }
            catch(Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR,"Failed to Load Data from Database").show();
            }
        });
        
        colName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("lineTotal"));
        colEdit.setCellFactory(cell -> new TableCell<QuotationDTO, Void>(){
            Button btn = new Button("Remove");
            
            {
                
                btn.setStyle(
            "-fx-background-color: #4b9e34;" +
            "-fx-text-fill: white;" +
            "-fx-padding: 6 12 6 12;" +
            "-fx-background-radius: 5;"
        );
                
                btn.setOnAction(event -> {
                    QuotationDTO item = getTableView().getItems().get(getIndex());
                    quotationItemObList.remove(item);
                    loadQuotationItemTbl();
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
    }
    
    @FXML
    private void loadItemNames(){
        try{
            List<String> names = itemBO.loadItemNames();
            
            ObservableList<String> itemNames = FXCollections.observableArrayList();
            
            for(String name : names){
                itemNames.add(name);
            }
            itemNameBox.setItems(itemNames);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void loadItemColors(String itemName){
        try{
            Connection conn = DBConnection.getInstance().getConnection();
            
            String sql = "SELECT ics.color FROM item i JOIN item_color_stock ics ON i.item_id = ics.item_id WHERE i.item_name = ?";
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, itemName);
            
            ObservableList<String> colorList = FXCollections.observableArrayList();
            
            ResultSet results = pstm.executeQuery();
            
            while(results.next()){
                colorList.add(results.getString("color"));
            }
            
            colorBox.setItems(colorList);
            loadQty(itemName);
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void loadQty(String itemName){
        try{
            Double price = itemBO.getPriceForItem(itemName);
            unitPriceField.setText(String.valueOf(price));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void addToQuotation(ActionEvent event) {

        try{
            String itemName = String.valueOf(itemNameBox.getValue());
            String color = String.valueOf(colorBox.getValue());
            int qty = Integer.parseInt(qtyField.getText());
            double price = Double.parseDouble(unitPriceField.getText());
            double total = qty * price;

            quotationItemObList.add(new QuotationDTO(itemName,color, qty,price,total));
       
 
        loadQuotationItemTbl();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
     private void loadQuotationItemTbl() {
    
        tblQuotation.setItems(quotationItemObList);
        calcOrderTotal();
    }
     
     private void cleanFields(){
         itemNameBox.setValue(null);
         colorBox.setValue(null);
         customerField.setText("");
         qtyField.setText("");
         unitPriceField.setText("");
         subtotalLabel.setText("");
     }
     
     @FXML
    private void savePrintQuotation(ActionEvent event) {
         Connection connection = null;
        
        try{
            connection= DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            String customerName = customerField.getText();
            double fullTotal = Double.parseDouble(subtotalLabel.getText());
            
            // color?
//        String selectedColor = addingColorBox.getSelectionModel().getSelectedItem();
//        String addingId = addingIdField.getText();
//        String qty = addingQtyField.getText();
        
        // order items? - orderItemObList

          boolean r1 = quotationDAO.save(new QuotationItemDTO(customerName, fullTotal));

            if (!r1) {
                connection.rollback();
                connection.setAutoCommit(true);
                //return false;
            }

            int newId = quotationDAO.getId();

            for (QuotationDTO quotationDTO : quotationItemObList) {
                boolean r2 = quotationItemDAO.save(new QuotationDTO(newId, quotationDTO.getItemName(),quotationDTO.getColor(),quotationDTO.getQty(),quotationDTO.getUnitPrice(),quotationDTO.getLineTotal()));

                if (!r2) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    //return false;
                }
            }

            connection.commit();
            connection.setAutoCommit(true);
            cleanFields();
            tblQuotation.getItems().clear();
            printBill();
            new Alert(Alert.AlertType.INFORMATION,"Quotation Placed Successfully !").show();
        }
        catch (Exception e) {
            try { if (connection != null) connection.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            new Alert(Alert.AlertType.ERROR, "Something went Wrong !").show();
            e.printStackTrace();
        } finally {
            try { if (connection != null) connection.setAutoCommit(true); } catch (SQLException ex) { ex.printStackTrace(); }
        }
        
    }
     
     private void calcOrderTotal(){
        double total = 0.0;
        
        for(QuotationDTO quotationItem : quotationItemObList){
            total += quotationItem.getLineTotal();
        }
        
        subtotalLabel.setText(String.valueOf(total));
    }
     
     public void printBill()throws SQLException , JRException{
        
        Connection conn = DBConnection.getInstance().getConnection();
        
        InputStream inputStream = getClass().getResourceAsStream("/lk/ijse/zanystore/reports/Quotation.jrxml" );
        JasperReport jr =JasperCompileManager.compileReport(inputStream);
        
        JasperPrint jp = JasperFillManager.fillReport(jr, null , conn);
        
        JasperViewer.viewReport(jp, false);
    
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



