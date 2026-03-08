package lk.ijse.zanystore.bo.custom;

import lk.ijse.zanystore.db.DBConnection;
import lk.ijse.zanystore.entity.Return;
import lk.ijse.zanystore.entity.ReturnDetail;
import lk.ijse.zanystore.bo.custom.SuperBO;
import java.sql.Connection;
import java.sql.SQLException;

public interface ReturnBO extends SuperBO {

    public boolean saveReturn(String detail, String orderId, String date);

    public boolean deleteReturn(int returnId);

    public String generateNextReturnId() throws SQLException;
}
