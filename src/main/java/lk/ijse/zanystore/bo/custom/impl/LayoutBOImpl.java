package lk.ijse.zanystore.bo.custom.impl;

import lk.ijse.zanystore.dao.custom.ClothOrderDAO;
import lk.ijse.zanystore.dao.custom.PaymentDAO;
import lk.ijse.zanystore.dao.custom.ReturnDAO;
import lk.ijse.zanystore.dao.custom.TaskDAO;
import lk.ijse.zanystore.dao.custom.impl.ClothOrderDAOImpl;
import lk.ijse.zanystore.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.zanystore.dao.custom.impl.ReturnDAOImpl;
import lk.ijse.zanystore.dao.custom.impl.TaskDAOImpl;
import lk.ijse.zanystore.dto.TaskDTO;
import lk.ijse.zanystore.entity.Task;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LayoutBOImpl {

    ClothOrderDAO clothOrderDAO =  new ClothOrderDAOImpl();
    ReturnDAO returnDAO = new ReturnDAOImpl();
    PaymentDAO paymentDAO = new PaymentDAOImpl();
    TaskDAO taskDAO = new TaskDAOImpl();

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
}
