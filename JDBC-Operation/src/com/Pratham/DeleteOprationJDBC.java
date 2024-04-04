package com.Pratham;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteOprationJDBC {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "Pratham@2911";
        String query = "DELETE FROM employees where id = 3";
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
                System.out.println("Delete Successfully " + rowsAffected + "row(s) affected");
            }else {
                System.out.println("Deletion Failed!! ");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
