package lk.ijse.zanystore.bo.custom;

import lk.ijse.zanystore.db.DBConnection;
import lk.ijse.zanystore.dto.QueryDTO.LoadReturnDTO;
import lk.ijse.zanystore.entity.Return;
import lk.ijse.zanystore.entity.ReturnDetail;
import lk.ijse.zanystore.bo.custom.SuperBO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ReturnBO extends SuperBO {

    public boolean saveReturn(String detail, String orderId, String date);

    public boolean deleteReturn(int returnId);

    public String generateNextReturnId() throws SQLException;

    public List<LoadReturnDTO> loadReturnTable() throws SQLException;
}
