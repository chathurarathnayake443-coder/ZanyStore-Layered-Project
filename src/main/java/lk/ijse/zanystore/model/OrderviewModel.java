/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.zanystore.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lk.ijse.zanystore.dto.OrderDTO;
import lk.ijse.zanystore.util.CrudUtil;

/**
 *
 * @author chathura
 */
public class OrderviewModel {

    public List<OrderDTO> loadOrderViewTable() throws SQLException{
        ResultSet results = CrudUtil.execute("SELECT c.cloth_order_id, c.cloth_order_description, c.cloth_order_start_date, c.cloth_order_end_date, c.customer_id FROM cloth_order c JOIN order_serprovider_details os ON c.cloth_order_id = os.cloth_order_id");

        List<OrderDTO> orderList = new ArrayList<>();

        while(results.next()){
                int orderId = results.getInt("cloth_order_id");
                int cusId = results.getInt("customer_id");
                String detail = results.getString("cloth_order_description");
                String startDate = results.getString("cloth_order_start_date");
                String endDate = results.getString("cloth_order_end_date");

                OrderDTO orderDTO = new OrderDTO(orderId,cusId,detail,startDate,endDate);
                orderList.add(orderDTO);
            }

        return orderList;
    }
}
