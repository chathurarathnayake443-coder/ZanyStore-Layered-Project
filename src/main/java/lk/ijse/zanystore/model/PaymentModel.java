/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.zanystore.model;

import java.sql.Connection;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import lk.ijse.zanystore.db.DBConnection;
import lk.ijse.zanystore.dto.PaymentDTO;
import lk.ijse.zanystore.util.CrudUtil;

/**
 *
 * @author chathura
 */
public class PaymentModel {
    public boolean savePayment(PaymentDTO paymentDTO) throws SQLException{

        Connection conn = DBConnection.getInstance().getConnection();
        try{
            conn.setAutoCommit(false);
            boolean result1 = CrudUtil.execute("INSERT INTO payment (payment_amount, payment_method) VALUES (?,?)", paymentDTO.getAmount(),paymentDTO.getMethod());

        if(result1){
            boolean result2 = CrudUtil.execute("INSERT INTO order_payment_details (cloth_order_id,payment_id,customer_id,date) VALUES (?,?,?,?)", paymentDTO.getOrderId(),paymentDTO.getPaymentId(),paymentDTO.getCustomerId(),paymentDTO.getDate());
        }

        else{
            throw new SQLException();
        }
        conn.commit();
        return true;
        }
        catch(Exception e){
            e.printStackTrace();
            conn.rollback();
            throw e;
        }
        finally{
            conn.setAutoCommit(true);
        }

    }
}
