package lk.ijse.zanystore.bo.custom.impl;

import lk.ijse.zanystore.dao.custom.ClothOrderDAO;
import lk.ijse.zanystore.dao.custom.ClothOrderDetailDAO;
import lk.ijse.zanystore.dao.custom.ItemColorStockDAO;
import lk.ijse.zanystore.dao.custom.OrderSerproviderDAO;
import lk.ijse.zanystore.dao.custom.impl.ClothOrderDAOImpl;
import lk.ijse.zanystore.dao.custom.impl.ClothOrderDetailDAOImpl;
import lk.ijse.zanystore.dao.custom.impl.ItemColorStockDAOImpl;
import lk.ijse.zanystore.dao.custom.impl.OrderSerproviderDAOImpl;
import lk.ijse.zanystore.db.DBConnection;
import lk.ijse.zanystore.dto.ItemColorStockDTO;
import lk.ijse.zanystore.dto.OrderDTO;
import lk.ijse.zanystore.dto.OrderItemDTO;
import lk.ijse.zanystore.dto.OrderSerproviderDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PlaceOrderBOImpl {

    ClothOrderDAO clothOrderDAO = new ClothOrderDAOImpl();
    ClothOrderDetailDAO clothOrderDetailDAO = new ClothOrderDetailDAOImpl();
    ItemColorStockDAO itemColorStockDAO = new ItemColorStockDAOImpl();
    OrderSerproviderDAO orderSerproviderDAO = new OrderSerproviderDAOImpl();

    public boolean saveOrder(String customerId, String orderDetails, String sDate, String eDate, String gDate, String rDate, List<OrderItemDTO> orderItemDTOList) {
        Connection connection = null;
        try{
            connection= DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean b1 = clothOrderDAO.save(new OrderDTO(Integer.parseInt(customerId), orderDetails, sDate, eDate));

            if (!b1) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            String orderId = clothOrderDAO.getId();

            for (OrderItemDTO orderItemDTO : orderItemDTOList) {
                boolean b2 = clothOrderDetailDAO.save(new OrderItemDTO(Integer.parseInt(orderId),orderItemDTO.getItem_id(),orderItemDTO.getQty(),orderItemDTO.getColor()));

                if (!b2) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }

                boolean b3 = itemColorStockDAO.decreaseQty(new ItemColorStockDTO(orderItemDTO.getItem_id(),orderItemDTO.getColor(),orderItemDTO.getQty()));

                if (!b3) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }

            boolean b4 = orderSerproviderDAO.save(new OrderSerproviderDTO(Integer.parseInt(orderId),gDate,rDate));

            if (!b4) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            connection.commit();
            connection.setAutoCommit(true);
        }
        catch (Exception e) {
            try { if (connection != null) connection.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
        } finally {
            try { if (connection != null) connection.setAutoCommit(true); } catch (SQLException ex) { ex.printStackTrace(); }
        }
        return true;
    }
}
