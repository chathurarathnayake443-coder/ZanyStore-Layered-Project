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
public class ItemModel {
    public boolean decreaseItemQty(int itemId, String color, int qty) throws SQLException{

       boolean result = CrudUtil.execute("UPDATE item_color_stock SET qty = qty - ? WHERE item_id = ? AND color = ? AND qty >= ?", qty, itemId, color, qty);
       return result;
   }
}

