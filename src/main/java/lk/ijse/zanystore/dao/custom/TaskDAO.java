package lk.ijse.zanystore.dao.custom;

import lk.ijse.zanystore.dao.CrudDAO;
import lk.ijse.zanystore.dto.TaskDTO;
import lk.ijse.zanystore.entity.Task;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface TaskDAO extends CrudDAO<Task> {

    public boolean save(Task task) throws SQLException;

    public List<Task> getAll() throws SQLException;

    public boolean delete(Task task) throws SQLException;
}
