/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package lk.ijse.zanystore.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import lk.ijse.zanystore.App;

/**
 * FXML Controller class
 *
 * @author chathura
 */
public class CollectionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
