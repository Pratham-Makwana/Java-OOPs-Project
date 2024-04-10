package com.Pratham;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImageInsertionDatabase {
    public static void main(String[] args) {


        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "Pratham@2911";
        String image_path = "C:\\Users\\Pratham\\Desktop\\antiragging.png";
        String query = "INSERT INTO image_table(image_data) VALUES (?)";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded Successfully ");

        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }


        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            System.out.println("Connection Establish Successfully");

            FileInputStream fileInputStream = new FileInputStream(image_path);
            byte[] imageData = new byte[fileInputStream.available()];
            fileInputStream.read(imageData);

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBytes(1,imageData);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0){
                System.out.println("Image Inserted Successful");
            }else {
                System.out.println("Image not Inserted");
            }


        }catch (SQLException e){
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
