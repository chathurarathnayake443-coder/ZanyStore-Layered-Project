package lk.ijse.zanystore.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    final String DB_URL = "jdbc:mysql://localhost:3306/zany_store";
    final String DB_USER = "root";
    final String DB_PASSWORD = "mysql";
    
    private static Connection connection;
    private static DBConnection dbc;
    
    private DBConnection() throws SQLException{
        Connection conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
        connection = conn;
    }
    
    public static Connection getConnection(){
        return connection;
    }
    
    public static DBConnection getInstance() throws SQLException{
        if(dbc == null){
            dbc = new DBConnection();
        }
        return dbc;
    }
}
