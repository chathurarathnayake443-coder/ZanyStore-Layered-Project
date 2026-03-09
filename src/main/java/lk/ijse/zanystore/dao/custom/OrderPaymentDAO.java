package lk.ijse.zanystore.dao.custom;

import lk.ijse.zanystore.dao.CrudDAO;
import lk.ijse.zanystore.dto.PaymentDTO;
import lk.ijse.zanystore.entity.OrderPayment;
import lk.ijse.zanystore.entity.Payment;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.SQLException;

public interface OrderPaymentDAO extends CrudDAO<OrderPayment> {

}
