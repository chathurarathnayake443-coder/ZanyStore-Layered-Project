package lk.ijse.zanystore.dao.custom.impl;

import lk.ijse.zanystore.dao.custom.TaskDAO;
import lk.ijse.zanystore.dto.TaskDTO;
import lk.ijse.zanystore.entity.Task;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDAOImpl implements TaskDAO {

    public boolean save(Task entity) throws SQLException {
        boolean result = CrudUtil.execute("INSERT INTO task (task_name, date) VALUES (?,?)",entity.getTask_name(),entity.getDate());
        return result;
    }

    @Override
    public boolean update(Task entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return CrudUtil.execute("DELETE FROM task WHERE task_id = ?", id);
    }

    public List<Task> getAll() throws SQLException{
        ResultSet result = CrudUtil.execute("SELECT * FROM task");

        List<Task> taskList = new ArrayList<>();
        while (result.next()){
            int task_id = result.getInt("task_id");
            String task_name = result.getString("task_name");
            String task_date = result.getString("date");
            taskList.add(new Task(task_id,task_name,task_date));
        }
        return taskList;
    }

    @Override
    public String showNextId() throws SQLException {
        return "";
    }

    @Override
    public Task find(int empId) throws SQLException {
        return null;
    }

    @Override
    public String getId() throws SQLException {
        return "";
    }

//    public boolean delete(Task task) throws SQLException {
//        boolean result = CrudUtil.execute("DELETE FROM task WHERE task_name = ? AND date = ?",task.getTask_name(),task.getDate());
//        return result;
//    }
}
