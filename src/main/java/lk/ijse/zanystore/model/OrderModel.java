/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.zanystore.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import lk.ijse.zanystore.db.DBConnection;
import lk.ijse.zanystore.dto.OrderDTO;
import lk.ijse.zanystore.util.CrudUtil;

/**
 *
 * @author chathura
 */
public class OrderModel {

    private OrderItemModel orderItemModel = new OrderItemModel();
    public boolean placeOrder(OrderDTO orderDTO) throws SQLException{

        Connection conn = DBConnection.getInstance().getConnection();

        try{
            conn.setAutoCommit(false);
            boolean result = CrudUtil.execute("INSERT INTO cloth_order (customer_id, cloth_order_description, cloth_order_start_date, cloth_order_end_date) VALUES (?,?,?,?)", orderDTO.getCustomer_id(), orderDTO.getCloth_order_description(), orderDTO.getCloth_order_start_date(),orderDTO.getCloth_order_end_date());

        if(result){
            ResultSet rs = CrudUtil.execute("SELECT cloth_order_id FROM cloth_order ORDER BY cloth_order_id DESC LIMIT 1");

            if(rs.next()){
                int orderId = rs.getInt("cloth_order_id");
                boolean result1 = orderItemModel.saveOrderItems(orderDTO.getItemList(), orderId);

                if(result1){
                    boolean result2 = CrudUtil.execute("INSERT INTO order_serprovider_details (cloth_order_id,given_date,received_date) VALUES (?,?,?)", orderId,orderDTO.getGiven_date(),orderDTO.getRecieved_date());
                }
            }

        }
        else{
            throw new SQLException();
        }
        conn.commit();
        return true;
        }
        catch(Exception e){
            conn.rollback();
            throw e;
        }
        finally{
            conn.setAutoCommit(true);
        }

    }
}
