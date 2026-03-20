package lk.ijse.zanystore.bo.custom;

import lk.ijse.zanystore.dto.QueryDTO.LoadLastOrderDTO;
import lk.ijse.zanystore.dto.QueryDTO.LoadLastPaymentDTO;
import lk.ijse.zanystore.dto.QueryDTO.LoadLowStockDTO;
import lk.ijse.zanystore.dto.TaskDTO;
import lk.ijse.zanystore.entity.Task;
import lk.ijse.zanystore.bo.custom.SuperBO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface LayoutBO extends SuperBO {

    public int getOrderCount() throws SQLException;

    public int getReturnOrderCount() throws SQLException;

    public double getSumOfPayments() throws SQLException;

    public boolean addTask(TaskDTO taskDTO) throws SQLException;

    public List<TaskDTO> viewAllTasks() throws SQLException;

    public boolean deleteTask(TaskDTO taskDTO) throws SQLException;

    public LoadLastOrderDTO loadLastOrderTable() throws SQLException;

    public LoadLastPaymentDTO loadLastPaymentTable() throws SQLException;

    public List<LoadLowStockDTO> loadLowStockTable() throws SQLException;
}
