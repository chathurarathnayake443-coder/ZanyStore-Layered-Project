package lk.ijse.zanystore.controller;

import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.zanystore.App;
import lk.ijse.zanystore.bo.BOFactory;
import lk.ijse.zanystore.bo.custom.ItemBO;
import lk.ijse.zanystore.db.DBConnection;
import lk.ijse.zanystore.dto.QueryDTO.LoadItemDTO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class ItemController implements Initializable {

    @FXML private TextField idField, nameField, typeField, colorField, priceField, qtyField;
    @FXML private ComboBox<String> nameBox, colorBox;
    @FXML private TableView<LoadItemDTO> tableItem;
    @FXML private TableColumn<LoadItemDTO, Integer> colId;
    @FXML private TableColumn<LoadItemDTO, String> colName, colType, colColor;
    @FXML private TableColumn<LoadItemDTO, Double> colPrice;
    @FXML private TableColumn<LoadItemDTO, Integer> colQty;

    ItemBO itemBO = (ItemBO) BOFactory.getInstance().getBOFactory(BOFactory.BOTypes.ITEM);

    private final String ITEM_NAME_REGEX = "^[A-Za-z0-9\\s]{3,}$";
    private final String ITEM_TYPE_REGEX = "^[A-Za-z]{1,}$";
    private final String ITEM_COLOR_REGEX = "^[A-Za-z]{1,}$";
    private final String ITEM_PRICE_REGEX = "^[0-9]+(?:\\.[0-9]{1,2})?$";
    private final String ITEM_QTY_REGEX = "^[0-9]{1,}$";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Item Page Loaded");

        colId.setCellValueFactory(new PropertyValueFactory<>("item_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        colType.setCellValueFactory(new PropertyValueFactory<>("item_type"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("item_unit_price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        tableItem.setOnMouseClicked(event -> {
            LoadItemDTO selected = tableItem.getSelectionModel().getSelectedItem();
            if (selected != null) {
                idField.setText(String.valueOf(selected.getItem_id()));
                nameField.setText(selected.getItem_name());
                typeField.setText(selected.getItem_type());
                priceField.setText(String.valueOf(selected.getItem_unit_price()));
                qtyField.setText(String.valueOf(selected.getQty()));
                nameBox.setValue(selected.getItem_name());
                colorBox.setValue(selected.getColor());
            }
        });

        nameBox.setEditable(true);
        colorBox.setEditable(true);

        loadItemTable();
        loadItemNames();

        nameBox.getSelectionModel().selectedItemProperty().addListener((obs, oldName, newName) -> {
            if (newName == null) {
                colorBox.getItems().clear();
                nameField.setText("");
                colorField.setText("");
                qtyField.setText("");
                return;
            }
            nameField.setText(newName);
            try {
                int itemId = getItemIdByName(newName);
                populateColorsForItem(itemId);
                int total = getTotalQtyForItem(itemId);
                qtyField.setText(String.valueOf(total));
                colorField.setText(""); // clear until color selected
            } catch (SQLException ex) {
                ex.printStackTrace();
                new Alert(Alert.AlertType.ERROR,"Error from Database").show();
            }
        });

        colorBox.getSelectionModel().selectedItemProperty().addListener((obs, oldColor, newColor) -> {
            String itemName = nameBox.getValue();
            if (itemName == null || newColor == null) {
                colorField.setText("");
                qtyField.setText("");
                return;
            }
            colorField.setText(newColor);
            try {
                int itemId = getItemIdByName(itemName);
                int colorQty = getColorQty(itemId, newColor);
                qtyField.setText(String.valueOf(colorQty));
            } catch (SQLException ex) {
                ex.printStackTrace();
                new Alert(Alert.AlertType.ERROR,"Error from Database").show();
            }
        });
        
        showNextId();
    }

    @FXML
    private void clickAddItem() {
        try {
            String name = (nameBox.getValue() != null && !nameBox.getValue().trim().isEmpty()) 
                    ? nameBox.getValue().trim() : nameField.getText().trim();
            String type = typeField.getText().trim();
            String color = (colorBox.getValue() != null && !colorBox.getValue().trim().isEmpty()) 
                    ? colorBox.getValue().trim() : colorField.getText().trim();
            String priceText = priceField.getText().trim();
            String qtyText = qtyField.getText().trim();

            if (!name.matches(ITEM_NAME_REGEX) || !type.matches(ITEM_TYPE_REGEX) ||
                !color.matches(ITEM_COLOR_REGEX) || !priceText.matches(ITEM_PRICE_REGEX) ||
                !qtyText.matches(ITEM_QTY_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid input!").show();
                return;
            }

            int qtyToAdd = Integer.parseInt(qtyText);
            double unitPrice = Double.parseDouble(priceText);

            boolean result = itemBO.saveItem(color, qtyToAdd, name, type, unitPrice);
            if (!result) {
                new Alert(Alert.AlertType.ERROR, "Failed to Save Item!").show();
                return;
            }
            new Alert(Alert.AlertType.INFORMATION, "Item Saved!").show();
            loadItemTable();
            showNextId();

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Unexpected error").show();
        }
    }

    @FXML
    private void clickDeleteItem() {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText("Are you sure you want to Delete Item?");

            ButtonType yesButton = new ButtonType("Yes");
            ButtonType noButton = new ButtonType("No");
            alert.getButtonTypes().setAll(yesButton, noButton);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == yesButton) {
                String idText = idField.getText();
                if (idText == null || idText.trim().isEmpty()) {
                    new Alert(Alert.AlertType.ERROR, "Enter an Item ID").show();
                    return;
                }

                int itemId = Integer.parseInt(idText.trim());

                boolean deleted = itemBO.deleteItem(itemId);

                if (deleted) {
                    new Alert(Alert.AlertType.INFORMATION, "Item Deleted Successfully!").show();
                    cleanFields();
                    loadItemTable();
                    loadItemNames();
                    showNextId();
                } else {
                    new Alert(Alert.AlertType.ERROR, "No item found with ID: " + itemId).show();
                }
            }

        } catch (NumberFormatException nfe) {
            new Alert(Alert.AlertType.ERROR, "Invalid ID format").show();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    @FXML
    private void clickReset() {
        loadItemTable();
        cleanFields();
    }

    private void loadItemTable() {
        try {
            List<LoadItemDTO> itemList = itemBO.loadItemTable();
            tableItem.setItems(FXCollections.observableArrayList(itemList));

        } catch (Exception e) { e.printStackTrace(); }
    }

    private void loadItemNames() {
        try {
            List<String> itemNames = itemBO.loadItemNames();
            ObservableList<String> names = FXCollections.observableArrayList();
            for(String name : itemNames){
                names.add(name);
            }
            nameBox.setItems(names);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void populateColorsForItem(int itemId) {
        try {
            List<String> colors = itemBO.populateColors(itemId);
            ObservableList<String> colorNames = FXCollections.observableArrayList();
            for(String name : colors){
                colorNames.add(name);
            }
            colorBox.setItems(colorNames);
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    private int getItemIdByName(String itemName) throws SQLException {
        int id = itemBO.getItemIdByName(itemName);
        if (id == 0) {
            throw new SQLException("Item not found: " + itemName);
        }
        return id;
    }

    private int getTotalQtyForItem(int itemId) {
        try {
            int qty = itemBO.getTotalQtyForItem(itemId);
            return qty;
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    private int getColorQty(int itemId, String color) {
        try {
            int qty = itemBO.getColorQty(itemId, color);
            return qty;
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    @FXML
    private void cleanFields() {
        idField.setText("");
        nameField.setText("");
        typeField.setText("");
        priceField.setText("");
        colorField.setText("");
        qtyField.setText("");
        nameBox.getSelectionModel().clearSelection();
        colorBox.getSelectionModel().clearSelection();
    }
    
    @FXML
private void showNextId(){
    try{
        String id = itemBO.showNextId();
        idField.setText(id);
    }
    catch(Exception e){
        e.printStackTrace();
    }
}


    public void printReport()throws SQLException , JRException{
        
        Connection conn = DBConnection.getInstance().getConnection();
        
        InputStream inputStream = getClass().getResourceAsStream("/lk/ijse/zanystore/reports/Items.jrxml" );
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

