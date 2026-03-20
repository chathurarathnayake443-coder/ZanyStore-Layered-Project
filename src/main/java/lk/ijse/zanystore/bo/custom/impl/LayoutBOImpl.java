package lk.ijse.zanystore.bo.custom.impl;

import lk.ijse.zanystore.bo.custom.LayoutBO;
import lk.ijse.zanystore.dao.DAOFactory;
import lk.ijse.zanystore.dao.custom.*;
import lk.ijse.zanystore.dao.custom.impl.ClothOrderDAOImpl;
import lk.ijse.zanystore.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.zanystore.dao.custom.impl.ReturnDAOImpl;
import lk.ijse.zanystore.dao.custom.impl.TaskDAOImpl;
import lk.ijse.zanystore.dto.QueryDTO.LoadLastOrderDTO;
import lk.ijse.zanystore.dto.QueryDTO.LoadLastPaymentDTO;
import lk.ijse.zanystore.dto.QueryDTO.LoadLowStockDTO;
import lk.ijse.zanystore.dto.TaskDTO;
import lk.ijse.zanystore.entity.Task;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LayoutBOImpl implements LayoutBO {

    ClothOrderDAO clothOrderDAO =  (ClothOrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CLOTH_ORDER);
    ReturnDAO returnDAO = (ReturnDAO)DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RETURN);
    PaymentDAO paymentDAO = (PaymentDAO)DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);
    TaskDAO taskDAO = (TaskDAO)DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.TASK);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);

    public int getOrderCount() throws SQLException {
        return clothOrderDAO.getOrderCount();
    }

    public int getReturnOrderCount() throws SQLException {
        return returnDAO.getTotalCount();
    }

    public double getSumOfPayments() throws SQLException {
        return paymentDAO.getSum();
    }

    public boolean addTask(TaskDTO taskDTO) throws SQLException {
        return taskDAO.save(new Task(taskDTO.getTask_name(),taskDTO.getTask_date()));
    }

    public List<TaskDTO> viewAllTasks() throws SQLException {
        List<Task> list = taskDAO.getAll();
        List<TaskDTO> taskList = new ArrayList<>();
        for (Task task : list) {
            taskList.add(new TaskDTO(task.getTask_id(),task.getTask_name(),task.getDate()));
        }
        return taskList;
    }

    public boolean deleteTask(TaskDTO taskDTO) throws SQLException {
        boolean result = taskDAO.delete(taskDTO.getTask_id());
        return result;
    }

    public LoadLastOrderDTO loadLastOrderTable() throws SQLException {
        LoadLastOrderDTO dto = queryDAO.loadLastOrderTable();
        return dto;
    }

    public LoadLastPaymentDTO loadLastPaymentTable() throws SQLException {
        LoadLastPaymentDTO dto = queryDAO.loadLastPaymentTable();
        return dto;
    }

    public List<LoadLowStockDTO> loadLowStockTable() throws SQLException {
        List<LoadLowStockDTO> list = queryDAO.loadLowStockTable();
        return list;
    }
}
