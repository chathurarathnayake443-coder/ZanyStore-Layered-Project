package lk.ijse.zanystore.dao.custom;

import lk.ijse.zanystore.dto.TaskDTO;
import lk.ijse.zanystore.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface TaskDAO {

    public boolean save(TaskDTO task) throws SQLException;

    public List<TaskDTO> getAll() throws SQLException;

    public boolean delete(TaskDTO task) throws SQLException;
}
