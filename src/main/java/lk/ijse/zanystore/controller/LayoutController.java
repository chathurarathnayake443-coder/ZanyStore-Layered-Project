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
import lk.ijse.zanystore.bo.BOFactory;
import lk.ijse.zanystore.bo.custom.LayoutBO;
import lk.ijse.zanystore.bo.custom.PlaceOrderBO;
import lk.ijse.zanystore.bo.custom.QueryBO;
import lk.ijse.zanystore.bo.custom.impl.LayoutBOImpl;
import lk.ijse.zanystore.bo.custom.impl.PlaceOrderBOImpl;
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
import lk.ijse.zanystore.dto.QueryDTO.LoadLastOrderDTO;
import lk.ijse.zanystore.dto.QueryDTO.LoadLastPaymentDTO;
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

    LayoutBO layoutBO = (LayoutBO) BOFactory.getInstance().getBOFactory(BOFactory.BOTypes.LAYOUT);

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
            int orderCount = layoutBO.getOrderCount();
            ordersLbl.setText(String.valueOf(orderCount));
            animateOrderCount(orderCount);

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void getReturnCount(){
        try{
            int totalReturns = layoutBO.getReturnOrderCount();
            returnsLbl.setText(String.valueOf(totalReturns));
            animateReturnCount(totalReturns);

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
      private void findTotal(){
          try{
              double amount = layoutBO.getSumOfPayments();

              String total = String.valueOf(amount/1000);
              salesLbl.setText(total);
              int total1 = (int)amount/1000;
              animateFindTotal(total1);

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

            LoadLastOrderDTO lastOrder = layoutBO.loadLastOrderTable();
                
                lbl1.setText(String.valueOf(lastOrder.getCloth_order_id()));
                lbl2.setText(lastOrder.getCustomer_name());
                lbl3.setText(lastOrder.getCloth_order_description());
                lbl7.setText(lastOrder.getCloth_order_end_date());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void loadLastPayment(){
        try{
                LoadLastPaymentDTO lastPayment = layoutBO.loadLastPaymentTable();
                lbl4.setText(String.valueOf(lastPayment.getPayment_id()));
                lbl5.setText(lastPayment.getCustomer_name());
                lbl6.setText(String.valueOf(lastPayment.getPayment_amount()));
                lbl8.setText(lastPayment.getDate());

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
            
            boolean result = layoutBO.addTask(new TaskDTO(task,date));
            
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
            
            List<TaskDTO> taskList = layoutBO.viewAllTasks();

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
            boolean result = layoutBO.deleteTask(task);
            if (!result) {
                new Alert(Alert.AlertType.ERROR, "Failed to remove task!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error deleting task!").show();
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
