package lk.ijse.zanystore.bo.custom;

import lk.ijse.zanystore.db.DBConnection;
import lk.ijse.zanystore.dto.OrderItemDTO;
import lk.ijse.zanystore.dto.QueryDTO.LoadItemDTO;
import lk.ijse.zanystore.entity.ClothOrder;
import lk.ijse.zanystore.entity.ItemColorStock;
import lk.ijse.zanystore.entity.OrderItem;
import lk.ijse.zanystore.entity.OrderSerprovider;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import lk.ijse.zanystore.bo.custom.SuperBO;

public interface PlaceOrderBO extends SuperBO {

    public boolean saveOrder(String customerId, String orderDetails, String sDate, String eDate, String gDate, String rDate, List<OrderItemDTO> orderItemDTOList);

    public List<String> loadItemColors(int itemId) throws SQLException;

    public String getItemNameFromId(int itemId) throws SQLException;

    public String generateNextOrderId() throws SQLException;

    public List<String> getAllId() throws SQLException;

    public List<LoadItemDTO> loadItemTable() throws SQLException;
}
