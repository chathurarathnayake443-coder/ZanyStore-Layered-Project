package lk.ijse.zanystore.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.zanystore.App;
import lk.ijse.zanystore.bo.custom.impl.UserBOImpl;
import lk.ijse.zanystore.dao.custom.UserDAO;
import lk.ijse.zanystore.dao.custom.impl.UserDAOImpl;
import lk.ijse.zanystore.db.DBConnection;
import lk.ijse.zanystore.dto.UserDTO;
//import lk.ijse.zanystore.model.LoginModel;

public class LoginController implements Initializable {

    @FXML
    private TextField usernameField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML 
    private AnchorPane mainContent;

    UserBOImpl userBO = new UserBOImpl();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Log In Page Loaded");
        
        try{
            Parent imagesliderFXML = App.loadFXML("imageslider");
        mainContent.getChildren().setAll(imagesliderFXML);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }   
    
    @FXML
    private void login() {
        try{
            String name = usernameField.getText().trim();
            String password = passwordField.getText().trim();
            boolean userNotFound = true;
        
            List<UserDTO> list = userBO.getAllUser();
            
            for(UserDTO user : list){
                if(user.getUser_name().equals(name) && user.getUser_password().equalsIgnoreCase(password)){
                    //new Alert(Alert.AlertType.INFORMATION,"Logged In Successfully !").show();
                    userNotFound = false;
                    App.setRoot("layout");
                }
            }
            
            if(userNotFound){
                new Alert(Alert.AlertType.ERROR,"Oops! User Not Found").show();
                cleanFields();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handlePressLogin(KeyEvent event){
        
        try{
            if(event.getCode() == KeyCode.ENTER){
            String name = usernameField.getText().trim();
            String password = passwordField.getText().trim();
            
            boolean userNotFound = true;

                List<UserDTO> list = userBO.getAllUser();

                for(UserDTO user : list){
                    if(user.getUser_name().equals(name) && user.getUser_password().equalsIgnoreCase(password)){
                        //new Alert(Alert.AlertType.INFORMATION,"Logged In Successfully !").show();
                        userNotFound = false;
                        App.setRoot("layout");
                    }
                }
            
            if(userNotFound){
                new Alert(Alert.AlertType.ERROR,"Oops! User Not Found").show();
                cleanFields();
            }
                
            
        }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    
    private void cleanFields(){
        usernameField.setText("");
        passwordField.setText("");
    }
    
    @FXML
    private void clickLogOut() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText("Are you sure you want to exit?");

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");

        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == yesButton) {
            Platform.exit();
        } 
    }
    
}
