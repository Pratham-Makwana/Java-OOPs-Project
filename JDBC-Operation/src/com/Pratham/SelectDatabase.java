package com.Pratham;

import java.sql.*;

public class SelectDatabase {
    public static void main(String[] args)  {

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "Pratham@2911";
        String query = "Select * from employees";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

            try {
                Connection con = DriverManager.getConnection(url,username,password);
                System.out.println("Connection Successfully");
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(query);
                while (rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String job_title = rs.getString("job_title");
                    double salary = rs.getDouble("salary");

                    System.out.println();
                    System.out.println("=============");
                    System.out.println("ID: " + id);
                    System.out.println("Name: " + name);
                    System.out.println("job-title: " + job_title);
                    System.out.println("Salary: " + salary);
                }

                rs.close();
                stm.close();
                con.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }


    }
}
