package com.Pratham;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PreparedStatement {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "Pratham@2911";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully");
        }catch (ClassNotFoundException e){
            e.getMessage();
        }

        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            System.out.println("Connection Successfully");

            String query = "Select * from employees where name = ? and job_title = ?";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,"hemant");
            preparedStatement.setString(2,"Full-Stack Developer");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String job_title = resultSet.getString("job_title");
                double salary = resultSet.getDouble("salary");
                System.out.println("ID: "+ id );
                System.out.println("Name: "+ name );
                System.out.println("Job_title: "+ job_title );
                System.out.println("Salary: "+ salary );
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
            System.out.println();
            System.out.println("Connecton Close Successfully");
        }catch (SQLException e){
            e.getMessage();
        }
    }
}
