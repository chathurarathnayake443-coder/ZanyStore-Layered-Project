package lk.ijse.zanystore.controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.zanystore.App;
import lk.ijse.zanystore.bo.BOFactory;
import lk.ijse.zanystore.bo.custom.EmployeeBO;
import lk.ijse.zanystore.bo.custom.impl.EmployeeBOImpl;
import lk.ijse.zanystore.dao.custom.EmployeeDAO;
import lk.ijse.zanystore.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.zanystore.dto.EmployeeDTO;

public class EmployeeController implements Initializable {
    
    @FXML
    private TextField idField;
    
    @FXML
    private TextField nameField;
    
    @FXML
    private TextField ageField;
    
    @FXML
    private TextField addressField;
    
    @FXML
    private TextField salaryField;
    
    @FXML
    private TextField contactField;
    
    @FXML
    private TextArea noteField;
    
    @FXML
    private TableView tableEmployee;
    
    @FXML
    private TableColumn colId;
    
    @FXML
    private TableColumn colName;
    
    @FXML
    private TableColumn colAge;
    
    @FXML
    private TableColumn colAddress;
    
    @FXML
    private TableColumn colSalary;
    
    @FXML
    private TableColumn colContact;
    
    @FXML
    private TableColumn colNotes;
    
    private final String EMP_ID_REGEX = "^[0-9]+$";
    private final String EMP_AGE_REGEX = "^[0-9]{1,3}$";
    private final String EMP_NAME_REGEX = "^[A-Za-z0-9\\s]{3,}$";
    private final String EMP_ADDRESS_REGEX = "^[A-Za-z0-9\\s,]{3,}$";
    private final String EMP_SALARY_REGEX = "^[0-9]+(?:\\.[0-9]{1,2})?$";
    private final String EMP_CONTACT_REGEX = "^[0-9]{10}$";

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getInstance().getBOFactory(BOFactory.BOTypes.EMPLOYEE);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Employee View Loaded");
        
