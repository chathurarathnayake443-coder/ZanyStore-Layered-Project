module lk.ijse.zanystore {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    requires net.sf.jasperreports.core;
    //requires lk.ijse.zanystore;

    opens lk.ijse.zanystore.controller to javafx.fxml;
    opens lk.ijse.zanystore.dto to java.base;
    exports lk.ijse.zanystore;
    exports lk.ijse.zanystore.controller;
    exports lk.ijse.zanystore.dto;
    exports lk.ijse.zanystore.dto.QueryDTO;
    opens lk.ijse.zanystore.dto.QueryDTO to java.base;

}

