/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.zanystore.util;

/**
 *
 * @author chathura
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lk.ijse.zanystore.db.DBConnection;

public class CrudUtil {
    
    public static <T> T  execute(String sql, Object...object) throws SQLException{
        Connection conn = DBConnection.getInstance().getConnection();
        
        PreparedStatement pstm = conn.prepareStatement(sql);
            
            for(int i = 0; i < object.length; i++){
                pstm.setObject(i + 1, object[i]);
            }
            
            if((sql.startsWith("select") || sql.startsWith("SELECT"))){
                ResultSet results = pstm.executeQuery();
                return (T)results;
            }
            else{
                int result = pstm.executeUpdate();
                return (T)(Boolean)(result > 0);
            }
    }
}

