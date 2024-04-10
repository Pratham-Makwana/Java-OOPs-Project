package com.Pratham;

import java.io.*;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.SortedMap;

public class ImageRetrieverDatabase {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "Pratham@2911";
        String folder_path = "C:\\Users\\Pratham\\Desktop\\";
        String query = "SELECT image_data from image_table where image_url = (?)";


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded Successfully");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            System.out.println("Connection Establish Successfully");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,1);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                byte[] image_Data = resultSet.getBytes("image_data");
                String image_path = folder_path + "antiRagging.png";
                OutputStream outputStream = new FileOutputStream(image_path);
                outputStream.write(image_Data);
                System.out.println("Image Founded!!!");
            }else {
                System.out.println("Image Not Found");
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
