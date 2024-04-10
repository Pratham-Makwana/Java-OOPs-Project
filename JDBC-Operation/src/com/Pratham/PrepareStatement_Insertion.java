package com.Pratham;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrepareStatement_Insertion {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "Pratham@2911";
        String query = "INSERT INTO employees (id, name, job_title, salary) VALUES (?,?, ?, ?)";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded Successfully");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }


        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,3);
            preparedStatement.setString(2,"Prabhat");
            preparedStatement.setString(3,"DevOps Engineer");
            preparedStatement.setDouble(4,80000.00);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0){
                System.out.println("Data Inserted Successfully");

            }else {
                System.out.println("Data Insertion Failed!!");
            }
        }catch (SQLException  e){
            System.out.println(e.getMessage());
        }
    }
}