        colId.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("employee_name"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("employee_age"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("employee_address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("employee_salary"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("employee_contact_number"));
        colNotes.setCellValueFactory(new PropertyValueFactory<>("employee_special_notes"));
        
        tableEmployee.setOnMouseClicked(event -> {
        Object object = tableEmployee.getSelectionModel().getSelectedItem();
        EmployeeDTO selected = (EmployeeDTO)object;
        if (selected != null) {
            idField.setText(String.valueOf(selected.getEmployee_id()));
            nameField.setText(selected.getEmployee_name());
            ageField.setText(String.valueOf(selected.getEmployee_age()));
            addressField.setText(selected.getEmployee_address());
            salaryField.setText(String.valueOf(selected.getEmployee_salary()));
            contactField.setText(selected.getEmployee_contact_number());
            noteField.setText(selected.getEmployee_special_notes());
        }
    });
        
        loadEmployeeTable();
        showNextId();
    } 
    
    @FXML
    private void searchEmployee(KeyEvent event){
        try{
            if(event.getCode() == KeyCode.ENTER){
                String id = idField.getText();
                
                if(!(id.matches(EMP_ID_REGEX))){
                new Alert(Alert.AlertType.ERROR,"Invalid ID").show();
            }
            else{
              
                    EmployeeDTO employeeDTO = employeeBO.searchEmployee(Integer.parseInt(id));
                    
                    if(employeeDTO != null){
                        idField.setText(String.valueOf(employeeDTO.getEmployee_id()));
                        nameField.setText(employeeDTO.getEmployee_name());
                        ageField.setText(String.valueOf(employeeDTO.getEmployee_age()));
                        addressField.setText(employeeDTO.getEmployee_address());
                        salaryField.setText(String.valueOf(employeeDTO.getEmployee_salary()));
                        contactField.setText(employeeDTO.getEmployee_contact_number());
                        noteField.setText(employeeDTO.getEmployee_special_notes());     
                    }
                    else{
                        new Alert(Alert.AlertType.ERROR,"Employee Not Found").show();
                    }
            }
        }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void clickDeleteEmployee(){
        try{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText("Are you sure you want to Delete Employee?");

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");

        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == yesButton) {
            String id = idField.getText().trim();
        
            boolean deleteResult = employeeBO.deleteEmployee(Integer.parseInt(id));
            
            if(deleteResult){
                new Alert(Alert.AlertType.INFORMATION,"Employee Deleted Successfully !").show();
                loadEmployeeTable();
                cleanFields();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Employee Deletion Unsuccessfull").show();
            }
        } 
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void clickUpdateEmployee(){
        try{
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String age = ageField.getText().trim();
            String address = addressField.getText().trim();
            String salary = salaryField.getText().trim();
            String contactNumber = contactField.getText().trim();
            String specialNotes = noteField.getText().trim();
            
            if(!name.matches(EMP_NAME_REGEX)){
                    new Alert(Alert.AlertType.ERROR,"Invalid Employee Name").show();
                    return;
                }
            
            if(!age.matches(EMP_AGE_REGEX)){
                    new Alert(Alert.AlertType.ERROR,"Invalid Employee Age").show();
                    return;
                }
            
            if(!address.matches(EMP_ADDRESS_REGEX)){
                    new Alert(Alert.AlertType.ERROR,"Invalid Employee Address").show();
                    return;
                }
            
            if(!salary.matches(EMP_SALARY_REGEX)){
                    new Alert(Alert.AlertType.ERROR,"Invalid Employee Salary").show();
                    return;
                }
            
            if(!contactNumber.matches(EMP_CONTACT_REGEX)){
                    new Alert(Alert.AlertType.ERROR,"Invalid Employee Age").show();
                    return;
                }
            boolean result = employeeBO.updateEmployee(new EmployeeDTO(Integer.parseInt(id),name,Integer.parseInt(age),address,Double.parseDouble(salary),contactNumber,specialNotes));
            
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Employee Updated Successfully !").show();
                loadEmployeeTable();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Update Operation Failed !").show();
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void clickSaveEmployee(){
        try{
            String name = nameField.getText().trim();
            String age = ageField.getText().trim();
            String address = addressField.getText().trim();
            String salary = salaryField.getText().trim();
            String contactNumber = contactField.getText().trim();
            String specialNotes = noteField.getText().trim();
            
            if(!name.matches(EMP_NAME_REGEX)){
                    new Alert(Alert.AlertType.ERROR,"Invalid Employee Name").show();
                    return;
                }
            
            if(!age.matches(EMP_AGE_REGEX)){
                    new Alert(Alert.AlertType.ERROR,"Invalid Employee Age").show();
                    return;
                }
            
            if(!address.matches(EMP_ADDRESS_REGEX)){
                    new Alert(Alert.AlertType.ERROR,"Invalid Employee Address").show();
                    return;
                }
            
            if(!salary.matches(EMP_SALARY_REGEX)){
                    new Alert(Alert.AlertType.ERROR,"Invalid Employee Salary").show();
                    return;
                }
            
            if(!contactNumber.matches(EMP_CONTACT_REGEX)){
                    new Alert(Alert.AlertType.ERROR,"Invalid Employee Age").show();
                    return;
                }
            boolean result = employeeBO.saveEmployee(new EmployeeDTO(name,Integer.parseInt(age),address,Double.parseDouble(salary),contactNumber,specialNotes));
            
            if(result){
                new Alert(Alert.AlertType.INFORMATION,"Employee Added Successfully !").show();
                loadEmployeeTable();
                cleanFields();
            }
            else{
                new Alert(Alert.AlertType.ERROR,"Employee not Saved !").show();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void loadEmployeeTable(){
        try{
            List<EmployeeDTO> employeeList = employeeBO.loadEmployeeTable();
            
            ObservableList<EmployeeDTO> obList = FXCollections.observableArrayList();
            
            for(EmployeeDTO employeeDTO : employeeList){
                obList.add(employeeDTO);
            }
            
            tableEmployee.setItems(obList);
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
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        addressField.setText("");
        salaryField.setText("");
        contactField.setText("");
        noteField.setText("");
    }
    
    @FXML
private void showNextId(){
    try{
        String id = employeeBO.showNextId();
        idField.setText(id);
    }
    catch(Exception e){
        e.printStackTrace();
    }
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
