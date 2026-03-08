package lk.ijse.zanystore.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import lk.ijse.zanystore.App;
import lk.ijse.zanystore.bo.custom.impl.UserBOImpl;
import lk.ijse.zanystore.dao.custom.UserDAO;
//import lk.ijse.zanystore.dao.custom.impl.UserDAOImpl;
import lk.ijse.zanystore.dao.custom.impl.UserDAOImpl;
import lk.ijse.zanystore.db.DBConnection;
import lk.ijse.zanystore.dto.UserDTO;

public class SignupController implements Initializable {
    
    @FXML
    private TextField idField;
    
    @FXML
    private TextField nameField;
    
    @FXML
    private TextField addressField;
    
    @FXML
    private TextField salaryField;
    
    @FXML
    private TextField contactField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML 
    private PasswordField confirmPasswordField;
    
    @FXML
    private TableView tableUser;
    
    @FXML
    private TableColumn colName;
    
    @FXML
    private TableColumn colAddress;
    
    @FXML
    private TableColumn colSalary;
    
    @FXML
    private TableColumn colContact;
    
    @FXML
    private TableColumn colPassword;

    UserBOImpl userBO = new UserBOImpl();
    
    private final String USER_ID_REGEX = "^[0-9]+$";
    private final String USER_NAME_REGEX = "^[A-Za-z0-9\\s]{3,}$";
    private final String USER_ADDRESS_REGEX = "^[A-Za-z0-9\\s,]{3,}$";
    private final String USER_SALARY_REGEX = "^[0-9]+(?:\\.[0-9]{1,2})?$";
    private final String USER_CONTACT_REGEX = "^[0-9]{10}$";
    private final String USER_PASSWORD_REGEX = "^[A-Za-z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]{5,}$";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Sign Up Loaded");
       
