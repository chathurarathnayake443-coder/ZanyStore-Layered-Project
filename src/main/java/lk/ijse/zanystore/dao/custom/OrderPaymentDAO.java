package lk.ijse.zanystore.dao.custom;

import lk.ijse.zanystore.dto.PaymentDTO;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.SQLException;

public interface OrderPaymentDAO {

    public boolean save(PaymentDTO paymentDTO) throws SQLException;
}
