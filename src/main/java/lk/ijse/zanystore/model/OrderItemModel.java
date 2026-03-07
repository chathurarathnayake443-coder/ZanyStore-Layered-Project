/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.zanystore.model;

import java.sql.SQLException;
import java.util.List;
import lk.ijse.zanystore.dto.OrderItemDTO;
import lk.ijse.zanystore.util.CrudUtil;

/**
 *
 * @author chathura
 */
public class OrderItemModel {

    private ItemModel itemModel = new ItemModel();
    public boolean saveOrderItems(List<OrderItemDTO> orderList, int id) throws SQLException{

        int orderId = id;

        for(OrderItemDTO orderItem : orderList){
            boolean result = CrudUtil.execute("INSERT INTO order_item_details (cloth_order_id, item_id, qty, price, color)VALUES (?,?,?,?,?)", orderId, orderItem.getItem_id(), orderItem.getQty(), orderItem.getPrice(), orderItem.getColor());

            if(result){
                boolean decreased = itemModel.decreaseItemQty(orderItem.getItem_id(), orderItem.getColor(), orderItem.getQty());

                if(!decreased){
                    throw new SQLException();
                }
            }
            else{
                throw new SQLException();
            }
        }
        return true;
    }
}
