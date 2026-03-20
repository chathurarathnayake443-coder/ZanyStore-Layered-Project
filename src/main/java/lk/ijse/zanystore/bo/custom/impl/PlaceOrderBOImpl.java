package lk.ijse.zanystore.bo.custom.impl;

import lk.ijse.zanystore.bo.custom.PlaceOrderBO;
import lk.ijse.zanystore.dao.DAOFactory;
import lk.ijse.zanystore.dao.custom.*;
import lk.ijse.zanystore.dao.custom.impl.*;
import lk.ijse.zanystore.db.DBConnection;
import lk.ijse.zanystore.dto.ItemColorStockDTO;
import lk.ijse.zanystore.dto.OrderDTO;
import lk.ijse.zanystore.dto.OrderItemDTO;
import lk.ijse.zanystore.dto.OrderSerproviderDTO;
import lk.ijse.zanystore.dto.QueryDTO.LoadItemDTO;
import lk.ijse.zanystore.dto.QueryDTO.LoadItemDetailDTO;
import lk.ijse.zanystore.dto.QueryDTO.LoadOrderViewDTO;
import lk.ijse.zanystore.dto.QueryDTO.LoadOtherDetailsDTO;
import lk.ijse.zanystore.entity.ClothOrder;
import lk.ijse.zanystore.entity.ItemColorStock;
import lk.ijse.zanystore.entity.OrderItem;
import lk.ijse.zanystore.entity.OrderSerprovider;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO {

    ClothOrderDAO clothOrderDAO = (ClothOrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CLOTH_ORDER);
    ClothOrderDetailDAO clothOrderDetailDAO = (ClothOrderDetailDAO)DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CLOTH_ORDER_DETAIL);
    ItemColorStockDAO itemColorStockDAO = (ItemColorStockDAO)DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM_COLOR_STOCK);
    OrderSerproviderDAO orderSerproviderDAO = (OrderSerproviderDAO)DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CLOTH_ORDER_SERPROVIDER);
    ItemDAO itemDAO = (ItemDAO)DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);

    public boolean saveOrder(String customerId, String orderDetails, String sDate, String eDate, String gDate, String rDate, List<OrderItemDTO> orderItemDTOList) {
        Connection connection = null;
        try{
            connection= DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean b1 = clothOrderDAO.save(new ClothOrder(orderDetails, sDate, eDate,Integer.parseInt(customerId)));

            if (!b1) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            String orderId = clothOrderDAO.getId();

            for (OrderItemDTO orderItemDTO : orderItemDTOList) {
                boolean b2 = clothOrderDetailDAO.save(new OrderItem(Integer.parseInt(orderId),orderItemDTO.getItem_id(),orderItemDTO.getQty(),orderItemDTO.getPrice(),orderItemDTO.getColor()));

                if (!b2) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }

                boolean b3 = itemColorStockDAO.decreaseQty(new ItemColorStock(orderItemDTO.getItem_id(),orderItemDTO.getColor(),orderItemDTO.getQty()));

                if (!b3) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }

            boolean b4 = orderSerproviderDAO.save(new OrderSerprovider(Integer.parseInt(orderId),gDate,rDate));

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

    public List<String> loadItemColors(int itemId) throws SQLException {
        List<String> colorList = itemColorStockDAO.getColorsById(itemId);
        return colorList;
    }

    public String getItemNameFromId(int itemId) throws SQLException {
        String name = itemDAO.getNameById(itemId);
        return name;
    }

    public String generateNextOrderId() throws SQLException {
        String id = clothOrderDAO.showNextId();
        return id;
    }

    public List<String> getAllId() throws SQLException {
        List<String> list = clothOrderDAO.getAllId();
        return list;
    }

    public List<LoadItemDTO> loadItemTable() throws SQLException {
        List<LoadItemDTO> list = queryDAO.loadItemTable();
        return list;
    }

    public LoadOtherDetailsDTO loadOtherDetailsTable(int id) throws SQLException {
        LoadOtherDetailsDTO dto = queryDAO.loadOtherDetailsTable(id);
        return dto;
    }

    public List<LoadItemDetailDTO> loadItemDetailTable(int id) throws SQLException {
        List<LoadItemDetailDTO> list = queryDAO.loadItemDetailTable(id);
        return list;
    }

    public List<LoadOrderViewDTO> loadOrderViewTable() throws SQLException {
        List<LoadOrderViewDTO> list = queryDAO.loadOrderViewTable();
        return list;
    }
}
