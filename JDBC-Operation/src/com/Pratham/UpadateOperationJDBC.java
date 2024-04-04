package com.Pratham;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpadateOperationJDBC {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "Pratham@2911";
        String query = "UPDATE employees SET job_title = 'Full-Stack Developer', salary = 70000 where id = 2";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded Successfully");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url,username,password);
            Statement stm = con.createStatement();
            int rowsAffected = stm.executeUpdate(query);

            if (rowsAffected > 0){
                System.out.println("Update Successfully " + rowsAffected + "row(s) affected");
            }else {
                System.out.println("update Failed!! ");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
