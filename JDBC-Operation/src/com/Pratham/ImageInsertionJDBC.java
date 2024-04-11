package com.Pratham;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class ImageInsertionJDBC {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String password = "Pratham@2911";
        String username = "root";

        String image_path = "C:\\Users\\Pratham\\Desktop\\antiRagging.png";

        String query ="INSERT INTO image_table(image_data) VALUES (?)";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded Successfully!!");

        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            System.out.println("Connection Establish Successfully");
            // FileInputStream for converting image into binary format
            FileInputStream fileInputStream = new FileInputStream(image_path);
            byte[] imageData = new byte[fileInputStream.available()];
            // store binary data on the imageData array
            fileInputStream.read(imageData);


            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBytes(1,imageData);
            int affectedRows = preparedStatement.executeUpdate();


            if (affectedRows > 0){
                System.out.println("Image inserted Successfully");
            }else {
                System.out.println("Image Not Found");
            }



            connection.close();
            System.out.println("Connection Close!!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
