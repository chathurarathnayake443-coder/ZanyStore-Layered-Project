/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.zanystore.model;

import java.sql.SQLException;
import lk.ijse.zanystore.util.CrudUtil;

/**
 *
 * @author chathura
 */
public class ReturnModel {

    public boolean toReturn() throws SQLException{
        boolean result = CrudUtil.execute("INSERT INTO return_order (return_order_details) VALUES (?)");
        return result;
    }

    public boolean toOrderReturn() throws SQLException{
        boolean result = CrudUtil.execute("INSERT INTO return_order_details (cloth_order_id, return_order_id, order_return_date) VALUES (?,?)");
        return result;
    }
}
