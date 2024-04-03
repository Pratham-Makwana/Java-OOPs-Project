package JDBC_Test;

import java.sql.*;

public class InsertOperationJDBC {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "Pratham@2911";
        String query = "INSERT INTO employees(id,name,job_title,salary) VALUES (3,'Harshite','FullStack Developer',85000.0)";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Loaded Successfully");
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {

            Connection con = DriverManager.getConnection(url,username,password);
            System.out.println("Connection Successfully");
            Statement stm = con.createStatement();
            int rowsAffected = stm.executeUpdate(query);

            if (rowsAffected > 0){
                System.out.println("Inset Successfully " + rowsAffected + " row(s) affected");
            }else {
                System.out.println("Insertion Failed!!");
            }
            stm.close();
            con.close();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
