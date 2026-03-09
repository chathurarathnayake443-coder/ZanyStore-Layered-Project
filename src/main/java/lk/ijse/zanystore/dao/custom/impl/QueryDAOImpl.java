package lk.ijse.zanystore.dao.custom.impl;

import lk.ijse.zanystore.dao.custom.QueryDAO;
import lk.ijse.zanystore.dto.OrderDTO;
import lk.ijse.zanystore.dto.QueryDTO.*;
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

    //for PaymentController -> loadPaymentTable();
    public List<LoadPaymentDTO> loadPaymentTable() throws SQLException {
        ResultSet results = CrudUtil.execute("SELECT p.payment_id, p.payment_amount, o.cloth_order_id, c.customer_name, o.date FROM payment p JOIN order_payment_details o ON p.payment_id = o.payment_id JOIN cloth_order co on o.cloth_order_id = co.cloth_order_id JOIN customer c on o.customer_id = c.customer_id and co.customer_id = c.customer_id");
        List<LoadPaymentDTO> list = new ArrayList<>();
        while(results.next()){
            int paymentId = results.getInt("payment_id");
            double paymentAmount = results.getDouble("payment_amount");
            int orderId = results.getInt("cloth_order_id");
            String customerName = results.getString("customer_name");
            String date = results.getString("date");

            list.add(new LoadPaymentDTO(paymentId,orderId,paymentAmount,customerName,date));
        }
        return list;
    }

    //for QuotationController -> loadItemColors();
    public List<String> loadItemColorsTable(String itemName) throws SQLException {
        ResultSet results = CrudUtil.execute("SELECT ics.color FROM item i JOIN item_color_stock ics ON i.item_id = ics.item_id WHERE i.item_name = ?", itemName);
        List<String> list = new ArrayList<>();
        while (results.next()) {
            list.add(results.getString("color"));
        }
        return list;
    }

    //for ReturnController -> loadReturnTable();
    public List<LoadReturnDTO> loadReturnTable() throws SQLException {
        ResultSet results = CrudUtil.execute("SELECT r.return_order_id, o.cloth_order_id, r.return_order_details, c.customer_name, c.customer_address, ro.order_return_date FROM return_order_details ro JOIN return_order r ON ro.return_order_id = r.return_order_id JOIN cloth_order o ON ro.cloth_order_id = o.cloth_order_id JOIN customer c ON c.customer_id = o.customer_id");
        List<LoadReturnDTO> list = new ArrayList<>();
        while (results.next()) {
            int returnOrderId = results.getInt("return_order_id");
            int clothOrderId = results.getInt("cloth_order_id");
            String returnOrderDetails = results.getString("return_order_details");
            String customerName = results.getString("customer_name");
            String customerAddress = results.getString("customer_address");
            String date = results.getString("order_return_date");

            list.add(new LoadReturnDTO(returnOrderId,clothOrderId,returnOrderDetails,customerName,customerAddress,date));
        }
        return list;
    }

    //for UsedItemsController -> loadOtherDetailsTable();
    public LoadOtherDetailsDTO loadOtherDetailsTable(int id) throws SQLException {
        ResultSet results = CrudUtil.execute("SELECT customer.customer_name, payment.payment_amount, cloth_order.cloth_order_end_date FROM cloth_order JOIN customer ON customer.customer_id = cloth_order.customer_id LEFT JOIN order_payment_details ON cloth_order.cloth_order_id = order_payment_details.cloth_order_id LEFT JOIN payment ON order_payment_details.payment_id = payment.payment_id WHERE cloth_order.cloth_order_id = ?",id);
        if (results.next()) {
            String customerName = results.getString("customer_name");
            double paymentAmount = results.getDouble("payment_amount");
            String clothOrderEndDate = results.getString("cloth_order_end_date");

            return new LoadOtherDetailsDTO(customerName,paymentAmount,clothOrderEndDate);
        }
        return null;
    }

    //for UsedItemsController -> loadItemDetailTable();
    public List<LoadItemDetailDTO> loadItemDetailTable(int id) throws SQLException {
        ResultSet results = CrudUtil.execute("SELECT oid.color, oid.qty, i.item_name FROM order_item_details oid JOIN item i ON i.item_id = oid.item_id JOIN item_color_stock ics ON ics.item_id = oid.item_id AND ics.color = oid.color WHERE oid.cloth_order_id = ?",id);
        List<LoadItemDetailDTO> list = new ArrayList<>();
        while (results.next()) {
            String color = results.getString("color");
            int qty = results.getInt("qty");
            String itemName = results.getString("item_name");

            list.add(new LoadItemDetailDTO(color,qty,itemName));
        }
        return list;
    }

    //for OrderViewController -> loadOrderViewTable();
    public List<LoadOrderViewDTO> loadOrderViewTable() throws SQLException{
        ResultSet results = CrudUtil.execute("SELECT c.cloth_order_id, c.cloth_order_description, c.cloth_order_start_date, c.cloth_order_end_date, c.customer_id FROM cloth_order c JOIN order_serprovider_details os ON c.cloth_order_id = os.cloth_order_id");

        List<LoadOrderViewDTO> orderList = new ArrayList<>();

        while(results.next()){
            int orderId = results.getInt("cloth_order_id");
            int cusId = results.getInt("customer_id");
            String detail = results.getString("cloth_order_description");
            String startDate = results.getString("cloth_order_start_date");
            String endDate = results.getString("cloth_order_end_date");

            orderList.add(new LoadOrderViewDTO(orderId,detail,startDate,endDate,cusId));
        }

        return orderList;
    }

}
