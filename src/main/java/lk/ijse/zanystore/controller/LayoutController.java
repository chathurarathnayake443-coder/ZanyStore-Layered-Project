package lk.ijse.zanystore.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import lk.ijse.zanystore.App;
import lk.ijse.zanystore.dao.custom.TaskDAO;
import lk.ijse.zanystore.dao.custom.impl.TaskDAOImpl;
import lk.ijse.zanystore.db.DBConnection;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import lk.ijse.zanystore.dto.OrderItemDTO;
import lk.ijse.zanystore.dto.TaskDTO;

public class LayoutController implements Initializable {
    
    @FXML
    private AnchorPane mainContent;
    
    @FXML
    private Button createNewUserBtn;
    
    @FXML
    private Button customerBtn;

    @FXML
    private Button employeeBtn;

    @FXML
    private Button itemBtn;

    @FXML
    private Button logOutBtn;

    @FXML
    private Button newOrderBtn;

    @FXML
    private Button quotationBtn;

    @FXML
    private Button returnBtn;

    @FXML
    private Button serviceBtn;

    @FXML
    private Button supplierBtn;
    
    @FXML 
    private ImageView zanyLogo;
    
    @FXML
    private Label salesLbl;
    
    @FXML
    private Label ordersLbl;
    
    @FXML
    private Label returnsLbl;
    
    @FXML
    private Label lbl1;
    
    @FXML
    private Label lbl2;
    
    @FXML
    private Label lbl3;
    
    @FXML
    private Label lbl4;
    
    @FXML
    private Label lbl5;
    
    @FXML
    private Label lbl6;
    
    @FXML
    private Label lbl7;
    
    @FXML
    private Label lbl8;
    
    @FXML
    private Label lbl9;
    
    @FXML
    private DatePicker taskDate;
    
    @FXML
    private TextField taskField;
    
    @FXML
    private TableView taskTable;
    
    @FXML
    private TableColumn colTask;
    
    @FXML
    private TableColumn colDate;
    
    @FXML
    private TableColumn colEdit;
    
    private final ObservableList<TaskDTO> obList = FXCollections.observableArrayList();
    
    @FXML
private AnchorPane imageSliderPane;

    TaskDAO taskDAO = new TaskDAOImpl();

private final List<Image> images = new ArrayList<>();
private final List<ImageView> imageViews = new ArrayList<>();
private double sliderSpeed = 1.0; // pixels per frame
  
    private static LayoutController instance;
    
    private String defaultStyle;
private String hoverStyle;
private String pressedStyle;

private Button activeButton;

    private static final String DEFAULT_STYLE =
            "-fx-background-color:   #8d72f7;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 15;";

    private static final String HOVER_STYLE =
            "-fx-background-color: #ada5f8;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 15;";

    private static final String ACTIVE_STYLE =
            "-fx-background-color: white;" +
            "-fx-text-fill: #773396;" +
            "-fx-background-radius: 15;";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Layout Page Loaded");
        
        setupButton(createNewUserBtn);
        setupButton(customerBtn);
        setupButton(employeeBtn);
        setupButton(itemBtn);
        setupButton(newOrderBtn);
        setupButton(quotationBtn);
        setupButton(returnBtn);
        setupButton(serviceBtn);
        setupButton(supplierBtn);
        //setupButton(logOutBtn);
        
        getOrderCount();
        getReturnCount();
        findTotal();
        instance = this;
        