        colName.setCellValueFactory(new PropertyValueFactory<>("user_name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("user_address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("user_salary"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("user_contact_no"));
        
        tableUser.setOnMouseClicked(event -> {
        Object object = tableUser.getSelectionModel().getSelectedItem();
        UserDTO selected = (UserDTO)object;
        if (selected != null) {
            nameField.setText(selected.getUser_name());
            addressField.setText(selected.getUser_address());
            salaryField.setText(String.valueOf(selected.getUser_salary()));
            contactField.setText(selected.getUser_contact_no());
            passwordField.setText(selected.getUser_password());
        }
    });
        
        loadUserTable();
    }
    
    @FXML
    private void clickSaveUser(){
        try{
            String name = nameField.getText().trim();
            String address = addressField.getText().trim();
            String salary = salaryField.getText().trim();
            String contact = contactField.getText().trim();
            String password = passwordField.getText().trim();
            String confirmPassword = confirmPasswordField.getText().trim();
            boolean userFound = false;
            
            if(!password.equals(confirmPassword)){
                new Alert(Alert.AlertType.ERROR,"Password is Not Matching You Entered !").show();
                return;
            }
            if(!name.matches(USER_NAME_REGEX)){
                    new Alert(Alert.AlertType.ERROR,"Invalid User Name").show();
                    return;
                }
            if(!address.matches(USER_ADDRESS_REGEX)){
                    new Alert(Alert.AlertType.ERROR,"Invalid Address").show();
                    return;
                }
            if(!salary.matches(USER_SALARY_REGEX)){
                    new Alert(Alert.AlertType.ERROR,"Invalid Salary Amount").show();
                    return;
                }
            if(!contact.matches(USER_CONTACT_REGEX)){
                    new Alert(Alert.AlertType.ERROR,"Invalid Contact Number").show();
                    return;
                }
            if(!password.matches(USER_PASSWORD_REGEX)){
                    new Alert(Alert.AlertType.ERROR,"Invalid Password Format").show();
                    return;
                }
            boolean result = userBO.existUser(name);
                    
                    if (result) {
                    new Alert(Alert.AlertType.ERROR, "User Name Already Exists!").show();
                    return; 
                }
                    boolean result1 = userBO.saveUser(new UserDTO(name,address,Double.parseDouble(salary),contact,password));

                        if (result1) {
                            loadUserTable();
                            new Alert(Alert.AlertType.INFORMATION, "User Registered Successfully !").show();
                            cleanFields();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Registration Unsuccessful !").show();
                        }
                    
                }
                catch(Exception e){
                    new Alert(Alert.AlertType.ERROR, "Something went Wrong !").show();
                    e.printStackTrace();
                }
    }
    
    @FXML
    private void clickDeleteUser(){
        try{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText("Are you sure you want to Delete User?");

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");

        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == yesButton) {
           String userName  = nameField.getText().trim();

           boolean delResult = userBO.deleteUser(userName);
            if(delResult){
                loadUserTable();
                new Alert(Alert.AlertType.INFORMATION,"User Deleted Successfully !").show();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Deletion Unsuccessfull !").show();
            }
        } 
        
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void clickUpdateUser(){
        try{
            String name = nameField.getText().trim();
            String address = addressField.getText().trim();
            String salary = salaryField.getText().trim();
            String password = passwordField.getText().trim();
            String contact = contactField.getText().trim();
            String confirmPassword = confirmPasswordField.getText().trim();
            
            if(!password.equals(confirmPassword)){
                new Alert(Alert.AlertType.ERROR,"Password is Not Matching You Entered !").show();
                return;
            }
            if(!name.matches(USER_NAME_REGEX)){
                    new Alert(Alert.AlertType.ERROR,"Invalid User Name").show();
                    return;
                }
            if(!address.matches(USER_ADDRESS_REGEX)){
                    new Alert(Alert.AlertType.ERROR,"Invalid Address").show();
                    return;
                }
            if(!salary.matches(USER_SALARY_REGEX)){
                    new Alert(Alert.AlertType.ERROR,"Invalid Salary Amount").show();
                    return;
                }
            if(!contact.matches(USER_CONTACT_REGEX)){
                    new Alert(Alert.AlertType.ERROR,"Invalid Contact Number").show();
                    return;
                }
            if(!password.matches(USER_PASSWORD_REGEX)){
                    new Alert(Alert.AlertType.ERROR,"Invalid Password Format").show();
                    return;
                }
            
            UserDTO user = userBO.findUser(name);
            
            if(user != null){
                String userPassword = user.getUser_password();
            
            String enteredPassword = askForPassword(
            "Confirm Password",
            "Please enter your existing password",
            "Password:"
                );

            if (enteredPassword == null) {
                return;
            }

            if (enteredPassword.equals(userPassword)) {
                // allow update
                
            boolean results = userBO.updateUser(new UserDTO(name, address, Double.parseDouble(salary), contact, password));
            
            if(results){
                loadUserTable();
                new Alert(Alert.AlertType.INFORMATION,"Updated Successfully !").show();
            }
            } else {
                new Alert(Alert.AlertType.ERROR, "You are not Allowed to Change !").show();
            }
            }
            else{
                new Alert(Alert.AlertType.ERROR,"User Not Found !").show();
            }
            
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void loadUserTable(){
        try{
            List<UserDTO> userList = userBO.getAllUser();

            ObservableList<UserDTO> obList = FXCollections.observableArrayList();

            for(UserDTO userDTO : userList){
                obList.add(userDTO);
            }

            tableUser.setItems(obList);
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
    private void cleanFields(){
        nameField.setText("");
        addressField.setText("");
        salaryField.setText("");
        contactField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
    }
    
    @FXML
    private String askForPassword(String title, String header, String label) {
    Dialog<String> dialog = new Dialog<>();
    dialog.setTitle(title);
    dialog.setHeaderText(header);

    PasswordField pf = new PasswordField();
    pf.setPromptText(label);

    VBox box = new VBox(10, new Label(label), pf);
    dialog.getDialogPane().setContent(box);

    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

    dialog.setResultConverter(button -> {
        if (button == ButtonType.OK) {
            return pf.getText();
        }
        return null;   // cancel or close
    });

    return dialog.showAndWait().orElse(null);
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
