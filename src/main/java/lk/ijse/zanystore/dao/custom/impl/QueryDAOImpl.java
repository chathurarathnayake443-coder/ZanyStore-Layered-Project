package lk.ijse.zanystore.dao.custom.impl;

import lk.ijse.zanystore.dao.custom.QueryDAO;
import lk.ijse.zanystore.dto.QueryDTO.LoadItemDTO;
import lk.ijse.zanystore.dto.QueryDTO.LoadLastOrderDTO;
import lk.ijse.zanystore.dto.QueryDTO.LoadLastPaymentDTO;
import lk.ijse.zanystore.dto.QueryDTO.LoadLowStockDTO;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {

    //for ItemController
    public List<LoadItemDTO> loadItemTable() throws SQLException {
        ResultSet results = CrudUtil.execute("SELECT ics.item_id, i.item_name, i.item_type, i.item_unit_price, ics.color, ics.qty " +
                "FROM item_color_stock ics JOIN item i ON ics.item_id = i.item_id");

        List<LoadItemDTO> list = new ArrayList<>();
        while (results.next()) {
            int itemId = results.getInt("item_id");
            String itemName = results.getString("item_name");
            String itemType = results.getString("item_type");
            int itemQty = results.getInt("qty");
            double price = results.getDouble("item_unit_price");
            String color = results.getString("color");

            list.add(new LoadItemDTO(itemId,itemName,itemType,price,color,itemQty));
        }
        return list;
    }

    //for LayoutController -> loadLastOrder();
    public LoadLastOrderDTO loadLastOrderTable() throws SQLException {
        ResultSet results = CrudUtil.execute("SELECT o.cloth_order_id, c.customer_name, o.cloth_order_description, o.cloth_order_end_date FROM cloth_order o JOIN customer c ON o.customer_id = c.customer_id ORDER BY cloth_order_id DESC LIMIT 1");
        if(results.next()) {
            int orderId = results.getInt("cloth_order_id");
            String customerName = results.getString("customer_name");
            String clothOrderDescription = results.getString("cloth_order_description");
            String clothOrderEndDate = results.getString("cloth_order_end_date");

            return new LoadLastOrderDTO(orderId,customerName,clothOrderDescription,clothOrderEndDate);
        }
        return null;
    }

    //for LayoutController -> loadLastPayment();
    public LoadLastPaymentDTO loadLastPaymentTable() throws SQLException {
        ResultSet result = CrudUtil.execute("SELECT p.payment_id, p.payment_amount, c.customer_name, o.date FROM payment p JOIN order_payment_details o ON p.payment_id = o.payment_id JOIN cloth_order co on o.cloth_order_id = co.cloth_order_id JOIN customer c on o.customer_id = c.customer_id and co.customer_id = c.customer_id ORDER BY p.payment_id DESC LIMIT 1");
        if(result.next()) {
            int paymentId = result.getInt("payment_id");
            double paymentAmount = result.getDouble("payment_amount");
            String customerName = result.getString("customer_name");
            String paymentEndDate = result.getString("date");

            return new LoadLastPaymentDTO(paymentId,paymentAmount,customerName,paymentEndDate);
        }
        return null;
    }

    //for LowStockController -> loadItemTable();
    public List<LoadLowStockDTO> loadLowStockTable() throws SQLException {
        ResultSet results = CrudUtil.execute("SELECT\n" +
                "    i.item_id,\n" +
                "    i.item_name,\n" +
                "    ics.color,\n" +
                "    ics.qty AS remaining_qty\n" +
                "FROM item_color_stock ics\n" +
                "JOIN item i\n" +
                "    ON i.item_id = ics.item_id\n" +
                "WHERE ics.qty <= 200\n" +
                "ORDER BY ics.qty ASC;");
        List<LoadLowStockDTO> list = new ArrayList<>();
        while (results.next()) {
            int itemId = results.getInt("item_id");
            String itemName = results.getString("item_name");
            String color = results.getString("color");
            int qty = results.getInt("remaining_qty");

            list.add(new LoadLowStockDTO(itemId,itemName,color,qty));
        }
        return list;
    }

}
