/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package lk.ijse.zanystore.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.zanystore.App;
import lk.ijse.zanystore.bo.custom.impl.PaymentBOImpl;
import lk.ijse.zanystore.dao.custom.impl.OrderPaymentDAOImpl;
import lk.ijse.zanystore.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.zanystore.db.DBConnection;
import lk.ijse.zanystore.dto.PaymentDTO;
//import lk.ijse.zanystore.model.PaymentModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author chathura
 */
public class PaymentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField amountField;

    @FXML
    private TextField cIdField;
    
     @FXML
    private TextField dateField;

    @FXML
    private TextField methodField;

    @FXML
    private TextField oIdField;

    @FXML
    private Button orderBtn;

    @FXML
    private TextField pIdField;

    @FXML
    private Button saveBtn;
    
    @FXML
    private TableColumn colPId;
    
    @FXML
    private TableColumn colOId;
    
    @FXML
    private TableColumn colCId;
    
    @FXML
    private TableColumn colName;
    
    @FXML
    private TableColumn colAmount;
    
    @FXML
    private TableColumn colDate;
    
    @FXML
    private DatePicker dateBox;
    
     @FXML
    private ComboBox<String> methodBox;
     
     @FXML
     private Label totalLbl;
     
     @FXML
     private TableView tablePayment;

     PaymentBOImpl paymentBO = new PaymentBOImpl();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showNextId();
        loadPaymentTypes();
        
        colPId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colOId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        loadpaymentTable();
        findTotal();
        
    }  
    
    @FXML
private void backToOrders() throws IOException {
    Parent orderFXML = App.loadFXML("newOrder");
    LayoutController.getInstance()
            .getMainContent()
            .getChildren()
            .setAll(orderFXML);
}
        
         @FXML
private void showNextId(){
    try{
        String id = paymentBO.generateNextPaymentId();
        pIdField.setText(id);
    }
    catch(Exception e){
        e.printStackTrace();
    }
}
        
        @FXML
        private void makePayment() {
            Connection connection = null;
            try {
                connection = DBConnection.getInstance().getConnection();
                connection.setAutoCommit(false);

                String pId = pIdField.getText();
                String oId = oIdField.getText();
                String cId = cIdField.getText();
                String amount = amountField.getText();
                String method = methodBox.getValue();
                String date = String.valueOf(dateBox.getValue());

                System.out.println("payment id : " + pIdField.getText());

                boolean result = paymentBO.savePayment(pId,oId,cId,amount,method,date);

                if(!result){
                    new Alert(Alert.AlertType.ERROR, "Failed to Place Payment !").show();
                    return;
                }

                new Alert(Alert.AlertType.INFORMATION, "Payment Successfull").show();
                loadpaymentTable();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went Wrong !").show();
                e.printStackTrace();

            }
        }
        
       private void loadPaymentTypes() {
    methodBox.getItems().addAll("Cash", "Cheque");
    methodBox.getSelectionModel().selectFirst(); // optional default
}
       
       @FXML
       private void loadpaymentTable(){
           try{
               Connection conn = DBConnection.getInstance().getConnection();
               
               String sql = "SELECT p.payment_id, p.payment_amount, o.cloth_order_id, c.customer_name, o.date FROM payment p JOIN order_payment_details o ON p.payment_id = o.payment_id JOIN cloth_order co on o.cloth_order_id = co.cloth_order_id JOIN customer c on o.customer_id = c.customer_id and co.customer_id = c.customer_id";
               
               PreparedStatement pstm = conn.prepareStatement(sql);
               
               ResultSet results = pstm.executeQuery();
               
               List<PaymentDTO> paymentList = new ArrayList<>();
               
               while(results.next()){
                   int paymentId = results.getInt("payment_id");
                   int orderId = results.getInt("cloth_order_id");
                   String name = results.getString("customer_name");
                   double amount = results.getDouble("payment_amount");
                   String date = results.getString("date");

                   paymentList.add(new PaymentDTO(paymentId, orderId, name, amount, date));
               }
               
               ObservableList<PaymentDTO> obList = FXCollections.observableArrayList();
               
               for(PaymentDTO paymentDTO : paymentList){
                   obList.add(paymentDTO);
               }
               
               tablePayment.setItems(obList);
           }
           catch(Exception e){
               e.printStackTrace();
           }
       }
       
      @FXML
      private void findTotal(){
          try{
              String total = paymentBO.getTotalAmount();
              totalLbl.setText(total);
          }
          catch(Exception e){
              e.printStackTrace();
          }
      }
      
      
      public void printReport()throws SQLException , JRException{
        
        Connection conn = DBConnection.getInstance().getConnection();
        
        InputStream inputStream = getClass().getResourceAsStream("/lk/ijse/zanystore/reports/Sales.jrxml" );
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

public void printBill()throws SQLException , JRException{
        
        Connection conn = DBConnection.getInstance().getConnection();
        
        InputStream inputStream = getClass().getResourceAsStream("/lk/ijse/zanystore/reports/Bill.jrxml" );
        JasperReport jr =JasperCompileManager.compileReport(inputStream);
        
        JasperPrint jp = JasperFillManager.fillReport(jr, null , conn);
        
        JasperViewer.viewReport(jp, false);
    
    }
    
}