        loadLastOrder();
        loadLastPayment();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd | HH:mm:ss");

Timeline clock = new Timeline(
        new KeyFrame(Duration.ZERO, e ->
                lbl9.setText(LocalDateTime.now().format(formatter))
        ),
        new KeyFrame(Duration.seconds(1))
);

clock.setCycleCount(Animation.INDEFINITE);
clock.play();

        
        colTask.setCellValueFactory(new PropertyValueFactory<>("task_name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("task_date"));
        
        colEdit.setCellFactory(cell -> new TableCell<TaskDTO, Void>(){
            Button btn = new Button("Remove");
            
            {
                
                btn.setStyle(
            "-fx-background-color: #4b9e34;" +
            "-fx-text-fill: white;" +
            "-fx-padding: 6 12 6 12;" +
            "-fx-background-radius: 5;"
        );
                
                btn.setOnAction(event -> {
                    TaskDTO task = getTableView().getItems().get(getIndex());
                    deleteTask(task); 
                    loadTaskTable();
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
        
        loadTaskTable();
        taskField.setPromptText("Enter Task Here ...");

    } 
    
    public AnchorPane getMainContent(){
        return mainContent;
    }
    
    public static LayoutController getInstance() {
        return instance;
    }
    
private void setupButton(Button btn) {
    if (btn == null) return;

    btn.setStyle(DEFAULT_STYLE);

    // hover ON
    btn.setOnMouseEntered(e -> {
        if (btn != activeButton) {
            btn.setStyle(HOVER_STYLE);
        }
    });

    // hover OFF
    btn.setOnMouseExited(e -> {
        if (btn != activeButton) {
            btn.setStyle(DEFAULT_STYLE);
        }
    });
}
    
     private void setActiveButton(Button btn) {

        if (activeButton != null) {
            activeButton.setStyle(DEFAULT_STYLE);
        }

        btn.setStyle(ACTIVE_STYLE);
        activeButton = btn;
    }
   
    @FXML
    private void clickCreateNewUser() throws IOException{
        setActiveButton(createNewUserBtn);
        Parent signupFXML = App.loadFXML("signup");
        mainContent.getChildren().setAll(signupFXML);
    }
    
    @FXML
    private void clickItem() throws IOException{
        setActiveButton(itemBtn);
        Parent itemFXML = App.loadFXML("item");
        mainContent.getChildren().setAll(itemFXML);
    }
    
    @FXML
    private void clickNewOrder() throws IOException{
        setActiveButton(newOrderBtn);
        Parent orderFXML = App.loadFXML("newOrder");
        mainContent.getChildren().setAll(orderFXML);
    }
    
    @FXML
    private void clickEmployeeDetails() throws IOException{
        setActiveButton(employeeBtn);
        Parent employeeFXML = App.loadFXML("employee");
        mainContent.getChildren().setAll(employeeFXML);
    }
    
    @FXML
    private void clickCustomerDetails() throws IOException{
        setActiveButton(customerBtn);
        Parent customerFXML = App.loadFXML("customer");
        mainContent.getChildren().setAll(customerFXML);
    }
    
    @FXML
    private void clickSupplierDetails() throws IOException{
        setActiveButton(supplierBtn);
        Parent supplierFXML = App.loadFXML("supplier");
        mainContent.getChildren().setAll(supplierFXML);
    }
    
    @FXML
    private void clickServiceProviderDetails() throws IOException{
        setActiveButton(serviceBtn);
        Parent serviceproviderFXML = App.loadFXML("serviceprovider");
        mainContent.getChildren().setAll(serviceproviderFXML);
    }
    
    @FXML
    private void clickReturnedOrders() throws IOException{
        setActiveButton(returnBtn);
        Parent returnFXML = App.loadFXML("return");
        mainContent.getChildren().setAll(returnFXML);
    }
    
    @FXML
    private void clickMakeQuotation() throws IOException{
        setActiveButton(quotationBtn);
        Parent quotationFXML = App.loadFXML("quotation");
        mainContent.getChildren().setAll(quotationFXML);
    }
    
    @FXML
    private void clickCollection() throws IOException{
        Parent collectionFXML = App.loadFXML("collection");
        mainContent.getChildren().setAll(collectionFXML);
    }
    
    @FXML
    private void clickLogOut() {
        try{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        
        alert.setTitle("Log Out Confirmation");
        alert.setHeaderText("Are you sure you want to log out?");

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");

        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == yesButton) {
            App.setRoot("login");
        } 
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML 
    private void logoClick(){
       try{
           App.setRoot("layout");
       }
       catch(Exception e){
           e.printStackTrace();
       }
    }
    
    @FXML
    private void getOrderCount(){
        try{
            Connection conn = DBConnection.getInstance().getConnection();
            
            PreparedStatement pstm = conn.prepareStatement("SELECT COUNT(cloth_order_id) AS total_orders FROM cloth_order");
            
            ResultSet result = pstm.executeQuery();
            
            if(result.next()){
                ordersLbl.setText(String.valueOf(result.getInt("total_orders")));
                int count = result.getInt("total_orders");
                animateOrderCount(count);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void getReturnCount(){
        try{
            Connection conn = DBConnection.getInstance().getConnection();
            
            PreparedStatement pstm = conn.prepareStatement("SELECT COUNT(return_order_id) AS total_returns FROM return_order");
            
            ResultSet result = pstm.executeQuery();
            
            if(result.next()){
                returnsLbl.setText(String.valueOf(result.getInt("total_returns")));
                int count = result.getInt("total_returns");
                animateReturnCount(count);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
      private void findTotal(){
          try{
              Connection conn = DBConnection.getInstance().getConnection();
              
              String sql = "SELECT SUM(payment_amount) AS sum FROM payment";
              
              PreparedStatement pstm = conn.prepareStatement(sql);
              
              ResultSet results = pstm.executeQuery();
              
              if(results.next()){
                String total = String.valueOf(results.getDouble("sum")/1000);
                salesLbl.setText(total);
                int total1 = (int)results.getDouble("sum")/1000;               
                animateFindTotal(total1);
              }
          }
          catch(Exception e){
              e.printStackTrace();
          }
      }
      
      private void animateFindTotal(int finalCount) {

    salesLbl.setText("0");

    int durationMillis = 800;   // total animation time
    int steps = Math.max(finalCount, 1);
    double stepTime = (double) durationMillis / steps;

    Timeline timeline = new Timeline();
    timeline.setCycleCount(finalCount);

    KeyFrame keyFrame = new KeyFrame(
            Duration.millis(stepTime),
            event -> {
                int current = Integer.parseInt(salesLbl.getText());
                salesLbl.setText(String.valueOf(current + 1));
            }
    );

    timeline.getKeyFrames().add(keyFrame);
    timeline.play();
}
    
    private void animateOrderCount(int finalCount) {

    ordersLbl.setText("0");

    int durationMillis = 400;   // total animation time
    int steps = Math.max(finalCount, 1);
    double stepTime = (double) durationMillis / steps;

    Timeline timeline = new Timeline();
    timeline.setCycleCount(finalCount);

    KeyFrame keyFrame = new KeyFrame(
            Duration.millis(stepTime),
            event -> {
                int current = Integer.parseInt(ordersLbl.getText());
                ordersLbl.setText(String.valueOf(current + 1));
            }
    );

    timeline.getKeyFrames().add(keyFrame);
    timeline.play();
}
    
    private void animateReturnCount(int finalCount) {

    returnsLbl.setText("0");

    int durationMillis = 400;   // total animation time
    int steps = Math.max(finalCount, 1);
    double stepTime = (double) durationMillis / steps;

    Timeline timeline = new Timeline();
    timeline.setCycleCount(finalCount);

    KeyFrame keyFrame = new KeyFrame(
            Duration.millis(stepTime),
            event -> {
                int current = Integer.parseInt(returnsLbl.getText());
                returnsLbl.setText(String.valueOf(current + 1));
            }
    );

    timeline.getKeyFrames().add(keyFrame);
    timeline.play();
}
    
    private void loadLastOrder(){
        try{
            Connection conn = DBConnection.getInstance().getConnection();
            
            String sql = "SELECT o.cloth_order_id, c.customer_name, o.cloth_order_description, o.cloth_order_end_date FROM cloth_order o JOIN customer c ON o.customer_id = c.customer_id ORDER BY cloth_order_id DESC LIMIT 1";
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            
            ResultSet results = pstm.executeQuery();
            
            if(results.next()){
                String id = String.valueOf(results.getInt("cloth_order_id"));
                String name = results.getString("customer_name");
                String detail = results.getString("cloth_order_description");
                String date = results.getString("cloth_order_end_date");
                
                lbl1.setText(id);
                lbl2.setText(name);
                lbl3.setText(detail);
                lbl7.setText(date);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void loadLastPayment(){
        try{
            Connection conn = DBConnection.getInstance().getConnection();
            
            String sql = "SELECT p.payment_id, p.payment_amount, c.customer_name, o.date FROM payment p JOIN order_payment_details o ON p.payment_id = o.payment_id JOIN cloth_order co on o.cloth_order_id = co.cloth_order_id JOIN customer c on o.customer_id = c.customer_id and co.customer_id = c.customer_id ORDER BY p.payment_id DESC LIMIT 1";
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            
            ResultSet results = pstm.executeQuery();
            
            if(results.next()){
                String id = String.valueOf(results.getInt("payment_id"));
                String name = results.getString("customer_name");
                String amount = String.valueOf(results.getDouble("payment_amount"));
                String date = results.getString("date");
                
                lbl4.setText(id);
                lbl5.setText(name);
                lbl6.setText(amount);
                lbl8.setText(date);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void addTask(){
        try{
            String task = taskField.getText();
            String date = String.valueOf(taskDate.getValue());
            
            boolean result = taskDAO.save(new TaskDTO(task,date));
            
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Task Added !").show();
                loadTaskTable();
                taskField.setText("");
                taskDate.setValue(null);
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Task Failed !").show();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void loadTaskTable(){
        try{
            obList.clear();
            
            List<TaskDTO> taskList = taskDAO.getAll();

            for(TaskDTO taskDTO : taskList){
                obList.add(taskDTO);
            }
            
            taskTable.setItems(obList);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void deleteTask(TaskDTO task) {
    try {
        boolean result = taskDAO.delete(task);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    @FXML
private void clickLowStock() throws IOException {
    Parent paymentFXML = App.loadFXML("lowstock");
    LayoutController.getInstance()
            .getMainContent()
            .getChildren()
            .setAll(paymentFXML);
}

    

}
