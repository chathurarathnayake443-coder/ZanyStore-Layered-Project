package lk.ijse.zanystore.dao.custom.impl;

import lk.ijse.zanystore.dao.custom.TaskDAO;
import lk.ijse.zanystore.dto.TaskDTO;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDAOImpl implements TaskDAO {

    public boolean save(TaskDTO task) throws SQLException {
        boolean result = CrudUtil.execute("INSERT INTO task (task_name, date) VALUES (?,?)",task.getTask_name(),task.getTask_date());
        return result;
    }

    public List<TaskDTO> getAll() throws SQLException{
        ResultSet result = CrudUtil.execute("SELECT * FROM task");

        List<TaskDTO> taskList = new ArrayList<>();
        while (result.next()){
            int task_id = result.getInt("task_id");
            String task_name = result.getString("task_name");
            String task_date = result.getString("date");
            taskList.add(new TaskDTO(task_id,task_name,task_date));
        }
        return taskList;
    }

    public boolean delete(TaskDTO task) throws SQLException {
        boolean result = CrudUtil.execute("DELETE FROM task WHERE task_name = ? AND date = ?",task.getTask_name(),task.getTask_date());
        return result;
    }
}
