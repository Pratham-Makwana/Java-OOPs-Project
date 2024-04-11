package com.Pratham;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class BatchProcessing {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "Pratham@2911";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded Successfully");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            System.out.println("Connection Establish Successfully!!");
            connection.setAutoCommit(false);
            String query = "INSERT INTO employees (name, job_title, salary) VALUES ( ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            while (true){
            Scanner scanner = new Scanner(System.in);
                System.out.print("Name: ");
                String name = scanner.nextLine();
                System.out.print("Job_title: ");
                String job_title = scanner.nextLine();
                System.out.print("Salary: ");
                double salary = scanner.nextDouble();



                preparedStatement.setString(1,name);
                preparedStatement.setString(2,job_title);
                preparedStatement.setDouble(3,salary);
                scanner.nextLine();
                preparedStatement.addBatch();
                System.out.print("Add More values Y/N: ");
                String decision = scanner.nextLine();
                if (decision.toUpperCase().equals("N")){
                    break;
                }

            }

            int[] bacthResult = preparedStatement.executeBatch();
            connection.commit();
            System.out.println("Batch Executed Successfully");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
