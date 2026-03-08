package lk.ijse.zanystore.bo.custom;

import lk.ijse.zanystore.dto.QueryDTO.LoadItemDTO;
import lk.ijse.zanystore.bo.custom.SuperBO;
import java.sql.SQLException;
import java.util.List;

public interface QueryBO extends SuperBO {

    public List<LoadItemDTO> loadItemTable() throws SQLException;
}
