package JDBC_Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnectionExample {
    public static void main(String[] args) throws SQLException {
        String url ="jdbc:mysql://localhost:3306/Students";

        String username = "root";
        String password = "Pratham@2911";

        try (Connection con = DriverManager.getConnection(url,username,password)){
            System.out.println("Connection To DataBase Successfully");
        }catch (SQLException e){
            System.err.println("Connection Fail To DataBase" + e.getMessage());
        }
    }


}
